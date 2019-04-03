package orangebd.newaspaper.mymuktopathapp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.Manifest.permission.READ_CONTACTS;

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
    private String IdOfSelectedItem="";

    String url= GlobalVar.gApiBaseUrl + "/api/registration?type=registration";

    String basicInfourl= GlobalVar.gApiBaseUrl + "/api/basic-info";

    private String token="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createaccount);

        mContext=this;

        getSupportActionBar().hide();

        mEmailView =  findViewById(R.id.email);
        mEdtTxtPwd = findViewById(R.id.password);
        //mEdtTxtProfession = findViewById(R.id.profession);
        mEdtGender = findViewById(R.id.gender);
        mEdtTxtFullName = findViewById(R.id.fullname);
        mEdtTxtPwdConfirm = findViewById(R.id.confirm_password);
        final Spinner mSpinnerProfCat=findViewById(R.id.spnrProfessionCategory);

        mContext=this;

        new GetBasicData().execute(basicInfourl);

        token = GlobalVar.getTokenArray.get(0).getmAccessToken();
        String firstWord = GlobalVar.getTokenArray.get(0).getmTokenType();

        token = firstWord + " " + token;

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

                JSONObject object=new JSONObject();

                try {
                    object.put("email", mStrEmail);
                    object.put("password", mStrPwd);
                    object.put("confirm_password", mStrPwdConfirm);
                    object.put("name", mStrFullName);
                    object.put("gender", mStrGender);
                    object.put("profession", IdOfSelectedItem);
                }
                catch (Exception ex){ }

                RequestQueue mQueue = Volley.newRequestQueue(getApplicationContext());

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, object,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response)
                            {
                                JSONObject jObject = new JSONObject();

                                String abcd="aranya";
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("TAG", error.getMessage(), error);
                    }
                })
                {
                    //no semicolon or coma
                    @Override
                    public Map<String, String> getHeaders() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("Content-Type", "application/json");
                        params.put("Authorization", token);
                        return params;
                    }
                };
                mQueue.add(jsonObjectRequest);

                /*Intent i=new Intent(mContext,LoginActivity.class);
                startActivity(i);*/
            }
        });

        final List<String> ccCat=new ArrayList<String>();

        try {
            ccCat.add("পেশা");
            ccCat.add(detailListProfessions.get(0).getmProfessionTitleBn());
            ccCat.add(detailListProfessions.get(1).getmProfessionTitleBn());
            ccCat.add(detailListProfessions.get(2).getmProfessionTitleBn());
            ccCat.add(detailListProfessions.get(3).getmProfessionTitleBn());
            ccCat.add(detailListProfessions.get(4).getmProfessionTitleBn());
            ccCat.add(detailListProfessions.get(5).getmProfessionTitleBn());
            ccCat.add(detailListProfessions.get(6).getmProfessionTitleBn());
            ccCat.add(detailListProfessions.get(7).getmProfessionTitleBn());
            ccCat.add(detailListProfessions.get(8).getmProfessionTitleBn());
            ccCat.add(detailListProfessions.get(9).getmProfessionTitleBn());
            ccCat.add(detailListProfessions.get(10).getmProfessionTitleBn());
            ccCat.add(detailListProfessions.get(11).getmProfessionTitleBn());
            ccCat.add(detailListProfessions.get(12).getmProfessionTitleBn());
            ccCat.add(detailListProfessions.get(13).getmProfessionTitleBn());
            ccCat.add(detailListProfessions.get(14).getmProfessionTitleBn());
            ccCat.add(detailListProfessions.get(15).getmProfessionTitleBn());
            ccCat.add(detailListProfessions.get(16).getmProfessionTitleBn());
            ccCat.add(detailListProfessions.get(17).getmProfessionTitleBn());
        }
        catch (Exception ex){
            Log.d("", "onCreate: ");
        }

        ArrayAdapter<String> spinnerArrayAdapterCCCat = new ArrayAdapter<>(mContext, R.layout.spinner_item_prof_info, ccCat);
        spinnerArrayAdapterCCCat.setDropDownViewResource(R.layout.spinner_item);
        mSpinnerProfCat.setAdapter(spinnerArrayAdapterCCCat);

        mSpinnerProfCat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                int item=position;
                selectedItem  =ccCat.get(item);

                for(int si=0; si<detailListProfessions.size(); si++) {

                    DetailDataModelBasicInfo idNameOfSelectedItem = detailListProfessions.get(si);
                    String matchName=idNameOfSelectedItem.getmProfessionTitleBn();

                    if (selectedItem.equalsIgnoreCase(matchName)){
                        IdOfSelectedItem=idNameOfSelectedItem.getmProfessionId();
                    }
                }
                String breakpoint="gradle";
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

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
        }
    }


}

