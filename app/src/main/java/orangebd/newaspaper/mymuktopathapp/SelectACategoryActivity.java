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
import android.widget.CheckBox;
import android.widget.CompoundButton;
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


    private CheckBox mCourseCheckBox1;
    private CheckBox mCourseCheckBox2;
    private CheckBox mCourseCheckBox3;
    private CheckBox mCourseCheckBox4;
    private CheckBox mCourseCheckBox5;
    private CheckBox mCourseCheckBox6;
    private CheckBox mCourseCheckBox7;
    private CheckBox mCourseCheckBox8;
    private CheckBox mCourseCheckBox9;
    private CheckBox mCourseCheckBox10;


    private TextView mCourseHiddenEn1;
    private TextView mCourseHiddenEn2;
    private TextView mCourseHiddenEn3;
    private TextView mCourseHiddenEn4;


    private TextView mCourseHiddenEnId1;
    private TextView mCourseHiddenEnId2;
    private TextView mCourseHiddenEnId3;
    private TextView mCourseHiddenEnId4;


    String urlGetCourseCats = GlobalVar.gApiBaseUrl + "/api/course-categories";

    //All the detail Lists
    private ArrayList<DetailDataModelAll> detailListMainActivityCourseCat;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_acategory);

        mContext=this;

        mCourseCheckBox1=findViewById(R.id.firstCatCheckBoxId);
        mCourseCheckBox2=findViewById(R.id.SecondCatCheckBoxId);
        mCourseCheckBox3=findViewById(R.id.ThirdCatCheckBoxId);
        mCourseCheckBox4=findViewById(R.id.fourthCatCheckBoxId);
        //TODO
        //TODO
        //mCourseCheckBox5=findViewById(R.id.fifthCatCheckBoxId);
        //mCourseCheckBox6=findViewById(R.id.SixthCatCheckBoxId);

        //For hidden en texts ens
        mCourseHiddenEn1=findViewById(R.id.CourseHiddenEnId1);
        mCourseHiddenEn2=findViewById(R.id.CourseHiddenEnId2);
        mCourseHiddenEn3=findViewById(R.id.CourseHiddenEnId3);
        mCourseHiddenEn4=findViewById(R.id.CourseHiddenEnId4);

        //For hidden en texts ids
        mCourseHiddenEnId1=findViewById(R.id.CourseHiddenEnIdId1);
        mCourseHiddenEnId2=findViewById(R.id.CourseHiddenEnIdId2);
        mCourseHiddenEnId3=findViewById(R.id.CourseHiddenEnIdId3);
        mCourseHiddenEnId4=findViewById(R.id.CourseHiddenEnIdId4);

        //

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

        GlobalVar.gRecommendedCategories = new ArrayList<>();

        GlobalVar.gRecommendedCategoriesEn = new ArrayList<>();
        GlobalVar.gRecommendedCategoriesId = new ArrayList<>();

        mCourseCheckBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    CharSequence checkedCat1=buttonView.getText();
                    String checkedStrCat1=checkedCat1.toString();
                    GlobalVar.gRecommendedCategories.add(checkedStrCat1);

                    CharSequence checkedStrCatEnCs1=mCourseHiddenEn1.getText();
                    String checkedStrCatEn1=checkedStrCatEnCs1.toString();
                    GlobalVar.gRecommendedCategoriesEn.add(checkedStrCatEn1);

                    CharSequence checkedStrCatEnCsId1=mCourseHiddenEnId1.getText();
                    String checkedStrCatEnId1=checkedStrCatEnCsId1.toString();
                    GlobalVar.gRecommendedCategoriesId.add(checkedStrCatEnId1);
                }
            }
        });

        mCourseCheckBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    CharSequence checkedCat2=buttonView.getText();
                    String checkedStrCat2=checkedCat2.toString();
                    GlobalVar.gRecommendedCategories.add(checkedStrCat2);

                    CharSequence checkedStrCatEnCs2=mCourseHiddenEn2.getText();
                    String checkedStrCatEn2=checkedStrCatEnCs2.toString();
                    GlobalVar.gRecommendedCategoriesEn.add(checkedStrCatEn2);

                    CharSequence checkedStrCatEnCsId2=mCourseHiddenEnId2.getText();
                    String checkedStrCatEnId2=checkedStrCatEnCsId2.toString();
                    GlobalVar.gRecommendedCategoriesId.add(checkedStrCatEnId2);
                }
            }
        });

        mCourseCheckBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    CharSequence checkedCat3=buttonView.getText();
                    String checkedStrCat3=checkedCat3.toString();
                    GlobalVar.gRecommendedCategories.add(checkedStrCat3);

                    CharSequence checkedStrCatEnCs3=mCourseHiddenEn3.getText();
                    String checkedStrCatEn3=checkedStrCatEnCs3.toString();
                    GlobalVar.gRecommendedCategoriesEn.add(checkedStrCatEn3);

                    CharSequence checkedStrCatEnCsId3=mCourseHiddenEnId3.getText();
                    String checkedStrCatEnId3=checkedStrCatEnCsId3.toString();
                    GlobalVar.gRecommendedCategoriesId.add(checkedStrCatEnId3);
                }
            }
        });

        mCourseCheckBox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    CharSequence checkedCat4=buttonView.getText();
                    String checkedStrCat4=checkedCat4.toString();
                    GlobalVar.gRecommendedCategories.add(checkedStrCat4);

                    CharSequence checkedStrCatEnCs4=mCourseHiddenEn4.getText();
                    String checkedStrCatEn4=checkedStrCatEnCs4.toString();
                    GlobalVar.gRecommendedCategoriesEn.add(checkedStrCatEn4);

                    CharSequence checkedStrCatEnCsId4=mCourseHiddenEnId4.getText();
                    String checkedStrCatEnId4=checkedStrCatEnCsId4.toString();
                    GlobalVar.gRecommendedCategoriesId.add(checkedStrCatEnId4);
                }
            }
        });

        //TODO
        //TODO
        /*mCourseCheckBox5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    CharSequence checkedCat5=buttonView.getText();

                    String checkedStrCat2=checkedCat5.toString();

                    GlobalVar.gRecommendedCategories.add(checkedStrCat2);
                }
            }
        });

        mCourseCheckBox6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    CharSequence checkedCat6=buttonView.getText();

                    String checkedStrCat2=checkedCat6.toString();

                    GlobalVar.gRecommendedCategories.add(checkedStrCat2);

                    String text="abcd";
                }
            }
        });*/


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
                    mCourseCheckBox1.setText(detailListMainActivityCourseCat.get(0).getmCourseCategoryNameBn());
                    mCourseCheckBox2.setText(detailListMainActivityCourseCat.get(1).getmCourseCategoryNameBn());
                    mCourseCheckBox3.setText(detailListMainActivityCourseCat.get(2).getmCourseCategoryNameBn());
                    mCourseCheckBox4.setText(detailListMainActivityCourseCat.get(3).getmCourseCategoryNameBn());

                    mCourseHiddenEn1.setText(detailListMainActivityCourseCat.get(0).getmCourseCategoryNameEn());
                    mCourseHiddenEn2.setText(detailListMainActivityCourseCat.get(1).getmCourseCategoryNameEn());
                    mCourseHiddenEn3.setText(detailListMainActivityCourseCat.get(2).getmCourseCategoryNameEn());
                    mCourseHiddenEn4.setText(detailListMainActivityCourseCat.get(3).getmCourseCategoryNameEn());

                    mCourseHiddenEnId1.setText(detailListMainActivityCourseCat.get(0).getmCourseCategoryId());
                    mCourseHiddenEnId2.setText(detailListMainActivityCourseCat.get(1).getmCourseCategoryId());
                    mCourseHiddenEnId3.setText(detailListMainActivityCourseCat.get(2).getmCourseCategoryId());
                    mCourseHiddenEnId4.setText(detailListMainActivityCourseCat.get(3).getmCourseCategoryId());

                    /*
                    mCourseCheckBox5.setText(detailListMainActivityCourseCat.get(4).getmCourseCategoryNameBn());
                    mCourseCheckBox6.setText(detailListMainActivityCourseCat.get(5).getmCourseCategoryNameBn());
                    mCourseCheckBox7. setText(detailListMainActivityCourseCat.get(6).getmCourseCategoryNameBn());
                    mCourseCheckBox8. setText(detailListMainActivityCourseCat.get(7).getmCourseCategoryNameBn());
                    mCourseCheckBox9. setText(detailListMainActivityCourseCat.get(8).getmCourseCategoryNameBn());
                    mCourseCheckBox10.setText(detailListMainActivityCourseCat.get(9).getmCourseCategoryNameBn());*/
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
