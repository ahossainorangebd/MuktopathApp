package orangebd.newaspaper.mymuktopathapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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
import java.util.HashMap;
import java.util.Map;

public class CourseDetailActivity extends AppCompatActivity {

    private static RecyclerView recyclerView;
    private ProgressBar mProgressSpinner;
    private RecyclerView.Adapter adapter;

    private WebView mWebView;
    private Context context;
    String ImgUrl;
    String DetailDescription;
    String title;
    String paymentStatus;
    String batchId;
    String scSize;

    int sUnitSize;

    private TextView titleTextView;
    private WebView detailDescTextView;
    private ImageView CoverPhoto;

    //private ImageView mLogoIcon;

    ArrayList<DetailDataModel> detailListHeadLine=new ArrayList<>();

    private HashMap<String,String> mapEnroll;

    private LinearLayout enrollThisSection;

    private TextView enrollText;

    private String paymentStatusFreeOrNot;

    String urlCheckEnrolledOrNot = GlobalVar.gApiBaseUrl +"/api/enrolled/check";
    String urlEnrollThis = GlobalVar.gApiBaseUrl +"/api/course-enrollment";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        context=this;

        //TODO
        /*mWebView=findViewById(R.id.myWebView);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new MyWebViewClient());*/

        View view = LayoutInflater.from(context).inflate(R.layout.custom_logodetails, null, false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().hide();
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(view);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#7a19aa")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

         titleTextView=findViewById(R.id.courseTitle);
         detailDescTextView=findViewById(R.id.detailDesc);
         CoverPhoto=findViewById(R.id.CourseDetailCoverImage);

        enrollThisSection=findViewById(R.id.enrollThisId);
        enrollText=findViewById(R.id.enrollText);

        ImgUrl = getIntent().getExtras().getString("img");
        DetailDescription=getIntent().getExtras().getString("detail");
        title = getIntent().getExtras().getString("ttl");
        paymentStatus = getIntent().getExtras().getString("pstatus");
        batchId = getIntent().getExtras().getString("batchid");

        scSize = getIntent().getExtras().getString("scsize");
        sUnitSize = getIntent().getExtras().getInt("usize");

        final int scSizeConv = Integer.parseInt(scSize);

        titleTextView.setText(title);

        detailDescTextView.getSettings().setJavaScriptEnabled(true);
        detailDescTextView.getSettings().setDomStorageEnabled(true);

        detailDescTextView.loadDataWithBaseURL("",DetailDescription, "text/html", "utf-8", "");


        enrollThisSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JSONArray jArray = new JSONArray();

                StringBuilder sb= new StringBuilder();

                String start = "start";
                String completeness = "completeness";

                try {
                    JSONObject jObj = new JSONObject();
                    jObj.put(start, 0);
                    jObj.put(completeness, 0);

                for(int jStatusCount=0; jStatusCount<scSizeConv; jStatusCount++){

                    jArray.put(jObj);
                }

                }
                catch (Exception ex){
                    Log.d("", "onClick: ");
                }

                JSONArray jArray2= new JSONArray();


                JSONArray jArray3= new JSONArray();

                for(int jUnitCount=0; jUnitCount<sUnitSize; jUnitCount++){

                    jArray3.put(jArray);
                }

                //jArray3.put(jArray);

                String strForReplacing=jArray3.toString();
                strForReplacing.replace("","");
                strForReplacing.toCharArray();

                //int firstNumber=sb.length();

                //String something =sb.substring(0,firstNumber-1);

                /*String fBrac="[[";
                String lBrac="]]";

                String jStatusInJsonFormat= fBrac + something + lBrac;*/

                String jObjStr=jArray3.toString();

                mapEnroll =  new HashMap<>();
                mapEnroll.put("batches[]", batchId);
                mapEnroll.put("amount", "0");
                mapEnroll.put("journey_status", jObjStr);

                new StartEnroll().execute(urlEnrollThis);
            }
        });

        //detailDescTextView.setText(DetailDescription);

        try {
            Picasso.with(context)
                    .load(ImgUrl)
                    .into(CoverPhoto);
        }
        catch (Exception ex){}

        setRecyclerView();
    }

    public static void deleteCache(Context context) {

        try {
            File dir = context.getCacheDir();
            deleteDir(dir);
        } catch (Exception e) {}
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if(dir!= null && dir.isFile()) {
            return dir.delete();
        }
        else {
            return false;
        }
    }

    /*private void shareIt() {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Rtv");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, url);
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, ShareURL);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }*/

    @Override
    public void onBackPressed() {
        finish();
        return;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id==R.id.disablePushNotification){
            Intent intent = new Intent();
            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.putExtra("app_package", getPackageName());
            intent.putExtra("app_uid", getApplicationInfo().uid);
            intent.putExtra("android.provider.extra.APP_PACKAGE", getPackageName());
            startActivity(intent);
        }

        else if (id == android.R.id.home) {
            finish();
            return true;
        }
        /*else if (id==R.id.searchBtnId)
            shareIt();*/
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPause(){
        super.onPause();

       //TODO
        //mWebView.onPause();
    }

    private void setRecyclerView() {

        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setNestedScrollingEnabled(false);

        adapter=new RecyclerViewAdapterCourseDetailContents(GlobalVar.gChildArrayOfContent,context);

        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        new CheckEnrollStatus().execute(urlCheckEnrolledOrNot+"/"+batchId);
    }


    public class CheckEnrollStatus extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... arg0) {
            String response = null;

            try {
                HttpURLConnection c = (HttpURLConnection) new URL(arg0[0]).openConnection();
                c.setRequestMethod("GET");
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

            String abbbbaaaqq="";

            if(paymentStatus==null){
                paymentStatus="0";
            }

            if(paymentStatus.equalsIgnoreCase("0")) {

                paymentStatusFreeOrNot="";
            }
            else{
                paymentStatusFreeOrNot="(Paid)";
            }

            try {
                JSONObject jObject = new JSONObject(result);

                String mCheckStatus = jObject.getString("status");
                String mCheckEnrollmentId = jObject.getString("EnrollmentId");

                if(mCheckStatus.equalsIgnoreCase("2")) {
                    enrollText.setText("োর্স শুরু করুন"+ paymentStatusFreeOrNot);
                }
                else if (mCheckStatus.equalsIgnoreCase("1")) {
                    enrollText.setText("আপনি ইতিমধ্যে কোর্সটিতে অংশগ্রহণ করেছেন" + paymentStatusFreeOrNot);
                }
                else if (mCheckStatus.equalsIgnoreCase("3")) {
                    enrollText.setText("কোর্স শুরু করুন"+ paymentStatusFreeOrNot);
                }

                /*else if (mCheckStatus.equalsIgnoreCase("3")) {
                    enrollText.setText("Coordinator");
                }*/

            }
            catch (Exception ex){
                Log.d("", "onPostExecute: ");
            }
        }
    }

    public class StartEnroll extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            String data = performPostCall(params[0], mapEnroll);

            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {
                JSONObject jObject = new JSONObject(result);

                String returnMessage = jObject.getString("message");

                Toast.makeText(context,returnMessage,Toast.LENGTH_LONG).show();


                deleteCache(context);


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

    //Other files

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
