package orangebd.newaspaper.mymuktopathapp;


import android.app.Dialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;

public class RecyclerViewAdapterMyPageContentTypes extends RecyclerView.Adapter<RecyclerViewAdapterMyPageContentTypes.MyViewHolder> {

    private String BASE_URL = "http://muktopaath.orangebd.com";

    private int unitNumberSet;
    private String unitNumberSetStr;

    private String unitIdStr;

    private String unitTitle;

    private ArrayList<DetailDataModelCoursesDetailContents> dataSet;
    private ArrayList<DetailDataModelCoursesDetailContents> dataSet2;
    private Context mContext;
    private String stringPath;
    private String addOn;

    private String entryDate;
    private String updateDate;

    private String titleText;
    private String titleType;

    private Typeface tf;

    private ArrayList<DetailDataModel> mFilteredList;

    ArrayList<DetailDataModelCoursesDetailContents> mPulseArrayList = new ArrayList<DetailDataModelCoursesDetailContents>();

    //private String copyRightText;
    // private ImageView mainImage;

    private String mtitleText;
    private String descriptionText;
    private String videoCode;
    private String timeStatus;
    private String ownerId;
    private String contentSize;
    private String contentDuration;

    private String youTubeVideoLink;

    private String contentIconType;

    private Button downloadBtn;

    private DBHelper myDb;


    private String videoUrl;

    private String courseBanner;
    private String courseId;
    private String courseName;

    private String subImgPath;


    private String videoType;


    private String thisLessonCompleteness;

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView textViewName;
        TextView completedTextview;

        TextView textViewVersion;

        ImageView mCheckBox;

        TextView textViewVersion2;

        ImageView imageViewIcon;
        ImageView imageViewDownload;

        ImageView imageViewdotIconId;

        Typeface typeface;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            this.textViewName = itemView.findViewById(R.id.textViewName);
            this.completedTextview = itemView.findViewById(R.id.completedTextviewId);
            this.mCheckBox = itemView.findViewById(R.id.completedChecked);
            this.textViewVersion = itemView.findViewById(R.id.textViewVersion);
           // this.textViewVersion2 = itemView.findViewById(R.id.textViewVersion2);
            this.imageViewIcon = itemView.findViewById(R.id.contentTypeIcons);
            this.imageViewDownload = itemView.findViewById(R.id.cloudDownloadBtn);


            this.imageViewdotIconId = itemView.findViewById(R.id.dotIconId);
            //this.typeface=Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/SolaimanLipi.ttf");
            //textViewVersion.setTypeface(typeface);
        }
    }

    public RecyclerViewAdapterMyPageContentTypes(String unitName, int unitNumber, ArrayList<DetailDataModelCoursesDetailContents> data, ArrayList<DetailDataModelCoursesDetailContents> data2, Context context) {
        this.dataSet = data;
        this.dataSet2 = data2;

        this.unitNumberSet= unitNumber;
        this.unitTitle= unitName;

        this.mContext=context;
        stringPath = "file:///android_res/drawable/company_credit_logo.png";
        addOn = String.format("<img src=\"%s\" />", stringPath);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= new View(mContext);

        try {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_mypage_content_types, parent, false);
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
        final TextView mCompletedTextview = holder.completedTextview;

        TextView textViewVersion = holder.textViewVersion;
        TextView textViewVersion2 = holder.textViewVersion2;
        ImageView imageView = holder.imageViewIcon;
        ImageView imageViewdown = holder.imageViewDownload;
        ImageView imageViewdot = holder.imageViewdotIconId;


        try {
            thisLessonCompleteness  = dataSet.get(listPosition).getLessonCompletenessStatus();
        }
        catch (Exception ex){
            Log.d("", "onBindViewHolder: ");
        }

        ImageView completedCheckbox = holder.mCheckBox;

        //GlobalVar.gLessonCompleteTempUnitId
        //GlobalVar.gLessonCompleteTempLessonId

        if(GlobalVar.gLessonCompleteTempUnitId!=null) {

            if (GlobalVar.gLessonCompleteTempUnitId.equalsIgnoreCase(String.valueOf(unitNumberSet + 1))) {

                if (GlobalVar.gLessonCompleteTempLessonId != null) {

                    if (GlobalVar.gLessonCompleteTempLessonId.equalsIgnoreCase(String.valueOf(listPosition + 1))) {

                        completedCheckbox.setVisibility(View.VISIBLE);

                        completedCheckbox.setImageResource(R.drawable.completed_checked_icon);

                        mCompletedTextview.setVisibility(View.VISIBLE);
                    }
                }
            }
        }


        if(thisLessonCompleteness.equalsIgnoreCase("100")){
            completedCheckbox.setVisibility(View.VISIBLE);

            completedCheckbox.setImageResource(R.drawable.completed_checked_icon);

            mCompletedTextview.setVisibility(View.VISIBLE);
        }
        else{
            imageViewdot.setVisibility(View.INVISIBLE);
        }






        titleText=dataSet2.get(listPosition).getmContentType();


        //This section is for getting content array List


        final ArrayList<DetailDataModelCoursesDetailContents> thisFragmentContents = GlobalVar.thisFragmentContents.get(unitNumberSet);


        for(int customListPosition=0; customListPosition<thisFragmentContents.size(); customListPosition++) {
            mtitleText = thisFragmentContents.get(customListPosition).getTitle_content();
            descriptionText = thisFragmentContents.get(customListPosition).getmDesc();
            videoCode = thisFragmentContents.get(customListPosition).getFile_name();
            timeStatus = thisFragmentContents.get(customListPosition).getStatus_content();
            ownerId = thisFragmentContents.get(customListPosition).getOwner_id();
            contentSize = thisFragmentContents.get(customListPosition).getmContentSize();
            contentDuration = thisFragmentContents.get(customListPosition).getmDurationAnother();

            youTubeVideoLink = thisFragmentContents.get(customListPosition).getExternal_Link();
        }


        final int lPosition=listPosition;

        String lessonName="";

        if(titleText==null){
            textViewName.setText(". . . . .");
        }
        else {
            if(titleText.equalsIgnoreCase("video")){
                //titleText="Lessons";
                lessonName=dataSet2.get(listPosition).getTitle_content();

                if(lessonName==null){
                    titleText=". . . . .";
                }
                else {
                    titleText=lessonName;
                }


                imageView.setImageResource(R.drawable.play_button_round);

                contentIconType="video";
            }
            else if(titleText.equalsIgnoreCase("exam")){
                titleText="Exam";

                imageView.setImageResource(R.drawable.mukto_exam_icon);

                contentIconType="exam";
            }
            else if(titleText.equalsIgnoreCase("quiz")){
                titleText="Quiz";

                imageView.setImageResource(R.drawable.mukto_quiz_icon);

                contentIconType="quiz";
            }
            else if(titleText.equalsIgnoreCase("assignment")){
                titleText="Assignment";

                imageView.setImageResource(R.drawable.mukto_assignment_icon);

                contentIconType="assignment";
            }
            else if(titleText.equalsIgnoreCase("discussion")){
                titleText="Discussion";

                imageView.setImageResource(R.drawable.mukto_discussion_icon);

                contentIconType="discussion";
            }
            else if(titleText.equalsIgnoreCase("live_class")) {
                titleText="Live class";

                imageView.setImageResource(R.drawable.mukto_live_class);

                contentIconType="live_class";
            }
            else if(titleText.equalsIgnoreCase("manual_content")) {

                titleText="Manual Content";

                imageView.setImageResource(R.drawable.mukto_assignment_icon);

                contentIconType="manual_content";
            }

            textViewName.setText(titleText);


            /*imageView.setImageDrawable(R.id.mukto_ques_icon);
            imageView.setImageDrawable(R.id.mukto_ques_icon);
            imageView.setImageDrawable(R.id.mukto_ques_icon);
            imageView.setImageDrawable(R.id.mukto_ques_icon);*/
        }


        myDb = new DBHelper(mContext);

        videoType = thisFragmentContents.get(listPosition).getmChooseVideoType();

        if(contentIconType.equalsIgnoreCase("video")) {
            if (videoType.equalsIgnoreCase("0")) {
                imageViewdown.setImageResource(R.drawable.lock_icon);
            }
        }

        imageViewdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*if(GlobalVar.isDownloadOnWifyOnly==true){*/
                    if(videoType.equalsIgnoreCase("0")){
                        Toast.makeText(mContext,"Download is locked for this lesson",Toast.LENGTH_LONG).show();
                    }
                    else{
                        contentIconType=textViewName.getText().toString();


                        // Get Course id
                        courseId=GlobalVar.gEnrollCourseId.get(GlobalVar.gNthCourse).getmEcId();
                        courseName=GlobalVar.gEnrollCourseId.get(GlobalVar.gNthCourse).getmCourseAliasName();

                        String courseBannerName =GlobalVar.courseContentDetailList.get(0).getmArrayListThumbnails().get(GlobalVar.gNthCourse).getCover_code_image();

                        courseBanner = GlobalVar.gBaseUrl + "/cache-images/" + "219x145x1" + "/uploads/images/" + courseBannerName;


                        DownloadManager.Request request2 = new DownloadManager.Request(Uri.parse(courseBanner));
                        request2.setDescription("Downloading...");   //appears the same in Notification bar while downloading
                        request2.setTitle("banner_"+courseName);
                        request2.setVisibleInDownloadsUi(false);

                        String ontesting="";

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                            request2.allowScanningByMediaScanner();
                            request2.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
                        }
                        request2.setDestinationInExternalFilesDir(mContext, "/muktopaathimg", "banner_"+courseName+".jpg");

                        DownloadManager manager2 = (DownloadManager)mContext.getSystemService(Context.DOWNLOAD_SERVICE);
                        Objects.requireNonNull(manager2).enqueue(request2);
                        if (DownloadManager.STATUS_SUCCESSFUL == 8) {
                            //DownloadSuccess();
                        }


                        Picasso.with(mContext).load(courseBanner).into(new Target() {
                                                                           @Override
                                                                           public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                                                               try {


                                                                                   File mImgPath=mContext.getExternalFilesDir(Environment.getExternalStorageDirectory().toString());
                                                                                   subImgPath = mImgPath.toString();
                                                                                   subImgPath=subImgPath.substring(0,subImgPath.lastIndexOf("/storage"));
                                                                                   subImgPath=subImgPath+"/muktopaathimg";

                                                                                   subImgPath=subImgPath+"/"+ "banner_"+courseName+".jpg";


                                                                                   String ontest="";

                                                                               }
                                                                               catch(Exception e){
                                                                                   Toast.makeText(mContext,"Banner download failed for some reasone",Toast.LENGTH_LONG).show();
                                                                               }
                                                                           }

                                                                           @Override
                                                                           public void onBitmapFailed(Drawable errorDrawable) {
                                                                               Toast.makeText(mContext,"Banner download failed for some reasone",Toast.LENGTH_LONG).show();
                                                                           }

                                                                           @Override
                                                                           public void onPrepareLoad(Drawable placeHolderDrawable) {
                                                                               Toast.makeText(mContext,"Banner download successful",Toast.LENGTH_LONG).show();
                                                                           }
                                                                       }
                        );

                        videoType = thisFragmentContents.get(listPosition).getmChooseVideoType();

                        if(videoType.equalsIgnoreCase("1")){
                            videoCode = thisFragmentContents.get(listPosition).getFile_name();
                        }
                        else{
                            videoCode = thisFragmentContents.get(listPosition).getExternal_Link();
                        }

                        videoCode = thisFragmentContents.get(listPosition).getFile_name();


                        titleText = dataSet2.get(listPosition).getTitle_content();

                        descriptionText = dataSet2.get(listPosition).getmDesc();

                        videoUrl = BASE_URL + "/storage/uploads/videos/" + "user-" + ownerId + "/"+ videoCode;

                        new DownloadFileAsync().execute();
                    }
                /*}
                else {
                    Toast.makeText(mContext,"দয়া করে ওয়াইফাই চালু করুন",Toast.LENGTH_LONG).show();
                }*/



            }
        });



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {


                thisLessonCompleteness  = dataSet.get(listPosition).getLessonCompletenessStatus();


                videoType = thisFragmentContents.get(listPosition).getmChooseVideoType();

                contentDuration = thisFragmentContents.get(listPosition).getmDurationAnother();

                ownerId = thisFragmentContents.get(listPosition).getOwner_id();

                descriptionText = dataSet2.get(listPosition).getmDesc();


                if(videoType.equalsIgnoreCase("1")){
                    videoCode = thisFragmentContents.get(listPosition).getFile_name();
                }
                else{
                    videoCode = thisFragmentContents.get(listPosition).getExternal_Link();
                }


                String titleText=textViewName.getText().toString();


                String lessonId = GlobalVar.courseContentDetailList.get(0).getmArrayListCourseLesson().get(GlobalVar.gNthCourse).get(unitNumberSet).get(listPosition).getIdLesson();

                GlobalVar.gLessonId=lessonId;

                if(titleText==null){

                }
                else {

                    if(titleText.equalsIgnoreCase("Exam")){
                        Intent i = new Intent(mContext, StartExamActivity.class);
                        v.getContext().startActivity(i);
                    }
                    else if(titleText.equalsIgnoreCase("Quiz")){
                        Intent i = new Intent(mContext, StartQuizActivity.class);
                        v.getContext().startActivity(i);
                    }
                    else if(titleText.equalsIgnoreCase("Assignment")){
                        showPopUpMessageBox();
                    }
                    else if(titleText.equalsIgnoreCase("Discussion")){
                        Intent i = new Intent(mContext, DiscussionActivity.class);
                        v.getContext().startActivity(i);
                    }
                    else if(titleText.equalsIgnoreCase("Manual Content")){
                        Intent i = new Intent(mContext, DiscussionActivity.class);
                        v.getContext().startActivity(i);
                    }

                    else{

                        /*Intent i = new Intent(mContext, CourseContentActivity.class);
                        v.getContext().startActivity(i);*/

                        unitIdStr= String.valueOf(unitNumberSet+1);


                        contentIconType = thisFragmentContents.get(listPosition).getmContentType();

                        if(contentIconType.equalsIgnoreCase("manual_content")){
                            contentIconType="manual_content";
                        }
                        else{
                            contentIconType="video";
                        }

                        Intent i = new Intent(mContext, CourseContentDetailActivity.class);
                        i.putExtra("ttl", titleText);
                        i.putExtra("detail", descriptionText);
                        i.putExtra("usernumber", ownerId);
                        i.putExtra("vcode", videoCode);
                        i.putExtra("videostatus", timeStatus);
                        i.putExtra("unitid", unitIdStr);
                        i.putExtra("csize", contentSize);
                        i.putExtra("cduration", contentDuration);
                        i.putExtra("cvtype", videoType);
                        i.putExtra("ciconype", contentIconType);
                        i.putExtra("thislessoncompltness", thisLessonCompleteness);

                        GlobalVar.gListPosition=Integer.toString(listPosition);

                        //For History data

                        GlobalVar.gDescriptionText=descriptionText;
                        GlobalVar.gUserNumber=ownerId;
                        GlobalVar.gVideoCode=videoCode;
                        GlobalVar.gTimeStatus=timeStatus;
                        GlobalVar.gContentDuration=contentDuration;
                        GlobalVar.gChooseVideoType=videoType;
                        GlobalVar.gContentIconType=contentIconType;
                        GlobalVar.gLessonCompleteness=thisLessonCompleteness;

                        String aqwqw="";

                        //i.putExtra("img", CoverPhoto);
                        try {
                            v.getContext().startActivity(i);
                        }
                        catch (Exception ex){
                            String msg=ex.getMessage();
                            Log.d("msg",msg);
                        }
                    }
                }
            }
        });

    }


    /*public void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }
        );
    }*/

    public void downloadFile() {



        String DownloadUrl = videoUrl;
        DownloadManager.Request request1 = new DownloadManager.Request(Uri.parse(DownloadUrl));
        request1.setDescription("Downloading...");   //appears the same in Notification bar while downloading
        request1.setTitle(titleText);
        request1.setVisibleInDownloadsUi(false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            request1.allowScanningByMediaScanner();
            request1.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        }
        request1.setDestinationInExternalFilesDir(mContext, "/muktopaath", titleText+".mp4");

        DownloadManager manager1 = (DownloadManager)mContext.getSystemService(Context.DOWNLOAD_SERVICE);
        Objects.requireNonNull(manager1).enqueue(request1);
        if (DownloadManager.STATUS_SUCCESSFUL == 8) {
            //DownloadSuccess();
        }



    }

    private void showPopUpMessageBox()
    {
        // custom dialog
        final Dialog dialog = new Dialog(mContext, R.style.DialogCustomTheme);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.popupwindowforassignmentmsg);

        dialog.show();
    }

    private String convertEnglishDateToBengali(String englishDate) throws ParseException {
        // Initial date time in String forma†
        //String timeOrg = "Mon Apr 18 22:56:10 GMT+05:30 2016";
        String timeOrg = englishDate;
        // Corresponding date format
        //2018-05-13 14:47:38
        String dateTimeFormatOrg ="yyyy-MM-dd HH:mm:ss"; //"EEE MMM dd hh:mm:ss z yyyy";
        // SimpleDateFormat using US locale to be able to parse "Mon Apr"
        SimpleDateFormat sdfgmtOrg = new SimpleDateFormat(dateTimeFormatOrg, Locale.US);
        // Parse the date
        Date time = sdfgmtOrg.parse(timeOrg);

        // Target date format
        String dateTimeFormat = "dd MMM yyyy hh:mm";
        // SimpleDateFormat using the target locale
        SimpleDateFormat sdfgmt = new SimpleDateFormat(dateTimeFormat, new Locale("bn","BD"));
        // Set the Time Zone to UTC
        sdfgmt.setTimeZone(TimeZone.getDefault());
        // Print the formatted date
        String returnDate=sdfgmt.format(time);
        //System.out.println(sdfgmt.format(time));
        return returnDate;
    }

    @Override
    public int getItemCount() {
        if(dataSet2==null) {
            return 0;
        }
        else
            return dataSet2.size();
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


    public class DownloadFileAsync extends AsyncTask<Void, Void, Void>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            downloadFile();
            return null;
        }

        @Override
        protected void onPostExecute(Void result)
        {
            super.onPostExecute(result);



            File mPath=mContext.getExternalFilesDir(Environment.getExternalStorageDirectory().toString());
            String subPath=mPath.toString();
            subPath=subPath.substring(0,subPath.lastIndexOf("/storage"));
            subPath=subPath+"/muktopaath";

            subPath=subPath+"/"+ titleText+".mp4";

            //Toast.makeText(mContext,"No downloadable file found.",Toast.LENGTH_LONG).show();

            unitNumberSetStr = String.valueOf(unitNumberSet+1);


            boolean isInserted = myDb.insertData(courseId, unitNumberSetStr, subPath,titleText, descriptionText, courseName, unitTitle, subImgPath);

            Cursor mCursor =  myDb.getAllData();

            int mInt =mCursor.getCount();



            if(isInserted == true)
                Toast.makeText(mContext,"Lesson Downloading",Toast.LENGTH_LONG).show();
            else
                Toast.makeText(mContext,"Lesson Download Failed",Toast.LENGTH_LONG).show();

        }
    }

}
