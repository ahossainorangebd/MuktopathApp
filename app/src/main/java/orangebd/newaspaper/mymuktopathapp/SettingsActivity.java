package orangebd.newaspaper.mymuktopathapp;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.provider.CalendarContract;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class SettingsActivity extends AppCompatActivity {

    private Context mContext;

    private Switch mOnOffPushNotificationSwitch;
    private Switch mOnDownloadOnWify;
    private Switch mOnOffSyncCalender;
    private Switch mOnDeleteCourse;

    private LinearLayout mSuppoerHelp;
    private LinearLayout mTermsCondi;
    private LinearLayout mPrivacyPoli;
    private LinearLayout mFeedBackId;

    private TextView mCustomContentTitle;

    private SessionManager sm;

    private String feedbackDetails;
    private TextInputEditText feedbackField;

    private TextView mVersionName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mContext=this;

        final View view = LayoutInflater.from(mContext).inflate(R.layout.custom_logodetails_settings, null, false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(view);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#7a19aa")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        sm= new SessionManager(mContext);
        HashMap<String, String> mSpInfo=sm.getUserDetails();

        String emailFromCache = mSpInfo.get("email");
        String passwordFromChache = mSpInfo.get("password");

        mCustomContentTitle=view.findViewById(R.id.muktoCustomEmail);

        if(GlobalVar.gEmail=="null"){
            mCustomContentTitle.setText(GlobalVar.gMobile);
        }
        else{
            mCustomContentTitle.setText(GlobalVar.gEmail);
        }

        mOnOffPushNotificationSwitch=findViewById(R.id.onOffPushNotification);
        mOnOffPushNotificationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked) {

                    Intent intent = new Intent();
                    intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
                    intent.putExtra("app_package", getPackageName());
                    intent.putExtra("app_uid", getApplicationInfo().uid);
                    intent.putExtra("android.provider.extra.APP_PACKAGE", getPackageName());
                    startActivity(intent);
                }
            }
        });

        mOnDownloadOnWify=findViewById(R.id.onOffDownloadOnWify);
        mOnDownloadOnWify.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked) {
                    GlobalVar.isDownloadOnWifyOnly = true;

                    Toast.makeText(mContext, "From now download will continue when wify is on.", Toast.LENGTH_LONG).show();
                }
            }
        });

        mOnDeleteCourse=findViewById(R.id.onOffDeleteCourse);
        mOnDeleteCourse.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked) {
                    GlobalVar.isDeleteAutometically = true;

                    Toast.makeText(mContext, "From now all completed course will be deleted autometically.", Toast.LENGTH_LONG).show();
                }
            }
        });


        mOnOffSyncCalender=findViewById(R.id.onOffSyncCalender);
        mOnOffSyncCalender.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked) {
                    requestCalendarSync();

                    Toast.makeText(mContext, "From now calender is synced with muktopaath.", Toast.LENGTH_LONG).show();


                    /*Date currentDate = new Date();
                    String myCurrentDate = new SimpleDateFormat("yyyy-MM-dd").format(currentDate);
                    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");



                    Intent calIntent = new Intent(Intent.ACTION_INSERT);

                    calIntent.setData(CalendarContract.Events.CONTENT_URI);
                    calIntent.putExtra(CalendarContract.Events.TITLE, "Google IO Afterparty");
                    calIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Hello Muktopaath");
                    calIntent.putExtra(CalendarContract.Events.DESCRIPTION, "Hang on a second");
                    Calendar startTime = Calendar.getInstance();
                    startTime.set(2012, 5, 29, 18, 0);
                    Calendar endTime = Calendar.getInstance();
                    endTime.set(2012, 5, 29, 22, 30);
                    calIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                            startTime.getTimeInMillis());
                    calIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,
                            endTime.getTimeInMillis());
                    startActivity(calIntent);*/
                }




            }
        });

        mSuppoerHelp=findViewById(R.id.suppoerHelp);
        mSuppoerHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://testfront.muktopaath.orangebd.com/articles/4/%E0%A6%AC%E0%A7%8D%E0%A6%AF%E0%A6%AC%E0%A6%B9%E0%A6%BE%E0%A6%B0-%E0%A6%B8%E0%A6%B9%E0%A6%BE%E0%A7%9F%E0%A6%BF%E0%A6%95%E0%A6%BE");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        mTermsCondi=findViewById(R.id.termsCondi);
        mTermsCondi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://testfront.muktopaath.orangebd.com/articles/4/%E0%A6%AC%E0%A7%8D%E0%A6%AF%E0%A6%AC%E0%A6%B9%E0%A6%BE%E0%A6%B0-%E0%A6%B8%E0%A6%B9%E0%A6%BE%E0%A7%9F%E0%A6%BF%E0%A6%95%E0%A6%BE");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        mPrivacyPoli=findViewById(R.id.privacyPoli);
        mPrivacyPoli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://testfront.muktopaath.orangebd.com/articles/4/%E0%A6%AC%E0%A7%8D%E0%A6%AF%E0%A6%AC%E0%A6%B9%E0%A6%BE%E0%A6%B0-%E0%A6%B8%E0%A6%B9%E0%A6%BE%E0%A7%9F%E0%A6%BF%E0%A6%95%E0%A6%BE");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        LinearLayout mLogOutBtn = findViewById(R.id.logOutBtnId);
        mLogOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showPopUpImageBox();
            }
        });


        mFeedBackId=findViewById(R.id.feedBackId);
        mFeedBackId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showPopUpFeedbackBox();
            }
        });


        String versionName = BuildConfig.VERSION_NAME;

        mVersionName=findViewById(R.id.settingsVersionName);
        mVersionName.setText(versionName);

        /*WifiManager wifi =(WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (wifi.isWifiEnabled()){

        }*/
    }


    private void requestCalendarSync()
    {
        AccountManager aM = AccountManager.get(this);
        Account[] accounts = aM.getAccounts();

        for (Account account : accounts)
        {
            int isSyncable = ContentResolver.getIsSyncable(account,  CalendarContract.AUTHORITY);

            if (isSyncable > 0)
            {
                Bundle extras = new Bundle();
                extras.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
                ContentResolver.requestSync(accounts[0], CalendarContract.AUTHORITY, extras);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {

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


    private void showPopUpFeedbackBox()
    {
        // custom dialog
        final Dialog dialogFeedback = new Dialog(mContext, R.style.DialogCustomTheme);
        dialogFeedback.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogFeedback.setContentView(R.layout.popupwindowforfeedback);

        Button mLogOutYes=dialogFeedback.findViewById(R.id.send);
        mLogOutYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                feedbackField=dialogFeedback.findViewById(R.id.feedBackField);

                feedbackDetails=feedbackField.getText().toString();


                Intent intent = new Intent(Intent.ACTION_SEND);

                intent.setType("message/rfc822");

                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"info@muktopaath.gov.bd"});

                intent.putExtra(Intent.EXTRA_SUBJECT, "Muktopaath Feedback from user: " + GlobalVar.gName);

                intent.putExtra(Intent.EXTRA_TEXT, feedbackDetails);

                startActivity(Intent.createChooser(intent, "Send Email"));

                dialogFeedback.dismiss();
            }
        });

        Button mLogOutNo=dialogFeedback.findViewById(R.id.cancel);
        mLogOutNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogFeedback.dismiss();
            }
        });

        dialogFeedback.show();
    }


}


