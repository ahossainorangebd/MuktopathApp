package orangebd.newaspaper.mymuktopathapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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
import java.util.HashMap;
import java.util.Map;

public class DiscussionActivity extends AppCompatActivity {

    private String discussionUrl = GlobalVar.gApiBaseUrl + "/api/discussion/";
    private String discussionGet = GlobalVar.gApiBaseUrl + "/api/discussion/get/";
    private String discussionGetAll = GlobalVar.gApiBaseUrl + "/api/discussion/get_all/";

    private Context context;

    private String commentString;

    private Button mSubmitMyComment;

    private TextInputEditText mCommentField;

    private HashMap<String,String> mapSubmit;
    private HashMap<String,String> mapSubmitGet;
    private HashMap<String,String> mapSubmitGetAll;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private String enrollId;

    private String thisCourseId;

    ArrayList<DetailDataModelCoursesDetailContents> commentList;


    ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> commentAndReply;



    ArrayList<DetailDataModelCoursesDetailContents> mReplyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion);

        context=this;

        enrollId=GlobalVar.gEnrollCourseId.get(GlobalVar.gNthCourse).getmEcId();

        // getting course it
        thisCourseId=GlobalVar.gEnrollCourseList.get(GlobalVar.gNthCourse).getmId();

        mCommentField=findViewById(R.id.commentField);


        mSubmitMyComment=findViewById(R.id.submitMyCommentId);
        mSubmitMyComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                commentString=mCommentField.getText().toString();

                mapSubmit =  new HashMap<>();
                mapSubmit.put("unit_id", GlobalVar.gUnitId);
                mapSubmit.put("lesson_id", GlobalVar.gLessonId);
                mapSubmit.put("comments", commentString);

                new StartSubmit().execute(discussionUrl+enrollId);
            }
        });

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

                mapSubmitGet =  new HashMap<>();
                mapSubmitGet.put("unit_id", GlobalVar.gUnitId);
                mapSubmitGet.put("lesson_id", GlobalVar.gLessonId);

                new StartSubmitGet().execute(discussionGet+enrollId);
            }
            catch (Exception ex){
                Log.d("", "onPostExecute: ");
            }
        }
        @Override
        protected void onCancelled() {

        }
    }


    public class StartSubmitGet extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            String data = performPostCall(params[0], mapSubmitGet);

            try {

                JSONObject jObjectAllDiscussion = new JSONObject(data);

                String axbsdwd="";
            }
            catch (Exception ex){
                Log.d("", "doInBackground: ");
            }

            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {
                JSONObject jObject = new JSONObject(result);

                mapSubmitGetAll =  new HashMap<>();
                mapSubmitGetAll.put("unit_id", GlobalVar.gUnitId);
                mapSubmitGetAll.put("lesson_id", GlobalVar.gLessonId);
                mapSubmitGetAll.put("enrolled_id", enrollId);


                new StartSubmitGetAll().execute(discussionGetAll+ thisCourseId);
            }
            catch (Exception ex){
                Log.d("", "onPostExecute: ");
            }
        }
        @Override
        protected void onCancelled() {

        }
    }

    public class StartSubmitGetAll extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            String data = performPostCall(params[0], mapSubmitGetAll);

            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {
                JSONArray jsonArray = new JSONArray(result);

                commentList = new ArrayList<>();
                commentAndReply = new ArrayList<>();

                for(int comments=0; comments<jsonArray.length(); comments++){

                    DetailDataModelCoursesDetailContents modelComments = new DetailDataModelCoursesDetailContents();

                    JSONObject commentNumbers = jsonArray.getJSONObject(comments);

                    String commenterName = commentNumbers.getString("user_name");
                    String mComments = commentNumbers.getString("comments");

                    modelComments.setmCommenterNames(commenterName);
                    modelComments.setmCommentBody(mComments);

                    JSONArray replyArray = commentNumbers.getJSONArray("get_replies");

                    mReplyList = new ArrayList<>();

                    for(int replies=0; replies<replyArray.length(); replies++){

                        DetailDataModelCoursesDetailContents modelReplies = new DetailDataModelCoursesDetailContents();

                        JSONObject replyNumbers = replyArray.getJSONObject(replies);

                        JSONObject replierInfo = replyNumbers.getJSONObject("get_user");
                        String replyBody = replyNumbers.getString("comments");

                        JSONObject getReplierInfo = replierInfo.getJSONObject("get_user_info");

                        String replierName = replierInfo.getString("name");
                       // String replyBody = replierInfo.getString("comments");

                        modelReplies.setmReplierNames(replierName);
                        modelReplies.setmReplyBody(replyBody);


                        mReplyList.add(modelReplies);
                    }

                    commentList.add(modelComments);


                    commentAndReply.add(mReplyList);
                }

            }
            catch (Exception ex){
                Log.d("", "onPostExecute: ");
            }



            setRecyclerViewContent();

            mCommentField.setText("");
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

    private void setRecyclerViewContent() {

        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setNestedScrollingEnabled(false);

        new GetAllComments().execute();
    }

    public class GetAllComments extends AsyncTask<String, Void, Void> {
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

            adapter=new RecyclerViewAdapterLessonDiscussion(commentList,commentAndReply,context);

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
}
