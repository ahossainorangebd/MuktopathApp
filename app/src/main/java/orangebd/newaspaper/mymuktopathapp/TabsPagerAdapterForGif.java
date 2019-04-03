package orangebd.newaspaper.mymuktopathapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapterForGif extends FragmentPagerAdapter {

    private int NUM_ITEMS = 3;

    private String[] titles = new String[] {"1","2","3"};

    public TabsPagerAdapterForGif(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {

        return NUM_ITEMS;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {

            case 0:
                return new SplashGifFragment1();
            case 1:
                return new SplashGifFragment2();
            case 2:
                return new SplashGifFragment3();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return titles[position];
    }

}
