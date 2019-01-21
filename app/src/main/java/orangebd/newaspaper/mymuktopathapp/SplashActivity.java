package orangebd.newaspaper.mymuktopathapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

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

public class SplashActivity extends AppCompatActivity {

    private Button splashActvtyCreateAccountBtn;
    private TextView slpashActLoginTxt;

    private Context mContext;


    private HashMap<String,String> map;

    private ArrayList<DetailDataModelAll> detailList;

    private String mStrEmail;
    private String mStrpwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mContext=this;

        getSupportActionBar().hide();

        map = new HashMap<String, String>();

        map.put("grant_type", "client_credentials");
        map.put("client_id", "1");
        map.put("client_secret", "kb4wGS6M3TKWfRRuZOeh0ZfGtDXE8L1N7htXTDub");

        splashActvtyCreateAccountBtn=findViewById(R.id.splashActvtyCreateAccountId);
        splashActvtyCreateAccountBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent i=new Intent(mContext,CreateAccountActivity.class);
                v.getContext().startActivity(i);
            }
        });

        slpashActLoginTxt=findViewById(R.id.slpashActLoginId);
        slpashActLoginTxt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent i=new Intent(mContext,LoginActivity.class);
                v.getContext().startActivity(i);
            }
        });

        new getTokenInfo().execute("http://api.muktopaath.orangebd.com/oauth/token");

    }

    public class getTokenInfo extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            String data= performPostCall(params[0],map);

            /*if (data != null) try {

                JSONObject jsonObj = new JSONObject(data);
                detailList=new ArrayList<DetailDataModelAll>();

                DetailDataModelAll model = new DetailDataModelAll();

                try {
                    for (int i=0;i<jsonObj.length()-1;i++)
                    {

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
                return "";*/

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

               // JSONObject object = (JSONObject) jsonObj.get(""+0);

                //for User data
                String aToken = jsonObj.getString("access_token");
                String expireIn = jsonObj.getString("expires_in");
                String tType = jsonObj.getString("token_type");

                //GlobalVar.gName=Ename;

                model.setmAccessToken(aToken);
                model.setmExpiresIn(expireIn);
                model.setmTokenType(tType);

                //for Login success & data

                detailList.add(model);


                GlobalVar.getTokenArray = detailList;
            }

            catch (Exception ex){
            Log.d("", "onPostExecute: ");
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