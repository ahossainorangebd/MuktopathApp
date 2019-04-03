package orangebd.newaspaper.mymuktopathapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.rd.PageIndicatorView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

public class SplashActivity extends AppCompatActivity {

    TabsPagerAdapterForGif myAdapter;
    SlidingTabLayout mSlidingTabLayout;

    private Button splashActvtyCreateAccountBtn;
    private TextView slpashActLoginTxt;

    //private PageIndicatorView piView;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mContext=this;

        getSupportActionBar().hide();

        //piView=findViewById(R.id.pageIndicatorView);

        splashActvtyCreateAccountBtn=findViewById(R.id.splashActvtyCreateAccountId);
        splashActvtyCreateAccountBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent i=new Intent(mContext,CreateAccountActivity.class);
                v.getContext().startActivity(i);
            }
        });

        slpashActLoginTxt=findViewById(R.id.slpashActLoginId);
        slpashActLoginTxt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent i=new Intent(mContext,LoginActivity.class);
                v.getContext().startActivity(i);
            }
        });


        final ViewPager vpPager = findViewById(R.id.vpagergifid);

        myAdapter= new TabsPagerAdapterForGif(getSupportFragmentManager());
        vpPager.setAdapter(myAdapter);
        vpPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
               // piView.setSelection(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        mSlidingTabLayout = findViewById(R.id.sliding_tabs_for_gif);
        mSlidingTabLayout.setDistributeEvenly(true);
    }
}