package orangebd.newaspaper.mymuktopathapp;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

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
import java.util.List;
import java.util.Map;

/**
 * A login screen that offers login via email/password.
 */
public class CreateAccountActivity extends AppCompatActivity{

    private static final int REQUEST_READ_CONTACTS = 0;
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };

    private Context mContext;

    private Button mRegiPageSignUpBtn;

    private ArrayList<DetailDataModelBasicInfo> detailListProfessions =new ArrayList<>();
    private ArrayList<DetailDataModelBasicInfo> detailListGender =new ArrayList<>();

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

    private String selectedItem;
    private String selectedItemG;
    private String IdOfSelectedItem="";
    private String IdOfSelectedItemG="";

    String url= GlobalVar.gApiBaseUrl + "/api/registration?type=registration";

    String basicInfourl= GlobalVar.gApiBaseUrl + "/api/basic-info";

    private String token="";

    private List<String> ccCat=new ArrayList<String>();
    private List<String> ccCat2=new ArrayList<String>();
    private Spinner mSpinnerProfCat;
    private Spinner mSpinnerGendCat;

    private TextView mReturnMessage;


    private HashMap<String,String> mapRegi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createaccount);

        mContext=this;

        getSupportActionBar().hide();

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        mEmailView =  findViewById(R.id.email);
        mEdtTxtPwd = findViewById(R.id.password);
        mEdtTxtFullName = findViewById(R.id.fullname);
        mEdtTxtPwdConfirm = findViewById(R.id.confirm_password);



        token = GlobalVar.getTokenArray.get(0).getmAccessToken();
        String firstWord = GlobalVar.getTokenArray.get(0).getmTokenType();

        token = firstWord + " " + token;



        new GetBasicData().execute(basicInfourl);

        Button mEmailSignInButton = findViewById(R.id.regiPageSignUpId);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mStrEmail=mEmailView.getText().toString();
                mStrPwd=mEdtTxtPwd.getText().toString();
                mStrPwdConfirm=mEdtTxtPwd.getText().toString();
                //mStrProfession=mEdtTxtProfession.getText().toString();
                mStrFullName=mEdtTxtFullName.getText().toString();

                if(mStrPwd.length() < 8) {
                    mEdtTxtPwd.setError("Password length must be at least 8");
                }
                else{

                    mapRegi =  new HashMap<>();

                    try {

                        if(mStrEmail.contains("@")) {
                            mapRegi.put("email", mStrEmail);
                            mapRegi.put("password", mStrPwd);
                            mapRegi.put("confirm_password", mStrPwdConfirm);
                            mapRegi.put("name", mStrFullName);
                            mapRegi.put("gender", mStrGender);
                            mapRegi.put("profession", IdOfSelectedItem);
                        }
                        else {
                            mapRegi.put("phone", mStrEmail);
                            mapRegi.put("password", mStrPwd);
                            mapRegi.put("confirm_password", mStrPwdConfirm);
                            mapRegi.put("name", mStrFullName);
                            mapRegi.put("gender", mStrGender);
                            mapRegi.put("profession", IdOfSelectedItem);
                        }

                        new StartRegi().execute(url);
                    }
                    catch (Exception ex){ }


                }
            }
        });

        mSpinnerProfCat=findViewById(R.id.spnrProfessionCategory);
        mSpinnerGendCat=findViewById(R.id.spnrGenderCategory);

    }

    public String  performPostCall(String requestURL, HashMap<String, String> postDataParams) {

        URL url;
        String response = "";

        try {
            url = new URL(requestURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
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


    public class GetBasicData extends AsyncTask<String, Void, Void>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(String... arg0) {
            HttpHandler sh = new HttpHandler();

            String jsonStr = sh.makeServiceCallBasicInfo(basicInfourl, token);

            try {
                JSONObject totalJsonObj = new JSONObject(jsonStr);

                JSONObject jObjectData = totalJsonObj.getJSONObject("data");

                JSONArray objectProfessions = (JSONArray) jObjectData.getJSONArray("professions");

                int allProfessionNumbers=objectProfessions.length();

                detailListProfessions =new ArrayList<>();

                ArrayList<String> spinnerItemArray= new ArrayList<>();

                try {
                    for (int ap = 0; ap < objectProfessions.length(); ap++)
                    {
                        DetailDataModelBasicInfo modelBasicInfo= new DetailDataModelBasicInfo();

                        JSONObject jObjProfessions = objectProfessions.getJSONObject(ap);

                        String professionTitleBn=jObjProfessions.getString("bn_title");
                        String professionId=jObjProfessions.getString("id");

                        modelBasicInfo.setmProfessionTitleBn(professionTitleBn);
                        modelBasicInfo.setmProfessionId(professionId);

                        detailListProfessions.add(modelBasicInfo);
                    }
                } catch (Exception ex) {
                    Log.d("", "onResponse: ");
                }

            }
            catch (Exception ex){
                Log.d("", "doInBackground: ");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            try {
                ccCat.add("পেশা");

                for(int nthProfTtl=0; nthProfTtl<detailListProfessions.size(); nthProfTtl++){
                    ccCat.add(detailListProfessions.get(nthProfTtl).getmProfessionTitleBn());

                    String qwwret="";
                }
            }
            catch (Exception ex){
                Log.d("", "onCreate: ");
            }


            ArrayAdapter<String> spinnerArrayAdapterCCCat = new ArrayAdapter<>(mContext, R.layout.spinner_item_prof_info, ccCat);
            spinnerArrayAdapterCCCat.setDropDownViewResource(R.layout.spinner_item);
            mSpinnerProfCat.setAdapter(spinnerArrayAdapterCCCat);

            mSpinnerProfCat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    int item=position;
                    selectedItem  =ccCat.get(item);

                    for(int si=0; si<detailListProfessions.size(); si++) {

                        DetailDataModelBasicInfo idNameOfSelectedItem = detailListProfessions.get(si);
                        String matchName=idNameOfSelectedItem.getmProfessionTitleBn();

                        if (selectedItem.equalsIgnoreCase(matchName)){
                            IdOfSelectedItem=idNameOfSelectedItem.getmProfessionId();
                        }
                    }


                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


            ccCat2.add("লিঙ্গ নির্ধারণ");
            ccCat2.add("পুরুষ");
            ccCat2.add("মহিলা");
            ccCat2.add("অন্যান্য");

            ArrayAdapter<String> spinnerArrayAdapterCCCat2 = new ArrayAdapter<>(mContext, R.layout.spinner_item_prof_info, ccCat2);
            spinnerArrayAdapterCCCat2.setDropDownViewResource(R.layout.spinner_item);
            mSpinnerGendCat.setAdapter(spinnerArrayAdapterCCCat2);

            mSpinnerGendCat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    int item=position;
                    selectedItemG  =ccCat2.get(item);


                    if(selectedItemG.equalsIgnoreCase("পুরুষ")){
                        mStrGender="1";
                    }
                    else if(selectedItemG.equalsIgnoreCase("মহিলা")){
                        mStrGender="2";
                    }
                    else {
                        mStrGender="3";
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }
    }


    public class StartRegi extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected String doInBackground(String... params) {

            String data = performPostCallPost(params[0], mapRegi);

            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);


            if(result!=""){

                try {
                    JSONObject jObject = new JSONObject(result);


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
                        i.putExtra("phn", mStrEmail);

                        startActivity(i);
                    }

                }
                catch (Exception ex){
                    Log.d("", "onPostExecute: ");
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

    public String  performPostCallPost(String requestURL, HashMap<String, String> postDataParams) {

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

                if(responseCode==400){

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

        }
        catch (Exception e) {

            e.printStackTrace();
        }

        return response;
    }
}



    /*String message="";
    String type="";

                                    try {
                                            JSONObject jObjectData = response.getJSONObject("success");

                                            message=jObjectData.getString("message");
                                            type=jObjectData.getString("type");
                                            }
                                            catch (Exception ex) {
                                            Log.d("", "onResponse: ");
                                            }

                                            Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();

                                            if(type.equalsIgnoreCase("2")){
                                            Intent i=new Intent(mContext,VerifyAccountActivity.class);


        i.putExtra("msg", message);
        i.putExtra("phn", mStrEmail);

        startActivity(i);
        }
        else {
        Intent i=new Intent(mContext,EmailRegiCompleteActivity.class);

        i.putExtra("msg", message);

        startActivity(i);
        }*/

