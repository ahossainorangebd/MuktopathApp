package orangebd.newaspaper.mymuktopathapp;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class QuizFragment10 extends Fragment {


    private TextView mQuizTitle;

    private View view;
    private Context context;

    private int nthQuiz=9;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    ArrayList<DetailDataModelCoursesDetailContents> optionTItles = new ArrayList<>();

    private String answers;
    private int trueCount;

    private int prevExam=8;
    private int nextExam=10;
    private Button nextButton;
    private Button prevButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_quiz_fragment10, container, false);

        context=getContext();

        GlobalVar.isRedirectFromQuizFragment1=false;

        GlobalVar.gNthQuiz=nthQuiz;

        mQuizTitle=view.findViewById(R.id.quizTitle);

        /** For question and option expandable list*/
        final ArrayList<DetailDataModelCoursesDetailContents> mQuizParents = GlobalVar.courseContentDetailList.get(0).getmArrayListCourseQuizs().get(GlobalVar.gNthCourse);
        final ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> mQuizOptionChilds = GlobalVar.courseContentDetailList.get(0).getmArrayListCourseQuizOptions().get(GlobalVar.gNthCourse);

        String quizTitle = mQuizParents.get(nthQuiz).getmQuizTitle();
        optionTItles = mQuizOptionChilds.get(nthQuiz);

        mQuizTitle.setText(quizTitle);

        setRecyclerView();

        nextButton =view.findViewById(R.id.nextButtonId);
        prevButton =view.findViewById(R.id.prevButtonId);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ArrayList<DetailDataModelCoursesDetailContents> mQListForId = GlobalVar.courseContentDetailList.get(0).getmArrayListCourseQuizs().get(GlobalVar.gNthCourse);

                String getAttendedQArray=mQListForId.get(nthQuiz).getmQuizId();

                GlobalVar.attendedQArrayQuiz.add(getAttendedQArray);

                GlobalVar.gQuizViewPager.setCurrentItem(nextExam);
            }
        });

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalVar.gQuizViewPager.setCurrentItem(prevExam);
            }
        });

        return view;
    }


    private void setRecyclerView() {

        recyclerView = view.findViewById(R.id.quiz_options_recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setNestedScrollingEnabled(false);

        new GetThisFragmentQuizOptions().execute();
    }

    public class GetThisFragmentQuizOptions extends AsyncTask<String, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //mProgressSpinner.setIndeterminate(true);
            //mProgressSpinner.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(String... arg0) {

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            trueCount=0;

            for(int checktrue=0; checktrue<optionTItles.size(); checktrue++) {

                answers = optionTItles.get(checktrue).getmOptionAnswer();

                if (answers.equalsIgnoreCase("true")){
                    trueCount++;
                }
            }
            if(trueCount>1){
                adapter=new RecyclerViewAdapterQuizOptions(nthQuiz, optionTItles,context);
            }
            else {
                adapter=new RecyclerViewAdapterQuizRadioOptions(nthQuiz, optionTItles,context);
            }

            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            //mProgressSpinner.setVisibility(View.GONE);
        }
    }
}
