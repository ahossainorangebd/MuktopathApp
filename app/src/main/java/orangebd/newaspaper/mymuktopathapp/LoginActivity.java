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

    private ArrayList<DetailDataModelUserData> detailList=new ArrayList<>();
    private ArrayList<DetailDataModelUserData> detailList2=new ArrayList<>();
    private ArrayList<DetailDataModelUserData> detailList3=new ArrayList<>();
    private ArrayList<DetailDataModelUserData> detailList4=new ArrayList<>();

    private ArrayList<DetailDataModelUserData> detailListAnoPart1=new ArrayList<>();
    private ArrayList<DetailDataModelUserData> detailListAnoPart2=new ArrayList<>();
    private ArrayList<DetailDataModelUserData> detailListAnoPart3=new ArrayList<>();

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
        mEdtTxtPwd = findViewById(R.id.password);

        mContext=this;

        /*mRegiPageSignUpBtn=findViewById(R.id.regiPageSignUpId);

        mRegiPageSignUpBtn.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v) {

            }
        });*/

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

                map = new HashMap<String, String>();


                token = GlobalVar.getTokenArray.get(0).getmAccessToken();
                String firstWord = GlobalVar.getTokenArray.get(0).getmTokenType();

                token = firstWord + " " + token;

                JSONObject object=new JSONObject();

                try {
                    object.put("email", mStrEmail);
                    object.put("password", mStrPwd);
                }
                catch (Exception ex){ }

                RequestQueue mQueue = Volley.newRequestQueue(getApplicationContext());

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, object,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response)
                            {
                                detailList=new ArrayList<DetailDataModelUserData>();

                                DetailDataModelUserData model = new DetailDataModelUserData();

                                JSONObject jObject = new JSONObject();

                                try {
                                    jObject = response.getJSONObject("data");
                                }
                                catch (Exception ex){
                                    Log.d("", "onResponse: ");
                                }

                                // For parsing simple JSON

                                    try {
                                        for (int i=0;i<jObject.length()-1;i++)
                                        {

                                            String id = jObject.getString("id");
                                            String username = jObject.getString("username");
                                            String email = jObject.getString("email");
                                            String Completeness = jObject.getString("completeness");
                                            String name = jObject.getString("name");

                                            String lastLoginIpAddress = jObject.getString("last_login_ip_address");
                                            String last_login_time = jObject.getString("last_login_time");
                                            String login_status = jObject.getString("login_status");
                                            //String password = jObject.getString("password");
                                            String phone = jObject.getString("phone");
                                            String status = jObject.getString("status");
                                            String token = jObject.getString("token");
                                            String coursecompleted = jObject.getString("CourseCompleted");
                                            String totalEnrollment = jObject.getString("TotalEnrollment");

                                            GlobalVar.gName = name;
                                            GlobalVar.gMobile=phone;
                                            GlobalVar.gEmail=email;
                                            GlobalVar.gReplacingToken=token;

                                            model.setmId(id);
                                            model.setmUserName(username);
                                            model.setmName(name);
                                            model.setmUserEmail(email);
                                            //model.setmUserPassword(password);
                                            model.setmCompletenesss(Completeness);
                                            model.setmCourseCompleted(coursecompleted);
                                            model.setmTotalEnrollment(totalEnrollment);
                                            model.setmLastLoginIpAddress(lastLoginIpAddress);
                                            model.setmLastLoginTime(last_login_time);
                                            model.setmToken(token);
                                            model.setmStatus(status);
                                            model.setmLoginStatus(login_status);
                                            model.setmUserPhone(phone);

                                            detailList.add(model);

                                            // For parsing 1st Array of info JSON

                                            detailList2=new ArrayList<DetailDataModelUserData>();

                                            DetailDataModelUserData model2 = new DetailDataModelUserData();

                                            try {
                                                for (int ii=0;ii<jObject.length()-1;ii++)
                                                {
                                                    JSONArray object = (JSONArray) jObject.get("owninstitution");
                                                    JSONObject object2 = (JSONObject) object.get(ii);

                                                    String instaddress = object2.getString("address");
                                                    String instcontacts = object2.getString("contacts");
                                                    String instcontact_person = object2.getString("contact_person");
                                                    String instcontact_person_email = object2.getString("contact_person_email");
                                                    String instcontact_person_mobile = object2.getString("contact_person_mobile");

                                                    String instcreated_at = object2.getString("created_at");
                                                    String instcreated_by = object2.getString("created_by");
                                                    String instcredit = object2.getString("credit");
                                                    String instdeleted_at = object2.getString("deleted_at");
                                                    String instemail = object2.getString("email");
                                                    String instgoogle_location = object2.getString("google_location");
                                                    String instheading = object2.getString("heading");
                                                    String instid = object2.getString("id");
                                                    String initial = object2.getString("initial");
                                                    String institution_name = object2.getString("institution_name");
                                                    String institution_type = object2.getString("institution_type");
                                                    String instlegal_document_file = object2.getString("legal_document_file");
                                                    String instmetadata = object2.getString("metadata");
                                                    String instphone = object2.getString("phone");
                                                    String instsocial = object2.getString("social");
                                                    String inststatus = object2.getString("status");
                                                    String instsubscription = object2.getString("subscription");
                                                    String insttype = object2.getString("type");
                                                    String instupdated_at = object2.getString("updated_at");
                                                    String instupdated_by = object2.getString("updated_by");
                                                    String instusername = object2.getString("username");
                                                    String instuser_id = object2.getString("user_id");
                                                    String instwebsite = object2.getString("website");

                                                    model2.setmInstAddress(instaddress);
                                                    model2.setmInstContacts(instcontacts);
                                                    model2.setmInstContactPerson(instcontact_person);
                                                    model2.setmInstcontactPersonEmail(instcontact_person_email);
                                                    model2.setmInstContactPersonMobile(instcontact_person_mobile);
                                                    model2.setmInstCreatedAt(instcreated_at);
                                                    model2.setmInstCreatedBy(instcreated_by);
                                                    model2.setmInstCcredit(instcredit);
                                                    model2.setmInstDeletedAt(instdeleted_at);
                                                    model2.setmInstEmail(instemail);
                                                    model2.setmInstGoogleLocation(instgoogle_location);
                                                    model2.setmInstHeading(instheading);
                                                    model2.setmInstId(instid);
                                                    model2.setmInitial(initial);
                                                    model2.setmInstName(institution_name);
                                                    model2.setmInstType(institution_type);
                                                    model2.setmInstLegalDocFile(instlegal_document_file);
                                                    model2.setmInstMetaData(instmetadata);
                                                    model2.setInstphone(instphone);
                                                    model2.setInstsocial(instsocial);
                                                    model2.setInststatus(inststatus);
                                                    model2.setInstsubscription(instsubscription);
                                                    model2.setInsttype(insttype);
                                                    model2.setInstupdated_at(instupdated_at);
                                                    model2.setInstupdated_by(instupdated_by);
                                                    model2.setInstusername(instusername);
                                                    model2.setInstuser_id(instuser_id);
                                                    model2.setInstwebsite(instwebsite);

                                                    detailList2.add(model2);
                                                }

                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }


                                            // For parsing 2nd Array of info JSON

                                            detailList3=new ArrayList<DetailDataModelUserData>();

                                            DetailDataModelUserData model3 = new DetailDataModelUserData();

                                            try {
                                                for (int iii=0;iii<jObject.length()-1;iii++)
                                                {
                                                    JSONArray objectAnother = (JSONArray) jObject.get("institution");
                                                    JSONObject objectAnother2 = (JSONObject) objectAnother.get(iii);

                                                    String instaddress = objectAnother2.getString("address");
                                                    String instcontacts = objectAnother2.getString("contacts");
                                                    String instcontact_person = objectAnother2.getString("contact_person");
                                                    String instcontact_person_email = objectAnother2.getString("contact_person_email");
                                                    String instcontact_person_mobile = objectAnother2.getString("contact_person_mobile");

                                                    String instcreated_at = objectAnother2.getString("created_at");
                                                    String instcreated_by = objectAnother2.getString("created_by");
                                                    String instcredit = objectAnother2.getString("credit");
                                                    String instdeleted_at = objectAnother2.getString("deleted_at");
                                                    String instemail = objectAnother2.getString("email");
                                                    String instgoogle_location = objectAnother2.getString("google_location");
                                                    String instheading = objectAnother2.getString("heading");
                                                    String instid = objectAnother2.getString("id");
                                                    String initial = objectAnother2.getString("initial");
                                                    String institution_name = objectAnother2.getString("institution_name");
                                                    String institution_type = objectAnother2.getString("institution_type");
                                                    String instlegal_document_file = objectAnother2.getString("legal_document_file");
                                                    String instmetadata = objectAnother2.getString("metadata");
                                                    String instphone = objectAnother2.getString("phone");
                                                    String instsocial = objectAnother2.getString("social");
                                                    String inststatus = objectAnother2.getString("status");
                                                    String instsubscription = objectAnother2.getString("subscription");
                                                    String insttype = objectAnother2.getString("type");
                                                    String instupdated_at = objectAnother2.getString("updated_at");
                                                    String instupdated_by = objectAnother2.getString("updated_by");
                                                    String instusername = objectAnother2.getString("username");
                                                    String instuser_id = objectAnother2.getString("user_id");
                                                    String instwebsite = objectAnother2.getString("website");

                                                    model3.setmInstAddress(instaddress);
                                                    model3.setmInstContacts(instcontacts);
                                                    model3.setmInstContactPerson(instcontact_person);
                                                    model3.setmInstcontactPersonEmail(instcontact_person_email);
                                                    model3.setmInstContactPersonMobile(instcontact_person_mobile);
                                                    model3.setmInstCreatedAt(instcreated_at);
                                                    model3.setmInstCreatedBy(instcreated_by);
                                                    model3.setmInstCcredit(instcredit);
                                                    model3.setmInstDeletedAt(instdeleted_at);
                                                    model3.setmInstEmail(instemail);
                                                    model3.setmInstGoogleLocation(instgoogle_location);
                                                    model3.setmInstHeading(instheading);
                                                    model3.setmInstId(instid);
                                                    model3.setmInitial(initial);
                                                    model3.setmInstName(institution_name);
                                                    model3.setmInstType(institution_type);
                                                    model3.setmInstLegalDocFile(instlegal_document_file);
                                                    model3.setmInstMetaData(instmetadata);
                                                    model3.setInstphone(instphone);
                                                    model3.setInstsocial(instsocial);
                                                    model3.setInststatus(inststatus);
                                                    model3.setInstsubscription(instsubscription);
                                                    model3.setInsttype(insttype);
                                                    model3.setInstupdated_at(instupdated_at);
                                                    model3.setInstupdated_by(instupdated_by);
                                                    model3.setInstusername(instusername);
                                                    model3.setInstuser_id(instuser_id);
                                                    model3.setInstwebsite(instwebsite);

                                                    detailList3.add(model3);
                                                }

                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }


                                            // For parsing 3rd Array of info JSON


                                            detailList4=new ArrayList<DetailDataModelUserData>();


                                            detailListAnoPart1=new ArrayList<DetailDataModelUserData>();
                                            detailListAnoPart2=new ArrayList<DetailDataModelUserData>();
                                            detailListAnoPart3=new ArrayList<DetailDataModelUserData>();

                                            DetailDataModelUserData modelPart1 = new DetailDataModelUserData();
                                            DetailDataModelUserData modelPart2 = new DetailDataModelUserData();
                                            DetailDataModelUserData modelPart3 = new DetailDataModelUserData();

                                            JSONArray objectAgainAnother = (JSONArray) jObject.get("RoleInstitution");
                                            JSONArray objectEnrollCourse = (JSONArray) jObject.getJSONArray("EnrollCourse");

                                            JSONObject objectEnrollCourseInside = (JSONObject) objectEnrollCourse.get(0);

                                            JSONObject objectCourse2 = objectEnrollCourseInside.getJSONObject("Course");

                                            DetailDataModelUserData modelForLoginCourse = new DetailDataModelUserData();

                                            String featured = objectCourse2.getString("featured");
                                            String Eid = objectCourse2.getString("id");
                                            String Edetails = objectCourse2.getString("details");
                                            String Eadmission_status = objectCourse2.getString("admission_status");
                                            String averageRating = objectCourse2.getString("averageRating");
                                            String certificate_alias_name = objectCourse2.getString("certificate_alias_name");
                                            String clone_status = objectCourse2.getString("clone_status");
                                            String code = objectCourse2.getString("code");
                                            String courses_for_status = objectCourse2.getString("courses_for_status");
                                            String course_alias_name = objectCourse2.getString("course_alias_name");
                                            String course_motto = objectCourse2.getString("course_motto");
                                            String created_at = objectCourse2.getString("created_at");
                                            String duration = objectCourse2.getString("duration");
                                            String end_date = objectCourse2.getString("end_date");
                                            String enrolment_approval_status = objectCourse2.getString("enrolment_approval_status");

                                            modelForLoginCourse.setmCertificateAliasName(certificate_alias_name);
                                            modelForLoginCourse.setmAdmissionStatus(Eadmission_status);
                                            modelForLoginCourse.setmAverageRating(averageRating);
                                            modelForLoginCourse.setmCloneStatus(clone_status);
                                            modelForLoginCourse.setmCode(code);
                                            modelForLoginCourse.setmCreatedAt(created_at);
                                            modelForLoginCourse.setmDuration(duration);
                                            modelForLoginCourse.setmEndDate(end_date);
                                            modelForLoginCourse.setmId(Eid);
                                            modelForLoginCourse.setmDetails(Edetails);
                                            modelForLoginCourse.setmFeatured(featured);
                                            modelForLoginCourse.setmEnrolmentApprovalStatus(enrolment_approval_status);
                                            modelForLoginCourse.setmCursesForStatus(courses_for_status);
                                            modelForLoginCourse.setmCourseAliasName(course_alias_name);
                                            modelForLoginCourse.setmCourseMotto(course_motto);
                                            modelForLoginCourse.setmStatus(status);

                                            detailListAnoPart3.add(modelForLoginCourse);

                                                    //JSONObject objectAgainAnother2 = (JSONObject) objectAgainAnother.get(iv);

                                                    /*JSONObject objectAgainAnotherPart1 = (JSONObject) objectAgainAnother2.get("Institution");
                                                    JSONObject objectAgainAnotherPart2 = (JSONObject) objectAgainAnother2.get("Role");
                                                    JSONObject objectAgainAnotherPart3 = (JSONObject) objectAgainAnother2.get("UserRole");*/

                                                    // For parsing 3rd Array's 1st Object of info JSON


                                                    /*String instaddress = objectAgainAnotherPart1.getString("address");
                                                    String instcontacts = objectAgainAnotherPart1.getString("contacts");
                                                    String instcontact_person = objectAgainAnotherPart1.getString("contact_person");
                                                    String instcontact_person_email = objectAgainAnotherPart1.getString("contact_person_email");
                                                    String instcontact_person_mobile = objectAgainAnotherPart1.getString("contact_person_mobile");
                                                    String instcreated_at = objectAgainAnotherPart1.getString("created_at");
                                                    String instcreated_by = objectAgainAnotherPart1.getString("created_by");
                                                    String instcredit = objectAgainAnotherPart1.getString("credit");
                                                    String instdeleted_at = objectAgainAnotherPart1.getString("deleted_at");
                                                    String instemail = objectAgainAnotherPart1.getString("email");
                                                    String instgoogle_location = objectAgainAnotherPart1.getString("google_location");
                                                    String instheading = objectAgainAnotherPart1.getString("heading");
                                                    String instid = objectAgainAnotherPart1.getString("id");
                                                    String initial = objectAgainAnotherPart1.getString("initial");
                                                    String institution_name = objectAgainAnotherPart1.getString("institution_name");
                                                    String institution_type = objectAgainAnotherPart1.getString("institution_type");
                                                    String instlegal_document_file = objectAgainAnotherPart1.getString("legal_document_file");
                                                    String instmetadata = objectAgainAnotherPart1.getString("metadata");
                                                    String instphone = objectAgainAnotherPart1.getString("phone");
                                                    String instsocial = objectAgainAnotherPart1.getString("social");
                                                    String inststatus = objectAgainAnotherPart1.getString("status");
                                                    String instsubscription = objectAgainAnotherPart1.getString("subscription");
                                                    String insttype = objectAgainAnotherPart1.getString("type");
                                                    String instupdated_at = objectAgainAnotherPart1.getString("updated_at");
                                                    String instupdated_by = objectAgainAnotherPart1.getString("updated_by");
                                                    String instusername = objectAgainAnotherPart1.getString("username");
                                                    String instuser_id = objectAgainAnotherPart1.getString("user_id");
                                                    String instwebsite = objectAgainAnotherPart1.getString("website");

                                                    modelPart1.setmInstAddress(instaddress);
                                                    modelPart1.setmInstContacts(instcontacts);
                                                    modelPart1.setmInstContactPerson(instcontact_person);
                                                    modelPart1.setmInstcontactPersonEmail(instcontact_person_email);
                                                    modelPart1.setmInstContactPersonMobile(instcontact_person_mobile);
                                                    modelPart1.setmInstCreatedAt(instcreated_at);
                                                    modelPart1.setmInstCreatedBy(instcreated_by);
                                                    modelPart1.setmInstCcredit(instcredit);
                                                    modelPart1.setmInstDeletedAt(instdeleted_at);
                                                    modelPart1.setmInstEmail(instemail);
                                                    modelPart1.setmInstGoogleLocation(instgoogle_location);
                                                    modelPart1.setmInstHeading(instheading);
                                                    modelPart1.setmInstId(instid);
                                                    modelPart1.setmInitial(initial);
                                                    modelPart1.setmInstName(institution_name);
                                                    modelPart1.setmInstType(institution_type);
                                                    modelPart1.setmInstLegalDocFile(instlegal_document_file);
                                                    modelPart1.setmInstMetaData(instmetadata);
                                                    modelPart1.setInstphone(instphone);
                                                    modelPart1.setInstsocial(instsocial);
                                                    modelPart1.setInststatus(inststatus);
                                                    modelPart1.setInstsubscription(instsubscription);
                                                    modelPart1.setInsttype(insttype);
                                                    modelPart1.setInstupdated_at(instupdated_at);
                                                    modelPart1.setInstupdated_by(instupdated_by);
                                                    modelPart1.setInstusername(instusername);
                                                    modelPart1.setInstuser_id(instuser_id);
                                                    modelPart1.setInstwebsite(instwebsite);

                                                    detailListAnoPart1.add(modelPart1);



                                                    // For parsing 3rd Array's 2nd Object of info JSON


                                                    String instaddress2 = objectAgainAnotherPart2.getString("address");
                                                    String instcontacts2 = objectAgainAnotherPart2.getString("contacts");
                                                    String instcontact_person2 = objectAgainAnotherPart2.getString("contact_person");
                                                    String instcontact_person_email2 = objectAgainAnotherPart2.getString("contact_person_email");
                                                    String instcontact_person_mobile2 = objectAgainAnotherPart2.getString("contact_person_mobile");
                                                    String instcreated_at2 = objectAgainAnotherPart2.getString("created_at");
                                                    String instcreated_by2 = objectAgainAnotherPart2.getString("created_by");
                                                    String instcredit2 = objectAgainAnotherPart2.getString("credit");
                                                    String instdeleted_at2 = objectAgainAnotherPart2.getString("deleted_at");
                                                    String instemail2 = objectAgainAnotherPart2.getString("email");
                                                    String instgoogle_location2 = objectAgainAnotherPart2.getString("google_location");
                                                    String instheading2 = objectAgainAnotherPart2.getString("heading");
                                                    String instid2 = objectAgainAnotherPart2.getString("id");
                                                    String initial2 = objectAgainAnotherPart2.getString("initial");
                                                    String institution_name2 = objectAgainAnotherPart2.getString("institution_name");
                                                    String institution_type2 = objectAgainAnotherPart2.getString("institution_type");
                                                    String instlegal_document_file2 = objectAgainAnotherPart2.getString("legal_document_file");
                                                    String instmetadata2 = objectAgainAnotherPart2.getString("metadata");
                                                    String instphone2 = objectAgainAnotherPart2.getString("phone");
                                                    String instsocial2 = objectAgainAnotherPart2.getString("social");
                                                    String inststatus2 = objectAgainAnotherPart2.getString("status");
                                                    String instsubscription2 = objectAgainAnotherPart2.getString("subscription");
                                                    String insttype2 = objectAgainAnotherPart2.getString("type");
                                                    String instupdated_at2 = objectAgainAnotherPart2.getString("updated_at");
                                                    String instupdated_by2 = objectAgainAnotherPart2.getString("updated_by");
                                                    String instusername2 = objectAgainAnotherPart2.getString("username");
                                                    String instuser_id2 = objectAgainAnotherPart2.getString("user_id");
                                                    String instwebsite2 = objectAgainAnotherPart2.getString("website");

                                                    modelPart2.setmInstAddress(instaddress2);
                                                    modelPart2.setmInstContacts(instcontacts2);
                                                    modelPart2.setmInstContactPerson(instcontact_person2);
                                                    modelPart2.setmInstcontactPersonEmail(instcontact_person_email2);
                                                    modelPart2.setmInstContactPersonMobile(instcontact_person_mobile2);
                                                    modelPart2.setmInstCreatedAt(instcreated_at2);
                                                    modelPart2.setmInstCreatedBy(instcreated_by2);
                                                    modelPart2.setmInstCcredit(instcredit2);
                                                    modelPart2.setmInstDeletedAt(instdeleted_at2);
                                                    modelPart2.setmInstEmail(instemail2);
                                                    modelPart2.setmInstGoogleLocation(instgoogle_location2);
                                                    modelPart2.setmInstHeading(instheading2);
                                                    modelPart2.setmInstId(instid2);
                                                    modelPart2.setmInitial(initial2);
                                                    modelPart2.setmInstName(institution_name2);
                                                    modelPart2.setmInstType(institution_type2);
                                                    modelPart2.setmInstLegalDocFile(instlegal_document_file2);
                                                    modelPart2.setmInstMetaData(instmetadata2);
                                                    modelPart2.setInstphone(instphone2);
                                                    modelPart2.setInstsocial(instsocial2);
                                                    modelPart2.setInststatus(inststatus2);
                                                    modelPart2.setInstsubscription(instsubscription2);
                                                    modelPart2.setInsttype(insttype2);
                                                    modelPart2.setInstupdated_at(instupdated_at2);
                                                    modelPart2.setInstupdated_by(instupdated_by2);
                                                    modelPart2.setInstusername(instusername2);
                                                    modelPart2.setInstuser_id(instuser_id2);
                                                    modelPart2.setInstwebsite(instwebsite2);

                                                    detailListAnoPart2.add(modelPart2);



                                                    // For parsing 3rd Array's 3rd Object of info JSON



                                                    String instaddress3 = objectAgainAnotherPart3.getString("address");
                                                    String instcontacts3 = objectAgainAnotherPart3.getString("contacts");
                                                    String instcontact_person3 = objectAgainAnotherPart3.getString("contact_person");
                                                    String instcontact_person_email3 = objectAgainAnotherPart3.getString("contact_person_email");
                                                    String instcontact_person_mobile3 = objectAgainAnotherPart3.getString("contact_person_mobile");
                                                    String instcreated_at3 = objectAgainAnotherPart3.getString("created_at");
                                                    String instcreated_by3 = objectAgainAnotherPart3.getString("created_by");
                                                    String instcredit3 = objectAgainAnotherPart3.getString("credit");
                                                    String instdeleted_at3 = objectAgainAnotherPart3.getString("deleted_at");
                                                    String instemail3 = objectAgainAnotherPart3.getString("email");
                                                    String instgoogle_location3 = objectAgainAnotherPart3.getString("google_location");
                                                    String instheading3 = objectAgainAnotherPart3.getString("heading");
                                                    String instid3 = objectAgainAnotherPart3.getString("id");
                                                    String initial3 = objectAgainAnotherPart3.getString("initial");
                                                    String institution_name3 = objectAgainAnotherPart3.getString("institution_name");
                                                    String institution_type3 = objectAgainAnotherPart3.getString("institution_type");
                                                    String instlegal_document_file3 = objectAgainAnotherPart3.getString("legal_document_file");
                                                    String instmetadata3 = objectAgainAnotherPart3.getString("metadata");
                                                    String instphone3 = objectAgainAnotherPart3.getString("phone");
                                                    String instsocial3 = objectAgainAnotherPart3.getString("social");
                                                    String inststatus3 = objectAgainAnotherPart3.getString("status");
                                                    String instsubscription3 = objectAgainAnotherPart3.getString("subscription");
                                                    String insttype3 = objectAgainAnotherPart3.getString("type");
                                                    String instupdated_at3 = objectAgainAnotherPart3.getString("updated_at");
                                                    String instupdated_by3 = objectAgainAnotherPart3.getString("updated_by");
                                                    String instusername3 = objectAgainAnotherPart3.getString("username");
                                                    String instuser_id3 = objectAgainAnotherPart3.getString("user_id");
                                                    String instwebsite3 = objectAgainAnotherPart3.getString("website");

                                                    modelPart3.setmInstAddress(instaddress3);
                                                    modelPart3.setmInstContacts(instcontacts3);
                                                    modelPart3.setmInstContactPerson(instcontact_person3);
                                                    modelPart3.setmInstcontactPersonEmail(instcontact_person_email3);
                                                    modelPart3.setmInstContactPersonMobile(instcontact_person_mobile3);
                                                    modelPart3.setmInstCreatedAt(instcreated_at3);
                                                    modelPart3.setmInstCreatedBy(instcreated_by3);
                                                    modelPart3.setmInstCcredit(instcredit3);
                                                    modelPart3.setmInstDeletedAt(instdeleted_at3);
                                                    modelPart3.setmInstEmail(instemail3);
                                                    modelPart3.setmInstGoogleLocation(instgoogle_location3);
                                                    modelPart3.setmInstHeading(instheading3);
                                                    modelPart3.setmInstId(instid3);
                                                    modelPart3.setmInitial(initial3);
                                                    modelPart3.setmInstName(institution_name3);
                                                    modelPart3.setmInstType(institution_type3);
                                                    modelPart3.setmInstLegalDocFile(instlegal_document_file3);
                                                    modelPart3.setmInstMetaData(instmetadata3);
                                                    modelPart3.setInstphone(instphone3);
                                                    modelPart3.setInstsocial(instsocial3);
                                                    modelPart3.setInststatus(inststatus3);
                                                    modelPart3.setInstsubscription(instsubscription3);
                                                    modelPart3.setInsttype(insttype3);
                                                    modelPart3.setInstupdated_at(instupdated_at3);
                                                    modelPart3.setInstupdated_by(instupdated_by3);
                                                    modelPart3.setInstusername(instusername3);
                                                    modelPart3.setInstuser_id(instuser_id3);
                                                    modelPart3.setInstwebsite(instwebsite3);

                                                    detailListAnoPart3.add(modelPart3);*/

                                                    //for parsing EnrollCourse


                                        }

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
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

                Intent i=new Intent(mContext,SelectACategoryActivity.class);
                startActivity(i);
            }
        });
    }
}

