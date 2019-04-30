package orangebd.newaspaper.mymuktopathapp;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
import java.util.HashMap;
import java.util.Map;

/**
 * A login screen that offers login via email/password.
 */
public class CreateAccountActivityTemp extends AppCompatActivity{



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

    private EditText mEdtTxtPwdConfirm;
    private String mStrPwdConfirm;

    private EditText mEdtTxtProfession;
    private String mStrProfession;

    private EditText mEdtGender;
    private String mStrGender;

    private EditText mEdtTxtFullName;
    private String mStrFullName;

    private ArrayList<DetailDataModelCourses> detailList=new ArrayList<>();
    private ArrayList<DetailDataModelCourses> detailList2=new ArrayList<>();
    private ArrayList<DetailDataModelCourses> detailList3=new ArrayList<>();
    private ArrayList<DetailDataModelCourses> detailList4=new ArrayList<>();

    private ArrayList<DetailDataModelCourses> detailListAnoPart1=new ArrayList<>();
    private ArrayList<DetailDataModelCourses> detailListAnoPart2=new ArrayList<>();
    private ArrayList<DetailDataModelCourses> detailListAnoPart3=new ArrayList<>();

    String url= GlobalVar.gApiBaseUrl + "/api/registration?type=registration";

    String basicInfourl= GlobalVar.gApiBaseUrl + "/api/basic-info";

    private String token="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createaccount);

        getSupportActionBar().hide();

        mEmailView =  findViewById(R.id.email);
        mEdtTxtPwd = findViewById(R.id.password);
        //mEdtTxtProfession = findViewById(R.id.profession);
        mEdtTxtFullName = findViewById(R.id.fullname);
        mEdtTxtPwdConfirm = findViewById(R.id.confirm_password);

        mContext=this;

        Button mEmailSignInButton = findViewById(R.id.regiPageSignUpId);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                mStrEmail=mEmailView.getText().toString();
                mStrPwd=mEdtTxtPwd.getText().toString();
                mStrPwdConfirm=mEdtTxtPwd.getText().toString();
                mStrProfession=mEdtTxtProfession.getText().toString();
                mStrGender=mEdtGender.getText().toString();
                mStrFullName=mEdtTxtFullName.getText().toString();

                token = GlobalVar.getTokenArray.get(0).getmAccessToken();
                String firstWord = GlobalVar.getTokenArray.get(0).getmTokenType();

                token = firstWord + " " + token;

                map = new HashMap<String, String>();

                map.put("email", mStrEmail);
                map.put("password", mStrPwd);
                map.put("confirm_password", mStrPwdConfirm);
                map.put("name", mStrFullName);
                map.put("gender", mStrGender);
                map.put("profession", mStrProfession);

                new CreateAccount().execute(url);
            }
        });
    }

    public class CreateAccount extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            /*mProgressSpinner10.setIndeterminate(true);
            mProgressSpinner10.setVisibility(View.VISIBLE);*/
        }

        @Override
        protected String doInBackground(String... params) {

            String data= performPostCall(params[0],map);

            return data;
        }

        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);

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

