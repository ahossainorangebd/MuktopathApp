package orangebd.newaspaper.mymuktopathapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class SelectACategoryActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    //boolean for linearlayout checked or not

    private  boolean CatIsChecked1=false;
    private  boolean CatIsChecked2=false;
    private  boolean CatIsChecked3=false;
    private  boolean CatIsChecked4=false;
    private  boolean CatIsChecked5=false;
    private  boolean CatIsChecked6=false;
    private  boolean CatIsChecked7=false;
    private  boolean CatIsChecked8=false;
    private  boolean CatIsChecked9=false;
    private  boolean CatIsChecked10=false;



    private LinearLayout chooseCategory1;
    private LinearLayout category1Expand;

    private LinearLayout chooseCategory2;
    private LinearLayout category2Expand;

    private LinearLayout chooseCategory3;
    private LinearLayout category3Expand;

    private LinearLayout chooseCategory4;
    private LinearLayout category4Expand;

    private LinearLayout chooseCategory5;
    private LinearLayout category5Expand;

    private LinearLayout chooseCategory6;
    private LinearLayout category6Expand;

    private LinearLayout chooseCategory7;
    private LinearLayout category7Expand;

    private LinearLayout chooseCategory8;
    private LinearLayout category8Expand;

    private LinearLayout chooseCategory9;
    private LinearLayout category9Expand;

    private LinearLayout chooseCategory10;
    private LinearLayout category10Expand;

    private boolean isExpand;

    private Button mNextBtn;

    private Context mContext;

    private RotateAnimation rotateAnimation;

    private TextView mCourseTextView1;
    private TextView mCourseTextView2;
    private TextView mCourseTextView3;
    private TextView mCourseTextView4;
    private TextView mCourseTextView5;
    private TextView mCourseTextView6;
    private TextView mCourseTextView7;
    private TextView mCourseTextView8;
    private TextView mCourseTextView9;
    private TextView mCourseTextView10;

    private TextView mCourseHiddenEn1;
    private TextView mCourseHiddenEn2;
    private TextView mCourseHiddenEn3;
    private TextView mCourseHiddenEn4;
    private TextView mCourseHiddenEn5;
    private TextView mCourseHiddenEn6;
    private TextView mCourseHiddenEn7;
    private TextView mCourseHiddenEn8;
    private TextView mCourseHiddenEn9;
    private TextView mCourseHiddenEn10;

    private TextView mCourseHiddenEnId1;
    private TextView mCourseHiddenEnId2;
    private TextView mCourseHiddenEnId3;
    private TextView mCourseHiddenEnId4;
    private TextView mCourseHiddenEnId5;
    private TextView mCourseHiddenEnId6;
    private TextView mCourseHiddenEnId7;
    private TextView mCourseHiddenEnId8;
    private TextView mCourseHiddenEnId9;
    private TextView mCourseHiddenEnId10;

    private ImageView mCourseLogo1;
    private ImageView mCourseLogo2;
    private ImageView mCourseLogo3;
    private ImageView mCourseLogo4;
    private ImageView mCourseLogo5;
    private ImageView mCourseLogo6;
    private ImageView mCourseLogo7;
    private ImageView mCourseLogo8;
    private ImageView mCourseLogo9;
    private ImageView mCourseLogo10;


    String urlGetCourseCats = GlobalVar.gApiBaseUrl + "/api/course-categories";

    //All the detail Lists
    private ArrayList<DetailDataModelAll> detailListMainActivityCourseCat;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_acategory);

        mContext=this;

        /*recyclerView = findViewById(R.id.recyclerview_catList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext,2));
        recyclerView.setNestedScrollingEnabled(false);*/

        mCourseTextView1=findViewById(R.id.firstCatTextViewId);
        mCourseTextView2=findViewById(R.id.SecondCatTextViewId);
        mCourseTextView3=findViewById(R.id.ThirdCatTextViewId);
        mCourseTextView4=findViewById(R.id.fourthCatTextViewId);
        mCourseTextView5=findViewById(R.id.fifthCatTextViewId);
        mCourseTextView6=findViewById(R.id.sixthCatTextViewId);
        mCourseTextView7=findViewById(R.id.seventhCatTextViewId);
        mCourseTextView8=findViewById(R.id.eightCatTextViewId);
        mCourseTextView9=findViewById(R.id.ninthCatTextViewId);
        mCourseTextView10=findViewById(R.id.tenthCatTextViewId);

        //For hidden en texts ens
        mCourseHiddenEn1=findViewById(R.id.CourseHiddenEnId1);
        mCourseHiddenEn2=findViewById(R.id.CourseHiddenEnId2);
        mCourseHiddenEn3=findViewById(R.id.CourseHiddenEnId3);
        mCourseHiddenEn4=findViewById(R.id.CourseHiddenEnId4);
        mCourseHiddenEn5=findViewById(R.id.CourseHiddenEnId5);
        mCourseHiddenEn6=findViewById(R.id.CourseHiddenEnId6);
        mCourseHiddenEn7=findViewById(R.id.CourseHiddenEnId7);
        mCourseHiddenEn8=findViewById(R.id.CourseHiddenEnId8);
        mCourseHiddenEn9=findViewById(R.id.CourseHiddenEnId9);
        mCourseHiddenEn10=findViewById(R.id.CourseHiddenEnId10);

        //For hidden en texts ids
        mCourseHiddenEnId1=findViewById(R.id.CourseHiddenEnIdId1);
        mCourseHiddenEnId2=findViewById(R.id.CourseHiddenEnIdId2);
        mCourseHiddenEnId3=findViewById(R.id.CourseHiddenEnIdId3);
        mCourseHiddenEnId4=findViewById(R.id.CourseHiddenEnIdId4);
        mCourseHiddenEnId5=findViewById(R.id.CourseHiddenEnIdId5);
        mCourseHiddenEnId6=findViewById(R.id.CourseHiddenEnIdId6);
        mCourseHiddenEnId7=findViewById(R.id.CourseHiddenEnIdId7);
        mCourseHiddenEnId8=findViewById(R.id.CourseHiddenEnIdId8);
        mCourseHiddenEnId9=findViewById(R.id.CourseHiddenEnIdId9);
        mCourseHiddenEnId10=findViewById(R.id.CourseHiddenEnIdId10);

        //for course cat logos

        mCourseLogo1=findViewById(R.id.courseCatLogo1);
        mCourseLogo2=findViewById(R.id.courseCatLogo2);
        mCourseLogo3=findViewById(R.id.courseCatLogo3);
        mCourseLogo4=findViewById(R.id.courseCatLogo4);
        mCourseLogo5=findViewById(R.id.courseCatLogo5);
        mCourseLogo6=findViewById(R.id.courseCatLogo6);
        mCourseLogo7=findViewById(R.id.courseCatLogo7);
        mCourseLogo8=findViewById(R.id.courseCatLogo8);
        mCourseLogo9=findViewById(R.id.courseCatLogo9);
        mCourseLogo10=findViewById(R.id.courseCatLogo10);


        chooseCategory1=findViewById(R.id.chooseCategory1);
        chooseCategory2=findViewById(R.id.chooseCategory2);
        chooseCategory3=findViewById(R.id.chooseCategory3);
        chooseCategory4=findViewById(R.id.chooseCategory4);
        chooseCategory5=findViewById(R.id.chooseCategory5);
        chooseCategory6=findViewById(R.id.chooseCategory6);
        chooseCategory7=findViewById(R.id.chooseCategory7);
        chooseCategory8=findViewById(R.id.chooseCategory8);
        chooseCategory9=findViewById(R.id.chooseCategory9);
        chooseCategory10=findViewById(R.id.chooseCategory10);

        GlobalVar.gRecommendedCategories = new ArrayList<>();

        GlobalVar.gRecommendedCategoriesEn = new ArrayList<>();
        GlobalVar.gRecommendedCategoriesId = new ArrayList<>();

        chooseCategory1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(CatIsChecked1==true) {

                    CatIsChecked1=false;

                    chooseCategory1.setBackgroundColor(Color.parseColor("#ffffff"));

                    for(int checkId=0; checkId<GlobalVar.gRecommendedCategories.size(); checkId++){

                        String checkCat1=GlobalVar.gRecommendedCategories.get(checkId);

                        if(checkCat1.equalsIgnoreCase(mCourseTextView1.getText().toString())){

                            int index=checkId;

                            GlobalVar.gRecommendedCategories.remove(index);
                            GlobalVar.gRecommendedCategoriesEn.remove(index);
                            GlobalVar.gRecommendedCategoriesId.remove(index);
                        }

                        //GlobalVar.gRecommendedCategories.remove(0);
                    }
                }
                else {
                    CatIsChecked1=true;

                    CharSequence checkedCat1=mCourseTextView1.getText();
                    String checkedStrCat1=checkedCat1.toString();
                    GlobalVar.gRecommendedCategories.add(checkedStrCat1);

                    CharSequence checkedStrCatEnCs1=mCourseHiddenEn1.getText();
                    String checkedStrCatEn1=checkedStrCatEnCs1.toString();
                    GlobalVar.gRecommendedCategoriesEn.add(checkedStrCatEn1);

                    CharSequence checkedStrCatEnCsId1=mCourseHiddenEnId1.getText();
                    String checkedStrCatEnId1=checkedStrCatEnCsId1.toString();
                    GlobalVar.gRecommendedCategoriesId.add(checkedStrCatEnId1);

                    chooseCategory1.setBackgroundColor(Color.parseColor("#a725ed"));

                }
            }
        });

        chooseCategory2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(CatIsChecked2==true) {

                    CatIsChecked2=false;

                    chooseCategory2.setBackgroundColor(Color.parseColor("#ffffff"));

                    for(int checkId=0; checkId<GlobalVar.gRecommendedCategories.size(); checkId++){

                        String checkCat2=GlobalVar.gRecommendedCategories.get(checkId);

                        if(checkCat2.equalsIgnoreCase(mCourseTextView2.getText().toString())){

                            int index=checkId;

                            GlobalVar.gRecommendedCategories.remove(index);
                            GlobalVar.gRecommendedCategoriesEn.remove(index);
                            GlobalVar.gRecommendedCategoriesId.remove(index);
                        }

                        //GlobalVar.gRecommendedCategories.remove(0);
                    }
                }
                else {
                    CatIsChecked2=true;

                    CharSequence checkedCat2=mCourseTextView2.getText();
                    String checkedStrCat2=checkedCat2.toString();
                    GlobalVar.gRecommendedCategories.add(checkedStrCat2);

                    CharSequence checkedStrCatEnCs2=mCourseHiddenEn2.getText();
                    String checkedStrCatEn2=checkedStrCatEnCs2.toString();
                    GlobalVar.gRecommendedCategoriesEn.add(checkedStrCatEn2);

                    CharSequence checkedStrCatEnCsId2=mCourseHiddenEnId2.getText();
                    String checkedStrCatEnId2=checkedStrCatEnCsId2.toString();
                    GlobalVar.gRecommendedCategoriesId.add(checkedStrCatEnId2);

                    chooseCategory2.setBackgroundColor(Color.parseColor("#a725ed"));

                }
            }
        });

        chooseCategory3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(CatIsChecked3==true) {

                    CatIsChecked3=false;

                    chooseCategory3.setBackgroundColor(Color.parseColor("#ffffff"));

                    for(int checkId=0; checkId<GlobalVar.gRecommendedCategories.size(); checkId++){

                        String checkCat3=GlobalVar.gRecommendedCategories.get(checkId);

                        if(checkCat3.equalsIgnoreCase(mCourseTextView3.getText().toString())){

                            int index=checkId;

                            GlobalVar.gRecommendedCategories.remove(index);
                            GlobalVar.gRecommendedCategoriesEn.remove(index);
                            GlobalVar.gRecommendedCategoriesId.remove(index);
                        }

                        //GlobalVar.gRecommendedCategories.remove(0);
                    }
                }
                else {
                    CatIsChecked3=true;

                    CharSequence checkedCat3=mCourseTextView3.getText();
                    String checkedStrCat3=checkedCat3.toString();
                    GlobalVar.gRecommendedCategories.add(checkedStrCat3);

                    CharSequence checkedStrCatEnCs3=mCourseHiddenEn3.getText();
                    String checkedStrCatEn3=checkedStrCatEnCs3.toString();
                    GlobalVar.gRecommendedCategoriesEn.add(checkedStrCatEn3);

                    CharSequence checkedStrCatEnCsId3=mCourseHiddenEnId3.getText();
                    String checkedStrCatEnId3=checkedStrCatEnCsId3.toString();
                    GlobalVar.gRecommendedCategoriesId.add(checkedStrCatEnId3);

                    chooseCategory3.setBackgroundColor(Color.parseColor("#a725ed"));

                }
            }
        });

        chooseCategory4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(CatIsChecked4==true) {

                    CatIsChecked4=false;

                    chooseCategory4.setBackgroundColor(Color.parseColor("#ffffff"));

                    for(int checkId=0; checkId<GlobalVar.gRecommendedCategories.size(); checkId++){

                        String checkCat4=GlobalVar.gRecommendedCategories.get(checkId);

                        if(checkCat4.equalsIgnoreCase(mCourseTextView4.getText().toString())){

                            int index=checkId;

                            GlobalVar.gRecommendedCategories.remove(index);
                            GlobalVar.gRecommendedCategoriesEn.remove(index);
                            GlobalVar.gRecommendedCategoriesId.remove(index);
                        }

                        //GlobalVar.gRecommendedCategories.remove(0);
                    }
                }
                else {
                    CatIsChecked4=true;

                    CharSequence checkedCat4=mCourseTextView4.getText();
                    String checkedStrCat4=checkedCat4.toString();
                    GlobalVar.gRecommendedCategories.add(checkedStrCat4);

                    CharSequence checkedStrCatEnCs4=mCourseHiddenEn4.getText();
                    String checkedStrCatEn4=checkedStrCatEnCs4.toString();
                    GlobalVar.gRecommendedCategoriesEn.add(checkedStrCatEn4);

                    CharSequence checkedStrCatEnCsId4=mCourseHiddenEnId4.getText();
                    String checkedStrCatEnId4=checkedStrCatEnCsId4.toString();
                    GlobalVar.gRecommendedCategoriesId.add(checkedStrCatEnId4);

                    chooseCategory4.setBackgroundColor(Color.parseColor("#a725ed"));

                }
            }
        });

        chooseCategory5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(CatIsChecked5==true) {

                    CatIsChecked5=false;

                    chooseCategory5.setBackgroundColor(Color.parseColor("#ffffff"));

                    for(int checkId=0; checkId<GlobalVar.gRecommendedCategories.size(); checkId++){

                        String checkCat5=GlobalVar.gRecommendedCategories.get(checkId);

                        if(checkCat5.equalsIgnoreCase(mCourseTextView5.getText().toString())){

                            int index=checkId;

                            GlobalVar.gRecommendedCategories.remove(index);
                            GlobalVar.gRecommendedCategoriesEn.remove(index);
                            GlobalVar.gRecommendedCategoriesId.remove(index);
                        }

                        //GlobalVar.gRecommendedCategories.remove(0);
                    }
                }
                else {
                    CatIsChecked5=true;

                    CharSequence checkedCat5=mCourseTextView5.getText();
                    String checkedStrCat5=checkedCat5.toString();
                    GlobalVar.gRecommendedCategories.add(checkedStrCat5);

                    CharSequence checkedStrCatEnCs5=mCourseHiddenEn5.getText();
                    String checkedStrCatEn5=checkedStrCatEnCs5.toString();
                    GlobalVar.gRecommendedCategoriesEn.add(checkedStrCatEn5);

                    CharSequence checkedStrCatEnCsId5=mCourseHiddenEnId5.getText();
                    String checkedStrCatEnId5=checkedStrCatEnCsId5.toString();
                    GlobalVar.gRecommendedCategoriesId.add(checkedStrCatEnId5);

                    chooseCategory5.setBackgroundColor(Color.parseColor("#a725ed"));

                }
            }
        });

        chooseCategory6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(CatIsChecked6==true) {

                    CatIsChecked6=false;

                    chooseCategory6.setBackgroundColor(Color.parseColor("#ffffff"));

                    for(int checkId=0; checkId<GlobalVar.gRecommendedCategories.size(); checkId++){

                        String checkCat6=GlobalVar.gRecommendedCategories.get(checkId);

                        if(checkCat6.equalsIgnoreCase(mCourseTextView6.getText().toString())){

                            int index=checkId;

                            GlobalVar.gRecommendedCategories.remove(index);
                            GlobalVar.gRecommendedCategoriesEn.remove(index);
                            GlobalVar.gRecommendedCategoriesId.remove(index);
                        }

                        //GlobalVar.gRecommendedCategories.remove(0);
                    }
                }
                else {
                    CatIsChecked6=true;

                    CharSequence checkedCat6=mCourseTextView6.getText();
                    String checkedStrCat6=checkedCat6.toString();
                    GlobalVar.gRecommendedCategories.add(checkedStrCat6);

                    CharSequence checkedStrCatEnCs6=mCourseHiddenEn6.getText();
                    String checkedStrCatEn6=checkedStrCatEnCs6.toString();
                    GlobalVar.gRecommendedCategoriesEn.add(checkedStrCatEn6);

                    CharSequence checkedStrCatEnCsId6=mCourseHiddenEnId6.getText();
                    String checkedStrCatEnId6=checkedStrCatEnCsId6.toString();
                    GlobalVar.gRecommendedCategoriesId.add(checkedStrCatEnId6);

                    chooseCategory6.setBackgroundColor(Color.parseColor("#a725ed"));

                }
            }
        });

        chooseCategory7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(CatIsChecked7==true) {

                    CatIsChecked7=false;

                    chooseCategory7.setBackgroundColor(Color.parseColor("#ffffff"));

                    for(int checkId=0; checkId<GlobalVar.gRecommendedCategories.size(); checkId++){

                        String checkCat7=GlobalVar.gRecommendedCategories.get(checkId);

                        if(checkCat7.equalsIgnoreCase(mCourseTextView7.getText().toString())){

                            int index=checkId;

                            GlobalVar.gRecommendedCategories.remove(index);
                            GlobalVar.gRecommendedCategoriesEn.remove(index);
                            GlobalVar.gRecommendedCategoriesId.remove(index);
                        }

                        //GlobalVar.gRecommendedCategories.remove(0);
                    }
                }
                else {
                    CatIsChecked7=true;

                    CharSequence checkedCat7=mCourseTextView7.getText();
                    String checkedStrCat7=checkedCat7.toString();
                    GlobalVar.gRecommendedCategories.add(checkedStrCat7);

                    CharSequence checkedStrCatEnCs7=mCourseHiddenEn7.getText();
                    String checkedStrCatEn7=checkedStrCatEnCs7.toString();
                    GlobalVar.gRecommendedCategoriesEn.add(checkedStrCatEn7);

                    CharSequence checkedStrCatEnCsId7=mCourseHiddenEnId7.getText();
                    String checkedStrCatEnId7=checkedStrCatEnCsId7.toString();
                    GlobalVar.gRecommendedCategoriesId.add(checkedStrCatEnId7);

                    chooseCategory7.setBackgroundColor(Color.parseColor("#a725ed"));

                }
            }
        });

        chooseCategory8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(CatIsChecked8==true) {

                    CatIsChecked8=false;

                    chooseCategory8.setBackgroundColor(Color.parseColor("#ffffff"));

                    for(int checkId=0; checkId<GlobalVar.gRecommendedCategories.size(); checkId++){

                        String checkCat8=GlobalVar.gRecommendedCategories.get(checkId);

                        if(checkCat8.equalsIgnoreCase(mCourseTextView8.getText().toString())){

                            int index=checkId;

                            GlobalVar.gRecommendedCategories.remove(index);
                            GlobalVar.gRecommendedCategoriesEn.remove(index);
                            GlobalVar.gRecommendedCategoriesId.remove(index);
                        }

                        //GlobalVar.gRecommendedCategories.remove(0);
                    }
                }
                else {
                    CatIsChecked8=true;

                    CharSequence checkedCat8=mCourseTextView8.getText();
                    String checkedStrCat8=checkedCat8.toString();
                    GlobalVar.gRecommendedCategories.add(checkedStrCat8);

                    CharSequence checkedStrCatEnCs8=mCourseHiddenEn8.getText();
                    String checkedStrCatEn8=checkedStrCatEnCs8.toString();
                    GlobalVar.gRecommendedCategoriesEn.add(checkedStrCatEn8);

                    CharSequence checkedStrCatEnCsId8=mCourseHiddenEnId8.getText();
                    String checkedStrCatEnId8=checkedStrCatEnCsId8.toString();
                    GlobalVar.gRecommendedCategoriesId.add(checkedStrCatEnId8);

                    chooseCategory8.setBackgroundColor(Color.parseColor("#a725ed"));

                }
            }
        });

        chooseCategory9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(CatIsChecked9==true) {

                    CatIsChecked9=false;

                    chooseCategory9.setBackgroundColor(Color.parseColor("#ffffff"));

                    for(int checkId=0; checkId<GlobalVar.gRecommendedCategories.size(); checkId++){

                        String checkCat9=GlobalVar.gRecommendedCategories.get(checkId);

                        if(checkCat9.equalsIgnoreCase(mCourseTextView9.getText().toString())){

                            int index=checkId;

                            GlobalVar.gRecommendedCategories.remove(index);
                            GlobalVar.gRecommendedCategoriesEn.remove(index);
                            GlobalVar.gRecommendedCategoriesId.remove(index);
                        }

                        //GlobalVar.gRecommendedCategories.remove(0);
                    }
                }
                else {
                    CatIsChecked9=true;

                    CharSequence checkedCat9=mCourseTextView9.getText();
                    String checkedStrCat9=checkedCat9.toString();
                    GlobalVar.gRecommendedCategories.add(checkedStrCat9);

                    CharSequence checkedStrCatEnCs9=mCourseHiddenEn9.getText();
                    String checkedStrCatEn9=checkedStrCatEnCs9.toString();
                    GlobalVar.gRecommendedCategoriesEn.add(checkedStrCatEn9);

                    CharSequence checkedStrCatEnCsId9=mCourseHiddenEnId9.getText();
                    String checkedStrCatEnId9=checkedStrCatEnCsId9.toString();
                    GlobalVar.gRecommendedCategoriesId.add(checkedStrCatEnId9);

                    chooseCategory9.setBackgroundColor(Color.parseColor("#a725ed"));

                }
            }
        });




        mNextBtn=findViewById(R.id.nextBtnId);
        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, MyPageActivity.class);
                v.getContext().startActivity(i);
            }
        });

        new GetCourseCategories().execute(urlGetCourseCats);

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

                try {
                    mCourseTextView1.setText(detailListMainActivityCourseCat.get(0).getmCourseCategoryNameBn());
                    mCourseTextView2.setText(detailListMainActivityCourseCat.get(1).getmCourseCategoryNameBn());
                    mCourseTextView3.setText(detailListMainActivityCourseCat.get(2).getmCourseCategoryNameBn());
                    mCourseTextView4.setText(detailListMainActivityCourseCat.get(3).getmCourseCategoryNameBn());
                    mCourseTextView5.setText(detailListMainActivityCourseCat.get(4).getmCourseCategoryNameBn());
                    mCourseTextView6.setText(detailListMainActivityCourseCat.get(5).getmCourseCategoryNameBn());
                    mCourseTextView7.setText(detailListMainActivityCourseCat.get(6).getmCourseCategoryNameBn());
                    mCourseTextView8.setText(detailListMainActivityCourseCat.get(7).getmCourseCategoryNameBn());
                    mCourseTextView9.setText(detailListMainActivityCourseCat.get(8).getmCourseCategoryNameBn());
                    //mCourseTextView4.setText(detailListMainActivityCourseCat.get(10).getmCourseCategoryNameBn());

                    mCourseHiddenEn1.setText(detailListMainActivityCourseCat.get(0).getmCourseCategoryNameEn());
                    mCourseHiddenEn2.setText(detailListMainActivityCourseCat.get(1).getmCourseCategoryNameEn());
                    mCourseHiddenEn3.setText(detailListMainActivityCourseCat.get(2).getmCourseCategoryNameEn());
                    mCourseHiddenEn4.setText(detailListMainActivityCourseCat.get(3).getmCourseCategoryNameEn());
                    mCourseHiddenEn5.setText(detailListMainActivityCourseCat.get(4).getmCourseCategoryNameEn());
                    mCourseHiddenEn6.setText(detailListMainActivityCourseCat.get(5).getmCourseCategoryNameEn());
                    mCourseHiddenEn7.setText(detailListMainActivityCourseCat.get(6).getmCourseCategoryNameEn());
                    mCourseHiddenEn8.setText(detailListMainActivityCourseCat.get(7).getmCourseCategoryNameEn());
                    mCourseHiddenEn9.setText(detailListMainActivityCourseCat.get(8).getmCourseCategoryNameEn());
                    //mCourseHiddenEn10.setText(detailListMainActivityCourseCat.get(9).getmCourseCategoryNameEn());

                    mCourseHiddenEnId1.setText(detailListMainActivityCourseCat.get(0).getmCourseCategoryId());
                    mCourseHiddenEnId2.setText(detailListMainActivityCourseCat.get(1).getmCourseCategoryId());
                    mCourseHiddenEnId3.setText(detailListMainActivityCourseCat.get(2).getmCourseCategoryId());
                    mCourseHiddenEnId4.setText(detailListMainActivityCourseCat.get(3).getmCourseCategoryId());
                    mCourseHiddenEnId5.setText(detailListMainActivityCourseCat.get(4).getmCourseCategoryId());
                    mCourseHiddenEnId6.setText(detailListMainActivityCourseCat.get(5).getmCourseCategoryId());
                    mCourseHiddenEnId7.setText(detailListMainActivityCourseCat.get(6).getmCourseCategoryId());
                    mCourseHiddenEnId8.setText(detailListMainActivityCourseCat.get(7).getmCourseCategoryId());
                    mCourseHiddenEnId9.setText(detailListMainActivityCourseCat.get(8).getmCourseCategoryId());
                    //mCourseHiddenEnId10.setText(detailListMainActivityCourseCat.get(9).getmCourseCategoryId());


                    String mCategoryLogo1 = GlobalVar.gBaseUrlForProfile + "/images/courseCategory/" + detailListMainActivityCourseCat.get(0).getmCourseCategoryLogo();
                    String mCategoryLogo2 = GlobalVar.gBaseUrlForProfile + "/images/courseCategory/" + detailListMainActivityCourseCat.get(1).getmCourseCategoryLogo();
                    String mCategoryLogo3 = GlobalVar.gBaseUrlForProfile + "/images/courseCategory/" + detailListMainActivityCourseCat.get(2).getmCourseCategoryLogo();
                    String mCategoryLogo4 = GlobalVar.gBaseUrlForProfile + "/images/courseCategory/" + detailListMainActivityCourseCat.get(3).getmCourseCategoryLogo();
                    String mCategoryLogo5 = GlobalVar.gBaseUrlForProfile + "/images/courseCategory/" + detailListMainActivityCourseCat.get(4).getmCourseCategoryLogo();
                    String mCategoryLogo6 = GlobalVar.gBaseUrlForProfile + "/images/courseCategory/" + detailListMainActivityCourseCat.get(5).getmCourseCategoryLogo();
                    String mCategoryLogo7 = GlobalVar.gBaseUrlForProfile + "/images/courseCategory/" + detailListMainActivityCourseCat.get(6).getmCourseCategoryLogo();
                    String mCategoryLogo8 = GlobalVar.gBaseUrlForProfile + "/images/courseCategory/" + detailListMainActivityCourseCat.get(7).getmCourseCategoryLogo();
                    String mCategoryLogo9 = GlobalVar.gBaseUrlForProfile + "/images/courseCategory/" + detailListMainActivityCourseCat.get(8).getmCourseCategoryLogo();
                    //String mCategoryLogo10 = GlobalVar.gBaseUrlForProfile + "/images/courseCategory/" + detailListMainActivityCourseCat.get(9).getmCourseCategoryLogo();

                    try {
                        Picasso.with(mContext).load(mCategoryLogo1).into(mCourseLogo1);
                    }
                    catch (Exception ex){}

                    try {
                        Picasso.with(mContext).load(mCategoryLogo2).into(mCourseLogo2);
                    }
                    catch (Exception ex){}

                    try {
                        Picasso.with(mContext).load(mCategoryLogo3).into(mCourseLogo3);
                    }
                    catch (Exception ex){}

                    try {
                        Picasso.with(mContext).load(mCategoryLogo4).into(mCourseLogo4);
                    }
                    catch (Exception ex){}


                    try {
                        Picasso.with(mContext).load(mCategoryLogo5).into(mCourseLogo5);
                    }
                    catch (Exception ex){}

                    try {
                        Picasso.with(mContext).load(mCategoryLogo6).into(mCourseLogo6);
                    }
                    catch (Exception ex){}

                    try {
                        Picasso.with(mContext).load(mCategoryLogo7).into(mCourseLogo7);
                    }
                    catch (Exception ex){}

                    try {
                        Picasso.with(mContext).load(mCategoryLogo8).into(mCourseLogo8);
                    }
                    catch (Exception ex){}

                    try {
                        Picasso.with(mContext).load(mCategoryLogo9).into(mCourseLogo9);
                    }
                    catch (Exception ex){}

                    try {
                        //Picasso.with(mContext).load(mCategoryLogo10).into(mCourseLogo10);
                    }
                    catch (Exception ex){}


                    /*
                    mCourseTextView5.setText(detailListMainActivityCourseCat.get(4).getmCourseCategoryNameBn());
                    mCourseTextView6.setText(detailListMainActivityCourseCat.get(5).getmCourseCategoryNameBn());
                    mCourseTextView7. setText(detailListMainActivityCourseCat.get(6).getmCourseCategoryNameBn());
                    mCourseTextView8. setText(detailListMainActivityCourseCat.get(7).getmCourseCategoryNameBn());
                    mCourseTextView9. setText(detailListMainActivityCourseCat.get(8).getmCourseCategoryNameBn());
                    mCourseTextView10.setText(detailListMainActivityCourseCat.get(9).getmCourseCategoryNameBn());*/


                    String testing1="";
                }
                catch (Exception ex){
                    Log.d("", "onPostExecute: ");
                }

            }
            catch (Exception ex){
                Log.d("", "onPostExecute: ");
            }


            /*LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
            recyclerView.setLayoutManager(layoutManager);
            adapter=new RecyclerViewAdapterRecommendedCategoryList.MyViewHolder(GlobalVar.gRecommendedCategories, GlobalVar.gRecommendedCategoriesEn , GlobalVar.gRecommendedCategoriesId, mContext);
            recyclerView.setAdapter(adapter);*/

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
