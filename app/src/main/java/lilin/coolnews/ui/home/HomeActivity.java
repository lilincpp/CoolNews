package lilin.coolnews.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lilin.coolnews.Base.BaseActivity;
import lilin.coolnews.R;
import lilin.coolnews.model.ChannelModel;

public class HomeActivity extends BaseActivity {
    private static final String TAG = "HomeActivity";

    @BindView(R.id.tablayout)
    TabLayout mTablayout;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;


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

    }


}
