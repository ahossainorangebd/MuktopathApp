package orangebd.newaspaper.mymuktopathapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
import java.util.HashMap;
import java.util.Map;

public class ChangePasswordRetypeActivity extends AppCompatActivity {

    private HashMap<String,String> mapSubmit;

    private Context context;

    private String pwdStr;
    private String pwdStrRe;


    String urlVerificationCodeSubmit = GlobalVar.gApiBaseUrl +"/api/forgot-password/Check";

    private Button mSubmitBtn;

    private EditText mPwdEdtTxt;
    private EditText mRePwdEdtTxt;

    private String email;

    private String token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password_retype);

        context=this;

        mSubmitBtn=findViewById(R.id.submitBtn);

        mPwdEdtTxt = findViewById(R.id.typepass);
        mRePwdEdtTxt = findViewById(R.id.repassedt);

        email=this.getIntent().getExtras().getString("email");
        token=this.getIntent().getExtras().getString("token");

        String abcd="";


        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pwdStr = mPwdEdtTxt.getText().toString();
                pwdStrRe = mRePwdEdtTxt.getText().toString();


                mapSubmit =  new HashMap<>();

                mapSubmit.put("email_or_phone", email);
                mapSubmit.put("token", token);
                mapSubmit.put("password", pwdStr);
                mapSubmit.put("confirm_password", pwdStrRe);
                mapSubmit.put("type", "3");

                new StartSubmit().execute(urlVerificationCodeSubmit);
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

            String acdsd="";

            try {
                JSONObject jObject = new JSONObject(result);

                //Toast.makeText(context,result,Toast.LENGTH_LONG).show();

                if(result!=null){
                    Intent i=new Intent(context,LoginActivity.class);

                    startActivity(i);
                }

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
            conn.setRequestProperty ("Authorization", GlobalVar.gWelcomeTokenForVerify);
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
