package orangebd.newaspaper.mymuktopathapp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

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
import java.util.HashMap;
import java.util.Map;

public class ManualContentActivity extends AppCompatActivity {

    private Context context;

    String DetailDescription;
    String title;

    private String unitId;

    private WebView detailDescTextView;

    private HashMap<String,String> mapHitGamification;

    private String hitGamificationForLessonComplete = GlobalVar.gApiBaseUrl + "/api/gamification/course";

    private String thisCourseId;
    private String thisBatchId;

    // for posting completeness
    private HashMap<String,String> mapSubmit;

    String urlCompletenessSubmit = GlobalVar.gApiBaseUrl +"/api/journey/status/app/";


    private int CountAUnitsLessons;


    private TextView mCustomContentTitle;

    private String lessonCompleteness;


    private String contentIconType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_content);


        context=this;

        final View view = LayoutInflater.from(context).inflate(R.layout.custom_logodetails_course_content_title, null, false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(view);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#7a19aa")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        detailDescTextView=findViewById(R.id.detailDesc);

        if(GlobalVar.isRedirectFromContentPage){

            DetailDescription=GlobalVar.gDescriptionText;
            title = GlobalVar.gCourseDetailTitle;
            lessonCompleteness = GlobalVar.gLessonCompleteness;

            contentIconType = GlobalVar.gContentIconType;

            String abcd1212aads="";
        }
        else {
            DetailDescription=getIntent().getExtras().getString("detail");
            title = getIntent().getExtras().getString("ttl");
            unitId = getIntent().getExtras().getString("unitid");

            contentIconType = getIntent().getExtras().getString("ciconype");
            lessonCompleteness = getIntent().getExtras().getString("thislessoncompltness");


            String abcd1212="";
        }

        GlobalVar.gLastReadLessonTitle=title;

        mCustomContentTitle=view.findViewById(R.id.muktoCustomContentTitle);
        mCustomContentTitle.setText(title);

        detailDescTextView.getSettings().setJavaScriptEnabled(true);
        detailDescTextView.getSettings().setDomStorageEnabled(true);
        detailDescTextView.loadDataWithBaseURL("",DetailDescription, "text/html", "utf-8", "");

        thisCourseId=GlobalVar.gCourseIdListForCourseId.get(GlobalVar.gNthCourse).getIdCourse();
        thisBatchId=GlobalVar.gEnrollCourseList.get(GlobalVar.gNthCourse).getmId();
    }

    public String  performPostCallWithBearer(String requestURL, HashMap<String, String> postDataParams) {

        URL url;
        String response = "";

        try {
            url = new URL(requestURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setRequestProperty ("Authorization", "Bearer "+GlobalVar.gReplacingToken);
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

    private void showPopUpCompletenessSubmit()
    {
        final Dialog dialog = new Dialog(context, R.style.DialogCustomTheme);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popupwindowforcompletenesssubmit);

        Button okButton = dialog.findViewById(R.id.submitBtn);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                final String enrollId=GlobalVar.gEnrollCourseId.get(GlobalVar.gNthCourse).getmEcId();

                String enrollCourseCompltness=GlobalVar.gEnrollCourseId.get(GlobalVar.gNthCourse).getmEcCompleteness();

                // Let's count the total progress of a course
                ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> units= GlobalVar.courseContentDetailList.get(0).getmUnitAllArrayList().get(GlobalVar.gNthCourse);


                for(int incrUnits=0; incrUnits<units.size(); incrUnits++) {

                    ArrayList <DetailDataModelCoursesDetailContents> lessons = units.get(incrUnits);

                    CountAUnitsLessons = CountAUnitsLessons + lessons.size();
                }

                int eachLessonProgress = 100/CountAUnitsLessons;

                double enrollCourseCompltnessDouble = Double.parseDouble(enrollCourseCompltness);

                double progressedEnrollCourseCompleteDouble = enrollCourseCompltnessDouble + eachLessonProgress;

                enrollCourseCompltness = String.valueOf(progressedEnrollCourseCompleteDouble);


                enrollCourseCompltness = String.format("%.0f", progressedEnrollCourseCompleteDouble);

                if(Integer.parseInt(enrollCourseCompltness)>100) {
                    enrollCourseCompltness="100";
                }

                //String eachLessonProgressStr = String.valueOf(eachLessonProgress);

                mapSubmit =  new HashMap<>();

                mapSubmit.put("unit_id", unitId);
                mapSubmit.put("lesson_id", GlobalVar.gLessonId);
                mapSubmit.put("completeness", "100");
                mapSubmit.put("start", "0");
                mapSubmit.put("course_completeness", enrollCourseCompltness);

                GlobalVar.gLessonCompleteTempUnitId = unitId;
                GlobalVar.gLessonCompleteTempLessonId = GlobalVar.gLessonId;

                new CompletenessSubmit().execute(urlCompletenessSubmit+enrollId);

                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public class CompletenessSubmit extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            String data = performPostCallWithBearer(params[0], mapSubmit);

            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {
                JSONObject jObject = new JSONObject(result);

                //Toast.makeText(context,"successfully submitted.",Toast.LENGTH_LONG).show();
            }
            catch (Exception ex){
                Log.d("", "onPostExecute: ");
            }


            mapHitGamification =  new HashMap<>();

            mapHitGamification.put("unit_id", GlobalVar.gUnitId);
            mapHitGamification.put("lesson_id", GlobalVar.gLessonId);
            mapHitGamification.put("course_batch_id", thisBatchId);
            mapHitGamification.put("slug", "vlc");

            new HitGamificationOnCompleteLesson().execute(hitGamificationForLessonComplete);
        }
        @Override
        protected void onCancelled() {

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {

            //TODO
            GlobalVar.isRedirectFromContentPage=true;

            Intent i=new Intent(context,MyPageCourseDetail.class);
            startActivity(i);

            return true;

            /*finish();
            return true;*/

        }
        return super.onOptionsItemSelected(item);
    }

    public class HitGamificationOnCompleteLesson extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            String data = performPostCallWithBearer(params[0], mapHitGamification);

            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            /*try {
                JSONArray jsonArray = new JSONArray(result);
            }
            catch (Exception ex){
                Log.d("", "onPostExecute: ");
            }*/
        }

        @Override
        protected void onCancelled() {

        }
    }

}
