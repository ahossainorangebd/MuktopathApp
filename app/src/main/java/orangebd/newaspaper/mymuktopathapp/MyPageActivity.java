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

public class MyPageActivity extends AppCompatActivity {

    private Button mSplashActvtySearchSomething;
    private Context mContext;

    private Button startMyPageBtn;

    // Button Layout of footer

    private LinearLayout allCourseBtn;
    private LinearLayout recomendedBtn;
    private LinearLayout myPageBtn;
    private LinearLayout downloadsBtn;
    private LinearLayout profileBtn;

    String ImgUrl;
    String DetailDescription;
    String title;

    // for calling API

    private static RecyclerView recyclerView;
    private ProgressBar mProgressSpinner;
    private RecyclerView.Adapter adapter;

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

    private ArrayList<DetailDataModelCourses> detailList2parent;
    private ArrayList<DetailDataModelCourses3rdGrandFather> detailList3parent;

    private Object[] mArrayList;

    String url="http://api.muktopaath.orangebd.com/api/courses";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        mContext=this;

        GlobalVar.gBaseUrl="http://muktopaath.orangebd.com";

        View view2 = findViewById(android.R.id.content);
        Animation mLoadAnimation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in);
        mLoadAnimation.setDuration(1000);
        view2.startAnimation(mLoadAnimation);

        startMyPageBtn=findViewById(R.id.startMyPageBtnId);

        allCourseBtn = findViewById(R.id.allCourseBtnId);
        recomendedBtn = findViewById(R.id.recomendedBtnId);
        myPageBtn = findViewById(R.id.myPageBtnId);
        downloadsBtn = findViewById(R.id.downloadsBtnId);
        profileBtn = findViewById(R.id.profilePageBtnId);

        startMyPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, MyPageCourseDetail.class);
                v.getContext().startActivity(i);
            }
        });

        allCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(mContext, MainActivity.class);
                v.getContext().startActivity(i);
            }
        });

        recomendedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(mContext, RecomendedActivity.class);
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

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(mContext,ProfileActivity.class);
                v.getContext().startActivity(i);
            }
        });

        getSupportActionBar().hide();

        /*mSplashActvtySearchSomething=findViewById(R.id.splashActvtySearchSomething);
        mSplashActvtySearchSomething.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(mContext,MainActivity.class);
                v.getContext().startActivity(i);
            }
        });*/


        JSONObject object=new JSONObject();

        try {
            object.put("username", "1");
            object.put("featured", "");
            object.put("upcomming", "" );
            object.put("favorite", "1" );
            object.put("admin_featured", "" );
            object.put("order", "" );
            object.put("payment_status", "" );
            object.put("price_search", "" );
            object.put("price_start", "" );
            object.put("price_end", "" );
            object.put("duration_search", "" );
            object.put("duration_start", "" );
            object.put("limit", "10" );
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

                        detailListCourseDetailContentss = new ArrayList<ArrayList<DetailDataModelCoursesDetailContents>>();


                        /*detailListCourseDetailContents=new ArrayList<DetailDataModelCoursesDetailContents>();*/

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

                                            detailListCourseDetailContents=new ArrayList<DetailDataModelCoursesDetailContents>();

                                            for (int lmn = 0; lmn < jSObject2.length(); lmn++)
                                            {

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

                                                try {
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
                                                }
                                                catch (Exception ex){
                                                    Log.d("", "onResponse: ");
                                                }
                                                detailListCourseDetailContents.add(modelCourseContents);


                                                //For parsing array "multi_ques_list" > {0} > {0} > "syllebus" > "0" > "data"

                                                detailList5 = new ArrayList<DetailDataModelCourses>();

                                                //TODO
                                                //For now multi Q. is comment off for preventing course content
                                                /*DetailDataModelCourses model5 = new DetailDataModelCourses();

                                                try {
                                                    for (int l = 0; l < jSObject3.length() - 1; l++) {
                                                        JSONArray jSonObjMultiQ = (JSONArray) jSObject3.get("multi_ques_list");
                                                        JSONObject objectAgainAnother2 = (JSONObject) jSonObjMultiQ.get(l);

                                                        String mPulse = objectAgainAnother2.getString("pulse");

                                                        model5.setPulse(mPulse);
                                                    }
                                                } catch (Exception ex) {
                                                    Log.d("", "onResponse: ");
                                                }*/
                                            }
                                        }
                                    }

                                }
                                catch (Exception ex) {
                                    Log.d("", "onResponse: ");
                                }

                                model.setmArrayListContentDetails(detailListCourseDetailContentss);

                                detailListCourseDetailContentss.add(detailListCourseDetailContents);
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

                                ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> contentArray = detailListCourse.get(3).getmArrayListContentDetails();

                                mArrayList = contentArray.get(3).toArray();

                                GlobalVar.gChildArrayOfContent=mArrayList;

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

}
