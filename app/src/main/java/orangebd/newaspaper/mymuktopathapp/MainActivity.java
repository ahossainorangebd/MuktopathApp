package orangebd.newaspaper.mymuktopathapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;

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
import java.io.IOException;
import java.io.InputStream;
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

public class MainActivity extends AppCompatActivity {

    private Context mContext;

    private Button mSearchBtnIcon;

    private LinearLayout searchBar;

    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;
    private RecyclerView recyclerView3;
    private RecyclerView recyclerView4;
    private RecyclerView recyclerView5;
    private RecyclerView recyclerView6;
    private RecyclerView recyclerView7;
    private RecyclerView recyclerView8;
    private RecyclerView recyclerView9;
    private RecyclerView recyclerView10;

    private RecyclerView.Adapter adapter;
    private RecyclerView.Adapter adapter2;
    private RecyclerView.Adapter adapter3;
    private RecyclerView.Adapter adapter4;
    private RecyclerView.Adapter adapter5;
    private RecyclerView.Adapter adapter6;
    private RecyclerView.Adapter adapter7;
    private RecyclerView.Adapter adapter8;
    private RecyclerView.Adapter adapter9;
    private RecyclerView.Adapter adapter10;

    //All the detail Lists
    private ArrayList<DetailDataModelAll> detailListMainActivityCourseCat;

    // Button Layout of footer

    private LinearLayout allCourseBtn;
    private LinearLayout recomendedBtn;
    private LinearLayout myPageBtn;
    private LinearLayout downloadsBtn;
    private LinearLayout profileBtn;

    private ProgressBar mProgressSpinner;
    private ProgressBar mProgressSpinner2;
    private ProgressBar mProgressSpinner3;
    private ProgressBar mProgressSpinner4;
    private ProgressBar mProgressSpinner5;
    private ProgressBar mProgressSpinner6;
    private ProgressBar mProgressSpinner7;
    private ProgressBar mProgressSpinner8;
    private ProgressBar mProgressSpinner9;
    private ProgressBar mProgressSpinner10;

    String urlGetCourses = "http://api.muktopaath.orangebd.com/api/course/search";
    String urlGetCourseCats = "http://api.muktopaath.orangebd.com/api/course-categories";

    private HashMap<String,String> map1;
    private HashMap<String,String> map2;
    private HashMap<String,String> map3;
    private HashMap<String,String> map4;
    private HashMap<String,String> map5;
    private HashMap<String,String> map6;
    private HashMap<String,String> map7;
    private HashMap<String,String> map8;
    private HashMap<String,String> map9;
    private HashMap<String,String> map10;

    // arraylists


    //For category1
    private ArrayList<DetailDataModelCourses> detailListMainActivityCourse=new ArrayList<>();

    private ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> detailListMainActivityCourseDetailContentss;
    private ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> detailListMainActivityCourseDetailUnits;
    private ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> detailListMainActivityCourseDetailUnitQuizList;

    private ArrayList<DetailDataModelCoursesThumbnails> detailListMainActivityCourseThumbnail;

    ArrayList<DetailDataModelCoursesDetailContents> mContentArrayListNew;
    ArrayList<DetailDataModelCoursesDetailContents> mUnitArrayListNew;
    ArrayList<DetailDataModelCoursesDetailContents> mUnitQuizList;


    // for 2nd-10th categories
    private ArrayList<DetailDataModelCourses> detailListMainActivityCourse2=new ArrayList<>();
    private ArrayList<DetailDataModelCourses> detailListMainActivityCourse3=new ArrayList<>();
    private ArrayList<DetailDataModelCourses> detailListMainActivityCourse4=new ArrayList<>();
    private ArrayList<DetailDataModelCourses> detailListMainActivityCourse5=new ArrayList<>();
    private ArrayList<DetailDataModelCourses> detailListMainActivityCourse6=new ArrayList<>();
    private ArrayList<DetailDataModelCourses> detailListMainActivityCourse7=new ArrayList<>();
    private ArrayList<DetailDataModelCourses> detailListMainActivityCourse8=new ArrayList<>();
    private ArrayList<DetailDataModelCourses> detailListMainActivityCourse9=new ArrayList<>();
    private ArrayList<DetailDataModelCourses> detailListMainActivityCourse10=new ArrayList<>();

    //for courseNames

    private TextView courseText1;
    private TextView courseText2;
    private TextView courseText3;
    private TextView courseText4;
    private TextView courseText5;
    private TextView courseText6;
    private TextView courseText7;
    private TextView courseText8;
    private TextView courseText9;
    private TextView courseText10;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext=this;

        getSupportActionBar().hide();

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        View view2 = findViewById(android.R.id.content);
        Animation mLoadAnimation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in);
        mLoadAnimation.setDuration(1000);
        view2.startAnimation(mLoadAnimation);

        allCourseBtn = findViewById(R.id.allCourseBtnId);
        recomendedBtn = findViewById(R.id.recomendedBtnId);
        myPageBtn = findViewById(R.id.myPageBtnId);
        downloadsBtn = findViewById(R.id.downloadsBtnId);
        profileBtn = findViewById(R.id.profilePageBtnId);

        mSearchBtnIcon=findViewById(R.id.searchBtnIcon);

        mProgressSpinner=findViewById(R.id.loadingSpinnerId);
        mProgressSpinner2=findViewById(R.id.loadingSpinnerId2);
        mProgressSpinner3=findViewById(R.id.loadingSpinnerId3);
        mProgressSpinner4=findViewById(R.id.loadingSpinnerId4);
        mProgressSpinner5=findViewById(R.id.loadingSpinnerId5);
        mProgressSpinner6=findViewById(R.id.loadingSpinnerId6);
        mProgressSpinner7=findViewById(R.id.loadingSpinnerId7);
        mProgressSpinner8=findViewById(R.id.loadingSpinnerId8);
        mProgressSpinner9=findViewById(R.id.loadingSpinnerId9);
        mProgressSpinner10=findViewById(R.id.loadingSpinnerId10);


        //Course Category TextViews

        courseText1 = findViewById(R.id.courseName1);
        courseText2 = findViewById(R.id.courseName2);
        courseText3 = findViewById(R.id.courseName3);
        courseText4 = findViewById(R.id.courseName4);
        courseText5 = findViewById(R.id.courseName5);
        courseText6 = findViewById(R.id.courseName6);
        courseText7 = findViewById(R.id.courseName7);
        courseText8 = findViewById(R.id.courseName8);
        courseText9 = findViewById(R.id.courseName9);
        courseText10 = findViewById(R.id.courseName10);


        recomendedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(mContext, RecomendedActivity.class);
                v.getContext().startActivity(i);
            }
        });

        myPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(mContext,MyPageActivity.class);
                v.getContext().startActivity(i);
            }
        });

        downloadsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(mContext,DownloadActivity.class);
                v.getContext().startActivity(i);
            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(mContext,ProfileActivity.class);
                v.getContext().startActivity(i);
            }
        });
        //

        searchBar=findViewById(R.id.searchBarId);
        searchBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(mContext,SearchActivity.class);
                v.getContext().startActivity(i);
            }
        });

        mSearchBtnIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(mContext,SearchActivity.class);
                v.getContext().startActivity(i);
            }
        });

        map1 = new HashMap<String, String>();
        map2 = new HashMap<String, String>();
        map3 = new HashMap<String, String>();
        map4 = new HashMap<String, String>();
        map5 = new HashMap<String, String>();
        map6 = new HashMap<String, String>();
        map7 = new HashMap<String, String>();
        map8 = new HashMap<String, String>();
        map9 = new HashMap<String, String>();
        map10 = new HashMap<String, String>();

        new GetCourseCategories().execute(urlGetCourseCats);

        JSONObject object=new JSONObject();
    }

    public class GetCourseCategories extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... arg0) {
            String response = null;

            try {
                HttpURLConnection c = (HttpURLConnection) new URL(arg0[0]).openConnection();
                c.setRequestMethod("GET");
                c.setUseCaches(false);
                c.setRequestProperty ("Authorization", GlobalVar.gReplacingTokenForAllCategories);
                c.connect();

                InputStream in = new BufferedInputStream(c.getInputStream());
                response = convertStreamToString(in);
                c.disconnect();
            }
            catch (Exception ex){
                Log.d("",ex.getMessage());
            }
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {

                detailListMainActivityCourseCat = new ArrayList<DetailDataModelAll>();

                JSONObject jObject = new JSONObject(result);
                JSONArray objectCourseCatNames = (JSONArray) jObject.getJSONArray("data");

                try {

                    for (int cc = 0; cc < objectCourseCatNames.length(); cc++)
                    {


                        JSONObject jObjEnrolledCourses = objectCourseCatNames.getJSONObject(cc);
                        DetailDataModelAll modelCourseCatAll = new DetailDataModelAll();

                        String courseNameBn = jObjEnrolledCourses.getString("bn_title");
                        String courseNameEn = jObjEnrolledCourses.getString("title");
                        String courseId = jObjEnrolledCourses.getString("id");

                        modelCourseCatAll.setmCourseCategoryId(courseId);
                        modelCourseCatAll.setmCourseCategoryNameEn(courseNameEn);
                        modelCourseCatAll.setmCourseCategoryNameBn(courseNameBn);

                        detailListMainActivityCourseCat.add(modelCourseCatAll);

                        GlobalVar.gAllCourseCategories=detailListMainActivityCourseCat;

                        String aaaa="";
                    }
                }
                catch (Exception ex) {
                    Log.d("", "onResponse: ");
                }

                try {
                    courseText1. setText(detailListMainActivityCourseCat.get(0).getmCourseCategoryNameBn());
                    courseText2. setText(detailListMainActivityCourseCat.get(1).getmCourseCategoryNameBn());
                    courseText3. setText(detailListMainActivityCourseCat.get(2).getmCourseCategoryNameBn());
                    courseText4. setText(detailListMainActivityCourseCat.get(3).getmCourseCategoryNameBn());
                    courseText5. setText(detailListMainActivityCourseCat.get(4).getmCourseCategoryNameBn());
                    courseText6. setText(detailListMainActivityCourseCat.get(5).getmCourseCategoryNameBn());
                    courseText7. setText(detailListMainActivityCourseCat.get(6).getmCourseCategoryNameBn());
                    courseText8. setText(detailListMainActivityCourseCat.get(7).getmCourseCategoryNameBn());
                    courseText9. setText(detailListMainActivityCourseCat.get(8).getmCourseCategoryNameBn());
                    courseText10.setText(detailListMainActivityCourseCat.get(9).getmCourseCategoryNameBn());
                }
                catch (Exception ex){
                    Log.d("", "onPostExecute: ");
                }

                /*try{
                    for(int cname=0; cname<detailListMainActivityCourseCat.size(); cname++) {

                        strCourseNames.add(detailListMainActivityCourseCat.get(cname).getmCourseCategoryNameBn());

                    }
                }*/

                setRecyclerView();

            }
            catch (Exception ex){
                Log.d("", "onPostExecute: ");
            }
        }
    }

    private void setRecyclerView() {
        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setNestedScrollingEnabled(false);

        map1.put("id", detailListMainActivityCourseCat.get(0).getmCourseCategoryId());
        map1.put("name", detailListMainActivityCourseCat.get(0).getmCourseCategoryNameEn());
        map1.put("rating", "");

        new Category1().execute(urlGetCourses);
    }

    private void setRecyclerView2() {
        recyclerView2 = findViewById(R.id.my_recycler_view2);
        recyclerView2.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recyclerView2.setLayoutManager(layoutManager);

        //nestedScrollView.setSmoothScrollingEnabled(true);
        //nestedScrollView.fullScroll(View.FOCUS_UP);
        recyclerView2.setNestedScrollingEnabled(false);
    }

    private void setRecyclerView3() {
        recyclerView3 = findViewById(R.id.my_recycler_view3);
        recyclerView3.setHasFixedSize(true);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recyclerView3.setLayoutManager(layoutManager);

        //nestedScrollView.setSmoothScrollingEnabled(true);
        //nestedScrollView.fullScroll(View.FOCUS_UP);
        recyclerView3.setNestedScrollingEnabled(false);
    }

    private void setRecyclerView4() {
        recyclerView4 = findViewById(R.id.my_recycler_view4);
        recyclerView4.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recyclerView4.setLayoutManager(layoutManager);
        recyclerView4.setNestedScrollingEnabled(false);
    }

    private void setRecyclerView5() {
        recyclerView5 = findViewById(R.id.my_recycler_view5);
        recyclerView5.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recyclerView5.setLayoutManager(layoutManager);

        recyclerView5.setNestedScrollingEnabled(false);
    }

    private void setRecyclerView6() {
        recyclerView6 = findViewById(R.id.my_recycler_view6);
        recyclerView6.setHasFixedSize(true);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recyclerView6.setLayoutManager(layoutManager);

        recyclerView6.setNestedScrollingEnabled(false);
    }

    private void setRecyclerView7() {
        recyclerView7 = findViewById(R.id.my_recycler_view7);
        recyclerView7.setHasFixedSize(true);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recyclerView7.setLayoutManager(layoutManager);

        recyclerView7.setNestedScrollingEnabled(false);
    }

    private void setRecyclerView8() {
        recyclerView8 = findViewById(R.id.my_recycler_view8);
        recyclerView8.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recyclerView8.setLayoutManager(layoutManager);

        recyclerView8.setNestedScrollingEnabled(false);
    }

    private void setRecyclerView9() {
        recyclerView9 = findViewById(R.id.my_recycler_view9);
        recyclerView9.setHasFixedSize(true);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recyclerView9.setLayoutManager(layoutManager);

        recyclerView9.setNestedScrollingEnabled(false);
    }

    private void setRecyclerView10() {
        recyclerView10 = findViewById(R.id.my_recycler_view10);
        recyclerView10.setHasFixedSize(true);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recyclerView10.setLayoutManager(layoutManager);

        recyclerView10.setNestedScrollingEnabled(false);
    }

    //Category call

    public class Category1 extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mProgressSpinner.setIndeterminate(true);
            mProgressSpinner.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {

            String data = performPostCall(params[0], map1);

            detailListMainActivityCourse=new ArrayList<DetailDataModelCourses>();

            //detailList=new ArrayList<DetailDataModelCourses>();

            detailListMainActivityCourseThumbnail=new ArrayList<DetailDataModelCoursesThumbnails>();

            detailListMainActivityCourseDetailContentss = new ArrayList<>();
            detailListMainActivityCourseDetailUnits = new ArrayList<>();
            detailListMainActivityCourseDetailUnitQuizList = new ArrayList<>();

            DetailDataModelCourses model = new DetailDataModelCourses();

            try{

                JSONObject jObjectMain = new JSONObject(data);
                JSONObject jObject;

                JSONArray objectCourse = (JSONArray) jObjectMain.getJSONArray("data");

                try {

                    for (int ec = 0; ec < objectCourse.length(); ec++)
                    {
                        DetailDataModelCourses modelAlterMainActivity = new DetailDataModelCourses();

                        JSONObject jObjEnrolledCourses = objectCourse.getJSONObject(ec);

                        String featured = jObjEnrolledCourses.getString("featured");
                        String Eid = jObjEnrolledCourses.getString("id");
                        String Edetails = jObjEnrolledCourses.getString("details");
                        String Eadmission_status = jObjEnrolledCourses.getString("admission_status");
                        String averageRating = jObjEnrolledCourses.getString("averageRating");
                        String certificate_alias_name = jObjEnrolledCourses.getString("certificate_alias_name");
                        String clone_status = jObjEnrolledCourses.getString("clone_status");
                        String code = jObjEnrolledCourses.getString("code");
                        String courses_for_status = jObjEnrolledCourses.getString("courses_for_status");
                        String course_alias_name = jObjEnrolledCourses.getString("course_alias_name");
                        String course_motto = jObjEnrolledCourses.getString("course_motto");
                        String created_at = jObjEnrolledCourses.getString("created_at");
                        String duration = jObjEnrolledCourses.getString("duration");
                        String end_date = jObjEnrolledCourses.getString("end_date");
                        String enrolment_approval_status = jObjEnrolledCourses.getString("enrolment_approval_status");

                        modelAlterMainActivity.setmCertificateAliasName(certificate_alias_name);
                        modelAlterMainActivity.setmAdmissionStatus(Eadmission_status);
                        modelAlterMainActivity.setmAverageRating(averageRating);
                        modelAlterMainActivity.setmCloneStatus(clone_status);
                        modelAlterMainActivity.setmCode(code);
                        modelAlterMainActivity.setmCreatedAt(created_at);
                        modelAlterMainActivity.setmDuration(duration);
                        modelAlterMainActivity.setmEndDate(end_date);
                        modelAlterMainActivity.setmId(Eid);
                        modelAlterMainActivity.setmDetails(Edetails);
                        modelAlterMainActivity.setmFeatured(featured);
                        modelAlterMainActivity.setmEnrolmentApprovalStatus(enrolment_approval_status);
                        modelAlterMainActivity.setmCursesForStatus(courses_for_status);
                        modelAlterMainActivity.setmCourseAliasName(course_alias_name);
                        modelAlterMainActivity.setmCourseMotto(course_motto);
                        //modelForMainActivityCourse.setmStatus(status);

                       // detailListMainActivityCourse.add(modelAlterMainActivity);

                        try
                        {
                            jObject = jObjEnrolledCourses.getJSONObject("syllabus");

                            JSONArray objectEnrollCourseUnits = (JSONArray) jObject.getJSONArray("units");

                            mUnitArrayListNew = new ArrayList<>();

                            for (int ecu=0; ecu<objectEnrollCourseUnits.length()-1; ecu++)
                            {
                                DetailDataModelCoursesDetailContents modelUnitElements = new DetailDataModelCoursesDetailContents();

                                JSONObject objectUnitElements = (JSONObject) objectEnrollCourseUnits.get(ecu);

                                String orderEcu = objectUnitElements.getString("order");
                                String nameEcu = objectUnitElements.getString("name");

                                modelUnitElements.setUnitOrders(orderEcu);
                                modelUnitElements.setUnitNames(nameEcu);

                                mUnitArrayListNew.add(modelUnitElements);
                            }

                            //for parsing syllebus strings

                            DetailDataModelCourses model7 = new DetailDataModelCourses();

                            String study_mode_Syllebus = jObject.getString("study_mode");

                            model7.setStudyModeSyllebus(study_mode_Syllebus);

                            JSONObject jObjectCourse = jObjEnrolledCourses.getJSONObject("course");

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

                            detailListMainActivityCourseThumbnail.add(modelCourseThumbnail);

                            //model.setmArrayListThumbnails(detailListMainActivityCourseThumbnail);

                            //for parsing Updated by strings
                            JSONObject jObjectUpdatedBy = jObjEnrolledCourses.getJSONObject("UpdatedBy");


                            DetailDataModelCourses model9 = new DetailDataModelCourses();

                            String education_statusUpdatedBy = jObjectUpdatedBy.getString("education_status");
                            String emailUpdatedBy = jObjectUpdatedBy.getString("email");
                            String idUpdatedBy = jObjectUpdatedBy.getString("id");
                            String nameUpdatedBy = jObjectUpdatedBy.getString("name");
                            String phoneUpdatedBy = jObjectUpdatedBy.getString("phone");
                            String UserInfoUpdatedBy = jObjectUpdatedBy.getString("UserInfo");
                            String usernameUpdatedBy = jObjectUpdatedBy.getString("username");

                            model9.setEducation_statusUpdatedBy(education_statusUpdatedBy);
                            model9.setEmailUpdatedBy(emailUpdatedBy);
                            model9.setIdUpdatedBy(idUpdatedBy);
                            model9.setNameUpdatedBy(nameUpdatedBy);
                            model9.setPhoneUpdatedBy(phoneUpdatedBy);
                            model9.setUserInfoUpdatedBy(UserInfoUpdatedBy);
                            model9.setUsernameUpdatedBy(usernameUpdatedBy);

                            //for parsing owner strings
                            JSONObject jObjectOwner = jObjEnrolledCourses.getJSONObject("owner");

                            DetailDataModelCourses model10 = new DetailDataModelCourses();

                            String updated_at_owner = jObjectOwner.getString("updated_at");
                            String institution_name_owner = jObjectOwner.getString("institution_name");
                            String id_owner = jObjectOwner.getString("id");
                            String created_at_owner = jObjectOwner.getString("created_at");

                            model10.setUpdated_at_owner(updated_at_owner);
                            model10.setInstitution_name_owner(institution_name_owner);
                            model10.setId_owner(id_owner);
                            model10.setCreated_at_owner(created_at_owner);

                            //for parsing created by strings
                            JSONObject jObjectCreatedBy = jObjEnrolledCourses.getJSONObject("CreatedBy");


                            DetailDataModelCourses model11 = new DetailDataModelCourses();

                            String education_statusCreatedBy = jObjectCreatedBy.getString("education_status");
                            String emailCreatedBy = jObjectCreatedBy.getString("email");
                            String idCreatedBy = jObjectCreatedBy.getString("id");
                            String nameCreatedBy = jObjectCreatedBy.getString("name");
                            String phoneCreatedBy = jObjectCreatedBy.getString("phone");
                            String UserInfoCreatedBy = jObjectCreatedBy.getString("UserInfo");
                            String usernameCreatedBy = jObjectCreatedBy.getString("username");

                            model11.setEducation_statusUpdatedBy(education_statusCreatedBy);
                            model11.setEmailUpdatedBy(emailCreatedBy);
                            model11.setIdCreatedBy(idCreatedBy);
                            model11.setNameCreatedBy(nameCreatedBy);
                            model11.setPhoneCreatedBy(phoneCreatedBy);
                            model11.setUserInfoCreatedBy(UserInfoCreatedBy);
                            model11.setUsernameCreatedBy(usernameCreatedBy);

                            // parsing from syllebus

                            try{
                                mContentArrayListNew = new ArrayList<>();


                                for (int ii = 0; ii < jObject.length()-2; ii++)
                                {
                                    JSONObject jSObject2 = jObject.getJSONObject("" + ii);

                                    //for parsing lessons > {0} > "syllebus" > "0" > "data"

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
                                            //model2.setmTitleAnother(mTitle);//*

                                            // For parsing "file_type" > "data" > {0} > {0} > "syllebus" > "0" > "data"

                                            JSONObject jObjAgain2 = jObjAgain.getJSONObject("file_type");

                                            DetailDataModelCourses modelFileType = new DetailDataModelCourses();

                                            String pdf = jObjAgain2.getString("pdf");
                                            String excel = jObjAgain2.getString("excel");
                                            String doc = jObjAgain2.getString("doc");
                                            String csv = jObjAgain2.getString("csv");

                                            modelFileType.setmPdf(pdf);
                                            modelFileType.setmXcel(excel);
                                            modelFileType.setmDoc(doc);
                                            modelFileType.setmCsv(csv);

                                            try{
                                                DetailDataModelCoursesDetailContents modelCourseContents = new DetailDataModelCoursesDetailContents();

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
                                                modelCourseContents.setDescription_content(description_content);
                                                modelCourseContents.setFile_encode_path(file_encode_path);
                                                modelCourseContents.setFile_name(file_name);
                                                modelCourseContents.setId_content(id_content);
                                                modelCourseContents.setLicense(license);
                                                modelCourseContents.setOwner_id(owner_id);
                                                modelCourseContents.setCreated_at_content(created_at_content);

                                                mContentArrayListNew.add(modelCourseContents);
                                            }
                                            catch (Exception ex){
                                                Log.d("", "onResponse: ");
                                            }

                                            //For parsing Quizes

                                            //JSONArray jObjQuizes = (JSONArray) jSObject3.getJSONArray("multi_ques_list");

                                            // For parsing object "Content" > {0} > {0} > "syllebus" > "0" > "data"

                                            DetailDataModelCourses model5 = new DetailDataModelCourses();

                                            if (content_type.equalsIgnoreCase("quiz")) {
                                                try {

                                                    for (int ctq = 0; ctq < jSObject3.length(); ctq++) {

                                                        JSONArray jSonObjMultiQ2 = (JSONArray) jSObject3.getJSONArray("ques_list");

                                                        mUnitQuizList = new ArrayList<>();

                                                        for(int qlist2=0; qlist2<jSonObjMultiQ2.length(); qlist2++){

                                                            DetailDataModelCoursesDetailContents modelUnitQuizElements2 = new DetailDataModelCoursesDetailContents();

                                                            JSONObject objectAgainAnother2 = (JSONObject) jSonObjMultiQ2.get(qlist2);

                                                            String qTitle = objectAgainAnother2.getString("title");

                                                            qTitle = qTitle.replace("<p>","");
                                                            qTitle = qTitle.replace("</p>","");

                                                            modelUnitQuizElements2.setmQuizTitle(qTitle);

                                                            mUnitQuizList.add(modelUnitQuizElements2);
                                                        }
                                                    }
                                                } catch (Exception ex) {
                                                    Log.d("", "onResponse: ");
                                                }
                                            }

                                            if (quizYesOrNot.equalsIgnoreCase("1")) {

                                                try {

                                                    for (int l = 0; l < jSObject3.length(); l++) {

                                                        JSONArray jSonObjMultiQ = (JSONArray) jSObject3.getJSONArray("multi_ques_list");

                                                        for (int mql = 0; mql < jSonObjMultiQ.length(); mql++) {

                                                            JSONObject objectAgainAnother2 = (JSONObject) jSonObjMultiQ.get(mql);

                                                            String mPulse = objectAgainAnother2.getString("pulse");

                                                            
                                                        }


                                                    }
                                                } catch (Exception ex) {
                                                    Log.d("", "onResponse: ");
                                                }
                                            } else {
                                                //  Toast.makeText(mContext, "", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    }
                                    catch (Exception ex){
                                        Log.d("", "onResponse: ");
                                    }
                                }
                            }
                            catch (Exception ex){
                                Log.d("", "onResponse: ");
                            }

                            detailListMainActivityCourseDetailContentss.add(mContentArrayListNew);

                            detailListMainActivityCourseDetailUnits.add(mUnitArrayListNew);

                            detailListMainActivityCourseDetailUnitQuizList.add(mUnitQuizList);
                        }



                        catch (Exception ex){
                            Log.d("", "onResponse: ");
                        }

                        modelAlterMainActivity.setmArrayListThumbnails(detailListMainActivityCourseThumbnail);
                        modelAlterMainActivity.setmArrayListContentDetails(detailListMainActivityCourseDetailContentss);
                        modelAlterMainActivity.setmArrayListCourseUnits(detailListMainActivityCourseDetailUnits);
                        modelAlterMainActivity.setmArrayListCourseQuizs(detailListMainActivityCourseDetailUnitQuizList);

                        detailListMainActivityCourse.add(modelAlterMainActivity);


                    }



                }


                catch (Exception ex) {
                    Log.d("", "onResponse: ");
                }


            }
            catch (Exception ex){
                Log.d("", "doInBackground: ");
            }




            //GlobalVar.allDataDetailList.add(modelAlterMainActivity);

            return data;
        }

        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);

            adapter=new RecyclerViewAdapterCategory1(detailListMainActivityCourse,mContext);

            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            mProgressSpinner.setVisibility(View.GONE);

            map2.put("id", detailListMainActivityCourseCat.get(1).getmCourseCategoryId());
            map2.put("name", detailListMainActivityCourseCat.get(1).getmCourseCategoryNameEn());
            map2.put("rating", "");

            new Category2().execute(urlGetCourses);
        }

        @Override
        protected void onCancelled() {

        }
    }

    public class Category2 extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mProgressSpinner2.setIndeterminate(true);
            mProgressSpinner2.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {

            String data= performPostCall(params[0],map2);


            detailListMainActivityCourse2=new ArrayList<DetailDataModelCourses>();

            //detailList=new ArrayList<DetailDataModelCourses>();

            detailListMainActivityCourseThumbnail=new ArrayList<DetailDataModelCoursesThumbnails>();

            detailListMainActivityCourseDetailContentss = new ArrayList<>();
            detailListMainActivityCourseDetailUnits = new ArrayList<>();
            detailListMainActivityCourseDetailUnitQuizList = new ArrayList<>();

            DetailDataModelCourses model = new DetailDataModelCourses();

            try{

                JSONObject jObjectMain = new JSONObject(data);
                JSONObject jObject;

                JSONArray objectCourse = (JSONArray) jObjectMain.getJSONArray("data");

                try {



                    for (int ec = 0; ec < objectCourse.length(); ec++)
                    {
                        DetailDataModelCourses modelAlterMainActivity = new DetailDataModelCourses();

                        JSONObject jObjEnrolledCourses = objectCourse.getJSONObject(ec);

                        String featured = jObjEnrolledCourses.getString("featured");
                        String Eid = jObjEnrolledCourses.getString("id");
                        String Edetails = jObjEnrolledCourses.getString("details");
                        String Eadmission_status = jObjEnrolledCourses.getString("admission_status");
                        String averageRating = jObjEnrolledCourses.getString("averageRating");
                        String certificate_alias_name = jObjEnrolledCourses.getString("certificate_alias_name");
                        String clone_status = jObjEnrolledCourses.getString("clone_status");
                        String code = jObjEnrolledCourses.getString("code");
                        String courses_for_status = jObjEnrolledCourses.getString("courses_for_status");
                        String course_alias_name = jObjEnrolledCourses.getString("course_alias_name");
                        String course_motto = jObjEnrolledCourses.getString("course_motto");
                        String created_at = jObjEnrolledCourses.getString("created_at");
                        String duration = jObjEnrolledCourses.getString("duration");
                        String end_date = jObjEnrolledCourses.getString("end_date");
                        String enrolment_approval_status = jObjEnrolledCourses.getString("enrolment_approval_status");

                        modelAlterMainActivity.setmCertificateAliasName(certificate_alias_name);
                        modelAlterMainActivity.setmAdmissionStatus(Eadmission_status);
                        modelAlterMainActivity.setmAverageRating(averageRating);
                        modelAlterMainActivity.setmCloneStatus(clone_status);
                        modelAlterMainActivity.setmCode(code);
                        modelAlterMainActivity.setmCreatedAt(created_at);
                        modelAlterMainActivity.setmDuration(duration);
                        modelAlterMainActivity.setmEndDate(end_date);
                        modelAlterMainActivity.setmId(Eid);
                        modelAlterMainActivity.setmDetails(Edetails);
                        modelAlterMainActivity.setmFeatured(featured);
                        modelAlterMainActivity.setmEnrolmentApprovalStatus(enrolment_approval_status);
                        modelAlterMainActivity.setmCursesForStatus(courses_for_status);
                        modelAlterMainActivity.setmCourseAliasName(course_alias_name);
                        modelAlterMainActivity.setmCourseMotto(course_motto);
                        //modelForMainActivityCourse.setmStatus(status);

                        // detailListMainActivityCourse.add(modelAlterMainActivity);

                        try
                        {
                            jObject = jObjEnrolledCourses.getJSONObject("syllabus");

                            JSONArray objectEnrollCourseUnits = (JSONArray) jObject.getJSONArray("units");

                            mUnitArrayListNew = new ArrayList<>();

                            for (int ecu=0; ecu<objectEnrollCourseUnits.length()-1; ecu++)
                            {
                                DetailDataModelCoursesDetailContents modelUnitElements = new DetailDataModelCoursesDetailContents();

                                JSONObject objectUnitElements = (JSONObject) objectEnrollCourseUnits.get(ecu);

                                String orderEcu = objectUnitElements.getString("order");
                                String nameEcu = objectUnitElements.getString("name");

                                modelUnitElements.setUnitOrders(orderEcu);
                                modelUnitElements.setUnitNames(nameEcu);

                                mUnitArrayListNew.add(modelUnitElements);
                            }

                            //for parsing syllebus strings

                            DetailDataModelCourses model7 = new DetailDataModelCourses();

                            String study_mode_Syllebus = jObject.getString("study_mode");

                            model7.setStudyModeSyllebus(study_mode_Syllebus);

                            JSONObject jObjectCourse = jObjEnrolledCourses.getJSONObject("course");

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

                            detailListMainActivityCourseThumbnail.add(modelCourseThumbnail);

                            //model.setmArrayListThumbnails(detailListMainActivityCourseThumbnail);

                            //for parsing Updated by strings
                            JSONObject jObjectUpdatedBy = jObjEnrolledCourses.getJSONObject("UpdatedBy");


                            DetailDataModelCourses model9 = new DetailDataModelCourses();

                            String education_statusUpdatedBy = jObjectUpdatedBy.getString("education_status");
                            String emailUpdatedBy = jObjectUpdatedBy.getString("email");
                            String idUpdatedBy = jObjectUpdatedBy.getString("id");
                            String nameUpdatedBy = jObjectUpdatedBy.getString("name");
                            String phoneUpdatedBy = jObjectUpdatedBy.getString("phone");
                            String UserInfoUpdatedBy = jObjectUpdatedBy.getString("UserInfo");
                            String usernameUpdatedBy = jObjectUpdatedBy.getString("username");

                            model9.setEducation_statusUpdatedBy(education_statusUpdatedBy);
                            model9.setEmailUpdatedBy(emailUpdatedBy);
                            model9.setIdUpdatedBy(idUpdatedBy);
                            model9.setNameUpdatedBy(nameUpdatedBy);
                            model9.setPhoneUpdatedBy(phoneUpdatedBy);
                            model9.setUserInfoUpdatedBy(UserInfoUpdatedBy);
                            model9.setUsernameUpdatedBy(usernameUpdatedBy);

                            //for parsing owner strings
                            JSONObject jObjectOwner = jObjEnrolledCourses.getJSONObject("owner");

                            DetailDataModelCourses model10 = new DetailDataModelCourses();

                            String updated_at_owner = jObjectOwner.getString("updated_at");
                            String institution_name_owner = jObjectOwner.getString("institution_name");
                            String id_owner = jObjectOwner.getString("id");
                            String created_at_owner = jObjectOwner.getString("created_at");

                            model10.setUpdated_at_owner(updated_at_owner);
                            model10.setInstitution_name_owner(institution_name_owner);
                            model10.setId_owner(id_owner);
                            model10.setCreated_at_owner(created_at_owner);

                            //for parsing created by strings
                            JSONObject jObjectCreatedBy = jObjEnrolledCourses.getJSONObject("CreatedBy");


                            DetailDataModelCourses model11 = new DetailDataModelCourses();

                            String education_statusCreatedBy = jObjectCreatedBy.getString("education_status");
                            String emailCreatedBy = jObjectCreatedBy.getString("email");
                            String idCreatedBy = jObjectCreatedBy.getString("id");
                            String nameCreatedBy = jObjectCreatedBy.getString("name");
                            String phoneCreatedBy = jObjectCreatedBy.getString("phone");
                            String UserInfoCreatedBy = jObjectCreatedBy.getString("UserInfo");
                            String usernameCreatedBy = jObjectCreatedBy.getString("username");

                            model11.setEducation_statusUpdatedBy(education_statusCreatedBy);
                            model11.setEmailUpdatedBy(emailCreatedBy);
                            model11.setIdCreatedBy(idCreatedBy);
                            model11.setNameCreatedBy(nameCreatedBy);
                            model11.setPhoneCreatedBy(phoneCreatedBy);
                            model11.setUserInfoCreatedBy(UserInfoCreatedBy);
                            model11.setUsernameCreatedBy(usernameCreatedBy);

                            // parsing from syllebus

                            try{
                                mContentArrayListNew = new ArrayList<>();


                                for (int ii = 0; ii < jObject.length()-2; ii++)
                                {
                                    JSONObject jSObject2 = jObject.getJSONObject("" + ii);

                                    //for parsing lessons > {0} > "syllebus" > "0" > "data"

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
                                            //model2.setmTitleAnother(mTitle);//*

                                            // For parsing "file_type" > "data" > {0} > {0} > "syllebus" > "0" > "data"

                                            JSONObject jObjAgain2 = jObjAgain.getJSONObject("file_type");

                                            DetailDataModelCourses modelFileType = new DetailDataModelCourses();

                                            String pdf = jObjAgain2.getString("pdf");
                                            String excel = jObjAgain2.getString("excel");
                                            String doc = jObjAgain2.getString("doc");
                                            String csv = jObjAgain2.getString("csv");

                                            modelFileType.setmPdf(pdf);
                                            modelFileType.setmXcel(excel);
                                            modelFileType.setmDoc(doc);
                                            modelFileType.setmCsv(csv);

                                            try{
                                                DetailDataModelCoursesDetailContents modelCourseContents = new DetailDataModelCoursesDetailContents();

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
                                                modelCourseContents.setDescription_content(description_content);
                                                modelCourseContents.setFile_encode_path(file_encode_path);
                                                modelCourseContents.setFile_name(file_name);
                                                modelCourseContents.setId_content(id_content);
                                                modelCourseContents.setLicense(license);
                                                modelCourseContents.setOwner_id(owner_id);
                                                modelCourseContents.setCreated_at_content(created_at_content);

                                                mContentArrayListNew.add(modelCourseContents);
                                            }
                                            catch (Exception ex){
                                                Log.d("", "onResponse: ");
                                            }

                                            //For parsing Quizes

                                            //JSONArray jObjQuizes = (JSONArray) jSObject3.getJSONArray("multi_ques_list");

                                            // For parsing object "Content" > {0} > {0} > "syllebus" > "0" > "data"

                                            DetailDataModelCourses model5 = new DetailDataModelCourses();

                                            if (content_type.equalsIgnoreCase("quiz")) {
                                                try {

                                                    for (int ctq = 0; ctq < jSObject3.length(); ctq++) {

                                                        JSONArray jSonObjMultiQ2 = (JSONArray) jSObject3.getJSONArray("ques_list");

                                                        mUnitQuizList = new ArrayList<>();

                                                        for(int qlist2=0; qlist2<jSonObjMultiQ2.length(); qlist2++){

                                                            DetailDataModelCoursesDetailContents modelUnitQuizElements2 = new DetailDataModelCoursesDetailContents();

                                                            JSONObject objectAgainAnother2 = (JSONObject) jSonObjMultiQ2.get(qlist2);

                                                            String qTitle = objectAgainAnother2.getString("title");

                                                            qTitle = qTitle.replace("<p>","");
                                                            qTitle = qTitle.replace("</p>","");

                                                            modelUnitQuizElements2.setmQuizTitle(qTitle);

                                                            mUnitQuizList.add(modelUnitQuizElements2);
                                                        }
                                                    }
                                                } catch (Exception ex) {
                                                    Log.d("", "onResponse: ");
                                                }
                                            }

                                            if (quizYesOrNot.equalsIgnoreCase("1")) {

                                                try {

                                                    for (int l = 0; l < jSObject3.length(); l++) {

                                                        JSONArray jSonObjMultiQ = (JSONArray) jSObject3.getJSONArray("multi_ques_list");

                                                        for (int mql = 0; mql < jSonObjMultiQ.length(); mql++) {

                                                            JSONObject objectAgainAnother2 = (JSONObject) jSonObjMultiQ.get(mql);

                                                            String mPulse = objectAgainAnother2.getString("pulse");

                                                            
                                                        }


                                                    }
                                                } catch (Exception ex) {
                                                    Log.d("", "onResponse: ");
                                                }
                                            } else {
                                                //  Toast.makeText(mContext, "", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    }
                                    catch (Exception ex){
                                        Log.d("", "onResponse: ");
                                    }
                                }
                            }
                            catch (Exception ex){
                                Log.d("", "onResponse: ");
                            }

                            detailListMainActivityCourseDetailContentss.add(mContentArrayListNew);

                            detailListMainActivityCourseDetailUnits.add(mUnitArrayListNew);

                            detailListMainActivityCourseDetailUnitQuizList.add(mUnitQuizList);
                        }



                        catch (Exception ex){
                            Log.d("", "onResponse: ");
                        }

                        modelAlterMainActivity.setmArrayListThumbnails(detailListMainActivityCourseThumbnail);
                        modelAlterMainActivity.setmArrayListContentDetails(detailListMainActivityCourseDetailContentss);
                        modelAlterMainActivity.setmArrayListCourseUnits(detailListMainActivityCourseDetailUnits);
                        modelAlterMainActivity.setmArrayListCourseQuizs(detailListMainActivityCourseDetailUnitQuizList);

                        detailListMainActivityCourse2.add(modelAlterMainActivity);


                    }



                }


                catch (Exception ex) {
                    Log.d("", "onResponse: ");
                }


            }
            catch (Exception ex){
                Log.d("", "doInBackground: ");
            }

            return data;
        }

        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);

            setRecyclerView2();

            adapter2=new RecyclerViewAdapterCategory2(detailListMainActivityCourse2,mContext);

            recyclerView2.setAdapter(adapter2);
            adapter2.notifyDataSetChanged();

            mProgressSpinner2.setVisibility(View.GONE);

            map3.put("id", detailListMainActivityCourseCat.get(2).getmCourseCategoryId());
            map3.put("name", detailListMainActivityCourseCat.get(2).getmCourseCategoryNameEn());
            map3.put("rating", "");

            new Category3().execute(urlGetCourses);
        }

        @Override
        protected void onCancelled() {

        }
    }
    public class Category3 extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mProgressSpinner3.setIndeterminate(true);
            mProgressSpinner3.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {

            String data= performPostCall(params[0],map3);

            detailListMainActivityCourse3=new ArrayList<DetailDataModelCourses>();

            //detailList=new ArrayList<DetailDataModelCourses>();

            detailListMainActivityCourseThumbnail=new ArrayList<DetailDataModelCoursesThumbnails>();

            detailListMainActivityCourseDetailContentss = new ArrayList<>();
            detailListMainActivityCourseDetailUnits = new ArrayList<>();
            detailListMainActivityCourseDetailUnitQuizList = new ArrayList<>();

            DetailDataModelCourses model = new DetailDataModelCourses();

            try{

                JSONObject jObjectMain = new JSONObject(data);
                JSONObject jObject;

                JSONArray objectCourse = (JSONArray) jObjectMain.getJSONArray("data");

                try {



                    for (int ec = 0; ec < objectCourse.length(); ec++)
                    {
                        DetailDataModelCourses modelAlterMainActivity = new DetailDataModelCourses();

                        JSONObject jObjEnrolledCourses = objectCourse.getJSONObject(ec);

                        String featured = jObjEnrolledCourses.getString("featured");
                        String Eid = jObjEnrolledCourses.getString("id");
                        String Edetails = jObjEnrolledCourses.getString("details");
                        String Eadmission_status = jObjEnrolledCourses.getString("admission_status");
                        String averageRating = jObjEnrolledCourses.getString("averageRating");
                        String certificate_alias_name = jObjEnrolledCourses.getString("certificate_alias_name");
                        String clone_status = jObjEnrolledCourses.getString("clone_status");
                        String code = jObjEnrolledCourses.getString("code");
                        String courses_for_status = jObjEnrolledCourses.getString("courses_for_status");
                        String course_alias_name = jObjEnrolledCourses.getString("course_alias_name");
                        String course_motto = jObjEnrolledCourses.getString("course_motto");
                        String created_at = jObjEnrolledCourses.getString("created_at");
                        String duration = jObjEnrolledCourses.getString("duration");
                        String end_date = jObjEnrolledCourses.getString("end_date");
                        String enrolment_approval_status = jObjEnrolledCourses.getString("enrolment_approval_status");

                        modelAlterMainActivity.setmCertificateAliasName(certificate_alias_name);
                        modelAlterMainActivity.setmAdmissionStatus(Eadmission_status);
                        modelAlterMainActivity.setmAverageRating(averageRating);
                        modelAlterMainActivity.setmCloneStatus(clone_status);
                        modelAlterMainActivity.setmCode(code);
                        modelAlterMainActivity.setmCreatedAt(created_at);
                        modelAlterMainActivity.setmDuration(duration);
                        modelAlterMainActivity.setmEndDate(end_date);
                        modelAlterMainActivity.setmId(Eid);
                        modelAlterMainActivity.setmDetails(Edetails);
                        modelAlterMainActivity.setmFeatured(featured);
                        modelAlterMainActivity.setmEnrolmentApprovalStatus(enrolment_approval_status);
                        modelAlterMainActivity.setmCursesForStatus(courses_for_status);
                        modelAlterMainActivity.setmCourseAliasName(course_alias_name);
                        modelAlterMainActivity.setmCourseMotto(course_motto);
                        //modelForMainActivityCourse.setmStatus(status);

                        // detailListMainActivityCourse.add(modelAlterMainActivity);

                        try
                        {
                            jObject = jObjEnrolledCourses.getJSONObject("syllabus");

                            JSONArray objectEnrollCourseUnits = (JSONArray) jObject.getJSONArray("units");

                            mUnitArrayListNew = new ArrayList<>();

                            for (int ecu=0; ecu<objectEnrollCourseUnits.length()-1; ecu++)
                            {
                                DetailDataModelCoursesDetailContents modelUnitElements = new DetailDataModelCoursesDetailContents();

                                JSONObject objectUnitElements = (JSONObject) objectEnrollCourseUnits.get(ecu);

                                String orderEcu = objectUnitElements.getString("order");
                                String nameEcu = objectUnitElements.getString("name");

                                modelUnitElements.setUnitOrders(orderEcu);
                                modelUnitElements.setUnitNames(nameEcu);

                                mUnitArrayListNew.add(modelUnitElements);
                            }

                            //for parsing syllebus strings

                            DetailDataModelCourses model7 = new DetailDataModelCourses();

                            String study_mode_Syllebus = jObject.getString("study_mode");

                            model7.setStudyModeSyllebus(study_mode_Syllebus);

                            JSONObject jObjectCourse = jObjEnrolledCourses.getJSONObject("course");

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

                            detailListMainActivityCourseThumbnail.add(modelCourseThumbnail);

                            //model.setmArrayListThumbnails(detailListMainActivityCourseThumbnail);

                            //for parsing Updated by strings
                            JSONObject jObjectUpdatedBy = jObjEnrolledCourses.getJSONObject("UpdatedBy");


                            DetailDataModelCourses model9 = new DetailDataModelCourses();

                            String education_statusUpdatedBy = jObjectUpdatedBy.getString("education_status");
                            String emailUpdatedBy = jObjectUpdatedBy.getString("email");
                            String idUpdatedBy = jObjectUpdatedBy.getString("id");
                            String nameUpdatedBy = jObjectUpdatedBy.getString("name");
                            String phoneUpdatedBy = jObjectUpdatedBy.getString("phone");
                            String UserInfoUpdatedBy = jObjectUpdatedBy.getString("UserInfo");
                            String usernameUpdatedBy = jObjectUpdatedBy.getString("username");

                            model9.setEducation_statusUpdatedBy(education_statusUpdatedBy);
                            model9.setEmailUpdatedBy(emailUpdatedBy);
                            model9.setIdUpdatedBy(idUpdatedBy);
                            model9.setNameUpdatedBy(nameUpdatedBy);
                            model9.setPhoneUpdatedBy(phoneUpdatedBy);
                            model9.setUserInfoUpdatedBy(UserInfoUpdatedBy);
                            model9.setUsernameUpdatedBy(usernameUpdatedBy);

                            //for parsing owner strings
                            JSONObject jObjectOwner = jObjEnrolledCourses.getJSONObject("owner");

                            DetailDataModelCourses model10 = new DetailDataModelCourses();

                            String updated_at_owner = jObjectOwner.getString("updated_at");
                            String institution_name_owner = jObjectOwner.getString("institution_name");
                            String id_owner = jObjectOwner.getString("id");
                            String created_at_owner = jObjectOwner.getString("created_at");

                            model10.setUpdated_at_owner(updated_at_owner);
                            model10.setInstitution_name_owner(institution_name_owner);
                            model10.setId_owner(id_owner);
                            model10.setCreated_at_owner(created_at_owner);

                            //for parsing created by strings
                            JSONObject jObjectCreatedBy = jObjEnrolledCourses.getJSONObject("CreatedBy");


                            DetailDataModelCourses model11 = new DetailDataModelCourses();

                            String education_statusCreatedBy = jObjectCreatedBy.getString("education_status");
                            String emailCreatedBy = jObjectCreatedBy.getString("email");
                            String idCreatedBy = jObjectCreatedBy.getString("id");
                            String nameCreatedBy = jObjectCreatedBy.getString("name");
                            String phoneCreatedBy = jObjectCreatedBy.getString("phone");
                            String UserInfoCreatedBy = jObjectCreatedBy.getString("UserInfo");
                            String usernameCreatedBy = jObjectCreatedBy.getString("username");

                            model11.setEducation_statusUpdatedBy(education_statusCreatedBy);
                            model11.setEmailUpdatedBy(emailCreatedBy);
                            model11.setIdCreatedBy(idCreatedBy);
                            model11.setNameCreatedBy(nameCreatedBy);
                            model11.setPhoneCreatedBy(phoneCreatedBy);
                            model11.setUserInfoCreatedBy(UserInfoCreatedBy);
                            model11.setUsernameCreatedBy(usernameCreatedBy);

                            // parsing from syllebus

                            try{
                                mContentArrayListNew = new ArrayList<>();


                                for (int ii = 0; ii < jObject.length()-2; ii++)
                                {
                                    JSONObject jSObject2 = jObject.getJSONObject("" + ii);

                                    //for parsing lessons > {0} > "syllebus" > "0" > "data"

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
                                            //model2.setmTitleAnother(mTitle);//*

                                            // For parsing "file_type" > "data" > {0} > {0} > "syllebus" > "0" > "data"

                                            JSONObject jObjAgain2 = jObjAgain.getJSONObject("file_type");

                                            DetailDataModelCourses modelFileType = new DetailDataModelCourses();

                                            String pdf = jObjAgain2.getString("pdf");
                                            String excel = jObjAgain2.getString("excel");
                                            String doc = jObjAgain2.getString("doc");
                                            String csv = jObjAgain2.getString("csv");

                                            modelFileType.setmPdf(pdf);
                                            modelFileType.setmXcel(excel);
                                            modelFileType.setmDoc(doc);
                                            modelFileType.setmCsv(csv);

                                            try{
                                                DetailDataModelCoursesDetailContents modelCourseContents = new DetailDataModelCoursesDetailContents();

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
                                                modelCourseContents.setDescription_content(description_content);
                                                modelCourseContents.setFile_encode_path(file_encode_path);
                                                modelCourseContents.setFile_name(file_name);
                                                modelCourseContents.setId_content(id_content);
                                                modelCourseContents.setLicense(license);
                                                modelCourseContents.setOwner_id(owner_id);
                                                modelCourseContents.setCreated_at_content(created_at_content);

                                                mContentArrayListNew.add(modelCourseContents);
                                            }
                                            catch (Exception ex){
                                                Log.d("", "onResponse: ");
                                            }

                                            //For parsing Quizes

                                            //JSONArray jObjQuizes = (JSONArray) jSObject3.getJSONArray("multi_ques_list");

                                            // For parsing object "Content" > {0} > {0} > "syllebus" > "0" > "data"

                                            DetailDataModelCourses model5 = new DetailDataModelCourses();

                                            if (content_type.equalsIgnoreCase("quiz")) {
                                                try {

                                                    for (int ctq = 0; ctq < jSObject3.length(); ctq++) {

                                                        JSONArray jSonObjMultiQ2 = (JSONArray) jSObject3.getJSONArray("ques_list");

                                                        mUnitQuizList = new ArrayList<>();

                                                        for(int qlist2=0; qlist2<jSonObjMultiQ2.length(); qlist2++){

                                                            DetailDataModelCoursesDetailContents modelUnitQuizElements2 = new DetailDataModelCoursesDetailContents();

                                                            JSONObject objectAgainAnother2 = (JSONObject) jSonObjMultiQ2.get(qlist2);

                                                            String qTitle = objectAgainAnother2.getString("title");

                                                            qTitle = qTitle.replace("<p>","");
                                                            qTitle = qTitle.replace("</p>","");

                                                            modelUnitQuizElements2.setmQuizTitle(qTitle);

                                                            mUnitQuizList.add(modelUnitQuizElements2);
                                                        }
                                                    }
                                                } catch (Exception ex) {
                                                    Log.d("", "onResponse: ");
                                                }
                                            }

                                            if (quizYesOrNot.equalsIgnoreCase("1")) {

                                                try {

                                                    for (int l = 0; l < jSObject3.length(); l++) {

                                                        JSONArray jSonObjMultiQ = (JSONArray) jSObject3.getJSONArray("multi_ques_list");

                                                        for (int mql = 0; mql < jSonObjMultiQ.length(); mql++) {

                                                            JSONObject objectAgainAnother2 = (JSONObject) jSonObjMultiQ.get(mql);

                                                            String mPulse = objectAgainAnother2.getString("pulse");

                                                            
                                                        }


                                                    }
                                                } catch (Exception ex) {
                                                    Log.d("", "onResponse: ");
                                                }
                                            } else {
                                                //  Toast.makeText(mContext, "", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    }
                                    catch (Exception ex){
                                        Log.d("", "onResponse: ");
                                    }
                                }
                            }
                            catch (Exception ex){
                                Log.d("", "onResponse: ");
                            }

                            detailListMainActivityCourseDetailContentss.add(mContentArrayListNew);

                            detailListMainActivityCourseDetailUnits.add(mUnitArrayListNew);

                            detailListMainActivityCourseDetailUnitQuizList.add(mUnitQuizList);
                        }



                        catch (Exception ex){
                            Log.d("", "onResponse: ");
                        }

                        modelAlterMainActivity.setmArrayListThumbnails(detailListMainActivityCourseThumbnail);
                        modelAlterMainActivity.setmArrayListContentDetails(detailListMainActivityCourseDetailContentss);
                        modelAlterMainActivity.setmArrayListCourseUnits(detailListMainActivityCourseDetailUnits);
                        modelAlterMainActivity.setmArrayListCourseQuizs(detailListMainActivityCourseDetailUnitQuizList);

                        detailListMainActivityCourse3.add(modelAlterMainActivity);


                    }



                }


                catch (Exception ex) {
                    Log.d("", "onResponse: ");
                }


            }
            catch (Exception ex){
                Log.d("", "doInBackground: ");
            }

            return data;
        }

        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);

            setRecyclerView3();

            adapter3=new RecyclerViewAdapterCategory3(detailListMainActivityCourse3,mContext);

            recyclerView3.setAdapter(adapter3);
            adapter3.notifyDataSetChanged();

            mProgressSpinner3.setVisibility(View.GONE);

            map4.put("id", detailListMainActivityCourseCat.get(3).getmCourseCategoryId());
            map4.put("name", detailListMainActivityCourseCat.get(3).getmCourseCategoryNameEn());
            map4.put("rating", "");

            new Category4().execute(urlGetCourses);
        }

        @Override
        protected void onCancelled() {

        }
    }
    public class Category4 extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mProgressSpinner4.setIndeterminate(true);
            mProgressSpinner4.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {

            String data= performPostCall(params[0],map4);

            detailListMainActivityCourse4=new ArrayList<DetailDataModelCourses>();

            //detailList=new ArrayList<DetailDataModelCourses>();

            detailListMainActivityCourseThumbnail=new ArrayList<DetailDataModelCoursesThumbnails>();

            detailListMainActivityCourseDetailContentss = new ArrayList<>();
            detailListMainActivityCourseDetailUnits = new ArrayList<>();
            detailListMainActivityCourseDetailUnitQuizList = new ArrayList<>();

            DetailDataModelCourses model = new DetailDataModelCourses();

            try{

                JSONObject jObjectMain = new JSONObject(data);
                JSONObject jObject;

                JSONArray objectCourse = (JSONArray) jObjectMain.getJSONArray("data");

                try {



                    for (int ec = 0; ec < objectCourse.length(); ec++)
                    {
                        DetailDataModelCourses modelAlterMainActivity = new DetailDataModelCourses();

                        JSONObject jObjEnrolledCourses = objectCourse.getJSONObject(ec);

                        String featured = jObjEnrolledCourses.getString("featured");
                        String Eid = jObjEnrolledCourses.getString("id");
                        String Edetails = jObjEnrolledCourses.getString("details");
                        String Eadmission_status = jObjEnrolledCourses.getString("admission_status");
                        String averageRating = jObjEnrolledCourses.getString("averageRating");
                        String certificate_alias_name = jObjEnrolledCourses.getString("certificate_alias_name");
                        String clone_status = jObjEnrolledCourses.getString("clone_status");
                        String code = jObjEnrolledCourses.getString("code");
                        String courses_for_status = jObjEnrolledCourses.getString("courses_for_status");
                        String course_alias_name = jObjEnrolledCourses.getString("course_alias_name");
                        String course_motto = jObjEnrolledCourses.getString("course_motto");
                        String created_at = jObjEnrolledCourses.getString("created_at");
                        String duration = jObjEnrolledCourses.getString("duration");
                        String end_date = jObjEnrolledCourses.getString("end_date");
                        String enrolment_approval_status = jObjEnrolledCourses.getString("enrolment_approval_status");

                        modelAlterMainActivity.setmCertificateAliasName(certificate_alias_name);
                        modelAlterMainActivity.setmAdmissionStatus(Eadmission_status);
                        modelAlterMainActivity.setmAverageRating(averageRating);
                        modelAlterMainActivity.setmCloneStatus(clone_status);
                        modelAlterMainActivity.setmCode(code);
                        modelAlterMainActivity.setmCreatedAt(created_at);
                        modelAlterMainActivity.setmDuration(duration);
                        modelAlterMainActivity.setmEndDate(end_date);
                        modelAlterMainActivity.setmId(Eid);
                        modelAlterMainActivity.setmDetails(Edetails);
                        modelAlterMainActivity.setmFeatured(featured);
                        modelAlterMainActivity.setmEnrolmentApprovalStatus(enrolment_approval_status);
                        modelAlterMainActivity.setmCursesForStatus(courses_for_status);
                        modelAlterMainActivity.setmCourseAliasName(course_alias_name);
                        modelAlterMainActivity.setmCourseMotto(course_motto);
                        //modelForMainActivityCourse.setmStatus(status);

                        // detailListMainActivityCourse.add(modelAlterMainActivity);

                        try
                        {
                            jObject = jObjEnrolledCourses.getJSONObject("syllabus");

                            JSONArray objectEnrollCourseUnits = (JSONArray) jObject.getJSONArray("units");

                            mUnitArrayListNew = new ArrayList<>();

                            for (int ecu=0; ecu<objectEnrollCourseUnits.length()-1; ecu++)
                            {
                                DetailDataModelCoursesDetailContents modelUnitElements = new DetailDataModelCoursesDetailContents();

                                JSONObject objectUnitElements = (JSONObject) objectEnrollCourseUnits.get(ecu);

                                String orderEcu = objectUnitElements.getString("order");
                                String nameEcu = objectUnitElements.getString("name");

                                modelUnitElements.setUnitOrders(orderEcu);
                                modelUnitElements.setUnitNames(nameEcu);

                                mUnitArrayListNew.add(modelUnitElements);
                            }

                            //for parsing syllebus strings

                            DetailDataModelCourses model7 = new DetailDataModelCourses();

                            String study_mode_Syllebus = jObject.getString("study_mode");

                            model7.setStudyModeSyllebus(study_mode_Syllebus);

                            JSONObject jObjectCourse = jObjEnrolledCourses.getJSONObject("course");

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

                            detailListMainActivityCourseThumbnail.add(modelCourseThumbnail);

                            //model.setmArrayListThumbnails(detailListMainActivityCourseThumbnail);

                            //for parsing Updated by strings
                            JSONObject jObjectUpdatedBy = jObjEnrolledCourses.getJSONObject("UpdatedBy");


                            DetailDataModelCourses model9 = new DetailDataModelCourses();

                            String education_statusUpdatedBy = jObjectUpdatedBy.getString("education_status");
                            String emailUpdatedBy = jObjectUpdatedBy.getString("email");
                            String idUpdatedBy = jObjectUpdatedBy.getString("id");
                            String nameUpdatedBy = jObjectUpdatedBy.getString("name");
                            String phoneUpdatedBy = jObjectUpdatedBy.getString("phone");
                            String UserInfoUpdatedBy = jObjectUpdatedBy.getString("UserInfo");
                            String usernameUpdatedBy = jObjectUpdatedBy.getString("username");

                            model9.setEducation_statusUpdatedBy(education_statusUpdatedBy);
                            model9.setEmailUpdatedBy(emailUpdatedBy);
                            model9.setIdUpdatedBy(idUpdatedBy);
                            model9.setNameUpdatedBy(nameUpdatedBy);
                            model9.setPhoneUpdatedBy(phoneUpdatedBy);
                            model9.setUserInfoUpdatedBy(UserInfoUpdatedBy);
                            model9.setUsernameUpdatedBy(usernameUpdatedBy);

                            //for parsing owner strings
                            JSONObject jObjectOwner = jObjEnrolledCourses.getJSONObject("owner");

                            DetailDataModelCourses model10 = new DetailDataModelCourses();

                            String updated_at_owner = jObjectOwner.getString("updated_at");
                            String institution_name_owner = jObjectOwner.getString("institution_name");
                            String id_owner = jObjectOwner.getString("id");
                            String created_at_owner = jObjectOwner.getString("created_at");

                            model10.setUpdated_at_owner(updated_at_owner);
                            model10.setInstitution_name_owner(institution_name_owner);
                            model10.setId_owner(id_owner);
                            model10.setCreated_at_owner(created_at_owner);

                            //for parsing created by strings
                            JSONObject jObjectCreatedBy = jObjEnrolledCourses.getJSONObject("CreatedBy");


                            DetailDataModelCourses model11 = new DetailDataModelCourses();

                            String education_statusCreatedBy = jObjectCreatedBy.getString("education_status");
                            String emailCreatedBy = jObjectCreatedBy.getString("email");
                            String idCreatedBy = jObjectCreatedBy.getString("id");
                            String nameCreatedBy = jObjectCreatedBy.getString("name");
                            String phoneCreatedBy = jObjectCreatedBy.getString("phone");
                            String UserInfoCreatedBy = jObjectCreatedBy.getString("UserInfo");
                            String usernameCreatedBy = jObjectCreatedBy.getString("username");

                            model11.setEducation_statusUpdatedBy(education_statusCreatedBy);
                            model11.setEmailUpdatedBy(emailCreatedBy);
                            model11.setIdCreatedBy(idCreatedBy);
                            model11.setNameCreatedBy(nameCreatedBy);
                            model11.setPhoneCreatedBy(phoneCreatedBy);
                            model11.setUserInfoCreatedBy(UserInfoCreatedBy);
                            model11.setUsernameCreatedBy(usernameCreatedBy);

                            // parsing from syllebus

                            try{
                                mContentArrayListNew = new ArrayList<>();


                                for (int ii = 0; ii < jObject.length()-2; ii++)
                                {
                                    JSONObject jSObject2 = jObject.getJSONObject("" + ii);

                                    //for parsing lessons > {0} > "syllebus" > "0" > "data"

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
                                            //model2.setmTitleAnother(mTitle);//*

                                            // For parsing "file_type" > "data" > {0} > {0} > "syllebus" > "0" > "data"

                                            JSONObject jObjAgain2 = jObjAgain.getJSONObject("file_type");

                                            DetailDataModelCourses modelFileType = new DetailDataModelCourses();

                                            String pdf = jObjAgain2.getString("pdf");
                                            String excel = jObjAgain2.getString("excel");
                                            String doc = jObjAgain2.getString("doc");
                                            String csv = jObjAgain2.getString("csv");

                                            modelFileType.setmPdf(pdf);
                                            modelFileType.setmXcel(excel);
                                            modelFileType.setmDoc(doc);
                                            modelFileType.setmCsv(csv);

                                            try{
                                                DetailDataModelCoursesDetailContents modelCourseContents = new DetailDataModelCoursesDetailContents();

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
                                                modelCourseContents.setDescription_content(description_content);
                                                modelCourseContents.setFile_encode_path(file_encode_path);
                                                modelCourseContents.setFile_name(file_name);
                                                modelCourseContents.setId_content(id_content);
                                                modelCourseContents.setLicense(license);
                                                modelCourseContents.setOwner_id(owner_id);
                                                modelCourseContents.setCreated_at_content(created_at_content);

                                                mContentArrayListNew.add(modelCourseContents);
                                            }
                                            catch (Exception ex){
                                                Log.d("", "onResponse: ");
                                            }

                                            //For parsing Quizes

                                            //JSONArray jObjQuizes = (JSONArray) jSObject3.getJSONArray("multi_ques_list");

                                            // For parsing object "Content" > {0} > {0} > "syllebus" > "0" > "data"

                                            DetailDataModelCourses model5 = new DetailDataModelCourses();

                                            if (content_type.equalsIgnoreCase("quiz")) {
                                                try {

                                                    for (int ctq = 0; ctq < jSObject3.length(); ctq++) {

                                                        JSONArray jSonObjMultiQ2 = (JSONArray) jSObject3.getJSONArray("ques_list");

                                                        mUnitQuizList = new ArrayList<>();

                                                        for(int qlist2=0; qlist2<jSonObjMultiQ2.length(); qlist2++){

                                                            DetailDataModelCoursesDetailContents modelUnitQuizElements2 = new DetailDataModelCoursesDetailContents();

                                                            JSONObject objectAgainAnother2 = (JSONObject) jSonObjMultiQ2.get(qlist2);

                                                            String qTitle = objectAgainAnother2.getString("title");

                                                            qTitle = qTitle.replace("<p>","");
                                                            qTitle = qTitle.replace("</p>","");

                                                            modelUnitQuizElements2.setmQuizTitle(qTitle);

                                                            mUnitQuizList.add(modelUnitQuizElements2);
                                                        }
                                                    }
                                                } catch (Exception ex) {
                                                    Log.d("", "onResponse: ");
                                                }
                                            }

                                            if (quizYesOrNot.equalsIgnoreCase("1")) {

                                                try {

                                                    for (int l = 0; l < jSObject3.length(); l++) {

                                                        JSONArray jSonObjMultiQ = (JSONArray) jSObject3.getJSONArray("multi_ques_list");

                                                        for (int mql = 0; mql < jSonObjMultiQ.length(); mql++) {

                                                            JSONObject objectAgainAnother2 = (JSONObject) jSonObjMultiQ.get(mql);

                                                            String mPulse = objectAgainAnother2.getString("pulse");

                                                            
                                                        }


                                                    }
                                                } catch (Exception ex) {
                                                    Log.d("", "onResponse: ");
                                                }
                                            } else {
                                                //  Toast.makeText(mContext, "", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    }
                                    catch (Exception ex){
                                        Log.d("", "onResponse: ");
                                    }
                                }
                            }
                            catch (Exception ex){
                                Log.d("", "onResponse: ");
                            }

                            detailListMainActivityCourseDetailContentss.add(mContentArrayListNew);

                            detailListMainActivityCourseDetailUnits.add(mUnitArrayListNew);

                            detailListMainActivityCourseDetailUnitQuizList.add(mUnitQuizList);
                        }



                        catch (Exception ex){
                            Log.d("", "onResponse: ");
                        }

                        modelAlterMainActivity.setmArrayListThumbnails(detailListMainActivityCourseThumbnail);
                        modelAlterMainActivity.setmArrayListContentDetails(detailListMainActivityCourseDetailContentss);
                        modelAlterMainActivity.setmArrayListCourseUnits(detailListMainActivityCourseDetailUnits);
                        modelAlterMainActivity.setmArrayListCourseQuizs(detailListMainActivityCourseDetailUnitQuizList);

                        detailListMainActivityCourse4.add(modelAlterMainActivity);


                    }



                }


                catch (Exception ex) {
                    Log.d("", "onResponse: ");
                }


            }
            catch (Exception ex){
                Log.d("", "doInBackground: ");
            }

            return data;
        }

        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);

            setRecyclerView4();

            adapter4=new RecyclerViewAdapterCategory1(detailListMainActivityCourse4,mContext);

            recyclerView4.setAdapter(adapter4);
            adapter4.notifyDataSetChanged();

            mProgressSpinner4.setVisibility(View.GONE);

            map5.put("id", detailListMainActivityCourseCat.get(4).getmCourseCategoryId());
            map5.put("name", detailListMainActivityCourseCat.get(4).getmCourseCategoryNameEn());
            map5.put("rating", "");

            new Category5().execute(urlGetCourses);
        }

        @Override
        protected void onCancelled() {

        }
    }
    public class Category5 extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mProgressSpinner5.setIndeterminate(true);
            mProgressSpinner5.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {

            String data= performPostCall(params[0],map5);

            detailListMainActivityCourse5=new ArrayList<DetailDataModelCourses>();

            //detailList=new ArrayList<DetailDataModelCourses>();

            detailListMainActivityCourseThumbnail=new ArrayList<DetailDataModelCoursesThumbnails>();

            detailListMainActivityCourseDetailContentss = new ArrayList<>();
            detailListMainActivityCourseDetailUnits = new ArrayList<>();
            detailListMainActivityCourseDetailUnitQuizList = new ArrayList<>();

            DetailDataModelCourses model = new DetailDataModelCourses();

            try{

                JSONObject jObjectMain = new JSONObject(data);
                JSONObject jObject;

                JSONArray objectCourse = (JSONArray) jObjectMain.getJSONArray("data");

                try {



                    for (int ec = 0; ec < objectCourse.length(); ec++)
                    {
                        DetailDataModelCourses modelAlterMainActivity = new DetailDataModelCourses();

                        JSONObject jObjEnrolledCourses = objectCourse.getJSONObject(ec);

                        String featured = jObjEnrolledCourses.getString("featured");
                        String Eid = jObjEnrolledCourses.getString("id");
                        String Edetails = jObjEnrolledCourses.getString("details");
                        String Eadmission_status = jObjEnrolledCourses.getString("admission_status");
                        String averageRating = jObjEnrolledCourses.getString("averageRating");
                        String certificate_alias_name = jObjEnrolledCourses.getString("certificate_alias_name");
                        String clone_status = jObjEnrolledCourses.getString("clone_status");
                        String code = jObjEnrolledCourses.getString("code");
                        String courses_for_status = jObjEnrolledCourses.getString("courses_for_status");
                        String course_alias_name = jObjEnrolledCourses.getString("course_alias_name");
                        String course_motto = jObjEnrolledCourses.getString("course_motto");
                        String created_at = jObjEnrolledCourses.getString("created_at");
                        String duration = jObjEnrolledCourses.getString("duration");
                        String end_date = jObjEnrolledCourses.getString("end_date");
                        String enrolment_approval_status = jObjEnrolledCourses.getString("enrolment_approval_status");

                        modelAlterMainActivity.setmCertificateAliasName(certificate_alias_name);
                        modelAlterMainActivity.setmAdmissionStatus(Eadmission_status);
                        modelAlterMainActivity.setmAverageRating(averageRating);
                        modelAlterMainActivity.setmCloneStatus(clone_status);
                        modelAlterMainActivity.setmCode(code);
                        modelAlterMainActivity.setmCreatedAt(created_at);
                        modelAlterMainActivity.setmDuration(duration);
                        modelAlterMainActivity.setmEndDate(end_date);
                        modelAlterMainActivity.setmId(Eid);
                        modelAlterMainActivity.setmDetails(Edetails);
                        modelAlterMainActivity.setmFeatured(featured);
                        modelAlterMainActivity.setmEnrolmentApprovalStatus(enrolment_approval_status);
                        modelAlterMainActivity.setmCursesForStatus(courses_for_status);
                        modelAlterMainActivity.setmCourseAliasName(course_alias_name);
                        modelAlterMainActivity.setmCourseMotto(course_motto);
                        //modelForMainActivityCourse.setmStatus(status);

                        // detailListMainActivityCourse.add(modelAlterMainActivity);

                        try
                        {
                            jObject = jObjEnrolledCourses.getJSONObject("syllabus");

                            JSONArray objectEnrollCourseUnits = (JSONArray) jObject.getJSONArray("units");

                            mUnitArrayListNew = new ArrayList<>();

                            for (int ecu=0; ecu<objectEnrollCourseUnits.length()-1; ecu++)
                            {
                                DetailDataModelCoursesDetailContents modelUnitElements = new DetailDataModelCoursesDetailContents();

                                JSONObject objectUnitElements = (JSONObject) objectEnrollCourseUnits.get(ecu);

                                String orderEcu = objectUnitElements.getString("order");
                                String nameEcu = objectUnitElements.getString("name");

                                modelUnitElements.setUnitOrders(orderEcu);
                                modelUnitElements.setUnitNames(nameEcu);

                                mUnitArrayListNew.add(modelUnitElements);
                            }

                            //for parsing syllebus strings

                            DetailDataModelCourses model7 = new DetailDataModelCourses();

                            String study_mode_Syllebus = jObject.getString("study_mode");

                            model7.setStudyModeSyllebus(study_mode_Syllebus);

                            JSONObject jObjectCourse = jObjEnrolledCourses.getJSONObject("course");

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

                            detailListMainActivityCourseThumbnail.add(modelCourseThumbnail);

                            //model.setmArrayListThumbnails(detailListMainActivityCourseThumbnail);

                            //for parsing Updated by strings
                            JSONObject jObjectUpdatedBy = jObjEnrolledCourses.getJSONObject("UpdatedBy");


                            DetailDataModelCourses model9 = new DetailDataModelCourses();

                            String education_statusUpdatedBy = jObjectUpdatedBy.getString("education_status");
                            String emailUpdatedBy = jObjectUpdatedBy.getString("email");
                            String idUpdatedBy = jObjectUpdatedBy.getString("id");
                            String nameUpdatedBy = jObjectUpdatedBy.getString("name");
                            String phoneUpdatedBy = jObjectUpdatedBy.getString("phone");
                            String UserInfoUpdatedBy = jObjectUpdatedBy.getString("UserInfo");
                            String usernameUpdatedBy = jObjectUpdatedBy.getString("username");

                            model9.setEducation_statusUpdatedBy(education_statusUpdatedBy);
                            model9.setEmailUpdatedBy(emailUpdatedBy);
                            model9.setIdUpdatedBy(idUpdatedBy);
                            model9.setNameUpdatedBy(nameUpdatedBy);
                            model9.setPhoneUpdatedBy(phoneUpdatedBy);
                            model9.setUserInfoUpdatedBy(UserInfoUpdatedBy);
                            model9.setUsernameUpdatedBy(usernameUpdatedBy);

                            //for parsing owner strings
                            JSONObject jObjectOwner = jObjEnrolledCourses.getJSONObject("owner");

                            DetailDataModelCourses model10 = new DetailDataModelCourses();

                            String updated_at_owner = jObjectOwner.getString("updated_at");
                            String institution_name_owner = jObjectOwner.getString("institution_name");
                            String id_owner = jObjectOwner.getString("id");
                            String created_at_owner = jObjectOwner.getString("created_at");

                            model10.setUpdated_at_owner(updated_at_owner);
                            model10.setInstitution_name_owner(institution_name_owner);
                            model10.setId_owner(id_owner);
                            model10.setCreated_at_owner(created_at_owner);

                            //for parsing created by strings
                            JSONObject jObjectCreatedBy = jObjEnrolledCourses.getJSONObject("CreatedBy");


                            DetailDataModelCourses model11 = new DetailDataModelCourses();

                            String education_statusCreatedBy = jObjectCreatedBy.getString("education_status");
                            String emailCreatedBy = jObjectCreatedBy.getString("email");
                            String idCreatedBy = jObjectCreatedBy.getString("id");
                            String nameCreatedBy = jObjectCreatedBy.getString("name");
                            String phoneCreatedBy = jObjectCreatedBy.getString("phone");
                            String UserInfoCreatedBy = jObjectCreatedBy.getString("UserInfo");
                            String usernameCreatedBy = jObjectCreatedBy.getString("username");

                            model11.setEducation_statusUpdatedBy(education_statusCreatedBy);
                            model11.setEmailUpdatedBy(emailCreatedBy);
                            model11.setIdCreatedBy(idCreatedBy);
                            model11.setNameCreatedBy(nameCreatedBy);
                            model11.setPhoneCreatedBy(phoneCreatedBy);
                            model11.setUserInfoCreatedBy(UserInfoCreatedBy);
                            model11.setUsernameCreatedBy(usernameCreatedBy);

                            // parsing from syllebus

                            try{
                                mContentArrayListNew = new ArrayList<>();


                                for (int ii = 0; ii < jObject.length()-2; ii++)
                                {
                                    JSONObject jSObject2 = jObject.getJSONObject("" + ii);

                                    //for parsing lessons > {0} > "syllebus" > "0" > "data"

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
                                            //model2.setmTitleAnother(mTitle);//*

                                            // For parsing "file_type" > "data" > {0} > {0} > "syllebus" > "0" > "data"

                                            JSONObject jObjAgain2 = jObjAgain.getJSONObject("file_type");

                                            DetailDataModelCourses modelFileType = new DetailDataModelCourses();

                                            String pdf = jObjAgain2.getString("pdf");
                                            String excel = jObjAgain2.getString("excel");
                                            String doc = jObjAgain2.getString("doc");
                                            String csv = jObjAgain2.getString("csv");

                                            modelFileType.setmPdf(pdf);
                                            modelFileType.setmXcel(excel);
                                            modelFileType.setmDoc(doc);
                                            modelFileType.setmCsv(csv);

                                            try{
                                                DetailDataModelCoursesDetailContents modelCourseContents = new DetailDataModelCoursesDetailContents();

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
                                                modelCourseContents.setDescription_content(description_content);
                                                modelCourseContents.setFile_encode_path(file_encode_path);
                                                modelCourseContents.setFile_name(file_name);
                                                modelCourseContents.setId_content(id_content);
                                                modelCourseContents.setLicense(license);
                                                modelCourseContents.setOwner_id(owner_id);
                                                modelCourseContents.setCreated_at_content(created_at_content);

                                                mContentArrayListNew.add(modelCourseContents);
                                            }
                                            catch (Exception ex){
                                                Log.d("", "onResponse: ");
                                            }

                                            //For parsing Quizes

                                            //JSONArray jObjQuizes = (JSONArray) jSObject3.getJSONArray("multi_ques_list");

                                            // For parsing object "Content" > {0} > {0} > "syllebus" > "0" > "data"

                                            DetailDataModelCourses model5 = new DetailDataModelCourses();

                                            if (content_type.equalsIgnoreCase("quiz")) {
                                                try {

                                                    for (int ctq = 0; ctq < jSObject3.length(); ctq++) {

                                                        JSONArray jSonObjMultiQ2 = (JSONArray) jSObject3.getJSONArray("ques_list");

                                                        mUnitQuizList = new ArrayList<>();

                                                        for(int qlist2=0; qlist2<jSonObjMultiQ2.length(); qlist2++){

                                                            DetailDataModelCoursesDetailContents modelUnitQuizElements2 = new DetailDataModelCoursesDetailContents();

                                                            JSONObject objectAgainAnother2 = (JSONObject) jSonObjMultiQ2.get(qlist2);

                                                            String qTitle = objectAgainAnother2.getString("title");

                                                            qTitle = qTitle.replace("<p>","");
                                                            qTitle = qTitle.replace("</p>","");

                                                            modelUnitQuizElements2.setmQuizTitle(qTitle);

                                                            mUnitQuizList.add(modelUnitQuizElements2);
                                                        }
                                                    }
                                                } catch (Exception ex) {
                                                    Log.d("", "onResponse: ");
                                                }
                                            }

                                            if (quizYesOrNot.equalsIgnoreCase("1")) {

                                                try {

                                                    for (int l = 0; l < jSObject3.length(); l++) {

                                                        JSONArray jSonObjMultiQ = (JSONArray) jSObject3.getJSONArray("multi_ques_list");

                                                        for (int mql = 0; mql < jSonObjMultiQ.length(); mql++) {

                                                            JSONObject objectAgainAnother2 = (JSONObject) jSonObjMultiQ.get(mql);

                                                            String mPulse = objectAgainAnother2.getString("pulse");

                                                            
                                                        }


                                                    }
                                                } catch (Exception ex) {
                                                    Log.d("", "onResponse: ");
                                                }
                                            } else {
                                                //  Toast.makeText(mContext, "", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    }
                                    catch (Exception ex){
                                        Log.d("", "onResponse: ");
                                    }
                                }
                            }
                            catch (Exception ex){
                                Log.d("", "onResponse: ");
                            }

                            detailListMainActivityCourseDetailContentss.add(mContentArrayListNew);

                            detailListMainActivityCourseDetailUnits.add(mUnitArrayListNew);

                            detailListMainActivityCourseDetailUnitQuizList.add(mUnitQuizList);
                        }



                        catch (Exception ex){
                            Log.d("", "onResponse: ");
                        }

                        modelAlterMainActivity.setmArrayListThumbnails(detailListMainActivityCourseThumbnail);
                        modelAlterMainActivity.setmArrayListContentDetails(detailListMainActivityCourseDetailContentss);
                        modelAlterMainActivity.setmArrayListCourseUnits(detailListMainActivityCourseDetailUnits);
                        modelAlterMainActivity.setmArrayListCourseQuizs(detailListMainActivityCourseDetailUnitQuizList);

                        detailListMainActivityCourse5.add(modelAlterMainActivity);


                    }



                }


                catch (Exception ex) {
                    Log.d("", "onResponse: ");
                }


            }
            catch (Exception ex){
                Log.d("", "doInBackground: ");
            }

            return data;
        }

        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);

            setRecyclerView5();

            adapter5=new RecyclerViewAdapterCategory1(detailListMainActivityCourse5,mContext);

            recyclerView5.setAdapter(adapter5);
            adapter5.notifyDataSetChanged();

            mProgressSpinner5.setVisibility(View.GONE);

            try {
                map6.put("id", detailListMainActivityCourseCat.get(5).getmCourseCategoryId());
                map6.put("name", detailListMainActivityCourseCat.get(5).getmCourseCategoryNameEn());
                map6.put("rating", "");
            }
            catch (Exception ex){
                Log.d("", "onPostExecute: ");
            }

            new Category6().execute(urlGetCourses);
        }

        @Override
        protected void onCancelled() {

        }
    }

    public class Category6 extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mProgressSpinner6.setIndeterminate(true);
            mProgressSpinner6.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {

            String data= performPostCall(params[0],map6);

            detailListMainActivityCourse6=new ArrayList<DetailDataModelCourses>();

            //detailList=new ArrayList<DetailDataModelCourses>();

            detailListMainActivityCourseThumbnail=new ArrayList<DetailDataModelCoursesThumbnails>();

            detailListMainActivityCourseDetailContentss = new ArrayList<>();
            detailListMainActivityCourseDetailUnits = new ArrayList<>();
            detailListMainActivityCourseDetailUnitQuizList = new ArrayList<>();

            DetailDataModelCourses model = new DetailDataModelCourses();

            try{

                JSONObject jObjectMain = new JSONObject(data);
                JSONObject jObject;

                JSONArray objectCourse = (JSONArray) jObjectMain.getJSONArray("data");

                try {



                    for (int ec = 0; ec < objectCourse.length(); ec++)
                    {
                        DetailDataModelCourses modelAlterMainActivity = new DetailDataModelCourses();

                        JSONObject jObjEnrolledCourses = objectCourse.getJSONObject(ec);

                        String featured = jObjEnrolledCourses.getString("featured");
                        String Eid = jObjEnrolledCourses.getString("id");
                        String Edetails = jObjEnrolledCourses.getString("details");
                        String Eadmission_status = jObjEnrolledCourses.getString("admission_status");
                        String averageRating = jObjEnrolledCourses.getString("averageRating");
                        String certificate_alias_name = jObjEnrolledCourses.getString("certificate_alias_name");
                        String clone_status = jObjEnrolledCourses.getString("clone_status");
                        String code = jObjEnrolledCourses.getString("code");
                        String courses_for_status = jObjEnrolledCourses.getString("courses_for_status");
                        String course_alias_name = jObjEnrolledCourses.getString("course_alias_name");
                        String course_motto = jObjEnrolledCourses.getString("course_motto");
                        String created_at = jObjEnrolledCourses.getString("created_at");
                        String duration = jObjEnrolledCourses.getString("duration");
                        String end_date = jObjEnrolledCourses.getString("end_date");
                        String enrolment_approval_status = jObjEnrolledCourses.getString("enrolment_approval_status");

                        modelAlterMainActivity.setmCertificateAliasName(certificate_alias_name);
                        modelAlterMainActivity.setmAdmissionStatus(Eadmission_status);
                        modelAlterMainActivity.setmAverageRating(averageRating);
                        modelAlterMainActivity.setmCloneStatus(clone_status);
                        modelAlterMainActivity.setmCode(code);
                        modelAlterMainActivity.setmCreatedAt(created_at);
                        modelAlterMainActivity.setmDuration(duration);
                        modelAlterMainActivity.setmEndDate(end_date);
                        modelAlterMainActivity.setmId(Eid);
                        modelAlterMainActivity.setmDetails(Edetails);
                        modelAlterMainActivity.setmFeatured(featured);
                        modelAlterMainActivity.setmEnrolmentApprovalStatus(enrolment_approval_status);
                        modelAlterMainActivity.setmCursesForStatus(courses_for_status);
                        modelAlterMainActivity.setmCourseAliasName(course_alias_name);
                        modelAlterMainActivity.setmCourseMotto(course_motto);
                        //modelForMainActivityCourse.setmStatus(status);

                        // detailListMainActivityCourse.add(modelAlterMainActivity);

                        try
                        {
                            jObject = jObjEnrolledCourses.getJSONObject("syllabus");

                            JSONArray objectEnrollCourseUnits = (JSONArray) jObject.getJSONArray("units");

                            mUnitArrayListNew = new ArrayList<>();

                            for (int ecu=0; ecu<objectEnrollCourseUnits.length()-1; ecu++)
                            {
                                DetailDataModelCoursesDetailContents modelUnitElements = new DetailDataModelCoursesDetailContents();

                                JSONObject objectUnitElements = (JSONObject) objectEnrollCourseUnits.get(ecu);

                                String orderEcu = objectUnitElements.getString("order");
                                String nameEcu = objectUnitElements.getString("name");

                                modelUnitElements.setUnitOrders(orderEcu);
                                modelUnitElements.setUnitNames(nameEcu);

                                mUnitArrayListNew.add(modelUnitElements);
                            }

                            //for parsing syllebus strings

                            DetailDataModelCourses model7 = new DetailDataModelCourses();

                            String study_mode_Syllebus = jObject.getString("study_mode");

                            model7.setStudyModeSyllebus(study_mode_Syllebus);

                            JSONObject jObjectCourse = jObjEnrolledCourses.getJSONObject("course");

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

                            detailListMainActivityCourseThumbnail.add(modelCourseThumbnail);

                            //model.setmArrayListThumbnails(detailListMainActivityCourseThumbnail);

                            //for parsing Updated by strings
                            JSONObject jObjectUpdatedBy = jObjEnrolledCourses.getJSONObject("UpdatedBy");


                            DetailDataModelCourses model9 = new DetailDataModelCourses();

                            String education_statusUpdatedBy = jObjectUpdatedBy.getString("education_status");
                            String emailUpdatedBy = jObjectUpdatedBy.getString("email");
                            String idUpdatedBy = jObjectUpdatedBy.getString("id");
                            String nameUpdatedBy = jObjectUpdatedBy.getString("name");
                            String phoneUpdatedBy = jObjectUpdatedBy.getString("phone");
                            String UserInfoUpdatedBy = jObjectUpdatedBy.getString("UserInfo");
                            String usernameUpdatedBy = jObjectUpdatedBy.getString("username");

                            model9.setEducation_statusUpdatedBy(education_statusUpdatedBy);
                            model9.setEmailUpdatedBy(emailUpdatedBy);
                            model9.setIdUpdatedBy(idUpdatedBy);
                            model9.setNameUpdatedBy(nameUpdatedBy);
                            model9.setPhoneUpdatedBy(phoneUpdatedBy);
                            model9.setUserInfoUpdatedBy(UserInfoUpdatedBy);
                            model9.setUsernameUpdatedBy(usernameUpdatedBy);

                            //for parsing owner strings
                            JSONObject jObjectOwner = jObjEnrolledCourses.getJSONObject("owner");

                            DetailDataModelCourses model10 = new DetailDataModelCourses();

                            String updated_at_owner = jObjectOwner.getString("updated_at");
                            String institution_name_owner = jObjectOwner.getString("institution_name");
                            String id_owner = jObjectOwner.getString("id");
                            String created_at_owner = jObjectOwner.getString("created_at");

                            model10.setUpdated_at_owner(updated_at_owner);
                            model10.setInstitution_name_owner(institution_name_owner);
                            model10.setId_owner(id_owner);
                            model10.setCreated_at_owner(created_at_owner);

                            //for parsing created by strings
                            JSONObject jObjectCreatedBy = jObjEnrolledCourses.getJSONObject("CreatedBy");


                            DetailDataModelCourses model11 = new DetailDataModelCourses();

                            String education_statusCreatedBy = jObjectCreatedBy.getString("education_status");
                            String emailCreatedBy = jObjectCreatedBy.getString("email");
                            String idCreatedBy = jObjectCreatedBy.getString("id");
                            String nameCreatedBy = jObjectCreatedBy.getString("name");
                            String phoneCreatedBy = jObjectCreatedBy.getString("phone");
                            String UserInfoCreatedBy = jObjectCreatedBy.getString("UserInfo");
                            String usernameCreatedBy = jObjectCreatedBy.getString("username");

                            model11.setEducation_statusUpdatedBy(education_statusCreatedBy);
                            model11.setEmailUpdatedBy(emailCreatedBy);
                            model11.setIdCreatedBy(idCreatedBy);
                            model11.setNameCreatedBy(nameCreatedBy);
                            model11.setPhoneCreatedBy(phoneCreatedBy);
                            model11.setUserInfoCreatedBy(UserInfoCreatedBy);
                            model11.setUsernameCreatedBy(usernameCreatedBy);

                            // parsing from syllebus

                            try{
                                mContentArrayListNew = new ArrayList<>();


                                for (int ii = 0; ii < jObject.length()-2; ii++)
                                {
                                    JSONObject jSObject2 = jObject.getJSONObject("" + ii);

                                    //for parsing lessons > {0} > "syllebus" > "0" > "data"

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
                                            //model2.setmTitleAnother(mTitle);//*

                                            // For parsing "file_type" > "data" > {0} > {0} > "syllebus" > "0" > "data"

                                            JSONObject jObjAgain2 = jObjAgain.getJSONObject("file_type");

                                            DetailDataModelCourses modelFileType = new DetailDataModelCourses();

                                            String pdf = jObjAgain2.getString("pdf");
                                            String excel = jObjAgain2.getString("excel");
                                            String doc = jObjAgain2.getString("doc");
                                            String csv = jObjAgain2.getString("csv");

                                            modelFileType.setmPdf(pdf);
                                            modelFileType.setmXcel(excel);
                                            modelFileType.setmDoc(doc);
                                            modelFileType.setmCsv(csv);

                                            try{
                                                DetailDataModelCoursesDetailContents modelCourseContents = new DetailDataModelCoursesDetailContents();

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
                                                modelCourseContents.setDescription_content(description_content);
                                                modelCourseContents.setFile_encode_path(file_encode_path);
                                                modelCourseContents.setFile_name(file_name);
                                                modelCourseContents.setId_content(id_content);
                                                modelCourseContents.setLicense(license);
                                                modelCourseContents.setOwner_id(owner_id);
                                                modelCourseContents.setCreated_at_content(created_at_content);

                                                mContentArrayListNew.add(modelCourseContents);
                                            }
                                            catch (Exception ex){
                                                Log.d("", "onResponse: ");
                                            }

                                            //For parsing Quizes

                                            //JSONArray jObjQuizes = (JSONArray) jSObject3.getJSONArray("multi_ques_list");

                                            // For parsing object "Content" > {0} > {0} > "syllebus" > "0" > "data"

                                            DetailDataModelCourses model5 = new DetailDataModelCourses();

                                            if (content_type.equalsIgnoreCase("quiz")) {
                                                try {

                                                    for (int ctq = 0; ctq < jSObject3.length(); ctq++) {

                                                        JSONArray jSonObjMultiQ2 = (JSONArray) jSObject3.getJSONArray("ques_list");

                                                        mUnitQuizList = new ArrayList<>();

                                                        for(int qlist2=0; qlist2<jSonObjMultiQ2.length(); qlist2++){

                                                            DetailDataModelCoursesDetailContents modelUnitQuizElements2 = new DetailDataModelCoursesDetailContents();

                                                            JSONObject objectAgainAnother2 = (JSONObject) jSonObjMultiQ2.get(qlist2);

                                                            String qTitle = objectAgainAnother2.getString("title");

                                                            qTitle = qTitle.replace("<p>","");
                                                            qTitle = qTitle.replace("</p>","");

                                                            modelUnitQuizElements2.setmQuizTitle(qTitle);

                                                            mUnitQuizList.add(modelUnitQuizElements2);
                                                        }
                                                    }
                                                } catch (Exception ex) {
                                                    Log.d("", "onResponse: ");
                                                }
                                            }

                                            if (quizYesOrNot.equalsIgnoreCase("1")) {

                                                try {

                                                    for (int l = 0; l < jSObject3.length(); l++) {

                                                        JSONArray jSonObjMultiQ = (JSONArray) jSObject3.getJSONArray("multi_ques_list");

                                                        for (int mql = 0; mql < jSonObjMultiQ.length(); mql++) {

                                                            JSONObject objectAgainAnother2 = (JSONObject) jSonObjMultiQ.get(mql);

                                                            String mPulse = objectAgainAnother2.getString("pulse");

                                                            
                                                        }


                                                    }
                                                } catch (Exception ex) {
                                                    Log.d("", "onResponse: ");
                                                }
                                            } else {
                                                //  Toast.makeText(mContext, "", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    }
                                    catch (Exception ex){
                                        Log.d("", "onResponse: ");
                                    }
                                }
                            }
                            catch (Exception ex){
                                Log.d("", "onResponse: ");
                            }

                            detailListMainActivityCourseDetailContentss.add(mContentArrayListNew);

                            detailListMainActivityCourseDetailUnits.add(mUnitArrayListNew);

                            detailListMainActivityCourseDetailUnitQuizList.add(mUnitQuizList);
                        }



                        catch (Exception ex){
                            Log.d("", "onResponse: ");
                        }

                        modelAlterMainActivity.setmArrayListThumbnails(detailListMainActivityCourseThumbnail);
                        modelAlterMainActivity.setmArrayListContentDetails(detailListMainActivityCourseDetailContentss);
                        modelAlterMainActivity.setmArrayListCourseUnits(detailListMainActivityCourseDetailUnits);
                        modelAlterMainActivity.setmArrayListCourseQuizs(detailListMainActivityCourseDetailUnitQuizList);

                        detailListMainActivityCourse6.add(modelAlterMainActivity);


                    }



                }


                catch (Exception ex) {
                    Log.d("", "onResponse: ");
                }


            }
            catch (Exception ex){
                Log.d("", "doInBackground: ");
            }

            return data;
        }

        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);

            setRecyclerView6();

            adapter6=new RecyclerViewAdapterCategory1(detailListMainActivityCourse6,mContext);

            recyclerView6.setAdapter(adapter6);
            adapter6.notifyDataSetChanged();

            mProgressSpinner6.setVisibility(View.GONE);

            try {
                map7.put("id", detailListMainActivityCourseCat.get(6).getmCourseCategoryId());
                map7.put("name", detailListMainActivityCourseCat.get(6).getmCourseCategoryNameEn());
                map7.put("rating", "");

                new Category7().execute(urlGetCourses);
            }
            catch (Exception ex){
                Log.d("", "onPostExecute: ");
            }
        }

        @Override
        protected void onCancelled() {

        }
    }
    public class Category7 extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mProgressSpinner7.setIndeterminate(true);
            mProgressSpinner7.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {

            String data= performPostCall(params[0],map7);

            detailListMainActivityCourse7=new ArrayList<DetailDataModelCourses>();

            //detailList=new ArrayList<DetailDataModelCourses>();

            detailListMainActivityCourseThumbnail=new ArrayList<DetailDataModelCoursesThumbnails>();

            detailListMainActivityCourseDetailContentss = new ArrayList<>();
            detailListMainActivityCourseDetailUnits = new ArrayList<>();
            detailListMainActivityCourseDetailUnitQuizList = new ArrayList<>();

            DetailDataModelCourses model = new DetailDataModelCourses();

            try{

                JSONObject jObjectMain = new JSONObject(data);
                JSONObject jObject;

                JSONArray objectCourse = (JSONArray) jObjectMain.getJSONArray("data");

                try {



                    for (int ec = 0; ec < objectCourse.length(); ec++)
                    {
                        DetailDataModelCourses modelAlterMainActivity = new DetailDataModelCourses();

                        JSONObject jObjEnrolledCourses = objectCourse.getJSONObject(ec);

                        String featured = jObjEnrolledCourses.getString("featured");
                        String Eid = jObjEnrolledCourses.getString("id");
                        String Edetails = jObjEnrolledCourses.getString("details");
                        String Eadmission_status = jObjEnrolledCourses.getString("admission_status");
                        String averageRating = jObjEnrolledCourses.getString("averageRating");
                        String certificate_alias_name = jObjEnrolledCourses.getString("certificate_alias_name");
                        String clone_status = jObjEnrolledCourses.getString("clone_status");
                        String code = jObjEnrolledCourses.getString("code");
                        String courses_for_status = jObjEnrolledCourses.getString("courses_for_status");
                        String course_alias_name = jObjEnrolledCourses.getString("course_alias_name");
                        String course_motto = jObjEnrolledCourses.getString("course_motto");
                        String created_at = jObjEnrolledCourses.getString("created_at");
                        String duration = jObjEnrolledCourses.getString("duration");
                        String end_date = jObjEnrolledCourses.getString("end_date");
                        String enrolment_approval_status = jObjEnrolledCourses.getString("enrolment_approval_status");

                        modelAlterMainActivity.setmCertificateAliasName(certificate_alias_name);
                        modelAlterMainActivity.setmAdmissionStatus(Eadmission_status);
                        modelAlterMainActivity.setmAverageRating(averageRating);
                        modelAlterMainActivity.setmCloneStatus(clone_status);
                        modelAlterMainActivity.setmCode(code);
                        modelAlterMainActivity.setmCreatedAt(created_at);
                        modelAlterMainActivity.setmDuration(duration);
                        modelAlterMainActivity.setmEndDate(end_date);
                        modelAlterMainActivity.setmId(Eid);
                        modelAlterMainActivity.setmDetails(Edetails);
                        modelAlterMainActivity.setmFeatured(featured);
                        modelAlterMainActivity.setmEnrolmentApprovalStatus(enrolment_approval_status);
                        modelAlterMainActivity.setmCursesForStatus(courses_for_status);
                        modelAlterMainActivity.setmCourseAliasName(course_alias_name);
                        modelAlterMainActivity.setmCourseMotto(course_motto);
                        //modelForMainActivityCourse.setmStatus(status);

                        // detailListMainActivityCourse.add(modelAlterMainActivity);

                        try
                        {
                            jObject = jObjEnrolledCourses.getJSONObject("syllabus");

                            JSONArray objectEnrollCourseUnits = (JSONArray) jObject.getJSONArray("units");

                            mUnitArrayListNew = new ArrayList<>();

                            for (int ecu=0; ecu<objectEnrollCourseUnits.length()-1; ecu++)
                            {
                                DetailDataModelCoursesDetailContents modelUnitElements = new DetailDataModelCoursesDetailContents();

                                JSONObject objectUnitElements = (JSONObject) objectEnrollCourseUnits.get(ecu);

                                String orderEcu = objectUnitElements.getString("order");
                                String nameEcu = objectUnitElements.getString("name");

                                modelUnitElements.setUnitOrders(orderEcu);
                                modelUnitElements.setUnitNames(nameEcu);

                                mUnitArrayListNew.add(modelUnitElements);
                            }

                            //for parsing syllebus strings

                            DetailDataModelCourses model7 = new DetailDataModelCourses();

                            String study_mode_Syllebus = jObject.getString("study_mode");

                            model7.setStudyModeSyllebus(study_mode_Syllebus);

                            JSONObject jObjectCourse = jObjEnrolledCourses.getJSONObject("course");

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

                            detailListMainActivityCourseThumbnail.add(modelCourseThumbnail);

                            //model.setmArrayListThumbnails(detailListMainActivityCourseThumbnail);

                            //for parsing Updated by strings
                            JSONObject jObjectUpdatedBy = jObjEnrolledCourses.getJSONObject("UpdatedBy");


                            DetailDataModelCourses model9 = new DetailDataModelCourses();

                            String education_statusUpdatedBy = jObjectUpdatedBy.getString("education_status");
                            String emailUpdatedBy = jObjectUpdatedBy.getString("email");
                            String idUpdatedBy = jObjectUpdatedBy.getString("id");
                            String nameUpdatedBy = jObjectUpdatedBy.getString("name");
                            String phoneUpdatedBy = jObjectUpdatedBy.getString("phone");
                            String UserInfoUpdatedBy = jObjectUpdatedBy.getString("UserInfo");
                            String usernameUpdatedBy = jObjectUpdatedBy.getString("username");

                            model9.setEducation_statusUpdatedBy(education_statusUpdatedBy);
                            model9.setEmailUpdatedBy(emailUpdatedBy);
                            model9.setIdUpdatedBy(idUpdatedBy);
                            model9.setNameUpdatedBy(nameUpdatedBy);
                            model9.setPhoneUpdatedBy(phoneUpdatedBy);
                            model9.setUserInfoUpdatedBy(UserInfoUpdatedBy);
                            model9.setUsernameUpdatedBy(usernameUpdatedBy);

                            //for parsing owner strings
                            JSONObject jObjectOwner = jObjEnrolledCourses.getJSONObject("owner");

                            DetailDataModelCourses model10 = new DetailDataModelCourses();

                            String updated_at_owner = jObjectOwner.getString("updated_at");
                            String institution_name_owner = jObjectOwner.getString("institution_name");
                            String id_owner = jObjectOwner.getString("id");
                            String created_at_owner = jObjectOwner.getString("created_at");

                            model10.setUpdated_at_owner(updated_at_owner);
                            model10.setInstitution_name_owner(institution_name_owner);
                            model10.setId_owner(id_owner);
                            model10.setCreated_at_owner(created_at_owner);

                            //for parsing created by strings
                            JSONObject jObjectCreatedBy = jObjEnrolledCourses.getJSONObject("CreatedBy");


                            DetailDataModelCourses model11 = new DetailDataModelCourses();

                            String education_statusCreatedBy = jObjectCreatedBy.getString("education_status");
                            String emailCreatedBy = jObjectCreatedBy.getString("email");
                            String idCreatedBy = jObjectCreatedBy.getString("id");
                            String nameCreatedBy = jObjectCreatedBy.getString("name");
                            String phoneCreatedBy = jObjectCreatedBy.getString("phone");
                            String UserInfoCreatedBy = jObjectCreatedBy.getString("UserInfo");
                            String usernameCreatedBy = jObjectCreatedBy.getString("username");

                            model11.setEducation_statusUpdatedBy(education_statusCreatedBy);
                            model11.setEmailUpdatedBy(emailCreatedBy);
                            model11.setIdCreatedBy(idCreatedBy);
                            model11.setNameCreatedBy(nameCreatedBy);
                            model11.setPhoneCreatedBy(phoneCreatedBy);
                            model11.setUserInfoCreatedBy(UserInfoCreatedBy);
                            model11.setUsernameCreatedBy(usernameCreatedBy);

                            // parsing from syllebus

                            try{
                                mContentArrayListNew = new ArrayList<>();


                                for (int ii = 0; ii < jObject.length()-2; ii++)
                                {
                                    JSONObject jSObject2 = jObject.getJSONObject("" + ii);

                                    //for parsing lessons > {0} > "syllebus" > "0" > "data"

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
                                            //model2.setmTitleAnother(mTitle);//*

                                            // For parsing "file_type" > "data" > {0} > {0} > "syllebus" > "0" > "data"

                                            JSONObject jObjAgain2 = jObjAgain.getJSONObject("file_type");

                                            DetailDataModelCourses modelFileType = new DetailDataModelCourses();

                                            String pdf = jObjAgain2.getString("pdf");
                                            String excel = jObjAgain2.getString("excel");
                                            String doc = jObjAgain2.getString("doc");
                                            String csv = jObjAgain2.getString("csv");

                                            modelFileType.setmPdf(pdf);
                                            modelFileType.setmXcel(excel);
                                            modelFileType.setmDoc(doc);
                                            modelFileType.setmCsv(csv);

                                            try{
                                                DetailDataModelCoursesDetailContents modelCourseContents = new DetailDataModelCoursesDetailContents();

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
                                                modelCourseContents.setDescription_content(description_content);
                                                modelCourseContents.setFile_encode_path(file_encode_path);
                                                modelCourseContents.setFile_name(file_name);
                                                modelCourseContents.setId_content(id_content);
                                                modelCourseContents.setLicense(license);
                                                modelCourseContents.setOwner_id(owner_id);
                                                modelCourseContents.setCreated_at_content(created_at_content);

                                                mContentArrayListNew.add(modelCourseContents);
                                            }
                                            catch (Exception ex){
                                                Log.d("", "onResponse: ");
                                            }

                                            //For parsing Quizes

                                            //JSONArray jObjQuizes = (JSONArray) jSObject3.getJSONArray("multi_ques_list");

                                            // For parsing object "Content" > {0} > {0} > "syllebus" > "0" > "data"

                                            DetailDataModelCourses model5 = new DetailDataModelCourses();

                                            if (content_type.equalsIgnoreCase("quiz")) {
                                                try {

                                                    for (int ctq = 0; ctq < jSObject3.length(); ctq++) {

                                                        JSONArray jSonObjMultiQ2 = (JSONArray) jSObject3.getJSONArray("ques_list");

                                                        mUnitQuizList = new ArrayList<>();

                                                        for(int qlist2=0; qlist2<jSonObjMultiQ2.length(); qlist2++){

                                                            DetailDataModelCoursesDetailContents modelUnitQuizElements2 = new DetailDataModelCoursesDetailContents();

                                                            JSONObject objectAgainAnother2 = (JSONObject) jSonObjMultiQ2.get(qlist2);

                                                            String qTitle = objectAgainAnother2.getString("title");

                                                            qTitle = qTitle.replace("<p>","");
                                                            qTitle = qTitle.replace("</p>","");

                                                            modelUnitQuizElements2.setmQuizTitle(qTitle);

                                                            mUnitQuizList.add(modelUnitQuizElements2);
                                                        }
                                                    }
                                                } catch (Exception ex) {
                                                    Log.d("", "onResponse: ");
                                                }
                                            }

                                            if (quizYesOrNot.equalsIgnoreCase("1")) {

                                                try {

                                                    for (int l = 0; l < jSObject3.length(); l++) {

                                                        JSONArray jSonObjMultiQ = (JSONArray) jSObject3.getJSONArray("multi_ques_list");

                                                        for (int mql = 0; mql < jSonObjMultiQ.length(); mql++) {

                                                            JSONObject objectAgainAnother2 = (JSONObject) jSonObjMultiQ.get(mql);

                                                            String mPulse = objectAgainAnother2.getString("pulse");

                                                            
                                                        }


                                                    }
                                                } catch (Exception ex) {
                                                    Log.d("", "onResponse: ");
                                                }
                                            } else {
                                                //  Toast.makeText(mContext, "", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    }
                                    catch (Exception ex){
                                        Log.d("", "onResponse: ");
                                    }
                                }
                            }
                            catch (Exception ex){
                                Log.d("", "onResponse: ");
                            }

                            detailListMainActivityCourseDetailContentss.add(mContentArrayListNew);

                            detailListMainActivityCourseDetailUnits.add(mUnitArrayListNew);

                            detailListMainActivityCourseDetailUnitQuizList.add(mUnitQuizList);
                        }



                        catch (Exception ex){
                            Log.d("", "onResponse: ");
                        }

                        modelAlterMainActivity.setmArrayListThumbnails(detailListMainActivityCourseThumbnail);
                        modelAlterMainActivity.setmArrayListContentDetails(detailListMainActivityCourseDetailContentss);
                        modelAlterMainActivity.setmArrayListCourseUnits(detailListMainActivityCourseDetailUnits);
                        modelAlterMainActivity.setmArrayListCourseQuizs(detailListMainActivityCourseDetailUnitQuizList);

                        detailListMainActivityCourse7.add(modelAlterMainActivity);


                    }



                }


                catch (Exception ex) {
                    Log.d("", "onResponse: ");
                }


            }
            catch (Exception ex){
                Log.d("", "doInBackground: ");
            }

            return data;
        }

        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);

            setRecyclerView7();

            adapter7=new RecyclerViewAdapterCategory1(detailListMainActivityCourse7,mContext);

            recyclerView7.setAdapter(adapter7);
            adapter7.notifyDataSetChanged();

            mProgressSpinner7.setVisibility(View.GONE);

            try {
                map8.put("id", detailListMainActivityCourseCat.get(7).getmCourseCategoryId());
                map8.put("name", detailListMainActivityCourseCat.get(7).getmCourseCategoryNameEn());
                map8.put("rating", "");

                new Category8().execute(urlGetCourses);
            }
            catch (Exception ex){
                Log.d("", "onPostExecute: ");
            }


        }

        @Override
        protected void onCancelled() {

        }
    }
    public class Category8 extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mProgressSpinner8.setIndeterminate(true);
            mProgressSpinner8.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {

            String data= performPostCall(params[0],map8);

            detailListMainActivityCourse8=new ArrayList<DetailDataModelCourses>();

            //detailList=new ArrayList<DetailDataModelCourses>();

            detailListMainActivityCourseThumbnail=new ArrayList<DetailDataModelCoursesThumbnails>();

            detailListMainActivityCourseDetailContentss = new ArrayList<>();
            detailListMainActivityCourseDetailUnits = new ArrayList<>();
            detailListMainActivityCourseDetailUnitQuizList = new ArrayList<>();

            DetailDataModelCourses model = new DetailDataModelCourses();

            try{

                JSONObject jObjectMain = new JSONObject(data);
                JSONObject jObject;

                JSONArray objectCourse = (JSONArray) jObjectMain.getJSONArray("data");

                try {



                    for (int ec = 0; ec < objectCourse.length(); ec++)
                    {
                        DetailDataModelCourses modelAlterMainActivity = new DetailDataModelCourses();

                        JSONObject jObjEnrolledCourses = objectCourse.getJSONObject(ec);

                        String featured = jObjEnrolledCourses.getString("featured");
                        String Eid = jObjEnrolledCourses.getString("id");
                        String Edetails = jObjEnrolledCourses.getString("details");
                        String Eadmission_status = jObjEnrolledCourses.getString("admission_status");
                        String averageRating = jObjEnrolledCourses.getString("averageRating");
                        String certificate_alias_name = jObjEnrolledCourses.getString("certificate_alias_name");
                        String clone_status = jObjEnrolledCourses.getString("clone_status");
                        String code = jObjEnrolledCourses.getString("code");
                        String courses_for_status = jObjEnrolledCourses.getString("courses_for_status");
                        String course_alias_name = jObjEnrolledCourses.getString("course_alias_name");
                        String course_motto = jObjEnrolledCourses.getString("course_motto");
                        String created_at = jObjEnrolledCourses.getString("created_at");
                        String duration = jObjEnrolledCourses.getString("duration");
                        String end_date = jObjEnrolledCourses.getString("end_date");
                        String enrolment_approval_status = jObjEnrolledCourses.getString("enrolment_approval_status");

                        modelAlterMainActivity.setmCertificateAliasName(certificate_alias_name);
                        modelAlterMainActivity.setmAdmissionStatus(Eadmission_status);
                        modelAlterMainActivity.setmAverageRating(averageRating);
                        modelAlterMainActivity.setmCloneStatus(clone_status);
                        modelAlterMainActivity.setmCode(code);
                        modelAlterMainActivity.setmCreatedAt(created_at);
                        modelAlterMainActivity.setmDuration(duration);
                        modelAlterMainActivity.setmEndDate(end_date);
                        modelAlterMainActivity.setmId(Eid);
                        modelAlterMainActivity.setmDetails(Edetails);
                        modelAlterMainActivity.setmFeatured(featured);
                        modelAlterMainActivity.setmEnrolmentApprovalStatus(enrolment_approval_status);
                        modelAlterMainActivity.setmCursesForStatus(courses_for_status);
                        modelAlterMainActivity.setmCourseAliasName(course_alias_name);
                        modelAlterMainActivity.setmCourseMotto(course_motto);
                        //modelForMainActivityCourse.setmStatus(status);

                        // detailListMainActivityCourse.add(modelAlterMainActivity);

                        try
                        {
                            jObject = jObjEnrolledCourses.getJSONObject("syllabus");

                            JSONArray objectEnrollCourseUnits = (JSONArray) jObject.getJSONArray("units");

                            mUnitArrayListNew = new ArrayList<>();

                            for (int ecu=0; ecu<objectEnrollCourseUnits.length()-1; ecu++)
                            {
                                DetailDataModelCoursesDetailContents modelUnitElements = new DetailDataModelCoursesDetailContents();

                                JSONObject objectUnitElements = (JSONObject) objectEnrollCourseUnits.get(ecu);

                                String orderEcu = objectUnitElements.getString("order");
                                String nameEcu = objectUnitElements.getString("name");

                                modelUnitElements.setUnitOrders(orderEcu);
                                modelUnitElements.setUnitNames(nameEcu);

                                mUnitArrayListNew.add(modelUnitElements);
                            }

                            //for parsing syllebus strings

                            DetailDataModelCourses model7 = new DetailDataModelCourses();

                            String study_mode_Syllebus = jObject.getString("study_mode");

                            model7.setStudyModeSyllebus(study_mode_Syllebus);

                            JSONObject jObjectCourse = jObjEnrolledCourses.getJSONObject("course");

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

                            detailListMainActivityCourseThumbnail.add(modelCourseThumbnail);

                            //model.setmArrayListThumbnails(detailListMainActivityCourseThumbnail);

                            //for parsing Updated by strings
                            JSONObject jObjectUpdatedBy = jObjEnrolledCourses.getJSONObject("UpdatedBy");


                            DetailDataModelCourses model9 = new DetailDataModelCourses();

                            String education_statusUpdatedBy = jObjectUpdatedBy.getString("education_status");
                            String emailUpdatedBy = jObjectUpdatedBy.getString("email");
                            String idUpdatedBy = jObjectUpdatedBy.getString("id");
                            String nameUpdatedBy = jObjectUpdatedBy.getString("name");
                            String phoneUpdatedBy = jObjectUpdatedBy.getString("phone");
                            String UserInfoUpdatedBy = jObjectUpdatedBy.getString("UserInfo");
                            String usernameUpdatedBy = jObjectUpdatedBy.getString("username");

                            model9.setEducation_statusUpdatedBy(education_statusUpdatedBy);
                            model9.setEmailUpdatedBy(emailUpdatedBy);
                            model9.setIdUpdatedBy(idUpdatedBy);
                            model9.setNameUpdatedBy(nameUpdatedBy);
                            model9.setPhoneUpdatedBy(phoneUpdatedBy);
                            model9.setUserInfoUpdatedBy(UserInfoUpdatedBy);
                            model9.setUsernameUpdatedBy(usernameUpdatedBy);

                            //for parsing owner strings
                            JSONObject jObjectOwner = jObjEnrolledCourses.getJSONObject("owner");

                            DetailDataModelCourses model10 = new DetailDataModelCourses();

                            String updated_at_owner = jObjectOwner.getString("updated_at");
                            String institution_name_owner = jObjectOwner.getString("institution_name");
                            String id_owner = jObjectOwner.getString("id");
                            String created_at_owner = jObjectOwner.getString("created_at");

                            model10.setUpdated_at_owner(updated_at_owner);
                            model10.setInstitution_name_owner(institution_name_owner);
                            model10.setId_owner(id_owner);
                            model10.setCreated_at_owner(created_at_owner);

                            //for parsing created by strings
                            JSONObject jObjectCreatedBy = jObjEnrolledCourses.getJSONObject("CreatedBy");


                            DetailDataModelCourses model11 = new DetailDataModelCourses();

                            String education_statusCreatedBy = jObjectCreatedBy.getString("education_status");
                            String emailCreatedBy = jObjectCreatedBy.getString("email");
                            String idCreatedBy = jObjectCreatedBy.getString("id");
                            String nameCreatedBy = jObjectCreatedBy.getString("name");
                            String phoneCreatedBy = jObjectCreatedBy.getString("phone");
                            String UserInfoCreatedBy = jObjectCreatedBy.getString("UserInfo");
                            String usernameCreatedBy = jObjectCreatedBy.getString("username");

                            model11.setEducation_statusUpdatedBy(education_statusCreatedBy);
                            model11.setEmailUpdatedBy(emailCreatedBy);
                            model11.setIdCreatedBy(idCreatedBy);
                            model11.setNameCreatedBy(nameCreatedBy);
                            model11.setPhoneCreatedBy(phoneCreatedBy);
                            model11.setUserInfoCreatedBy(UserInfoCreatedBy);
                            model11.setUsernameCreatedBy(usernameCreatedBy);

                            // parsing from syllebus

                            try{
                                mContentArrayListNew = new ArrayList<>();


                                for (int ii = 0; ii < jObject.length()-2; ii++)
                                {
                                    JSONObject jSObject2 = jObject.getJSONObject("" + ii);

                                    //for parsing lessons > {0} > "syllebus" > "0" > "data"

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
                                            //model2.setmTitleAnother(mTitle);//*

                                            // For parsing "file_type" > "data" > {0} > {0} > "syllebus" > "0" > "data"

                                            JSONObject jObjAgain2 = jObjAgain.getJSONObject("file_type");

                                            DetailDataModelCourses modelFileType = new DetailDataModelCourses();

                                            String pdf = jObjAgain2.getString("pdf");
                                            String excel = jObjAgain2.getString("excel");
                                            String doc = jObjAgain2.getString("doc");
                                            String csv = jObjAgain2.getString("csv");

                                            modelFileType.setmPdf(pdf);
                                            modelFileType.setmXcel(excel);
                                            modelFileType.setmDoc(doc);
                                            modelFileType.setmCsv(csv);

                                            try{
                                                DetailDataModelCoursesDetailContents modelCourseContents = new DetailDataModelCoursesDetailContents();

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
                                                modelCourseContents.setDescription_content(description_content);
                                                modelCourseContents.setFile_encode_path(file_encode_path);
                                                modelCourseContents.setFile_name(file_name);
                                                modelCourseContents.setId_content(id_content);
                                                modelCourseContents.setLicense(license);
                                                modelCourseContents.setOwner_id(owner_id);
                                                modelCourseContents.setCreated_at_content(created_at_content);

                                                mContentArrayListNew.add(modelCourseContents);
                                            }
                                            catch (Exception ex){
                                                Log.d("", "onResponse: ");
                                            }

                                            //For parsing Quizes

                                            //JSONArray jObjQuizes = (JSONArray) jSObject3.getJSONArray("multi_ques_list");

                                            // For parsing object "Content" > {0} > {0} > "syllebus" > "0" > "data"

                                            DetailDataModelCourses model5 = new DetailDataModelCourses();

                                            if (content_type.equalsIgnoreCase("quiz")) {
                                                try {

                                                    for (int ctq = 0; ctq < jSObject3.length(); ctq++) {

                                                        JSONArray jSonObjMultiQ2 = (JSONArray) jSObject3.getJSONArray("ques_list");

                                                        mUnitQuizList = new ArrayList<>();

                                                        for(int qlist2=0; qlist2<jSonObjMultiQ2.length(); qlist2++){

                                                            DetailDataModelCoursesDetailContents modelUnitQuizElements2 = new DetailDataModelCoursesDetailContents();

                                                            JSONObject objectAgainAnother2 = (JSONObject) jSonObjMultiQ2.get(qlist2);

                                                            String qTitle = objectAgainAnother2.getString("title");

                                                            qTitle = qTitle.replace("<p>","");
                                                            qTitle = qTitle.replace("</p>","");

                                                            modelUnitQuizElements2.setmQuizTitle(qTitle);

                                                            mUnitQuizList.add(modelUnitQuizElements2);
                                                        }
                                                    }
                                                } catch (Exception ex) {
                                                    Log.d("", "onResponse: ");
                                                }
                                            }

                                            if (quizYesOrNot.equalsIgnoreCase("1")) {

                                                try {

                                                    for (int l = 0; l < jSObject3.length(); l++) {

                                                        JSONArray jSonObjMultiQ = (JSONArray) jSObject3.getJSONArray("multi_ques_list");

                                                        for (int mql = 0; mql < jSonObjMultiQ.length(); mql++) {

                                                            JSONObject objectAgainAnother2 = (JSONObject) jSonObjMultiQ.get(mql);

                                                            String mPulse = objectAgainAnother2.getString("pulse");

                                                            
                                                        }


                                                    }
                                                } catch (Exception ex) {
                                                    Log.d("", "onResponse: ");
                                                }
                                            } else {
                                                //  Toast.makeText(mContext, "", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    }
                                    catch (Exception ex){
                                        Log.d("", "onResponse: ");
                                    }
                                }
                            }
                            catch (Exception ex){
                                Log.d("", "onResponse: ");
                            }

                            detailListMainActivityCourseDetailContentss.add(mContentArrayListNew);

                            detailListMainActivityCourseDetailUnits.add(mUnitArrayListNew);

                            detailListMainActivityCourseDetailUnitQuizList.add(mUnitQuizList);
                        }



                        catch (Exception ex){
                            Log.d("", "onResponse: ");
                        }

                        modelAlterMainActivity.setmArrayListThumbnails(detailListMainActivityCourseThumbnail);
                        modelAlterMainActivity.setmArrayListContentDetails(detailListMainActivityCourseDetailContentss);
                        modelAlterMainActivity.setmArrayListCourseUnits(detailListMainActivityCourseDetailUnits);
                        modelAlterMainActivity.setmArrayListCourseQuizs(detailListMainActivityCourseDetailUnitQuizList);

                        detailListMainActivityCourse8.add(modelAlterMainActivity);


                    }



                }


                catch (Exception ex) {
                    Log.d("", "onResponse: ");
                }


            }
            catch (Exception ex){
                Log.d("", "doInBackground: ");
            }

            return data;
        }

        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);

            setRecyclerView8();

            adapter8=new RecyclerViewAdapterCategory1(detailListMainActivityCourse8,mContext);

            recyclerView8.setAdapter(adapter8);
            adapter8.notifyDataSetChanged();

            mProgressSpinner8.setVisibility(View.GONE);

            try {
                map9.put("id", detailListMainActivityCourseCat.get(8).getmCourseCategoryId());
                map9.put("name", detailListMainActivityCourseCat.get(8).getmCourseCategoryNameEn());
                map9.put("rating", "");

                new Category9().execute(urlGetCourses);
            }

            catch (Exception ex){
                Log.d("", "onPostExecute: ");
            }
        }

        @Override
        protected void onCancelled() {

        }
    }
    public class Category9 extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mProgressSpinner9.setIndeterminate(true);
            mProgressSpinner9.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {

            String data= performPostCall(params[0],map9);

            detailListMainActivityCourse9=new ArrayList<DetailDataModelCourses>();

            //detailList=new ArrayList<DetailDataModelCourses>();

            detailListMainActivityCourseThumbnail=new ArrayList<DetailDataModelCoursesThumbnails>();

            detailListMainActivityCourseDetailContentss = new ArrayList<>();
            detailListMainActivityCourseDetailUnits = new ArrayList<>();
            detailListMainActivityCourseDetailUnitQuizList = new ArrayList<>();

            DetailDataModelCourses model = new DetailDataModelCourses();

            try{

                JSONObject jObjectMain = new JSONObject(data);
                JSONObject jObject;

                JSONArray objectCourse = (JSONArray) jObjectMain.getJSONArray("data");

                try {



                    for (int ec = 0; ec < objectCourse.length(); ec++)
                    {
                        DetailDataModelCourses modelAlterMainActivity = new DetailDataModelCourses();

                        JSONObject jObjEnrolledCourses = objectCourse.getJSONObject(ec);

                        String featured = jObjEnrolledCourses.getString("featured");
                        String Eid = jObjEnrolledCourses.getString("id");
                        String Edetails = jObjEnrolledCourses.getString("details");
                        String Eadmission_status = jObjEnrolledCourses.getString("admission_status");
                        String averageRating = jObjEnrolledCourses.getString("averageRating");
                        String certificate_alias_name = jObjEnrolledCourses.getString("certificate_alias_name");
                        String clone_status = jObjEnrolledCourses.getString("clone_status");
                        String code = jObjEnrolledCourses.getString("code");
                        String courses_for_status = jObjEnrolledCourses.getString("courses_for_status");
                        String course_alias_name = jObjEnrolledCourses.getString("course_alias_name");
                        String course_motto = jObjEnrolledCourses.getString("course_motto");
                        String created_at = jObjEnrolledCourses.getString("created_at");
                        String duration = jObjEnrolledCourses.getString("duration");
                        String end_date = jObjEnrolledCourses.getString("end_date");
                        String enrolment_approval_status = jObjEnrolledCourses.getString("enrolment_approval_status");

                        modelAlterMainActivity.setmCertificateAliasName(certificate_alias_name);
                        modelAlterMainActivity.setmAdmissionStatus(Eadmission_status);
                        modelAlterMainActivity.setmAverageRating(averageRating);
                        modelAlterMainActivity.setmCloneStatus(clone_status);
                        modelAlterMainActivity.setmCode(code);
                        modelAlterMainActivity.setmCreatedAt(created_at);
                        modelAlterMainActivity.setmDuration(duration);
                        modelAlterMainActivity.setmEndDate(end_date);
                        modelAlterMainActivity.setmId(Eid);
                        modelAlterMainActivity.setmDetails(Edetails);
                        modelAlterMainActivity.setmFeatured(featured);
                        modelAlterMainActivity.setmEnrolmentApprovalStatus(enrolment_approval_status);
                        modelAlterMainActivity.setmCursesForStatus(courses_for_status);
                        modelAlterMainActivity.setmCourseAliasName(course_alias_name);
                        modelAlterMainActivity.setmCourseMotto(course_motto);
                        //modelForMainActivityCourse.setmStatus(status);

                        // detailListMainActivityCourse.add(modelAlterMainActivity);

                        try
                        {
                            jObject = jObjEnrolledCourses.getJSONObject("syllabus");

                            JSONArray objectEnrollCourseUnits = (JSONArray) jObject.getJSONArray("units");

                            mUnitArrayListNew = new ArrayList<>();

                            for (int ecu=0; ecu<objectEnrollCourseUnits.length()-1; ecu++)
                            {
                                DetailDataModelCoursesDetailContents modelUnitElements = new DetailDataModelCoursesDetailContents();

                                JSONObject objectUnitElements = (JSONObject) objectEnrollCourseUnits.get(ecu);

                                String orderEcu = objectUnitElements.getString("order");
                                String nameEcu = objectUnitElements.getString("name");

                                modelUnitElements.setUnitOrders(orderEcu);
                                modelUnitElements.setUnitNames(nameEcu);

                                mUnitArrayListNew.add(modelUnitElements);
                            }

                            //for parsing syllebus strings

                            DetailDataModelCourses model7 = new DetailDataModelCourses();

                            String study_mode_Syllebus = jObject.getString("study_mode");

                            model7.setStudyModeSyllebus(study_mode_Syllebus);

                            JSONObject jObjectCourse = jObjEnrolledCourses.getJSONObject("course");

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

                            detailListMainActivityCourseThumbnail.add(modelCourseThumbnail);

                            //model.setmArrayListThumbnails(detailListMainActivityCourseThumbnail);

                            //for parsing Updated by strings
                            JSONObject jObjectUpdatedBy = jObjEnrolledCourses.getJSONObject("UpdatedBy");


                            DetailDataModelCourses model9 = new DetailDataModelCourses();

                            String education_statusUpdatedBy = jObjectUpdatedBy.getString("education_status");
                            String emailUpdatedBy = jObjectUpdatedBy.getString("email");
                            String idUpdatedBy = jObjectUpdatedBy.getString("id");
                            String nameUpdatedBy = jObjectUpdatedBy.getString("name");
                            String phoneUpdatedBy = jObjectUpdatedBy.getString("phone");
                            String UserInfoUpdatedBy = jObjectUpdatedBy.getString("UserInfo");
                            String usernameUpdatedBy = jObjectUpdatedBy.getString("username");

                            model9.setEducation_statusUpdatedBy(education_statusUpdatedBy);
                            model9.setEmailUpdatedBy(emailUpdatedBy);
                            model9.setIdUpdatedBy(idUpdatedBy);
                            model9.setNameUpdatedBy(nameUpdatedBy);
                            model9.setPhoneUpdatedBy(phoneUpdatedBy);
                            model9.setUserInfoUpdatedBy(UserInfoUpdatedBy);
                            model9.setUsernameUpdatedBy(usernameUpdatedBy);

                            //for parsing owner strings
                            JSONObject jObjectOwner = jObjEnrolledCourses.getJSONObject("owner");

                            DetailDataModelCourses model10 = new DetailDataModelCourses();

                            String updated_at_owner = jObjectOwner.getString("updated_at");
                            String institution_name_owner = jObjectOwner.getString("institution_name");
                            String id_owner = jObjectOwner.getString("id");
                            String created_at_owner = jObjectOwner.getString("created_at");

                            model10.setUpdated_at_owner(updated_at_owner);
                            model10.setInstitution_name_owner(institution_name_owner);
                            model10.setId_owner(id_owner);
                            model10.setCreated_at_owner(created_at_owner);

                            //for parsing created by strings
                            JSONObject jObjectCreatedBy = jObjEnrolledCourses.getJSONObject("CreatedBy");


                            DetailDataModelCourses model11 = new DetailDataModelCourses();

                            String education_statusCreatedBy = jObjectCreatedBy.getString("education_status");
                            String emailCreatedBy = jObjectCreatedBy.getString("email");
                            String idCreatedBy = jObjectCreatedBy.getString("id");
                            String nameCreatedBy = jObjectCreatedBy.getString("name");
                            String phoneCreatedBy = jObjectCreatedBy.getString("phone");
                            String UserInfoCreatedBy = jObjectCreatedBy.getString("UserInfo");
                            String usernameCreatedBy = jObjectCreatedBy.getString("username");

                            model11.setEducation_statusUpdatedBy(education_statusCreatedBy);
                            model11.setEmailUpdatedBy(emailCreatedBy);
                            model11.setIdCreatedBy(idCreatedBy);
                            model11.setNameCreatedBy(nameCreatedBy);
                            model11.setPhoneCreatedBy(phoneCreatedBy);
                            model11.setUserInfoCreatedBy(UserInfoCreatedBy);
                            model11.setUsernameCreatedBy(usernameCreatedBy);

                            // parsing from syllebus

                            try{
                                mContentArrayListNew = new ArrayList<>();


                                for (int ii = 0; ii < jObject.length()-2; ii++)
                                {
                                    JSONObject jSObject2 = jObject.getJSONObject("" + ii);

                                    //for parsing lessons > {0} > "syllebus" > "0" > "data"

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
                                            //model2.setmTitleAnother(mTitle);//*

                                            // For parsing "file_type" > "data" > {0} > {0} > "syllebus" > "0" > "data"

                                            JSONObject jObjAgain2 = jObjAgain.getJSONObject("file_type");

                                            DetailDataModelCourses modelFileType = new DetailDataModelCourses();

                                            String pdf = jObjAgain2.getString("pdf");
                                            String excel = jObjAgain2.getString("excel");
                                            String doc = jObjAgain2.getString("doc");
                                            String csv = jObjAgain2.getString("csv");

                                            modelFileType.setmPdf(pdf);
                                            modelFileType.setmXcel(excel);
                                            modelFileType.setmDoc(doc);
                                            modelFileType.setmCsv(csv);

                                            try{
                                                DetailDataModelCoursesDetailContents modelCourseContents = new DetailDataModelCoursesDetailContents();

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
                                                modelCourseContents.setDescription_content(description_content);
                                                modelCourseContents.setFile_encode_path(file_encode_path);
                                                modelCourseContents.setFile_name(file_name);
                                                modelCourseContents.setId_content(id_content);
                                                modelCourseContents.setLicense(license);
                                                modelCourseContents.setOwner_id(owner_id);
                                                modelCourseContents.setCreated_at_content(created_at_content);

                                                mContentArrayListNew.add(modelCourseContents);
                                            }
                                            catch (Exception ex){
                                                Log.d("", "onResponse: ");
                                            }

                                            //For parsing Quizes

                                            //JSONArray jObjQuizes = (JSONArray) jSObject3.getJSONArray("multi_ques_list");

                                            // For parsing object "Content" > {0} > {0} > "syllebus" > "0" > "data"

                                            DetailDataModelCourses model5 = new DetailDataModelCourses();

                                            if (content_type.equalsIgnoreCase("quiz")) {
                                                try {

                                                    for (int ctq = 0; ctq < jSObject3.length(); ctq++) {

                                                        JSONArray jSonObjMultiQ2 = (JSONArray) jSObject3.getJSONArray("ques_list");

                                                        mUnitQuizList = new ArrayList<>();

                                                        for(int qlist2=0; qlist2<jSonObjMultiQ2.length(); qlist2++){

                                                            DetailDataModelCoursesDetailContents modelUnitQuizElements2 = new DetailDataModelCoursesDetailContents();

                                                            JSONObject objectAgainAnother2 = (JSONObject) jSonObjMultiQ2.get(qlist2);

                                                            String qTitle = objectAgainAnother2.getString("title");

                                                            qTitle = qTitle.replace("<p>","");
                                                            qTitle = qTitle.replace("</p>","");

                                                            modelUnitQuizElements2.setmQuizTitle(qTitle);

                                                            mUnitQuizList.add(modelUnitQuizElements2);
                                                        }
                                                    }
                                                } catch (Exception ex) {
                                                    Log.d("", "onResponse: ");
                                                }
                                            }

                                            if (quizYesOrNot.equalsIgnoreCase("1")) {

                                                try {

                                                    for (int l = 0; l < jSObject3.length(); l++) {

                                                        JSONArray jSonObjMultiQ = (JSONArray) jSObject3.getJSONArray("multi_ques_list");

                                                        for (int mql = 0; mql < jSonObjMultiQ.length(); mql++) {

                                                            JSONObject objectAgainAnother2 = (JSONObject) jSonObjMultiQ.get(mql);

                                                            String mPulse = objectAgainAnother2.getString("pulse");

                                                            
                                                        }


                                                    }
                                                } catch (Exception ex) {
                                                    Log.d("", "onResponse: ");
                                                }
                                            } else {
                                                //  Toast.makeText(mContext, "", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    }
                                    catch (Exception ex){
                                        Log.d("", "onResponse: ");
                                    }
                                }
                            }
                            catch (Exception ex){
                                Log.d("", "onResponse: ");
                            }

                            detailListMainActivityCourseDetailContentss.add(mContentArrayListNew);

                            detailListMainActivityCourseDetailUnits.add(mUnitArrayListNew);

                            detailListMainActivityCourseDetailUnitQuizList.add(mUnitQuizList);
                        }



                        catch (Exception ex){
                            Log.d("", "onResponse: ");
                        }

                        modelAlterMainActivity.setmArrayListThumbnails(detailListMainActivityCourseThumbnail);
                        modelAlterMainActivity.setmArrayListContentDetails(detailListMainActivityCourseDetailContentss);
                        modelAlterMainActivity.setmArrayListCourseUnits(detailListMainActivityCourseDetailUnits);
                        modelAlterMainActivity.setmArrayListCourseQuizs(detailListMainActivityCourseDetailUnitQuizList);

                        detailListMainActivityCourse9.add(modelAlterMainActivity);


                    }



                }


                catch (Exception ex) {
                    Log.d("", "onResponse: ");
                }


            }
            catch (Exception ex){
                Log.d("", "doInBackground: ");
            }

            return data;
        }

        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);

            setRecyclerView9();

            adapter9=new RecyclerViewAdapterCategory1(detailListMainActivityCourse9,mContext);

            recyclerView9.setAdapter(adapter9);
            adapter9.notifyDataSetChanged();

            mProgressSpinner9.setVisibility(View.GONE);

            try {
                map10.put("id", detailListMainActivityCourseCat.get(9).getmCourseCategoryId());
                map10.put("name", detailListMainActivityCourseCat.get(9).getmCourseCategoryNameEn());
                map10.put("rating", "");

                new Category10().execute(urlGetCourses);
            }
            catch (Exception ex){
                Log.d("", "onPostExecute: ");
            }
        }

        @Override
        protected void onCancelled() {

        }
    }
    public class Category10 extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mProgressSpinner10.setIndeterminate(true);
            mProgressSpinner10.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {

            String data= performPostCall(params[0],map10);

            detailListMainActivityCourse10=new ArrayList<DetailDataModelCourses>();

            //detailList=new ArrayList<DetailDataModelCourses>();

            detailListMainActivityCourseThumbnail=new ArrayList<DetailDataModelCoursesThumbnails>();

            detailListMainActivityCourseDetailContentss = new ArrayList<>();
            detailListMainActivityCourseDetailUnits = new ArrayList<>();
            detailListMainActivityCourseDetailUnitQuizList = new ArrayList<>();

            DetailDataModelCourses model = new DetailDataModelCourses();

            try{

                JSONObject jObjectMain = new JSONObject(data);
                JSONObject jObject;

                JSONArray objectCourse = (JSONArray) jObjectMain.getJSONArray("data");

                try {



                    for (int ec = 0; ec < objectCourse.length(); ec++)
                    {
                        DetailDataModelCourses modelAlterMainActivity = new DetailDataModelCourses();

                        JSONObject jObjEnrolledCourses = objectCourse.getJSONObject(ec);

                        String featured = jObjEnrolledCourses.getString("featured");
                        String Eid = jObjEnrolledCourses.getString("id");
                        String Edetails = jObjEnrolledCourses.getString("details");
                        String Eadmission_status = jObjEnrolledCourses.getString("admission_status");
                        String averageRating = jObjEnrolledCourses.getString("averageRating");
                        String certificate_alias_name = jObjEnrolledCourses.getString("certificate_alias_name");
                        String clone_status = jObjEnrolledCourses.getString("clone_status");
                        String code = jObjEnrolledCourses.getString("code");
                        String courses_for_status = jObjEnrolledCourses.getString("courses_for_status");
                        String course_alias_name = jObjEnrolledCourses.getString("course_alias_name");
                        String course_motto = jObjEnrolledCourses.getString("course_motto");
                        String created_at = jObjEnrolledCourses.getString("created_at");
                        String duration = jObjEnrolledCourses.getString("duration");
                        String end_date = jObjEnrolledCourses.getString("end_date");
                        String enrolment_approval_status = jObjEnrolledCourses.getString("enrolment_approval_status");

                        modelAlterMainActivity.setmCertificateAliasName(certificate_alias_name);
                        modelAlterMainActivity.setmAdmissionStatus(Eadmission_status);
                        modelAlterMainActivity.setmAverageRating(averageRating);
                        modelAlterMainActivity.setmCloneStatus(clone_status);
                        modelAlterMainActivity.setmCode(code);
                        modelAlterMainActivity.setmCreatedAt(created_at);
                        modelAlterMainActivity.setmDuration(duration);
                        modelAlterMainActivity.setmEndDate(end_date);
                        modelAlterMainActivity.setmId(Eid);
                        modelAlterMainActivity.setmDetails(Edetails);
                        modelAlterMainActivity.setmFeatured(featured);
                        modelAlterMainActivity.setmEnrolmentApprovalStatus(enrolment_approval_status);
                        modelAlterMainActivity.setmCursesForStatus(courses_for_status);
                        modelAlterMainActivity.setmCourseAliasName(course_alias_name);
                        modelAlterMainActivity.setmCourseMotto(course_motto);
                        //modelForMainActivityCourse.setmStatus(status);

                        // detailListMainActivityCourse.add(modelAlterMainActivity);

                        try
                        {
                            jObject = jObjEnrolledCourses.getJSONObject("syllabus");

                            JSONArray objectEnrollCourseUnits = (JSONArray) jObject.getJSONArray("units");

                            mUnitArrayListNew = new ArrayList<>();

                            for (int ecu=0; ecu<objectEnrollCourseUnits.length()-1; ecu++)
                            {
                                DetailDataModelCoursesDetailContents modelUnitElements = new DetailDataModelCoursesDetailContents();

                                JSONObject objectUnitElements = (JSONObject) objectEnrollCourseUnits.get(ecu);

                                String orderEcu = objectUnitElements.getString("order");
                                String nameEcu = objectUnitElements.getString("name");

                                modelUnitElements.setUnitOrders(orderEcu);
                                modelUnitElements.setUnitNames(nameEcu);

                                mUnitArrayListNew.add(modelUnitElements);
                            }

                            //for parsing syllebus strings

                            DetailDataModelCourses model7 = new DetailDataModelCourses();

                            String study_mode_Syllebus = jObject.getString("study_mode");

                            model7.setStudyModeSyllebus(study_mode_Syllebus);

                            JSONObject jObjectCourse = jObjEnrolledCourses.getJSONObject("course");

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

                            detailListMainActivityCourseThumbnail.add(modelCourseThumbnail);

                            //model.setmArrayListThumbnails(detailListMainActivityCourseThumbnail);

                            //for parsing Updated by strings
                            JSONObject jObjectUpdatedBy = jObjEnrolledCourses.getJSONObject("UpdatedBy");


                            DetailDataModelCourses model9 = new DetailDataModelCourses();

                            String education_statusUpdatedBy = jObjectUpdatedBy.getString("education_status");
                            String emailUpdatedBy = jObjectUpdatedBy.getString("email");
                            String idUpdatedBy = jObjectUpdatedBy.getString("id");
                            String nameUpdatedBy = jObjectUpdatedBy.getString("name");
                            String phoneUpdatedBy = jObjectUpdatedBy.getString("phone");
                            String UserInfoUpdatedBy = jObjectUpdatedBy.getString("UserInfo");
                            String usernameUpdatedBy = jObjectUpdatedBy.getString("username");

                            model9.setEducation_statusUpdatedBy(education_statusUpdatedBy);
                            model9.setEmailUpdatedBy(emailUpdatedBy);
                            model9.setIdUpdatedBy(idUpdatedBy);
                            model9.setNameUpdatedBy(nameUpdatedBy);
                            model9.setPhoneUpdatedBy(phoneUpdatedBy);
                            model9.setUserInfoUpdatedBy(UserInfoUpdatedBy);
                            model9.setUsernameUpdatedBy(usernameUpdatedBy);

                            //for parsing owner strings
                            JSONObject jObjectOwner = jObjEnrolledCourses.getJSONObject("owner");

                            DetailDataModelCourses model10 = new DetailDataModelCourses();

                            String updated_at_owner = jObjectOwner.getString("updated_at");
                            String institution_name_owner = jObjectOwner.getString("institution_name");
                            String id_owner = jObjectOwner.getString("id");
                            String created_at_owner = jObjectOwner.getString("created_at");

                            model10.setUpdated_at_owner(updated_at_owner);
                            model10.setInstitution_name_owner(institution_name_owner);
                            model10.setId_owner(id_owner);
                            model10.setCreated_at_owner(created_at_owner);

                            //for parsing created by strings
                            JSONObject jObjectCreatedBy = jObjEnrolledCourses.getJSONObject("CreatedBy");


                            DetailDataModelCourses model11 = new DetailDataModelCourses();

                            String education_statusCreatedBy = jObjectCreatedBy.getString("education_status");
                            String emailCreatedBy = jObjectCreatedBy.getString("email");
                            String idCreatedBy = jObjectCreatedBy.getString("id");
                            String nameCreatedBy = jObjectCreatedBy.getString("name");
                            String phoneCreatedBy = jObjectCreatedBy.getString("phone");
                            String UserInfoCreatedBy = jObjectCreatedBy.getString("UserInfo");
                            String usernameCreatedBy = jObjectCreatedBy.getString("username");

                            model11.setEducation_statusUpdatedBy(education_statusCreatedBy);
                            model11.setEmailUpdatedBy(emailCreatedBy);
                            model11.setIdCreatedBy(idCreatedBy);
                            model11.setNameCreatedBy(nameCreatedBy);
                            model11.setPhoneCreatedBy(phoneCreatedBy);
                            model11.setUserInfoCreatedBy(UserInfoCreatedBy);
                            model11.setUsernameCreatedBy(usernameCreatedBy);

                            // parsing from syllebus

                            try{
                                mContentArrayListNew = new ArrayList<>();


                                for (int ii = 0; ii < jObject.length()-2; ii++)
                                {
                                    JSONObject jSObject2 = jObject.getJSONObject("" + ii);

                                    //for parsing lessons > {0} > "syllebus" > "0" > "data"

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
                                            //model2.setmTitleAnother(mTitle);//*

                                            // For parsing "file_type" > "data" > {0} > {0} > "syllebus" > "0" > "data"

                                            JSONObject jObjAgain2 = jObjAgain.getJSONObject("file_type");

                                            DetailDataModelCourses modelFileType = new DetailDataModelCourses();

                                            String pdf = jObjAgain2.getString("pdf");
                                            String excel = jObjAgain2.getString("excel");
                                            String doc = jObjAgain2.getString("doc");
                                            String csv = jObjAgain2.getString("csv");

                                            modelFileType.setmPdf(pdf);
                                            modelFileType.setmXcel(excel);
                                            modelFileType.setmDoc(doc);
                                            modelFileType.setmCsv(csv);

                                            try{
                                                DetailDataModelCoursesDetailContents modelCourseContents = new DetailDataModelCoursesDetailContents();

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
                                                modelCourseContents.setDescription_content(description_content);
                                                modelCourseContents.setFile_encode_path(file_encode_path);
                                                modelCourseContents.setFile_name(file_name);
                                                modelCourseContents.setId_content(id_content);
                                                modelCourseContents.setLicense(license);
                                                modelCourseContents.setOwner_id(owner_id);
                                                modelCourseContents.setCreated_at_content(created_at_content);

                                                mContentArrayListNew.add(modelCourseContents);
                                            }
                                            catch (Exception ex){
                                                Log.d("", "onResponse: ");
                                            }

                                            //For parsing Quizes

                                            //JSONArray jObjQuizes = (JSONArray) jSObject3.getJSONArray("multi_ques_list");

                                            // For parsing object "Content" > {0} > {0} > "syllebus" > "0" > "data"

                                            DetailDataModelCourses model5 = new DetailDataModelCourses();

                                            if (content_type.equalsIgnoreCase("quiz")) {
                                                try {

                                                    for (int ctq = 0; ctq < jSObject3.length(); ctq++) {

                                                        JSONArray jSonObjMultiQ2 = (JSONArray) jSObject3.getJSONArray("ques_list");

                                                        mUnitQuizList = new ArrayList<>();

                                                        for(int qlist2=0; qlist2<jSonObjMultiQ2.length(); qlist2++){

                                                            DetailDataModelCoursesDetailContents modelUnitQuizElements2 = new DetailDataModelCoursesDetailContents();

                                                            JSONObject objectAgainAnother2 = (JSONObject) jSonObjMultiQ2.get(qlist2);

                                                            String qTitle = objectAgainAnother2.getString("title");

                                                            qTitle = qTitle.replace("<p>","");
                                                            qTitle = qTitle.replace("</p>","");

                                                            modelUnitQuizElements2.setmQuizTitle(qTitle);

                                                            mUnitQuizList.add(modelUnitQuizElements2);
                                                        }
                                                    }
                                                } catch (Exception ex) {
                                                    Log.d("", "onResponse: ");
                                                }
                                            }

                                            if (quizYesOrNot.equalsIgnoreCase("1")) {

                                                try {

                                                    for (int l = 0; l < jSObject3.length(); l++) {

                                                        JSONArray jSonObjMultiQ = (JSONArray) jSObject3.getJSONArray("multi_ques_list");

                                                        for (int mql = 0; mql < jSonObjMultiQ.length(); mql++) {

                                                            JSONObject objectAgainAnother2 = (JSONObject) jSonObjMultiQ.get(mql);

                                                            String mPulse = objectAgainAnother2.getString("pulse");

                                                            
                                                        }


                                                    }
                                                } catch (Exception ex) {
                                                    Log.d("", "onResponse: ");
                                                }
                                            } else {
                                                //  Toast.makeText(mContext, "", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    }
                                    catch (Exception ex){
                                        Log.d("", "onResponse: ");
                                    }
                                }
                            }
                            catch (Exception ex){
                                Log.d("", "onResponse: ");
                            }

                            detailListMainActivityCourseDetailContentss.add(mContentArrayListNew);

                            detailListMainActivityCourseDetailUnits.add(mUnitArrayListNew);

                            detailListMainActivityCourseDetailUnitQuizList.add(mUnitQuizList);
                        }



                        catch (Exception ex){
                            Log.d("", "onResponse: ");
                        }

                        modelAlterMainActivity.setmArrayListThumbnails(detailListMainActivityCourseThumbnail);
                        modelAlterMainActivity.setmArrayListContentDetails(detailListMainActivityCourseDetailContentss);
                        modelAlterMainActivity.setmArrayListCourseUnits(detailListMainActivityCourseDetailUnits);
                        modelAlterMainActivity.setmArrayListCourseQuizs(detailListMainActivityCourseDetailUnitQuizList);

                        detailListMainActivityCourse10.add(modelAlterMainActivity);


                    }



                }
                catch (Exception ex) {
                    Log.d("", "onResponse: ");
                }

            }
            catch (Exception ex){
                Log.d("", "doInBackground: ");
            }

            return data;
        }

        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);

            setRecyclerView10();

            adapter10=new RecyclerViewAdapterCategory1(detailListMainActivityCourse10,mContext);

            recyclerView10.setAdapter(adapter10);
            adapter10.notifyDataSetChanged();

            mProgressSpinner10.setVisibility(View.GONE);
        }

        @Override
        protected void onCancelled() {

        }
    }

    //Other files

    public String  performPostCall(String requestURL, HashMap<String, String> postDataParams) {

        URL url;
        String response = "";

        try {
            url = new URL(requestURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setRequestProperty ("Authorization", GlobalVar.gReplacingTokenForAllCategories);
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

    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
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
