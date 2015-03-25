package ahmad.ef.workhours.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ahmad.ef.workhours.GamesFragment;
import ahmad.ef.workhours.MoviesFragment;
import ahmad.ef.workhours.TopRatedFragment;

/**
 * Created by asma on 3/19/2015.
 */
public class TabsPagerAdapter extends FragmentPagerAdapter {

    private static final int PAGE_COUNT = 3;

    public TabsPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                // Top rated fragment activity
                return new TopRatedFragment();
            case 1:
                // Games fragment activity
                return new GamesFragment();
            case 2:
                // Movies fragment activity
                return new MoviesFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        // Get item count - equal to number of tabs
        return PAGE_COUNT;
    }
}
