package lilin.coolnews.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import lilin.coolnews.Base.BaseFragment;
import lilin.coolnews.R;

/**
 * Created by lilin on 2016/8/10.
 */
public class HomeFragment extends BaseFragment implements HomeContract.View {

    @BindView(R.id.tv)
    TextView tv;

    private HomeContract.Presenter mHomePresenter;

    public static HomeFragment getInsance(String name) {
        HomeFragment homeFragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        homeFragment.setArguments(bundle);
        return homeFragment;
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
        String name = getArguments().getString("name");
        tv.setText(name);
    }

    @Override
    public void setPresent(HomeContract.Presenter presenter) {
        mHomePresenter = presenter;
    }
}
