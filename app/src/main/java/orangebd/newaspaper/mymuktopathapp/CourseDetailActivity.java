package orangebd.newaspaper.mymuktopathapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

public class CourseDetailActivity extends AppCompatActivity {

    private static RecyclerView recyclerView;
    private ProgressBar mProgressSpinner;
    private RecyclerView.Adapter adapter;

    private WebView mWebView;
    private Context context;
    String ImgUrl;
    String DetailDescription;
    String title;

    private TextView titleTextView;
    private TextView detailDescTextView;
    private ImageView CoverPhoto;

    //private ImageView mLogoIcon;

    ArrayList<DetailDataModel> detailListHeadLine=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        context=this;

        //TODO
        /*mWebView=findViewById(R.id.myWebView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new MyWebViewClient());*/

        View view = LayoutInflater.from(context).inflate(R.layout.custom_logodetails, null, false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(view);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#7a19aa")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

         titleTextView=findViewById(R.id.courseTitle);
         detailDescTextView=findViewById(R.id.detailDesc);
         CoverPhoto=findViewById(R.id.CourseDetailCoverImage);

        ImgUrl = getIntent().getExtras().getString("img");
        DetailDescription=getIntent().getExtras().getString("detail");
        title = getIntent().getExtras().getString("ttl");

        titleTextView.setText(title);
        detailDescTextView.setText(DetailDescription);

        try {
            Picasso.with(context)
                    .load(ImgUrl)
                    .into(CoverPhoto);
        }
        catch (Exception ex){}

        /*try
        {
            url = getIntent().getExtras().getString("URL");
            ShareURL=getIntent().getExtras().getString("SURL");
            title = getIntent().getExtras().getString("ttl");

            *//*childM = findViewById(R.id.ChildM2);
            childM.setText(title);*//*

           // mLogoIcon=findViewById(R.id.rtvheadlogo);

            if (null != url) {

                //TODO
                mWebView.getSettings().setJavaScriptEnabled(true);
                mWebView.getSettings().setDomStorageEnabled(true);
                String summary = getIntent().getExtras().getString("URL");
                //mWebView.loadData(summary, "text/html", null);
                mWebView.loadDataWithBaseURL("", getIntent().getExtras().getString("URL"), "text/html", "utf-8", "");
            }
        }

        catch (Exception ex){
            Log.d("",ex.getMessage());
        }*/

        /*mShareImage=view.findViewById(R.id.shareBtnId);

        mShareImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareIt();
            }
        });*/


        /*mLogoIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteCache(context);
                Intent i=new Intent(context,MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                view.getContext().startActivity(i);
            }
        });*/

        setRecyclerView();
    }

    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            deleteDir(dir);
        } catch (Exception e) {}
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if(dir!= null && dir.isFile()) {
            return dir.delete();
        }
        else {
            return false;
        }
    }

    /*private void shareIt() {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Rtv");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, url);
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, ShareURL);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }*/

    @Override
    public void onBackPressed() {
        finish();
        return;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id==R.id.disablePushNotification){
            Intent intent = new Intent();
            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.putExtra("app_package", getPackageName());
            intent.putExtra("app_uid", getApplicationInfo().uid);
            intent.putExtra("android.provider.extra.APP_PACKAGE", getPackageName());
            startActivity(intent);
        }

        else if (id == android.R.id.home) {
            finish();
            return true;
        }
        /*else if (id==R.id.searchBtnId)
            shareIt();*/
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPause(){
        super.onPause();

       //TODO
        //mWebView.onPause();
    }

    private void setRecyclerView() {

        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setNestedScrollingEnabled(false);

        adapter=new RecyclerViewAdapterCourseDetailContent(GlobalVar.courseContentDetailList,context);

        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}
