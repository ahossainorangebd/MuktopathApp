package orangebd.newaspaper.mymuktopathapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapterExam extends FragmentPagerAdapter {

    private int NUM_ITEMS = 10;

    private String[] titles = new String[] {};

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

    public TabsPagerAdapterExam(FragmentManager fm) {
        super(fm);
    }

    // Returns total number of pages
    @Override
    public int getCount() {

        return GlobalVar.gTotalExamNumberThisCourse ;
        //return NUM_ITEMS;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {


        if(GlobalVar.gTotalExamNumberThisCourse==2){
            switch (position) {

                case 0:
                    return new ExamFragment1();
                case 1:
                    return new ExamFragmentSubmit();
                default:
                    return null;
            }
        }

        else if(GlobalVar.gTotalExamNumberThisCourse==3){
            switch (position) {

                case 0:
                    return new ExamFragment1();
                case 1:
                    return new ExamFragment2();
                case 2:
                    return new ExamFragmentSubmit();
                default:
                    return null;
            }
        }

        else if(GlobalVar.gTotalExamNumberThisCourse==4)
        {
            switch (position) {

                case 0:
                    return new ExamFragment1();
                case 1:
                    return new ExamFragment2();
                case 2:
                    return new ExamFragment3();
                case 3:
                    return new ExamFragmentSubmit();
                default:
                    return null;
            }
        }

        else if(GlobalVar.gTotalExamNumberThisCourse==5)
        {
            switch (position) {

                case 0:
                    return new ExamFragment1();
                case 1:
                    return new ExamFragment2();
                case 2:
                    return new ExamFragment3();
                case 3:
                    return new ExamFragment4();
                case 4:
                    return new ExamFragmentSubmit();
                default:
                    return null;
            }
        }

        else if(GlobalVar.gTotalExamNumberThisCourse==6)
        {
            switch (position) {

                case 0:
                    return new ExamFragment1();
                case 1:
                    return new ExamFragment2();
                case 2:
                    return new ExamFragment3();
                case 3:
                    return new ExamFragment4();
                case 4:
                    return new ExamFragment5();
                case 5:
                    return new ExamFragmentSubmit();
                default:
                    return null;
            }
        }

        else if(GlobalVar.gTotalExamNumberThisCourse==7)
        {
            switch (position) {

                case 0:
                    return new ExamFragment1();
                case 1:
                    return new ExamFragment2();
                case 2:
                    return new ExamFragment3();
                case 3:
                    return new ExamFragment4();
                case 4:
                    return new ExamFragment5();
                case 5:
                    return new ExamFragment6();
                case 6:
                    return new ExamFragmentSubmit();
                default:
                    return null;
            }
        }

        else if(GlobalVar.gTotalExamNumberThisCourse==8)
        {
            switch (position) {

                case 0:
                    return new ExamFragment1();
                case 1:
                    return new ExamFragment2();
                case 2:
                    return new ExamFragment3();
                case 3:
                    return new ExamFragment4();
                case 4:
                    return new ExamFragment5();
                case 5:
                    return new ExamFragment6();
                case 6:
                    return new ExamFragment7();
                case 7:
                    return new ExamFragmentSubmit();
                default:
                    return null;
            }
        }

        else if(GlobalVar.gTotalExamNumberThisCourse==9)
        {
            switch (position) {

                case 0:
                    return new ExamFragment1();
                case 1:
                    return new ExamFragment2();
                case 2:
                    return new ExamFragment3();
                case 3:
                    return new ExamFragment4();
                case 4:
                    return new ExamFragment5();
                case 5:
                    return new ExamFragment6();
                case 6:
                    return new ExamFragment7();
                case 7:
                    return new ExamFragment8();
                case 8:
                    return new ExamFragmentSubmit();
                default:
                    return null;
            }
        }

        else if(GlobalVar.gTotalExamNumberThisCourse==10)
        {
            switch (position) {

                case 0:
                    return new ExamFragment1();
                case 1:
                    return new ExamFragment2();
                case 2:
                    return new ExamFragment3();
                case 3:
                    return new ExamFragment4();
                case 4:
                    return new ExamFragment5();
                case 5:
                    return new ExamFragment6();
                case 6:
                    return new ExamFragment7();
                case 7:
                    return new ExamFragment8();
                case 8:
                    return new ExamFragment9();
                case 9:
                    return new ExamFragmentSubmit();
                default:
                    return null;
            }
        }

        else if(GlobalVar.gTotalExamNumberThisCourse==11)
        {
            switch (position) {

                case 0:
                    return new ExamFragment1();
                case 1:
                    return new ExamFragment2();
                case 2:
                    return new ExamFragment3();
                case 3:
                    return new ExamFragment4();
                case 4:
                    return new ExamFragment5();
                case 5:
                    return new ExamFragment6();
                case 6:
                    return new ExamFragment7();
                case 7:
                    return new ExamFragment8();
                case 8:
                    return new ExamFragment9();
                case 9:
                    return new ExamFragment10();
                case 10:
                    return new ExamFragmentSubmit();
                default:
                    return null;
            }
        }

        //from 11 to 20


        else if(GlobalVar.gTotalExamNumberThisCourse==12)
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
                    return new ExamFragmentSubmit();
                default:
                    return null;
            }
        }


        else if(GlobalVar.gTotalExamNumberThisCourse==13)
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
                    return new ExamFragmentSubmit();
                default:
                    return null;
            }
        }

        else if(GlobalVar.gTotalExamNumberThisCourse==14)
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
                    return new ExamFragmentSubmit();
                default:
                    return null;
            }
        }

        else if(GlobalVar.gTotalExamNumberThisCourse==15)
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
                    return new ExamFragmentSubmit();
                default:
                    return null;
            }
        }

        else if(GlobalVar.gTotalExamNumberThisCourse==16)
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
                case 15:
                    return new ExamFragmentSubmit();
                default:
                    return null;
            }
        }

        else if(GlobalVar.gTotalExamNumberThisCourse==17)
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
                    return new ExamFragmentSubmit();
                default:
                    return null;
            }
        }

        else if(GlobalVar.gTotalExamNumberThisCourse==18)
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
                    return new ExamFragmentSubmit();
                default:
                    return null;
            }
        }

        else if(GlobalVar.gTotalExamNumberThisCourse==19)
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
                    return new ExamFragmentSubmit();
                default:
                    return null;
            }
        }

        else if(GlobalVar.gTotalExamNumberThisCourse==20)
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
                    return new ExamFragmentSubmit();

                default:
                    return null;
            }
        }

        else if(GlobalVar.gTotalExamNumberThisCourse==21)
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
                case 20:
                    return new ExamFragmentSubmit();
                default:
                    return null;
            }
        }

        else if(GlobalVar.gTotalExamNumberThisCourse==22)
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
                case 20:
                    return new MyPageFragment21();
                case 21:
                    return new ExamFragmentSubmit();
                default:
                    return null;
            }
        }

        else if(GlobalVar.gTotalExamNumberThisCourse>=23)
        {
            switch (position) {

                case 0:
                    return new ExamFragment1();
                case 1:
                    return new ExamFragment2();
                case 2:
                    return new ExamFragment3();
                case 3:
                    return new ExamFragment4();
                case 4:
                    return new ExamFragment5();
                case 5:
                    return new ExamFragment6();
                case 6:
                    return new ExamFragment7();
                case 7:
                    return new ExamFragment8();
                case 8:
                    return new ExamFragment9();
                case 9:
                    return new ExamFragment10();
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
                case 20:
                    return new MyPageFragment21();
                case 21:
                    return new MyPageFragment22();
                case 22:
                    return new ExamFragmentSubmit();
                default:
                    return null;
            }
        }

        return null;
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {

        if(GlobalVar.gTotalExamNumberThisCourse==3){
            return titles[position] = titles3[position];
        }
        else if(GlobalVar.gTotalExamNumberThisCourse==4){
            return titles[position] = titles4[position];
        }
        else if(GlobalVar.gTotalExamNumberThisCourse==5){
            return titles[position] = titles5[position];
        }
        else if(GlobalVar.gTotalExamNumberThisCourse==6){
            return titles[position] = titles6[position];
        }
        else if(GlobalVar.gTotalExamNumberThisCourse==7){
            return titles[position] = titles7[position];
        }
        else if(GlobalVar.gTotalExamNumberThisCourse==8){
            return titles[position] = titles8[position];
        }
        else if(GlobalVar.gTotalExamNumberThisCourse==9){
            return titles[position] = titles9[position];
        }
        else if(GlobalVar.gTotalExamNumberThisCourse==10){
            return titles[position] = titles10[position];
        }
        else if(GlobalVar.gTotalExamNumberThisCourse==11){
            return titles[position] = titles11[position];
        }
        else if(GlobalVar.gTotalExamNumberThisCourse==12){
            return titles[position] = titles12[position];
        }
        else if(GlobalVar.gTotalExamNumberThisCourse==13){
            return titles[position] = titles13[position];
        }
        else if(GlobalVar.gTotalExamNumberThisCourse==14){
            return titles[position] = titles14[position];
        }
        else if(GlobalVar.gTotalExamNumberThisCourse==15){
            return titles[position] = titles15[position];
        }
        else if(GlobalVar.gTotalExamNumberThisCourse==16){
            return titles[position] = titles16[position];
        }
        else if(GlobalVar.gTotalExamNumberThisCourse==17) {
            return titles[position] = titles17[position];
        }
        else if(GlobalVar.gTotalExamNumberThisCourse==18){
            return titles[position] = titles18[position];
        }
        else if(GlobalVar.gTotalExamNumberThisCourse==19){
            return titles[position] = titles19[position];
        }
        else if(GlobalVar.gTotalExamNumberThisCourse==20){
            return titles[position] = titles20[position];
        }
        else if(GlobalVar.gTotalExamNumberThisCourse==21){
            return titles[position] = titles21[position];
        }
        else if(GlobalVar.gTotalExamNumberThisCourse>=22){
            return titles[position] = titles22[position];
        }

        return titles[position];
    }

}
