package orangebd.newaspaper.mymuktopathapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapterDetail extends FragmentPagerAdapter {

    private int NUM_ITEMS = 10;

    private String[] titles = new String[] {"1","2","3","4","5","6","7","8","9","10"};

    /*private String[] titles3= new String[] {"1","2","3"};
    private String[] titles4= new String[] {"1","2","3","4"};
    private String[] titles5= new String[] {"1","2","3","4","5"};
    private String[] titles6= new String[] {"1","2","3","4","5","6"};
    private String[] titles7= new String[] {"1","2","3","4","5","6","7"};
    private String[] titles8= new String[] {"1","2","3","4","5","6","7","8"};
    private String[] titles9= new String[] {"1","2","3","4","5","6","7","8","9"};
    private String[] titles10= new String[] {"1","2","3","4","5","6","7","8","9","10"};*/

    public TabsPagerAdapterDetail(FragmentManager fm) {
        super(fm);
    }

    // Returns total number of pages
    @Override
    public int getCount() {

        return GlobalVar.gEnrollCourseNumber ;
        //return NUM_ITEMS;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {

        if(GlobalVar.gEnrollCourseNumber==3)
        {
            switch (position) {

                case 0:
                    return new MyPageFragment1();
                case 1:
                    return new MyPageFragment2();
                case 2:
                    return new MyPageFragment3();
                default:
                    return null;
            }
        }

        else if(GlobalVar.gEnrollCourseNumber==4)
        {
            switch (position) {

                case 0:
                    return new MyPageFragment1();
                case 1:
                    return new MyPageFragment2();
                case 2:
                    return new MyPageFragment3();
                case 3:
                    return new MyPageFragment4();
                default:
                    return null;
            }
        }

        else if(GlobalVar.gEnrollCourseNumber==5)
        {
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
                default:
                    return null;
            }
        }

        else if(GlobalVar.gEnrollCourseNumber==6)
        {
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
                default:
                    return null;
            }
        }

        else if(GlobalVar.gEnrollCourseNumber==7)
        {
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
                default:
                    return null;
            }
        }

        else if(GlobalVar.gEnrollCourseNumber==8)
        {
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
                default:
                    return null;
            }
        }

        else if(GlobalVar.gEnrollCourseNumber==9)
        {
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
                default:
                    return null;
            }
        }

        else if(GlobalVar.gEnrollCourseNumber==10)
        {
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

        //from 11 to 20


        else if(GlobalVar.gEnrollCourseNumber==11)
        {
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
                case 10:
                    return new MyPageFragment11();
                default:
                    return null;
            }
        }


        else if(GlobalVar.gEnrollCourseNumber==12)
        {
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
                case 10:
                    return new MyPageFragment11();
                case 11:
                    return new MyPageFragment12();
                default:
                    return null;
            }
        }

        else if(GlobalVar.gEnrollCourseNumber==13)
        {
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
                case 10:
                    return new MyPageFragment11();
                case 11:
                    return new MyPageFragment12();
                case 12:
                    return new MyPageFragment13();
                default:
                    return null;
            }
        }

        else if(GlobalVar.gEnrollCourseNumber==14)
        {
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
                case 10:
                    return new MyPageFragment11();
                case 11:
                    return new MyPageFragment12();
                case 12:
                    return new MyPageFragment13();
                case 13:
                    return new MyPageFragment14();
                default:
                    return null;
            }
        }

        else if(GlobalVar.gEnrollCourseNumber==15)
        {
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
                case 10:
                    return new MyPageFragment11();
                case 11:
                    return new MyPageFragment12();
                case 12:
                    return new MyPageFragment13();
                case 13:
                    return new MyPageFragment14();
                case 14:
                    return new MyPageFragment15();
                default:
                    return null;
            }
        }

        else if(GlobalVar.gEnrollCourseNumber==16)
        {
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
                case 10:
                    return new MyPageFragment11();
                case 11:
                    return new MyPageFragment12();
                case 12:
                    return new MyPageFragment13();
                case 13:
                    return new MyPageFragment14();
                case 14:
                    return new MyPageFragment15();
                case 15:
                    return new MyPageFragment16();
                default:
                    return null;
            }
        }

        else if(GlobalVar.gEnrollCourseNumber==17)
        {
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
                case 10:
                    return new MyPageFragment11();
                case 11:
                    return new MyPageFragment12();
                case 12:
                    return new MyPageFragment13();
                case 13:
                    return new MyPageFragment14();
                case 14:
                    return new MyPageFragment15();
                case 15:
                    return new MyPageFragment16();
                case 16:
                    return new MyPageFragment17();
                default:
                    return null;
            }
        }

        else if(GlobalVar.gEnrollCourseNumber==18)
        {
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
                case 10:
                    return new MyPageFragment11();
                case 11:
                    return new MyPageFragment12();
                case 12:
                    return new MyPageFragment13();
                case 13:
                    return new MyPageFragment14();
                case 14:
                    return new MyPageFragment15();
                case 15:
                    return new MyPageFragment16();
                case 16:
                    return new MyPageFragment17();
                case 17:
                    return new MyPageFragment18();
                default:
                    return null;
            }
        }

        else if(GlobalVar.gEnrollCourseNumber==19)
        {
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
                case 10:
                    return new MyPageFragment11();
                case 11:
                    return new MyPageFragment12();
                case 12:
                    return new MyPageFragment13();
                case 13:
                    return new MyPageFragment14();
                case 14:
                    return new MyPageFragment15();
                case 15:
                    return new MyPageFragment16();
                case 16:
                    return new MyPageFragment17();
                case 17:
                    return new MyPageFragment18();
                case 18:
                    return new MyPageFragment19();

                default:
                    return null;
            }
        }

        else if(GlobalVar.gEnrollCourseNumber>=20)
        {
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
                case 10:
                    return new MyPageFragment11();
                case 11:
                    return new MyPageFragment12();
                case 12:
                    return new MyPageFragment13();
                case 13:
                    return new MyPageFragment14();
                case 14:
                    return new MyPageFragment15();
                case 15:
                    return new MyPageFragment16();
                case 16:
                    return new MyPageFragment17();
                case 17:
                    return new MyPageFragment18();
                case 18:
                    return new MyPageFragment19();
                case 19:
                    return new MyPageFragment20();
                default:
                    return null;
            }
        }

        return null;
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {

        /*if(GlobalVar.gEnrollCourseNumber==3){
            return titles[position] = titles3[position];
        }
        else if(GlobalVar.gEnrollCourseNumber==4){
            return titles[position] = titles4[position];
        }
        else if(GlobalVar.gEnrollCourseNumber==5){
            return titles[position] = titles5[position];
        }
        else if(GlobalVar.gEnrollCourseNumber==6){
            return titles[position] = titles6[position];
        }
        else if(GlobalVar.gEnrollCourseNumber==7){
            return titles[position] = titles7[position];
        }
        else if(GlobalVar.gEnrollCourseNumber==8){
            return titles[position] = titles8[position];
        }
        else if(GlobalVar.gEnrollCourseNumber==9){
            return titles[position] = titles9[position];
        }
        else if(GlobalVar.gEnrollCourseNumber>=10){
            return titles[position] = titles10[position];
        }*/

        return titles[position];
    }

}
