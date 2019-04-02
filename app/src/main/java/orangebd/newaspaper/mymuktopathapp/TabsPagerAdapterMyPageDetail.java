package orangebd.newaspaper.mymuktopathapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.Toast;

public class TabsPagerAdapterMyPageDetail extends FragmentPagerAdapter {

    private int NUM_ITEMS = 4;
    private String[] titles = new String[] {"1"};
    private String[] titles2=  new String[] {"1","2"};
    private String[] titles3=  new String[] {"1","2","3"};
    private String[] titles4=  new String[] {"1","2","3","4"};
    private String[] titles5=  new String[] {"1","2","3","4","5"};
    private String[] titles6=  new String[] {"1","2","3","4","5","6"};
    private String[] titles7=  new String[] {"1","2","3","4","5","6","7"};
    private String[] titles8=  new String[] {"1","2","3","4","5","6","7","8"};
    private String[] titles9=  new String[] {"1","2","3","4","5","6","7","8","9"};
    private String[] titles10= new String[] {"1","2","3","4","5","6","7","8","9","10"};
    private String[] titles11= new String[] {"1","2","3","4","5","6","7","8","9","10","11"};
    private String[] titles12= new String[] {"1","2","3","4","5","6","7","8","9","10","11","12"};
    private String[] titles13= new String[] {"1","2","3","4","5","6","7","8","9","10","11","12","13"};
    private String[] titles14= new String[] {"1","2","3","4","5","6","7","8","9","10","11","12","13","14"};
    private String[] titles15= new String[] {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15"};
    private String[] titles16= new String[] {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16"};
    private String[] titles17= new String[] {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17"};
    private String[] titles18= new String[] {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18"};
    private String[] titles19= new String[] {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19"};
    private String[] titles20= new String[] {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"};
    private String[] titles21= new String[] {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21"};
    private String[] titles22= new String[] {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22"};

    public TabsPagerAdapterMyPageDetail(FragmentManager fm) {
        super(fm);
    }

    // Returns total number of pages
    @Override
    public int getCount() {

        if(GlobalVar.gEnrolledCourseUnitSize<5) {
            return GlobalVar.gEnrolledCourseUnitSize;
        }
        else {
            return 5;
        }
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


        else if(GlobalVar.gEnrolledCourseUnitSize==4)
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
                case 4:
                    return new MyPageDetailFragment5();
                default:
                    return null;
            }
        }



        return null;
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {

        if(GlobalVar.gEnrolledCourseUnitSize==1){
            return titles[position];
        }
        else if(GlobalVar.gEnrolledCourseUnitSize==2){
            return titles2[position];
        }
        else if(GlobalVar.gEnrolledCourseUnitSize==3){
            return titles3[position];
        }
        else if(GlobalVar.gEnrolledCourseUnitSize==4){
            return titles4[position];
        }
        else if(GlobalVar.gEnrolledCourseUnitSize==5){
            return titles5[position];
        }
        else if(GlobalVar.gEnrolledCourseUnitSize==6){
            return titles6[position];
        }
        else if(GlobalVar.gEnrolledCourseUnitSize==7){
            return titles7[position];
        }
        else if(GlobalVar.gEnrolledCourseUnitSize==8){
            return titles8[position];
        }
        else if(GlobalVar.gEnrolledCourseUnitSize==9){
            return titles9[position];
        }
        else if(GlobalVar.gEnrolledCourseUnitSize==10){
            return titles10[position];
        }
        else if(GlobalVar.gEnrolledCourseUnitSize==11){
            return titles11[position];
        }
        else if(GlobalVar.gEnrolledCourseUnitSize==12){
            return titles12[position];
        }
        else if(GlobalVar.gEnrolledCourseUnitSize==13){
            return titles13[position];
        }
        else if(GlobalVar.gEnrolledCourseUnitSize==14){
            return titles14[position];
        }
        else if(GlobalVar.gEnrolledCourseUnitSize==15){
            return titles15[position];
        }
        else if(GlobalVar.gEnrolledCourseUnitSize==16){
            return titles16[position];
        }
        else if(GlobalVar.gEnrolledCourseUnitSize==17) {
            return titles17[position];
        }
        else if(GlobalVar.gEnrolledCourseUnitSize==18){
            return titles18[position];
        }
        else if(GlobalVar.gEnrolledCourseUnitSize==19){
            return titles19[position];
        }
        else if(GlobalVar.gEnrolledCourseUnitSize==20){
            return titles20[position];
        }
        else if(GlobalVar.gEnrolledCourseUnitSize==21){
            return titles21[position];
        }
        else if(GlobalVar.gEnrolledCourseUnitSize>=22){
            return titles22[position];
        }

        return titles[position];
    }

}
