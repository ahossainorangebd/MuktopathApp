package orangebd.newaspaper.mymuktopathapp;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class SlidingExamActivity extends AppCompatActivity {

    TabsPagerAdapterExam myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_exam);


        final ViewPager vpPager = findViewById(R.id.ExamSliderviewPagerId);

        myAdapter= new TabsPagerAdapterExam(getSupportFragmentManager());
        vpPager.setAdapter(myAdapter);

        GlobalVar.answerArray= new ArrayList<>();
        GlobalVar.attendedQArray= new ArrayList<>();
    }
}
