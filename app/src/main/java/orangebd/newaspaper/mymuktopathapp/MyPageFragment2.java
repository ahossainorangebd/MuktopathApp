package orangebd.newaspaper.mymuktopathapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MyPageFragment2 extends Fragment {

    private Context context;

    private View view;

    private LinearLayout allCourseBtn;
    private LinearLayout recomendedBtn;
    private LinearLayout myPageBtn;
    private LinearLayout downloadsBtn;
    private LinearLayout profileBtn;

    private TextView mCourseTitle;
    private TextView mCourseOwner;

    private Button startMyPageBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_my_page_fragment2, container, false);

        context=getContext();

        allCourseBtn = view.findViewById(R.id.allCourseBtnId);
        recomendedBtn = view.findViewById(R.id.recomendedBtnId);
        myPageBtn = view.findViewById(R.id.myPageBtnId);
        downloadsBtn = view.findViewById(R.id.downloadsBtnId);
        profileBtn = view.findViewById(R.id.profilePageBtnId);


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

        mCourseTitle = view.findViewById(R.id.courseTitle);
        mCourseOwner = view.findViewById(R.id.ownerName);

        String enrolledCourseTitle=GlobalVar.gEnrollCourseList.get(1).getmCourseAliasName();
        String enrolledCourseOwner=GlobalVar.gEnrolledInstitution.get(1).getInstitution_name_owner();

        mCourseTitle.setText(enrolledCourseTitle);
        mCourseOwner.setText(enrolledCourseOwner);

        return view;
    }
}
