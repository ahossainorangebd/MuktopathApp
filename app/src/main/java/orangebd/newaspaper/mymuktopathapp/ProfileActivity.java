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
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.lang.reflect.Method;
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

    private SessionManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mContext=this;

        sm= new SessionManager(mContext);
        HashMap<String, String> mSpInfo=sm.getUserDetails();

        String emailFromCache = mSpInfo.get("email");
        String passwordFromChache = mSpInfo.get("password");

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

        mUserCoverImage=findViewById(R.id.userCoverPhotoId);
        mUserProfileImage=findViewById(R.id.userProfileImageId);

        allCourseBtn = findViewById(R.id.allCourseBtnId);
        recomendedBtn = findViewById(R.id.recomendedBtnId);
        myPageBtn = findViewById(R.id.myPageBtnId);
        downloadsBtn = findViewById(R.id.downloadsBtnId);
        profileBtn = findViewById(R.id.profilePageBtnId);

        mUserName=findViewById(R.id.userNameId);
        mUserName.setText(GlobalVar.gName);

        ImageView mLogOutBtn = findViewById(R.id.logOutBtnId);
        mLogOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showPopUpImageBox();
            }
        });


        String mUserCoverPhoto = GlobalVar.gBaseUrlForProfile + "/images/profile/" + GlobalVar.courseContentDetailList.get(0).getmUserInformationArrayList().get(0).getmUserCoverPhoto();
        String mUserProfilePhoto = GlobalVar.gBaseUrlForProfile + "/images/profile/" + GlobalVar.courseContentDetailList.get(0).getmUserInformationArrayList().get(0).getmUserProfilePhoto();

        try {
            Picasso.with(mContext).load(mUserCoverPhoto).into(mUserCoverImage);
        }
        catch (Exception ex){}

        try {
            Picasso.with(mContext).load(mUserProfilePhoto).into(mUserProfileImage);
        }
        catch (Exception ex){}





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


    private void showPopUpImageBox()
    {
        // custom dialog
        final Dialog dialog = new Dialog(mContext, R.style.DialogCustomTheme);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popupwindowforlogout);

        Button mLogOutYes=dialog.findViewById(R.id.logOutYes);
        mLogOutYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sm.logoutUser();

            }
        });

        Button mLogOutNo=dialog.findViewById(R.id.logOutNo);
        mLogOutNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

}
