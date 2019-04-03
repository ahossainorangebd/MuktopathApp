package orangebd.newaspaper.mymuktopathapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

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

public class WelcomeActivity extends AppCompatActivity {

    private Context mContext;

    private Handler mHandler = new Handler();

    private HashMap<String,String> map;

    private ArrayList<DetailDataModelAll> detailList;

    SessionManager sm;

    //private boolean isLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        mContext=this;

        getSupportActionBar().hide();

        View view2 = findViewById(android.R.id.content);
        Animation mLoadAnimation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in);
        mLoadAnimation.setDuration(2000);
        view2.startAnimation(mLoadAnimation);

        sm= new SessionManager(mContext);

        map = new HashMap<String, String>();

        map.put("grant_type", "client_credentials");
        map.put("client_id", "1");
        map.put("client_secret", "kb4wGS6M3TKWfRRuZOeh0ZfGtDXE8L1N7htXTDub");

        new getTokenInfo().execute(GlobalVar.gApiBaseUrl +"/oauth/token");
    }

    public class getTokenInfo extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            String data= performPostCall(params[0],map);

            return data;
        }



        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);

            String tokenName = result;
            String mT = tokenName;

            try {

                JSONObject jsonObj = new JSONObject(tokenName);

                detailList = new ArrayList<DetailDataModelAll>();

                DetailDataModelAll model = new DetailDataModelAll();

                //for User data
                String aToken = jsonObj.getString("access_token");
                String expireIn = jsonObj.getString("expires_in");
                String tType = jsonObj.getString("token_type");

                model.setmAccessToken(aToken);
                model.setmExpiresIn(expireIn);
                model.setmTokenType(tType);

                //for Login success & data

                detailList.add(model);


                GlobalVar.getTokenArray = detailList;

                String token = GlobalVar.getTokenArray.get(0).getmAccessToken();
                String firstWord = GlobalVar.getTokenArray.get(0).getmTokenType();

                token = firstWord + " " + token;

                //GlobalVar.gReplacingTokenForRegi=token;
            }

            catch (Exception ex){
                Log.d("", "onPostExecute: ");
            }

            GlobalVar.gIsLogin=sm.checkLogin();

            if(GlobalVar.gIsLogin==true) {

                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(mContext, MyPageActivity.class);
                        startActivity(i);
                    }
                }, 2000);

            }
            else {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(mContext, SplashActivity.class);
                        startActivity(i);
                    }
                }, 2000);
            }

        }

        @Override
        protected void onCancelled() {
            //mAuthTask = null;
            //showProgress(false);
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
