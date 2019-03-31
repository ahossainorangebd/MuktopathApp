package orangebd.newaspaper.mymuktopathapp;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyPageCourseDetail extends AppCompatActivity {

    private static RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private Context mContext;

    private String ImgUrl;
    private String DetailDescription;
    private String title;
    private String ownerName;
    private int nthCourse;

    TabsPagerAdapterMyPageDetail myAdapter;
    SlidingTabLayout mSlidingTabLayout;

    private ImageView mCourseDetailCoverImage;

    private TextView mCourseTitle;
    private TextView mCourseOwner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my_page_course_detail);

        getSupportActionBar().hide();

        mContext=this;

        mCourseDetailCoverImage = findViewById(R.id.CourseDetailCoverImage);
        mCourseTitle = findViewById(R.id.courseTitle);
        mCourseOwner = findViewById(R.id.ownerName);

        final ViewPager vpPager = findViewById(R.id.viewpagerNews);
        myAdapter = new TabsPagerAdapterMyPageDetail(getSupportFragmentManager());
        vpPager.setAdapter(myAdapter);

        //Toast.makeText(VideoActivity.this,"Onclicklistner",Toast.LENGTH_LONG).show();

        mSlidingTabLayout = findViewById(R.id.sliding_tabs);

        mSlidingTabLayout.setDistributeEvenly(true);
        mSlidingTabLayout.setViewPager(vpPager);

        ImgUrl = getIntent().getExtras().getString("img");
        title = getIntent().getExtras().getString("ttl");
        ownerName = getIntent().getExtras().getString("oname");
        nthCourse = getIntent().getExtras().getInt("nthcourse");

        GlobalVar.gNthCourse=nthCourse;


        try {
            Picasso.with(mContext)
                    .load(ImgUrl)
                    .fit()
                    .into(mCourseDetailCoverImage);
        }
        catch (Exception ex){}

        mCourseTitle.setText(title);
        mCourseOwner.setText(ownerName);

        //setRecyclerView();

    }

}
