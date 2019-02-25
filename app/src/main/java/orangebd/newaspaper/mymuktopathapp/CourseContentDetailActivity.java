package orangebd.newaspaper.mymuktopathapp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.ArrayList;

public class CourseContentDetailActivity extends AppCompatActivity {


    private Context context;

    String DetailDescription;
    String title;
    private String eCode;
    private String timeStatus;
    private String mUserNumber;

    private TextView titleTextView;
    private WebView detailDescTextView;

    private WebView mVideoView;

    private String videoUrl;
    private String finalVideoUrl;

    private String BASE_URL = "http://muktopaath.orangebd.com";

    private VideoView vView;
    Uri videoUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_content_detail);

        context=this;

        View view = LayoutInflater.from(context).inflate(R.layout.custom_logodetails, null, false);
        getSupportActionBar().hide();
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(view);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#7a19aa")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        titleTextView=findViewById(R.id.courseTitle);
        detailDescTextView=findViewById(R.id.detailDesc);



        DetailDescription=getIntent().getExtras().getString("detail");
        title = getIntent().getExtras().getString("ttl");
        eCode = getIntent().getExtras().getString("vcode");
        mUserNumber = getIntent().getExtras().getString("usernumber");
        timeStatus = getIntent().getExtras().getString("videostatus");

        String videoUrl = BASE_URL + "/storage/uploads/videos/" + "user-" + mUserNumber + "/"+ eCode;

        finalVideoUrl = "<video controls='controls' autoplay='1' src="+ videoUrl+ " height='220' width='350' type='video/mp4' " + "#t=" + timeStatus +  "></video>";

        titleTextView.setText(title);

        mVideoView=findViewById(R.id.videoView);
        mVideoView.getSettings().setJavaScriptEnabled(true);
        mVideoView.getSettings().setDomStorageEnabled(true);
        //mVideoView.loadDataWithBaseURL("",finalVideoUrl, "text/html", "utf-8", "");

        mVideoView.loadDataWithBaseURL("", finalVideoUrl, "text/html; charset=utf-8", "UTF-8", null);

        //
        mVideoView.setWebChromeClient(new WebChromeClient());
        mVideoView.getSettings().setPluginState(WebSettings.PluginState.ON);
        mVideoView.getSettings().setPluginState(WebSettings.PluginState.ON_DEMAND);
        mVideoView.setWebViewClient(new WebViewClient());
        mVideoView.getSettings().setJavaScriptEnabled(true);

        detailDescTextView.getSettings().setJavaScriptEnabled(true);
        detailDescTextView.getSettings().setDomStorageEnabled(true);
        detailDescTextView.loadDataWithBaseURL("",DetailDescription, "text/html", "utf-8", "");

    }
}
