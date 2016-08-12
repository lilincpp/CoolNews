package lilin.coolnews.ui.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import lilin.coolnews.model.ChannelModel;

/**
 * Created by lilin on 2016/8/10.
 */
public class HomeViewPagerAdapter extends FragmentPagerAdapter {

    private List<ChannelModel.Channel> mChannels;

    public HomeViewPagerAdapter(FragmentManager fm, List<ChannelModel.Channel> channels) {
        super(fm);
        mChannels = channels;
    }

    @Override
    public Fragment getItem(int position) {
        return HomeFragment.getInsance(mChannels.get(position).getName());
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mChannels.get(position).getName();
    }

    @Override
    public int getCount() {
        return mChannels.size();
    }
}
