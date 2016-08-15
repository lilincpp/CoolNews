package lilin.coolnews.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lilin.coolnews.Base.BaseActivity;
import lilin.coolnews.R;
import lilin.coolnews.model.ChannelModel;
import lilin.coolnews.model.LNews;
import lilin.coolnews.model.NewsModel;
import lilin.coolnews.ui.detail.DetailAdapter;
import lilin.coolnews.utils.LContentUtil;

public class HomeActivity extends BaseActivity {
    private static final String TAG = "HomeActivity";

    @BindView(R.id.tablayout)
    TabLayout mTablayout;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;


    public boolean mIsTablet = false;
    private RecyclerView mRvDetail;
    private DetailAdapter mDetailAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);

        List<ChannelModel.Channel> channelList = ChannelModel.Channel.getAll();
        HomeViewPagerAdapter homeViewPagerAdapter = new HomeViewPagerAdapter(getSupportFragmentManager(), channelList);
        mViewPager.setAdapter(homeViewPagerAdapter);
        mTablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTablayout.setupWithViewPager(mViewPager);

        mIsTablet = findViewById(R.id.rv_main) != null;


        if (mIsTablet) {
            LNews news;
            if (savedInstanceState != null) {
                news = (LNews) savedInstanceState.getSerializable("old_data");
            } else {
                List<NewsModel> news1 = NewsModel.getNews(channelList.get(0).getName());
                news = (news1.size() != 0) ? LContentUtil.read(news1.get(news1.size() - 1).getmJson()).get(0) : new LNews();
            }


            mRvDetail = (RecyclerView) findViewById(R.id.rv_main);
            mDetailAdapter = new DetailAdapter(this, news);
            mRvDetail.setLayoutManager(new LinearLayoutManager(this));
            mRvDetail.setAdapter(mDetailAdapter);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mDetailAdapter != null) {
            outState.putSerializable("old_data", mDetailAdapter.getNowData());
        }
    }

    public void updateDetail(LNews lNews) {
        if (mIsTablet) {
            mDetailAdapter.update(lNews);
        }
    }


}
