package orangebd.newaspaper.mymuktopathapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Formatter;

public class StartQuizActivity extends AppCompatActivity {

    private Context context;

    private TextView totalQ;
    private TextView totalTime;
    private TextView totalMarks;

    private Button startMyQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_quiz);

        context=this;


        totalQ=findViewById(R.id.totalQid);
        totalTime=findViewById(R.id.timeDurationId);
        totalMarks=findViewById(R.id.totalMarksId);

        setQuiz();
    }

    private void setQuiz() {

        startMyQuiz=findViewById(R.id.startMyQuizId);

        startMyQuiz.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i=new Intent(context,SlidingQuizActivity.class);

                startActivity(i);
            }
        });

        String lessonId = GlobalVar.courseContentDetailList.get(0).getmArrayListCourseLesson().get(GlobalVar.gNthCourse).get(0).getIdLesson();
        GlobalVar.gLessonId=lessonId;

        totalQ=findViewById(R.id.totalQid);
        totalTime=findViewById(R.id.timeDurationId);
        totalMarks=findViewById(R.id.totalMarksId);

        final ArrayList<DetailDataModelCoursesDetailContents> mQuizInfo = GlobalVar.courseContentDetailList.get(0).getmUnitDataArrayListContent4().get(GlobalVar.gNthCourse);

        if(mQuizInfo.size()>0) {
            String quizMarks = mQuizInfo.get(0).getmQuizMarks();
            String quizTime = mQuizInfo.get(0).getmQuizTime();

            totalQ.setText(convertEngToBn(Integer.toString(GlobalVar.gTotalQuizNumberThisCourse-1)));
            totalTime.setText(convertEngToBn(stringForTime(Integer.parseInt(quizTime))));
            totalMarks.setText(convertEngToBn(quizMarks));
        }

    }

    public String convertEngToBn(String num) {

        num = num.replace("0","০");
        num = num.replace("1","১");
        num = num.replace("2","২");
        num = num.replace("3","৩");
        num = num.replace("4","৪");
        num = num.replace("5","৫");
        num = num.replace("6","৬");
        num = num.replace("7","৭");
        num = num.replace("8","৮");
        num = num.replace("9","৯");

        return num;
    }

    private String stringForTime(int timeMs) {

        Formatter mFormatter= new Formatter();

        //int seconds = timeMs % 60;
        int minutes = (timeMs / 60) % 60;
        int hours = timeMs / 3600;

        String mintBn = convertEngToBn(Integer.toString(minutes));
        String hrsBn = convertEngToBn(Integer.toString(hours));

        if (hours > 0) {
            return mFormatter.format("%s ঘণ্টা %s মিনিট", hrsBn, mintBn).toString();
        }
        else {
            return mFormatter.format("%s মিনিট", mintBn).toString();
        }
    }

}
