package orangebd.newaspaper.mymuktopathapp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;


public class MyPageDetailFragment1 extends Fragment {

    private Context context;

    private View view;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private TextView unitOrderText;
    private TextView unitTitleText;

    private LinearLayout quizSummeryLinLayout;

    private TextView totalQ;
    private TextView totalTime;
    private TextView totalMarks;

    private TextView mLastReadLesson;

    private Button startMyQuiz;

    private int thisFragmentUniNumber=0;

    private ArrayList<String> contentTypeArr;

    ArrayList<DetailDataModelCoursesDetailContents> contentTypeArray;

    private String ratingPointStr;

    private String lessonId;


    private String unitTitle;


    private String sendratingUrl = GlobalVar.gApiBaseUrl + "/api/rating/";
    private HashMap<String,String> mapHit;

    private ImageView mLessonIconView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_my_page_detail_fragment1, container, false);

        context=getContext();


        mLessonIconView=view.findViewById(R.id.lessonIconDynamicId);

        if(GlobalVar.gLastReadLessonTitle!=null){

            if(GlobalVar.gLastReadLessonTitle.equalsIgnoreCase("exam")){

                mLessonIconView.setImageResource(R.drawable.mukto_exam_icon);
            }
            else if(GlobalVar.gLastReadLessonTitle.equalsIgnoreCase("quiz")){

                mLessonIconView.setImageResource(R.drawable.mukto_quiz_icon);
            }
            else if(GlobalVar.gLastReadLessonTitle.equalsIgnoreCase("assignment")){

                mLessonIconView.setImageResource(R.drawable.mukto_assignment_icon);
            }
            else if(GlobalVar.gLastReadLessonTitle.equalsIgnoreCase("discussion")){

                mLessonIconView.setImageResource(R.drawable.mukto_discussion_icon);
            }
            else if(GlobalVar.gLastReadLessonTitle.equalsIgnoreCase("live_class")){

                mLessonIconView.setImageResource(R.drawable.mukto_live_class);
            }
            else{
                mLessonIconView.setImageResource(R.drawable.play_button_round);
            }
        }



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

        String qwfdfg="";

        if(units.size()>thisFragmentUniNumber) {

            unitTitle = units.get(thisFragmentUniNumber).getUnitNames();
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

            /*if(unitTitle.equalsIgnoreCase("কুইজ")) {
                setQuiz();
            }
            else if(unitTitle.equalsIgnoreCase("এসাইনমেন্ট")) {
                Toast.makeText(context,"Unit Assignment", Toast.LENGTH_LONG).show();
            }
            else if(unitTitle.equalsIgnoreCase("পরীক্ষা")){
                setExam();
            }
            else {

            }*/

            setRecyclerViewContent();
        }
        else {
            Toast.makeText(context,"No more data to show", Toast.LENGTH_LONG).show();
        }


        String enrollCourseCompltness=GlobalVar.gEnrollCourseId.get(GlobalVar.gNthCourse).getmEcCompleteness();


        /*if(enrollCourseCompltness.equalsIgnoreCase("100")){
            showPopUpImageBox();
        }*/

        return view;
    }



    private void setQuiz() {

        totalQ=view.findViewById(R.id.totalQid);
        totalTime=view.findViewById(R.id.timeDurationId);
        totalMarks=view.findViewById(R.id.totalMarksId);
        quizSummeryLinLayout=view.findViewById(R.id.quizSummeryLayoutId);

        final ArrayList<DetailDataModelCoursesDetailContents> mQuizInfo = GlobalVar.courseContentDetailList.get(0).getmUnitDataArrayListContent4().get(GlobalVar.gNthCourse);

        if(mQuizInfo.size()>0) {
            String quizMarks = mQuizInfo.get(0).getmQuizMarks();
            String quizTime = mQuizInfo.get(0).getmQuizTime();

            quizSummeryLinLayout.setVisibility(View.VISIBLE);

            totalQ.setText(convertEngToBn(Integer.toString(GlobalVar.gTotalQuizNumberThisCourse-1)));
            totalTime.setText(convertEngToBn(stringForTime(Integer.parseInt(quizTime))));
            totalMarks.setText(convertEngToBn(quizMarks));

            String abtqtq = "";
        }

    }

    private void setExam() {

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


            //getting completeness
            ArrayList <DetailDataModelCoursesDetailContents> lessonNamesCompleteness = GlobalVar.courseContentDetailList.get(0).getmArrayListCourseCompletenes().get(GlobalVar.gNthCourse).get(thisFragmentUniNumber);

            //handling the global dynamic unit numbers


            if(GlobalVar.gUnitGoingDirection!=null) {
                if (GlobalVar.gUnitGoingDirection.equalsIgnoreCase("left")) {
                    GlobalVar.gUnitNumber = thisFragmentUniNumber + 1;
                }
            }
            else {
                GlobalVar.gUnitNumber = 0;
            }

            //for content type array
            contentTypeArray = GlobalVar.courseContentDetailList.get(0).getmUnitAllArrayList().get(GlobalVar.gNthCourse).get(thisFragmentUniNumber);

            adapter=new RecyclerViewAdapterMyPageContentTypes(unitTitle, thisFragmentUniNumber,lessonNamesCompleteness,contentTypeArray,context);

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

    private void showPopUpImageBox()
    {
        // custom dialog
        final Dialog dialog = new Dialog(context, R.style.DialogCustomTheme);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popupwindowforrating);

        RatingBar ratingBar=dialog.findViewById(R.id.ratingBar);

        final TextInputEditText mFeedBackField = dialog.findViewById(R.id.feedBackField);
        Button sendRatingBtn = dialog.findViewById(R.id.sendReport);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                int ratingInt =  (int)rating;

                ratingBar.setRating(rating);

                ratingPointStr = Float.toString(rating);
            }
        });


        sendRatingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String feedBackStr=mFeedBackField.getText().toString();

                String enrollId=GlobalVar.gEnrollCourseId.get(GlobalVar.gNthCourse).getmEcId();

                mapHit =  new HashMap<>();

                mapHit.put("rating_point", ratingPointStr);
                mapHit.put("feedback_comments", feedBackStr);

                new SendRating().execute(sendratingUrl+ enrollId);

                dialog.dismiss();
            }
        });

        dialog.show();
    }


    public class SendRating extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... arg0) {
            String response = null;

            try {
                HttpURLConnection c = (HttpURLConnection) new URL(arg0[0]).openConnection();
                c.setRequestMethod("POST");
                c.setUseCaches(false);
                c.setRequestProperty ("Authorization", "Bearer "+GlobalVar.gReplacingToken);
                c.connect();

                InputStream in = new BufferedInputStream(c.getInputStream());
                response = convertStreamToString(in);
                c.disconnect();
            }
            catch (Exception ex){
                Log.d("",ex.getMessage());
            }
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {

                JSONObject jObject = new JSONObject(result);

                String abcd="";
            }
            catch (Exception ex){
                Log.d("", "onPostExecute: ");
            }
        }
    }


    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }

}
