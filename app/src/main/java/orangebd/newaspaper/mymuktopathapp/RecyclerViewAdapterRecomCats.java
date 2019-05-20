package orangebd.newaspaper.mymuktopathapp;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public class RecyclerViewAdapterRecomCats extends RecyclerView.Adapter<RecyclerViewAdapterRecomCats.MyViewHolder> {



    //For category1
    private ArrayList<DetailDataModelCourses> detailListMainActivityCourse=new ArrayList<>();

    private ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> detailListMainActivityCourseDetailContentss;
    private ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> detailListMainActivityCourseDetailUnits;
    private ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> detailListMainActivityCourseDetailUnitQuizList;

    private ArrayList<DetailDataModelCoursesThumbnails> detailListMainActivityCourseThumbnail;

    ArrayList<DetailDataModelCoursesDetailContents> mContentArrayListNew;
    ArrayList<DetailDataModelCoursesDetailContents> mUnitArrayListNew;
    ArrayList<DetailDataModelCoursesDetailContents> mUnitQuizList;

    private HashMap<String,String> map1;

    private int row_index = -1;


    String url= GlobalVar.gApiBaseUrl + "/api/course/search";


    String selectedId;
    String selectedEn;
    String selectedBn;






    private ArrayList<String> dataSet;
    private Context mContext;

    private boolean isCatTapped=false;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        LinearLayout LinearViewName;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewName = itemView.findViewById(R.id.textViewName);
            this.LinearViewName = itemView.findViewById(R.id.selectedCatLayoutId);
        }
    }

    public RecyclerViewAdapterRecomCats(ArrayList<String> data , Context context) {
        this.dataSet = data;

        this.mContext=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= new View(mContext);

        try {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_recommended_cats, parent, false);
        }
        catch (Exception ex){
            Log.d("", ex.getMessage());
        }

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        final TextView textViewName = holder.textViewName;

        final LinearLayout mLinearViewName = holder.LinearViewName;

        final String textValue= dataSet.get(listPosition);

        textViewName.setText(textValue);



        /*mLinearViewName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isCatTapped==true){

                    mLinearViewName.setBackground(mContext.getResources().getDrawable(R.drawable.button_style_selected_tags));
                    textViewName.setTextColor(Color.YELLOW);
                }
                else {

                    mLinearViewName.setBackground(mContext.getResources().getDrawable(R.drawable.button_style_tags));
                    textViewName.setTextColor(Color.WHITE);
                }
            }
        });*/







        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                if(isCatTapped==true){

                    textViewName.setTextColor(Color.WHITE);

                    mLinearViewName.setBackground(mContext.getResources().getDrawable(R.drawable.button_style_tags));

                    isCatTapped=false;
                }
                else {
                    String getRecomCatName = textViewName.getText().toString();

                    textViewName.setTextColor(Color.YELLOW);

                    mLinearViewName.setBackground(mContext.getResources().getDrawable(R.drawable.button_style_selected_tags));

                    isCatTapped=true;


                    for(int checkEnRecom=0; checkEnRecom<GlobalVar.gDetailListMainActivityCourseCat.size(); checkEnRecom++) {

                        String checkExistingBn = GlobalVar.gDetailListMainActivityCourseCat.get(checkEnRecom).getmCourseCategoryNameBn();

                        String checkExistingId = GlobalVar.gDetailListMainActivityCourseCat.get(checkEnRecom).getmCourseCategoryId();
                        String checkExistingEn = GlobalVar.gDetailListMainActivityCourseCat.get(checkEnRecom).getmCourseCategoryNameEn();

                        if (getRecomCatName.equalsIgnoreCase(checkExistingBn)) {

                            selectedId = checkExistingId;
                            selectedEn = checkExistingEn;
                            selectedBn = checkExistingBn;
                        }

                    }

                    setRecyclerView();


                    //GlobalVar.gRlecomRecyclerViewAdapter =new RecyclerViewAdapterRecomTemp(dataSet,mContext);
                }

            }
        });

    }



    private void setRecyclerView() {

        GlobalVar.gRecyclerViewCat.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);

        GlobalVar.gRecyclerViewCat.setLayoutManager(layoutManager);

        GlobalVar.gRecyclerViewCat.setNestedScrollingEnabled(false);



        map1 = new HashMap<String, String>();


        map1.put("id", selectedId);
        map1.put("name", selectedEn);
        map1.put("rating", "" );



        new RecommendedCategory().execute(url);
    }


    public class RecommendedCategory extends AsyncTask<String, Void, String>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            GlobalVar.gProgressSpinner.setIndeterminate(true);
            GlobalVar.gProgressSpinner.setVisibility(View.VISIBLE);

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

            GlobalVar.gRlecomRecyclerViewAdapter=new RecyclerViewAdapterRecomTemp(detailListMainActivityCourse,mContext);

            GlobalVar.gRecyclerViewCat.setAdapter(GlobalVar.gRlecomRecyclerViewAdapter);
            GlobalVar.gRlecomRecyclerViewAdapter.notifyDataSetChanged();


            GlobalVar.gProgressSpinner.setVisibility(View.GONE);
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
    public int getItemCount() {
        return dataSet.size();
    }
}
