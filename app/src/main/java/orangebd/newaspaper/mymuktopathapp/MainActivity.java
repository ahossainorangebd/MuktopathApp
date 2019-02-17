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

    private ArrayList<DetailDataModelCourses> detailList2parent;
    private ArrayList<DetailDataModelCourses3rdGrandFather> detailList3parent;

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

    String url="http://api.muktopaath.orangebd.com/api/courses";

    private HashMap<String,String> map;

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




        map = new HashMap<String, String>();

        JSONObject object=new JSONObject();

        try {
            object.put("username", "");
            object.put("featured", "");
            object.put("upcomming", "" );
            object.put("favorite", "" );
            object.put("admin_featured", "" );
            object.put("order", "" );
            object.put("payment_status", "" );
            object.put("price_search", "" );
            object.put("price_start", "" );
            object.put("price_end", "" );
            object.put("duration_search", "" );
            object.put("duration_start", "" );
            object.put("limit", "30" );
            object.put("duration_search", "" );
        }
        catch (Exception ex){ }

        RequestQueue mQueue = Volley.newRequestQueue(getApplicationContext());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, object,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        //  for parsing grand-parent "data"

                        detailListCourse=new ArrayList<DetailDataModelCourses>();

                        detailListCourseThumbnail=new ArrayList<DetailDataModelCoursesThumbnails>();


                        detailListCourseDetailContents=new ArrayList<DetailDataModelCoursesDetailContents>();

                        GlobalVar.courseContentDetailList=new ArrayList<>();

                        try {

                            JSONArray object = (JSONArray) response.get("data");

                            for (int i=0;i<object.length();i++)
                            {

                                JSONObject object2 = (JSONObject) object.get(i);

                                DetailDataModelCourses model = new DetailDataModelCourses();

                                String featured = object2.getString("featured");
                                String limit = object2.getString("limit");
                                String migration_allowe = object2.getString("migration_allowe");
                                String migration_fee = object2.getString("migration_fee");
                                String mig_payment_amount = object2.getString("mig_payment_amount");
                                String mig_pa_status = object2.getString("mig_pa_status");
                                String objective = object2.getString("objective");
                                String payment_point_amount = object2.getString("payment_point_amount");
                                String payment_point_status = object2.getString("payment_point_status");
                                String payment_status = object2.getString("payment_status");
                                String reg_end_date = object2.getString("reg_end_date");
                                String reg_start_date = object2.getString("reg_start_date");
                                String requirement = object2.getString("requirement");
                                String start_date = object2.getString("start_date");
                                String status = object2.getString("status");
                                String totalEnroll = object2.getString("totalEnroll");
                                String updated_at = object2.getString("updated_at");
                                String Eid = object2.getString("id");
                                String Etitle = object2.getString("title");
                                String Edetails = object2.getString("details");
                                String Eadmission_status = object2.getString("admission_status");
                                String averageRating = object2.getString("averageRating");
                                String certificate_alias_name = object2.getString("certificate_alias_name");
                                String clone_status = object2.getString("clone_status");
                                String code = object2.getString("code");
                                String courses_for_status = object2.getString("courses_for_status");
                                String course_alias_name = object2.getString("course_alias_name");
                                String course_motto = object2.getString("course_motto");
                                String created_at = object2.getString("created_at");
                                String duration = object2.getString("duration");
                                String end_date = object2.getString("end_date");
                                String enrolment_approval_status = object2.getString("enrolment_approval_status");

                                model.setmCertificateAliasName(certificate_alias_name);
                                model.setmAdmissionStatus(Eadmission_status);
                                model.setmAverageRating(averageRating);
                                model.setmCloneStatus(clone_status);
                                model.setmCode(code);
                                model.setmCreatedAt(created_at);
                                model.setmDuration(duration);
                                model.setmEndDate(end_date);
                                model.setmId(Eid);
                                model.setmTitle(Etitle);
                                model.setmDetails(Edetails);
                                model.setmLimit(limit);
                                model.setmFeatured(featured);
                                model.setmMigrationFee(migration_fee);
                                model.setmMigrationAllowe(migration_allowe);
                                model.setmMigPaymentAmount(mig_payment_amount);
                                model.setmPaymentPointAmount(payment_point_amount);
                                model.setmEnrolmentApprovalStatus(enrolment_approval_status);
                                model.setmPaymentPointStatus(payment_point_status);
                                model.setmCursesForStatus(courses_for_status);
                                model.setmCourseAliasName(course_alias_name);
                                model.setmRegStartDate(reg_start_date);
                                model.setmPaymentStatus(payment_status);
                                model.setmMigPaStatus(mig_pa_status);
                                model.setmRegEndDate(reg_end_date);
                                model.setmCourseMotto(course_motto);
                                model.setmRequirement(requirement);
                                model.setmStartDate(start_date);
                                model.setmTotalEnroll(totalEnroll);
                                model.setmUpdatedAt(updated_at);
                                model.setmObjective(objective);
                                model.setmStatus(status);

                                //  for parsing "syllebus" > "0" > "data"

                                detailList2=new ArrayList<DetailDataModelCourses>();
                                DetailDataModelCourses model2 = new DetailDataModelCourses();

                                JSONObject jObject = new JSONObject();

                                try {
                                    jObject = object2.getJSONObject("syllabus");


                                    for(int loop=0; loop<jObject.length(); loop++) {


                                        //for parsing syllebus strings

                                        detailList7 = new ArrayList<DetailDataModelCourses>();
                                        DetailDataModelCourses model7 = new DetailDataModelCourses();

                                        String study_mode_Syllebus = jObject.getString("study_mode");

                                        model7.setStudyModeSyllebus(study_mode_Syllebus);


                                        JSONObject jObjectCourse = object2.getJSONObject("course");

                                        //for parsing course strings

                                        detailList8 = new ArrayList<DetailDataModelCourses>();
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

                                        model.setmArrayListThumbnails(detailListCourseThumbnail);

                                        //for parsing Updated by strings
                                        JSONObject jObjectUpdatedBy = object2.getJSONObject("UpdatedBy");

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
                                        JSONObject jObjectOwner = object2.getJSONObject("owner");

                                        detailList10 = new ArrayList<DetailDataModelCourses>();
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
                                        JSONObject jObjectCreatedBy = object2.getJSONObject("CreatedBy");

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

                                        for (int ii = 0; ii < jObject.length(); ii++) {
                                            JSONObject jSObject2 = jObject.getJSONObject("" + ii);


                                        /*for(int j=0; j<jSObject2.length(); j++){
                                            JSONObject jSObject3 = jSObject2.getJSONObject(""+j);
                                        }*/


                                            //for parsing lessons > {0} > "syllebus" > "0" > "data"
                                            detailList6 = new ArrayList<DetailDataModelCourses>();

                                            DetailDataModelCourses model6 = new DetailDataModelCourses();

                                            try {
                                                for (int m = 0; m < jSObject2.length() - 1; m++) {
                                                    JSONArray jSonLessons = (JSONArray) jSObject2.get("lessons");
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


                                            for (int lmn = 0; lmn < jSObject2.length(); lmn++) {


                                                JSONObject jSObject3 = jSObject2.getJSONObject("" + lmn);
                                                JSONObject jObjAgain = jSObject3.getJSONObject("data");

                                                String allow_preview = jObjAgain.getString("allow_preview");
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
                                                String quiz = jObjAgain.getString("quiz");
                                                String time_unit = jObjAgain.getString("time_unit");
                                                String mTitle = jObjAgain.getString("title");

                                                model2.setmAllowPreview(allow_preview);
                                                model2.setmAnsRand(ans_rand);
                                                model2.setmAttempt(attempt);
                                                model2.setmChooseVideoType(choose_video_type);
                                                model2.setmContentType(content_type);
                                                model2.setmDesc(desc);
                                                model2.setmDownloadable(downloadable);
                                                model2.setmDurationAnother(mDuration);
                                                model2.setmForward(forward);
                                                model2.setmPeerLimit(peer_limit);
                                                model2.setmPeerReview(peer_review);
                                                model2.setmPulse(pulse);
                                                model2.setmQuesRand(ques_rand);
                                                model2.setmQuiz(quiz);
                                                model2.setmTimeUnit(time_unit);
                                                model2.setmTitleAnother(mTitle);


                                                // For parsing "file_type" > "data" > {0} > {0} > "syllebus" > "0" > "data"

                                                JSONObject jObjAgain2 = jObjAgain.getJSONObject("file_type");


                                                detailList3 = new ArrayList<DetailDataModelCourses>();
                                                DetailDataModelCourses model3 = new DetailDataModelCourses();

                                                String pdf = jObjAgain2.getString("pdf");
                                                String excel = jObjAgain2.getString("excel");
                                                String doc = jObjAgain2.getString("doc");
                                                String csv = jObjAgain2.getString("csv");

                                                model3.setmPdf(pdf);
                                                model3.setmXcel(excel);
                                                model3.setmDoc(doc);
                                                model3.setmCsv(csv);

                                                // For parsing object "Content" > {0} > {0} > "syllebus" > "0" > "data"

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

                                                detailListCourseDetailContents.add(modelCourseContents);

                                                /*model.setmArrayListContentDetails(detailListCourseDetailContents);*/
                                                model.setmArrayListContentDetails(detailListCourseDetailContentss);

                                                //For parsing array "multi_ques_list" > {0} > {0} > "syllebus" > "0" > "data"

                                                detailList5 = new ArrayList<DetailDataModelCourses>();

                                                DetailDataModelCourses model5 = new DetailDataModelCourses();

                                                try {
                                                    for (int l = 0; l < jSObject3.length() - 1; l++) {
                                                        JSONArray jSonObjMultiQ = (JSONArray) jSObject3.get("multi_ques_list");
                                                        JSONObject objectAgainAnother2 = (JSONObject) jSonObjMultiQ.get(l);

                                                        String mPulse = objectAgainAnother2.getString("pulse");

                                                        model5.setPulse(mPulse);


                                                    }
                                                } catch (Exception ex) {
                                                    Log.d("", "onResponse: ");
                                                }
                                            }


                                        }
                                    }


                                }
                                catch (Exception ex){
                                    Log.d("", "onResponse: ");
                                }

                                //parsing all direct strings of 2nd parent

                                detailList2parent=new ArrayList<DetailDataModelCourses>();
                                DetailDataModelCourses model2parent = new DetailDataModelCourses();

                                JSONObject ObjSecondParentStrings = (JSONObject) response.get("links");

                                String firstSecondParent = ObjSecondParentStrings.getString("first");
                                String lastSecondParent = ObjSecondParentStrings.getString("last");
                                String nextSecondParent = ObjSecondParentStrings.getString("next");
                                String prevSecondParent = ObjSecondParentStrings.getString("prev");

                                model2parent.setFirstSecondParent(firstSecondParent);
                                model2parent.setLastSecondParent(lastSecondParent);
                                model2parent.setNextSecondParent(nextSecondParent);
                                model2parent.setPrevSecondParent(prevSecondParent);


                                //parsing all direct strings of 3rd parent

                                detailList3parent=new ArrayList<DetailDataModelCourses3rdGrandFather>();
                                DetailDataModelCourses3rdGrandFather model3parent = new DetailDataModelCourses3rdGrandFather();

                                JSONObject ObjThirdParentStrings = (JSONObject) response.get("meta");

                                String current_page_meta = ObjThirdParentStrings.getString("current_page");
                                String from_meta = ObjThirdParentStrings.getString("from");
                                String last_page_meta = ObjThirdParentStrings.getString("last_page");
                                String path_meta = ObjThirdParentStrings.getString("path");
                                String per_page_meta = ObjThirdParentStrings.getString("per_page");
                                String to_meta = ObjThirdParentStrings.getString("to");
                                String total_meta = ObjThirdParentStrings.getString("total");

                                model3parent.setCurrent_page_metaThirdParent(current_page_meta);
                                model3parent.setFrom_metaThirdParent(from_meta);
                                model3parent.setLast_page_metaThirdParent(last_page_meta);
                                model3parent.setPath_metaThirdParent(path_meta);
                                model3parent.setLast_page_metaThirdParent(per_page_meta);
                                model3parent.setTotal_metaThirdParent(to_meta);
                                model3parent.setTotal_metaThirdParent(total_meta);


                                detailListCourse.add(model);


                            }

                            setRecyclerView();


                            GlobalVar.courseContentDetailList=detailListCourse;

                            /*setRecyclerView10();
                            adapter10=new RecyclerViewAdapterCategory10(detailListCourse,mContext);
                            recyclerView10.setAdapter(adapter10);
                            adapter10.notifyDataSetChanged();
                            mProgressSpinner10.setVisibility(View.GONE);*/

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
                params.put("Authorization", GlobalVar.gReplacingToken);
                return params;
            }
        };
        mQueue.add(jsonObjectRequest);



    }


    private void setRecyclerView() {
        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(layoutManager);

        //nestedScrollView.setSmoothScrollingEnabled(true);
        //nestedScrollView.fullScroll(View.FOCUS_UP);
        recyclerView.setNestedScrollingEnabled(false);

        new GetCategory1Courses().execute();
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

        //nestedScrollView.setSmoothScrollingEnabled(true);
        //nestedScrollView.fullScroll(View.FOCUS_UP);
        recyclerView5.setNestedScrollingEnabled(false);
    }

    private void setRecyclerView6() {
        recyclerView6 = findViewById(R.id.my_recycler_view6);
        recyclerView6.setHasFixedSize(true);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recyclerView6.setLayoutManager(layoutManager);

        //nestedScrollView.setSmoothScrollingEnabled(true);
        //nestedScrollView.fullScroll(View.FOCUS_UP);
        recyclerView6.setNestedScrollingEnabled(false);
    }

    private void setRecyclerView7() {
        recyclerView7 = findViewById(R.id.my_recycler_view7);
        recyclerView7.setHasFixedSize(true);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recyclerView7.setLayoutManager(layoutManager);

        //nestedScrollView.setSmoothScrollingEnabled(true);
        //nestedScrollView.fullScroll(View.FOCUS_UP);
        recyclerView7.setNestedScrollingEnabled(false);
    }

    private void setRecyclerView8() {
        recyclerView8 = findViewById(R.id.my_recycler_view8);
        recyclerView8.setHasFixedSize(true);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recyclerView8.setLayoutManager(layoutManager);

        //nestedScrollView.setSmoothScrollingEnabled(true);
        //nestedScrollView.fullScroll(View.FOCUS_UP);
        recyclerView8.setNestedScrollingEnabled(false);
    }

    private void setRecyclerView9() {
        recyclerView9 = findViewById(R.id.my_recycler_view9);
        recyclerView9.setHasFixedSize(true);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recyclerView9.setLayoutManager(layoutManager);


        //nestedScrollView.setSmoothScrollingEnabled(true);
        //nestedScrollView.fullScroll(View.FOCUS_UP);
        recyclerView9.setNestedScrollingEnabled(false);
    }

    private void setRecyclerView10() {
        recyclerView10 = findViewById(R.id.my_recycler_view10);
        recyclerView10.setHasFixedSize(true);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recyclerView10.setLayoutManager(layoutManager);

        //nestedScrollView.setSmoothScrollingEnabled(true);
        //nestedScrollView.fullScroll(View.FOCUS_UP);
        recyclerView10.setNestedScrollingEnabled(false);
    }



    public class GetCategory1Courses extends AsyncTask<String, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mProgressSpinner.setIndeterminate(true);
            mProgressSpinner.setVisibility(View.VISIBLE);
            //Toast.makeText(MainActivity.this,"Detail data is downloading...",Toast.LENGTH_LONG).show();
        }

        @Override
        protected Void doInBackground(String... arg0) {

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            adapter=new RecyclerViewAdapterCategory1(detailListCourse,mContext);
            //
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            mProgressSpinner.setVisibility(View.GONE);

            new GetCategory2Courses().execute();
        }
    }

    public class GetCategory2Courses extends AsyncTask<String, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mProgressSpinner2.setIndeterminate(true);
            mProgressSpinner2.setVisibility(View.VISIBLE);
            //Toast.makeText(MainActivity.this,"Detail data is downloading...",Toast.LENGTH_LONG).show();
        }

        @Override
        protected Void doInBackground(String... arg0) {
            /*HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = arg0[0];
            String jsonStr = sh.makeServiceCall(url);

            //Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) try {
                JSONObject jsonObj = new JSONObject(jsonStr);

                detailList=new ArrayList<DetailDataModel>();
                try {
                    for (int i=0;i<jsonObj.length()-1;i++)
                    {

                        JSONObject object = (JSONObject) jsonObj.get("" + i);
                        //Iterator<String> temp = object.keys();
                        //while (temp.hasNext()) {
                        DetailDataModel model = new DetailDataModel();
                        //String dynamicKey = (String) temp.next();

                        String hl1 = object.getString("hl1");
                        String hl2 = object.getString("hl2");
                        String img_url = object.getString("img_url");
                        String detail = object.getString("dtl");
                        String parentCatID=object.getString("parent_cat_id");
                        String update_time=object.getString("update_time");
                        String entry_time = object.getString("entry_time");
                        String rpt=object.getString("rpt");
                        String detaillLink=object.getString("dtl_url");
                        String image_caption_name = object.getString("img_caption");
                        model.setImg_caption(image_caption_name);
                        model.setDtl_url_link(detaillLink);
                        model.setEntry_time(entry_time);
                        model.setUpdate_time(update_time);
                        model.setRpt(rpt);
                        model.setParent_cat_id(parentCatID);
                        model.setHl1(hl1);
                        model.setHl2(hl2);
                        model.setImg_url(img_url);
                        model.setDtl_url(detail);

                        detailList.add(model);
                        publishProgress();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } catch (final JSONException e) {
                //Log.e(TAG, "Json parsing error: " + e.getMessage());
                Log.e("tag", "Couldn't get json from server.");

            }
            else {
                Log.e("tag", "Couldn't get json from server.");
            }*/

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            setRecyclerView2();

            adapter2=new RecyclerViewAdapterCategory2(detailListCourse,mContext);
            //
            recyclerView2.setAdapter(adapter2);
            adapter2.notifyDataSetChanged();

            mProgressSpinner2.setVisibility(View.GONE);

            new GetCategory3Courses().execute();
        }
    }

    public class GetCategory3Courses extends AsyncTask<String, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mProgressSpinner3.setIndeterminate(true);
            mProgressSpinner3.setVisibility(View.VISIBLE);
            //Toast.makeText(MainActivity.this,"Detail data is downloading...",Toast.LENGTH_LONG).show();
        }

        @Override
        protected Void doInBackground(String... arg0) {
            /*HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = arg0[0];
            String jsonStr = sh.makeServiceCall(url);

            //Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) try {
                JSONObject jsonObj = new JSONObject(jsonStr);
                detailList=new ArrayList<DetailDataModel>();
                try {
                    for (int i=0;i<jsonObj.length()-1;i++)
                    {

                        JSONObject object = (JSONObject) jsonObj.get("" + i);
                        //Iterator<String> temp = object.keys();
                        //while (temp.hasNext()) {
                        DetailDataModel model = new DetailDataModel();
                        //String dynamicKey = (String) temp.next();

                        String hl1 = object.getString("hl1");
                        String hl2 = object.getString("hl2");
                        String img_url = object.getString("img_url");
                        String detail = object.getString("dtl");
                        String parentCatID=object.getString("parent_cat_id");
                        String update_time=object.getString("update_time");
                        String entry_time = object.getString("entry_time");
                        String rpt=object.getString("rpt");
                        String detaillLink=object.getString("dtl_url");
                        String image_caption_name = object.getString("img_caption");
                        model.setImg_caption(image_caption_name);
                        model.setDtl_url_link(detaillLink);
                        model.setEntry_time(entry_time);
                        model.setUpdate_time(update_time);
                        model.setRpt(rpt);
                        model.setParent_cat_id(parentCatID);
                        model.setHl1(hl1);
                        model.setHl2(hl2);
                        model.setImg_url(img_url);
                        model.setDtl_url(detail);

                        detailList.add(model);
                        publishProgress();
                    }

                }
                catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            catch (final JSONException e) {
                //Log.e(TAG, "Json parsing error: " + e.getMessage());
                Log.e("tag", "Couldn't get json from server.");

            }
            else {
                Log.e("tag", "Couldn't get json from server.");
            }*/

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            setRecyclerView3();

            adapter3=new RecyclerViewAdapterCategory3(detailListCourse,mContext);
            //
            recyclerView3.setAdapter(adapter3);
            adapter3.notifyDataSetChanged();

            mProgressSpinner3.setVisibility(View.GONE);

            new GetCategory4Courses().execute();
        }
    }

    public class GetCategory4Courses extends AsyncTask<String, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mProgressSpinner4.setIndeterminate(true);
            mProgressSpinner4.setVisibility(View.VISIBLE);
            //Toast.makeText(MainActivity.this,"Detail data is downloading...",Toast.LENGTH_LONG).show();
        }

        @Override
        protected Void doInBackground(String... arg0) {
            /*HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = arg0[0];
            String jsonStr = sh.makeServiceCall(url);

            //Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) try {
                JSONObject jsonObj = new JSONObject(jsonStr);
                detailList=new ArrayList<DetailDataModel>();
                try {
                    for (int i=0;i<jsonObj.length()-1;i++)
                    {

                        JSONObject object = (JSONObject) jsonObj.get("" + i);
                        //Iterator<String> temp = object.keys();
                        //while (temp.hasNext()) {
                        DetailDataModel model = new DetailDataModel();
                        //String dynamicKey = (String) temp.next();

                        String hl1 = object.getString("hl1");
                        String hl2 = object.getString("hl2");
                        String img_url = object.getString("img_url");
                        String detail = object.getString("dtl");
                        String parentCatID=object.getString("parent_cat_id");
                        String update_time=object.getString("update_time");
                        String entry_time = object.getString("entry_time");
                        String rpt=object.getString("rpt");
                        String detaillLink=object.getString("dtl_url");
                        String image_caption_name = object.getString("img_caption");
                        model.setImg_caption(image_caption_name);
                        model.setDtl_url_link(detaillLink);
                        model.setEntry_time(entry_time);
                        model.setUpdate_time(update_time);
                        model.setRpt(rpt);
                        model.setParent_cat_id(parentCatID);
                        model.setHl1(hl1);
                        model.setHl2(hl2);
                        model.setImg_url(img_url);
                        model.setDtl_url(detail);

                        detailList.add(model);
                        publishProgress();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } catch (final JSONException e) {
                //Log.e(TAG, "Json parsing error: " + e.getMessage());
                Log.e("tag", "Couldn't get json from server.");

            }
            else {
                Log.e("tag", "Couldn't get json from server.");
            }*/

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            setRecyclerView4();

            adapter4=new RecyclerViewAdapterCategory4(detailListCourse,mContext);
            //
            recyclerView4.setAdapter(adapter4);
            adapter4.notifyDataSetChanged();

            mProgressSpinner4.setVisibility(View.GONE);

            new GetCategory5Courses().execute();
        }
    }

    public class GetCategory5Courses extends AsyncTask<String, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mProgressSpinner5.setIndeterminate(true);
            mProgressSpinner5.setVisibility(View.VISIBLE);
            //Toast.makeText(MainActivity.this,"Detail data is downloading...",Toast.LENGTH_LONG).show();
        }

        @Override
        protected Void doInBackground(String... arg0) {
            /*HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = arg0[0];
            String jsonStr = sh.makeServiceCall(url);

            //Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) try {
                JSONObject jsonObj = new JSONObject(jsonStr);
                detailList=new ArrayList<DetailDataModel>();
                try {
                    for (int i=0;i<jsonObj.length()-1;i++)
                    {

                        JSONObject object = (JSONObject) jsonObj.get("" + i);
                        //Iterator<String> temp = object.keys();
                        //while (temp.hasNext()) {
                        DetailDataModel model = new DetailDataModel();
                        //String dynamicKey = (String) temp.next();

                        String hl1 = object.getString("hl1");
                        String hl2 = object.getString("hl2");
                        String img_url = object.getString("img_url");
                        String detail = object.getString("dtl");
                        String parentCatID=object.getString("parent_cat_id");
                        String update_time=object.getString("update_time");
                        String entry_time = object.getString("entry_time");
                        String rpt=object.getString("rpt");
                        String detaillLink=object.getString("dtl_url");
                        String image_caption_name = object.getString("img_caption");
                        model.setImg_caption(image_caption_name);
                        model.setDtl_url_link(detaillLink);
                        model.setEntry_time(entry_time);
                        model.setUpdate_time(update_time);
                        model.setRpt(rpt);
                        model.setParent_cat_id(parentCatID);
                        model.setHl1(hl1);
                        model.setHl2(hl2);
                        model.setImg_url(img_url);
                        model.setDtl_url(detail);

                        detailList.add(model);
                        publishProgress();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } catch (final JSONException e) {
                //Log.e(TAG, "Json parsing error: " + e.getMessage());
                Log.e("tag", "Couldn't get json from server.");

            }
            else {
                Log.e("tag", "Couldn't get json from server.");
            }*/

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            setRecyclerView5();

            adapter5=new RecyclerViewAdapterCategory5(detailListCourse,mContext);
            //
            recyclerView5.setAdapter(adapter5);
            adapter5.notifyDataSetChanged();

            mProgressSpinner5.setVisibility(View.GONE);

            new GetCategory6Courses().execute();
        }
    }

    public class GetCategory6Courses extends AsyncTask<String, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mProgressSpinner6.setIndeterminate(true);
            mProgressSpinner6.setVisibility(View.VISIBLE);
            //Toast.makeText(MainActivity.this,"Detail data is downloading...",Toast.LENGTH_LONG).show();
        }

        @Override
        protected Void doInBackground(String... arg0) {
            /*HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = arg0[0];
            String jsonStr = sh.makeServiceCall(url);

            //Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) try {
                JSONObject jsonObj = new JSONObject(jsonStr);
                detailList=new ArrayList<DetailDataModel>();
                try {
                    for (int i=0;i<jsonObj.length()-1;i++)
                    {

                        JSONObject object = (JSONObject) jsonObj.get("" + i);
                        //Iterator<String> temp = object.keys();
                        //while (temp.hasNext()) {
                        DetailDataModel model = new DetailDataModel();
                        //String dynamicKey = (String) temp.next();

                        String hl1 = object.getString("hl1");
                        String hl2 = object.getString("hl2");
                        String img_url = object.getString("img_url");
                        String detail = object.getString("dtl");
                        String parentCatID=object.getString("parent_cat_id");
                        String update_time=object.getString("update_time");
                        String entry_time = object.getString("entry_time");
                        String rpt=object.getString("rpt");
                        String detaillLink=object.getString("dtl_url");
                        String image_caption_name = object.getString("img_caption");
                        model.setImg_caption(image_caption_name);
                        model.setDtl_url_link(detaillLink);
                        model.setEntry_time(entry_time);
                        model.setUpdate_time(update_time);
                        model.setRpt(rpt);
                        model.setParent_cat_id(parentCatID);
                        model.setHl1(hl1);
                        model.setHl2(hl2);
                        model.setImg_url(img_url);
                        model.setDtl_url(detail);

                        detailList.add(model);
                        publishProgress();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } catch (final JSONException e) {
                //Log.e(TAG, "Json parsing error: " + e.getMessage());
                Log.e("tag", "Couldn't get json from server.");

            }
            else {
                Log.e("tag", "Couldn't get json from server.");
            }*/

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            setRecyclerView6();

            adapter6=new RecyclerViewAdapterCategory6(detailListCourse,mContext);
            //
            recyclerView6.setAdapter(adapter6);
            adapter6.notifyDataSetChanged();

            mProgressSpinner6.setVisibility(View.GONE);

            new GetCategory7Courses().execute();
        }
    }

    public class GetCategory7Courses extends AsyncTask<String, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mProgressSpinner7.setIndeterminate(true);
            mProgressSpinner7.setVisibility(View.VISIBLE);
            //Toast.makeText(MainActivity.this,"Detail data is downloading...",Toast.LENGTH_LONG).show();
        }

        @Override
        protected Void doInBackground(String... arg0) {
            /*HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = arg0[0];
            String jsonStr = sh.makeServiceCall(url);

            //Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) try {
                JSONObject jsonObj = new JSONObject(jsonStr);
                detailList=new ArrayList<DetailDataModel>();
                try {
                    for (int i=0;i<jsonObj.length()-1;i++)
                    {

                        JSONObject object = (JSONObject) jsonObj.get("" + i);
                        //Iterator<String> temp = object.keys();
                        //while (temp.hasNext()) {
                        DetailDataModel model = new DetailDataModel();
                        //String dynamicKey = (String) temp.next();

                        String hl1 = object.getString("hl1");
                        String hl2 = object.getString("hl2");
                        String img_url = object.getString("img_url");
                        String detail = object.getString("dtl");
                        String parentCatID=object.getString("parent_cat_id");
                        String update_time=object.getString("update_time");
                        String entry_time = object.getString("entry_time");
                        String rpt=object.getString("rpt");
                        String detaillLink=object.getString("dtl_url");
                        String image_caption_name = object.getString("img_caption");
                        model.setImg_caption(image_caption_name);
                        model.setDtl_url_link(detaillLink);
                        model.setEntry_time(entry_time);
                        model.setUpdate_time(update_time);
                        model.setRpt(rpt);
                        model.setParent_cat_id(parentCatID);
                        model.setHl1(hl1);
                        model.setHl2(hl2);
                        model.setImg_url(img_url);
                        model.setDtl_url(detail);

                        detailList.add(model);
                        publishProgress();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } catch (final JSONException e) {
                //Log.e(TAG, "Json parsing error: " + e.getMessage());
                Log.e("tag", "Couldn't get json from server.");

            }
            else {
                Log.e("tag", "Couldn't get json from server.");
            }*/

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            setRecyclerView7();

            adapter7=new RecyclerViewAdapterCategory7(detailListCourse,mContext);
            //
            recyclerView7.setAdapter(adapter7);
            adapter7.notifyDataSetChanged();

            mProgressSpinner7.setVisibility(View.GONE);

            new GetCategory8Courses().execute();
        }
    }

    public class GetCategory8Courses extends AsyncTask<String, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mProgressSpinner8.setIndeterminate(true);
            mProgressSpinner8.setVisibility(View.VISIBLE);
            //Toast.makeText(MainActivity.this,"Detail data is downloading...",Toast.LENGTH_LONG).show();
        }

        @Override
        protected Void doInBackground(String... arg0) {
            /*HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = arg0[0];
            String jsonStr = sh.makeServiceCall(url);

            //Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) try {
                JSONObject jsonObj = new JSONObject(jsonStr);
                detailList=new ArrayList<DetailDataModel>();
                try {
                    for (int i=0;i<jsonObj.length()-1;i++)
                    {

                        JSONObject object = (JSONObject) jsonObj.get("" + i);
                        //Iterator<String> temp = object.keys();
                        //while (temp.hasNext()) {
                        DetailDataModel model = new DetailDataModel();
                        //String dynamicKey = (String) temp.next();

                        String hl1 = object.getString("hl1");
                        String hl2 = object.getString("hl2");
                        String img_url = object.getString("img_url");
                        String detail = object.getString("dtl");
                        String parentCatID=object.getString("parent_cat_id");
                        String update_time=object.getString("update_time");
                        String entry_time = object.getString("entry_time");
                        String rpt=object.getString("rpt");
                        String detaillLink=object.getString("dtl_url");
                        String image_caption_name = object.getString("img_caption");
                        model.setImg_caption(image_caption_name);
                        model.setDtl_url_link(detaillLink);
                        model.setEntry_time(entry_time);
                        model.setUpdate_time(update_time);
                        model.setRpt(rpt);
                        model.setParent_cat_id(parentCatID);
                        model.setHl1(hl1);
                        model.setHl2(hl2);
                        model.setImg_url(img_url);
                        model.setDtl_url(detail);

                        detailList.add(model);
                        publishProgress();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } catch (final JSONException e) {
                //Log.e(TAG, "Json parsing error: " + e.getMessage());
                Log.e("tag", "Couldn't get json from server.");

            }
            else {
                Log.e("tag", "Couldn't get json from server.");
            }*/

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            setRecyclerView8();

            adapter8=new RecyclerViewAdapterCategory8(detailListCourse,mContext);
            //
            recyclerView8.setAdapter(adapter8);
            adapter8.notifyDataSetChanged();

            mProgressSpinner8.setVisibility(View.GONE);

            new GetCategory9Courses().execute();
        }
    }

    public class GetCategory9Courses extends AsyncTask<String, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mProgressSpinner9.setIndeterminate(true);
            mProgressSpinner9.setVisibility(View.VISIBLE);
            //Toast.makeText(MainActivity.this,"Detail data is downloading...",Toast.LENGTH_LONG).show();
        }

        @Override
        protected Void doInBackground(String... arg0) {
            /*HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = arg0[0];
            String jsonStr = sh.makeServiceCall(url);

            //Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) try {
                JSONObject jsonObj = new JSONObject(jsonStr);
                detailList=new ArrayList<DetailDataModel>();
                try {
                    for (int i=0;i<jsonObj.length()-1;i++)
                    {

                        JSONObject object = (JSONObject) jsonObj.get("" + i);
                        //Iterator<String> temp = object.keys();
                        //while (temp.hasNext()) {
                        DetailDataModel model = new DetailDataModel();
                        //String dynamicKey = (String) temp.next();

                        String hl1 = object.getString("hl1");
                        String hl2 = object.getString("hl2");
                        String img_url = object.getString("img_url");
                        String detail = object.getString("dtl");
                        String parentCatID=object.getString("parent_cat_id");
                        String update_time=object.getString("update_time");
                        String entry_time = object.getString("entry_time");
                        String rpt=object.getString("rpt");
                        String detaillLink=object.getString("dtl_url");
                        String image_caption_name = object.getString("img_caption");
                        model.setImg_caption(image_caption_name);
                        model.setDtl_url_link(detaillLink);
                        model.setEntry_time(entry_time);
                        model.setUpdate_time(update_time);
                        model.setRpt(rpt);
                        model.setParent_cat_id(parentCatID);
                        model.setHl1(hl1);
                        model.setHl2(hl2);
                        model.setImg_url(img_url);
                        model.setDtl_url(detail);

                        detailList.add(model);
                        publishProgress();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } catch (final JSONException e) {
                //Log.e(TAG, "Json parsing error: " + e.getMessage());
                Log.e("tag", "Couldn't get json from server.");

            }
            else {
                Log.e("tag", "Couldn't get json from server.");
            }
            */

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            setRecyclerView9();

            adapter9=new RecyclerViewAdapterCategory9(detailListCourse,mContext);
            //
            recyclerView9.setAdapter(adapter9);
            adapter9.notifyDataSetChanged();

            mProgressSpinner9.setVisibility(View.GONE);

            new GetCategory10Courses().execute();
        }
    }

    public class GetCategory10Courses extends AsyncTask<String, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mProgressSpinner10.setIndeterminate(true);
            mProgressSpinner10.setVisibility(View.VISIBLE);
            //Toast.makeText(MainActivity.this,"Detail data is downloading...",Toast.LENGTH_LONG).show();
        }

        @Override
        protected Void doInBackground(String... arg0) {
            /*HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = arg0[0];
            String jsonStr = sh.makeServiceCall(url);

            //Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) try {
                JSONObject jsonObj = new JSONObject(jsonStr);
                detailList=new ArrayList<DetailDataModel>();
                try {
                    for (int i=0;i<jsonObj.length()-1;i++)
                    {

                        JSONObject object = (JSONObject) jsonObj.get("" + i);
                        //Iterator<String> temp = object.keys();
                        //while (temp.hasNext()) {
                        DetailDataModel model = new DetailDataModel();
                        //String dynamicKey = (String) temp.next();

                        String hl1 = object.getString("hl1");
                        String hl2 = object.getString("hl2");
                        String img_url = object.getString("img_url");
                        String detail = object.getString("dtl");
                        String parentCatID=object.getString("parent_cat_id");
                        String update_time=object.getString("update_time");
                        String entry_time = object.getString("entry_time");
                        String rpt=object.getString("rpt");
                        String detaillLink=object.getString("dtl_url");
                        String image_caption_name = object.getString("img_caption");
                        model.setImg_caption(image_caption_name);
                        model.setDtl_url_link(detaillLink);
                        model.setEntry_time(entry_time);
                        model.setUpdate_time(update_time);
                        model.setRpt(rpt);
                        model.setParent_cat_id(parentCatID);
                        model.setHl1(hl1);
                        model.setHl2(hl2);
                        model.setImg_url(img_url);
                        model.setDtl_url(detail);

                        detailList.add(model);
                        publishProgress();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } catch (final JSONException e) {
                //Log.e(TAG, "Json parsing error: " + e.getMessage());
                Log.e("tag", "Couldn't get json from server.");

            }
            else {
                Log.e("tag", "Couldn't get json from server.");
            }*/

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            setRecyclerView10();

            adapter10=new RecyclerViewAdapterCategory10(detailListCourse,mContext);
            //
            recyclerView10.setAdapter(adapter10);
            adapter10.notifyDataSetChanged();

            mProgressSpinner10.setVisibility(View.GONE);
        }
    }
}
