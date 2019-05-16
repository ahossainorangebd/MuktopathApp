package orangebd.newaspaper.mymuktopathapp;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;

public class DownloadedContentActivity extends AppCompatActivity {
    private VideoView videoView;
    SeekBar mProgressBar;
    private ProgressBar mBackProgressBar;
    private int time;
    private int seconds;
    private int duration;
    private TextView textView;
    private ImageView imgPlayView;
    private ImageView imgPauseView;
    private int pausePosition;
    private boolean isVideoStarted;
    private int seekbarProgress;
    private int seekbarStartPosition;
    private boolean isStartTrackingTouch;

    private TextView contentTitle;
    private TextView contentDetail;

    private LinearLayout mVideoLayOut;

    private int forwardable=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downloaded_content);

        String vPath=this.getIntent().getExtras().getString("contentpath");
        String cTitle=this.getIntent().getExtras().getString("lessonname");
        String cDetail=this.getIntent().getExtras().getString("lessondetail");

        cDetail = cDetail.replace("<p>", "");
        cDetail = cDetail.replace("</p>", "");

        videoView = findViewById(R.id.vdVw);
        mProgressBar = findViewById(R.id.Progressbar);

        contentTitle = findViewById(R.id.contentTitle);
        contentTitle.setText(cTitle);

        contentDetail = findViewById(R.id.contentDetail);
        contentDetail.setText(cDetail);

        //mBackProgressBar=findViewById(R.id.Progressbar1);
        textView=findViewById(R.id.txtView);
        Uri uri = Uri.parse(vPath);
        imgPauseView=findViewById(R.id.pause_button);
        imgPauseView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isVideoStarted) {
                    pausePosition = videoView.getCurrentPosition();
                    videoView.pause();
                    //imgPauseView.setImageResource(R.drawable.play);
                    isVideoStarted=true;
                }
                else{
                    videoView.seekTo(pausePosition);
                    videoView.start();
                    mProgressBar.setProgress(pausePosition);
                    mProgressBar.postDelayed(onEverySecond, 1000);
                    //imgPauseView.setImageResource(R.drawable.pause);
                    isVideoStarted=false;
                }
            }
        });

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

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(getApplicationContext(),"Completed",Toast.LENGTH_LONG).show();
            }
        });
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


                if (forwardable !=1)
                {
                    if (prog < vprog) {
                        videoView.seekTo(seekbarProgress);
                        mProgressBar.setSecondaryProgress(vprog);
                    } else {
                        videoView.seekTo(vprog);
                        //mProgressBar.setSecondaryProgress(seekbarProgress);
                    }
                }
                else {
                    int seekbarProgress=seekBar.getProgress();
                    videoView.seekTo(seekbarProgress);
                }

                mProgressBar.postDelayed(onEverySecond, 1000);


            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isStartTrackingTouch=true;
                seekbarStartPosition=videoView.getCurrentPosition();
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seconds=videoView.getCurrentPosition()/1000;
                textView.setText(""+seconds);
                seekbarProgress=progress;
                //mProgressBar.setSecondaryProgress(progress);
                //pausePosition=progress;

                if(fromUser) {
                    int vprog=videoView.getCurrentPosition();
                    int prog=progress;
                    if(prog<vprog) {
                        videoView.seekTo(progress);
                        mProgressBar.setSecondaryProgress(vprog);
                    }
                    else {
                        videoView.seekTo(vprog);
                        //mProgressBar.setSecondaryProgress(progress);
                        //mBackProgressBar.setProgress(progress);
                        //videoView.pause();
                    }
                }
            }
        });




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


        mVideoLayOut=findViewById(R.id.videoLayOut);
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