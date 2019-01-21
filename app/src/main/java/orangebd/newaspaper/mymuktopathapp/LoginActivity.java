package orangebd.newaspaper.mymuktopathapp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import java.util.List;
import java.util.Map;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {
    
    private static final int REQUEST_READ_CONTACTS = 0;
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };

    private Context mContext;

    private Button mRegiPageSignUpBtn;

    private HashMap<String, String> map;

    private EditText mEmailView;
    private String mStrEmail;

    private EditText mEdtTxtPwd;
    private String mStrPwd;

    private ArrayList<DetailDataModelAll> detailList=new ArrayList<>();

    private String token="";
    String url="http://api.muktopaath.orangebd.com/api/login";
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.

        getSupportActionBar().hide();

        mEmailView =  findViewById(R.id.email);
        mEdtTxtPwd=findViewById(R.id.password);

        mContext=this;

        mRegiPageSignUpBtn=findViewById(R.id.regiPageSignUpId);

        mRegiPageSignUpBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(mContext,WelcomeActivity.class);
                v.getContext().startActivity(i);
            }
        });

        Button mEmailSignInButton = findViewById(R.id.regiPageSignUpId);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //attemptLogin();                
                
                
                /*finish();
                Intent i=new Intent(mContext,WelcomeActivity.class);
                view.getContext().startActivity(i);*/


                mStrEmail=mEmailView.getText().toString();
                mStrPwd=mEdtTxtPwd.getText().toString();

                map = new HashMap<String, String>();



                token = GlobalVar.getTokenArray.get(0).getmAccessToken();
                String firstWord = GlobalVar.getTokenArray.get(0).getmTokenType();

                token = firstWord + " " + token;

                /*
                map.put("Authorization",token);
                new GetLogin().execute("http://api.muktopaath.orangebd.com/api/login");
                */
                map.put("email",mStrEmail);
                map.put("password",mStrPwd);
                JSONObject object=new JSONObject();
                try {
                    object.put("email", mStrEmail);
                    object.put("password", mStrPwd);
                }
                catch (Exception ex){}

                RequestQueue mQueue = Volley.newRequestQueue(getApplicationContext());


                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, object,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d("TAG", response.toString());
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("TAG", error.getMessage(), error);
                    }
                }) { //no semicolon or coma
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("Content-Type", "application/json");
                        params.put("Authorization", token);
                        return params;
                    }
                };
                mQueue.add(jsonObjectRequest);

            }
        });
    }

    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(mEmailView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });
        }
        else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }


    public class GetLogin extends AsyncTask<String, Void, String>
    {
        @Override
        protected String doInBackground(String... params) {

            String data= performPostCall(params[0],map);

            if (data != null) try {

                JSONObject jsonObj = new JSONObject(data);
                detailList=new ArrayList<DetailDataModelAll>();

                DetailDataModelAll model = new DetailDataModelAll();

                    try {
                        for (int i=0;i<jsonObj.length()-1;i++)
                        {
                            JSONArray object = (JSONArray) jsonObj.get("data");
                            JSONObject object2 = (JSONObject) object.get(0);

                            int length=object.length();

                            //for User data
                            String aToken = object2.getString("access_token");
                            String expireIn = object2.getString("expires_in");
                            String tType = object2.getString("token_type");

                            //GlobalVar.gName=Ename;

                            model.setmAccessToken(aToken);
                            model.setmExpiresIn(expireIn);
                            model.setmTokenType(tType);

                            //for Login success & data

                            detailList.add(model);
                            publishProgress();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
            }
            catch (final JSONException e) {
                Log.e("tag", "Couldn't get json from server.");
            }
            else {
                Log.e("tag", "Couldn't get json from server.");
            }

            if (data.equalsIgnoreCase("ok"))
                return "success";
            else
                return "";

        }

        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);

            //GlobalVar.gArrayFromLoginPage=detailList;

            //TODO
            //progressDialog.dismiss();

            if (detailList.size()>0) {

                String mAccessToken= detailList.get(0).getmAccessToken();
                String mExpireIn= detailList.get(0).getmExpiresIn();
                String mTokenType= detailList.get(0).getmTokenType();

                return;
            }

            else {
                mEmailView.setError("Your email or password is incorrect");
                mEmailView.requestFocus();

                return;
            }
        }

        @Override
        protected void onCancelled() { }

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

