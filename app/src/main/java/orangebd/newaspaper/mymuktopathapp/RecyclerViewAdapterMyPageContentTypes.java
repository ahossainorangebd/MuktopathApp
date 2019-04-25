package orangebd.newaspaper.mymuktopathapp;


import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class RecyclerViewAdapterMyPageContentTypes extends RecyclerView.Adapter<RecyclerViewAdapterMyPageContentTypes.MyViewHolder> {

    private ArrayList<DetailDataModelCoursesDetailContents> dataSet;
    private ArrayList<DetailDataModelCoursesDetailContents> dataSet2;
    private Context mContext;
    private String stringPath;
    private String addOn;

    private String entryDate;
    private String updateDate;

    private String titleText;

    private Typeface tf;

    private ArrayList<DetailDataModel> mFilteredList;

    ArrayList<DetailDataModelCoursesDetailContents> mPulseArrayList = new ArrayList<DetailDataModelCoursesDetailContents>();

    //private String copyRightText;
    // private ImageView mainImage;
    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView textViewName;
        TextView textViewVersion;
        TextView textViewVersion2;
        ImageView imageViewIcon;
        Typeface typeface;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            this.textViewName = itemView.findViewById(R.id.textViewName);
            this.textViewVersion = itemView.findViewById(R.id.textViewVersion);
           // this.textViewVersion2 = itemView.findViewById(R.id.textViewVersion2);
            this.imageViewIcon = itemView.findViewById(R.id.contentTypeIcons);
            //this.typeface=Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/SolaimanLipi.ttf");
            //textViewVersion.setTypeface(typeface);
        }
    }

    public RecyclerViewAdapterMyPageContentTypes(ArrayList<DetailDataModelCoursesDetailContents> data, ArrayList<DetailDataModelCoursesDetailContents> data2, Context context) {
        this.dataSet = data;
        this.dataSet2 = data2;

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

        TextView textViewVersion = holder.textViewVersion;
        TextView textViewVersion2 = holder.textViewVersion2;
        ImageView imageView = holder.imageViewIcon;


        titleText=dataSet2.get(listPosition).getmContentType();


        /*final String descriptionText=dataSet.get(listPosition).getmDesc();
        final String videoCode=dataSet.get(listPosition).getFile_name();
        final String timeStatus=dataSet.get(listPosition).getStatus_content();
        final String ownerId=dataSet.get(listPosition).getOwner_id();*/

        final int lPosition=listPosition;


        if(titleText==null){
            textViewName.setText(". . . . .");
        }
        else {
            if(titleText.equalsIgnoreCase("video")){
                titleText="Lessons";

                imageView.setImageResource(R.drawable.play_button_round);
            }
            else if(titleText.equalsIgnoreCase("exam")){
                titleText="Exam";

                imageView.setImageResource(R.drawable.mukto_exam_icon);
            }
            else if(titleText.equalsIgnoreCase("quiz")){
                titleText="Quiz";

                imageView.setImageResource(R.drawable.mukto_quiz_icon);
            }
            else if(titleText.equalsIgnoreCase("assignment")){
                titleText="Assignment";

                imageView.setImageResource(R.drawable.mukto_assignment_icon);
            }
            else if(titleText.equalsIgnoreCase("discussion")){
                titleText="Discussion";

                imageView.setImageResource(R.drawable.mukto_discussion_icon);
            }
            else if(titleText.equalsIgnoreCase("live_class")){
                titleText="Live class";

                imageView.setImageResource(R.drawable.mukto_live_class);
            }

            textViewName.setText(titleText);


            /*imageView.setImageDrawable(R.id.mukto_ques_icon);
            imageView.setImageDrawable(R.id.mukto_ques_icon);
            imageView.setImageDrawable(R.id.mukto_ques_icon);
            imageView.setImageDrawable(R.id.mukto_ques_icon);*/
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                String titleText=textViewName.getText().toString();


                if(titleText==null){

                }
                else {
                    if(titleText.equalsIgnoreCase("Lessons")){

                        Intent i = new Intent(mContext, CourseContentActivity.class);
                        v.getContext().startActivity(i);
                    }
                    else if(titleText.equalsIgnoreCase("Exam")){
                        Intent i = new Intent(mContext, StartExamActivity.class);
                        v.getContext().startActivity(i);
                    }
                    else if(titleText.equalsIgnoreCase("Quiz")){
                        Intent i = new Intent(mContext, StartQuizActivity.class);
                        v.getContext().startActivity(i);
                    }
                    else if(titleText.equalsIgnoreCase("Assignment")){

                    }
                    else if(titleText.equalsIgnoreCase("Discussion")){

                    }
                }
            }
        });

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
}