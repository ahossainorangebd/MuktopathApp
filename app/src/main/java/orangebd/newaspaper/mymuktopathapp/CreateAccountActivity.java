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
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
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
    private String token="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createaccount);

        getSupportActionBar().hide();

        mEmailView =  findViewById(R.id.email);
        mEdtTxtPwd = findViewById(R.id.password);
        mEdtTxtProfession = findViewById(R.id.profession);
        mEdtGender = findViewById(R.id.gender);
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

                JSONObject object=new JSONObject();

                try {
                    object.put("email", mStrEmail);
                    object.put("password", mStrPwd);
                    object.put("confirm_password", mStrPwdConfirm);
                    object.put("name", mStrFullName);
                    object.put("gender", mStrGender);
                    object.put("profession", mStrProfession);
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
                }) { //no semicolon or coma
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
    }
}

