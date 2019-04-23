package orangebd.newaspaper.mymuktopathapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Formatter;


public class MyPageDetailFragment2 extends Fragment {

    private Context context;

    private View view;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private TextView unitOrderText;
    private TextView unitTitleText;

    private ExpandableListView mExpandableList;

    private LinearLayout mParentLinLay;

    private ArrayList<String> mCheckCheckedString = new ArrayList<>();
    ArrayList<Parent> answerList = new ArrayList<>();

    private CheckBox optnCheckbox;

    private int markCount;

    private Button submitBtn;

    private LinearLayout quizSummeryLinLayout;

    private TextView totalQ;
    private TextView totalTime;
    private TextView totalMarks;

    private Button startMyQuiz;

    private int thisFragmentUniNumber=1;

    private TextView mLastReadLesson;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_my_page_detail_fragment2, container, false);

        context=getContext();

        mLastReadLesson=view.findViewById(R.id.lastReadLessonId);
        startMyQuiz=view.findViewById(R.id.startMyQuizId);
        mLastReadLesson.setText(GlobalVar.gLastReadLessonTitle);

        startMyQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context,SlidingQuizActivity.class);
                startActivity(i);
            }
        });

        LinearLayout mHistoryContent=view.findViewById(R.id.historyContent);
        mHistoryContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context,CourseContentDetailActivity.class);
                startActivity(i);
            }
        });

        ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> unitArray = GlobalVar.courseContentDetailList.get(0).getmArrayListCourseUnits();
        final ArrayList<DetailDataModelCoursesDetailContents> units = unitArray.get(GlobalVar.gNthCourse);

        if(units.size()>thisFragmentUniNumber) {

            String unitTitle = units.get(thisFragmentUniNumber).getUnitNames();
            String unitOrder = units.get(thisFragmentUniNumber).getUnitOrders();
            String unitId = units.get(thisFragmentUniNumber).getUnitID();

            GlobalVar.gUnitId=unitId;


            if(unitTitle.equalsIgnoreCase("Quiz")){

                unitTitle=("কুইজ");
            }
            if(unitTitle.equalsIgnoreCase("Exam")){

                unitTitle=("পরীক্ষা");
            }
            if(unitTitle.equalsIgnoreCase("Assignment")){

                unitTitle=("এসাইনমেন্ট");
            }


            unitOrderText = view.findViewById(R.id.unitOrder);
            unitTitleText = view.findViewById(R.id.unitTitleId);

            unitOrderText.setText(convertEngToBn(unitOrder));
            unitTitleText.setText(unitTitle);

            if(unitTitle.equalsIgnoreCase("কুইজ")) {
                setQuiz();
            }
            else if(unitTitle.equalsIgnoreCase("এসাইনমেন্ট")){
                Toast.makeText(context,"Unit Assignment", Toast.LENGTH_LONG).show();
            }
            else if(unitTitle.equalsIgnoreCase("পরীক্ষা")){
                setExam();
            }
            else {
                setRecyclerViewContent();
            }
        }
        else {
            Toast.makeText(context,"No more data to show", Toast.LENGTH_LONG).show();
        }

        return view;

    }

    private void setQuiz(){

        String lessonId = GlobalVar.courseContentDetailList.get(0).getmArrayListCourseLesson().get(GlobalVar.gNthCourse).get(0).getIdLesson();
        GlobalVar.gLessonId=lessonId;

        totalQ=view.findViewById(R.id.totalQid);
        totalTime=view.findViewById(R.id.timeDurationId);
        totalMarks=view.findViewById(R.id.totalMarksId);
        quizSummeryLinLayout=view.findViewById(R.id.quizSummeryLayoutId);

        final ArrayList<DetailDataModelCoursesDetailContents> mQuizInfo = GlobalVar.courseContentDetailList.get(0).getmUnitDataArrayListContent4().get(GlobalVar.gNthCourse);

        if(mQuizInfo.size()>thisFragmentUniNumber) {
            String quizMarks = mQuizInfo.get(thisFragmentUniNumber).getmQuizMarks();
            String quizTime = mQuizInfo.get(thisFragmentUniNumber).getmQuizTime();

            quizSummeryLinLayout.setVisibility(View.VISIBLE);

            totalQ.setText(convertEngToBn(Integer.toString(GlobalVar.gTotalQuizNumberThisCourse-1)));
            totalTime.setText(convertEngToBn(stringForTime(Integer.parseInt(quizTime))));
            totalMarks.setText(convertEngToBn(quizMarks));

        }
    }

    private void setRecyclerViewContent() {

        recyclerView = view.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setNestedScrollingEnabled(false);

        new GetCoursesContents().execute();
    }

    public class GetCoursesContents extends AsyncTask<String, Void, Void> {
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

            adapter=new RecyclerViewAdapterMyPageContents(GlobalVar.thisFragmentContents,context);

            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            //mProgressSpinner.setVisibility(View.GONE);
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

    private void setExam(){

        String lessonId = GlobalVar.courseContentDetailList.get(0).getmArrayListCourseLesson().get(GlobalVar.gNthCourse).get(0).getIdLesson();
        GlobalVar.gLessonId=lessonId;

        Button startMyExam=view.findViewById(R.id.startMyExamId);
        TextView totalEQ=view.findViewById(R.id.totalEQid);
        TextView totalETime=view.findViewById(R.id.timeEDurationId);
        TextView totalEMarks=view.findViewById(R.id.totalEMarksId);
        LinearLayout examSummeryLinLayout=view.findViewById(R.id.examSummeryLayoutId);
        TextView examPassMarksTextView=view.findViewById(R.id.passMarkId);

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

        String qwwret="";

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
}


//TODO
        /*for (int i=0;i<mQuizParents.size();i++)
        {
            ParentMenu parent = new ParentMenu();

            String quesTitle = mQuizParents.get(i).getmQuizTitle();

            final TextView mParentQTtl=new TextView(context);
            mParentQTtl.setText(quesTitle);

            mParentLinLay.addView(mParentQTtl);

            parent.setTitle(quesTitle);

            String childID="";
            String childAns="";

            childList=new ArrayList<>();


            arrayParents.add(parent);

            for(int child=0; child<mQuizOptionChilds.size(); child++)
            {
                try {
                     childID = mQuizOptionChilds.get(i).get(child).getmOptionBody();
                     childAns = mQuizOptionChilds.get(i).get(child).getmOptionAnswer();
                }
                catch (Exception ex) {
                    Log.d("", "onCreateView: ");
                }

                optnCheckbox= new CheckBox(context);
                optnCheckbox.setText(childID);


                mParentLinLay.addView(optnCheckbox);


                Parent p = new Parent();

                p.setCat_name(childID);
                p.setCat_id(childAns);
                childList.add(p);

                if (childList.size() > 0)
                    parent.setArrayChildren(childList);

                answerList= arrayParents.get(i).getArrayChildren();
                optnCheckbox.setTag(childAns);
                optnCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                        if(isChecked)
                        {
                            //buttonView.getTag();


                            try {
                                Object something = buttonView.getTag();

                                if (something.equals("true")) {
                                    //Toast.makeText(context,"Correct Answer",Toast.LENGTH_SHORT).show();

                                    markCount++;
                                }
                                else{
                                    //Toast.makeText(context,"Incorrect Answer",Toast.LENGTH_SHORT).show();
                                }

                                String abcd = something.toString();
                            }
                            catch (Exception ex){
                                Log.d("", "onCheckedChanged: ");
                            }

                            String abcd="";

                            *//*for(int tap=0; tap<answerList.size(); tap++) {
                                CharSequence checkedAns=buttonView.getText();
                                String ans=answerList.get(tap).getCat_name();

                                if(checkedAns.equals(ans)) {


                                }
                            }*//*
                        }
                    }
                }
                );
            }
        }*/
