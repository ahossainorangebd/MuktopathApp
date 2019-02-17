package orangebd.newaspaper.mymuktopathapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapterMyPageDetail extends FragmentPagerAdapter {

    private int NUM_ITEMS = 4;
    private String[] titles= new String[] {"Video1","Video2","Video3", "Video4"};

    public TabsPagerAdapterMyPageDetail(FragmentManager fm) {
        super(fm);
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return  NUM_ITEMS ;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new MyPageDetailFragment1();
            case 1:
                return new MyPageDetailFragment2();
            case 2:
                return new MyPageDetailFragment3();
            case 3:
                return new MyPageDetailFragment4();
            default:
                return null;
        }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {

        return  titles[position];
    }

}
