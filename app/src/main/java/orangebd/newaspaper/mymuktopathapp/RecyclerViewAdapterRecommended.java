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

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class RecyclerViewAdapterRecommended extends RecyclerView.Adapter<RecyclerViewAdapterRecommended.MyViewHolder> {

    private ArrayList<DetailDataModelCourses> dataSet;
    private Context mContext;
    private String stringPath;
    private String addOn;

    private String entryDate;
    private String updateDate;

    private Typeface tf;

    private ArrayList<DetailDataModel> mFilteredList;

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
            this.imageViewIcon = itemView.findViewById(R.id.imageView);
            //this.typeface=Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/SolaimanLipi.ttf");
            //textViewVersion.setTypeface(typeface);
        }
    }

    public RecyclerViewAdapterRecommended(ArrayList<DetailDataModelCourses> data, Context context) {
        this.dataSet = data;

        this.mContext=context;
        stringPath = "file:///android_res/drawable/company_credit_logo.png";
        addOn = String.format("<img src=\"%s\" />", stringPath);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= new View(mContext);

        try {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_recommended, parent, false);
        }
        catch (Exception ex){
            Log.d("", ex.getMessage());
        }

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {
        TextView textViewName = holder.textViewName;

        TextView textViewVersion = holder.textViewVersion;
        TextView textViewVersion2 = holder.textViewVersion2;
        ImageView imageView = holder.imageViewIcon;
        final String titleText=dataSet.get(listPosition).getmCourseAliasName();
        final String DetailDescription=dataSet.get(listPosition).getmDetails();
        textViewName.setText(titleText);

        final String academyName = GlobalVar.gRecommendedDetailList10.get(listPosition).getInstitution_name_owner();

        //final String parentCatID=dataSet.get(listPosition).getCat_id();
        //String reporterString=dataSet.get(listPosition).getRpt();

        ArrayList<DetailDataModelCoursesThumbnails> imgArray=dataSet.get(listPosition).getmArrayListThumbnails();

        final ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> contentArray = dataSet.get(listPosition).getmArrayListContentDetails();

        final Object[] mArrayList = contentArray.get(listPosition).toArray();

        DetailDataModelCoursesThumbnails imgUrlModel = imgArray.get(listPosition);

        String imgUrl = imgUrlModel.getCover_code_image();

        //String imgUrl = imgUrlModel.getCover_code_image();

        final String CoverPhoto = GlobalVar.gBaseUrl + "/cache-images/" + "219x145x1" + "/uploads/images/" + imgUrl;

        //String detailString=dataSet.get(listPosition).getDtl_url();
        //String imgCaption=dataSet.get(listPosition).getImg_caption();

        /*if(reporterString.equalsIgnoreCase("")){
            reporterString="Rtv Desk";
        }*/

        try {
            Picasso.with(mContext)
                    .load(CoverPhoto)
                    .into(imageView);
        }
        catch (Exception ex){ }

            entryDate=dataSet.get(listPosition).getmCreatedAt();
            //final String returnDate=sdf.toString();

        textViewVersion.setText(academyName);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                GlobalVar.gChildArrayOfContent = mArrayList;

                Intent i = new Intent(mContext, CourseDetailActivity.class);
                i.putExtra("ttl", titleText);
                i.putExtra("img", CoverPhoto);
                i.putExtra("detail", DetailDescription);
                try {
                    v.getContext().startActivity(i);
                }
                catch (Exception ex){
                    String msg=ex.getMessage();
                    Log.d("msg",msg);
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
