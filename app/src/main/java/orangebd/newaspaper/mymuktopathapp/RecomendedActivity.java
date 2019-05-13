package orangebd.newaspaper.mymuktopathapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.provider.SyncStateContract;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RecomendedActivity extends AppCompatActivity {

    private static RecyclerView recyclerView;
    private ProgressBar mProgressSpinner;
    private RecyclerView.Adapter adapter;

    private Context mContext;

    // Button Layout of footer

    private LinearLayout allCourseBtn;
    private LinearLayout recomendedBtn;
    private LinearLayout myPageBtn;
    private LinearLayout downloadsBtn;
    private LinearLayout profileBtn;

    private HashMap<String,String> map;

    private ArrayList<DetailDataModelCourses> detailListCourse;
    private ArrayList<DetailDataModelCourses> detailList2;
    private ArrayList<DetailDataModelCourses> detailList3;
    private ArrayList<DetailDataModelCourses> detailList4;
    private ArrayList<DetailDataModelCourses> detailList5;
    private ArrayList<DetailDataModelCourses> detailList6;
    private ArrayList<DetailDataModelCourses> detailList7;
    private ArrayList<DetailDataModelCourses> detailList8;
    private ArrayList<DetailDataModelCourses> detailList9;
    private ArrayList<DetailDataModelCourses> detailList10;
    private ArrayList<DetailDataModelCourses> detailList11;
    private ArrayList<DetailDataModelCoursesThumbnails> detailListCourseThumbnail;
    private ArrayList<DetailDataModelCoursesDetailContents> detailListCourseDetailContents;
    private ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> detailListCourseDetailContentss;

    private RecyclerView.Adapter adapterCats;

    private ArrayList<DetailDataModelCourses> detailList2parent;
    private ArrayList<DetailDataModelCourses3rdGrandFather> detailList3parent;

    private RecyclerView recyclerViewCat;

    private HashMap<String,String> map1;
    private HashMap<String,String> map2;
    private HashMap<String,String> map3;
    private HashMap<String,String> map4;
    private HashMap<String,String> map5;

    //For category1
    private ArrayList<DetailDataModelCourses> detailListMainActivityCourse=new ArrayList<>();

    private ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> detailListMainActivityCourseDetailContentss;
    private ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> detailListMainActivityCourseDetailUnits;
    private ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> detailListMainActivityCourseDetailUnitQuizList;

    private ArrayList<DetailDataModelCoursesThumbnails> detailListMainActivityCourseThumbnail;

    ArrayList<DetailDataModelCoursesDetailContents> mContentArrayListNew;
    ArrayList<DetailDataModelCoursesDetailContents> mUnitArrayListNew;
    ArrayList<DetailDataModelCoursesDetailContents> mUnitQuizList;


    private String firstRecomCatName="";
    private String secondRecomCatName="";
    private String thirdRecomCatName="";
    private String fourthRecomCatName="";
    private String fifthRecomCatName="";
    private String sixthRecomCatName="";
    private String seventhRecomCatName="";
    private String eighthRecomCatName="";

    private String firstRecomCatId="";
    private String secondRecomCatId="";
    private String thirdRecomCatId="";
    private String fourthRecomCatId="";
    private String fifthRecomCatId="";
    private String sixthRecomCatId="";
    private String seventhRecomCatId="";
    private String eighthRecomCatId="";

    //String url= GlobalVar.gApiBaseUrl + "/api/courses";
    String url= GlobalVar.gApiBaseUrl + "/api/course/search";


    String urlGetCourseCats = GlobalVar.gApiBaseUrl + "/api/course-categories";



    //All the detail Lists
    private ArrayList<DetailDataModelAll> detailListMainActivityCourseCat;

    private ArrayList<String> RecommendedCategoriesId = new ArrayList<>();
    private ArrayList<String> RecommendedCategoriesEn = new ArrayList<>();
    private ArrayList<String> RecommendedCategoriesBn = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomended);

        mContext=this;

        //View view = LayoutInflater.from(mContext).inflate(R.layout.custom_logodetails, null, false);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().hide();
        //getSupportActionBar().setCustomView(view);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#7a19aa")));
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        View view2 = findViewById(android.R.id.content);
        Animation mLoadAnimation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in);
        mLoadAnimation.setDuration(1000);
        view2.startAnimation(mLoadAnimation);

        allCourseBtn = findViewById(R.id.allCourseBtnId);
        recomendedBtn = findViewById(R.id.recomendedBtnId);
        myPageBtn = findViewById(R.id.myPageBtnId);
        downloadsBtn = findViewById(R.id.downloadsBtnId);
        profileBtn = findViewById(R.id.profilePageBtnId);
        mProgressSpinner=findViewById(R.id.loadingSpinnerId);

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

        myPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GlobalVar.isRedirectFromProfileRunningBtn=false;
                GlobalVar.isRedirectFromProfileNonEndedBtn=false;
                GlobalVar.isRedirectFromProfileEndedBtn=false;
                GlobalVar.isRedirectFromProfileWishListBtn=false;

                Intent i=new Intent(mContext,MyPageActivity.class);
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


        // let's get all the category names and ids

        new GetCourseCategories().execute(urlGetCourseCats);



        recyclerViewCat = findViewById(R.id.my_recycler_view_recom_cats);
        recyclerViewCat.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCat.setLayoutManager(layoutManager);
        recyclerViewCat.setNestedScrollingEnabled(false);

        if(GlobalVar.gRecommendedCategories==null) {
            Intent i = new Intent(mContext, SelectACategoryActivity.class);
            startActivity(i);

            Toast.makeText(mContext, "Please select at least one category", Toast.LENGTH_LONG).show();

            return;
        }

        //TODO
        //TODO
        //JSONObject object=new JSONObject();

    }


    private void setRecyclerView() {

        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setNestedScrollingEnabled(false);

        new RecommendedCategory1().execute(url);
    }

    /*private void setRecyclerView2() {
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
*/



    public class RecommendedCategory1 extends AsyncTask<String, Void, String>
    {

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

            adapter=new RecyclerViewAdapterRecomTemp(detailListMainActivityCourse,mContext);

            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            mProgressSpinner.setVisibility(View.GONE);


            if(RecommendedCategoriesId.size()>1){


                if(firstRecomCatId.equalsIgnoreCase("")){
                    firstRecomCatName="12";
                }

                map2.put("id", secondRecomCatId);
                map2.put("name", secondRecomCatName);
                map2.put("rating", "");

                new GetRecomCategory2().execute(url);
            }

        }

        @Override
        protected void onCancelled() {

        }
    }

    public class GetRecomCategory2 extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //mProgressSpinner2.setIndeterminate(true);
            //mProgressSpinner2.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {

            String data= performPostCall(params[0],map2);


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

            return data;
        }

        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);

            adapter=new RecyclerViewAdapterRecomTemp(detailListMainActivityCourse,mContext);

            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

//            mProgressSpinner2.setVisibility(View.GONE);

            if(RecommendedCategoriesId.size()>2) {

                if(firstRecomCatId.equalsIgnoreCase("")){
                    firstRecomCatName="13";
                }

                map3.put("id", thirdRecomCatId);
                map3.put("name", thirdRecomCatName);
                map3.put("rating", "");

                new GetRecomCategory3().execute(url);
            }
        }

        @Override
        protected void onCancelled() {

        }
    }

    public class GetRecomCategory3 extends AsyncTask<String, Void, String>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //mProgressSpinner2.setIndeterminate(true);
            //mProgressSpinner2.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {

            String data= performPostCall(params[0],map3);


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

            return data;
        }

        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);

            adapter=new RecyclerViewAdapterRecomTemp(detailListMainActivityCourse,mContext);

            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

//            mProgressSpinner2.setVisibility(View.GONE);

            if(RecommendedCategoriesId.size()>3) {

                if(firstRecomCatId.equalsIgnoreCase("")){
                    firstRecomCatName="11";
                }

                map4.put("id", fourthRecomCatId);
                map4.put("name", fourthRecomCatName);
                map4.put("rating", "");

                new GetRecomCategory4().execute(url);
            }
        }

        @Override
        protected void onCancelled() {

        }
    }

    public class GetRecomCategory4 extends AsyncTask<String, Void, String>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //mProgressSpinner2.setIndeterminate(true);
            //mProgressSpinner2.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {

            String data= performPostCall(params[0],map4);


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

            return data;
        }

        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);

            adapter=new RecyclerViewAdapterRecomTemp(detailListMainActivityCourse,mContext);

            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

//            mProgressSpinner2.setVisibility(View.GONE);

            if(RecommendedCategoriesId.size()>4) {

                map5.put("id", fourthRecomCatId);
                map5.put("name", fourthRecomCatName);
                map5.put("rating", "");

                new GetRecomCategory5().execute(url);
            }
        }

        @Override
        protected void onCancelled() {

        }
    }

    public class GetRecomCategory5 extends AsyncTask<String, Void, String>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //mProgressSpinner2.setIndeterminate(true);
            //mProgressSpinner2.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {

            String data= performPostCall(params[0],map5);


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

            return data;
        }

        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);

            adapter=new RecyclerViewAdapterRecomTemp(detailListMainActivityCourse,mContext);

            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

//            mProgressSpinner2.setVisibility(View.GONE);

            /*if(RecommendedCategoriesId.size()>4) {

                map4.put("id", fourthRecomCatId);
                map4.put("name", fourthRecomCatName);
                map4.put("rating", "");

                new GetRecomCategory5().execute(url);
            }*/
        }

        @Override
        protected void onCancelled() {

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id==R.id.disablePushNotification) {
            Intent intent = new Intent();
            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.putExtra("app_package", getPackageName());
            intent.putExtra("app_uid", getApplicationInfo().uid);
            intent.putExtra("android.provider.extra.APP_PACKAGE", getPackageName());
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
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
        protected void onPostExecute(String result)
        {
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
                        String courseLogo = jObjEnrolledCourses.getString("image");

                        modelCourseCatAll.setmCourseCategoryId(courseId);
                        modelCourseCatAll.setmCourseCategoryNameEn(courseNameEn);
                        modelCourseCatAll.setmCourseCategoryNameBn(courseNameBn);
                        modelCourseCatAll.setmCourseCategoryLogo(courseLogo);

                        detailListMainActivityCourseCat.add(modelCourseCatAll);

                        String aaaa="";
                    }
                } catch (Exception ex) {
                    Log.d("", "onResponse: ");
                }

            }
            catch (Exception ex){
                Log.d("", "onPostExecute: ");
            }

            String qwqwqw2222="";



            for(int checkRecomId=0; checkRecomId<GlobalVar.gRecommendedCategories.size(); checkRecomId++)
            {

                String checkRecomIdStr = GlobalVar.gRecommendedCategories.get(checkRecomId);

                for(int checkEnRecom=0; checkEnRecom<detailListMainActivityCourseCat.size(); checkEnRecom++) {


                    String checkExistingId = detailListMainActivityCourseCat.get(checkEnRecom).getmCourseCategoryId();
                    String checkExistingEn = detailListMainActivityCourseCat.get(checkEnRecom).getmCourseCategoryNameEn();
                    String checkExistingBn = detailListMainActivityCourseCat.get(checkEnRecom).getmCourseCategoryNameBn();


                    if(checkExistingId.equalsIgnoreCase(checkRecomIdStr)){


                        RecommendedCategoriesId.add(checkExistingId);
                        RecommendedCategoriesEn.add(checkExistingEn);
                        RecommendedCategoriesBn.add(checkExistingBn);


                        String qwqwqw2="";
                    }
                }

            }


            String qwqwqw="";

            adapterCats=new RecyclerViewAdapterRecomCats(RecommendedCategoriesBn,mContext);
            recyclerViewCat.setAdapter(adapterCats);
            adapterCats.notifyDataSetChanged();

            map = new HashMap<String, String>();
            map1 = new HashMap<String, String>();
            map2 = new HashMap<String, String>();
            map3 = new HashMap<String, String>();
            map4 = new HashMap<String, String>();
            map5 = new HashMap<String, String>();



            try {
                firstRecomCatName = RecommendedCategoriesEn.get(0);
                secondRecomCatName = RecommendedCategoriesEn.get(1);
                thirdRecomCatName = RecommendedCategoriesEn.get(2);
                fourthRecomCatName = RecommendedCategoriesEn.get(3);
                fifthRecomCatName = RecommendedCategoriesEn.get(4);
                sixthRecomCatName = RecommendedCategoriesEn.get(5);
                seventhRecomCatName = RecommendedCategoriesEn.get(6);
                eighthRecomCatName = RecommendedCategoriesEn.get(7);
            }
            catch (Exception ex){
                Log.d("", "onCreate: ");
            }

            try {
                firstRecomCatId = RecommendedCategoriesId.get(0);
                secondRecomCatId = RecommendedCategoriesId.get(1);
                thirdRecomCatId = RecommendedCategoriesId.get(2);
                fourthRecomCatId = RecommendedCategoriesId.get(3);
                fifthRecomCatId = RecommendedCategoriesId.get(4);
                sixthRecomCatId = RecommendedCategoriesId.get(5);
                seventhRecomCatId = RecommendedCategoriesId.get(6);
                eighthRecomCatId = RecommendedCategoriesId.get(7);
            }
            catch (Exception ex){
                Log.d("", "onCreate: ");
            }


            try {
                if(firstRecomCatId.equalsIgnoreCase("")){
                    firstRecomCatName="11";
                }

                map1.put("id", firstRecomCatId);
                map1.put("name", firstRecomCatName);
                map1.put("rating", "" );
            }
            catch (Exception ex){ }


            setRecyclerView();

        }
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
}
