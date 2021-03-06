package orangebd.newaspaper.mymuktopathapp;

import android.app.Dialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
import java.util.Objects;

import static android.net.sip.SipErrorCode.TIME_OUT;

public class CourseContentDetailActivity extends AppCompatActivity {

    private Context context;

    PopupWindow pw;

    private String getPulse;

    String DetailDescription;
    String title;

    private String eCode;
    private String timeStatus;
    private String unitId;
    private String contentSize;
    private String contentDuration;
    private String chooseVideoType;
    private String chooseDocType;

    private String contentIconType;
    private String lessonCompleteness;

    private String mListPosition;
    private String mUserNumber;

    private TextView mPulseQuesText;
    private WebView detailDescTextView;
    private WebView detailDescManualContent;

    private WebView mToutubeWebView;

    private boolean isGotQuestion;
    private boolean isNoQuestion;

    private TextView titleTextView;

    private ViewPager vpPager;

    private WebView mVideoView;

    private String videoUrl;
    private String finalVideoUrl;

    //private String BASE_URL = "http://muktopaath.orangebd.com";
    //private String BASE_URL = "http://testadmin.muktopaath.orangebd.com";

    private String BASE_URL = "http://muktopaath.orangebd.com";

    private VideoView vView;
    Uri videoUri;

    private VideoView videoView;
    private SeekBar mProgressBar;
    private int time;
    private int seconds;
    private int duration;
    private TextView textView;
    private TextView textViewTt;

    private int seekbarProgress;

    private int seekbarStartPosition;
    private int pausePosition;

    private boolean isStartTrackingTouch;
    private boolean isVideoStarted;

    private ImageView imgPauseView;

    private TextView mCustomContentTitle;


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    TabsPagerAdapterPulseQuiz myAdapter;


    DBHelper dbHelper;

    private HashMap<String,String> mapHitGamification;

    private int targetPopUp;

    private HashMap<String,String> mapHit;


    private ImageView mHitLikeBtn;
    private ImageView mHitDislikeBtn;
    private ImageView mHitFlagBtn;

    private ImageView mDownloadBtn;

    private String hitLikeUrl = GlobalVar.gApiBaseUrl + "/api/content/feedback/";

    private String getLikeUnlikeDetailUrl = GlobalVar.gApiBaseUrl + "/api/content/feedback/";

    private String hitGamificationForLessonComplete = GlobalVar.gApiBaseUrl + "/api/gamification/course";

    private String thisCourseId;
    private String thisBatchId;

    private String mTotal_dislikes;
    private String mTotal_flags;
    private String mTotal_likes;

    private String mDisliked;
    private String mFlagged;
    private String mLiked;


    private TextView mLikeNumberTv;
    private TextView mDislikeNumberTv;
    private TextView mFlagNumberTv;


    private int forwardable=1;


    private LinearLayout mVideoLayOut;
    private LinearLayout mYoutubeVideoLayOut;


    private int completedPercentage=97;


    private int videoEndSecond;

    // for posting completeness
    private HashMap<String,String> mapSubmit;

    String urlCompletenessSubmit = GlobalVar.gApiBaseUrl +"/api/journey/status/app/";


    private int CountAUnitsLessons;

    private String htmlText;


    private ScrollView mDetailPageScrollView;


    private static int TIME_OUT = 1000;



    // For the pop up quiz lines

    private FrameLayout mQuizLine1;
    private FrameLayout mQuizLine2;
    private FrameLayout mQuizLine3;
    private FrameLayout mQuizLine4;
    private FrameLayout mQuizLine5;
    private FrameLayout mQuizLine6;
    private FrameLayout mQuizLine7;
    private FrameLayout mQuizLine8;
    private FrameLayout mQuizLine9;
    private FrameLayout mQuizLine10;
    private FrameLayout mQuizLine11;
    private FrameLayout mQuizLine12;
    private FrameLayout mQuizLine13;
    private FrameLayout mQuizLine14;
    private FrameLayout mQuizLine15;
    private FrameLayout mQuizLine16;
    private FrameLayout mQuizLine17;
    private FrameLayout mQuizLine18;
    private FrameLayout mQuizLine19;
    private FrameLayout mQuizLine20;
    private FrameLayout mQuizLine21;
    private FrameLayout mQuizLine22;
    private FrameLayout mQuizLine23;
    private FrameLayout mQuizLine24;
    private FrameLayout mQuizLine25;


    private LinearLayout mFrameLay;



    private boolean isPopUpComplenessShowed=false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_content_detail);

        context=this;

        final View view = LayoutInflater.from(context).inflate(R.layout.custom_logodetails_course_content_title, null, false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(view);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#7a19aa")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mLikeNumberTv=findViewById(R.id.likeNumber);
        mDislikeNumberTv=findViewById(R.id.dislikeNumber);
        mFlagNumberTv=findViewById(R.id.flagNumber);

        mVideoLayOut=findViewById(R.id.videoLayOut);
        mYoutubeVideoLayOut=findViewById(R.id.youtubeVideoLayOut);
        mDetailPageScrollView=findViewById(R.id.detailPageScrollView);


        videoView = findViewById(R.id.vdVw);


        //titleTextView=findViewById(R.id.courseTitle);
        detailDescManualContent=findViewById(R.id.detailDescManualContent);

        detailDescTextView=findViewById(R.id.detailDesc);

        mToutubeWebView=findViewById(R.id.youtubeWebViewId);

        if(GlobalVar.isRedirectFromContentPage){

            DetailDescription=GlobalVar.gDescriptionText;
            title = GlobalVar.gCourseDetailTitle;
            eCode = GlobalVar.gVideoCode;
            mUserNumber = GlobalVar.gUserNumber;
            timeStatus = GlobalVar.gTimeStatus;
            contentDuration = GlobalVar.gContentDuration;
            chooseVideoType = GlobalVar.gChooseVideoType;
            chooseDocType = GlobalVar.gChooseDocType;
            contentIconType = GlobalVar.gContentIconType;
            lessonCompleteness = GlobalVar.gLessonCompleteness;

            String testing123="";
        }
        else {
            DetailDescription=getIntent().getExtras().getString("detail");
            title = getIntent().getExtras().getString("ttl");
            eCode = getIntent().getExtras().getString("vcode");
            mUserNumber = getIntent().getExtras().getString("usernumber");
            timeStatus = getIntent().getExtras().getString("videostatus");
            unitId = getIntent().getExtras().getString("unitid");
            contentSize = getIntent().getExtras().getString("csize");
            contentDuration = getIntent().getExtras().getString("cduration");
            chooseVideoType = getIntent().getExtras().getString("cvtype");
            chooseDocType = getIntent().getExtras().getString("cdtype");
            contentIconType = getIntent().getExtras().getString("ciconype");
            lessonCompleteness = getIntent().getExtras().getString("thislessoncompltness");

            String testing123="";
        }



        if(contentIconType.equalsIgnoreCase("video"))
        {
            if (chooseVideoType.equalsIgnoreCase("1")) {
                mVideoLayOut.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        imgPauseView.setVisibility(View.VISIBLE);

                        final Handler handler3 = new Handler();
                        handler3.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                imgPauseView.setVisibility(View.GONE);
                            }
                        }, 2000);
                    }
                });

                final Handler handler2 = new Handler();
                handler2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgPauseView.setVisibility(View.GONE);
                    }
                }, 2000);
            }
        }



        GlobalVar.gLastReadLessonTitle=title;


        final int listPositionNumber = Integer.parseInt(GlobalVar.gListPosition);

        mCustomContentTitle=view.findViewById(R.id.muktoCustomContentTitle);
        mCustomContentTitle.setText(title);


        final ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> pulseMultiArray  = GlobalVar.thisFragmentPulses;
        final ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> pulseQuesListWithAns = GlobalVar.thisFragmentPulseQs;

        try {
            String pulseQuizTitle = pulseQuesListWithAns.get(Integer.parseInt(GlobalVar.gListPosition)).get(0).getMpQuizTitle();
            GlobalVar.gPulseTitle=pulseQuizTitle;

            ArrayList<DetailDataModelCoursesDetailContents> optionArray = pulseQuesListWithAns.get(Integer.parseInt(GlobalVar.gListPosition));
            GlobalVar.gPulseAnswerArray=optionArray;

            String exing1224sf="";
        }
        catch (Exception ex) {
            Log.d("", "onCreate: ");
        }

        String aqwqwqw="";

        //TODO
        //TODO

        /*String firstPulse = GlobalVar.gThisVideoPulses.get(0).getmPulseOfVideoMulti();
        String secondPulse = GlobalVar.gThisVideoPulses.get(0).getmPulseOfVideoMulti();
        String thirdPulse = GlobalVar.gThisVideoPulses.get(0).getmPulseOfVideoMulti();
        String fourthPulse = GlobalVar.gThisVideoPulses.get(0).getmPulseOfVideoMulti();
        String fifthPulse = GlobalVar.gThisVideoPulses.get(0).getmPulseOfVideoMulti();*/

        /*for(int allPulse=0; allPulse<GlobalVar.gThisVideoPulses.size(); allPulse++){

            int eachPulse = GlobalVar.gThisVideoPulses.get(allPulse).getmPulseOfVideoMultiId();

            if(eachPulse==listPositionNumber) {
                String getPulse = GlobalVar.gThisVideoPulses.get(allPulse).getmPulseOfVideoMulti();
                Toast.makeText(context,"This video has question.",Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(context,"This video has no question.",Toast.LENGTH_SHORT).show();
            }
        }*/


        /** previously the loop of getting pulse was here */



        //String videoUrl = BASE_URL + "/storage/uploads/videos/" + "user-" + mUserNumber + "/"+ eCode;
        //finalVideoUrl = "<video controls='controls' autoplay='1' src="+ videoUrl+ " height='220' width='350' type='video/mp4' " + "#t=" + timeStatus +  "></video>";

        String abcdpqwpqw="";

        if(contentIconType.equalsIgnoreCase("video"))
        {
            if (chooseVideoType.equalsIgnoreCase("0")) {


                mYoutubeVideoLayOut.setVisibility(View.VISIBLE);
                mDetailPageScrollView.setVisibility(View.INVISIBLE);
                mVideoLayOut.setVisibility(View.INVISIBLE);
                videoView.setVisibility(View.INVISIBLE);

                htmlText = "<div class='container'>" + "<div class='row'>" +
                        "<iframe src=\"" + eCode+ "?autoplay=1" + "\" style=\"width:100%; height:200px\"> </iframe>" +
                        "<br/>" + "<br/>" + DetailDescription + "<br/>" + "</div>" + "</div>";

                String abcd = "";

            } else {

                /** Let's set the quiz lines */




                mYoutubeVideoLayOut.setVisibility(View.INVISIBLE);
                mVideoLayOut.setVisibility(View.VISIBLE);
                mDetailPageScrollView.setVisibility(View.VISIBLE);
                videoView.setVisibility(View.VISIBLE);
            }
        }
        else if(contentIconType.equalsIgnoreCase("document")){
            if (chooseDocType.equalsIgnoreCase("0")) {

                eCode = eCode.replace("width=\"722px\"", "width=\"322px\"");
                eCode = eCode.replace("height=\"565px\"", "height=\"200px\"");

                mYoutubeVideoLayOut.setVisibility(View.VISIBLE);
                mDetailPageScrollView.setVisibility(View.INVISIBLE);
                mVideoLayOut.setVisibility(View.INVISIBLE);
                videoView.setVisibility(View.INVISIBLE);

                htmlText = "<div class='container'>" + "<div class='row'>" +
                        eCode +
                        "<br/>" + "<br/>" + DetailDescription + "<br/>" + "</div>" + "</div>";

                String abcd = "";

            }
            else {

                mYoutubeVideoLayOut.setVisibility(View.INVISIBLE);
                mVideoLayOut.setVisibility(View.VISIBLE);
                mDetailPageScrollView.setVisibility(View.VISIBLE);
                videoView.setVisibility(View.VISIBLE);
            }
        }
        else{
            if (chooseVideoType.equalsIgnoreCase("1")) {
                mYoutubeVideoLayOut.setVisibility(View.VISIBLE);
                mDetailPageScrollView.setVisibility(View.INVISIBLE);
                mVideoLayOut.setVisibility(View.INVISIBLE);
                videoView.setVisibility(View.INVISIBLE);

                htmlText = "<div class='container'>" + "<div class='row'>" +
                        "<iframe src=\"" + eCode+ "?autoplay=1" + "\" style=\"width:100%; height:200px\"> </iframe>" +
                        "<br/>" + "<br/>" + DetailDescription + "<br/>" + "</div>" + "</div>";

                String abcd = "";

            } else {
                mYoutubeVideoLayOut.setVisibility(View.INVISIBLE);
                mVideoLayOut.setVisibility(View.VISIBLE);
                mDetailPageScrollView.setVisibility(View.VISIBLE);
                videoView.setVisibility(View.VISIBLE);
            }
        }


        videoUrl = BASE_URL + "/storage/uploads/videos/" + "user-" + mUserNumber + "/"+ eCode+"#t=" + timeStatus;

        GlobalVar.gEcode=eCode;

        if(contentIconType.equalsIgnoreCase("video"))
        {
            if (chooseVideoType.equalsIgnoreCase("1")) {
                imgPauseView = findViewById(R.id.pause_button);
                imgPauseView.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        if (!isVideoStarted) {

                            pausePosition = videoView.getCurrentPosition();
                            videoView.pause();
                            imgPauseView.setImageResource(R.drawable.mukto_video_play_icon);
                            isVideoStarted = true;
                        }
                        else {
                            videoView.seekTo(pausePosition);
                            videoView.start();
                            mProgressBar.setProgress(pausePosition);
                            mProgressBar.postDelayed(onEverySecond, 1000);
                            imgPauseView.setImageResource(R.drawable.mukto_video_pause_icon);
                            isVideoStarted = false;


                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    imgPauseView.setVisibility(View.GONE);
                                }
                            }, 2000);
                        }
                    }
                });
            } else {
                imgPauseView = findViewById(R.id.pause_button);
                imgPauseView.setVisibility(View.GONE);
            }
        }
        else{
            imgPauseView = findViewById(R.id.pause_button);
            imgPauseView.setVisibility(View.GONE);
        }

        //titleTextView.setText(title);

        detailDescManualContent.getSettings().setJavaScriptEnabled(true);
        detailDescManualContent.getSettings().setDomStorageEnabled(true);
        detailDescManualContent.loadDataWithBaseURL("",htmlText, "text/html", "utf-8", "");

        detailDescTextView.getSettings().setJavaScriptEnabled(true);
        detailDescTextView.getSettings().setDomStorageEnabled(true);
        detailDescTextView.loadDataWithBaseURL("",DetailDescription, "text/html", "utf-8", "");

        mToutubeWebView.getSettings().setJavaScriptEnabled(true);
        mToutubeWebView.getSettings().setDomStorageEnabled(true);
        mToutubeWebView.loadDataWithBaseURL("",htmlText, "text/html", "utf-8", "");


        mProgressBar = findViewById(R.id.Progressbar);
        textView=findViewById(R.id.txtView);
        textViewTt=findViewById(R.id.txtViewTt);

        if(contentIconType.equalsIgnoreCase("video"))
        {
            if (chooseVideoType.equalsIgnoreCase("1")) {
                Uri uri = Uri.parse(videoUrl);
                videoView.setVideoURI(uri);
                videoView.requestFocus();
                videoView.start();
                videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        duration = videoView.getDuration();
                        mProgressBar.setMax(videoView.getDuration());
                        mProgressBar.postDelayed(onEverySecond, 1000);
                    }
                });
            }
        }
        //mProgressBar.setProgressDrawable(getResources().getDrawable(R.drawable.seekbar_progress));
        //mProgressBar.setThumb(getResources().getDrawable(R.drawable.seekbar_thumb));



        if(contentIconType.equalsIgnoreCase("video"))
        {
            if (chooseVideoType.equalsIgnoreCase("1")) {
                mProgressBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                        int vprog = 0;
                        if (isStartTrackingTouch) {
                            vprog = seekbarStartPosition;
                            isStartTrackingTouch = false;
                        } else
                            vprog = videoView.getCurrentPosition();
                        int prog = seekbarProgress;
                        if (prog < vprog) {
                            videoView.seekTo(seekbarProgress);
                            mProgressBar.setSecondaryProgress(vprog);
                        } else {
                            videoView.seekTo(vprog);
                            //mProgressBar.setSecondaryProgress(seekbarProgress);
                        }


                        if (forwardable != 1) {
                            if (prog < vprog) {
                                videoView.seekTo(seekbarProgress);
                                mProgressBar.setSecondaryProgress(vprog);
                            } else {
                                videoView.seekTo(vprog);
                                //mProgressBar.setSecondaryProgress(seekbarProgress);
                            }
                        } else {
                            int seekbarProgress = seekBar.getProgress();
                            videoView.seekTo(seekbarProgress);
                        }

                        mProgressBar.postDelayed(onEverySecond, 1000);

                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                        isStartTrackingTouch = true;
                        seekbarStartPosition = videoView.getCurrentPosition();
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

                        seconds = videoView.getCurrentPosition() / 1000;

                        //TODO
                        //TODO

                        /** a later version of get Pulse section */

                        for(int allPulse=0; allPulse<GlobalVar.thisFragmentPulses.size(); allPulse++)
                        {

                            int eachPulse = GlobalVar.thisFragmentPulses.get(allPulse).get(0).getmPulseOfVideoMultiId();

                            if(eachPulse==listPositionNumber) {
                                getPulse = GlobalVar.thisFragmentPulses.get(allPulse).get(0).getmPulseOfVideoMulti();

                                //TODO
                                //Toast.makeText(context,"This video has question.",Toast.LENGTH_SHORT).show();

                                isGotQuestion=true;
                            }
                            else {

                                if(isNoQuestion==false){

                                    if(isGotQuestion==true){
                                        String nothing="Nothing";
                                    }
                                    else {
                                        //TODO
                                        //Toast.makeText(context, "This video has no question.", Toast.LENGTH_SHORT).show();
                                        isNoQuestion=true;
                                    }
                                }
                                else {
                                    String mNothing="nothing";
                                }
                            }
                        }

                        if (getPulse != null) {

                            targetPopUp = Integer.parseInt(getPulse);

                            String exing="";
                        } else {
                            targetPopUp = -1;
                        }



                        String exing1="11";

                        //int targetPopUp=5;

                        if (seconds == targetPopUp) {
                            //Toast.makeText(context,"A multiple question will pop up on "+firstPulse+"th Second",Toast.LENGTH_SHORT).show();

                            String wwww = "";

                            isVideoStarted = false;

                            if (!isVideoStarted) {

                                if (GlobalVar.gPulseMultiMarkCount == 0) {
                                    pausePosition = videoView.getCurrentPosition();
                                    videoView.pause();
                                    imgPauseView.setImageResource(R.drawable.mukto_video_play_icon);
                                    isVideoStarted = true;

                                }

                                if (GlobalVar.gPulseMultiMarkCount == 0) {

                                    showPopUpQuestionBox();
                                }

                            } else {
                                videoView.seekTo(pausePosition);
                                videoView.start();
                                mProgressBar.setProgress(pausePosition);
                                mProgressBar.postDelayed(onEverySecond, 1000);
                                imgPauseView.setImageResource(R.drawable.mukto_video_pause_icon);
                                isVideoStarted = false;
                            }

                        }

                        videoEndSecond = Integer.parseInt(contentDuration);

                        if (seconds == videoEndSecond-1) {

                            mProgressBar.setProgress(100);

                            if(lessonCompleteness.equalsIgnoreCase("100")) {
                                String testingabcd ="";
                            }
                            else{

                                if(isPopUpComplenessShowed==false) {

                                    showPopUpCompletenessSubmit();
                                    isPopUpComplenessShowed=true;
                                }
                                else{
                                    String testingabcd121212 ="";
                                }





                            }

                        }

                        textView.setText("" + seconds);


                        /*d minutes = (seconds % 3600) / 60;
                        int seconds2 = seconds % 60;

                        seconds = String.format("%02d:%02d", minutes, seconds2);*/


                        String HH= String.format("%02d",seconds/3600)+":";
                        String MM = String.format("%02d",seconds/60%60)+":";
                        String SS = String.format("%02d",seconds%60);

                        textView.setText(HH+MM+SS);
                        seekbarProgress = progress;

                        if (fromUser) {
                            int vprog = videoView.getCurrentPosition();
                            int prog = progress;
                            if (prog < vprog) {
                                videoView.seekTo(progress);
                                mProgressBar.setSecondaryProgress(vprog);
                            } else {
                                videoView.seekTo(vprog);
                            }
                        }


                        if(getPulse!=null) {
                            double firstPopUpTimeAsPercentage = ((100 / Double.parseDouble(contentDuration)) * Double.parseDouble(getPulse)) / 2;

                            int firstPopUpTimeAsPercentageInt = (Integer.parseInt(String.format("%.0f", firstPopUpTimeAsPercentage)));

                            if(firstPopUpTimeAsPercentageInt==1) {
                                mQuizLine1 = findViewById(R.id.quizLine1);
                                mQuizLine1.setVisibility(View.VISIBLE);
                            }
                            if(firstPopUpTimeAsPercentageInt==2) {
                                mQuizLine2 = findViewById(R.id.quizLine2);
                                mQuizLine2.setVisibility(View.VISIBLE);
                            }

                            if(firstPopUpTimeAsPercentageInt==3) {
                                mQuizLine3 = findViewById(R.id.quizLine3);
                                mQuizLine3.setVisibility(View.VISIBLE);
                            }
                            if(firstPopUpTimeAsPercentageInt==4) {
                                mQuizLine4 = findViewById(R.id.quizLine4);
                                mQuizLine4.setVisibility(View.VISIBLE);
                            }
                            if(firstPopUpTimeAsPercentageInt==1) {
                                mQuizLine5 = findViewById(R.id.quizLine5);
                                mQuizLine5.setVisibility(View.VISIBLE);
                            }
                            if(firstPopUpTimeAsPercentageInt==1) {
                                mQuizLine6 = findViewById(R.id.quizLine6);
                                mQuizLine6.setVisibility(View.VISIBLE);
                            }
                            if(firstPopUpTimeAsPercentageInt==1) {
                                mQuizLine7 = findViewById(R.id.quizLine7);
                                mQuizLine7.setVisibility(View.VISIBLE);
                            }
                            if(firstPopUpTimeAsPercentageInt==1) {
                                mQuizLine8 = findViewById(R.id.quizLine8);
                                mQuizLine8.setVisibility(View.VISIBLE);
                            }
                            if(firstPopUpTimeAsPercentageInt==1) {
                                mQuizLine9 = findViewById(R.id.quizLine9);
                                mQuizLine9.setVisibility(View.VISIBLE);
                            }
                            if(firstPopUpTimeAsPercentageInt==1) {
                                mQuizLine10 = findViewById(R.id.quizLine10);
                                mQuizLine10.setVisibility(View.VISIBLE);
                            }
                            if(firstPopUpTimeAsPercentageInt==1) {
                                mQuizLine11 = findViewById(R.id.quizLine11);
                                mQuizLine11.setVisibility(View.VISIBLE);
                            }
                            if(firstPopUpTimeAsPercentageInt==1) {
                                mQuizLine12 = findViewById(R.id.quizLine12);
                                mQuizLine12.setVisibility(View.VISIBLE);
                            }
                            if(firstPopUpTimeAsPercentageInt==1) {
                                mQuizLine13 = findViewById(R.id.quizLine13);
                                mQuizLine13.setVisibility(View.VISIBLE);
                            }
                            if(firstPopUpTimeAsPercentageInt==1) {
                                mQuizLine14 = findViewById(R.id.quizLine14);
                                mQuizLine14.setVisibility(View.VISIBLE);
                            }
                            if(firstPopUpTimeAsPercentageInt==1) {
                                mQuizLine15 = findViewById(R.id.quizLine15);
                                mQuizLine15.setVisibility(View.VISIBLE);
                            }
                            if(firstPopUpTimeAsPercentageInt==1) {
                                mQuizLine16 = findViewById(R.id.quizLine16);
                                mQuizLine16.setVisibility(View.VISIBLE);
                            }
                            if(firstPopUpTimeAsPercentageInt==1) {
                                mQuizLine17 = findViewById(R.id.quizLine17);
                                mQuizLine17.setVisibility(View.VISIBLE);
                            }
                            if(firstPopUpTimeAsPercentageInt==18) {
                                mQuizLine18 = findViewById(R.id.quizLine18);
                                mQuizLine18.setVisibility(View.VISIBLE);
                            }
                            if(firstPopUpTimeAsPercentageInt==19) {
                                mQuizLine19 = findViewById(R.id.quizLine19);
                                mQuizLine19.setVisibility(View.VISIBLE);
                            }
                            if(firstPopUpTimeAsPercentageInt==20) {
                                mQuizLine20 = findViewById(R.id.quizLine20);
                                mQuizLine20.setVisibility(View.VISIBLE);
                            }
                            if(firstPopUpTimeAsPercentageInt==21) {
                                mQuizLine21 = findViewById(R.id.quizLine21);
                                mQuizLine21.setVisibility(View.VISIBLE);
                            }
                            if(firstPopUpTimeAsPercentageInt==22) {
                                mQuizLine22 = findViewById(R.id.quizLine22);
                                mQuizLine22.setVisibility(View.VISIBLE);
                            }
                            if(firstPopUpTimeAsPercentageInt==23) {
                                mQuizLine23 = findViewById(R.id.quizLine23);
                                mQuizLine23.setVisibility(View.VISIBLE);
                            }
                            if(firstPopUpTimeAsPercentageInt==24) {
                                mQuizLine24 = findViewById(R.id.quizLine24);
                                mQuizLine24.setVisibility(View.VISIBLE);
                            }
                            if(firstPopUpTimeAsPercentageInt==25) {
                                mQuizLine25 = findViewById(R.id.quizLine25);
                                mQuizLine25.setVisibility(View.VISIBLE);
                            }

                        }

                    }



                });
            }
        }

        //thisCourseId=GlobalVar.gEnrollCourseList.get(GlobalVar.gNthCourse).getmId();

        thisCourseId=GlobalVar.gCourseIdListForCourseId.get(GlobalVar.gNthCourse).getIdCourse();
        thisBatchId=GlobalVar.gEnrollCourseList.get(GlobalVar.gNthCourse).getmId();

        new GetLykUnlykHistory().execute(getLikeUnlikeDetailUrl+ thisCourseId + "/" + unitId + "/" + GlobalVar.gLessonId);


        GlobalVar.gLastReadLessonCourseId=thisCourseId;

        mHitLikeBtn=findViewById(R.id.hitlikebtn);
        mHitLikeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mapHit =  new HashMap<>();

                if(mLiked.equalsIgnoreCase("1")) {
                    mapHit.put("action_type", "like");
                    mapHit.put("liked", "0");
                    mapHit.put("disliked", "0");
                    mapHit.put("unit_id", unitId);
                    mapHit.put("lesson_id", GlobalVar.gLessonId);

                    mHitLikeBtn.setImageResource(R.drawable.mukto_like_icon_fade);


                    int totalLikeInt = Integer.parseInt(mTotal_likes);

                    mLikeNumberTv.setText(String.valueOf(totalLikeInt-1));

                    //mLiked="0";

                }
                else {
                    mapHit.put("action_type", "like");
                    mapHit.put("liked", "1");
                    mapHit.put("disliked", "0");
                    mapHit.put("unit_id", unitId);
                    mapHit.put("lesson_id", GlobalVar.gLessonId);

                    mHitLikeBtn.setImageResource(R.drawable.mukto_like_icon_tap);
                    mHitDislikeBtn.setImageResource(R.drawable.mukto_dislike_icon_fade);


                    int totalLikeInt = Integer.parseInt(mTotal_likes);

                    mLikeNumberTv.setText(String.valueOf(totalLikeInt));

                    //mLiked="1";
                }

                new HitLikeOrUnlike().execute(hitLikeUrl+ thisCourseId);
            }
        });



        mHitDislikeBtn=findViewById(R.id.hitDislikebtn);
        mHitDislikeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapHit =  new HashMap<>();

                if(mDisliked.equalsIgnoreCase("1")){
                    mapHit.put("action_type", "dislike");
                    mapHit.put("liked", "0");
                    mapHit.put("disliked", "0");
                    mapHit.put("unit_id", GlobalVar.gUnitId);
                    mapHit.put("lesson_id", GlobalVar.gLessonId);

                    mHitDislikeBtn.setImageResource(R.drawable.mukto_dislike_icon_fade);

                    int totalDislikeInt = Integer.parseInt(mTotal_dislikes);

                    mDislikeNumberTv.setText(String.valueOf(totalDislikeInt));

                    //mDisliked="0";
                }
                else {
                    mapHit.put("action_type", "dislike");
                    mapHit.put("liked", "0");
                    mapHit.put("disliked", "1");
                    mapHit.put("unit_id", GlobalVar.gUnitId);
                    mapHit.put("lesson_id", GlobalVar.gLessonId);

                    mHitDislikeBtn.setImageResource(R.drawable.mukto_dislike_icon_tap);
                    mHitLikeBtn.setImageResource(R.drawable.mukto_like_icon_fade);

                    int totalDislikeInt = Integer.parseInt(mTotal_dislikes);

                    mDislikeNumberTv.setText(String.valueOf(totalDislikeInt+1));

                    //mDisliked="1";
                }

                new HitLikeOrUnlike().execute(hitLikeUrl+ thisCourseId);
            }
        });


        mHitFlagBtn=findViewById(R.id.hitFlagbtn);
        mHitFlagBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                showPopUpMessageBox();

            }
        });


        /*mDownloadBtn=findViewById(R.id.downloadBtn);
        mDownloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                downloadFile();


                //new DownloadFileAsync().execute();
            }
        });*/

        /*try {
            TIME_OUT = 1000 * (Integer.parseInt(contentDuration) - 5);
        }
        catch (Exception ex) {
            Log.d("", "onCreate: ");
        }*/


        String abcd="";

        try {
            TIME_OUT = 1000 * (Integer.parseInt(contentDuration));
        }
        catch (Exception ex){
            Log.d("", "onCreate: ");
        }

        String stepOver="";

        if(contentIconType.equalsIgnoreCase("video")) {
            if (chooseVideoType.equalsIgnoreCase("0")) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        showPopUpQuestionBox();
                    }
                }, TIME_OUT);
            }
        }

        String HHtt= String.format("%02d", Integer.parseInt(contentDuration) /3600)+":";
        String MMtt = String.format("%02d",Integer.parseInt(contentDuration)/60%60)+":";
        String SStt = String.format("%02d",Integer.parseInt(contentDuration)%60);

        textViewTt.setText(HHtt+MMtt+SStt);

    }

    private Runnable onEverySecond=new Runnable() {

        @Override
        public void run() {

            if(chooseVideoType.equalsIgnoreCase("1")) {
                if (mProgressBar != null) {
                    time = (videoView.getCurrentPosition() * 100) / duration;
                    seconds = videoView.getCurrentPosition() / 1000;
                    mProgressBar.setProgress(videoView.getCurrentPosition());
                }

                if (videoView.isPlaying()) {
                    mProgressBar.postDelayed(onEverySecond, 1000);
                }
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

    private void showPopUpQuestionBox() {
        final Dialog dialog = new Dialog(context, R.style.DialogCustomTheme);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //TODO
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(true);
        //TODO
        dialog.setContentView(R.layout.popupwindowforimage);

        // custom dialog

        //TODO
        mPulseQuesText = dialog.findViewById(R.id.pulseQuesId);

        recyclerView = dialog.findViewById(R.id.quiz_options_recycler_view);
        Button submitBtn = dialog.findViewById(R.id.pulseQuesSubmitBtnId);
        final TextView incorrectAnsTV = dialog.findViewById(R.id.incorrectAnswerId);

        mPulseQuesText.setText(GlobalVar.gPulseTitle);

        String exing1212="";

        final Button mPulseQuizCrossBtn = dialog.findViewById(R.id.MainAdCrossBtn);
        mPulseQuizCrossBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                mPulseQuizCrossBtn.setVisibility(View.GONE);
            }
        });

        /*vpPager = dialog.findViewById(R.id.QuizSliderviewPagerId);

        myAdapter= new TabsPagerAdapterPulseQuiz(getSupportFragmentManager());
        vpPager.setAdapter(myAdapter);*/

        //dialog.show();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int asqwweer=GlobalVar.gPulseMultiMarkCount;

                if (GlobalVar.gPulseMultiMarkCount>0) {

                    incorrectAnsTV.setVisibility(View.INVISIBLE);

                    dialog.dismiss();

                    videoView.seekTo(pausePosition);
                    videoView.start();
                    mProgressBar.setProgress(pausePosition);
                    mProgressBar.postDelayed(onEverySecond, 1000);
                    imgPauseView.setImageResource(R.drawable.mukto_video_pause_icon);
                    isVideoStarted=false;


                    if (chooseVideoType.equalsIgnoreCase("0")){

                        if(contentIconType.equalsIgnoreCase("video")) {
                            if (lessonCompleteness.equalsIgnoreCase("100")) {
                                String testingabcd = "";
                            } else {
                                showPopUpCompletenessSubmit();
                            }
                        }
                    }

                }
                else{
                    incorrectAnsTV.setVisibility(View.VISIBLE);

                    pausePosition = videoView.getCurrentPosition();
                    videoView.pause();
                    imgPauseView.setImageResource(R.drawable.mukto_video_play_icon);
                    isVideoStarted=true;
                }
            }
        });

        if(GlobalVar.gPulseMultiMarkCount==0) {
            setRecyclerViewAnswer();
            dialog.show();
        }


        /*WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        lp.gravity = Gravity.CENTER;

        Window window = dialog.getWindow();
        window.setAttributes(lp);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));*/



    }

    private void setRecyclerViewAnswer() {

        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setNestedScrollingEnabled(false);

        new GetPulseQuizOptions().execute();
    }

    public class GetPulseQuizOptions extends AsyncTask<String, Void, Void> {
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

            adapter=new RecyclerViewAdapterPulseQuizOptions(GlobalVar.gPulseAnswerArray,context);

            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            //mProgressSpinner.setVisibility(View.GONE);
        }
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_web_search, menu);

        return super.onCreateOptionsMenu(menu);
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {

            //TODO
            GlobalVar.isRedirectFromContentPage=true;

            Intent i=new Intent(context,MyPageCourseDetail.class);
            startActivity(i);

            return true;

            /*finish();
            return true;*/

        }
        return super.onOptionsItemSelected(item);
    }

    /*public void downloadFile() {
        String DownloadUrl = videoUrl;
        DownloadManager.Request request1 = new DownloadManager.Request(Uri.parse(DownloadUrl));
        request1.setDescription("Downloading...");
        request1.setTitle("Muktopaath Video");
        request1.setVisibleInDownloadsUi(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            request1.allowScanningByMediaScanner();
            request1.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        }
        //mFilePath=getApplicationContext()+"/muktopaath";
        request1.setDestinationInExternalFilesDir(getApplicationContext(), "/muktopaath", eCode);

        try {
            DownloadManager manager1 = (DownloadManager) getSystemService(this.DOWNLOAD_SERVICE);
            Objects.requireNonNull(manager1).enqueue(request1);
        }
        catch (Exception ex){
            Log.d("",ex.getMessage());
        }
        if (DownloadManager.STATUS_SUCCESSFUL == 8) {
            //DownloadSuccess();
        }
    }*/

    public void downloadFile() {
        String DownloadUrl = videoUrl;
        DownloadManager.Request request1 = new DownloadManager.Request(Uri.parse(DownloadUrl));
        request1.setDescription("Downloading...");   //appears the same in Notification bar while downloading
        request1.setTitle(title);
        request1.setVisibleInDownloadsUi(false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            request1.allowScanningByMediaScanner();
            request1.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        }
        request1.setDestinationInExternalFilesDir(getApplicationContext(), "/muktopaath", title+".mp4");

        DownloadManager manager1 = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        Objects.requireNonNull(manager1).enqueue(request1);
        if (DownloadManager.STATUS_SUCCESSFUL == 8) {
            //DownloadSuccess();
        }
    }


    public class DownloadFileAsync extends AsyncTask<Void, Void, Void>
    {
        String strURL=videoUrl;
        File outFile=getFilesDir();
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            downloadFile();

            return null;
        }

        @Override
        protected void onPostExecute(Void result)
        {
            super.onPostExecute(result);


        }
    }

    public class GetLykUnlykHistory extends AsyncTask<String, Void, String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... arg0) {
            String response = null;

            try {
                HttpURLConnection c = (HttpURLConnection) new URL(arg0[0]).openConnection();
                c.setRequestMethod("GET");
                c.setUseCaches(false);
                c.setRequestProperty ("Authorization", "Bearer "+GlobalVar.gReplacingToken);
                c.connect();

                InputStream in = new BufferedInputStream(c.getInputStream());
                response = convertStreamToString(in);
                c.disconnect();
            }
            catch (Exception ex){
                Log.d("",ex.getMessage());
            }
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {

                JSONObject jObject = new JSONObject(result);

                mTotal_dislikes = jObject.getString("total_dislikes");
                mTotal_flags = jObject.getString("total_flags");
                mTotal_likes = jObject.getString("total_likes");

                mDisliked = jObject.getString("disliked");
                mFlagged = jObject.getString("flagged");
                mLiked = jObject.getString("liked");
            }
            catch (Exception ex){
                Log.d("", "onPostExecute: ");
            }


            String adasasas="";

            // this section is for this user's like, dislike, values

            if(mLiked.equalsIgnoreCase("1")){
                mHitLikeBtn.setImageResource(R.drawable.mukto_like_icon_tap);
            }
            else {
                mHitLikeBtn.setImageResource(R.drawable.mukto_like_icon_fade);
            }

            if(mDisliked.equalsIgnoreCase("1")){
                mHitDislikeBtn.setImageResource(R.drawable.mukto_dislike_icon_tap);
            }
            else{
                mHitDislikeBtn.setImageResource(R.drawable.mukto_dislike_icon_fade);
            }

            if(mFlagged.equalsIgnoreCase("1")){
                mHitFlagBtn.setImageResource(R.drawable.mukto_flag_icon_tap);
            }
            else{
                mHitFlagBtn.setImageResource(R.drawable.mukto_flag_btn_fade);
            }


            // This section is for ignoring null values
            String abcd="";

            if(mTotal_likes==null){
                mTotal_likes="0";
            }
            if(mTotal_dislikes==null){
                mTotal_dislikes="0";
            }
            if(mTotal_flags==null){
                mTotal_flags="0";
            }


            if(mTotal_likes.equalsIgnoreCase("null")){
                mTotal_likes="0";
            }
            if(mTotal_dislikes.equalsIgnoreCase("null")){
                mTotal_dislikes="0";
            }
            if(mTotal_flags.equalsIgnoreCase("null")){
                mTotal_flags="0";
            }

            mLikeNumberTv.setText(mTotal_likes);
            mDislikeNumberTv.setText(mTotal_dislikes);
            mFlagNumberTv.setText(mTotal_flags);
        }
    }


    public class HitLikeOrUnlike extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            String data = performPostCallWithBearer(params[0], mapHit);

            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {
                JSONArray jsonArray = new JSONArray(result);
            }
            catch (Exception ex){
                Log.d("", "onPostExecute: ");
            }
        }

        @Override
        protected void onCancelled() {

        }
    }

    public class HitGamificationOnCompleteLesson extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            String data = performPostCallWithBearer(params[0], mapHitGamification);

            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            /*try {
                JSONArray jsonArray = new JSONArray(result);
            }
            catch (Exception ex){
                Log.d("", "onPostExecute: ");
            }*/
        }

        @Override
        protected void onCancelled() {

        }
    }


    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }


    private void showPopUpMessageBox()
    {
        // custom dialog
        final Dialog dialog = new Dialog(context, R.style.DialogCustomTheme);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popupwindowforflagtextlist);

        ArrayList<String> flagList = new ArrayList<>();

        flagList.add("Problem in Content");
        flagList.add("Sexual content");
        flagList.add("Violent or repulsive content");
        flagList.add("Hateful or abusive content");
        flagList.add("Harmful dangerous acts");
        flagList.add("Child abuse");
        flagList.add("Promotes terrorism");
        flagList.add("Spam or misleading");
        flagList.add("Infringes my rights");
        flagList.add("Captions issue");

        recyclerView = dialog.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setNestedScrollingEnabled(false);

        adapter=new RecyclerViewAdapterFlagList(flagList,context);

        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();




        Button mSendReport=dialog.findViewById(R.id.sendReport);
        mSendReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mFlagged.equalsIgnoreCase("1")){



                    GlobalVar.gFlagReportStr="";

                    mapHit =  new HashMap<>();

                    mapHit.put("action_type", "flag");
                    mapHit.put("liked", mLiked);
                    mapHit.put("flagged", "0");
                    mapHit.put("disliked", mDisliked);
                    mapHit.put("unit_id", GlobalVar.gUnitId);
                    mapHit.put("lesson_id", GlobalVar.gLessonId);
                    mapHit.put("flag_report", GlobalVar.gFlagReportStr);

                    mHitFlagBtn.setImageResource(R.drawable.mukto_flag_icon);

                }
                else {
                    mapHit =  new HashMap<>();

                    mapHit.put("action_type", "flag");
                    mapHit.put("liked", mLiked);
                    mapHit.put("flagged", "1");
                    mapHit.put("disliked", mDisliked);
                    mapHit.put("unit_id", GlobalVar.gUnitId);
                    mapHit.put("lesson_id", GlobalVar.gLessonId);
                    mapHit.put("flag_report", GlobalVar.gFlagReportStr);

                    mHitFlagBtn.setImageResource(R.drawable.mukto_flag_purple_icon);
                }


                new HitLikeOrUnlike().execute(hitLikeUrl+ thisCourseId);



                Toast.makeText(context,"Your flag report has been sent.",Toast.LENGTH_SHORT).show();

                dialog.dismiss();
            }
        });



        dialog.show();
    }



    public class CompletenessSubmit extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            String data = performPostCallWithBearer(params[0], mapSubmit);

            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {
                JSONObject jObject = new JSONObject(result);

                //Toast.makeText(context,"successfully submitted.",Toast.LENGTH_LONG).show();
            }
            catch (Exception ex){
                Log.d("", "onPostExecute: ");
            }


            mapHitGamification =  new HashMap<>();

            mapHitGamification.put("unit_id", GlobalVar.gUnitId);
            mapHitGamification.put("lesson_id", GlobalVar.gLessonId);
            mapHitGamification.put("course_batch_id", thisBatchId);
            mapHitGamification.put("slug", "vlc");

            new HitGamificationOnCompleteLesson().execute(hitGamificationForLessonComplete);
        }
        @Override
        protected void onCancelled() {

        }
    }




    public String  performPostCallWithBearer(String requestURL, HashMap<String, String> postDataParams) {

        URL url;
        String response = "";

        try {
            url = new URL(requestURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setRequestProperty ("Authorization", "Bearer "+GlobalVar.gReplacingToken);
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


    private void showPopUpCompletenessSubmit()
    {
        final Dialog dialog = new Dialog(context, R.style.DialogCustomTheme);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popupwindowforcompletenesssubmit);

        Button okButton = dialog.findViewById(R.id.submitBtn);
        okButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                final String enrollId=GlobalVar.gEnrollCourseId.get(GlobalVar.gNthCourse).getmEcId();

                String enrollCourseCompltness=GlobalVar.gEnrollCourseId.get(GlobalVar.gNthCourse).getmEcCompleteness();

                // Let's count the total progress of a course
                ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> units= GlobalVar.courseContentDetailList.get(0).getmUnitAllArrayList().get(GlobalVar.gNthCourse);


                for(int incrUnits=0; incrUnits<units.size(); incrUnits++) {

                    ArrayList <DetailDataModelCoursesDetailContents> lessons = units.get(incrUnits);

                    CountAUnitsLessons = CountAUnitsLessons + lessons.size();
                }

                int eachLessonProgress = 100/CountAUnitsLessons;

                double enrollCourseCompltnessDouble = Double.parseDouble(enrollCourseCompltness);

                double progressedEnrollCourseCompleteDouble = enrollCourseCompltnessDouble + eachLessonProgress;

                enrollCourseCompltness = String.valueOf(progressedEnrollCourseCompleteDouble);


                enrollCourseCompltness = String.format("%.0f", progressedEnrollCourseCompleteDouble);

                if(Integer.parseInt(enrollCourseCompltness)>100) {
                    enrollCourseCompltness="100";
                }

                //String eachLessonProgressStr = String.valueOf(eachLessonProgress);

                mapSubmit =  new HashMap<>();

                mapSubmit.put("unit_id", unitId);
                mapSubmit.put("lesson_id", GlobalVar.gLessonId);
                mapSubmit.put("completeness", "100");
                mapSubmit.put("start", String.valueOf(seconds));
                mapSubmit.put("course_completeness", enrollCourseCompltness);

                //GlobalVar.gLessonCompleteTempUnitId = unitId;
                //GlobalVar.gLessonCompleteTempLessonId = GlobalVar.gLessonId;

                GlobalVar.gLessonCompleteTempLessonArrayId.add(GlobalVar.gLessonId);
                GlobalVar.gLessonCompleteTempUnitArrayId.add(unitId);

                new CompletenessSubmit().execute(urlCompletenessSubmit+enrollId);

                dialog.dismiss();
            }
        });

        dialog.show();
    }

    @Override
    public void onPause(){
        super.onPause();

        mToutubeWebView.onPause();
    }

}
