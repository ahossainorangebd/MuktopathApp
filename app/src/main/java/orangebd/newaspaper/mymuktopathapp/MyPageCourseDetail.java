package orangebd.newaspaper.mymuktopathapp;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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


    private String mCourseMotto;
    private String mCourseObj;
    private String mCourseDesc;

    private ImageView goBackFromThisActivity;

    TabsPagerAdapterMyPageDetail myAdapter;
    SlidingTabLayout mSlidingTabLayout;

    private ImageView mCourseDetailCoverImage;

    private TextView mCourseTitle;
    private TextView mCourseOwner;


    //
    private static final float thresholdOffset = 0.5f;
    private static final int thresholdOffsetPixels = 1;
    private boolean scrollStarted, checkDirection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my_page_course_detail);

        getSupportActionBar().hide();

        mContext=this;

        goBackFromThisActivity=findViewById(R.id.goBackFromThisActivityId);
        goBackFromThisActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(mContext,MyPageActivity.class);

                i.putExtra("coursenumber", GlobalVar.nNumberCourseBack);

                startActivity(i);

                // finish();
            }
        });

        mCourseDetailCoverImage = findViewById(R.id.CourseDetailCoverImage);
        mCourseTitle = findViewById(R.id.courseTitle);
        mCourseOwner = findViewById(R.id.ownerName);

        final ViewPager vpPager = findViewById(R.id.viewpagerNews);
        myAdapter = new TabsPagerAdapterMyPageDetail(getSupportFragmentManager());
        vpPager.setAdapter(myAdapter);

        vpPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (checkDirection) {
                    if (thresholdOffset > positionOffset) {


                        GlobalVar.gUnitGoingDirection="right";
                    }
                    else {

                        GlobalVar.gUnitGoingDirection="left";
                    }
                    checkDirection = false;
                }
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (!scrollStarted && state == ViewPager.SCROLL_STATE_DRAGGING) {
                    scrollStarted = true;
                    checkDirection = true;
                } else {
                    scrollStarted = false;
                }
            }
        });

        //Toast.makeText(VideoActivity.this,"Onclicklistner",Toast.LENGTH_LONG).show();

        mSlidingTabLayout = findViewById(R.id.sliding_tabs);

        mSlidingTabLayout.setDistributeEvenly(true);
        mSlidingTabLayout.setViewPager(vpPager);

        if(GlobalVar.isRedirectFromContentPage==true){
            ImgUrl = GlobalVar.gCourseDetailCoverPhoto;
            title = GlobalVar.gCourseDetailTitle;
            ownerName = GlobalVar.gCourseDetailOwnerName;
            nthCourse = GlobalVar.gCourseNumber;
        }
        else {
            ImgUrl = getIntent().getExtras().getString("img");
            title = getIntent().getExtras().getString("ttl");
            ownerName = getIntent().getExtras().getString("oname");
            nthCourse = getIntent().getExtras().getInt("nthcourse");
            mCourseMotto = getIntent().getExtras().getString("cmto");
            mCourseObj = getIntent().getExtras().getString("cobj");
            mCourseDesc = getIntent().getExtras().getString("cdesc");
        }

        GlobalVar.gCourseDetailCoverPhoto =ImgUrl;
        GlobalVar.gCourseDetailTitle =title;
        GlobalVar.gCourseDetailOwnerName =ownerName;
        GlobalVar.gCourseNumber =nthCourse;

        GlobalVar.gCourseMotto =mCourseMotto;
        GlobalVar.gCourseObj =mCourseObj;
        GlobalVar.gCourseDesc =mCourseDesc;

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
