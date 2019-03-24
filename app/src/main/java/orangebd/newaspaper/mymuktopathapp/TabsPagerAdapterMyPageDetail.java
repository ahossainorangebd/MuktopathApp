package orangebd.newaspaper.mymuktopathapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.Toast;

public class TabsPagerAdapterMyPageDetail extends FragmentPagerAdapter {

    private int NUM_ITEMS = 4;
    private String[] titles= new String[] {"1","2","3","4","5"};

    public TabsPagerAdapterMyPageDetail(FragmentManager fm) {
        super(fm);
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return  GlobalVar.gEnrolledCourseUnitSize;
        //return  NUM_ITEMS ;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {

        if(GlobalVar.gEnrolledCourseUnitSize==1)
        {
            switch (position) {

                case 0:
                    return new MyPageDetailFragment1();
                default:
                    return null;
            }
        }

        else if(GlobalVar.gEnrolledCourseUnitSize==2) {
            switch (position) {

                case 0:
                    return new MyPageDetailFragment1();
                case 1:
                    return new MyPageDetailFragment2();
                default:
                    return null;
            }
        }


        else if(GlobalVar.gEnrolledCourseUnitSize==3)
        {
            switch (position) {

                case 0:
                    return new MyPageDetailFragment1();
                case 1:
                    return new MyPageDetailFragment2();
                case 2:
                    return new MyPageDetailFragment3();
                default:
                    return null;
            }
        }


        else if(GlobalVar.gEnrolledCourseUnitSize>=4)
        {
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
        return null;
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {

        return  titles[position];
    }

}
