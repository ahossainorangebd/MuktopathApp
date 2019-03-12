package orangebd.newaspaper.mymuktopathapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

    private RelativeLayout chooseCategory1;
    private LinearLayout category1Expand;

    private RelativeLayout chooseCategory2;
    private LinearLayout category2Expand;

    private RelativeLayout chooseCategory3;
    private LinearLayout category3Expand;

    private RelativeLayout chooseCategory4;
    private LinearLayout category4Expand;

    private RelativeLayout chooseCategory5;
    private LinearLayout category5Expand;

    private RelativeLayout chooseCategory6;
    private LinearLayout category6Expand;

    private RelativeLayout chooseCategory7;
    private LinearLayout category7Expand;

    private RelativeLayout chooseCategory8;
    private LinearLayout category8Expand;

    private RelativeLayout chooseCategory9;
    private LinearLayout category9Expand;

    private RelativeLayout chooseCategory10;
    private LinearLayout category10Expand;

    private RelativeLayout chooseCategory11;
    private LinearLayout category11Expand;
    private boolean isExpand;

    private Button mNextBtn;

    private Context mContext;

    private RotateAnimation rotateAnimation;


    private ImageView expandIcon1;
    private ImageView expandIcon2;
    private ImageView expandIcon3;
    private ImageView expandIcon4;
    private ImageView expandIcon5;
    private ImageView expandIcon6;
    private ImageView expandIcon7;
    private ImageView expandIcon8;
    private ImageView expandIcon9;
    private ImageView expandIcon10;
    private ImageView expandIcon11;


    private TextView mCourseText1;
    private TextView mCourseText2;
    private TextView mCourseText3;
    private TextView mCourseText4;
    private TextView mCourseText5;
    private TextView mCourseText6;
    private TextView mCourseText7;
    private TextView mCourseText8;
    private TextView mCourseText9;
    private TextView mCourseText10;

    String urlGetCourseCats = "http://api.muktopaath.orangebd.com/api/course-categories";

    //All the detail Lists
    private ArrayList<DetailDataModelAll> detailListMainActivityCourseCat;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_acategory);

        mContext=this;

        expandIcon1=findViewById(R.id.expandIconId1);
        expandIcon2=findViewById(R.id.expandIconId2);
        expandIcon3=findViewById(R.id.expandIconId3);
        expandIcon4=findViewById(R.id.expandIconId4);
        expandIcon5=findViewById(R.id.expandIconId5);
        expandIcon6=findViewById(R.id.expandIconId6);
        expandIcon7=findViewById(R.id.expandIconId7);
        expandIcon8=findViewById(R.id.expandIconId8);
        expandIcon9=findViewById(R.id.expandIconId9);
        expandIcon10=findViewById(R.id.expandIconId10);
        expandIcon11=findViewById(R.id.expandIconId11);


        mCourseText1=findViewById(R.id.courseName1);
        mCourseText2=findViewById(R.id.courseName2);
        mCourseText3=findViewById(R.id.courseName3);
        mCourseText4=findViewById(R.id.courseName4);
        mCourseText5=findViewById(R.id.courseName5);
        mCourseText6=findViewById(R.id.courseName6);
        mCourseText7=findViewById(R.id.courseName7);
        mCourseText8=findViewById(R.id.courseName8);
        mCourseText9=findViewById(R.id.courseName9);
        mCourseText10=findViewById(R.id.courseName10);

        category1Expand=findViewById(R.id.category1Expand);
        chooseCategory1=findViewById(R.id.chooseCategory1);

        category2Expand=findViewById(R.id.category2Expand);
        chooseCategory2=findViewById(R.id.chooseCategory2);

        category3Expand=findViewById(R.id.category3Expand);
        chooseCategory3=findViewById(R.id.chooseCategory3);

        category4Expand=findViewById(R.id.category4Expand);
        chooseCategory4=findViewById(R.id.chooseCategory4);

        category5Expand=findViewById(R.id.category5Expand);
        chooseCategory5=findViewById(R.id.chooseCategory5);

        category6Expand=findViewById(R.id.category6Expand);
        chooseCategory6=findViewById(R.id.chooseCategory6);

        category7Expand=findViewById(R.id.category7Expand);
        chooseCategory7=findViewById(R.id.chooseCategory7);

        category8Expand=findViewById(R.id.category8Expand);
        chooseCategory8=findViewById(R.id.chooseCategory8);

        category9Expand=findViewById(R.id.category9Expand);
        chooseCategory9=findViewById(R.id.chooseCategory9);

        category10Expand=findViewById(R.id.category10Expand);
        chooseCategory10=findViewById(R.id.chooseCategory10);

        category11Expand=findViewById(R.id.category11Expand);
        chooseCategory11=findViewById(R.id.chooseCategory11);

        mNextBtn=findViewById(R.id.nextBtnId);
        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, MyPageActivity.class);
                v.getContext().startActivity(i);
            }
        });

        chooseCategory1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (isExpand==true){
                    category1Expand.setVisibility(View.GONE);
                    chooseCategory1.setBackgroundColor(Color.parseColor("#ffffff"));

                    // rotate clockwise
                    rotateAnimation = new RotateAnimation(90, 0, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                    rotateAnimation.setDuration(200);
                    rotateAnimation.setFillAfter(true);
                    expandIcon1.startAnimation(rotateAnimation);

                    isExpand=false;

                }

                else {
                    category1Expand.setVisibility(View.VISIBLE);
                    chooseCategory1.setBackgroundColor(Color.parseColor("#bfebff"));

                    rotateAnimation = new RotateAnimation( 0, 90, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                    rotateAnimation.setDuration(200);
                    rotateAnimation.setFillAfter(true);
                    expandIcon1.startAnimation(rotateAnimation);

                    isExpand=true;
                }

            }
        });

        chooseCategory2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isExpand==true){
                    category2Expand.setVisibility(View.GONE);
                    chooseCategory2.setBackgroundColor(Color.parseColor("#ffffff"));

                    // rotate clockwise
                    rotateAnimation = new RotateAnimation(90, 0, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                    rotateAnimation.setDuration(200);
                    rotateAnimation.setFillAfter(true);
                    expandIcon2.startAnimation(rotateAnimation);

                    isExpand=false;
                }
                else {
                    category2Expand.setVisibility(View.VISIBLE);
                    chooseCategory2.setBackgroundColor(Color.parseColor("#bfebff"));

                    rotateAnimation = new RotateAnimation( 0, 90, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                    rotateAnimation.setDuration(200);
                    rotateAnimation.setFillAfter(true);
                    expandIcon2.startAnimation(rotateAnimation);

                    isExpand=true;
                }
            }
        });

        chooseCategory3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (isExpand==true){
                    category3Expand.setVisibility(View.GONE);
                    chooseCategory3.setBackgroundColor(Color.parseColor("#ffffff"));

                    // rotate clockwise
                    rotateAnimation = new RotateAnimation(90, 0, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                    rotateAnimation.setDuration(200);
                    rotateAnimation.setFillAfter(true);
                    expandIcon3.startAnimation(rotateAnimation);

                    isExpand=false;

                }
                else {
                    category3Expand.setVisibility(View.VISIBLE);
                    chooseCategory3.setBackgroundColor(Color.parseColor("#bfebff"));

                    rotateAnimation = new RotateAnimation( 0, 90, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                    rotateAnimation.setDuration(200);
                    rotateAnimation.setFillAfter(true);
                    expandIcon3.startAnimation(rotateAnimation);

                    isExpand=true;
                }

            }
        });

        chooseCategory4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (isExpand==true){
                    category4Expand.setVisibility(View.GONE);
                    chooseCategory4.setBackgroundColor(Color.parseColor("#ffffff"));

                    // rotate clockwise
                    rotateAnimation = new RotateAnimation(90, 0, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                    rotateAnimation.setDuration(200);
                    rotateAnimation.setFillAfter(true);
                    expandIcon4.startAnimation(rotateAnimation);

                    isExpand=false;

                }
                else {
                    category4Expand.setVisibility(View.VISIBLE);
                    chooseCategory4.setBackgroundColor(Color.parseColor("#bfebff"));

                    rotateAnimation = new RotateAnimation( 0, 90, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                    rotateAnimation.setDuration(200);
                    rotateAnimation.setFillAfter(true);
                    expandIcon4.startAnimation(rotateAnimation);

                    isExpand=true;
                }

            }
        });

        chooseCategory5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (isExpand==true){
                    category5Expand.setVisibility(View.GONE);
                    chooseCategory5.setBackgroundColor(Color.parseColor("#ffffff"));

                    // rotate clockwise
                    rotateAnimation = new RotateAnimation(90, 0, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                    rotateAnimation.setDuration(200);
                    rotateAnimation.setFillAfter(true);
                    expandIcon5.startAnimation(rotateAnimation);

                    isExpand=false;

                }
                else {
                    category5Expand.setVisibility(View.VISIBLE);
                    chooseCategory5.setBackgroundColor(Color.parseColor("#bfebff"));

                    rotateAnimation = new RotateAnimation( 0, 90, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                    rotateAnimation.setDuration(200);
                    rotateAnimation.setFillAfter(true);
                    expandIcon5.startAnimation(rotateAnimation);

                    isExpand=true;
                }

            }
        });

        chooseCategory6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (isExpand==true){
                    category6Expand.setVisibility(View.GONE);
                    chooseCategory6.setBackgroundColor(Color.parseColor("#ffffff"));

                    // rotate clockwise
                    rotateAnimation = new RotateAnimation(90, 0, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                    rotateAnimation.setDuration(200);
                    rotateAnimation.setFillAfter(true);
                    expandIcon6.startAnimation(rotateAnimation);

                    isExpand=false;

                }
                else {
                    category6Expand.setVisibility(View.VISIBLE);
                    chooseCategory6.setBackgroundColor(Color.parseColor("#bfebff"));

                    rotateAnimation = new RotateAnimation( 0, 90, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                    rotateAnimation.setDuration(200);
                    rotateAnimation.setFillAfter(true);
                    expandIcon6.startAnimation(rotateAnimation);

                    isExpand=true;
                }

            }
        });

        chooseCategory7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (isExpand==true){
                    category7Expand.setVisibility(View.GONE);
                    chooseCategory7.setBackgroundColor(Color.parseColor("#ffffff"));

                    // rotate clockwise
                    rotateAnimation = new RotateAnimation(90, 0, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                    rotateAnimation.setDuration(200);
                    rotateAnimation.setFillAfter(true);
                    expandIcon7.startAnimation(rotateAnimation);

                    isExpand=false;

                }
                else {
                    category7Expand.setVisibility(View.VISIBLE);
                    chooseCategory7.setBackgroundColor(Color.parseColor("#bfebff"));

                    rotateAnimation = new RotateAnimation( 0, 90, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                    rotateAnimation.setDuration(200);
                    rotateAnimation.setFillAfter(true);
                    expandIcon7.startAnimation(rotateAnimation);

                    isExpand=true;
                }

            }
        });

        chooseCategory8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (isExpand==true){
                    category8Expand.setVisibility(View.GONE);
                    chooseCategory8.setBackgroundColor(Color.parseColor("#ffffff"));

                    // rotate clockwise
                    rotateAnimation = new RotateAnimation(90, 0, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                    rotateAnimation.setDuration(200);
                    rotateAnimation.setFillAfter(true);
                    expandIcon8.startAnimation(rotateAnimation);

                    isExpand=false;

                }
                else {
                    category8Expand.setVisibility(View.VISIBLE);
                    chooseCategory8.setBackgroundColor(Color.parseColor("#bfebff"));

                    rotateAnimation = new RotateAnimation( 0, 90, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                    rotateAnimation.setDuration(200);
                    rotateAnimation.setFillAfter(true);
                    expandIcon8.startAnimation(rotateAnimation);

                    isExpand=true;
                }

            }
        });

        chooseCategory9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (isExpand==true){
                    category9Expand.setVisibility(View.GONE);
                    chooseCategory9.setBackgroundColor(Color.parseColor("#ffffff"));

                    // rotate clockwise
                    rotateAnimation = new RotateAnimation(90, 0, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                    rotateAnimation.setDuration(200);
                    rotateAnimation.setFillAfter(true);
                    expandIcon9.startAnimation(rotateAnimation);

                    isExpand=false;

                }
                else {
                    category9Expand.setVisibility(View.VISIBLE);
                    chooseCategory9.setBackgroundColor(Color.parseColor("#bfebff"));

                    rotateAnimation = new RotateAnimation( 0, 90, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                    rotateAnimation.setDuration(200);
                    rotateAnimation.setFillAfter(true);
                    expandIcon9.startAnimation(rotateAnimation);

                    isExpand=true;
                }

            }
        });

        chooseCategory10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (isExpand==true){
                    category10Expand.setVisibility(View.GONE);
                    chooseCategory10.setBackgroundColor(Color.parseColor("#ffffff"));

                    // rotate clockwise
                    rotateAnimation = new RotateAnimation(90, 0, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                    rotateAnimation.setDuration(200);
                    rotateAnimation.setFillAfter(true);
                    expandIcon10.startAnimation(rotateAnimation);

                    isExpand=false;

                }
                else {
                    category10Expand.setVisibility(View.VISIBLE);
                    chooseCategory10.setBackgroundColor(Color.parseColor("#bfebff"));

                    rotateAnimation = new RotateAnimation( 0, 90, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                    rotateAnimation.setDuration(200);
                    rotateAnimation.setFillAfter(true);
                    expandIcon10.startAnimation(rotateAnimation);

                    isExpand=true;
                }

            }
        });

        chooseCategory11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (isExpand==true){
                    category11Expand.setVisibility(View.GONE);
                    chooseCategory11.setBackgroundColor(Color.parseColor("#ffffff"));

                    // rotate clockwise
                    rotateAnimation = new RotateAnimation(90, 0, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                    rotateAnimation.setDuration(200);
                    rotateAnimation.setFillAfter(true);
                    expandIcon11.startAnimation(rotateAnimation);

                    isExpand=false;

                }
                else {
                    category11Expand.setVisibility(View.VISIBLE);
                    chooseCategory11.setBackgroundColor(Color.parseColor("#bfebff"));

                    rotateAnimation = new RotateAnimation( 0, 90, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                    rotateAnimation.setDuration(200);
                    rotateAnimation.setFillAfter(true);
                    expandIcon11.startAnimation(rotateAnimation);

                    isExpand=true;
                }

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

                        modelCourseCatAll.setmCourseCategoryId(courseId);
                        modelCourseCatAll.setmCourseCategoryNameEn(courseNameEn);
                        modelCourseCatAll.setmCourseCategoryNameBn(courseNameBn);

                        detailListMainActivityCourseCat.add(modelCourseCatAll);


                        String aaaa="";
                    }
                } catch (Exception ex) {
                    Log.d("", "onResponse: ");
                }

                try {
                    mCourseText1. setText(detailListMainActivityCourseCat.get(0).getmCourseCategoryNameBn());
                    mCourseText2. setText(detailListMainActivityCourseCat.get(1).getmCourseCategoryNameBn());
                    mCourseText3. setText(detailListMainActivityCourseCat.get(2).getmCourseCategoryNameBn());
                    mCourseText4. setText(detailListMainActivityCourseCat.get(3).getmCourseCategoryNameBn());
                    mCourseText5. setText(detailListMainActivityCourseCat.get(4).getmCourseCategoryNameBn());
                    mCourseText6. setText(detailListMainActivityCourseCat.get(5).getmCourseCategoryNameBn());
                    mCourseText7. setText(detailListMainActivityCourseCat.get(6).getmCourseCategoryNameBn());
                    mCourseText8. setText(detailListMainActivityCourseCat.get(7).getmCourseCategoryNameBn());
                    mCourseText9. setText(detailListMainActivityCourseCat.get(8).getmCourseCategoryNameBn());
                    mCourseText10.setText(detailListMainActivityCourseCat.get(9).getmCourseCategoryNameBn());
                }
                catch (Exception ex){
                    Log.d("", "onPostExecute: ");
                }

            }
            catch (Exception ex){
                Log.d("", "onPostExecute: ");
            }
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
