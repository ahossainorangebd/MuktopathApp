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

    private ArrayList<DetailDataModel> detailList=new ArrayList<>();


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

        profileBtn.setOnClickListener(new View.OnClickListener() {
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

                        DetailDataModelCourses model = new DetailDataModelCourses();

                        try {
                            JSONArray object = (JSONArray) response.get("data");

                            for (int i=0;i<object.length()-1;i++)
                            {

                                JSONObject object2 = (JSONObject) object.get(i);


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

                                detailListCourse.add(model);
                            }


                            setRecyclerView10();
                            adapter10=new RecyclerViewAdapterCategory10(detailListCourse,mContext);
                            recyclerView10.setAdapter(adapter10);
                            adapter10.notifyDataSetChanged();
                            mProgressSpinner10.setVisibility(View.GONE);

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


        setRecyclerView();
    }


    private void setRecyclerView() {
        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(layoutManager);

        //nestedScrollView.setSmoothScrollingEnabled(true);
        //nestedScrollView.fullScroll(View.FOCUS_UP);
        recyclerView.setNestedScrollingEnabled(false);

        new GetCategory1Courses().execute("https://rtvonline.com/json-feed/latest.json");
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
            HttpHandler sh = new HttpHandler();
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

            } catch (final JSONException e) {
                //Log.e(TAG, "Json parsing error: " + e.getMessage());
                Log.e("tag", "Couldn't get json from server.");

            }
            else {
                Log.e("tag", "Couldn't get json from server.");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            adapter=new RecyclerViewAdapterCategory1(detailList,mContext);
            //
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            mProgressSpinner.setVisibility(View.GONE);

            new GetCategory2Courses().execute("https://rtvonline.com/json-feed/bangladesh.json");
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
            HttpHandler sh = new HttpHandler();
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

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            setRecyclerView2();

            adapter2=new RecyclerViewAdapterCategory2(detailList,mContext);
            //
            recyclerView2.setAdapter(adapter2);
            adapter2.notifyDataSetChanged();

            mProgressSpinner2.setVisibility(View.GONE);

            new GetCategory3Courses().execute("https://rtvonline.com/json-feed/crime.json");
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
            HttpHandler sh = new HttpHandler();
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
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            setRecyclerView3();

            adapter3=new RecyclerViewAdapterCategory3(detailList,mContext);
            //
            recyclerView3.setAdapter(adapter3);
            adapter3.notifyDataSetChanged();

            mProgressSpinner3.setVisibility(View.GONE);

            new GetCategory4Courses().execute("https://rtvonline.com/json-feed/economics.json");
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
            HttpHandler sh = new HttpHandler();
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

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            setRecyclerView4();

            adapter4=new RecyclerViewAdapterCategory4(detailList,mContext);
            //
            recyclerView4.setAdapter(adapter4);
            adapter4.notifyDataSetChanged();

            mProgressSpinner4.setVisibility(View.GONE);

            new GetCategory5Courses().execute("https://rtvonline.com/json-feed/entertainment.json");
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
            HttpHandler sh = new HttpHandler();
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

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            setRecyclerView5();

            adapter5=new RecyclerViewAdapterCategory5(detailList,mContext);
            //
            recyclerView5.setAdapter(adapter5);
            adapter5.notifyDataSetChanged();

            mProgressSpinner5.setVisibility(View.GONE);

            new GetCategory6Courses().execute("https://rtvonline.com/json-feed/international.json");
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
            HttpHandler sh = new HttpHandler();
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

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            setRecyclerView6();

            adapter6=new RecyclerViewAdapterCategory6(detailList,mContext);
            //
            recyclerView6.setAdapter(adapter6);
            adapter6.notifyDataSetChanged();

            mProgressSpinner6.setVisibility(View.GONE);

            new GetCategory7Courses().execute("https://rtvonline.com/json-feed/lifestyle.json");
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
            HttpHandler sh = new HttpHandler();
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

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            setRecyclerView7();

            adapter7=new RecyclerViewAdapterCategory7(detailList,mContext);
            //
            recyclerView7.setAdapter(adapter7);
            adapter7.notifyDataSetChanged();

            mProgressSpinner7.setVisibility(View.GONE);

            new GetCategory8Courses().execute("https://rtvonline.com/json-feed/politics.json");
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
            HttpHandler sh = new HttpHandler();
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

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            setRecyclerView8();

            adapter8=new RecyclerViewAdapterCategory8(detailList,mContext);
            //
            recyclerView8.setAdapter(adapter8);
            adapter8.notifyDataSetChanged();

            mProgressSpinner8.setVisibility(View.GONE);

            new GetCategory9Courses().execute("https://rtvonline.com/json-feed/sports.json");
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
            HttpHandler sh = new HttpHandler();
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

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            setRecyclerView9();

            adapter9=new RecyclerViewAdapterCategory9(detailList,mContext);
            //
            recyclerView9.setAdapter(adapter9);
            adapter9.notifyDataSetChanged();

            mProgressSpinner9.setVisibility(View.GONE);

            new GetCategory10Courses().execute("https://rtvonline.com/json-feed/country.json");
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
            HttpHandler sh = new HttpHandler();
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

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            /*setRecyclerView10();

            adapter10=new RecyclerViewAdapterCategory10(detailListCourse,mContext);
            //
            recyclerView10.setAdapter(adapter10);
            adapter10.notifyDataSetChanged();
            */

            mProgressSpinner10.setVisibility(View.GONE);
        }
    }
}
