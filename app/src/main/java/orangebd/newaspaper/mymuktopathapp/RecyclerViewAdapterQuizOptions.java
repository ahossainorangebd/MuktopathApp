package orangebd.newaspaper.mymuktopathapp;


import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class RecyclerViewAdapterQuizOptions extends RecyclerView.Adapter<RecyclerViewAdapterQuizOptions.MyViewHolder> {

    private ArrayList<DetailDataModelCoursesDetailContents> dataSet;
    private Context mContext;

    private CheckBox mOptionCheckBox;
    private boolean isChecked=false;

    private int trueCount;
    private boolean isSingle;

    ArrayList<DetailDataModelCoursesDetailContents> mPulseArrayList = new ArrayList<DetailDataModelCoursesDetailContents>();

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView textViewName;

        CheckBox optionCheckBox;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            this.textViewName = itemView.findViewById(R.id.textViewName);
            this.optionCheckBox = itemView.findViewById(R.id.optionCheckBox);
        }
    }

    public RecyclerViewAdapterQuizOptions(ArrayList<DetailDataModelCoursesDetailContents> data, Context context) {
        this.dataSet = data;

        this.mContext=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= new View(mContext);

        try {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_quiz_options, parent, false);
        }
        catch (Exception ex){
            Log.d("", ex.getMessage());
        }

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        String aasasasasas="";

        final TextView textViewName = holder.textViewName;
        final CheckBox checkViewName = holder.optionCheckBox;

        final String titleText=dataSet.get(listPosition).getmOptionBody();
        final String answer=dataSet.get(listPosition).getmOptionAnswer();

        textViewName.setText(titleText);
        checkViewName.setText(answer);


        checkViewName.setTag(answer);

        if (answer.equalsIgnoreCase("true")){
            trueCount++;
        }

        String testing="";
        checkViewName.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked)
                {
                    try {
                        Object something = buttonView.getTag();

                        String selectedAnswer= textViewName.getText().toString();

                        final ArrayList<DetailDataModelCoursesDetailContents> mQListForId = GlobalVar.courseContentDetailList.get(0).getmArrayListCourseQuizs().get(GlobalVar.gNthCourse);

                        if(GlobalVar.isRedirectFromQuizFragment1==true) {
                            String getAttendedQArray=mQListForId.get(GlobalVar.gNthQuiz).getmQuizId();
                            GlobalVar.attendedQArrayQuiz.add(getAttendedQArray);
                        }

                        else{
                            String getAttendedQArray=mQListForId.get(GlobalVar.gNthQuiz-1).getmQuizId();
                            GlobalVar.attendedQArrayQuiz.add(getAttendedQArray);
                        }

                        GlobalVar.answerArrayQuiz.add(selectedAnswer);

                        if (something.equals("true")) {
                            //TODO
                            //Toast.makeText(mContext,"Correct Answer",Toast.LENGTH_SHORT).show();

                            GlobalVar.gMultiMarkCount++;

                        }
                        else{
                            //TODO
                            //Toast.makeText(mContext,"Incorrect Answer",Toast.LENGTH_SHORT).show();
                        }

                        String abcd = something.toString();
                    }
                    catch (Exception ex){
                        Log.d("", "onCheckedChanged: ");
                    }

                    String abcd="";

                    for(int tap=0; tap<dataSet.size(); tap++) {
                        CharSequence checkedAns=buttonView.getText();
                        String ans=dataSet.get(tap).getmOptionAnswer();

                        if(checkedAns.equals(ans)) {


                        }
                    }
                }
            }
        }
        );




        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                String testing="";

                if(isSingle==true){
                    Toast.makeText(mContext,"You can't select more than one answer.",Toast.LENGTH_SHORT).show();
                }
                else {
                    if(trueCount>1){

                        if(isChecked==true) {
                            checkViewName.setChecked(false);
                            isChecked = false;
                        }
                        else {
                            checkViewName.setChecked(true);
                            isChecked = true;
                        }

                    }
                    else{

                        isSingle=true;

                        if(isChecked==true) {
                            checkViewName.setChecked(false);
                            isChecked = false;
                        }
                        else {
                            checkViewName.setChecked(true);
                            isChecked = true;
                        }
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
        if(dataSet==null) {
            return 0;
        }
        else
            return dataSet.size();
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
