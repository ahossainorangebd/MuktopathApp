package orangebd.newaspaper.mymuktopathapp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
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

    private String getPulse;

    String DetailDescription;
    String title;

    private String eCode;
    private String timeStatus;
    private String mListPosition;
    private String mUserNumber;

    private TextView mPulseQuesText;
    private WebView detailDescTextView;

    private boolean isGotQuestion;
    private boolean isNoQuestion;

    private TextView titleTextView;

    private ViewPager vpPager;

    private WebView mVideoView;

    private String videoUrl;
    private String finalVideoUrl;

    //private String BASE_URL = "http://muktopaath.orangebd.com";
    private String BASE_URL = "http://testadmin.muktopaath.orangebd.com";

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

    private TextView mCustomContentTitle;


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    TabsPagerAdapterPulseQuiz myAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_content_detail);

        context=this;

        final View view = LayoutInflater.from(context).inflate(R.layout.custom_logodetails_course_content_title, null, false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(view);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#7a19aa")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //titleTextView=findViewById(R.id.courseTitle);
        detailDescTextView=findViewById(R.id.detailDesc);

        if(GlobalVar.isRedirectFromContentPage){

            DetailDescription=GlobalVar.gDescriptionText;
            title = GlobalVar.gCourseDetailTitle;
            eCode = GlobalVar.gVideoCode;
            mUserNumber = GlobalVar.gUserNumber;
            timeStatus = GlobalVar.gTimeStatus;
        }
        else {
            DetailDescription=getIntent().getExtras().getString("detail");
            title = getIntent().getExtras().getString("ttl");
            eCode = getIntent().getExtras().getString("vcode");
            mUserNumber = getIntent().getExtras().getString("usernumber");
            timeStatus = getIntent().getExtras().getString("videostatus");
        }




        GlobalVar.gLastReadLessonTitle=title;

        int listPositionNumber = Integer.parseInt(GlobalVar.gListPosition);

        mCustomContentTitle=view.findViewById(R.id.muktoCustomContentTitle);
        mCustomContentTitle.setText(title);


        final ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> pulseMultiArray  = GlobalVar.thisFragmentPulses;
        final ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> pulseQuesListWithAns = GlobalVar.thisFragmentPulseQs;

        try {
            String pulseQuizTitle = pulseQuesListWithAns.get(Integer.parseInt(GlobalVar.gListPosition)).get(0).getMpQuizTitle();
            GlobalVar.gPulseTitle=pulseQuizTitle;

            ArrayList<DetailDataModelCoursesDetailContents> optionArray = pulseQuesListWithAns.get(Integer.parseInt(GlobalVar.gListPosition));
            GlobalVar.gPulseAnswerArray=optionArray;
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




        for(int allPulse=0; allPulse<GlobalVar.thisFragmentPulses.size(); allPulse++) {

            int eachPulse = GlobalVar.thisFragmentPulses.get(allPulse).get(0).getmPulseOfVideoMultiId();

            if(eachPulse==listPositionNumber) {
                getPulse = GlobalVar.thisFragmentPulses.get(allPulse).get(0).getmPulseOfVideoMulti();
                Toast.makeText(context,"This video has question.",Toast.LENGTH_SHORT).show();

                isGotQuestion=true;
            }
            else {

                if(isNoQuestion==false){

                    if(isGotQuestion==true){
                        String nothing="Nothing";
                    }
                    else {
                        Toast.makeText(context, "This video has no question.", Toast.LENGTH_SHORT).show();
                        isNoQuestion=true;
                    }
                }
                else {
                    String mNothing="nothing";
                }
            }
        }

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
                    imgPauseView.setImageResource(R.drawable.mukto_video_play_icon);
                    isVideoStarted=true;
                }

                else{
                    videoView.seekTo(pausePosition);
                    videoView.start();
                    mProgressBar.setProgress(pausePosition);
                    mProgressBar.postDelayed(onEverySecond, 1000);
                    imgPauseView.setImageResource(R.drawable.mukto_video_pause_icon);
                    isVideoStarted=false;
                }
            }
        });


        //titleTextView.setText(title);

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

                int targetPopUp=0;

                try {
                    targetPopUp = Integer.parseInt(getPulse);
                }
                catch (Exception ex){
                    Log.d("", "onProgressChanged: ");
                }
                //int targetPopUp=5;

                if(seconds==targetPopUp)
                {
                    //Toast.makeText(context,"A multiple question will pop up on "+firstPulse+"th Second",Toast.LENGTH_SHORT).show();

                    String wwww="";

                    isVideoStarted=false;

                    if (!isVideoStarted) {

                        pausePosition = videoView.getCurrentPosition();
                        videoView.pause();
                        imgPauseView.setImageResource(R.drawable.mukto_video_play_icon);
                        isVideoStarted=true;


                        if(GlobalVar.gPulseMultiMarkCount==0) {
                            showPopUpQuestionBox();
                        }

                    }

                    else{
                        videoView.seekTo(pausePosition);
                        videoView.start();
                        mProgressBar.setProgress(pausePosition);
                        mProgressBar.postDelayed(onEverySecond, 1000);
                        imgPauseView.setImageResource(R.drawable.mukto_video_pause_icon);
                        isVideoStarted=false;
                    }

                }

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

            GlobalVar.isRedirectFromContentPage=true;

            Intent i=new Intent(context,MyPageCourseDetail.class);
            startActivity(i);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
