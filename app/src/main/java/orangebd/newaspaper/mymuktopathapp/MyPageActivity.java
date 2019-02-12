package orangebd.newaspaper.mymuktopathapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.provider.SyncStateContract;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyPageActivity extends AppCompatActivity {

    TabsPagerAdapterDetail myAdapter;
    SlidingTabLayout mSlidingTabLayout;

    private Context context;
    ImageView imageLogo;

    String url;
    String ShareURL;
    String title;
    String strRelatedUrl;

    private int newsIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        context=this;

        getSupportActionBar().hide();

        View view = LayoutInflater.from(context)
                .inflate(R.layout.custom_logodetails, null, false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(view);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffffff")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageLogo=view.findViewById(R.id.rtvheadlogo);

        final ViewPager vpPager = findViewById(R.id.VideoSliderviewPagerId);

        myAdapter= new TabsPagerAdapterDetail(getSupportFragmentManager());
        vpPager.setAdapter(myAdapter);

        mSlidingTabLayout = findViewById(R.id.sliding_tabs_fordetailvideo);
        mSlidingTabLayout.setDistributeEvenly(true);
    }

    @Override
    public void onBackPressed() {
        finish();
        return;
    }

    @Override
    protected boolean onPrepareOptionsPanel(View view, Menu menu) {
        if(menu != null){
            if(menu.getClass().getSimpleName().equals("MenuBuilder")){
                try{
                    Method m = menu.getClass().getDeclaredMethod(
                            "setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                }
                catch(NoSuchMethodException e){
                    Log.e(SyncStateContract.Constants.DATA, "onMenuOpened", e);
                }
                catch(Exception e){
                    throw new RuntimeException(e);
                }
            }
        }
        return true;
        //return super.onPrepareOptionsPanel(view, menu);
        //return super.onPrepareOptionsPanel(view, menu);
    }

    private void shareIt() {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Bangladesh Journal");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, ShareURL);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        }
        else if (id==R.id.nav_share)
            shareIt();
        return super.onOptionsItemSelected(item);
    }
}
