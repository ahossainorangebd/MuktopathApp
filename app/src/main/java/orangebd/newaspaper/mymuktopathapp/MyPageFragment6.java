package orangebd.newaspaper.mymuktopathapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;


public class MyPageFragment6 extends Fragment {


    private Context context;

    private View view;

    private TextView mCourseTitle;
    private TextView mCourseOwner;

    private Button startMyPageBtn;
    private ImageView mCourseDetailCoverImage;

    private TextView mExmNumberTtl;
    private TextView mAssignmentNumberTtl;
    private TextView mContentNumberTtl;
    private TextView mQuizNumberTtl;

    private LinearLayout mAssignmentSection;
    private LinearLayout mExamNumberSection;
    private LinearLayout mQuizNumberSection;
    private LinearLayout mContentNumberSection;

    private TextView mContentHour;
    private TextView mExamHour;
    private TextView mAssignmentHour;

    private Formatter mFormatter;

    private int nthCourse= 5;

    private int countQuizNumber;

    private int mQuizNumbers=0;

    private ImageView mAddToCalenderBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_page_fragment6, container, false);

        context=getContext();

        mAddToCalenderBtn=view.findViewById(R.id.addtocalenderbtn);



        //For getting all unit data details
        //ArrayList<DetailDataModelCoursesDetailContents> unitsArray = GlobalVar.courseContentDetailList.get(0).getmUnitAllArrayList().get(GlobalVar.gNthCourse);

        /** Progress of progressBar
         * */

        String enrollCourseCompltness=GlobalVar.gEnrollCourseId.get(nthCourse).getmEcCompleteness();

        try {
            int index = enrollCourseCompltness.indexOf(".");
            enrollCourseCompltness = enrollCourseCompltness.substring(0,index);
        }
        catch (Exception ex){
            Log.d("", "onCreateView: ");
        }

        ProgressBar mProgBar=view.findViewById(R.id.determinateBar);
        mProgBar.setProgress(Integer.parseInt(enrollCourseCompltness));

        TextView mProCompleteness=view.findViewById(R.id.proCompleteness);
        mProCompleteness.setText(convertEngToBn(enrollCourseCompltness));


        /**for getting quiz numbers
         * */

        countQuizNumber=0;

        ArrayList<DetailDataModelCoursesDetailContents> unitArrs = GlobalVar.courseContentDetailList.get(0).getmArrayListCourseUnits().get(GlobalVar.gNthCourse);

        for(int cqn=0; cqn<unitArrs.size(); cqn++) {
            String unitTitle = unitArrs.get(cqn).getUnitNames();

            if(unitTitle.equalsIgnoreCase("কুইজ")){

                countQuizNumber++;


                String qwewrser="";
            }
        }


        /**get device height and width*/

        double deviceHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

        LinearLayout mTableLayout = view.findViewById(R.id.tableLayoutId);

        /*int previousHeight= mTableLayout.getHeight();
        int previousWidth = mTableLayout.getWidth();*/

        double calculatedHeight = (double) 340 / 1184;
        calculatedHeight=calculatedHeight*deviceHeight;
        //int calculatedWidth = (previousHeight/720)*deviceWidth;

        double newHeight = calculatedHeight;
        //int newWidth = calculatedWidth;

        ViewGroup.LayoutParams params = mTableLayout.getLayoutParams();
        params.height = (int)newHeight;
        mTableLayout.setLayoutParams(params);

        /***/

        mCourseDetailCoverImage = view.findViewById(R.id.CourseDetailCoverImage);

        mContentHour=view.findViewById(R.id.contenthour);
        mExamHour=view.findViewById(R.id.examHour);
        mAssignmentHour=view.findViewById(R.id.assignmentHour);


        ArrayList<DetailDataModelCoursesThumbnails> imgArray=GlobalVar.courseContentDetailList.get(0).getmArrayListThumbnails();

        DetailDataModelCoursesThumbnails imgUrlModel = imgArray.get(nthCourse);

        String imgUrl = imgUrlModel.getCover_code_image();

        final String CoverPhoto = GlobalVar.gBaseUrl + "/cache-images/" + "219x14nthCoursex1" + "/uploads/images/" + imgUrl;

        try {
            Picasso.with(context)
                    .load(CoverPhoto)
                    .into(mCourseDetailCoverImage);
        }
        catch (Exception ex){}

        mCourseTitle = view.findViewById(R.id.courseTitle);
        mCourseOwner = view.findViewById(R.id.ownerName);

        mExmNumberTtl = view.findViewById(R.id.examNumber);
        mAssignmentNumberTtl = view.findViewById(R.id.assignmentNumber);
        mContentNumberTtl = view.findViewById(R.id.contentNumber);
        mQuizNumberTtl = view.findViewById(R.id.quizNumber);

        mAssignmentSection = view.findViewById(R.id.assignmentSection);
        mContentNumberSection = view.findViewById(R.id.contentNumberSection);
        mExamNumberSection = view.findViewById(R.id.mExamNumberSection);
        mQuizNumberSection = view.findViewById(R.id.mQuizNumberSection);

        final String enrolledCourseTitle=GlobalVar.gEnrollCourseList.get(nthCourse).getmCourseAliasName();
        final String enrolledCourseOwner=GlobalVar.gEnrolledInstitution.get(nthCourse).getInstitution_name_owner();

        final String enrolledCourseDetails=GlobalVar.gEnrollCourseList.get(nthCourse).getmDetails();
        final String enrolledCourseMotto=GlobalVar.gEnrollCourseList.get(nthCourse).getmCourseMotto();
        final String enrolledCourseObjective=GlobalVar.gEnrollCourseList.get(nthCourse).getmCourseObjective();

        ArrayList<ArrayList<ArrayList<DetailDataModelCoursesDetailContents>>> contentArray = GlobalVar.courseContentDetailList.get(0).getmArrayListContentDetails();

        final ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> pulseMultiArray = GlobalVar.courseContentDetailList.get(0).getmArrayListCourseVideoPulseMulti().get(nthCourse);
        final ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> contents = contentArray.get(nthCourse);

        ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> quizArray = GlobalVar.courseContentDetailList.get(0).getmArrayListCourseQuizs();
        final ArrayList<DetailDataModelCoursesDetailContents> quizes = quizArray.get(nthCourse);


        final ArrayList<DetailDataModelCoursesDetailContents> mQuizParents = GlobalVar.courseContentDetailList.get(0).getmArrayListCourseQuizs().get(nthCourse);
        final ArrayList<DetailDataModelCoursesDetailContents> mAssignment = GlobalVar.courseContentDetailList.get(0).getmUnitDataArrayListContent3().get(nthCourse);
        final ArrayList<DetailDataModelCoursesDetailContents> mExam= GlobalVar.courseContentDetailList.get(0).getmUnitDataArrayListContent2().get(nthCourse);
        final ArrayList<DetailDataModelCoursesDetailContents> mContent = GlobalVar.courseContentDetailList.get(0).getmUnitDataArrayListContent().get(nthCourse);

        // Let's count the number of Units

        final String examType = GlobalVar.courseContentDetailList.get(0).getmArrayListCourseQuizs().get(nthCourse).get(0).getQtype();

        /*if(GlobalVar.gGoingDirection.equalsIgnoreCase("right")) {
            GlobalVar.gEnrolledCourseUnitSize = GlobalVar.courseContentDetailList.get(0).getmArrayListCourseUnits().get(nthCourse - 1).size();
        }
        else {
            GlobalVar.gEnrolledCourseUnitSize = GlobalVar.courseContentDetailList.get(0).getmArrayListCourseUnits().get(nthCourse+1).size();
        }


        if(GlobalVar.gEnrollCourseNumber==6){
            GlobalVar.gEnrolledCourseUnitSize = GlobalVar.courseContentDetailList.get(0).getmArrayListCourseUnits().get(nthCourse).size();
        }*/


        final ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> pulseQuesListWithAns = GlobalVar.courseContentDetailList.get(0).getmArrayListCoursePulseQuizOptions().get(nthCourse-1);

        //Let's count the duration of Content/Quiz/Assignment

        int mAssignmentNumbers = mAssignment.size();
        int mExamNumbers = mExam.size();
        int mContentNumbers = mContent.size();

        try {
            mQuizNumbers = mQuizParents.size();
        }
        catch (Exception ex){
            Log.d("", "onCreateView: ");
        }

        mCourseTitle.setText(enrolledCourseTitle);
        //TODO
        //TODO
        //mCourseOwner.setText(enrolledCourseOwner);
        mExmNumberTtl.setText(convertEngToBn(Integer.toString(mExamNumbers)));
        mAssignmentNumberTtl.setText(convertEngToBn(Integer.toString(mAssignmentNumbers)));
        mContentNumberTtl.setText(convertEngToBn(Integer.toString(mContentNumbers)));
        mQuizNumberTtl.setText(convertEngToBn(Integer.toString(countQuizNumber)));


        if(mAssignmentNumbers>0){
            int contentListCount3=GlobalVar.courseContentDetailList.get(0).getmUnitDataArrayListContent3().get(4).size();

            int contentDuration3=0;

            for (int timeCount3=0; timeCount3<contentListCount3 ; timeCount3++) {

                String temptestList3=GlobalVar.courseContentDetailList.get(0).getmUnitDataArrayListContent3().get(4).get(timeCount3).getmDurationAnother();

                if(!temptestList3.equalsIgnoreCase("null")) {
                    contentDuration3 = contentDuration3 + Integer.parseInt(temptestList3);
                }
            }

            String contentDuration3New= stringForTime(contentDuration3);

            mAssignmentSection.setVisibility(View.VISIBLE);

            mAssignmentHour.setText(contentDuration3New);
        }

        if(mExamNumbers>0){

            int contentListCount2=GlobalVar.courseContentDetailList.get(0).getmUnitDataArrayListContent2().get(nthCourse).size();
            int contentDuration2=0;

            for (int timeCount2=0; timeCount2<contentListCount2 ; timeCount2++) {

                String temptestList2=GlobalVar.courseContentDetailList.get(0).getmUnitDataArrayListContent2().get(nthCourse).get(timeCount2).getmDurationAnother();

                if(!temptestList2.equalsIgnoreCase("null")) {
                    contentDuration2 = contentDuration2 + Integer.parseInt(temptestList2);
                }
            }

            String contentDuration2New= stringForTime(contentDuration2);

            mExamNumberSection.setVisibility(View.VISIBLE);

            mExamHour.setText(contentDuration2New);
        }

        if(mContentNumbers>0){

            int contentListCount=GlobalVar.courseContentDetailList.get(0).getmUnitDataArrayListContent().get(nthCourse).size();
            int contentDuration=0;

            for (int timeCount=0; timeCount<contentListCount ; timeCount++) {

                String temptestList=GlobalVar.courseContentDetailList.get(0).getmUnitDataArrayListContent().get(nthCourse).get(timeCount).getmDurationAnother();

                if(!temptestList.equalsIgnoreCase("null")) {
                    contentDuration = contentDuration + Integer.parseInt(temptestList);
                }
            }

            String contentDurationNew= stringForTime(contentDuration);

            mContentNumberSection.setVisibility(View.VISIBLE);
            mContentHour.setText(contentDurationNew);
        }

        if(mQuizNumbers>0){

            int contentListCount=GlobalVar.courseContentDetailList.get(0).getmUnitDataArrayListContent().get(nthCourse).size();
            int contentDuration=0;

            for (int timeCount=0; timeCount<contentListCount ; timeCount++) {

                String temptestList=GlobalVar.courseContentDetailList.get(0).getmUnitDataArrayListContent().get(nthCourse).get(timeCount).getmDurationAnother();

                if(!temptestList.equalsIgnoreCase("null")) {
                    contentDuration = contentDuration + Integer.parseInt(temptestList);
                }
            }

            String contentDurationNew= stringForTime(contentDuration);

            if(examType!=null) {
                if (examType.equalsIgnoreCase("multiple")) {
                    mQuizNumberSection.setVisibility(View.GONE);
                } else {
                    mQuizNumberSection.setVisibility(View.VISIBLE);
                }
            }
        }

        startMyPageBtn=view.findViewById(R.id.startMyPageBtnId);
        startMyPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // let's get the accurate pulse and video ques array

                final ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> pulseQuesListWithAns = GlobalVar.courseContentDetailList.get(0).getmArrayListCoursePulseQuizOptions().get(nthCourse);
                final ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> pulseMultiArray = GlobalVar.courseContentDetailList.get(0).getmArrayListCourseVideoPulseMulti().get(nthCourse);


                // Let's count the number of Units
                GlobalVar.gEnrolledCourseUnitSize = GlobalVar.courseContentDetailList.get(0).getmArrayListCourseUnits().get(nthCourse).size();

                GlobalVar.isRedirectFromContentPage=false;

                Intent i = new Intent(context, MyPageCourseDetail.class);

                i.putExtra("ttl", enrolledCourseTitle);
                i.putExtra("img", CoverPhoto);
                i.putExtra("oname", enrolledCourseOwner);
                i.putExtra("nthcourse", nthCourse);
                i.putExtra("cobj", enrolledCourseObjective);
                i.putExtra("cmto", enrolledCourseMotto);
                i.putExtra("cdesc", enrolledCourseDetails);

                GlobalVar.thisFragmentContents=contents;
                GlobalVar.thisFragmentQuizes=quizes;
                GlobalVar.thisFragmentPulses=pulseMultiArray;
                GlobalVar.thisFragmentPulseQs=pulseQuesListWithAns;
                GlobalVar.gTotalQuizNumberThisCourse=mQuizNumbers+1;

                try {
                    v.getContext().startActivity(i);
                }
                catch (Exception ex){
                    String msg=ex.getMessage();
                    Log.d("msg",msg);
                }

                GlobalVar.nNumberCourseBack=nthCourse;
            }
        });

        mAddToCalenderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(getActivity().findViewById(android.R.id.content), "", Snackbar.LENGTH_LONG)
                        .setAction("Add to Calendar", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                long enrolledCourseEndDateLong=0;
                                long currentDateInMillis=0;

                                final String enrolledCourseEndDate=GlobalVar.gEnrollCourseList.get(nthCourse).getmEndDate();

                                if(enrolledCourseEndDate.equalsIgnoreCase("")){
                                    Toast.makeText(context, "No end date for this course", Toast.LENGTH_LONG).show();
                                }
                                else{
                                    try {

                                        Date currentDate = new Date();
                                        String myCurrentDate = new SimpleDateFormat("yyyy-MM-dd").format(currentDate);
                                        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                                        Date date2 = sdf2.parse(myCurrentDate);
                                        currentDateInMillis = date2.getTime();


                                        String myDate = enrolledCourseEndDate;
                                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                        Date date = sdf.parse(myDate);
                                        enrolledCourseEndDateLong = date.getTime();
                                    }
                                    catch (Exception ex){
                                        Log.d("", "onClick: ");
                                    }

                                    Intent calIntent = new Intent(Intent.ACTION_INSERT);

                                    calIntent.setData(CalendarContract.Events.CONTENT_URI);
                                    calIntent.putExtra(CalendarContract.Events.TITLE, enrolledCourseTitle);
                                    calIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, "মুক্তপাঠ");
                                    calIntent.putExtra(CalendarContract.Events.DESCRIPTION, enrolledCourseDetails);
                                    Calendar startTime = Calendar.getInstance();
                                    startTime.set(2012, 5, 29, 18, 0);
                                    Calendar endTime = Calendar.getInstance();
                                    endTime.set(2012, 5, 29, 11, 59, 59);
                                    calIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                                            currentDateInMillis);
                                    calIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,
                                            enrolledCourseEndDateLong);
                                    startActivity(calIntent);
                                }

                            }
                        })
                        .show();
            }
        });


        return view;
    }

    private String stringForTime(int timeMs) {

        mFormatter= new Formatter();

        //int seconds = timeMs % 60;
        int minutes = (timeMs / 60) % 60;
        int hours = timeMs / 3600;

        String mintBn = convertEngToBn(Integer.toString(minutes));
        String hrsBn = convertEngToBn(Integer.toString(hours));

        if (hours > 0) {
            return mFormatter.format("%s ঘণ্টা %s মিনিট", hrsBn, mintBn).toString();
        }
        else {
            return mFormatter.format("%s মিনিট", mintBn).toString();
        }
    }

    public String convertEngToBn(String num){

        num = num.replace("0","০");
        num = num.replace("1","১");
        num = num.replace("2","২");
        num = num.replace("3","৩");
        num = num.replace("4","৪");
        num = num.replace("5","৫");
        num = num.replace("6","৬");
        num = num.replace("7","৭");
        num = num.replace("8","৮");
        num = num.replace("9","৯");

        return num;
    }
}
