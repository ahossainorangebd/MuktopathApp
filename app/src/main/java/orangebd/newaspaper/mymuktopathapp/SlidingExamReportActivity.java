package orangebd.newaspaper.mymuktopathapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

public class SlidingExamReportActivity extends AppCompatActivity {

    TabsPagerAdapterReportExam myAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_exam_report);



        final View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.custom_logodetails, null, false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(view);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#7a19aa")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        final ViewPager vpPager = findViewById(R.id.ExamSliderviewPagerId);

        myAdapter= new TabsPagerAdapterReportExam(getSupportFragmentManager());
        vpPager.setAdapter(myAdapter);


        String passMark = getIntent().getExtras().getString("epm");
        String totalGainedMark =getIntent().getExtras().getString("etgm");

        GlobalVar.gEPassMark=passMark;
        GlobalVar.gETotalGainedMark=totalGainedMark;

        String stepOver="";
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {

            Intent i=new Intent(getApplicationContext(),MyPageActivity.class);
            startActivity(i);

            return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
