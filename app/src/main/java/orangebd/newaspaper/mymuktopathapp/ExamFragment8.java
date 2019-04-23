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
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;


public class ExamFragment8 extends Fragment {

    private TextView mExamTitle;

    private View view;
    private Context context;

    private int nthExam=7;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private Button nextButton;

    ArrayList<DetailDataModelCoursesDetailContents> optionTItles = new ArrayList<>();

    private String answers;
    private int trueCount;

    private TextInputEditText examPaperField;
    private String getAttendedQArray;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_exam_fragment8, container, false);

        context=getContext();

        mExamTitle=view.findViewById(R.id.examTitle);
        examPaperField=view.findViewById(R.id.examPaperField);

        /** For question and option expandable list*/
        final ArrayList<DetailDataModelCoursesDetailContents> mExamParents = GlobalVar.courseContentDetailList.get(0).getmArrayListCourseQuizs().get(GlobalVar.gNthCourse);


        String examTitle = mExamParents.get(nthExam).getmQuizTitle();
        mExamTitle.setText(examTitle);

        nextButton =view.findViewById(R.id.nextButtonId);

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
            }
        });

        return view;
    }
}
