package orangebd.newaspaper.mymuktopathapp;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class MyPageFragment1 extends Fragment {

    private Context context;

    private View view;

    private LinearLayout allCourseBtn;
    private LinearLayout recomendedBtn;
    private LinearLayout myPageBtn;
    private LinearLayout downloadsBtn;
    private LinearLayout profileBtn;

    private TextView mCourseTitle;
    private TextView mCourseOwner;

    private TextView mExmNumberTtl;
    private TextView mAssignmentNumberTtl;

    private Button startMyPageBtn;

    private ImageView mCourseDetailCoverImage;

    //GlobalVar.gEnrollCourseList

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_my_page_fragment1, container, false);

        context=getContext();

        allCourseBtn = view.findViewById(R.id.allCourseBtnId);
        recomendedBtn = view.findViewById(R.id.recomendedBtnId);
        myPageBtn = view.findViewById(R.id.myPageBtnId);
        downloadsBtn = view.findViewById(R.id.downloadsBtnId);
        profileBtn = view.findViewById(R.id.profilePageBtnId);
        mCourseDetailCoverImage = view.findViewById(R.id.CourseDetailCoverImage);

        allCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, MainActivity.class);
                v.getContext().startActivity(i);
            }
        });

        recomendedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, RecomendedActivity.class);
                v.getContext().startActivity(i);
            }
        });

        downloadsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(context,DownloadActivity.class);
                v.getContext().startActivity(i);
            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(context,ProfileActivity.class);
                v.getContext().startActivity(i);
            }
        });

        startMyPageBtn=view.findViewById(R.id.startMyPageBtnId);
        startMyPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, MyPageCourseDetail.class);
                v.getContext().startActivity(i);
            }
        });

        ArrayList<DetailDataModelCoursesThumbnails> imgArray=GlobalVar.courseContentDetailList.get(0).getmArrayListThumbnails();

        DetailDataModelCoursesThumbnails imgUrlModel = imgArray.get(0);

        String imgUrl = imgUrlModel.getCover_code_image();

        String CoverPhoto = GlobalVar.gBaseUrl + "/cache-images/" + "219x145x1" + "/uploads/images/" + imgUrl;

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

        String enrolledCourseTitle=GlobalVar.gEnrollCourseList.get(0).getmCourseAliasName();
        String enrolledCourseOwner=GlobalVar.gEnrolledInstitution.get(0).getInstitution_name_owner();
        int mAssignmentNumbers=GlobalVar.gEnrolledInstitution.get(0).getmAssignmentNumbers();
        int mExamNumbers=GlobalVar.gEnrolledInstitution.get(0).getmExamNumbers();

        mCourseTitle.setText(enrolledCourseTitle);
        mCourseOwner.setText(enrolledCourseOwner);
        mExmNumberTtl.setText(Integer.toString(mAssignmentNumbers));
        mAssignmentNumberTtl.setText(Integer.toString(mExamNumbers));

        return view;
    }
}
