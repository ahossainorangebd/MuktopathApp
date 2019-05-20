package orangebd.newaspaper.mymuktopathapp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.provider.SyncStateContract;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

public class ProfileActivity extends AppCompatActivity {

    private Context mContext;

    // Button Layout of footer

    private ImageView mUserProfileImage;
    private ImageView mUserCoverImage;

    private LinearLayout allCourseBtn;
    private LinearLayout recomendedBtn;
    private LinearLayout myPageBtn;
    private LinearLayout downloadsBtn;
    private LinearLayout profileBtn;

    private Button mSplashActvtySearchSomething;

    private TextView mUserName;

    private TextView mProCompletenessTv;

    private String enrollCourseCompltness;

    private LinearLayout mNotCompleteCourseProfile;
    private LinearLayout mYesCompleteCourseProfile;

    private TextView mNumberOfCompletedCourse;
    private TextView mWishListNumberTV;
    private TextView mIncompleteTV;
    private TextView mNumberOfRunningCourseTV;



    private LinearLayout mWishListCourseButton;
    private LinearLayout mEndedCourseButton;
    private LinearLayout mNonEndedCourseButton;
    private LinearLayout mRunningCourseButton;


    private int backTapCount;

    private ImageView mSettingsBtn;

    private TextView mGamificationPoint;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mContext=this;

        /*View view = LayoutInflater.from(mContext).inflate(R.layout.custom_logodetails, null, false);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(view);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#7a19aa")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/
        getSupportActionBar().hide();

        View view2 = findViewById(android.R.id.content);
        Animation mLoadAnimation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in);
        mLoadAnimation.setDuration(1000);
        view2.startAnimation(mLoadAnimation);

        mGamificationPoint=findViewById(R.id.gamificationPoint);

        mGamificationPoint.setText(GlobalVar.gGamificationPoint);


        mSettingsBtn=findViewById(R.id.settingsBtn);
        mSettingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(mContext, SettingsActivity.class);
                v.getContext().startActivity(i);
            }
        });


        /** declaring ids of 4 buttons
         * */

        mWishListCourseButton=findViewById(R.id.wishListButtonId);
        mEndedCourseButton=findViewById(R.id.endedCourseBtnId);
        mNonEndedCourseButton=findViewById(R.id.nonEndedCourseBtnId);
        mRunningCourseButton=findViewById(R.id.runningCourseBtnId);

        mWishListCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                GlobalVar.isRedirectFromProfileWishListBtn=true;

                GlobalVar.isRedirectFromProfileEndedBtn=false;
                GlobalVar.isRedirectFromProfileNonEndedBtn=false;
                GlobalVar.isRedirectFromProfileRunningBtn=false;

                Intent i = new Intent(mContext, MyPageActivity.class);
                v.getContext().startActivity(i);
            }
        });

        mEndedCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GlobalVar.isRedirectFromProfileEndedBtn=true;

                GlobalVar.isRedirectFromProfileWishListBtn=false;
                GlobalVar.isRedirectFromProfileNonEndedBtn=false;
                GlobalVar.isRedirectFromProfileRunningBtn=false;

                Intent i = new Intent(mContext, MyPageActivity.class);
                v.getContext().startActivity(i);
            }
        });

        mNonEndedCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GlobalVar.isRedirectFromProfileNonEndedBtn=true;

                GlobalVar.isRedirectFromProfileEndedBtn=false;
                GlobalVar.isRedirectFromProfileWishListBtn=false;
                GlobalVar.isRedirectFromProfileRunningBtn=false;

                Intent i = new Intent(mContext, MyPageActivity.class);
                v.getContext().startActivity(i);
            }
        });

        mRunningCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GlobalVar.isRedirectFromProfileRunningBtn=true;

                GlobalVar.isRedirectFromProfileNonEndedBtn=false;
                GlobalVar.isRedirectFromProfileEndedBtn=false;
                GlobalVar.isRedirectFromProfileWishListBtn=false;


                Intent i = new Intent(mContext, MyPageActivity.class);
                v.getContext().startActivity(i);
            }
        });



        /** Checking a user's profile completed or not
         * */

        ArrayList<DetailDataModelCoursesDetailContents> mProfileInfoArrayList = GlobalVar.courseContentDetailList.get(0).getmArraylistProfileInfo();


        String totalEnrolledCourse = mProfileInfoArrayList.get(0).getmTotalEnrollment();
        String getmCourseCompleted = mProfileInfoArrayList.get(0).getmCourseCompleted();

        int totalIncompletedCourseInt = Integer.parseInt(totalEnrolledCourse) - Integer.parseInt(getmCourseCompleted);

        //String totalIncompletedCourse =  totalIncompletedCourseInt;
        String totalWishListCourse =  convertEngToBn(String.valueOf(GlobalVar.gWishListNumber));

        for (int dynamicCourseNo=0; dynamicCourseNo < GlobalVar.gEnrollCourseId.size(); dynamicCourseNo++) {

            enrollCourseCompltness=GlobalVar.gEnrollCourseId.get(dynamicCourseNo).getmEcCompleteness();

            if(enrollCourseCompltness.equalsIgnoreCase("100")) {

                GlobalVar.isCompletedCourseActivity=true;
            }
        }

        mNotCompleteCourseProfile=findViewById(R.id.notCompleteCourseProfile);
        mYesCompleteCourseProfile=findViewById(R.id.yesCourseCompleteLayout);

        mNumberOfCompletedCourse=findViewById(R.id.numberOfCompletedCourse);
        mWishListNumberTV=findViewById(R.id.numberOfWishlistCourse);
        mIncompleteTV=findViewById(R.id.numberOfIncompletedCourse);
        mNumberOfRunningCourseTV=findViewById(R.id.numberOfRunningCourse);


        mWishListNumberTV.setText(totalWishListCourse);
        mIncompleteTV.setText(convertEngToBn(String.valueOf(GlobalVar.gIncompletedCourseCount)));
        mNumberOfRunningCourseTV.setText(convertEngToBn(String.valueOf(totalIncompletedCourseInt)));

        if(Integer.parseInt(getmCourseCompleted)>0) {
            mYesCompleteCourseProfile.setVisibility(View.VISIBLE);
            mNumberOfCompletedCourse.setText(convertEngToBn(getmCourseCompleted));


        }
        else {
            mNotCompleteCourseProfile.setVisibility(View.VISIBLE);
        }

        /** Progress of progressBar
         * */

        String abcdpp="";

        if(GlobalVar.gProfileCompleteness==null){
            GlobalVar.gProfileCompleteness="0";
        }

        String enrollCourseCompltness=GlobalVar.gProfileCompleteness;

        try {
            int index = enrollCourseCompltness.indexOf(".");
            enrollCourseCompltness = enrollCourseCompltness.substring(0,index);
        }
        catch (Exception ex){
            Log.d("", "onCreateView: ");
        }

        ProgressBar mProgBar=findViewById(R.id.determinateBar);
        mProgBar.getIndeterminateDrawable().setColorFilter(0xFF009109,android.graphics.PorterDuff.Mode.MULTIPLY);
        mProgBar.setProgress(Integer.parseInt(enrollCourseCompltness));


        mProCompletenessTv=findViewById(R.id.proCompleteness);
        mProCompletenessTv.setText(convertEngToBn(GlobalVar.gProfileCompleteness));


        mUserCoverImage=findViewById(R.id.userCoverPhotoId);
        mUserProfileImage=findViewById(R.id.userProfileImageId);

        allCourseBtn = findViewById(R.id.allCourseBtnId);
        recomendedBtn = findViewById(R.id.recomendedBtnId);
        myPageBtn = findViewById(R.id.myPageBtnId);
        downloadsBtn = findViewById(R.id.downloadsBtnId);
        profileBtn = findViewById(R.id.profilePageBtnId);

        mUserName=findViewById(R.id.userNameId);
        mUserName.setText(GlobalVar.gName);


        String mUserCoverPhoto = GlobalVar.gBaseUrlForProfile + "/images/profile/" + GlobalVar.courseContentDetailList.get(0).getmUserInformationArrayList().get(0).getmUserCoverPhoto();
        String mUserProfilePhoto = GlobalVar.gBaseUrlForProfile + "/images/profile/" + GlobalVar.courseContentDetailList.get(0).getmUserInformationArrayList().get(0).getmUserProfilePhoto();

        try {
            Picasso.with(mContext).load(mUserCoverPhoto).into(mUserCoverImage);
        }
        catch (Exception ex){}

        String testingabcd="";

        String imgprofileurl = GlobalVar.courseContentDetailList.get(0).getmUserInformationArrayList().get(0).getmUserProfilePhoto();

        if(imgprofileurl=="null"){

            mUserProfileImage.setImageResource(R.drawable.profileicon);
        }
        else{
            try {
                Picasso.with(mContext).load(mUserProfilePhoto).into(mUserProfileImage);
            }
            catch (Exception ex){}
        }






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

        recomendedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GlobalVar.isRedirectFromProfileRunningBtn=false;
                GlobalVar.isRedirectFromProfileNonEndedBtn=false;
                GlobalVar.isRedirectFromProfileEndedBtn=false;
                GlobalVar.isRedirectFromProfileWishListBtn=false;

                Intent i = new Intent(mContext, RecomendedActivity.class);
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

        mSplashActvtySearchSomething=findViewById(R.id.splashActvtySearchSomething);
        mSplashActvtySearchSomething.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i=new Intent(mContext,MainActivity.class);
                v.getContext().startActivity(i);
            }
        });
    }


    @Override
    protected boolean onPrepareOptionsPanel(View view, Menu menu) {
        if(menu != null){
            if(menu.getClass().getSimpleName().equals("MenuBuilder")){
                try{
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                }
                catch(NoSuchMethodException e){
                    Log.e(SyncStateContract.Constants.DATA, "onMenuOpened", e);
                }
                catch(Exception e){
                    throw new RuntimeException(e);
                }
            }
        }
        return true;
        //return super.onPrepareOptionsPanel(view, menu);
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

    public String convertEngToBn(String num){

        num = num.replace("0","০");
        num = num.replace("1","১");
        num = num.replace("2","২");
        num = num.replace("3","৩");
        num = num.replace("4","৪");
        num = num.replace("5","৫");
        num = num.replace("6","৬");
        num = num.replace("7","৭");
        num = num.replace("8","৮");
        num = num.replace("9","৯");

        return num;
    }

    /*@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {

            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:

                    backTapCount++;

                    if(backTapCount>=2){
                        this.finish();
                    }
                    else if (backTapCount==1){
                        //A warning toast msg for double tapping exit
                        Toast.makeText(getApplicationContext(),"Press back button again to exit from the app.", Toast.LENGTH_LONG).show();
                    }

                    return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }*/

}
