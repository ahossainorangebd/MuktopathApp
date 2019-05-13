package orangebd.newaspaper.mymuktopathapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.provider.Settings;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class SlidingQuizActivity extends AppCompatActivity {

    TabsPagerAdapterQuiz myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_quiz);



        final View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.custom_logodetails, null, false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(view);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#7a19aa")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        final ViewPager vpPager = findViewById(R.id.QuizSliderviewPagerId);

        myAdapter= new TabsPagerAdapterQuiz(getSupportFragmentManager());
        vpPager.setAdapter(myAdapter);


        GlobalVar.gQuizViewPager=vpPager;

        //TODO

        GlobalVar.answerArrayQuiz= new ArrayList<>();
        GlobalVar.attendedQArrayQuiz= new ArrayList<>();

        /*int presentFragmentItem=vpPager.getCurrentItem();
        vpPager.setCurrentItem(presentFragmentItem + 1);*/

        /*Button nextBtn=findViewById(R.id.nextBtnId);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ArrayList<DetailDataModelCoursesDetailContents> mQListForId = GlobalVar.courseContentDetailList.get(0).getmArrayListCourseQuizs().get(GlobalVar.gNthCourse);

                String getAttendedQArray=mQListForId.get(GlobalVar.quiz).getmQuizId();

                GlobalVar.attendedQArrayQuiz.add(getAttendedQArray);

            }
        });*/
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
