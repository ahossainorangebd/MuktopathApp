package orangebd.newaspaper.mymuktopathapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class CourseDetailActivity extends AppCompatActivity {

    private WebView mWebView;
    private Context context;
    String url;
    String ShareURL;
    String title;

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
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0097D7")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_web_search, menu);
        //getMenuInflater().inflate(R.menu.main2, menu);

        return super.onCreateOptionsMenu(menu);
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
}


class MyWebViewClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
}
