package orangebd.newaspaper.mymuktopathapp;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.AsyncTask;
import android.provider.Settings;
import android.provider.SyncStateContract;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MyPageActivity extends AppCompatActivity {

    TabsPagerAdapterDetail myAdapter;
    SlidingTabLayout mSlidingTabLayout;

    private Context mContext;
    ImageView imageLogo;

    private TextView mCustomLogoText;

    String url = GlobalVar.gApiBaseUrl + "/api/login";
    String ShareURL;
    String title;
    String strRelatedUrl;

    private int newsIndex;

    private ProgressDialog mProgressDialog;

    //All ArrayLists

    private ArrayList<DetailDataModelCoursesDetailContents> detailList;
    private ArrayList<DetailDataModelCourses> detailList2=new ArrayList<>();
    private ArrayList<DetailDataModelCourses> detailList3=new ArrayList<>();

    //All the detail Lists
    private ArrayList<DetailDataModelCourses> detailList5;
    private ArrayList<DetailDataModelCourses> detailList6;
    private ArrayList<DetailDataModelCourses> detailList7;
    private ArrayList<DetailDataModelCourses> detailList8;
    private ArrayList<DetailDataModelCourses> detailList9;
    private ArrayList<DetailDataModelCourses> detailList10;
    private ArrayList<DetailDataModelCourses> detailList11;

    ArrayList<DetailDataModelCoursesDetailContents> mUnitQuizListExam;
    private ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> detailListCourseDetailUnitQuizListExam;

    private ArrayList<DetailDataModelCourses> detailListAnoPart3=new ArrayList<>();

    private ArrayList<DetailDataModelCoursesDetailContents> detailListUserInformation=new ArrayList<>();

    private ArrayList<DetailDataModelCourses> detailListCourse;

    private ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> detailListCourseDetailContentss;
    private ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> detailListCourseDetailUnits;
    private ArrayList<ArrayList<ArrayList<DetailDataModelCoursesDetailContents>>> detailListCourseDetailLesson;
    private ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> detailListCourseDetailUnitQuizList;

    private ArrayList<ArrayList<ArrayList<DetailDataModelCoursesDetailContents>>> detailListCourseDetailUnitQuizOptList;
    private ArrayList<ArrayList<ArrayList<DetailDataModelCoursesDetailContents>>> detailListCourseDetailPulseQuizOptList;
    private ArrayList<ArrayList<ArrayList<DetailDataModelCoursesDetailContents>>> detailListCourseDetailVideoPulseMulti;

    //for declaring arraylist of units
    private ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> detailListCourseUnit1Data;
    private ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> detailListCourseUnit2Data;
    private ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> detailListCourseUnit3Data;
    private ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> detailListCourseUnit4Data;


    private ArrayList<ArrayList<ArrayList<DetailDataModelCoursesDetailContents>>> detailListCourseUnitAllData;

    //TODO

    private ArrayList<DetailDataModelCoursesThumbnails> detailListCourseThumbnail;

    private ArrayList<DetailDataModelCoursesMarks> mPassPercentageArrayList;

    private ArrayList<DetailDataModelCourses> detailListFileType;

    ArrayList<DetailDataModelCoursesDetailContents> mContentArrayListNew;

    ArrayList<DetailDataModelCoursesDetailContents> mEnrollCourseArrayList;

    ArrayList<DetailDataModelCoursesDetailContents> mUnitArrayListNew;
    ArrayList<DetailDataModelCoursesDetailContents> mLessonArrayListNew;

    ArrayList<DetailDataModelCoursesDetailContents> mUnitQuizList;
    ArrayList<DetailDataModelCoursesDetailContents> mUnitQuizListMp;
    ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> mUnitQuizList2;
    ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> mPulseQuizList2;

    ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> mVideoPulseMulti;

    ArrayList<DetailDataModelCoursesDetailContents> mUnitQuizListWithOptions;

    ArrayList<DetailDataModelCoursesDetailContents> mUnitQuizListWithOptionsMp;
    ArrayList<DetailDataModelCoursesDetailContents> mUnitQuizOptList;
    ArrayList<DetailDataModelCoursesDetailContents> mPulseQuizOptList;
    ArrayList<DetailDataModelCoursesDetailContents> mContentArrayListNewPulse;

    ArrayList<DetailDataModelCoursesDetailContents> mUnit1DataArrayList;
    ArrayList<DetailDataModelCoursesDetailContents> mUnit2DataArrayList;
    ArrayList<DetailDataModelCoursesDetailContents> mUnit3DataArrayList;
    ArrayList<DetailDataModelCoursesDetailContents> mUnit4DataArrayList;

    ArrayList<DetailDataModelCoursesDetailContents> detailUnitNumbers;

    ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> detailUnitArrayNumbers;

    ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> lessonsIntoIndexs;

    ArrayList<DetailDataModelCoursesDetailContents> mUnitAllArrayList;

    private ArrayList<DetailDataModelCoursesDetailContents> detailListCourseDetailContents;

    private ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> detailListCourseDetailUnitQuizList2;

    private ArrayList<DetailDataModelCourses> detailListAnoPart1=new ArrayList<>();
    private ArrayList<DetailDataModelCourses> detailListAnoPart2=new ArrayList<>();

    private int multiQKey=-1;

    private LinearLayout allCourseBtn;
    private LinearLayout recomendedBtn;
    private LinearLayout myPageBtn;
    private LinearLayout downloadsBtn;
    private LinearLayout profileBtn;

    private TextView enrolledCourse;

    private ArrayList<DetailDataModelCourses> detailList4=new ArrayList<>();

    private HashMap<String, String> map;
    private String token="";

    private SessionManager sm;

    private String totalQuizNumber;

    private ImageView mSettingsBtn;

    // for uni wise arrays

    private ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> detailListCourseDetailUnit1;
    private ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> detailListCourseDetailUnit2;
    private ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> detailListCourseDetailUnit3;

    private static final float thresholdOffset = 0.5f;
    private static final int thresholdOffsetPixels = 1;
    private boolean scrollStarted, checkDirection;


    private RelativeLayout mRelativeBackground;
    private LinearLayout mNoEnroledCourseFound;


    //Lesson journey status list
    private ArrayList<DetailDataModelCoursesDetailContents> lessonJourneyStatusList;
    private ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> unitJourneyStatusList;
    private ArrayList<ArrayList<ArrayList<DetailDataModelCoursesDetailContents>>> courseJourneyStatusList;




    // for wishList

    ArrayList<DetailDataModelProfileButtons> wishListArray;


    private TextView myPageTextView;

    private LinearLayout mFiveFooterBtnsLayOut;


    private Button mFindNewCourseBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        mContext=this;

        GlobalVar.gIncompletedCourseCount=0;

        //getSupportActionBar().hide();

        allCourseBtn = findViewById(R.id.allCourseBtnId);
        recomendedBtn = findViewById(R.id.recomendedBtnId);
        myPageBtn = findViewById(R.id.myPageBtnId);
        downloadsBtn = findViewById(R.id.downloadsBtnId);
        profileBtn = findViewById(R.id.profilePageBtnId);

        mFindNewCourseBtn=findViewById(R.id.findNewCourseBtn);
        mFindNewCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, MainActivity.class);
                v.getContext().startActivity(i);
            }
        });


        mRelativeBackground=findViewById(R.id.drawerLayout);
        mNoEnroledCourseFound=findViewById(R.id.noEnroledCourseFound);


        myPageTextView=findViewById(R.id.myPageTextId);
        mFiveFooterBtnsLayOut=findViewById(R.id.fiveFooterBtnsLayOut);


        /**get device height and width*/

        double deviceHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

        ViewPager vpPager = findViewById(R.id.VideoSliderviewPagerId);

        /*int previousHeight= mTableLayout.getHeight();
        int previousWidth = mTableLayout.getWidth();*/

        double calculatedHeight = (double) 840 / 1184;
        //double calculatedHeight = (double) 940 / 1184;
        calculatedHeight=calculatedHeight*deviceHeight;
        //int calculatedWidth = (previousHeight/720)*deviceWidth;

        double newHeight = calculatedHeight;
        //int newWidth = calculatedWidth;

        ViewGroup.LayoutParams params = vpPager.getLayoutParams();
        params.height = (int)newHeight;
        vpPager.setLayoutParams(params);
        vpPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (checkDirection) {
                    if (thresholdOffset > positionOffset) {


                        GlobalVar.gGoingDirection="right";
                    }
                    else {

                        GlobalVar.gGoingDirection="left";
                    }
                    checkDirection = false;
                }
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (!scrollStarted && state == ViewPager.SCROLL_STATE_DRAGGING) {
                    scrollStarted = true;
                    checkDirection = true;
                } else {
                    scrollStarted = false;
                }
            }
        });


        /***/


        mSettingsBtn=findViewById(R.id.settingsBtn);
        mSettingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(mContext, SettingsActivity.class);
                v.getContext().startActivity(i);
            }
        });

        sm= new SessionManager(mContext);

        HashMap<String, String> mSpInfo=sm.getUserDetails();

        final String emailFromCache = mSpInfo.get("email");
        final String passwordFromChache = mSpInfo.get("password");
        final String phoneFromChache = mSpInfo.get("phone");

        map = new HashMap<String, String>();

        token = GlobalVar.getTokenArray.get(0).getmAccessToken();
        String firstWord = GlobalVar.getTokenArray.get(0).getmTokenType();

        token = firstWord + " " + token;

        GlobalVar.gReplacingTokenForAllCategories=token;

        JSONObject object=new JSONObject();

        try {


            if(emailFromCache!=null) {
                object.put("email", emailFromCache);
                object.put("password", passwordFromChache);
                object.put("type", "1");
            }
            else{
                object.put("phone", phoneFromChache);
                object.put("password", passwordFromChache);
                object.put("type", "2");
            }

        }
        catch (Exception ex){
            Log.d("", "onClick: ");
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

                            detailList = new ArrayList<DetailDataModelCoursesDetailContents>();

                            detailListCourseThumbnail = new ArrayList<DetailDataModelCoursesThumbnails>();

                            mPassPercentageArrayList = new ArrayList<DetailDataModelCoursesMarks>();

                            detailListCourseDetailContents = new ArrayList<DetailDataModelCoursesDetailContents>();
                            detailList10 = new ArrayList<DetailDataModelCourses>();

                            detailList7 = new ArrayList<DetailDataModelCourses>();
                            detailList8 = new ArrayList<DetailDataModelCourses>();

                            detailListCourseDetailContentss = new ArrayList<>();
                            detailListCourseDetailUnits = new ArrayList<>();
                            detailListCourseDetailLesson = new ArrayList<>();
                            detailListCourseDetailUnitQuizList = new ArrayList<>();
                            detailListCourseDetailUnitQuizList2 = new ArrayList<>();

                            detailListCourseDetailUnitQuizOptList = new ArrayList<>();
                            detailListCourseDetailPulseQuizOptList = new ArrayList<>();
                            detailListCourseDetailVideoPulseMulti = new ArrayList<>();

                            detailListCourseDetailUnitQuizListExam = new ArrayList<>();

                            //for renew arraylist of units arrays
                            detailListCourseUnit1Data = new ArrayList<>();
                            detailListCourseUnit2Data = new ArrayList<>();
                            detailListCourseUnit3Data = new ArrayList<>();
                            detailListCourseUnit4Data = new ArrayList<>();


                            detailListCourseUnitAllData = new ArrayList<>();

                           // DetailDataModelCourses model = new DetailDataModelCourses();
                            DetailDataModelCoursesDetailContents model = new DetailDataModelCoursesDetailContents();

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
                                String profilecompleteness = jObjectData.getString("profilecompleteness");
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
                                GlobalVar.gProfileCompleteness = profilecompleteness;

                                model.setmId(id);
                                model.setmUserName(username);
                                model.setmName(name);
                                model.setmUserEmail(email);
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


                                courseJourneyStatusList = new ArrayList<>();


                                try {
                                    detailListUserInformation = new ArrayList<DetailDataModelCoursesDetailContents>();

                                    DetailDataModelCourses modelEnrollCourse = new DetailDataModelCourses();

                                    DetailDataModelCoursesDetailContents modelUserInformation = new DetailDataModelCoursesDetailContents();

                                    JSONArray objectAgainAnother =  jObjectData.getJSONArray("RoleInstitution");
                                    JSONArray objectEnrollCourse =  jObjectData.getJSONArray("EnrollCourse");


                                    JSONArray favoriteCourseArray =  jObjectData.getJSONArray("FavoriteCategoryList");

                                    GlobalVar.gRecommendedCategories= new ArrayList<>();

                                    try {
                                        for (int favid = 0; favid < favoriteCourseArray.length(); favid++) {

                                            String ids = favoriteCourseArray.getString(favid);

                                            GlobalVar.gRecommendedCategories.add(ids);
                                        }
                                    }
                                    catch (Exception ex){
                                        Log.d("", "onResponse: ");
                                    }


                                    JSONArray objectWishList =  jObjectData.getJSONArray("WishList");

                                    JSONObject objectUserInformation = jObjectData.getJSONObject("UserInfo");

                                    String UserProfilePhoto = objectUserInformation.getString("photo_name");
                                    String UserCoverPhoto = objectUserInformation.getString("cover_image");

                                    modelUserInformation.setmUserProfilePhoto(UserProfilePhoto);
                                    modelUserInformation.setmUserCoverPhoto(UserCoverPhoto);

                                    detailListUserInformation.add(modelUserInformation);


                                    //wishList

                                    JSONObject ObjwListNumber = new JSONObject();

                                    wishListArray = new ArrayList<>();

                                    for(int wList=0; wList<objectWishList.length(); wList++){


                                        DetailDataModelProfileButtons wishListModel = new DetailDataModelProfileButtons();

                                        ObjwListNumber = objectWishList.getJSONObject(wList);


                                        String getWishListIds= ObjwListNumber.getString("id");

                                        wishListModel.setWishListId(getWishListIds);


                                        wishListArray.add(wishListModel);

                                        GlobalVar.gWishListArray=wishListArray;


                                        String abcdqwqwererrt="";
                                    }


                                    int enrolledCourseNumbers = objectEnrollCourse.length();


                                    GlobalVar.gWishListNumber =objectWishList.length();




                                    String aaaaaa = "";

                                    GlobalVar.gEnrollCourseId = new ArrayList<>();

                                    try {
                                        for (int ec = 0; ec < objectEnrollCourse.length(); ec++)
                                        {
                                            DetailDataModelCourses enrollCourseModel = new DetailDataModelCourses();

                                            JSONObject jObjEnrolledCourses = objectEnrollCourse.getJSONObject(ec);

                                            JSONObject objectCourse2 = jObjEnrolledCourses.getJSONObject("Course");

                                            String enrolCourseId= jObjEnrolledCourses.getString("id");
                                            String enrolCourseProgress= jObjEnrolledCourses.getString("course_completeness");

                                            double completedCourseDouble=Double.parseDouble(enrolCourseProgress);

                                            JSONArray exams = jObjEnrolledCourses.getJSONArray("exam");
                                            JSONArray assignments = jObjEnrolledCourses.getJSONArray("assignment");

                                            int examNumbers = exams.length();
                                            int assignmentsNumbers = assignments.length();

                                            DetailDataModelCourses modelForLoginCourse = new DetailDataModelCourses();

                                            String featured = objectCourse2.getString("featured");

                                            String admissionStatusForDate = objectCourse2.getString("admission_status");

                                            String course_end_date="";

                                            long courseEndDateInMillis=0;
                                            long currentDateInMillis=0;

                                            if(admissionStatusForDate.equalsIgnoreCase("0")) {
                                                course_end_date = objectCourse2.getString("end_date");


                                                String myDate = course_end_date;
                                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                                Date date = sdf.parse(myDate);
                                                courseEndDateInMillis = date.getTime();

                                                Date currentDate = new Date();
                                                String myCurrentDate = new SimpleDateFormat("yyyy-MM-dd").format(currentDate);
                                                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                                                Date date2 = sdf2.parse(myCurrentDate);
                                                currentDateInMillis = date2.getTime();
                                            }

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
                                            //String end_date = objectCourse2.getString("end_date");
                                            String enrolment_approval_status = objectCourse2.getString("enrolment_approval_status");
                                            String courseObjective = objectCourse2.getString("objective");

                                            courseObjective = courseObjective.replace("<p>", "");
                                            courseObjective = courseObjective.replace("</p>", "");

                                            Edetails = Edetails.replace("<p>", "");
                                            Edetails = Edetails.replace("</p>", "");

                                            modelForLoginCourse.setmCertificateAliasName(certificate_alias_name);
                                            modelForLoginCourse.setmAdmissionStatus(Eadmission_status);
                                            modelForLoginCourse.setmAverageRating(averageRating);
                                            modelForLoginCourse.setmCloneStatus(clone_status);
                                            modelForLoginCourse.setmCode(code);
                                            modelForLoginCourse.setmCreatedAt(created_at);
                                            modelForLoginCourse.setmDuration(duration);
                                            //modelForLoginCourse.setmEndDate(end_date);
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
                                            modelForLoginCourse.setmObjective(courseObjective);


                                            if(GlobalVar.isRedirectFromProfileEndedBtn==true){

                                                if(enrolCourseProgress.equalsIgnoreCase("100")) {

                                                    detailListAnoPart3.add(modelForLoginCourse);
                                                }
                                                else{

                                                    String nothingtest="";
                                                }

                                            }

                                            else if(GlobalVar.isRedirectFromProfileRunningBtn==true) {

                                                if (completedCourseDouble<100) {

                                                    detailListAnoPart3.add(modelForLoginCourse);
                                                }
                                                else{

                                                    String nothingisimpo="";
                                                }
                                            }

                                            else if(GlobalVar.isRedirectFromProfileNonEndedBtn==true) {

                                                if (completedCourseDouble<100) {

                                                    if(currentDateInMillis>courseEndDateInMillis) {

                                                        detailListAnoPart3.add(modelForLoginCourse);

                                                    }
                                                }
                                                else{

                                                    String nothingisimpo="";
                                                }
                                            }

                                            else if(GlobalVar.isRedirectFromProfileWishListBtn==true){

                                                for(int checkWishListId=0; checkWishListId<GlobalVar.gWishListArray.size(); checkWishListId++){

                                                    String getWishListIds = GlobalVar.gWishListArray.get(checkWishListId).getWishListId();

                                                    if (Eid.equalsIgnoreCase(getWishListIds)){

                                                        detailListAnoPart3.add(modelForLoginCourse);
                                                    }
                                                }
                                            }

                                            else if(completedCourseDouble<100) {

                                                detailListAnoPart3.add(modelForLoginCourse);
                                            }






                                            GlobalVar.gEnrollCourseList = detailListAnoPart3;

                                            try {
                                                jObject = objectCourse2.getJSONObject("syllabus");

                                                jObjectAllMarks = objectCourse2.getJSONObject("marks");

                                                JSONObject totalMarksQuiz=jObjectAllMarks.getJSONObject("quiz");
                                                JSONObject totalMarksExm=jObjectAllMarks.getJSONObject("exam");

                                                String courseQuizPassMark = totalMarksQuiz.getString("pass_marks");
                                                String courseExamPassMark = totalMarksExm.getString("pass_marks");

                                                DetailDataModelCoursesMarks modelCoursePassMark = new DetailDataModelCoursesMarks();

                                                modelCoursePassMark.setQuiz_pass_mark(courseQuizPassMark);
                                                modelCoursePassMark.setExam_pass_mark(courseExamPassMark);

                                                if(GlobalVar.isRedirectFromProfileEndedBtn==true){

                                                    if(enrolCourseProgress.equalsIgnoreCase("100")) {

                                                        mPassPercentageArrayList.add(modelCoursePassMark);
                                                    }
                                                    else{

                                                        String nothingtest="";
                                                    }

                                                }

                                                else if(GlobalVar.isRedirectFromProfileRunningBtn==true) {

                                                    if (completedCourseDouble<100) {

                                                        mPassPercentageArrayList.add(modelCoursePassMark);
                                                    }
                                                    else{

                                                        String nothingisimpo="";
                                                    }
                                                }

                                                else if(GlobalVar.isRedirectFromProfileNonEndedBtn==true) {

                                                    if (completedCourseDouble<100) {

                                                        if(currentDateInMillis>courseEndDateInMillis) {

                                                            mPassPercentageArrayList.add(modelCoursePassMark);

                                                        }
                                                    }
                                                    else{

                                                        String nothingisimpo="";
                                                    }
                                                }

                                                else if(GlobalVar.isRedirectFromProfileWishListBtn==true){

                                                    for(int checkWishListId=0; checkWishListId<GlobalVar.gWishListArray.size(); checkWishListId++){

                                                        String getWishListIds = GlobalVar.gWishListArray.get(checkWishListId).getWishListId();

                                                        if (Eid.equalsIgnoreCase(getWishListIds)){

                                                            mPassPercentageArrayList.add(modelCoursePassMark);
                                                        }
                                                    }
                                                }

                                                else if(completedCourseDouble<100) {

                                                    mPassPercentageArrayList.add(modelCoursePassMark);
                                                }



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

                                                if(GlobalVar.isRedirectFromProfileEndedBtn==true) {

                                                    if (enrolCourseProgress.equalsIgnoreCase("100")) {

                                                        detailListCourseThumbnail.add(modelCourseThumbnail);
                                                    }
                                                    else{

                                                        String nothingisimpo="";
                                                    }
                                                }

                                                else if(GlobalVar.isRedirectFromProfileRunningBtn==true) {

                                                    if (completedCourseDouble<100) {

                                                        detailListCourseThumbnail.add(modelCourseThumbnail);
                                                    }
                                                    else{

                                                        String nothingisimpo="";
                                                    }
                                                }

                                                else if(GlobalVar.isRedirectFromProfileNonEndedBtn==true) {

                                                    if (completedCourseDouble<100) {

                                                        if(currentDateInMillis>courseEndDateInMillis) {

                                                            detailListCourseThumbnail.add(modelCourseThumbnail);

                                                        }
                                                    }
                                                    else{

                                                        String nothingisimpo="";
                                                    }
                                                }


                                                else if(GlobalVar.isRedirectFromProfileWishListBtn==true){

                                                    for(int checkWishListId=0; checkWishListId<GlobalVar.gWishListArray.size(); checkWishListId++){

                                                        String getWishListIds = GlobalVar.gWishListArray.get(checkWishListId).getWishListId();

                                                        if (Eid.equalsIgnoreCase(getWishListIds)){

                                                            detailListCourseThumbnail.add(modelCourseThumbnail);
                                                        }
                                                    }
                                                }

                                                else if(completedCourseDouble<100) {

                                                    detailListCourseThumbnail.add(modelCourseThumbnail);
                                                }


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


                                                    detailUnitArrayNumbers = new ArrayList<>();
                                                    lessonsIntoIndexs = new ArrayList<>();


                                                    mUnitAllArrayList = new ArrayList<>();

                                                    mVideoPulseMulti = new ArrayList<>();



                                                    for (int ii = 0; ii < jObject.length() - 2; ii++)
                                                    {
                                                        //syllabus > 0,1,2
                                                        JSONObject jSObject2 = jObject.getJSONObject("" + ii);

                                                        detailList6 = new ArrayList<DetailDataModelCourses>();

                                                        DetailDataModelCourses model6 = new DetailDataModelCourses();

                                                        JSONArray jSonLessons = (JSONArray) jSObject2.getJSONArray("lessons");

                                                        try {

                                                            mLessonArrayListNew = new ArrayList<>();

                                                            for (int m = 0; m < jSonLessons.length(); m++) {

                                                                DetailDataModelCoursesDetailContents modelLessonElements = new DetailDataModelCoursesDetailContents();

                                                                JSONObject objectAgainAnotherLesson = (JSONObject) jSonLessons.get(m);

                                                                String idLesson = objectAgainAnotherLesson.getString("id");
                                                                String nameLesson = objectAgainAnotherLesson.getString("name");
                                                                String orderLesson = objectAgainAnotherLesson.getString("order");
                                                                String fixedLesson = objectAgainAnotherLesson.getString("fixed");

                                                                modelLessonElements.setIdLesson(idLesson);
                                                                modelLessonElements.setNameLesson(nameLesson);
                                                                modelLessonElements.setOrderLessom(orderLesson);
                                                                modelLessonElements.setFixedLesson(fixedLesson);

                                                                mLessonArrayListNew.add(modelLessonElements);


                                                                String testing1w212="";
                                                            }
                                                        } catch (Exception ex) {
                                                            Log.d("", "onResponse: ");
                                                        }

                                                        // for parsing "data" > {0} > {0} > "syllebus" > "0" > "data"

                                                        try {
                                                            //TODO
//                                                               mContentArrayListNew = new ArrayList<>();

                                                            detailUnitNumbers = new ArrayList<>();

                                                            for (int lmn = 0; lmn < jSObject2.length() - 1; lmn++)
                                                            {//syllabus > 0 > 0,1,2
                                                                JSONObject jSObject3 = jSObject2.getJSONObject("" + lmn);

                                                                //syllabus > 0 > 0,1,2 > "data"

                                                                DetailDataModelCoursesDetailContents modelUnitCourseContents = new DetailDataModelCoursesDetailContents();
                                                                JSONObject jObjAgain = jSObject3.getJSONObject("data");

                                                                //*String allow_preview = jObjAgain.getString("allow_preview");
                                                                String ans_rand = jObjAgain.getString("ans_rand");
                                                                String attempt = jObjAgain.getString("attempt");
                                                                String choose_video_type = jObjAgain.getString("choose_video_type");
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
                                                                String content_type = jObjAgain.getString("content_type");

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
                                                                modelUnitCourseContents.setTitle_content(mTitle);

                                                                detailUnitNumbers.add(modelUnitCourseContents);

                                                                //let's add

                                                                if (content_type.equalsIgnoreCase("exam")) {

                                                                    String mExamMakrs = jObjAgain.getString("marks");
                                                                    String mExamTime = jObjAgain.getString("time");

                                                                    modelUnitCourseContents.setmExamMarks(mExamMakrs);
                                                                    modelUnitCourseContents.setmExamTime(mExamTime);

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


                                                                //mUnitAllArrayList.add(modelUnitCourseContents);



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

                                                                    if(content_type.equalsIgnoreCase("video")){
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
                                                                        String contentSize = jObjAgainContent.getString("size");

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
                                                                        modelCourseContents.setmDesc(desc);
                                                                        modelCourseContents.setFile_encode_path(file_encode_path);
                                                                        modelCourseContents.setFile_name(file_name);
                                                                        modelCourseContents.setId_content(id_content);
                                                                        modelCourseContents.setLicense(license);
                                                                        modelCourseContents.setOwner_id(owner_id);
                                                                        modelCourseContents.setTitle_content(mTitle);
                                                                        modelCourseContents.setCreated_at_content(created_at_content);
                                                                        modelCourseContents.setmContentType(content_type);
                                                                        modelCourseContents.setmDurationAnother(mDuration);
                                                                        modelCourseContents.setmContentSize(contentSize);
                                                                    }
                                                                    else {
                                                                        JSONArray jObjAgainContent = jSObject3.getJSONArray("content");
                                                                    }

                                                                    //TODOabcd
                                                                    mContentArrayListNew.add(modelCourseContents);
                                                                } catch (Exception ex) {
                                                                    Log.d("", "onResponse: ");
                                                                }

                                                                //For parsing Quizes

                                                                //JSONArray jObjQuizes = (JSONArray) jSObject3.getJSONArray("multi_ques_list");

                                                                // For parsing object "Content" > {0} > {0} > "syllebus" > "0" > "data"

                                                                detailList5 = new ArrayList<DetailDataModelCourses>();

                                                                DetailDataModelCourses model5 = new DetailDataModelCourses();

                                                                if (content_type.equalsIgnoreCase("quiz"))
                                                                {
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
                                                                                String qIdQuiz = objectAgainAnother2.getString("id");

                                                                                qTitle = qTitle.replace("<p>", "");
                                                                                qTitle = qTitle.replace("</p>", "");

                                                                                qDesc = qDesc.replace("<p>", "");
                                                                                qDesc = qDesc.replace("</p>", "");

                                                                                modelUnitQuizElements2.setmQuizTitle(qTitle);
                                                                                modelUnitQuizElements2.setmSummeryDesc(qDesc);
                                                                                modelUnitQuizElements2.setmQuizId(qIdQuiz);
                                                                                modelUnitQuizElements2.setmContentType(content_type);
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
                                                                                        modelUnitQuizOptions.setmContentType(content_type);

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

                                                                else if (content_type.equalsIgnoreCase("exam"))
                                                                {
                                                                    try {
                                                                        for (int ctqexam = 0; ctqexam < jSObject3.length(); ctqexam++) {

                                                                            JSONArray jSonObjMultiQ2exam = (JSONArray) jSObject3.getJSONArray("ques_list");

                                                                            mUnitQuizListExam = new ArrayList<>();

                                                                            for (int qlist2 = 0; qlist2 < jSonObjMultiQ2exam.length(); qlist2++) {

                                                                                DetailDataModelCoursesDetailContents modelUnitQuizElements2 = new DetailDataModelCoursesDetailContents();

                                                                                JSONObject objectAgainAnother2 = (JSONObject) jSonObjMultiQ2exam.get(qlist2);

                                                                                String qTitle = objectAgainAnother2.getString("title");
                                                                                String qId = objectAgainAnother2.getString("id");

                                                                                qTitle = qTitle.replace("<p>", "");
                                                                                qTitle = qTitle.replace("</p>", "");

                                                                                modelUnitQuizElements2.setmQuizTitle(qTitle);
                                                                                modelUnitQuizElements2.setmQuizId(qId);
                                                                                modelUnitQuizElements2.setmQuesList(jSonObjMultiQ2exam);
                                                                                modelUnitQuizElements2.setmContentType(content_type);

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
                                                                }
                                                                else {

                                                                }

                                                            }


                                                        } catch (Exception ex) {
                                                            Log.d("", "onResponse: ");
                                                        }


                                                        detailUnitArrayNumbers.add(detailUnitNumbers);

                                                        lessonsIntoIndexs.add(mLessonArrayListNew);
                                                    }

                                                } catch (Exception ex) {
                                                    Log.d("", "onResponse: ");
                                                }
                                            } catch (Exception ex) {
                                                Log.d("", "onResponse: ");
                                            }



                                            try {
                                                JSONArray journeyStatusArr = jObjEnrolledCourses.getJSONArray("journey_status");

                                                try {

                                                    unitJourneyStatusList = new ArrayList<>();


                                                    for (int jStatusUnitNumber = 0; jStatusUnitNumber < journeyStatusArr.length(); jStatusUnitNumber++)
                                                    {

                                                        JSONArray journeyStatusUnitNumbers = (JSONArray) journeyStatusArr.getJSONArray(jStatusUnitNumber);

                                                        try {


                                                            lessonJourneyStatusList = new ArrayList<>();
                                                            //unitJourneyStatusList = new ArrayList<>();

                                                            for (int jStatusLessonNumber = 0; jStatusLessonNumber < journeyStatusUnitNumbers.length(); jStatusLessonNumber++)
                                                            {
                                                                //JSONArray journeyStatusLessonNumbers =  journeyStatusUnitNumbers.getJSONArray(jStatusUnitNumber);

                                                                //

                                                                DetailDataModelCoursesDetailContents modelJourneyStatus = new DetailDataModelCoursesDetailContents();

                                                                JSONObject journeyStatusLessonNumbersAgain = journeyStatusUnitNumbers.getJSONObject(jStatusLessonNumber);

                                                                String mLessonStartStatus = journeyStatusLessonNumbersAgain.getString("start");
                                                                String mLessonCompletenessStatus = journeyStatusLessonNumbersAgain.getString("completeness");


                                                                modelJourneyStatus.setLessonStartsStatus(mLessonStartStatus);
                                                                modelJourneyStatus.setLessonCompletenessStatus(mLessonCompletenessStatus);

                                                                lessonJourneyStatusList.add(modelJourneyStatus);

                                                                //unitJourneyStatusList.add(lessonJourneyStatusList);

                                                            }



                                                        }
                                                        catch (Exception ex){
                                                            Log.d("", "onResponse: ");
                                                        }

                                                        unitJourneyStatusList.add(lessonJourneyStatusList);
                                                    }
                                                }
                                                catch (Exception ex){
                                                    Log.d("", "onResponse: ");
                                                }
                                            }
                                            catch (Exception ex){
                                                Log.d("", "onResponse: ");
                                            }



                                            enrollCourseModel.setmEcId(enrolCourseId);
                                            enrollCourseModel.setmEcCompleteness(enrolCourseProgress);
                                            enrollCourseModel.setmCourseAliasName(course_alias_name);




                                            // Setting custom array list for profile 4 buttons

                                            if(GlobalVar.isRedirectFromProfileEndedBtn==true)
                                            {

                                                if(enrolCourseProgress.equalsIgnoreCase("100")) {

                                                    //detailListCourseDetailContentss.add(mContentArrayListNew);
                                                    //detailListCourseDetailContentss.remove(ec);

                                                    detailListCourseDetailContentss.add(mContentArrayListNew);
                                                    courseJourneyStatusList.add(unitJourneyStatusList);


                                                    detailListCourseDetailUnitQuizOptList.add(mUnitQuizList2);
                                                    detailListCourseDetailPulseQuizOptList.add(mPulseQuizList2);

                                                    detailListCourseDetailVideoPulseMulti.add(mVideoPulseMulti);

                                                    //TODO

                                                    //detailListCourseDetailLesson.add(mLessonArrayListNew);

                                                    //TODO

                                                    //detailListVideoPulse.add(mContentArrayListNewPulse);

                                                    detailListCourseDetailUnits.add(mUnitArrayListNew);

                                                    detailListCourseDetailUnitQuizList.add(mUnitQuizList);
                                                    detailListCourseDetailUnitQuizListExam.add(mUnitQuizListExam);

                                                    //detailListCourseDetailUnitQuizOptList.add(mUnitQuizListWithOptions);


                                                    //detailListCourseDetailUnitQuizOptList.add(mUnitQuizOptList);

                                                    // for unit arrays
                                                    detailListCourseUnit1Data.add(mUnit1DataArrayList);
                                                    detailListCourseUnit2Data.add(mUnit2DataArrayList);
                                                    detailListCourseUnit3Data.add(mUnit3DataArrayList);
                                                    detailListCourseUnit4Data.add(mUnit4DataArrayList);


                                                    detailListCourseUnitAllData.add(detailUnitArrayNumbers);
                                                    detailListCourseDetailLesson.add(lessonsIntoIndexs);

                                                    GlobalVar.gEnrolledInstitution = detailList10;


                                                    GlobalVar.gEnrollCourseId.add(enrollCourseModel);

                                                    String stp="";



                                                    String whatwas6="";

                                                }
                                                else{

                                                    String nothingabcd="abcd";

                                                }

                                            }



                                            else if(GlobalVar.isRedirectFromProfileRunningBtn==true){

                                                String nothingabcd="abcd";

                                                if (completedCourseDouble<100) {

                                                    //detailListCourseDetailContentss.add(mContentArrayListNew);
                                                    //detailListCourseDetailContentss.remove(ec);

                                                    detailListCourseDetailContentss.add(mContentArrayListNew);
                                                    courseJourneyStatusList.add(unitJourneyStatusList);


                                                    detailListCourseDetailUnitQuizOptList.add(mUnitQuizList2);
                                                    detailListCourseDetailPulseQuizOptList.add(mPulseQuizList2);

                                                    detailListCourseDetailVideoPulseMulti.add(mVideoPulseMulti);

                                                    //TODO

                                                    //detailListCourseDetailLesson.add(mLessonArrayListNew);

                                                    //TODO

                                                    //detailListVideoPulse.add(mContentArrayListNewPulse);

                                                    detailListCourseDetailUnits.add(mUnitArrayListNew);

                                                    detailListCourseDetailUnitQuizList.add(mUnitQuizList);
                                                    detailListCourseDetailUnitQuizListExam.add(mUnitQuizListExam);

                                                    //detailListCourseDetailUnitQuizOptList.add(mUnitQuizListWithOptions);


                                                    //detailListCourseDetailUnitQuizOptList.add(mUnitQuizOptList);

                                                    // for unit arrays
                                                    detailListCourseUnit1Data.add(mUnit1DataArrayList);
                                                    detailListCourseUnit2Data.add(mUnit2DataArrayList);
                                                    detailListCourseUnit3Data.add(mUnit3DataArrayList);
                                                    detailListCourseUnit4Data.add(mUnit4DataArrayList);


                                                    detailListCourseUnitAllData.add(detailUnitArrayNumbers);
                                                    detailListCourseDetailLesson.add(lessonsIntoIndexs);

                                                    GlobalVar.gEnrolledInstitution = detailList10;


                                                    GlobalVar.gEnrollCourseId.add(enrollCourseModel);

                                                    String stp="";



                                                    String whatwas6="";
                                                }
                                                else{

                                                    String nothingisimport="";
                                                }
                                            }

                                            else if(GlobalVar.isRedirectFromProfileNonEndedBtn==true){

                                                if (completedCourseDouble<100) {

                                                    if(currentDateInMillis>courseEndDateInMillis){
                                                        //detailListCourseDetailContentss.add(mContentArrayListNew);
                                                        //detailListCourseDetailContentss.remove(ec);

                                                        detailListCourseDetailContentss.add(mContentArrayListNew);
                                                        courseJourneyStatusList.add(unitJourneyStatusList);


                                                        detailListCourseDetailUnitQuizOptList.add(mUnitQuizList2);
                                                        detailListCourseDetailPulseQuizOptList.add(mPulseQuizList2);

                                                        detailListCourseDetailVideoPulseMulti.add(mVideoPulseMulti);

                                                        //TODO

                                                        //detailListCourseDetailLesson.add(mLessonArrayListNew);

                                                        //TODO

                                                        //detailListVideoPulse.add(mContentArrayListNewPulse);

                                                        detailListCourseDetailUnits.add(mUnitArrayListNew);

                                                        detailListCourseDetailUnitQuizList.add(mUnitQuizList);
                                                        detailListCourseDetailUnitQuizListExam.add(mUnitQuizListExam);

                                                        //detailListCourseDetailUnitQuizOptList.add(mUnitQuizListWithOptions);


                                                        //detailListCourseDetailUnitQuizOptList.add(mUnitQuizOptList);

                                                        // for unit arrays
                                                        detailListCourseUnit1Data.add(mUnit1DataArrayList);
                                                        detailListCourseUnit2Data.add(mUnit2DataArrayList);
                                                        detailListCourseUnit3Data.add(mUnit3DataArrayList);
                                                        detailListCourseUnit4Data.add(mUnit4DataArrayList);


                                                        detailListCourseUnitAllData.add(detailUnitArrayNumbers);
                                                        detailListCourseDetailLesson.add(lessonsIntoIndexs);

                                                        GlobalVar.gEnrolledInstitution = detailList10;


                                                        GlobalVar.gEnrollCourseId.add(enrollCourseModel);

                                                        String stp="";



                                                        String whatwas6="";
                                                    }


                                                }
                                                else{

                                                    String nothingisimport="";
                                                }
                                            }

                                            else if(GlobalVar.isRedirectFromProfileWishListBtn==true){

                                                for(int checkWishListId=0; checkWishListId<GlobalVar.gWishListArray.size(); checkWishListId++){

                                                    String getWishListIds = GlobalVar.gWishListArray.get(checkWishListId).getWishListId();

                                                    if (Eid.equalsIgnoreCase(getWishListIds)){

                                                        //detailListCourseDetailContentss.add(mContentArrayListNew);
                                                        //detailListCourseDetailContentss.remove(ec);

                                                        detailListCourseDetailContentss.add(mContentArrayListNew);
                                                        courseJourneyStatusList.add(unitJourneyStatusList);


                                                        detailListCourseDetailUnitQuizOptList.add(mUnitQuizList2);
                                                        detailListCourseDetailPulseQuizOptList.add(mPulseQuizList2);

                                                        detailListCourseDetailVideoPulseMulti.add(mVideoPulseMulti);

                                                        //TODO

                                                        //detailListCourseDetailLesson.add(mLessonArrayListNew);

                                                        //TODO

                                                        //detailListVideoPulse.add(mContentArrayListNewPulse);

                                                        detailListCourseDetailUnits.add(mUnitArrayListNew);

                                                        detailListCourseDetailUnitQuizList.add(mUnitQuizList);
                                                        detailListCourseDetailUnitQuizListExam.add(mUnitQuizListExam);

                                                        //detailListCourseDetailUnitQuizOptList.add(mUnitQuizListWithOptions);


                                                        //detailListCourseDetailUnitQuizOptList.add(mUnitQuizOptList);

                                                        // for unit arrays
                                                        detailListCourseUnit1Data.add(mUnit1DataArrayList);
                                                        detailListCourseUnit2Data.add(mUnit2DataArrayList);
                                                        detailListCourseUnit3Data.add(mUnit3DataArrayList);
                                                        detailListCourseUnit4Data.add(mUnit4DataArrayList);


                                                        detailListCourseUnitAllData.add(detailUnitArrayNumbers);
                                                        detailListCourseDetailLesson.add(lessonsIntoIndexs);

                                                        GlobalVar.gEnrolledInstitution = detailList10;


                                                        GlobalVar.gEnrollCourseId.add(enrollCourseModel);
                                                    }
                                                }
                                            }


                                            else if (completedCourseDouble<100)
                                            {

                                                //detailListCourseDetailContentss.add(mContentArrayListNew);
                                                //detailListCourseDetailContentss.remove(ec);

                                                detailListCourseDetailContentss.add(mContentArrayListNew);
                                                courseJourneyStatusList.add(unitJourneyStatusList);


                                                detailListCourseDetailUnitQuizOptList.add(mUnitQuizList2);
                                                detailListCourseDetailPulseQuizOptList.add(mPulseQuizList2);

                                                detailListCourseDetailVideoPulseMulti.add(mVideoPulseMulti);

                                                //TODO

                                                //detailListCourseDetailLesson.add(mLessonArrayListNew);

                                                //TODO

                                                //detailListVideoPulse.add(mContentArrayListNewPulse);

                                                detailListCourseDetailUnits.add(mUnitArrayListNew);

                                                detailListCourseDetailUnitQuizList.add(mUnitQuizList);
                                                detailListCourseDetailUnitQuizListExam.add(mUnitQuizListExam);

                                                //detailListCourseDetailUnitQuizOptList.add(mUnitQuizListWithOptions);


                                                //detailListCourseDetailUnitQuizOptList.add(mUnitQuizOptList);

                                                // for unit arrays
                                                detailListCourseUnit1Data.add(mUnit1DataArrayList);
                                                detailListCourseUnit2Data.add(mUnit2DataArrayList);
                                                detailListCourseUnit3Data.add(mUnit3DataArrayList);
                                                detailListCourseUnit4Data.add(mUnit4DataArrayList);


                                                detailListCourseUnitAllData.add(detailUnitArrayNumbers);
                                                detailListCourseDetailLesson.add(lessonsIntoIndexs);

                                                GlobalVar.gEnrolledInstitution = detailList10;


                                                GlobalVar.gEnrollCourseId.add(enrollCourseModel);

                                                String stp="";



                                                String whatwas6="";
                                            }


                                            if(currentDateInMillis>courseEndDateInMillis) {

                                                GlobalVar.gIncompletedCourseCount++;

                                            }

                                        }

                                    } catch (Exception ex) {
                                        Log.d("", "onResponse: ");
                                    }



                                    /** deciding the number of tabsPagerAdapter Fragments according to boolean var redirect from 4 buttons of profile*/


                                    int totalIncompletedCourseInt = enrolledCourseNumbers - Integer.parseInt(coursecompleted);

                                    if(GlobalVar.isRedirectFromProfileWishListBtn==true) {
                                        GlobalVar.gEnrollCourseNumber = GlobalVar.gWishListNumber;


                                        final View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.custom_logodetails_for_profile_btns, null, false);
                                        getSupportActionBar().setDisplayShowTitleEnabled(false);
                                        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
                                        getSupportActionBar().setCustomView(view);
                                        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#7a19aa")));
                                        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

                                        mCustomLogoText=view.findViewById(R.id.centertitlenametv);

                                        mCustomLogoText.setText("পছন্দের কোর্স");
                                        myPageTextView.setVisibility(View.GONE);

                                        mFiveFooterBtnsLayOut.setVisibility(View.GONE);


                                        mSettingsBtn.setVisibility(View.GONE);
                                    }

                                    else if (GlobalVar.isRedirectFromProfileEndedBtn==true) {

                                        final View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.custom_logodetails_for_profile_btns, null, false);
                                        getSupportActionBar().setDisplayShowTitleEnabled(false);
                                        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
                                        getSupportActionBar().setCustomView(view);
                                        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#7a19aa")));
                                        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

                                        mCustomLogoText=view.findViewById(R.id.centertitlenametv);

                                        GlobalVar.gEnrollCourseNumber = Integer.parseInt(coursecompleted);

                                        mCustomLogoText.setText("সম্পন্ন কোর্স");
                                        myPageTextView.setVisibility(View.GONE);

                                        mFiveFooterBtnsLayOut.setVisibility(View.GONE);

                                        mSettingsBtn.setVisibility(View.GONE);
                                    }

                                    else if(GlobalVar.isRedirectFromProfileNonEndedBtn==true) {

                                        GlobalVar.gEnrollCourseNumber = GlobalVar.gIncompletedCourseCount;

                                        getSupportActionBar().hide();

                                        /*View view = LayoutInflater.from(mContext)
                                                .inflate(R.layout.custom_logodetails, null, false);
                                        getSupportActionBar().setDisplayShowTitleEnabled(false);
                                        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
                                        getSupportActionBar().setCustomView(view);
                                        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#7a19aa")));
                                        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

                                        imageLogo=view.findViewById(R.id.rtvheadlogo);*/
                                    }

                                    else if(GlobalVar.isRedirectFromProfileRunningBtn==true) {

                                        GlobalVar.gEnrollCourseNumber = totalIncompletedCourseInt;


                                        getSupportActionBar().hide();

                                        /*View view = LayoutInflater.from(mContext)
                                                .inflate(R.layout.custom_logodetails, null, false);
                                        getSupportActionBar().setDisplayShowTitleEnabled(false);
                                        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
                                        getSupportActionBar().setCustomView(view);
                                        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#7a19aa")));
                                        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

                                        imageLogo=view.findViewById(R.id.rtvheadlogo);*/
                                    }

                                    else{
                                        GlobalVar.gEnrollCourseNumber = totalIncompletedCourseInt;

                                        getSupportActionBar().hide();

                                        /*View view = LayoutInflater.from(mContext)
                                                .inflate(R.layout.custom_logodetails, null, false);
                                        getSupportActionBar().setDisplayShowTitleEnabled(false);
                                        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
                                        getSupportActionBar().setCustomView(view);
                                        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#7a19aa")));
                                        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

                                        imageLogo=view.findViewById(R.id.rtvheadlogo);*/
                                    }


                                }

                                catch (Exception ex) {
                                    Log.d("", "onResponse: ");
                                }


                                modelAlter.setmArrayListCourseCompletenes(courseJourneyStatusList);

                                modelAlter.setmUnitDataArrayListContent(detailListCourseUnit1Data);
                                modelAlter.setmUnitDataArrayListContent2(detailListCourseUnit2Data);
                                modelAlter.setmUnitDataArrayListContent3(detailListCourseUnit3Data);
                                modelAlter.setmUnitDataArrayListContent4(detailListCourseUnit4Data);


                                modelAlter.setmUnitAllArrayList(detailListCourseUnitAllData);

                                modelAlter.setmUserInformationArrayList(detailListUserInformation);



                                modelAlter.setmArrayListThumbnails(detailListCourseThumbnail);
                                modelAlter.setmArrayListMarks(mPassPercentageArrayList);


                                //TODO
                                modelAlter.setmArrayListContentDetails(detailListCourseDetailContentss);
                                modelAlter.setmArrayListCourseLesson(detailListCourseDetailLesson);
                                //TODO

                                modelAlter.setmArrayListCourseUnits(detailListCourseDetailUnits);


                                modelAlter.setmArrayListCourseQuizs(detailListCourseDetailUnitQuizList);
                                modelAlter.setmArrayListCourseQuizsExam(detailListCourseDetailUnitQuizListExam);

                                //for option list of quest list
                                modelAlter.setmArrayListCourseQuizOptions(detailListCourseDetailUnitQuizOptList);
                                modelAlter.setmArrayListCoursePulseQuizOptions(detailListCourseDetailPulseQuizOptList);

                                modelAlter.setmArrayListCourseVideoPulseMulti(detailListCourseDetailVideoPulseMulti);

                                modelAlter.setmArraylistProfileInfo(detailList);

                                detailListCourse.add(modelAlter);

                                /*ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> contentArray = detailListCourse.get(0).getmArrayListContentDetails();

                                mArrayList = contentArray.get(0).toArray();

                                GlobalVar.gChildArrayOfContentMyPage=mArrayList;*/

                                GlobalVar.courseContentDetailList = detailListCourse;

                                //GlobalVar.gEnrollCourseQuestionList= GlobalVar.courseContentDetailList.get(i).getmArrayListCourseQuizs();

                                String stepover="";
                            } catch (Exception ex) {
                                Log.d("", "onResponse: ");
                            }

                            mProgressDialog.dismiss();

                        }
                        catch (Exception ex) {
                            Log.e("tag", "Couldn't get json from server.");
                        }
                        else {
                            Toast.makeText(mContext, "You must fill the email field.", Toast.LENGTH_LONG).show();
                        }


                        if(GlobalVar.gEnrollCourseNumber>0){
                            mRelativeBackground.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.mukto_mypage_background_img_sharp));
                        }
                        else {
                            mNoEnroledCourseFound.setVisibility(View.VISIBLE);
                        }






                        final ViewPager vpPager = findViewById(R.id.VideoSliderviewPagerId);

                        myAdapter= new TabsPagerAdapterDetail(getSupportFragmentManager());
                        vpPager.setAdapter(myAdapter);


                        String abcd="";


                        /*if(GlobalVar.nNumberCourseBack==0){
                            vpPager.setCurrentItem(0);
                        }
                        else {
                            vpPager.setCurrentItem(GlobalVar.nNumberCourseBack);
                        }*/

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

        //enrolledCourse.setText(GlobalVar.gEnrollCourseNumber);

        allCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GlobalVar.isRedirectFromProfileRunningBtn=false;
                GlobalVar.isRedirectFromProfileNonEndedBtn=false;
                GlobalVar.isRedirectFromProfileEndedBtn=false;
                GlobalVar.isRedirectFromProfileWishListBtn=false;

                Intent i = new Intent(mContext, MainActivity.class);
                v.getContext().startActivity(i);
            }
        });

        recomendedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GlobalVar.isRedirectFromProfileRunningBtn=false;
                GlobalVar.isRedirectFromProfileNonEndedBtn=false;
                GlobalVar.isRedirectFromProfileEndedBtn=false;
                GlobalVar.isRedirectFromProfileWishListBtn=false;

                Intent i = new Intent(mContext, RecomendedActivity.class);
                v.getContext().startActivity(i);
            }
        });

        downloadsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GlobalVar.isRedirectFromProfileRunningBtn=false;
                GlobalVar.isRedirectFromProfileNonEndedBtn=false;
                GlobalVar.isRedirectFromProfileEndedBtn=false;
                GlobalVar.isRedirectFromProfileWishListBtn=false;

                Intent i=new Intent(mContext,DownloadActivity.class);
                v.getContext().startActivity(i);
            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GlobalVar.isRedirectFromProfileRunningBtn=false;
                GlobalVar.isRedirectFromProfileNonEndedBtn=false;
                GlobalVar.isRedirectFromProfileEndedBtn=false;
                GlobalVar.isRedirectFromProfileWishListBtn=false;


                Intent i=new Intent(mContext,ProfileActivity.class);
                v.getContext().startActivity(i);
            }
        });


        /*try {

            for (int incrCourseWishIds = 0; incrCourseWishIds < wishListArray.size(); incrCourseWishIds++) {

                String incrCourseBatchIdsStr = wishListArray.get(incrCourseWishIds).getWishListId();

                try {

                    ArrayList<String> wishEmptyArray = new ArrayList<>();

                    for (int incrCourseBatchIds = 0; incrCourseBatchIds < detailListAnoPart3.size(); incrCourseBatchIds++) {

                        String incrCourseWishIdsStr = detailListAnoPart3.get(incrCourseBatchIds).getmId();

                        if (incrCourseBatchIdsStr.equalsIgnoreCase(incrCourseWishIdsStr)) {

                            wishEmptyArray.add(incrCourseWishIdsStr);

                            String abcd2="";
                        }
                        else {
                            wishEmptyArray.add("");

                            String abcd3="";
                        }

                        String abcd77="";
                    }

                    String abcd56765="";
                }
                catch (Exception ex){

                    Log.d("", "onCreate: ");
                }

                String abc1212d="";
            }
        }
        catch (Exception ex){
            Log.d("", "onCreate: ");
        }*/


        String abcd="";
    }

    @Override
    public void onBackPressed() {
        finish();
        return;
    }

    /*@Override
    protected boolean onPrepareOptionsPanel(View view, Menu menu) {
        if(menu != null){
            if(menu.getClass().getSimpleName().equals("MenuBuilder")){
                try{
                    Method m = menu.getClass().getDeclaredMethod(
                            "setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                }
                catch(NoSuchMethodException e){
                    Log.e(SyncStateContract.Constants.DATA, "onMenuOpened", e);
                }
                catch(Exception e){
                    throw new RuntimeException(e);
                }
            }
        }
        return true;
        //return super.onPrepareOptionsPanel(view, menu);
        //return super.onPrepareOptionsPanel(view, menu);
    }*/

    private void shareIt() {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Muktopaath");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, ShareURL);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main2_drawer, menu);
        //getMenuInflater().inflate(R.menu.main2, menu);

        return super.onCreateOptionsMenu(menu);
    }*/

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        }
        else if (id==R.id.nav_share)
            shareIt();
        return super.onOptionsItemSelected(item);
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {

            finish();

            return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private void showPopUpImageBox()
    {
        // custom dialog
        final Dialog dialog = new Dialog(mContext, R.style.DialogCustomTheme);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popupwindowforlogout);

        Button mLogOutYes=dialog.findViewById(R.id.logOutYes);
        mLogOutYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sm.logoutUser();

            }
        });

        Button mLogOutNo=dialog.findViewById(R.id.logOutNo);
        mLogOutNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
