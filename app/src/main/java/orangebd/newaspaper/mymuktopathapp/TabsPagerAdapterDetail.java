package orangebd.newaspaper.mymuktopathapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapterDetail extends FragmentPagerAdapter {

    private int NUM_ITEMS = 10;
    private String[] titles= new String[] {"Video1","Video2","Video3", "Video4","Video5","Video6","Video7","Video8","Video9","Video10"};

    public TabsPagerAdapterDetail(FragmentManager fm) {
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
                return new MyPageFragment1();
            case 1:
                return new MyPageFragment2();
            case 2:
                return new MyPageFragment3();
            case 3:
                return new MyPageFragment4();
            case 4:
                return new MyPageFragment5();
            case 5:
                return new MyPageFragment6();
            case 6:
                return new MyPageFragment7();
            case 7:
                return new MyPageFragment8();
            case 8:
                return new MyPageFragment9();
            case 9:
                return new MyPageFragment10();
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
