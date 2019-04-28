package orangebd.newaspaper.mymuktopathapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.Map;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {
    
    private static final int REQUEST_READ_CONTACTS = 0;
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };

    private ProgressDialog mProgressDialog;

    private Context mContext;

    private Button mRegiPageSignUpBtn;

    private HashMap<String, String> map;

    private EditText mEmailView;
    private String mStrEmail;

    private EditText mEdtTxtPwd;
    private String mStrPwd;

    private ArrayList<DetailDataModelCourses> detailList=new ArrayList<>();
    private ArrayList<DetailDataModelCourses> detailList2=new ArrayList<>();
    private ArrayList<DetailDataModelCourses> detailList3=new ArrayList<>();
    private ArrayList<DetailDataModelCourses> detailList4=new ArrayList<>();

    private ArrayList<DetailDataModelCoursesDetailContents> detailListCourseDetailContents;

    private ArrayList<DetailDataModelCourses> detailListAnoPart1=new ArrayList<>();
    private ArrayList<DetailDataModelCourses> detailListAnoPart2=new ArrayList<>();
    private ArrayList<DetailDataModelCourses> detailListAnoPart3=new ArrayList<>();

    private ArrayList<DetailDataModelCourses> detailListCourse;

    private Object[] mArrayList;


    //All the detail Lists
    private ArrayList<DetailDataModelCourses> detailList5;
    private ArrayList<DetailDataModelCourses> detailList6;
    private ArrayList<DetailDataModelCourses> detailList7;
    private ArrayList<DetailDataModelCourses> detailList8;
    private ArrayList<DetailDataModelCourses> detailList9;
    private ArrayList<DetailDataModelCourses> detailList10;
    private ArrayList<DetailDataModelCourses> detailList11;

    private ArrayList<DetailDataModelCourses> detailListEnrollCourses;

    private ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> detailListCourseDetailContentss;
    private ArrayList<DetailDataModelCoursesDetailContents> detailListVideoPulse;
    private ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> detailListCourseDetailUnits;
    private ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> detailListCourseDetailUnitQuizList;
    private ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> detailListCourseDetailUnitQuizListExam;
    private ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> detailListCourseDetailUnitQuizList2;

    private ArrayList<ArrayList<ArrayList<DetailDataModelCoursesDetailContents>>> detailListCourseDetailUnitQuizOptList;
    private ArrayList<ArrayList<ArrayList<DetailDataModelCoursesDetailContents>>> detailListCourseDetailPulseQuizOptList;
    private ArrayList<ArrayList<ArrayList<DetailDataModelCoursesDetailContents>>> detailListCourseDetailVideoPulseMulti;

    //for declaring arraylist of units
    private ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> detailListCourseUnit1Data;
    private ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> detailListCourseUnit2Data;
    private ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> detailListCourseUnit3Data;
    private ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> detailListCourseUnit4Data;

    //TODO

    private ArrayList<DetailDataModelCoursesThumbnails> detailListCourseThumbnail;

    private ArrayList<DetailDataModelCoursesMarks> mPassPercentageArrayList;

    private ArrayList<DetailDataModelCourses> detailListFileType;

    ArrayList<DetailDataModelCoursesDetailContents> mContentArrayListNew;
    ArrayList<DetailDataModelCoursesDetailContents> mUnitArrayListNew;

    ArrayList<DetailDataModelCoursesDetailContents> mUnitQuizList;
    ArrayList<DetailDataModelCoursesDetailContents> mUnitQuizListExam;
    ArrayList<DetailDataModelCoursesDetailContents> mUnitQuizListMp;
    ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> mUnitQuizList2;
    ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> mPulseQuizList2;

    ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> mVideoPulseMulti;

    ArrayList<DetailDataModelCoursesDetailContents> mUnitQuizListWithOptions;

    ArrayList<DetailDataModelCoursesDetailContents> mUnitQuizListWithOptionsMp;

    //TODO
    //ArrayList<DetailDataModelCoursesDetailContents> mUnitQuizOptList;
    ArrayList<DetailDataModelCoursesDetailContents> mUnitQuizOptList;
    ArrayList<DetailDataModelCoursesDetailContents> mPulseQuizOptList;
    ArrayList<DetailDataModelCoursesDetailContents> mContentArrayListNewPulse;

    ArrayList<DetailDataModelCoursesDetailContents> mUnit1DataArrayList;
    ArrayList<DetailDataModelCoursesDetailContents> mUnit2DataArrayList;
    ArrayList<DetailDataModelCoursesDetailContents> mUnit3DataArrayList;
    ArrayList<DetailDataModelCoursesDetailContents> mUnit4DataArrayList;

    private ArrayList<DetailDataModelCoursesDetailContents> detailListUserInformation=new ArrayList<>();

    private TextView mGoToRegiPage;

    private SessionManager sm;

    private int multiQKey=-1;

    //private boolean isLogin;

    private String token="";
    String url= GlobalVar.gApiBaseUrl + "/api/login";

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


        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        mGoToRegiPage=findViewById(R.id.goToregiPage);
        mGoToRegiPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(mContext,CreateAccountActivity.class);
                startActivity(i);
            }
        });

        mEdtTxtPwd.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {

                    return true;
                }
                return false;
            }
        });
        mEmailView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {

                    return true;
                }
                return false;
            }
        });

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

                sm = new SessionManager(mContext);

                map = new HashMap<String, String>();

                token = GlobalVar.getTokenArray.get(0).getmAccessToken();
                String firstWord = GlobalVar.getTokenArray.get(0).getmTokenType();

                token = firstWord + " " + token;

                GlobalVar.gReplacingTokenForAllCategories=token;

                final JSONObject object=new JSONObject();

                try {
                    object.put("email", mStrEmail);
                    object.put("password", mStrPwd);
                    object.put("type", "1");
                }
                catch (Exception ex){
                    Log.d("", "onClick: ");
                }

                if(mStrEmail.equalsIgnoreCase("")){

                    mEmailView.setError("You must type your email.");
                    mEmailView.requestFocus();

                    if(mStrPwd.equalsIgnoreCase("")){

                        mEdtTxtPwd.setError("You must type your password.");
                        mEdtTxtPwd.requestFocus();
                    }
                }

                else if(mStrEmail.equalsIgnoreCase(mStrEmail)){


                    if(mStrPwd.equalsIgnoreCase("")){

                        mEdtTxtPwd.setError("You must type your password.");
                        mEdtTxtPwd.requestFocus();
                    }

                    else if(mStrPwd.equalsIgnoreCase(mStrPwd)) {

                        if (mStrEmail.contains("@")) {


                            //GlobalVar.gIsLogin=sm.checkLogin();

                            if(!GlobalVar.gIsLogin) {

                                sm.createLoginSession(mStrEmail, mStrPwd);
                                sm.isLoggedIn();
                            }


                            mProgressDialog=new ProgressDialog(mContext);
                            mProgressDialog.setIndeterminate(true);
                            mProgressDialog.setMessage("Please wait...");
                            mProgressDialog.show();

                            RequestQueue mQueue = Volley.newRequestQueue(getApplicationContext());

                            final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, object,
                                    new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                            if (response != null) try
                                            {
                                                detailListCourse = new ArrayList<DetailDataModelCourses>();

                                                detailList = new ArrayList<DetailDataModelCourses>();

                                                detailListCourseThumbnail = new ArrayList<DetailDataModelCoursesThumbnails>();
                                                mPassPercentageArrayList = new ArrayList<DetailDataModelCoursesMarks>();
                                                detailListCourseDetailContents = new ArrayList<DetailDataModelCoursesDetailContents>();
                                                detailList10 = new ArrayList<DetailDataModelCourses>();

                                                detailList7 = new ArrayList<DetailDataModelCourses>();
                                                detailList8 = new ArrayList<DetailDataModelCourses>();

                                                detailListCourseDetailContentss = new ArrayList<>();
                                                detailListCourseDetailUnits = new ArrayList<>();
                                                detailListCourseDetailUnitQuizList = new ArrayList<>();
                                                detailListCourseDetailUnitQuizListExam = new ArrayList<>();
                                                detailListCourseDetailUnitQuizList2 = new ArrayList<>();

                                                detailListCourseDetailUnitQuizOptList = new ArrayList<>();
                                                detailListCourseDetailPulseQuizOptList = new ArrayList<>();
                                                detailListCourseDetailVideoPulseMulti = new ArrayList<>();

                                                //for renew arraylist of units arrays
                                                detailListCourseUnit1Data = new ArrayList<>();
                                                detailListCourseUnit2Data = new ArrayList<>();
                                                detailListCourseUnit3Data = new ArrayList<>();
                                                detailListCourseUnit4Data = new ArrayList<>();

                                                DetailDataModelCourses model = new DetailDataModelCourses();

                                                JSONObject jObject;
                                                JSONObject jObjectAllMarks;
                                                JSONObject jObjectData = new JSONObject();

                                                try {
                                                    jObjectData = response.getJSONObject("data");
                                                }
                                                catch (Exception ex) {
                                                    Log.d("", "onResponse: ");
                                                }

                                                // For parsing simple JSON
                                                //TODO

                                                try {
                                                    DetailDataModelCourses modelAlter = new DetailDataModelCourses();

                                                    String id = jObjectData.getString("id");
                                                    String username = jObjectData.getString("username");
                                                    String email = jObjectData.getString("email");
                                                    String Completeness = jObjectData.getString("completeness");
                                                    String name = jObjectData.getString("name");

                                                    String lastLoginIpAddress = jObjectData.getString("last_login_ip_address");
                                                    String last_login_time = jObjectData.getString("last_login_time");
                                                    String login_status = jObjectData.getString("login_status");
                                                    //String password = jObject.getString("password");
                                                    String phone = jObjectData.getString("phone");
                                                    String status = jObjectData.getString("status");
                                                    String token = jObjectData.getString("token");
                                                    String coursecompleted = jObjectData.getString("CourseCompleted");
                                                    String totalEnrollment = jObjectData.getString("TotalEnrollment");

                                                    GlobalVar.gName = name;
                                                    GlobalVar.gMobile = phone;
                                                    GlobalVar.gEmail = email;
                                                    GlobalVar.gReplacingToken = token;

                                                    /*model.setmId(id);
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
                                                    model.setmUserPhone(phone);*/

                                                    detailList.add(model);

                                                    // For parsing 1st Array of info JSON

                                                    detailList2 = new ArrayList<DetailDataModelCourses>();

                                                    DetailDataModelCourses model2 = new DetailDataModelCourses();

                                                    try {
                                                        JSONArray object = (JSONArray) jObjectData.getJSONArray("owninstitution");

                                                        for (int ii2 = 0; ii2 < object.length(); ii2++) {
                                                            JSONObject object2 = (JSONObject) object.get(ii2);

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


                                                    try {
                                                        JSONObject objectForOwnersInfo = (JSONObject) jObjectData.getJSONObject("UserInfo");

                                                        String usersidnumber = objectForOwnersInfo.getString("user_id");

                                                        GlobalVar.gUsersNumber = usersidnumber;
                                                    } catch (Exception ex) {
                                                        Log.d("", "onResponse: ");
                                                    }


                                                    // For parsing 2nd Array of info JSON

                                                    detailList3 = new ArrayList<DetailDataModelCourses>();

                                                    DetailDataModelCourses model3 = new DetailDataModelCourses();

                                                    try {

                                                        JSONArray objectAnother = (JSONArray) jObjectData.getJSONArray("institution");

                                                        for (int iii = 0; iii < objectAnother.length(); iii++) {
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

                                                    detailList4 = new ArrayList<DetailDataModelCourses>();

                                                    detailListAnoPart1 = new ArrayList<DetailDataModelCourses>();
                                                    detailListAnoPart2 = new ArrayList<DetailDataModelCourses>();
                                                    detailListAnoPart3 = new ArrayList<DetailDataModelCourses>();

                                                    DetailDataModelCourses modelPart1 = new DetailDataModelCourses();
                                                    DetailDataModelCourses modelPart2 = new DetailDataModelCourses();
                                                    DetailDataModelCourses modelPart3 = new DetailDataModelCourses();


                                                    try {
                                                        detailListUserInformation = new ArrayList<DetailDataModelCoursesDetailContents>();

                                                        DetailDataModelCourses modelEnrollCourse = new DetailDataModelCourses();

                                                        DetailDataModelCoursesDetailContents modelUserInformation = new DetailDataModelCoursesDetailContents();

                                                        JSONArray objectAgainAnother =  jObjectData.getJSONArray("RoleInstitution");
                                                        JSONArray objectEnrollCourse =  jObjectData.getJSONArray("EnrollCourse");

                                                        JSONObject objectUserInformation = jObjectData.getJSONObject("UserInfo");

                                                        String UserProfilePhoto = objectUserInformation.getString("photo_name");
                                                        String UserCoverPhoto = objectUserInformation.getString("cover_image");

                                                        modelUserInformation.setmUserProfilePhoto(UserProfilePhoto);
                                                        modelUserInformation.setmUserCoverPhoto(UserCoverPhoto);

                                                        detailListUserInformation.add(modelUserInformation);

                                                        int enrolledCourseNumbers = objectEnrollCourse.length();

                                                        GlobalVar.gEnrollCourseNumber = enrolledCourseNumbers;

                                                        String aaaaaa = "";

                                                        try {
                                                            for (int ec = 0; ec < objectEnrollCourse.length(); ec++) {

                                                                JSONObject jObjEnrolledCourses = objectEnrollCourse.getJSONObject(ec);

                                                                JSONObject objectCourse2 = jObjEnrolledCourses.getJSONObject("Course");

                                                                JSONArray exams = jObjEnrolledCourses.getJSONArray("exam");
                                                                JSONArray assignments = jObjEnrolledCourses.getJSONArray("assignment");

                                                                int examNumbers = exams.length();
                                                                int assignmentsNumbers = assignments.length();

                                                                DetailDataModelCourses modelForLoginCourse = new DetailDataModelCourses();

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
                                                                modelForLoginCourse.setmExamNumbers(examNumbers);
                                                                modelForLoginCourse.setmAssignmentNumbers(assignmentsNumbers);

                                                                detailListAnoPart3.add(modelForLoginCourse);

                                                                GlobalVar.gEnrollCourseList = detailListAnoPart3;

                                                                try {
                                                                    jObject = objectCourse2.getJSONObject("syllabus");

                                                                    jObjectAllMarks = objectCourse2.getJSONObject("marks");

                                                                    JSONObject totalMarks=jObjectAllMarks.getJSONObject("quiz");

                                                                    String courseQuizPassMark = totalMarks.getString("pass_marks");

                                                                    DetailDataModelCoursesMarks modelCoursePassMark = new DetailDataModelCoursesMarks();

                                                                    modelCoursePassMark.setQuiz_pass_mark(courseQuizPassMark);

                                                                    mPassPercentageArrayList.add(modelCoursePassMark);


                                                                    String awqwe="";


                                                                    JSONArray objectEnrollCourseUnits = (JSONArray) jObject.getJSONArray("units");

                                                                    mUnitArrayListNew = new ArrayList<>();

                                                                    for (int ecu = 0; ecu < objectEnrollCourseUnits.length(); ecu++) {
                                                                        DetailDataModelCoursesDetailContents modelUnitElements = new DetailDataModelCoursesDetailContents();

                                                                        JSONObject objectUnitElements = (JSONObject) objectEnrollCourseUnits.get(ecu);

                                                                        String orderEcu = objectUnitElements.getString("order");
                                                                        String nameEcu = objectUnitElements.getString("name");
                                                                        String idEcu = objectUnitElements.getString("id");

                                                                        modelUnitElements.setUnitOrders(orderEcu);
                                                                        modelUnitElements.setUnitNames(nameEcu);
                                                                        modelUnitElements.setUnitID(idEcu);

                                                                        mUnitArrayListNew.add(modelUnitElements);
                                                                    }


                                                    /*JSONArray quizList = (JSONArray) objectQuizes1.getJSONArray("ques_list");

                                                    mUnitQuizList = new ArrayList<>();

                                                    for (int qlist=0; qlist<quizList.length(); qlist++){

                                                        DetailDataModelCoursesDetailContents modelQuizElements = new DetailDataModelCoursesDetailContents();

                                                        JSONObject qlistElements = (JSONObject) quizList.get(qlist);

                                                        String qTitle = qlistElements.getString("title");

                                                        modelQuizElements.setmQuizTitle(qTitle);

                                                        mUnitQuizList.add(modelQuizElements);
                                                    }*/

                                                                    //for parsing syllebus strings

                                                                    DetailDataModelCourses model7 = new DetailDataModelCourses();

                                                                    String study_mode_Syllebus = jObject.getString("study_mode");

                                                                    model7.setStudyModeSyllebus(study_mode_Syllebus);

                                                                    JSONObject jObjectCourse = objectCourse2.getJSONObject("course");

                                                                    //for parsing course strings

                                                                    DetailDataModelCourses model8 = new DetailDataModelCourses();

                                                                    String course_codeCourse = jObjectCourse.getString("course_code");
                                                                    String course_levelCourse = jObjectCourse.getString("course_level");
                                                                    String idCourse = jObjectCourse.getString("id");
                                                                    String promovideoCourse = jObjectCourse.getString("promovideo");
                                                                    String titleCourse = jObjectCourse.getString("title");

                                                                    model8.setCourse_codeCourse(course_codeCourse);
                                                                    model8.setCourse_levelCourse(course_levelCourse);
                                                                    model8.setIdCourse(idCourse);
                                                                    model8.setPromovideoCourse(promovideoCourse);
                                                                    model8.setTitleCourse(titleCourse);

                                                                    //for parsing thumbnails of courses

                                                                    DetailDataModelCoursesThumbnails modelCourseThumbnail = new DetailDataModelCoursesThumbnails();

                                                                    JSONObject thumnail = jObjectCourse.getJSONObject("thumnail");
                                                                    String coverPhoto = thumnail.getString("file_encode_path");

                                                                    modelCourseThumbnail.setCover_code_image(coverPhoto);

                                                                    detailListCourseThumbnail.add(modelCourseThumbnail);

                                                                    //model.setmArrayListThumbnails(detailListCourseThumbnail);

                                                                    //for parsing Updated by strings
                                                                    JSONObject jObjectUpdatedBy = objectCourse2.getJSONObject("UpdatedBy");

                                                                    detailList9 = new ArrayList<DetailDataModelCourses>();
                                                                    DetailDataModelCourses model9 = new DetailDataModelCourses();

                                                                    String education_statusUpdatedBy = jObjectUpdatedBy.getString("education_status");
                                                                    //String emailUpdatedBy = jObjectUpdatedBy.getString("email");
                                                                    String idUpdatedBy = jObjectUpdatedBy.getString("id");
                                                                    //String nameUpdatedBy = jObjectUpdatedBy.getString("name");
                                                                    //String phoneUpdatedBy = jObjectUpdatedBy.getString("phone");
                                                                    //String UserInfoUpdatedBy = jObjectUpdatedBy.getString("UserInfo");
                                                                    //String usernameUpdatedBy = jObjectUpdatedBy.getString("username");

                                                                    model9.setEducation_statusUpdatedBy(education_statusUpdatedBy);
                                                                    //model9.setEmailUpdatedBy(emailUpdatedBy);
                                                                    model9.setIdUpdatedBy(idUpdatedBy);
                                                                    //model9.setNameUpdatedBy(nameUpdatedBy);
                                                                    //model9.setPhoneUpdatedBy(phoneUpdatedBy);
                                                                    //model9.setUserInfoUpdatedBy(UserInfoUpdatedBy);
                                                                    //model9.setUsernameUpdatedBy(usernameUpdatedBy);

                                                                    //for parsing owner strings
                                                                    JSONObject jObjectOwner = objectCourse2.getJSONObject("owner");

                                                                    DetailDataModelCourses model10 = new DetailDataModelCourses();

                                                                    String updated_at_owner = jObjectOwner.getString("updated_at");
                                                                    String institution_name_owner = jObjectOwner.getString("institution_name");
                                                                    String id_owner = jObjectOwner.getString("id");
                                                                    String created_at_owner = jObjectOwner.getString("created_at");

                                                                    model10.setUpdated_at_owner(updated_at_owner);
                                                                    model10.setInstitution_name_owner(institution_name_owner);
                                                                    model10.setId_owner(id_owner);
                                                                    model10.setCreated_at_owner(created_at_owner);

                                                                    detailList10.add((model10));

                                                                    //for parsing created by strings
                                                                    JSONObject jObjectCreatedBy = objectCourse2.getJSONObject("CreatedBy");

                                                                    detailList11 = new ArrayList<DetailDataModelCourses>();
                                                                    DetailDataModelCourses model11 = new DetailDataModelCourses();

                                                                    String education_statusCreatedBy = jObjectCreatedBy.getString("education_status");
                                                                    //String emailCreatedBy = jObjectCreatedBy.getString("email");
                                                                    String idCreatedBy = jObjectCreatedBy.getString("id");
                                                                    //String nameCreatedBy = jObjectCreatedBy.getString("name");
                                                                    //String phoneCreatedBy = jObjectCreatedBy.getString("phone");
                                                                    //String UserInfoCreatedBy = jObjectCreatedBy.getString("UserInfo");
                                                                    //String usernameCreatedBy = jObjectCreatedBy.getString("username");

                                                                    model11.setEducation_statusUpdatedBy(education_statusCreatedBy);
                                                                    //model11.setEmailUpdatedBy(emailCreatedBy);
                                                                    model11.setIdCreatedBy(idCreatedBy);
                                                                    //model11.setNameCreatedBy(nameCreatedBy);
                                                                    //model11.setPhoneCreatedBy(phoneCreatedBy);
                                                                    //model11.setUserInfoCreatedBy(UserInfoCreatedBy);
                                                                    //model11.setUsernameCreatedBy(usernameCreatedBy);

                                                                    // parsing from syllebus
                                                                    try {
                                                                        mContentArrayListNew = new ArrayList<>();
                                                                        mUnit1DataArrayList = new ArrayList<>();
                                                                        mUnit2DataArrayList = new ArrayList<>();
                                                                        mUnit3DataArrayList = new ArrayList<>();
                                                                        mUnit4DataArrayList = new ArrayList<>();

                                                                        mVideoPulseMulti = new ArrayList<>();

                                                                        for (int ii = 0; ii < jObject.length() - 1; ii++) {
                                                                            JSONObject jSObject2 = jObject.getJSONObject("" + ii);

                                                                            detailList6 = new ArrayList<DetailDataModelCourses>();

                                                                            DetailDataModelCourses model6 = new DetailDataModelCourses();

                                                                            JSONArray jSonLessons = (JSONArray) jSObject2.getJSONArray("lessons");

                                                                            try {
                                                                                for (int m = 0; m < jSonLessons.length(); m++) {

                                                                                    JSONObject objectAgainAnotherLesson = (JSONObject) jSonLessons.get(m);

                                                                                    String idLesson = objectAgainAnotherLesson.getString("id");
                                                                                    String nameLesson = objectAgainAnotherLesson.getString("name");
                                                                                    String orderLesson = objectAgainAnotherLesson.getString("order");
                                                                                    String fixedLesson = objectAgainAnotherLesson.getString("fixed");

                                                                                    model6.setIdLesson(idLesson);
                                                                                    model6.setNameLesson(nameLesson);
                                                                                    model6.setOrderLessom(orderLesson);
                                                                                    model6.setFixedLesson(fixedLesson);
                                                                                }
                                                                            } catch (Exception ex) {
                                                                                Log.d("", "onResponse: ");
                                                                            }

                                                                            // for parsing "data" > {0} > {0} > "syllebus" > "0" > "data"

                                                                            try {
                                                                                //TODO
//                                                               mContentArrayListNew = new ArrayList<>();

                                                                                for (int lmn = 0; lmn < jSObject2.length() - 1; lmn++) {
                                                                                    JSONObject jSObject3 = jSObject2.getJSONObject("" + lmn);

                                                                                    DetailDataModelCoursesDetailContents modelUnitCourseContents = new DetailDataModelCoursesDetailContents();
                                                                                    JSONObject jObjAgain = jSObject3.getJSONObject("data");

                                                                                    //*String allow_preview = jObjAgain.getString("allow_preview");
                                                                                    String ans_rand = jObjAgain.getString("ans_rand");
                                                                                    String attempt = jObjAgain.getString("attempt");
                                                                                    String choose_video_type = jObjAgain.getString("choose_video_type");
                                                                                    String content_type = jObjAgain.getString("content_type");
                                                                                    String desc = jObjAgain.getString("desc");
                                                                                    String downloadable = jObjAgain.getString("downloadable");
                                                                                    String mDuration = jObjAgain.getString("duration");
                                                                                    String forward = jObjAgain.getString("forward");
                                                                                    String peer_limit = jObjAgain.getString("peer_limit");
                                                                                    String peer_review = jObjAgain.getString("peer_review");
                                                                                    String pulse = jObjAgain.getString("pulse");
                                                                                    String ques_rand = jObjAgain.getString("ques_rand");
                                                                                    String quizYesOrNot = jObjAgain.getString("quiz");
                                                                                    String time_unit = jObjAgain.getString("time_unit");
                                                                                    String mTitle = jObjAgain.getString("title");

                                                                                    // model2.setmAllowPreview(allow_preview);
                                                                                    modelUnitCourseContents.setmAnsRand(ans_rand);
                                                                                    modelUnitCourseContents.setmAttempt(attempt);
                                                                                    modelUnitCourseContents.setmChooseVideoType(choose_video_type);
                                                                                    modelUnitCourseContents.setmContentType(content_type);
                                                                                    modelUnitCourseContents.setmDesc(desc);
                                                                                    modelUnitCourseContents.setmDownloadable(downloadable);
                                                                                    modelUnitCourseContents.setmDurationAnother(mDuration);
                                                                                    modelUnitCourseContents.setmForward(forward);
                                                                                    modelUnitCourseContents.setmPeerLimit(peer_limit);
                                                                                    modelUnitCourseContents.setmPeerReview(peer_review);
                                                                                    modelUnitCourseContents.setmPulse(pulse);
                                                                                    modelUnitCourseContents.setmQuesRand(ques_rand);
                                                                                    modelUnitCourseContents.setmQuiz(quizYesOrNot);
                                                                                    modelUnitCourseContents.setmTimeUnit(time_unit);

                                                                                    //let's add

                                                                                    if (content_type.equalsIgnoreCase("exam")) {
                                                                                        mUnit2DataArrayList.add(modelUnitCourseContents);
                                                                                    }
                                                                                    else if (content_type.equalsIgnoreCase("assignment")) {
                                                                                        mUnit3DataArrayList.add(modelUnitCourseContents);
                                                                                    }
                                                                                    else if (content_type.equalsIgnoreCase("quiz")) {

                                                                                        String mQuizMakrs = jObjAgain.getString("marks");
                                                                                        String mQuizTime = jObjAgain.getString("time");

                                                                                        modelUnitCourseContents.setmQuizMarks(mQuizMakrs);
                                                                                        modelUnitCourseContents.setmQuizTime(mQuizTime);

                                                                                        mUnit4DataArrayList.add(modelUnitCourseContents);
                                                                                    }
                                                                                    else {
                                                                                        mUnit1DataArrayList.add(modelUnitCourseContents);
                                                                                    }

                                                                                    //model2.setmTitleAnother(mTitle);//*

                                                                                    // For parsing "file_type" > "data" > {0} > {0} > "syllebus" > "0" > "data"

                                                                                    JSONObject jObjAgain2 = jObjAgain.getJSONObject("file_type");

                                                                                    detailListFileType = new ArrayList<DetailDataModelCourses>();
                                                                                    DetailDataModelCourses modelFileType = new DetailDataModelCourses();

                                                                                    String pdf = jObjAgain2.getString("pdf");
                                                                                    String excel = jObjAgain2.getString("excel");
                                                                                    String doc = jObjAgain2.getString("doc");
                                                                                    String csv = jObjAgain2.getString("csv");

                                                                                    modelFileType.setmPdf(pdf);
                                                                                    modelFileType.setmXcel(excel);
                                                                                    modelFileType.setmDoc(doc);
                                                                                    modelFileType.setmCsv(csv);

                                                                                    try {
                                                                                        DetailDataModelCoursesDetailContents modelCourseContents = new DetailDataModelCoursesDetailContents();

                                                                                        if(choose_video_type.equalsIgnoreCase("1")){
                                                                                            JSONObject jObjAgainContent = jSObject3.getJSONObject("content");

                                                                                            String cat_id = jObjAgainContent.getString("cat_id");
                                                                                            String content_id = jObjAgainContent.getString("content_id");
                                                                                            String copy_protect = jObjAgainContent.getString("copy_protect");
                                                                                            String cover_thumb_img = jObjAgainContent.getString("cover_thumb_img");
                                                                                            String created_at_content = jObjAgainContent.getString("created_at");
                                                                                            String created_by_content = jObjAgainContent.getString("created_by");
                                                                                            String deleted_at_content = jObjAgainContent.getString("deleted_at");
                                                                                            String description_content = jObjAgainContent.getString("description");
                                                                                            String file_encode_path = jObjAgainContent.getString("file_encode_path");
                                                                                            String file_name = jObjAgainContent.getString("file_name");
                                                                                            String id_content = jObjAgainContent.getString("id");
                                                                                            String license = jObjAgainContent.getString("license");
                                                                                            String owner_id = jObjAgainContent.getString("owner_id");
                                                                                            String paid = jObjAgainContent.getString("paid");
                                                                                            String price = jObjAgainContent.getString("price");
                                                                                            String shareable = jObjAgainContent.getString("shareable");
                                                                                            String size = jObjAgainContent.getString("size");
                                                                                            String status_content = jObjAgainContent.getString("status");
                                                                                            String tags = jObjAgainContent.getString("tags");
                                                                                            String title_content = jObjAgainContent.getString("title");
                                                                                            String type_content = jObjAgainContent.getString("type");
                                                                                            String updated_at_content = jObjAgainContent.getString("updated_at");
                                                                                            String updated_by_content = jObjAgainContent.getString("updated_by");

                                                                                            modelCourseContents.setPaid(paid);
                                                                                            modelCourseContents.setPrice(price);
                                                                                            modelCourseContents.setShareable(shareable);
                                                                                            modelCourseContents.setStatus_content(status_content);
                                                                                            modelCourseContents.setSize(size);
                                                                                            modelCourseContents.setTags(tags);
                                                                                            modelCourseContents.setTitle_content(title_content);
                                                                                            modelCourseContents.setType_content(type_content);
                                                                                            modelCourseContents.setUpdated_at_content(updated_at_content);
                                                                                            modelCourseContents.setUpdated_by_content(updated_by_content);
                                                                                            modelCourseContents.setCat_id(cat_id);
                                                                                            modelCourseContents.setContent_id(content_id);
                                                                                            modelCourseContents.setCopy_protect(copy_protect);
                                                                                            modelCourseContents.setCover_thumb_img(cover_thumb_img);
                                                                                            modelCourseContents.setCreated_by_content(created_by_content);
                                                                                            modelCourseContents.setDeleted_at_content(deleted_at_content);
                                                                                            modelCourseContents.setmDesc(desc);
                                                                                            modelCourseContents.setFile_encode_path(file_encode_path);
                                                                                            modelCourseContents.setFile_name(file_name);
                                                                                            modelCourseContents.setId_content(id_content);
                                                                                            modelCourseContents.setLicense(license);
                                                                                            modelCourseContents.setOwner_id(owner_id);
                                                                                            modelCourseContents.setCreated_at_content(created_at_content);
                                                                                        }
                                                                                        else {
                                                                                            JSONArray jObjAgainContent = jSObject3.getJSONArray("content");
                                                                                        }

                                                                                        mContentArrayListNew.add(modelCourseContents);
                                                                                    } catch (Exception ex) {
                                                                                        Log.d("", "onResponse: ");
                                                                                    }

                                                                                    //For parsing Quizes

                                                                                    //JSONArray jObjQuizes = (JSONArray) jSObject3.getJSONArray("multi_ques_list");

                                                                                    // For parsing object "Content" > {0} > {0} > "syllebus" > "0" > "data"

                                                                                    detailList5 = new ArrayList<DetailDataModelCourses>();

                                                                                    DetailDataModelCourses model5 = new DetailDataModelCourses();

                                                                                    if (content_type.equalsIgnoreCase("quiz")) {
                                                                                        try {
                                                                                            for (int ctq = 0; ctq < jSObject3.length(); ctq++) {

                                                                                                JSONArray jSonObjMultiQ2 = (JSONArray) jSObject3.getJSONArray("ques_list");

                                                                                                mUnitQuizList = new ArrayList<>();
                                                                                                mUnitQuizList2 = new ArrayList<>();


                                                                                                mUnitQuizListWithOptions = new ArrayList<>();

                                                                                                for (int qlist2 = 0; qlist2 < jSonObjMultiQ2.length(); qlist2++) {

                                                                                                    DetailDataModelCoursesDetailContents modelUnitQuizElements2 = new DetailDataModelCoursesDetailContents();

                                                                                                    DetailDataModelCoursesDetailContents modelUnitQuizListWithOptions = new DetailDataModelCoursesDetailContents();

                                                                                                    JSONObject objectAgainAnother2 = (JSONObject) jSonObjMultiQ2.get(qlist2);

                                                                                                    String qTitle = objectAgainAnother2.getString("title");
                                                                                                    String qDesc = objectAgainAnother2.getString("description");

                                                                                                    qTitle = qTitle.replace("<p>", "");
                                                                                                    qTitle = qTitle.replace("</p>", "");

                                                                                                    qDesc = qDesc.replace("<p>", "");
                                                                                                    qDesc = qDesc.replace("</p>", "");

                                                                                                    modelUnitQuizElements2.setmQuizTitle(qTitle);
                                                                                                    modelUnitQuizElements2.setmSummeryDesc(qDesc);
                                                                                                    mUnitQuizList.add(modelUnitQuizElements2);

                                                                                                    modelUnitQuizListWithOptions.setmQuizTitle(qTitle);
                                                                                                    mUnitQuizListWithOptions.add(modelUnitQuizListWithOptions);


                                                                                                    JSONArray jSonObjMultiQOptions = (JSONArray) objectAgainAnother2.getJSONArray("options");

                                                                                                    try {

                                                                                                        mUnitQuizOptList = new ArrayList<>();

                                                                                                        for (int optionList = 0; optionList < jSonObjMultiQOptions.length(); optionList++) {

                                                                                                            //DetailDataModelCoursesDetailContents modelUnitQuizOptions = new DetailDataModelCoursesDetailContents();
                                                                                                            DetailDataModelCoursesDetailContents modelUnitQuizOptions = new DetailDataModelCoursesDetailContents();

                                                                                                            JSONObject jObjectQuesOpt = (JSONObject) jSonObjMultiQOptions.get(optionList);

                                                                                                            String optionBody = jObjectQuesOpt.getString("body");
                                                                                                            String optionAnswer = jObjectQuesOpt.getString("answer");

                                                                                                            modelUnitQuizOptions.setmOptionBody(optionBody);
                                                                                                            modelUnitQuizOptions.setmOptionAnswer(optionAnswer);

                                                                                                            mUnitQuizOptList.add(modelUnitQuizOptions);
                                                                                                        }
                                                                                                    } catch (Exception ex) {
                                                                                                        Log.d("", "onResponse: ");
                                                                                                    }

                                                                                                    mUnitQuizList2.add(mUnitQuizOptList);
                                                                                                }
                                                                                            }
                                                                                        } catch (Exception ex) {
                                                                                            Log.d("", "onResponse: ");
                                                                                        }
                                                                                    }
                                                                                    else if (content_type.equalsIgnoreCase("exam")) {
                                                                                        try {
                                                                                            for (int ctqexam = 0; ctqexam < jSObject3.length(); ctqexam++) {

                                                                                                JSONArray jSonObjMultiQ2exam = (JSONArray) jSObject3.getJSONArray("ques_list");

                                                                                                mUnitQuizListExam = new ArrayList<>();

                                                                                                for (int qlist2 = 0; qlist2 < jSonObjMultiQ2exam.length(); qlist2++) {

                                                                                                    DetailDataModelCoursesDetailContents modelUnitQuizElements2 = new DetailDataModelCoursesDetailContents();

                                                                                                    JSONObject objectAgainAnother2 = (JSONObject) jSonObjMultiQ2exam.get(qlist2);

                                                                                                    String qTitle = objectAgainAnother2.getString("title");

                                                                                                    qTitle = qTitle.replace("<p>", "");
                                                                                                    qTitle = qTitle.replace("</p>", "");

                                                                                                    modelUnitQuizElements2.setmQuizTitle(qTitle);
                                                                                                    mUnitQuizListExam.add(modelUnitQuizElements2);
                                                                                                }
                                                                                            }
                                                                                        } catch (Exception ex) {
                                                                                            Log.d("", "onResponse: ");
                                                                                        }
                                                                                    }
                                                                                    multiQKey++;

                                                                                    if (quizYesOrNot.equalsIgnoreCase("1")) {
                                                                                        try {
                                                                                            for (int l = 0; l < jSObject3.length(); l++) {
                                                                                                JSONArray jSonObjMultiQ = new JSONArray();

                                                                                                jSonObjMultiQ = (JSONArray) jSObject3.getJSONArray("multi_ques_list");

                                                                                                mContentArrayListNewPulse = new ArrayList<>();

                                                                                                try {
                                                                                                    for (int mql = 0; mql < jSonObjMultiQ.length(); mql++) {

                                                                                                        DetailDataModelCoursesDetailContents modelVideoMultiPulse = new DetailDataModelCoursesDetailContents();

                                                                                                        JSONObject objectAgainAnother2 = (JSONObject) jSonObjMultiQ.get(mql);

                                                                                                        String mPulse = objectAgainAnother2.getString("pulse");

                                                                                                        modelVideoMultiPulse.setmPulseOfVideoMulti(mPulse);
                                                                                                        modelVideoMultiPulse.setmPulseOfVideoMultiId(multiQKey);

                                                                                                        try {
                                                                                                            mPulseQuizList2 = new ArrayList<>();

                                                                                                            for (int qs = 0; qs < objectAgainAnother2.length(); qs++) {

                                                                                                                JSONArray jSonObjMultiQuizes = (JSONArray) objectAgainAnother2.getJSONArray("ques_list");

                                                                                                                try {
                                                                                                                    mUnitQuizListMp = new ArrayList<>();

                                                                                                                    mUnitQuizListWithOptionsMp = new ArrayList<>();

                                                                                                                    for (int qlist = 0; qlist < jSonObjMultiQuizes.length(); qlist++) {

                                                                                                                        DetailDataModelCoursesDetailContents modelPulseQuizListWithOptionsMp = new DetailDataModelCoursesDetailContents();

                                                                                                                        JSONObject jSObjectQuizElements = jSonObjMultiQuizes.getJSONObject(qlist);

                                                                                                                        String mqTitle = jSObjectQuizElements.getString("title");

                                                                                                                        mqTitle = mqTitle.replace("<p>", "");
                                                                                                                        mqTitle = mqTitle.replace("</p>", "");

                                                                                                                        // modelPulseQuizListWithOptionsMp.setMpQuizTitle(mqTitle);
                                                                                                                        // mUnitQuizListWithOptionsMp.add(modelPulseQuizListWithOptionsMp);

                                                                                                                        JSONArray jSonObjMultiQOptionsPulse = (JSONArray) jSObjectQuizElements.getJSONArray("options");

                                                                                                                        try {
                                                                                                                            mPulseQuizOptList = new ArrayList<>();

                                                                                                                            for (int optionList = 0; optionList < jSonObjMultiQOptionsPulse.length(); optionList++) {

                                                                                                                                DetailDataModelCoursesDetailContents modelPulseQuizOptions = new DetailDataModelCoursesDetailContents();

                                                                                                                                JSONObject jObjectQuesOpt = (JSONObject) jSonObjMultiQOptionsPulse.get(optionList);

                                                                                                                                String optionPulseBody = jObjectQuesOpt.getString("body");
                                                                                                                                String optionPulseAnswer = jObjectQuesOpt.getString("answer");

                                                                                                                                modelPulseQuizOptions.setMpOptionBody(optionPulseBody);
                                                                                                                                modelPulseQuizOptions.setMpOptionAnswer(optionPulseAnswer);
                                                                                                                                modelPulseQuizOptions.setMpQuizTitle(mqTitle);

                                                                                                                                mPulseQuizOptList.add(modelPulseQuizOptions);
                                                                                                                            }
                                                                                                                        } catch (Exception ex) {
                                                                                                                            Log.d("", "onResponse: ");
                                                                                                                        }
                                                                                                                        mPulseQuizList2.add(mPulseQuizOptList);
                                                                                                                    }
                                                                                                                } catch (Exception ex) {
                                                                                                                    Log.d("", "onResponse: ");
                                                                                                                }
                                                                                                            }
                                                                                                        } catch (Exception ex) {
                                                                                                            Log.d("", "onResponse: ");
                                                                                                        }
                                                                                                        mContentArrayListNewPulse.add(modelVideoMultiPulse);
                                                                                                    }
                                                                                                } catch (Exception ex) {
                                                                                                    Log.d("", "onResponse: ");
                                                                                                }
                                                                                            }
                                                                                            mVideoPulseMulti.add(mContentArrayListNewPulse);
                                                                                        } catch (Exception ex) {
                                                                                            Log.d("", "onResponse: ");
                                                                                        }
                                                                                    } else {

                                                                                    }
                                                                                }
                                                                            } catch (Exception ex) {
                                                                                Log.d("", "onResponse: ");
                                                                            }
                                                                        }
                                                                    } catch (Exception ex) {
                                                                        Log.d("", "onResponse: ");
                                                                    }
                                                                } catch (Exception ex) {
                                                                    Log.d("", "onResponse: ");
                                                                }

                                                                detailListCourseDetailUnitQuizOptList.add(mUnitQuizList2);
                                                                detailListCourseDetailPulseQuizOptList.add(mPulseQuizList2);

                                                                detailListCourseDetailVideoPulseMulti.add(mVideoPulseMulti);

                                                                detailListCourseDetailContentss.add(mContentArrayListNew);

                                                                //detailListVideoPulse.add(mContentArrayListNewPulse);

                                                                detailListCourseDetailUnits.add(mUnitArrayListNew);
                                                                detailListCourseDetailUnitQuizList.add(mUnitQuizList);
                                                                detailListCourseDetailUnitQuizListExam.add(mUnitQuizListExam);

                                                                //detailListCourseDetailUnitQuizOptList.add(mUnitQuizListWithOptions);

                                                                //TODO
                                                                //detailListCourseDetailUnitQuizOptList.add(mUnitQuizOptList);

                                                                // for unit arrays
                                                                detailListCourseUnit1Data.add(mUnit1DataArrayList);
                                                                detailListCourseUnit2Data.add(mUnit2DataArrayList);
                                                                detailListCourseUnit3Data.add(mUnit3DataArrayList);
                                                                detailListCourseUnit4Data.add(mUnit4DataArrayList);

                                                                GlobalVar.gEnrolledInstitution = detailList10;
                                                            }
                                                        } catch (Exception ex) {
                                                            Log.d("", "onResponse: ");
                                                        }
                                                    } catch (Exception ex) {
                                                        Log.d("", "onResponse: ");
                                                    }

                                                    modelAlter.setmUnitDataArrayListContent(detailListCourseUnit1Data);
                                                    modelAlter.setmUnitDataArrayListContent2(detailListCourseUnit2Data);
                                                    modelAlter.setmUnitDataArrayListContent3(detailListCourseUnit3Data);
                                                    modelAlter.setmUnitDataArrayListContent4(detailListCourseUnit4Data);

                                                    modelAlter.setmUserInformationArrayList(detailListUserInformation);



                                                    modelAlter.setmArrayListThumbnails(detailListCourseThumbnail);
                                                    modelAlter.setmArrayListMarks(mPassPercentageArrayList);
                                                    modelAlter.setmArrayListContentDetails(detailListCourseDetailContentss);

                                                    modelAlter.setmArrayListCourseUnits(detailListCourseDetailUnits);
                                                    modelAlter.setmArrayListCourseQuizs(detailListCourseDetailUnitQuizList);
                                                    modelAlter.setmArrayListCourseQuizsExam(detailListCourseDetailUnitQuizListExam);

                                                    //for option list of quest list
                                                    modelAlter.setmArrayListCourseQuizOptions(detailListCourseDetailUnitQuizOptList);
                                                    modelAlter.setmArrayListCoursePulseQuizOptions(detailListCourseDetailPulseQuizOptList);

                                                    modelAlter.setmArrayListCourseVideoPulseMulti(detailListCourseDetailVideoPulseMulti);

                                                    detailListCourse.add(modelAlter);

                                                    /*ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> contentArray = detailListCourse.get(0).getmArrayListContentDetails();

                                                    mArrayList = contentArray.get(0).toArray();

                                                    GlobalVar.gChildArrayOfContentMyPage=mArrayList;*/

                                                    GlobalVar.courseContentDetailList = detailListCourse;

                                                    //GlobalVar.gEnrollCourseQuestionList= GlobalVar.courseContentDetailList.get(i).getmArrayListCourseQuizs();
                                                } catch (Exception ex) {
                                                    Log.d("", "onResponse: ");
                                                }

                                                mProgressDialog.dismiss();

                                                Intent i = new Intent(mContext, SelectACategoryActivity.class);
                                                startActivity(i);
                                            }

                                            catch (Exception ex) {
                                                Log.e("tag", "Couldn't get json from server.");
                                            }

                                            else {
                                                Toast.makeText(mContext, "You must fill the email field.", Toast.LENGTH_LONG).show();
                                            }

                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                    Log.e("TAG", error.getMessage(), error);

                                    mEmailView.requestFocus();
                                    mEmailView.setError("Invalid email.");
                                    mEdtTxtPwd.requestFocus();
                                    mEdtTxtPwd.setError("Invalid password.");
                                }
                            })
                            { //no semicolon or coma
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
                    }
                    else {
                        mEmailView.setError("Invalid email.");
                        mEmailView.requestFocus();
                    }
                }

            }
        });





    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }
}

