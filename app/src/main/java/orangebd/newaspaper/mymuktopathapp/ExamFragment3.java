package orangebd.newaspaper.mymuktopathapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class ExamFragment3 extends Fragment {

    private int nextExam=3;
    private int prevExam=1;

    private TextView mExamTitle;

    private View view;
    private Context context;

    private int nthExam=2;


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private Button nextButton;
    private Button prevButton;

    ArrayList<DetailDataModelCoursesDetailContents> optionTItles = new ArrayList<>();

    private String answers;
    private int trueCount;

    private TextInputEditText examPaperField;
    private String getAttendedQArray;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_exam_fragment3, container, false);

        context=getContext();

        mExamTitle=view.findViewById(R.id.examTitle);
        examPaperField=view.findViewById(R.id.examPaperField);

        /** For question and option expandable list*/
        final ArrayList<DetailDataModelCoursesDetailContents> mExamParents = GlobalVar.courseContentDetailList.get(0).getmArrayListCourseQuizsExam().get(GlobalVar.gNthCourse);


        String examTitle = mExamParents.get(nthExam).getmQuizTitle();
        mExamTitle.setText(examTitle);

        nextButton =view.findViewById(R.id.nextButtonId);
        prevButton =view.findViewById(R.id.prevButtonId);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ArrayList<DetailDataModelCoursesDetailContents> mQListForId = GlobalVar.courseContentDetailList.get(0).getmArrayListCourseQuizsExam().get(GlobalVar.gNthCourse);

                String examFieldString=examPaperField.getText().toString();

                getAttendedQArray=mQListForId.get(nthExam).getmQuizId();

                if(examFieldString!=""){
                    GlobalVar.attendedQArray.add(getAttendedQArray);

                    GlobalVar.answerArray.add(examFieldString);
                }
                else {

                }

                GlobalVar.gExamViewPager.setCurrentItem(nextExam);
            }
        });

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalVar.gExamViewPager.setCurrentItem(prevExam);
            }
        });

        return view;
    }

}
