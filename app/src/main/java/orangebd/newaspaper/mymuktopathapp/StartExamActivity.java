package orangebd.newaspaper.mymuktopathapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Formatter;

public class StartExamActivity extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_exam);

        context=this;

        setExam();
    }


    private void setExam(){

        String lessonId = GlobalVar.courseContentDetailList.get(0).getmArrayListCourseLesson().get(GlobalVar.gNthCourse).get(0).getIdLesson();
        GlobalVar.gLessonId=lessonId;

        Button startMyExam=findViewById(R.id.startMyExamId);
        TextView totalEQ=findViewById(R.id.totalEQid);
        TextView totalETime=findViewById(R.id.timeEDurationId);
        TextView totalEMarks=findViewById(R.id.totalEMarksId);
        LinearLayout examSummeryLinLayout=findViewById(R.id.examSummeryLayoutId);
        TextView examPassMarksTextView=findViewById(R.id.passMarkId);

        final ArrayList<DetailDataModelCoursesMarks> mExamPassInfo = GlobalVar.courseContentDetailList.get(0).getmArrayListMarks();
        final ArrayList<DetailDataModelCoursesDetailContents> mExamQs = GlobalVar.courseContentDetailList.get(0).getmArrayListCourseQuizsExam().get(GlobalVar.gNthCourse);
        final ArrayList<DetailDataModelCoursesDetailContents> mExamInfo = GlobalVar.courseContentDetailList.get(0).getmUnitDataArrayListContent2().get(GlobalVar.gNthCourse);

        GlobalVar.gTotalExamNumberThisCourse=mExamQs.size();

        String examMarks = mExamInfo.get(0).getmExamMarks();
        String examTime = mExamInfo.get(0).getmExamTime();

        examSummeryLinLayout.setVisibility(View.VISIBLE);

        totalEQ.setText(convertEngToBn(Integer.toString(mExamQs.size())));
        totalETime.setText(convertEngToBn(stringForTime(Integer.parseInt(examTime))));
        totalEMarks.setText(convertEngToBn(examMarks));

        examMarks = mExamInfo.get(0).getmExamMarks();
        String passPercentageMarksExam = mExamPassInfo.get(0).getExam_pass_mark();
        double passPercentageMarksInt = Double.parseDouble(passPercentageMarksExam);
        double passExameMarksInt = Double.parseDouble(examMarks);
        double passMarksInt = passExameMarksInt/passPercentageMarksInt;
        String examPassMarks = Double.toString(passMarksInt);
        examPassMarksTextView.setText(convertEngToBn(examPassMarks));


        startMyExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context,SlidingExamActivity.class);
                startActivity(i);
            }
        });
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
