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

    private ArrayList<DetailDataModelCourses> detailList=new ArrayList<>();


    private ArrayList<DetailDataModelCourses> detailListCourse=new ArrayList<>();

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
    private ArrayList<DetailDataModelAll> detailListCourseCat;

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

    String urlGetCourses="http://api.muktopaath.orangebd.com/api/course/search";

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

    private ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> detailListCourseDetailContentss;
    private ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> detailListCourseDetailUnits;
    private ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> detailListCourseDetailUnitQuizList;

    private ArrayList<DetailDataModelCoursesThumbnails> detailListCourseThumbnail;

    private ArrayList<DetailDataModelCourses> detailListFileType;

    ArrayList<DetailDataModelCoursesDetailContents> mContentArrayListNew;
    ArrayList<DetailDataModelCoursesDetailContents> mUnitArrayListNew;
    ArrayList<DetailDataModelCoursesDetailContents> mUnitQuizList;

    private ArrayList<DetailDataModelCourses> detailList2=new ArrayList<>();
    private ArrayList<DetailDataModelCourses> detailList3=new ArrayList<>();
    private ArrayList<DetailDataModelCourses> detailList4=new ArrayList<>();

    private ArrayList<DetailDataModelCoursesDetailContents> detailListCourseDetailContents;

    private ArrayList<DetailDataModelCourses> detailListAnoPart1=new ArrayList<>();
    private ArrayList<DetailDataModelCourses> detailListAnoPart2=new ArrayList<>();
    private ArrayList<DetailDataModelCourses> detailListAnoPart3=new ArrayList<>();

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

        new GetCourseCategories().execute("http://api.muktopaath.orangebd.com/api/course-categories");

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

                detailListCourseCat = new ArrayList<DetailDataModelAll>();

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

                        detailListCourseCat.add(modelCourseCatAll);



                    }
                } catch (Exception ex) {
                    Log.d("", "onResponse: ");
                }

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

        map1.put("id", detailListCourseCat.get(0).getmCourseCategoryId());
        map1.put("name", detailListCourseCat.get(0).getmCourseCategoryNameEn());
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

        //nestedScrollView.setSmoothScrollingEnabled(true);
        //nestedScrollView.fullScroll(View.FOCUS_UP);
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


            detailListCourse=new ArrayList<DetailDataModelCourses>();

            detailList=new ArrayList<DetailDataModelCourses>();

            detailListCourseThumbnail=new ArrayList<DetailDataModelCoursesThumbnails>();
            detailListCourseDetailContents=new ArrayList<DetailDataModelCoursesDetailContents>();
            detailList10 = new ArrayList<DetailDataModelCourses>();

            detailList7 = new ArrayList<DetailDataModelCourses>();
            detailList8 = new ArrayList<DetailDataModelCourses>();

            detailListCourseDetailContentss = new ArrayList<>();
            detailListCourseDetailUnits = new ArrayList<>();
            detailListCourseDetailUnitQuizList = new ArrayList<>();

            DetailDataModelCourses model = new DetailDataModelCourses();

            try{

                JSONObject jObjectMain = new JSONObject(data);
                JSONObject jObject = new JSONObject(data);


                JSONArray objectCourse = (JSONArray) jObjectMain.getJSONArray("data");

                try {
                    for (int ec = 0; ec < objectCourse.length(); ec++)
                    {

                        JSONObject jObjEnrolledCourses = objectCourse.getJSONObject(ec);

                        DetailDataModelCourses modelForLoginCourse = new DetailDataModelCourses();

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
                        //modelForLoginCourse.setmStatus(status);

                        detailListAnoPart3.add(modelForLoginCourse);

                        GlobalVar.gEnrollCourseList=detailListAnoPart3;

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

                            detailListCourseThumbnail.add(modelCourseThumbnail);

                            //model.setmArrayListThumbnails(detailListCourseThumbnail);

                            //for parsing Updated by strings
                            JSONObject jObjectUpdatedBy = jObjEnrolledCourses.getJSONObject("UpdatedBy");

                            detailList9 = new ArrayList<DetailDataModelCourses>();
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

                            detailList10.add((model10));



                            //for parsing created by strings
                            JSONObject jObjectCreatedBy = jObjEnrolledCourses.getJSONObject("CreatedBy");

                            detailList11 = new ArrayList<DetailDataModelCourses>();
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

                                            /*try{
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
                                            }*/


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

                                                            /*try {
                                                                for (int qs = 0; qs < objectAgainAnother2.length(); qs++) {

                                                                    JSONArray jSonObjMultiQuizes = (JSONArray) objectAgainAnother2.getJSONArray("ques_list");

                                                                    try {
                                                                        //mUnitQuizList = new ArrayList<>();

                                                                        for (int qlist = 0; qlist < jSonObjMultiQuizes.length(); qlist++) {

                                                                            DetailDataModelCoursesDetailContents modelUnitQuizElements = new DetailDataModelCoursesDetailContents();

                                                                            JSONObject jSObjectQuizElements = jSonObjMultiQuizes.getJSONObject(qlist);

                                                                            String qTitle = jSObjectQuizElements.getString("title");
                                                                            modelUnitQuizElements.setmQuizTitle(qTitle);

                                                                            //mUnitQuizList.add(modelUnitQuizElements);
                                                                        }
                                                                    } catch (Exception ex) {
                                                                        Log.d("", "onResponse: ");
                                                                    }
                                                                }
                                                            } catch (Exception ex) {
                                                                Log.d("", "onResponse: ");
                                                            }*/
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
                        }
                        catch (Exception ex){
                            Log.d("", "onResponse: ");
                        }


                        detailListCourseDetailContentss.add(mContentArrayListNew);

                        detailListCourseDetailUnits.add(mUnitArrayListNew);

                        detailListCourseDetailUnitQuizList.add(mUnitQuizList);

                        GlobalVar.gEnrolledInstitution=detailList10;
                    }
                } catch (Exception ex) {
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

            adapter=new RecyclerViewAdapterCategory1(detailListCourse,mContext);

            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            mProgressSpinner.setVisibility(View.GONE);

            map2.put("id", detailListCourseCat.get(0).getmCourseCategoryId());
            map2.put("name", detailListCourseCat.get(0).getmCourseCategoryNameEn());
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

            return data;
        }

        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);

            setRecyclerView2();

            adapter2=new RecyclerViewAdapterCategory2(detailListCourse,mContext);

            recyclerView2.setAdapter(adapter2);
            adapter2.notifyDataSetChanged();

            mProgressSpinner2.setVisibility(View.GONE);

            map3.put("id", detailListCourseCat.get(1).getmCourseCategoryId());
            map3.put("name", detailListCourseCat.get(1).getmCourseCategoryNameEn());
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

            return data;
        }

        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);

            setRecyclerView3();

            adapter3=new RecyclerViewAdapterCategory3(detailListCourse,mContext);

            recyclerView3.setAdapter(adapter3);
            adapter3.notifyDataSetChanged();

            mProgressSpinner3.setVisibility(View.GONE);

            map4.put("id", detailListCourseCat.get(2).getmCourseCategoryId());
            map4.put("name", detailListCourseCat.get(2).getmCourseCategoryNameEn());
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

            return data;
        }

        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);

            setRecyclerView4();

            adapter4=new RecyclerViewAdapterCategory1(detailListCourse,mContext);

            recyclerView4.setAdapter(adapter4);
            adapter4.notifyDataSetChanged();

            mProgressSpinner4.setVisibility(View.GONE);

            map5.put("id", detailListCourseCat.get(3).getmCourseCategoryId());
            map5.put("name", detailListCourseCat.get(3).getmCourseCategoryNameEn());
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

            return data;
        }

        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);

            setRecyclerView5();

            adapter5=new RecyclerViewAdapterCategory1(detailListCourse,mContext);

            recyclerView5.setAdapter(adapter5);
            adapter5.notifyDataSetChanged();

            mProgressSpinner5.setVisibility(View.GONE);

            try {
                map6.put("id", detailListCourseCat.get(4).getmCourseCategoryId());
                map6.put("name", detailListCourseCat.get(4).getmCourseCategoryNameEn());
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

            return data;
        }

        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);

            setRecyclerView6();

            adapter6=new RecyclerViewAdapterCategory1(detailListCourse,mContext);

            recyclerView6.setAdapter(adapter6);
            adapter6.notifyDataSetChanged();

            mProgressSpinner6.setVisibility(View.GONE);

            try {
                map7.put("id", detailListCourseCat.get(5).getmCourseCategoryId());
                map7.put("name", detailListCourseCat.get(5).getmCourseCategoryNameEn());
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

            return data;
        }

        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);

            setRecyclerView7();

            adapter7=new RecyclerViewAdapterCategory1(detailListCourse,mContext);

            recyclerView7.setAdapter(adapter7);
            adapter7.notifyDataSetChanged();

            mProgressSpinner7.setVisibility(View.GONE);

            try {
                map8.put("id", detailListCourseCat.get(6).getmCourseCategoryId());
                map8.put("name", detailListCourseCat.get(6).getmCourseCategoryNameEn());
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

            return data;
        }

        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);

            setRecyclerView8();

            adapter8=new RecyclerViewAdapterCategory1(detailListCourse,mContext);

            recyclerView8.setAdapter(adapter8);
            adapter8.notifyDataSetChanged();

            mProgressSpinner8.setVisibility(View.GONE);

            try {
                map9.put("id", detailListCourseCat.get(7).getmCourseCategoryId());
                map9.put("name", detailListCourseCat.get(7).getmCourseCategoryNameEn());
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

            return data;
        }

        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);

            setRecyclerView9();

            adapter9=new RecyclerViewAdapterCategory1(detailListCourse,mContext);

            recyclerView9.setAdapter(adapter9);
            adapter9.notifyDataSetChanged();

            mProgressSpinner9.setVisibility(View.GONE);

            try {
                map10.put("id", detailListCourseCat.get(8).getmCourseCategoryId());
                map10.put("name", detailListCourseCat.get(8).getmCourseCategoryNameEn());
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

            return data;
        }

        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);

            setRecyclerView10();

            adapter10=new RecyclerViewAdapterCategory1(detailListCourse,mContext);

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
