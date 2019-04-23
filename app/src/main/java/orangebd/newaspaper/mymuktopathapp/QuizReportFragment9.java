package orangebd.newaspaper.mymuktopathapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class QuizReportFragment9 extends Fragment {

    private TextView mSumDesc;

    private View view;

    private Context context;

    private int nthQuiz=8;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment_quiz_report_fragment9, container, false);

        context=getContext();

        mSumDesc=view.findViewById(R.id.summeryDesc);

        final ArrayList<DetailDataModelCoursesDetailContents> mQuizParents = GlobalVar.courseContentDetailList.get(0).getmArrayListCourseQuizs().get(GlobalVar.gNthCourse);

        String quizSumDesc = mQuizParents.get(nthQuiz).getmSummeryDesc();

        mSumDesc.setText(quizSumDesc);

        return view;
    }
}
