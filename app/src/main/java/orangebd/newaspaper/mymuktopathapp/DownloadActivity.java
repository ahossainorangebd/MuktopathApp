package orangebd.newaspaper.mymuktopathapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.SyncStateContract;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class DownloadActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;


    private Context mContext;

    // Button Layout of footer

    private LinearLayout allCourseBtn;
    private LinearLayout recomendedBtn;
    private LinearLayout myPageBtn;
    private LinearLayout downloadsBtn;
    private LinearLayout profileBtn;

    private Button mSplashActvtySearchSomething;

    private VideoView videoView;


    private ArrayList<String> myList;

    private ArrayList<String> filePathList;

    private LinearLayout mNoDownloadLayout;

    private DBHelper myDb;

    private String crsid="";


    private ArrayList<String> mArrayLisCourseId;
    private ArrayList<String> mArrayLisUnitId;
    private ArrayList<String> mArrayLisUnitName;
    private ArrayList<String> mArrayLisFilePath;
    private ArrayList<String> mArrayLisLessonName;
    private ArrayList<String> mArrayLisLessonDetail;
    private ArrayList<String> mArrayCourseName;


    private ArrayList<ArrayList<String>> mArrayLisWholeData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        mContext=this;

        getSupportActionBar().hide();

        /*View view = LayoutInflater.from(mContext).inflate(R.layout.custom_logodetails, null, false);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(view);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#7a19aa")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/

        View view2 = findViewById(android.R.id.content);
        Animation mLoadAnimation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in);
        mLoadAnimation.setDuration(1000);
        view2.startAnimation(mLoadAnimation);


        myDb = new DBHelper(this);

        mNoDownloadLayout=findViewById(R.id.noDownloadLayout);

        // File

        myList= new ArrayList<>();

        //filePathList= new ArrayList<>();

        /***/

        File mPath=this.getExternalFilesDir(Environment.getExternalStorageDirectory().toString());
        String subPath=mPath.toString();
        subPath=subPath.substring(0,subPath.lastIndexOf("/storage"));
        subPath=subPath+"/muktopaath";

        recyclerView=findViewById(R.id.recyclerviewdownloadedvideolist);

        int filesLength=0;

        File file=new File(subPath);
        File[] files=file.listFiles();


        if (files==null){
            filesLength =0;
        }
        else {
            filesLength =files.length;
        }


        if(filesLength>0){
            GetDownloadedData();


            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);

//            adapter=new RecyclerViewAdapterDownloadList(myList,filePathList,this);
            adapter=new RecyclerViewAdapterDownloadList(mArrayLisWholeData,this);

            recyclerView.setAdapter(adapter);

        }
        else {
            mNoDownloadLayout.setVisibility(View.VISIBLE);
        }


        /***/

        allCourseBtn = findViewById(R.id.allCourseBtnId);
        recomendedBtn = findViewById(R.id.recomendedBtnId);
        myPageBtn = findViewById(R.id.myPageBtnId);
        downloadsBtn = findViewById(R.id.downloadsBtnId);
        profileBtn = findViewById(R.id.profilePageBtnId);

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

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GlobalVar.isRedirectFromProfileRunningBtn=false;
                GlobalVar.isRedirectFromProfileNonEndedBtn=false;
                GlobalVar.isRedirectFromProfileEndedBtn=false;
                GlobalVar.isRedirectFromProfileWishListBtn=false;

                Intent i=new Intent(mContext,ProfileActivity.class);
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

        /*String path = "Phone storage/Android/data/" + getPackageName() + "/files/muktopaath/video-1-1553023608.mp4";
        //String path = getFilesDir()+"/video-1-1553023608.mp4";

        VideoView videoView = findViewById(R.id.vdVw);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.setVideoPath(path);
        videoView.start();*/
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



    //TODO
    //TODO
    /*public void DeleteData() {
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteData(editTextId.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(mContext,"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(mContext,"Data not Deleted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    public void UpdateData() {
        btnviewUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDb.updateData(editTextId.getText().toString(),
                                editName.getText().toString(),
                                editSurname.getText().toString(),editMarks.getText().toString());
                        if(isUpdate == true)
                            Toast.makeText(mContext,"Data Update",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(mContext,"Data not Updated",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    public void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(Integer.parseInt(editName.getText().toString()),
                                Integer.parseInt(editSurname.getText().toString()),
                                editMarks.getText().toString());
                        if(isInserted == true)
                            Toast.makeText(mContext,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(mContext,"Data not Inserted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void viewAll() {
        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Error","Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :"+ res.getString(0)+"\n");
                            buffer.append("User Id :"+ res.getString(1)+"\n");
                            buffer.append("Course Id :"+ res.getString(2)+"\n");
                            buffer.append("Content :"+ res.getString(3)+"\n\n");
                        }
                        // Show all data
                        showMessage("Data",buffer.toString());
                    }
                }
        );
    }*/

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void GetDownloadedData() {

        Cursor mCursorForCourseName = myDb.getGroupDataForCName();

        Cursor mCursorForUnitNumber = myDb.getGroupDataForUID();
        Cursor mCursorForUnitName = myDb.getGroupDataForUName();

        Cursor mCursor = myDb.getAllData();

        //Cursor mCursor = myDb.getAllData();

        mArrayLisWholeData = new ArrayList<>();

        mArrayLisCourseId = new ArrayList<>();
        mArrayLisUnitId = new ArrayList<>();
        mArrayLisUnitName = new ArrayList<>();
        mArrayLisFilePath = new ArrayList<>();
        mArrayLisLessonName = new ArrayList<>();
        mArrayLisLessonDetail = new ArrayList<>();
        mArrayCourseName = new ArrayList<>();


        StringBuffer buffer = new StringBuffer();
        while (mCursor.moveToNext()) {

            //buffer.append("Id :" + mCursor.getString(3) + "\n");

            mArrayLisFilePath.add(mCursor.getString(3));
            mArrayLisLessonName.add(mCursor.getString(4));
            mArrayLisLessonDetail.add(mCursor.getString(5));

        }

        while (mCursorForCourseName.moveToNext()) {

            mArrayLisCourseId.add(mCursorForCourseName.getString(1));
            mArrayCourseName.add(mCursorForCourseName.getString(6));

        }

        while (mCursorForUnitNumber.moveToNext()) {

            mArrayLisUnitId.add(mCursorForUnitNumber.getString(2));
           //mArrayLisUnitName.add(mCursorForUnitName.getString(7));

        }

        mArrayLisWholeData.add(mArrayLisCourseId);
        mArrayLisWholeData.add(mArrayCourseName);

        mArrayLisWholeData.add(mArrayLisUnitId);
        //mArrayLisWholeData.add(mArrayLisUnitName);

        mArrayLisWholeData.add(mArrayLisFilePath);

        mArrayLisWholeData.add(mArrayLisLessonName);
        mArrayLisWholeData.add(mArrayLisLessonDetail);



        String abcd2="";

    }

}





