package orangebd.newaspaper.mymuktopathapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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
import java.util.Locale;
import java.util.Map;


public class ExamFragmentSubmit extends Fragment {

    private Context context;
    private View view;

    private TextView totalQ;
    private TextView totalTime;
    private TextView totalMarks;
    private TextView passMarksTextView;

    private Button submitAnswers;

    String urlExamSubmit = GlobalVar.gApiBaseUrl +"/api/exam/app/";

    private int totalmark;


    private String examMarks;
    private String examTime;
    private String passPercentageMarks;

    private String passMarks;

    private HashMap<String,String> mapSubmit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_exam_fragment_submit, container, false);

        context=getContext();

        totalQ=view.findViewById(R.id.totalQid);
        totalTime=view.findViewById(R.id.timeDurationId);
        totalMarks=view.findViewById(R.id.totalMarksId);
        passMarksTextView=view.findViewById(R.id.passMarkId);

        final String enrollId=GlobalVar.gEnrollCourseId.get(GlobalVar.gNthCourse).getmEcId();


        final ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> mExamParents = GlobalVar.courseContentDetailList.get(0).getmArrayListCourseQuizs();

        final ArrayList<DetailDataModelCoursesDetailContents> mExamInfo = GlobalVar.courseContentDetailList.get(0).getmUnitDataArrayListContent2().get(GlobalVar.gNthCourse);
        final ArrayList<DetailDataModelCoursesMarks> mExamPassInfo = GlobalVar.courseContentDetailList.get(0).getmArrayListMarks();
        //.get(GlobalVar.gNthCourse);


        if(mExamInfo.size()>0) {
            examMarks = mExamInfo.get(0).getmExamMarks();
            examTime = mExamInfo.get(0).getmExamTime();

            passPercentageMarks = mExamPassInfo.get(0).getExam_pass_mark();

            double passPercentageMarksInt = Double.parseDouble(passPercentageMarks);
            double passExameMarksInt = Double.parseDouble(examMarks);

            double passMarksInt = passExameMarksInt/passPercentageMarksInt;
            passMarks = Double.toString(passMarksInt);

            totalQ.setText(convertEngToBn(Integer.toString(GlobalVar.gTotalExamNumberThisCourse-1)));
            totalTime.setText(convertEngToBn(stringForTime(Integer.parseInt(examTime))));
            totalMarks.setText(convertEngToBn(examMarks));
            passMarksTextView.setText(convertEngToBn(passMarks));

            String abtqtq = "";
        }

        final ArrayList<DetailDataModelCoursesDetailContents> mQListForId = GlobalVar.courseContentDetailList.get(0).getmArrayListCourseQuizsExam().get(GlobalVar.gNthCourse);

        submitAnswers=view.findViewById(R.id.submitAnswers);
        submitAnswers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int numberOfExam=mExamInfo.size();

                if(GlobalVar.gMultiMarkCount>0) {

                    totalmark = GlobalVar.gMultiMarkCount*2;

                    String aqw = "";
                }
                else if(GlobalVar.gMultiMarkCount>1){
                    totalmark = GlobalVar.gMultiMarkCount*2;

                    String aqw = "";
                }

                double totalmarkcalc= Double.parseDouble(Integer.toString(totalmark));
                double examMarkscalc= Double.parseDouble(examMarks);

                double gotPercentagedmark= (totalmarkcalc/examMarkscalc)*100;

                final String totalMarkWithPercentageStr = String.format(Locale.US, "%.2f", gotPercentagedmark);

                //for making Q id Array

                JSONArray jArray = new JSONArray();

                try {
                    for(int jQCount=0; jQCount<mQListForId.size(); jQCount++) {

                        String jObj=mQListForId.get(jQCount).getmQuizId();

                        jArray.put(jObj);
                    }
                }
                catch (Exception ex) {
                    Log.d("", "onClick: ");
                }
                String quesArray=jArray.toString();


                //for making attended Q id Array

                JSONArray jAtndArray = new JSONArray();

                try {
                    for(int jAQCount=0; jAQCount<GlobalVar.attendedQArray.size(); jAQCount++) {

                        String jAQObj=GlobalVar.attendedQArray.get(jAQCount);

                        jAtndArray.put(jAQObj);
                    }
                }
                catch (Exception ex) {
                    Log.d("", "onClick: ");
                }
                String attendedQuesArray=jAtndArray.toString();



                //for making attended Q answer Array

                JSONArray jAnsArray = new JSONArray();

                try {
                    for(int jAnsCount=0; jAnsCount<GlobalVar.answerArray.size(); jAnsCount++) {

                        String jAnsObj=GlobalVar.answerArray.get(jAnsCount);

                        jAnsArray.put(jAnsObj);
                    }
                }
                catch (Exception ex) {
                    Log.d("", "onClick: ");
                }
                String answerArray=jAnsArray.toString();





                mapSubmit =  new HashMap<>();
                mapSubmit.put("unit_id", GlobalVar.gUnitId);
                //mapSubmit.put("unit_id", "1");
                mapSubmit.put("lesson_id", GlobalVar.gLessonId);
                mapSubmit.put("out_of_marks", examMarks);
                mapSubmit.put("ques_list_id", quesArray);
                mapSubmit.put("attend_ques_list", attendedQuesArray);
                mapSubmit.put("ques_ans_list", answerArray);
                mapSubmit.put("obtain_marks", "");

                new StartSubmit().execute(urlExamSubmit+enrollId);

                Intent i = new Intent(context, ExamReportPendingActivity.class);

                i.putExtra("epm", passPercentageMarks);
                i.putExtra("etgm", totalMarkWithPercentageStr);


                v.getContext().startActivity(i);
            }
        });

        return view;
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


    public class StartSubmit extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            String data = performPostCall(params[0], mapSubmit);

            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {
                JSONObject jObject = new JSONObject(result);

                Toast.makeText(context,result,Toast.LENGTH_LONG).show();

                Intent i=new Intent(context,MyPageActivity.class);
                startActivity(i);
            }
            catch (Exception ex){
                Log.d("", "onPostExecute: ");
            }
        }
        @Override
        protected void onCancelled() {

        }
    }

    public String  performPostCall(String requestURL, HashMap<String, String> postDataParams) {

        URL url;
        String response = "";

        try {
            url = new URL(requestURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setRequestProperty ("Authorization", "Bearer "+GlobalVar.gReplacingTokenForAllCategories);
            conn.setDoInput(true);
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(postDataParams));

            writer.flush();
            writer.close();
            os.close();
            int responseCode=conn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                String line;
                BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line=br.readLine()) != null) {
                    response+=line;
                }
            }

            else {
                response="";
            }

        }
        catch (Exception e) {

            e.printStackTrace();
        }

        return response;
    }

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();

        boolean first = true;

        for(Map.Entry<String, String> entry : params.entrySet()) {

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }
}
