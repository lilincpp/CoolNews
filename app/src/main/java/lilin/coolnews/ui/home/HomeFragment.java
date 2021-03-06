package lilin.coolnews.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lilin.coolnews.R;
import lilin.coolnews.base.BaseFragment;
import lilin.coolnews.model.LNews;
import lilin.coolnews.ui.detail.DetailActivity;

/**
 * Created by lilin on 2016/8/10.
 */
public class HomeFragment extends BaseFragment implements HomeContract.View, SwipeRefreshLayout.OnRefreshListener, NewsAdapter
        .LOnClickListener {
    private static final String TAG = "HomeFragment";

    private static final String CHANNEL_NAME_KEY = "channel_name_key";
    @BindView(R.id.rv_news)
    RecyclerView mRvNews;
    @BindView(R.id.pb)
    ContentLoadingProgressBar mPb;
    @BindView(R.id.tv_tips)
    TextView mTvTips;
    @BindView(R.id.refreshlayout)
    SwipeRefreshLayout mRefreshlayout;
    private String mChannelName;
    private NewsAdapter mNewsAdapter;


    private HomeContract.Presenter mHomePresenter;

    public static HomeFragment getInsance(String channelName) {
        HomeFragment homeFragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(CHANNEL_NAME_KEY, channelName);
        homeFragment.setArguments(bundle);
        return homeFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        new HomePresenter(this);
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mChannelName = getArguments().getString(CHANNEL_NAME_KEY);
        mNewsAdapter = new NewsAdapter(new ArrayList<LNews>(), this);
        mRvNews.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvNews.setAdapter(mNewsAdapter);

        mRefreshlayout.setOnRefreshListener(this);
        mRefreshlayout.setColorSchemeResources(R.color.colorPrimary);

    }

    @Override
    public void onResume() {
        super.onResume();
        if (mHomePresenter != null) {
            mHomePresenter.loadNews(getActivity(), mChannelName);
        }
    }

    @Override
    public void setPresent(HomeContract.Presenter presenter) {
        mHomePresenter = presenter;
    }

    @Override
    public void show(List<LNews> newses) {
        if (newses != null) {
            mNewsAdapter.update(newses);
        }
        mRvNews.setVisibility(View.VISIBLE);
        mTvTips.setVisibility(View.GONE);
        mPb.hide();
        if (mRefreshlayout.isRefreshing()) {
            mRefreshlayout.setRefreshing(false);
            Snackbar.make(mRefreshlayout, R.string.home_refreshed, Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public void loadFail() {
        if (mNewsAdapter.getItemCount() == 0) {
            mTvTips.setText(getString(R.string.home_error_load));
            mTvTips.setVisibility(View.VISIBLE);
            mPb.hide();
        } else {
            Snackbar.make(mRefreshlayout, R.string.home_error_load, Snackbar.LENGTH_SHORT).show();
        }

        if (mRefreshlayout.isRefreshing()) {
            mRefreshlayout.setRefreshing(false);
        }
    }


    @Override
    public void onRefresh() {
        if (mHomePresenter != null) {
            mHomePresenter.refresh(getActivity(), mChannelName);
        }
    }

    @Override
    public void onClick(int position, LNews lNews) {
        HomeActivity homeActivity = (HomeActivity) getActivity();
        if (homeActivity.mIsTablet) {
            homeActivity.updateDetail(lNews);
        } else {
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra("news", lNews);
            getActivity().startActivity(intent);
        }

    }
}
