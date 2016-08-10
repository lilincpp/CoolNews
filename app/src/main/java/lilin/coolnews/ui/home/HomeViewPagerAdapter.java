package lilin.coolnews.ui.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by lilin on 2016/8/10.
 */
public class HomeViewPagerAdapter extends FragmentPagerAdapter {

    private String[] mTitles;

    public HomeViewPagerAdapter(FragmentManager fm, String[] titles) {
        super(fm);
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return HomeFragment.getInsance(mTitles[position]);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }
}
