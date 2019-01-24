package orangebd.newaspaper.mymuktopathapp;

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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

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
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RecomendedActivity extends AppCompatActivity {

    private Context mContext;

    // Button Layout of footer

    private LinearLayout allCourseBtn;
    private LinearLayout recomendedBtn;
    private LinearLayout myPageBtn;
    private LinearLayout downloadsBtn;
    private LinearLayout profileBtn;

    private HashMap<String,String> map;

    private ArrayList<DetailDataModelCourses> detailList;
    private ArrayList<DetailDataModelCourses> detailList2;

    String url="http://api.muktopaath.orangebd.com/api/courses";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomended);

        mContext=this;

        View view = LayoutInflater.from(mContext).inflate(R.layout.custom_logodetails, null, false);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(view);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#7a19aa")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        View view2 = findViewById(android.R.id.content);
        Animation mLoadAnimation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in);
        mLoadAnimation.setDuration(1000);
        view2.startAnimation(mLoadAnimation);

        allCourseBtn = findViewById(R.id.allCourseBtnId);
        recomendedBtn = findViewById(R.id.recomendedBtnId);
        myPageBtn = findViewById(R.id.myPageBtnId);
        downloadsBtn = findViewById(R.id.downloadsBtnId);
        profileBtn = findViewById(R.id.profilePageBtnId);

        allCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(mContext, MainActivity.class);
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

        map = new HashMap<String, String>();

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

                        detailList=new ArrayList<DetailDataModelCourses>();

                        DetailDataModelCourses model = new DetailDataModelCourses();

                        try {
                            for (int i=0;i<response.length()-1;i++)
                            {
                                JSONArray object = (JSONArray) response.get("data");
                                JSONObject object2 = (JSONObject) object.get(0);


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

                                detailList.add(model);

                                detailList2=new ArrayList<DetailDataModelCourses>();

                                JSONObject jObject = new JSONObject();

                                try {
                                    jObject = response.getJSONObject("syllabus");
                                }
                                catch (Exception ex){
                                    Log.d("", "onResponse: ");
                                }

                                DetailDataModelCourses model2 = new DetailDataModelCourses();


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
                params.put("Authorization", GlobalVar.gReplacingToken);
                return params;
            }
        };
        mQueue.add(jsonObjectRequest);
    }

    @Override
    public void onBackPressed() {
        finish();
        return;
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

        else if (id == android.R.id.home) {

            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
