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

public class ExamReportFragment8 extends Fragment {

    private TextView mSumDesc;

    private View view;

    private Context context;

    private int nthExam=7;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment_exam_report_fragment8, container, false);

        context=getContext();

        mSumDesc=view.findViewById(R.id.summeryDesc);

        final ArrayList<DetailDataModelCoursesDetailContents> mExamParents = GlobalVar.courseContentDetailList.get(0).getmArrayListCourseQuizsExam().get(GlobalVar.gNthCourse);

        String examSumDesc = mExamParents.get(nthExam).getmSummeryDesc();

        mSumDesc.setText(examSumDesc);

        return view;
    }
}
