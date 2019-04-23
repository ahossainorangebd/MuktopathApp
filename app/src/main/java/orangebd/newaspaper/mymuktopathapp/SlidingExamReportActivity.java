package orangebd.newaspaper.mymuktopathapp;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SlidingExamReportActivity extends AppCompatActivity {

    TabsPagerAdapterReportExam myAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_exam_report);

        final ViewPager vpPager = findViewById(R.id.ExamSliderviewPagerId);

        myAdapter= new TabsPagerAdapterReportExam(getSupportFragmentManager());
        vpPager.setAdapter(myAdapter);


        String passMark = getIntent().getExtras().getString("epm");
        String totalGainedMark =getIntent().getExtras().getString("etgm");

        GlobalVar.gEPassMark=passMark;
        GlobalVar.gETotalGainedMark=totalGainedMark;

        String stepOver="";
    }
}
