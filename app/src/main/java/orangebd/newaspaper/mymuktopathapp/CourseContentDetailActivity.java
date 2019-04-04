package orangebd.newaspaper.mymuktopathapp;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CourseContentDetailActivity extends AppCompatActivity {

    private Context context;

    PopupWindow pw;

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

    private int seekbarProgress;

    private int seekbarStartPosition;
    private int pausePosition;

    private boolean isStartTrackingTouch;
    private boolean isVideoStarted;

    private ImageView imgPauseView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_content_detail);

        context=this;

        final View view = LayoutInflater.from(context).inflate(R.layout.custom_logodetails, null, false);
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

        //TODO
        //TODO
        //final String firstPulse = GlobalVar.gThisVideoPulses.get(0).getmPulseOfVideoMulti();

        /*String secondPulse = GlobalVar.gThisVideoPulses.get(0).getmPulseOfVideoMulti();
        String thirdPulse = GlobalVar.gThisVideoPulses.get(0).getmPulseOfVideoMulti();
        String fourthPulse = GlobalVar.gThisVideoPulses.get(0).getmPulseOfVideoMulti();
        String fifthPulse = GlobalVar.gThisVideoPulses.get(0).getmPulseOfVideoMulti();*/

        //String videoUrl = BASE_URL + "/storage/uploads/videos/" + "user-" + mUserNumber + "/"+ eCode;
        //finalVideoUrl = "<video controls='controls' autoplay='1' src="+ videoUrl+ " height='220' width='350' type='video/mp4' " + "#t=" + timeStatus +  "></video>";

        String videoUrl = BASE_URL + "/storage/uploads/videos/" + "user-" + mUserNumber + "/"+ eCode+"#t=" + timeStatus;

        imgPauseView=findViewById(R.id.pause_button);
        imgPauseView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isVideoStarted) {

                    pausePosition = videoView.getCurrentPosition();
                    videoView.pause();
                    imgPauseView.setImageResource(R.drawable.mukto_play_icon_fresh);
                    isVideoStarted=true;
                }

                else{
                    videoView.seekTo(pausePosition);
                    videoView.start();
                    mProgressBar.setProgress(pausePosition);
                    mProgressBar.postDelayed(onEverySecond, 1000);
                    imgPauseView.setImageResource(R.drawable.mukto_resume_icon_fresh);
                    isVideoStarted=false;
                }
            }
        });


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
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener()
        {
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

                int vprog=0;
                if (isStartTrackingTouch) {
                    vprog = seekbarStartPosition;
                    isStartTrackingTouch=false;
                }
                else
                    vprog = videoView.getCurrentPosition();
                int prog=seekbarProgress;
                if(prog<vprog) {
                    videoView.seekTo(seekbarProgress);
                    mProgressBar.setSecondaryProgress(vprog);
                }
                else {
                    videoView.seekTo(vprog);
                    //mProgressBar.setSecondaryProgress(seekbarProgress);

                }
                mProgressBar.postDelayed(onEverySecond, 1000);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

                isStartTrackingTouch=true;
                seekbarStartPosition=videoView.getCurrentPosition();
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                /*seconds=videoView.getCurrentPosition()/1000;



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

                if(Integer.toString(seconds)=="1"){
                    Toast.makeText(context,"th Position",Toast.LENGTH_SHORT).show();
                }*/

                seconds=videoView.getCurrentPosition()/1000;

                //TODO
                //TODO
                /*int targetPopUp=Integer.parseInt(firstPulse);
                //int targetPopUp=5;

                if(seconds==targetPopUp)
                {
                    //Toast.makeText(context,"A multiple question will pop up on "+firstPulse+"th Second",Toast.LENGTH_SHORT).show();

                    isVideoStarted=false;

                    if (!isVideoStarted) {

                        pausePosition = videoView.getCurrentPosition();
                        videoView.pause();
                        imgPauseView.setImageResource(R.drawable.play_icon_mukto);
                        isVideoStarted=true;
                    }

                    else{
                        videoView.seekTo(pausePosition);
                        videoView.start();
                        mProgressBar.setProgress(pausePosition);
                        mProgressBar.postDelayed(onEverySecond, 1000);
                        imgPauseView.setImageResource(R.drawable.pause_icon_mukto);
                        isVideoStarted=false;
                    }


                    showPopUpImageBox();



                }*/

                textView.setText(""+seconds);
                seekbarProgress=progress;

                if(fromUser) {
                    int vprog=videoView.getCurrentPosition();
                    int prog=progress;
                    if(prog<vprog) {
                        videoView.seekTo(progress);
                        mProgressBar.setSecondaryProgress(vprog);
                    }
                    else {
                        videoView.seekTo(vprog);
                    }
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



    private Runnable onEveryFifteenSecond=new Runnable() {

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


    public String  performPostCall(String requestURL, HashMap<String, String> postDataParams)
    {
        URL url;
        String response = "";

        try {
            url = new URL(requestURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(postDataParams));

            writer.flush();
            writer.close();
            os.close();
            int responseCode=conn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                String line;
                BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line=br.readLine()) != null) {
                    response+=line;
                }
            }

            else {
                response="";
            }

        }
        catch (Exception e) {

            e.printStackTrace();
        }

        return response;
    }

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();

        boolean first = true;

        for(Map.Entry<String, String> entry : params.entrySet()) {

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }

    private void showPopUpImageBox()
    {
        // custom dialog
        final Dialog dialog = new Dialog(context, R.style.DialogCustomTheme);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popupwindowforimage);

        dialog.show();
    }
}
