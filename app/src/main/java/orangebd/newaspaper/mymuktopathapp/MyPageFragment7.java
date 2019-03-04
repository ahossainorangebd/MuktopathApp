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

public class MyPageFragment7 extends Fragment {

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_my_page_fragment7, container, false);

        context=getContext();

        mCourseDetailCoverImage = view.findViewById(R.id.CourseDetailCoverImage);



        startMyPageBtn=view.findViewById(R.id.startMyPageBtnId);
        startMyPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, MyPageCourseDetail.class);
                v.getContext().startActivity(i);
            }
        });

        ArrayList<DetailDataModelCoursesThumbnails> imgArray=GlobalVar.courseContentDetailList.get(0).getmArrayListThumbnails();

        DetailDataModelCoursesThumbnails imgUrlModel = imgArray.get(6);

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
        final ArrayList<DetailDataModelCoursesDetailContents> contents = contentArray.get(7);

        ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> quizArray = GlobalVar.courseContentDetailList.get(0).getmArrayListCourseQuizs();
        final ArrayList<DetailDataModelCoursesDetailContents> quizes = quizArray.get(7);

        final int nthCourse= 7;
        int mAssignmentNumbers=GlobalVar.gEnrolledInstitution.get(7).getmAssignmentNumbers();
        int mExamNumbers=GlobalVar.gEnrolledInstitution.get(7).getmExamNumbers();
        int mContentNumbers = contents.size();

        mCourseTitle.setText(enrolledCourseTitle);
        mCourseOwner.setText(enrolledCourseOwner);
        mExmNumberTtl.setText(Integer.toString(mAssignmentNumbers));
        mAssignmentNumberTtl.setText(Integer.toString(mExamNumbers));
        mContentNumberTtl.setText(Integer.toString(mContentNumbers));


        if(mAssignmentNumbers==0){
            mAssignmentSection.setVisibility(View.INVISIBLE);
        }

        if(mExamNumbers==0){
            mExamNumberSection.setVisibility(View.INVISIBLE);
        }

        if(mContentNumbers==0){
            mContentNumberSection.setVisibility(View.INVISIBLE);
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
