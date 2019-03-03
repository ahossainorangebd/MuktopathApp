package orangebd.newaspaper.mymuktopathapp;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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

    /*private void setRecyclerView() {

        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setNestedScrollingEnabled(false);

       // new GetCoursesContents().execute();
    }*/

    /*public class GetCoursesContents extends AsyncTask<String, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //mProgressSpinner.setIndeterminate(true);
            //mProgressSpinner.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(String... arg0) {

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            adapter=new RecyclerViewAdapterMyPageContents(GlobalVar.thisFragmentContents,mContext);

            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            //mProgressSpinner.setVisibility(View.GONE);
        }
    }*/

}
