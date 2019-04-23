package orangebd.newaspaper.mymuktopathapp;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SlidingQuizReportActivity extends AppCompatActivity {

    TabsPagerAdapterReportQuiz myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_quiz_report);

        final ViewPager vpPager = findViewById(R.id.QuizSliderviewPagerId);

        myAdapter= new TabsPagerAdapterReportQuiz(getSupportFragmentManager());
        vpPager.setAdapter(myAdapter);


        String passMark = getIntent().getExtras().getString("pm");
        String totalGainedMark =getIntent().getExtras().getString("tgm");

        GlobalVar.gPassMark=passMark;
        GlobalVar.gTotalGainedMark=totalGainedMark;

        String stepOver="";
    }
}
