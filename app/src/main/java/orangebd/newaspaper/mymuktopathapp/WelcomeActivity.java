package orangebd.newaspaper.mymuktopathapp;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

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

    private Dialog alertbox;

    //private boolean isLogin;

    public final String[] EXTERNAL_PERMS = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE
    };

    public final int EXTERNAL_REQUEST = 138;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        mContext=this;

        getSupportActionBar().hide();

        View view2 = findViewById(android.R.id.content);
        Animation mLoadAnimation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in);
        mLoadAnimation.setDuration(1000);
        view2.startAnimation(mLoadAnimation);


        //Let's check the version code and redirect to playstore

        int versionCode = BuildConfig.VERSION_CODE;
        String versionName = BuildConfig.VERSION_NAME;
        String prevVersion="1.0";

        if(versionName.equalsIgnoreCase(prevVersion)) {

            String abcd="";

        }
        else {
            final String appPackageName = getPackageName();
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
            }
            catch (android.content.ActivityNotFoundException anfe) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
            }
        }

        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

        boolean isData = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();
        boolean isWifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();

        if (!isData && !isWifi) {

            Toast.makeText(mContext,"Your Network Connection is Off, Please check your Network Conncetion.", Toast.LENGTH_LONG).show();

            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //finish();

                    Intent i = new Intent(mContext, DownloadActivity.class);
                    startActivity(i);
                }
            }, 4000);




        }
        else {
            sm= new SessionManager(mContext);

            map = new HashMap<String, String>();

            map.put("grant_type", "client_credentials");
            map.put("client_id", "1");
            map.put("client_secret", "kb4wGS6M3TKWfRRuZOeh0ZfGtDXE8L1N7htXTDub");

            new getTokenInfo().execute(GlobalVar.gApiBaseUrl +"/oauth/token");
        }


        Intent startIntent = getIntent();

        if (startIntent.hasExtra("content"))
        {


            GlobalVar.isNotificationSent = true;

            String msg = startIntent.getExtras().getString("content");

            GlobalVar.gData = msg;

            if (alertbox == null) {
                ShowPushNotification();
            }
            else {
                GlobalVar.isNotificationSent = false;
            }
        }



        requestForPermission();

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
                }, 4000);

            }
            else {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if(!sm.isLoggedIn()) {

                        }
                    }
                }, 4000);
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

    private void ShowPushNotification() {

        final AlertDialog.Builder builder1 = new AlertDialog.Builder(this);

        builder1.setTitle("মাত্র পাওয়া");
        builder1.setMessage(GlobalVar.gData);
        builder1.setCancelable(true);
        builder1.setPositiveButton(

                "ওকে",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        GlobalVar.isNotificationSent=false;

                        dialog.dismiss();

                    }
                });

        AlertDialog alert11 = builder1.create();

        try{
            alert11.show();
        }
        catch (Exception e){
            Log.d("", "Something is : ");
        }
    }


    public boolean requestForPermission() {

        boolean isPermissionOn = true;
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23) {
            if (!canAccessExternalSd()) {
                isPermissionOn = false;
                requestPermissions(EXTERNAL_PERMS, EXTERNAL_REQUEST);
            }
        }

        return isPermissionOn;
    }

    public boolean canAccessExternalSd() {
        return (hasPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE));
    }

    private boolean hasPermission(String perm) {
        return (PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this, perm));

    }


}
