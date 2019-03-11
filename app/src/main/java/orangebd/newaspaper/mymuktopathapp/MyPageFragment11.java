package orangebd.newaspaper.mymuktopathapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Formatter;

public class MyPageFragment11 extends Fragment {

    private Context context;

    private View view;

    private TextView mCourseTitle;
    private TextView mCourseOwner;

    private Button startMyPageBtn;
    private ImageView mCourseDetailCoverImage;

    private TextView mExmNumberTtl;
    private TextView mAssignmentNumberTtl;
    private TextView mContentNumberTtl;

    private LinearLayout mAssignmentSection;
    private LinearLayout mExamNumberSection;
    private LinearLayout mContentNumberSection;

    private TextView mContentHour;
    private TextView mExamHour;
    private TextView mAssignmentHour;

    private Formatter mFormatter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_my_page_fragment11, container, false);

        context=getContext();

        mCourseDetailCoverImage = view.findViewById(R.id.CourseDetailCoverImage);

        mContentHour=view.findViewById(R.id.contenthour);
        mExamHour=view.findViewById(R.id.examHour);
        mAssignmentHour=view.findViewById(R.id.assignmentHour);

        startMyPageBtn=view.findViewById(R.id.startMyPageBtnId);
        startMyPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, MyPageCourseDetail.class);
                v.getContext().startActivity(i);
            }
        });

        ArrayList<DetailDataModelCoursesThumbnails> imgArray= GlobalVar.courseContentDetailList.get(0).getmArrayListThumbnails();

        DetailDataModelCoursesThumbnails imgUrlModel = imgArray.get(10);

        String imgUrl = imgUrlModel.getCover_code_image();

        final String CoverPhoto = GlobalVar.gBaseUrl + "/cache-images/" + "219x145x1" + "/uploads/images/" + imgUrl;

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

        mAssignmentSection = view.findViewById(R.id.assignmentSection);
        mContentNumberSection = view.findViewById(R.id.contentNumberSection);
        mExamNumberSection = view.findViewById(R.id.mExamNumberSection);

        final String enrolledCourseTitle=GlobalVar.gEnrollCourseList.get(3).getmCourseAliasName();
        final String enrolledCourseOwner=GlobalVar.gEnrolledInstitution.get(3).getInstitution_name_owner();

        ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> contentArray = GlobalVar.courseContentDetailList.get(0).getmArrayListContentDetails();
        final ArrayList<DetailDataModelCoursesDetailContents> contents = contentArray.get(10);

        ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> quizArray = GlobalVar.courseContentDetailList.get(0).getmArrayListCourseQuizs();
        final ArrayList<DetailDataModelCoursesDetailContents> quizes = quizArray.get(10);

        //Let's count the duration of Content/Quiz/Assignment

        final int nthCourse= 9;
        int mAssignmentNumbers=GlobalVar.gEnrollCourseList.get(10).getmAssignmentNumbers();
        int mExamNumbers=GlobalVar.gEnrollCourseList.get(10).getmExamNumbers();
        int mContentNumbers = contents.size();


        // Let's count the number of Units
        GlobalVar.gEnrolledCourseUnitSize = GlobalVar.courseContentDetailList.get(0).getmArrayListCourseUnits().get(nthCourse-1).size();


        mCourseTitle.setText(enrolledCourseTitle);
        mCourseOwner.setText(enrolledCourseOwner);
        mExmNumberTtl.setText(Integer.toString(mAssignmentNumbers));
        mAssignmentNumberTtl.setText(Integer.toString(mExamNumbers));
        mContentNumberTtl.setText(Integer.toString(mContentNumbers));


        if(mAssignmentNumbers>0){

            int contentListCount3=GlobalVar.courseContentDetailList.get(0).getmUnitDataArrayListContent3().get(10).size();

            int contentDuration3=0;

            for (int timeCount3=0; timeCount3<contentListCount3 ; timeCount3++) {

                String temptestList3=GlobalVar.courseContentDetailList.get(0).getmUnitDataArrayListContent3().get(10).get(timeCount3).getmDurationAnother();

                if(!temptestList3.equalsIgnoreCase("null")) {
                    contentDuration3 = contentDuration3 + Integer.parseInt(temptestList3);
                }
            }

            String contentDuration3New= stringForTime(contentDuration3);

            mAssignmentSection.setVisibility(View.VISIBLE);

            mAssignmentHour.setText(contentDuration3New);
        }

        if(mExamNumbers>0){


            int contentListCount2=GlobalVar.courseContentDetailList.get(0).getmUnitDataArrayListContent2().get(10).size();
            int contentDuration2=0;

            for (int timeCount2=0; timeCount2<contentListCount2 ; timeCount2++) {

                String temptestList2=GlobalVar.courseContentDetailList.get(0).getmUnitDataArrayListContent2().get(10).get(timeCount2).getmDurationAnother();

                if(!temptestList2.equalsIgnoreCase("null")) {
                    contentDuration2 = contentDuration2 + Integer.parseInt(temptestList2);
                }
            }

            String contentDuration2New= stringForTime(contentDuration2);

            mExamNumberSection.setVisibility(View.VISIBLE);

            mExamHour.setText(contentDuration2New);

        }

        if(mContentNumbers>0){


            int contentListCount=GlobalVar.courseContentDetailList.get(0).getmUnitDataArrayListContent().get(10).size();
            int contentDuration=0;

            for (int timeCount=0; timeCount<contentListCount ; timeCount++) {

                String temptestList=GlobalVar.courseContentDetailList.get(0).getmUnitDataArrayListContent().get(10).get(timeCount).getmDurationAnother();

                if(!temptestList.equalsIgnoreCase("null")) {
                    contentDuration = contentDuration + Integer.parseInt(temptestList);
                }
            }

            String contentDurationNew= stringForTime(contentDuration);

            mContentNumberSection.setVisibility(View.VISIBLE);
            mContentHour.setText(contentDurationNew);

        }

        //setting text for hours

        startMyPageBtn=view.findViewById(R.id.startMyPageBtnId);
        startMyPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, MyPageCourseDetail.class);

                i.putExtra("ttl", enrolledCourseTitle);
                i.putExtra("img", CoverPhoto);
                i.putExtra("oname", enrolledCourseOwner);
                i.putExtra("nthcourse", nthCourse);

                GlobalVar.thisFragmentContents=contents;
                GlobalVar.thisFragmentQuizes=quizes;

                try {
                    v.getContext().startActivity(i);
                }
                catch (Exception ex){
                    String msg=ex.getMessage();
                    Log.d("msg",msg);
                }
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
