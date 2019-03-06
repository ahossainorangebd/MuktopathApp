package orangebd.newaspaper.mymuktopathapp;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
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
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class MyPageFragment1 extends Fragment {

    private Context context;

    private View view;

    private TextView mCourseTitle;
    private TextView mCourseOwner;

    private TextView mExmNumberTtl;
    private TextView mAssignmentNumberTtl;
    private TextView mContentNumberTtl;

    private Button startMyPageBtn;

    private ImageView mCourseDetailCoverImage;

    private LinearLayout mAssignmentSection;
    private LinearLayout mExamNumberSection;
    private LinearLayout mContentNumberSection;

    private TextView mContentHour;
    private TextView mExamHour;
    private TextView mAssignmentHour;

    //GlobalVar.gEnrollCourseList

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_my_page_fragment1, container, false);

        context=getContext();

        mCourseDetailCoverImage = view.findViewById(R.id.CourseDetailCoverImage);

        mContentHour= view.findViewById(R.id.contentHour);
        mExamHour=view.findViewById(R.id.examHour);
        mAssignmentHour=view.findViewById(R.id.assignmentHour);

        ArrayList<DetailDataModelCoursesThumbnails> imgArray=GlobalVar.courseContentDetailList.get(0).getmArrayListThumbnails();

        DetailDataModelCoursesThumbnails imgUrlModel = imgArray.get(0);

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

        final String enrolledCourseTitle=GlobalVar.gEnrollCourseList.get(0).getmCourseAliasName();
        final String enrolledCourseOwner=GlobalVar.gEnrolledInstitution.get(0).getInstitution_name_owner();

        ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> contentArray = GlobalVar.courseContentDetailList.get(0).getmArrayListContentDetails();
        final ArrayList<DetailDataModelCoursesDetailContents> contents = contentArray.get(0);

        ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> quizArray = GlobalVar.courseContentDetailList.get(0).getmArrayListCourseQuizs();
        final ArrayList<DetailDataModelCoursesDetailContents> quizes = quizArray.get(0);

        //Let's count the duration of Content/Quiz/Assignment

        final int nthCourse= 0;
        //int mAssignmentNumbers=GlobalVar.gEnrolledInstitution.get(0).getmAssignmentNumbers();
        //int mExamNumbers=GlobalVar.gEnrolledInstitution.get(0).getmExamNumbers();

        int mAssignmentNumbers=GlobalVar.gEnrollCourseList.get(0).getmAssignmentNumbers();
        int mExamNumbers=GlobalVar.gEnrollCourseList.get(0).getmExamNumbers();
        int mContentNumbers = contents.size();

        mCourseTitle.setText(enrolledCourseTitle);
        mCourseOwner.setText(enrolledCourseOwner);
        mExmNumberTtl.setText(Integer.toString(mAssignmentNumbers));
        mAssignmentNumberTtl.setText(Integer.toString(mExamNumbers));
        mContentNumberTtl.setText(Integer.toString(mContentNumbers));

        if(mAssignmentNumbers>0){

            int contentListCount3=GlobalVar.courseContentDetailList.get(0).getmUnitDataArrayListContent().get(2).size();

            int contentDuration3=0;

            for (int timeCount3=0; timeCount3<contentListCount3-1 ; timeCount3++) {

                String temptestList3=GlobalVar.courseContentDetailList.get(0).getmUnitDataArrayListContent().get(2).get(timeCount3).getmDurationAnother();

                if(!temptestList3.equalsIgnoreCase("null")) {
                    contentDuration3 = contentDuration3 + Integer.parseInt(temptestList3);
                }
            }

            mAssignmentSection.setVisibility(View.VISIBLE);

            mAssignmentHour.setText(Integer.toString(contentDuration3));
        }

        if(mExamNumbers>0){

            int contentListCount2=GlobalVar.courseContentDetailList.get(0).getmUnitDataArrayListContent().get(1).size();
            int contentDuration2=0;

            for (int timeCount2=0; timeCount2<contentListCount2-1 ; timeCount2++) {

                String temptestList2=GlobalVar.courseContentDetailList.get(0).getmUnitDataArrayListContent().get(1).get(timeCount2).getmDurationAnother();

                if(!temptestList2.equalsIgnoreCase("null")) {
                    contentDuration2 = contentDuration2 + Integer.parseInt(temptestList2);
                }
            }

            mExamNumberSection.setVisibility(View.VISIBLE);

            mExamHour.setText(Integer.toString(contentDuration2));
        }

        if(mContentNumbers>0){

            int contentListCount=GlobalVar.courseContentDetailList.get(0).getmUnitDataArrayListContent().get(0).size();
            int contentDuration=0;

            for (int timeCount=0; timeCount<contentListCount-1 ; timeCount++) {

                String temptestList=GlobalVar.courseContentDetailList.get(0).getmUnitDataArrayListContent().get(0).get(timeCount).getmDurationAnother();

                if(!temptestList.equalsIgnoreCase("null")) {
                    contentDuration = contentDuration + Integer.parseInt(temptestList);
                }
            }

            mContentNumberSection.setVisibility(View.VISIBLE);
            mContentHour.setText(Integer.toString(contentDuration));
        }


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

}
