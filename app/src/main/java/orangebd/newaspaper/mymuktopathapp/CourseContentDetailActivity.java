package orangebd.newaspaper.mymuktopathapp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
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
import android.widget.SeekBar;
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

    private VideoView videoView;
    private SeekBar mProgressBar;
    private int time;
    private int seconds;
    private int duration;
    private TextView textView;

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

        //String videoUrl = BASE_URL + "/storage/uploads/videos/" + "user-" + mUserNumber + "/"+ eCode;
        //finalVideoUrl = "<video controls='controls' autoplay='1' src="+ videoUrl+ " height='220' width='350' type='video/mp4' " + "#t=" + timeStatus +  "></video>";

        String videoUrl = BASE_URL + "/storage/uploads/videos/" + "user-" + mUserNumber + "/"+ eCode+"#t=" + timeStatus;

        titleTextView.setText(title);

        detailDescTextView.getSettings().setJavaScriptEnabled(true);
        detailDescTextView.getSettings().setDomStorageEnabled(true);
        detailDescTextView.loadDataWithBaseURL("",DetailDescription, "text/html", "utf-8", "");

        videoView = findViewById(R.id.vdVw);
        mProgressBar = findViewById(R.id.Progressbar);
        textView=findViewById(R.id.txtView);

        Uri uri = Uri.parse(videoUrl);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                duration=videoView.getDuration();
                mProgressBar.setMax(videoView.getDuration());
                mProgressBar.postDelayed(onEverySecond, 1000);
            }
        });
        //mProgressBar.setProgressDrawable(getResources().getDrawable(R.drawable.seekbar_progress));
        //mProgressBar.setThumb(getResources().getDrawable(R.drawable.seekbar_thumb));
        mProgressBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                seconds=videoView.getCurrentPosition()/1000;
                textView.setText(""+seconds);

                //seekBar.setBackgroundColor(Color.rgb(200,200,150));
                //seekBar.setProgressDrawable(getResources().getDrawable(R.drawable.seekbar_progress));

                if(fromUser) {
                    int vprog=videoView.getCurrentPosition();
                    int prog=progress;
                    if(prog<vprog)
                        videoView.seekTo(progress);
                    else
                        videoView.seekTo(vprog);
                }
            }
        });
    }

    private Runnable onEverySecond=new Runnable() {

        @Override
        public void run() {

            if(mProgressBar != null) {
                time = (videoView.getCurrentPosition()*100)/duration;
                seconds=videoView.getCurrentPosition()/1000;
                mProgressBar.setProgress(videoView.getCurrentPosition());
            }

            if(videoView.isPlaying()) {
                mProgressBar.postDelayed(onEverySecond, 1000);
            }

        }
    };
}
