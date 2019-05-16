package orangebd.newaspaper.mymuktopathapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
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

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {
    
    private static final int REQUEST_READ_CONTACTS = 0;
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };

    private ProgressDialog mProgressDialog;

    private Context mContext;

    private Button mRegiPageSignUpBtn;

    private HashMap<String, String> map;

    private EditText mEmailView;
    private String mStrEmail;

    private EditText mEdtTxtPwd;
    private String mStrPwd;

    private ArrayList<DetailDataModelCourses> detailList=new ArrayList<>();
    private ArrayList<DetailDataModelCourses> detailList2=new ArrayList<>();
    private ArrayList<DetailDataModelCourses> detailList3=new ArrayList<>();
    private ArrayList<DetailDataModelCourses> detailList4=new ArrayList<>();

    private ArrayList<DetailDataModelCoursesDetailContents> detailListCourseDetailContents;

    private ArrayList<DetailDataModelCourses> detailListAnoPart1=new ArrayList<>();
    private ArrayList<DetailDataModelCourses> detailListAnoPart2=new ArrayList<>();
    private ArrayList<DetailDataModelCourses> detailListAnoPart3=new ArrayList<>();

    private ArrayList<DetailDataModelCourses> detailListCourse;

    private Object[] mArrayList;


    //All the detail Lists
    private ArrayList<DetailDataModelCourses> detailList5;
    private ArrayList<DetailDataModelCourses> detailList6;
    private ArrayList<DetailDataModelCourses> detailList7;
    private ArrayList<DetailDataModelCourses> detailList8;
    private ArrayList<DetailDataModelCourses> detailList9;
    private ArrayList<DetailDataModelCourses> detailList10;
    private ArrayList<DetailDataModelCourses> detailList11;

    private ArrayList<DetailDataModelCourses> detailListEnrollCourses;

    private ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> detailListCourseDetailContentss;
    private ArrayList<DetailDataModelCoursesDetailContents> detailListVideoPulse;
    private ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> detailListCourseDetailUnits;
    private ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> detailListCourseDetailUnitQuizList;
    private ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> detailListCourseDetailUnitQuizListExam;
    private ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> detailListCourseDetailUnitQuizList2;

    private ArrayList<ArrayList<ArrayList<DetailDataModelCoursesDetailContents>>> detailListCourseDetailUnitQuizOptList;
    private ArrayList<ArrayList<ArrayList<DetailDataModelCoursesDetailContents>>> detailListCourseDetailPulseQuizOptList;
    private ArrayList<ArrayList<ArrayList<DetailDataModelCoursesDetailContents>>> detailListCourseDetailVideoPulseMulti;

    //for declaring arraylist of units
    private ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> detailListCourseUnit1Data;
    private ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> detailListCourseUnit2Data;
    private ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> detailListCourseUnit3Data;
    private ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> detailListCourseUnit4Data;

    //TODO

    private ArrayList<DetailDataModelCoursesThumbnails> detailListCourseThumbnail;

    private ArrayList<DetailDataModelCoursesMarks> mPassPercentageArrayList;

    private ArrayList<DetailDataModelCourses> detailListFileType;

    ArrayList<DetailDataModelCoursesDetailContents> mContentArrayListNew;
    ArrayList<DetailDataModelCoursesDetailContents> mUnitArrayListNew;

    ArrayList<DetailDataModelCoursesDetailContents> mUnitQuizList;
    ArrayList<DetailDataModelCoursesDetailContents> mUnitQuizListExam;
    ArrayList<DetailDataModelCoursesDetailContents> mUnitQuizListMp;
    ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> mUnitQuizList2;
    ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> mPulseQuizList2;

    ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> mVideoPulseMulti;

    ArrayList<DetailDataModelCoursesDetailContents> mUnitQuizListWithOptions;

    ArrayList<DetailDataModelCoursesDetailContents> mUnitQuizListWithOptionsMp;

    //TODO
    //ArrayList<DetailDataModelCoursesDetailContents> mUnitQuizOptList;
    ArrayList<DetailDataModelCoursesDetailContents> mUnitQuizOptList;
    ArrayList<DetailDataModelCoursesDetailContents> mPulseQuizOptList;
    ArrayList<DetailDataModelCoursesDetailContents> mContentArrayListNewPulse;

    ArrayList<DetailDataModelCoursesDetailContents> mUnit1DataArrayList;
    ArrayList<DetailDataModelCoursesDetailContents> mUnit2DataArrayList;
    ArrayList<DetailDataModelCoursesDetailContents> mUnit3DataArrayList;
    ArrayList<DetailDataModelCoursesDetailContents> mUnit4DataArrayList;

    private ArrayList<DetailDataModelCoursesDetailContents> detailListUserInformation=new ArrayList<>();

    private TextView mGoToRegiPage;

    private SessionManager sm;

    private int multiQKey=-1;


    private HashMap<String,String> mapSubmit;

    //private boolean isLogin;

    private String token="";
    String url= GlobalVar.gApiBaseUrl + "/api/login";


    private TextView forgetPasswordTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.

        getSupportActionBar().hide();

        mEmailView =  findViewById(R.id.email);
        mEdtTxtPwd = findViewById(R.id.password);

        forgetPasswordTxt=findViewById(R.id.resetPwdLebelId);
        forgetPasswordTxt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(mContext,ForgetPasswordActivity.class);
                startActivity(i);
            }
        });

        mContext=this;

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        mGoToRegiPage=findViewById(R.id.goToregiPage);
        mGoToRegiPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(mContext,CreateAccountActivity.class);
                startActivity(i);
            }
        });

        mEdtTxtPwd.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {

                    return true;
                }
                return false;
            }
        });
        mEmailView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {

                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = findViewById(R.id.regiPageSignUpId);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //attemptLogin();

                /*finish();
                Intent i=new Intent(mContext,WelcomeActivity.class);
                view.getContext().startActivity(i);*/

                mStrEmail=mEmailView.getText().toString();
                mStrPwd=mEdtTxtPwd.getText().toString();

                sm = new SessionManager(mContext);

                map = new HashMap<String, String>();

                token = GlobalVar.getTokenArray.get(0).getmAccessToken();
                String firstWord = GlobalVar.getTokenArray.get(0).getmTokenType();

                token = firstWord + " " + token;

                GlobalVar.gReplacingTokenForAllCategories=token;

                mapSubmit =  new HashMap<>();

                if(mStrEmail.contains("@"))
                {
                    try {

                        if(mStrEmail.contains("@")){

                            mapSubmit.put("email", mStrEmail);
                            mapSubmit.put("password", mStrPwd);
                            mapSubmit.put("type", "1");
                        }
                        else {
                            mapSubmit.put("phone", mStrEmail);
                            mapSubmit.put("password", mStrPwd);
                            mapSubmit.put("type", "2");
                        }

                    }
                    catch (Exception ex){
                        Log.d("", "onClick: ");
                    }

                    if(mStrEmail.equalsIgnoreCase("")){

                        mEmailView.setError("You must type your email.");
                        mEmailView.requestFocus();

                        if(mStrPwd.equalsIgnoreCase("")){

                            mEdtTxtPwd.setError("You must type your password.");
                            mEdtTxtPwd.requestFocus();
                        }
                    }

                    else if(mStrEmail.equalsIgnoreCase(mStrEmail))
                    {

                        if(mStrPwd.equalsIgnoreCase("")){

                            mEdtTxtPwd.setError("You must type your password.");
                            mEdtTxtPwd.requestFocus();
                        }

                        else if(mStrPwd.equalsIgnoreCase(mStrPwd)) {

                            if (mStrEmail.contains("@"))
                            {
                                //GlobalVar.gIsLogin=sm.checkLogin();

                                if(!GlobalVar.gIsLogin) {

                                    sm.createLoginSession(mStrEmail, mStrPwd);
                                    sm.isLoggedIn();
                                }

                                new StartLogin().execute(url);
                            }
                            else {

                                if(!GlobalVar.gIsLogin) {

                                    sm.createLoginSessionForPhone(mStrEmail, mStrPwd);
                                    sm.isLoggedIn();
                                }
                            }
                        }
                        else {
                            mEmailView.setError("Invalid email.");
                            mEmailView.requestFocus();
                        }
                    }
                    else{
                        Toast.makeText(mContext, "Testing", Toast.LENGTH_LONG).show();
                    }
                }
                else {


                    try {

                        if(mStrEmail.contains("@")){

                            mapSubmit.put("email", mStrEmail);
                            mapSubmit.put("password", mStrPwd);
                            mapSubmit.put("type", "1");
                        }
                        else {
                            mapSubmit.put("phone", mStrEmail);
                            mapSubmit.put("password", mStrPwd);
                            mapSubmit.put("type", "2");
                        }

                    }
                    catch (Exception ex){
                        Log.d("", "onClick: ");
                    }

                    if(mStrEmail.equalsIgnoreCase("")){

                        mEmailView.setError("You must type your email.");
                        mEmailView.requestFocus();

                        if(mStrPwd.equalsIgnoreCase("")){

                            mEdtTxtPwd.setError("You must type your password.");
                            mEdtTxtPwd.requestFocus();
                        }
                    }

                    else if(mStrEmail.equalsIgnoreCase(mStrEmail))
                    {

                        if(mStrPwd.equalsIgnoreCase("")){

                            mEdtTxtPwd.setError("You must type your password.");
                            mEdtTxtPwd.requestFocus();
                        }

                        else if(mStrPwd.equalsIgnoreCase(mStrPwd)) {

                            if (mStrEmail.contains("@"))
                            {
                                //GlobalVar.gIsLogin=sm.checkLogin();

                                if(!GlobalVar.gIsLogin) {

                                    sm.createLoginSession(mStrEmail, mStrPwd);
                                    sm.isLoggedIn();
                                }

                                new StartLogin().execute(url);
                            }
                            else {

                                if(!GlobalVar.gIsLogin) {

                                    sm.createLoginSessionForPhone(mStrEmail, mStrPwd);
                                    sm.isLoggedIn();
                                }

                                new StartLogin().execute(url);
                            }
                        }
                        else {
                            mEmailView.setError("Invalid email.");
                            mEmailView.requestFocus();
                        }
                    }
                    else{
                        Toast.makeText(mContext, "Testing", Toast.LENGTH_LONG).show();
                    }

                    //mEmailView.setError("Invalid email");
                }



            }
        });


    }


    public class StartLogin extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mProgressDialog=new ProgressDialog(mContext);
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setMessage("Please wait...");
            mProgressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            String data = performPostCall(params[0], mapSubmit);

            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);


            if(result!=""){

                String token="";

                try {
                    JSONObject jObject = new JSONObject(result);

                    try {

                        GlobalVar.gRecommendedCategories= new ArrayList<>();

                        JSONObject jObjectData = jObject.getJSONObject("data");

                        token = jObjectData.getString("token");
                        JSONArray objectEnrollCourse =  jObjectData.getJSONArray("EnrollCourse");

                        JSONArray favoriteCourseArray =  jObjectData.getJSONArray("FavoriteCategoryList");

                        for(int favid=0; favid<favoriteCourseArray.length(); favid++){

                            String ids=favoriteCourseArray.getString(favid);

                            GlobalVar.gRecommendedCategories.add(ids);
                        }

                        GlobalVar.gTokenForSelectCatId=token;

                        int favoriteCourseNumbers = favoriteCourseArray.length();
                    }
                    catch (Exception ex) {
                        Log.d("", "onResponse: ");
                    }

                    String aaaaa="";
                }
                catch (Exception ex){
                    Log.d("", "onPostExecute: ");
                }

                mProgressDialog.dismiss();

                if(GlobalVar.gRecommendedCategories.size()>1) {

                    Intent i = new Intent(mContext, MyPageActivity.class);
                    startActivity(i);
                }
                else {

                    Intent i = new Intent(mContext, SelectACategoryActivity.class);
                    startActivity(i);
                }

            }
            else {
                mEdtTxtPwd.setError("Wrong email or password");
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
            conn.setRequestProperty ("Authorization", token);
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
                if(mStrEmail.contains("@")){
                    String message="আপনার ই-মেইল চেক করুন এবং অ্যাকাউন্ট সক্রিয় করুন।";

                    Intent i=new Intent(mContext,EmailRegiCompleteActivity.class);

                    i.putExtra("msg", message);

                    startActivity(i);
                }
                else{
                    String message="আপনার ফোন চেক করুন এবং অ্যাকাউন্ট সক্রিয় করুন।";

                    Intent i=new Intent(mContext,VerifyAccountActivity.class);

                    i.putExtra("msg", message);

                    startActivity(i);
                }
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



/*if(emailFromCache!=null) {

        Intent i = new Intent(mContext, EmailRegiCompleteActivity.class);
        startActivity(i);
        }
        else{

        Intent i = new Intent(mContext, VerifyAccountActivity.class);
        i.putExtra("phn", phoneFromChache);
        startActivity(i);
        }*/

