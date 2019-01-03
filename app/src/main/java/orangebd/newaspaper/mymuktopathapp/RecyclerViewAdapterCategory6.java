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

public class RecyclerViewAdapterCategory6 extends RecyclerView.Adapter<RecyclerViewAdapterCategory6.MyViewHolder> {

    private ArrayList<DetailDataModel> dataSet;
    private Context mContext;
    private String stringPath;
    private String addOn;

    private String entryDate;
    private String updateDate;

    private Typeface tf;

    private ArrayList<DetailDataModel> mFilteredList;

    //private String copyRightText;
    // private ImageView mainImage;
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        TextView textViewVersion;
        ImageView imageViewIcon;
        Typeface typeface;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewName = itemView.findViewById(R.id.textViewName);
            this.textViewVersion = itemView.findViewById(R.id.textViewVersion);
            this.imageViewIcon = itemView.findViewById(R.id.imageView);
            //this.typeface=Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/SolaimanLipi.ttf");
            //textViewVersion.setTypeface(typeface);
        }
    }

    public RecyclerViewAdapterCategory6(ArrayList<DetailDataModel> data, Context context) {
        this.dataSet = data;
        this.mContext=context;
        stringPath = "file:///android_res/drawable/company_credit_logo.png";
        addOn = String.format("<img src=\"%s\" />", stringPath);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= new View(mContext);

        try {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_layout, parent, false);
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
        ImageView imageView = holder.imageViewIcon;
        String titleText=dataSet.get(listPosition).getHl2();
        textViewName.setText(titleText);

        final String parentCatID=dataSet.get(listPosition).getParent_cat_id();
        String reporterString=dataSet.get(listPosition).getRpt();
        String imgUrl=dataSet.get(listPosition).getImg_url();
        String detailString=dataSet.get(listPosition).getDtl_url();
        String imgCaption=dataSet.get(listPosition).getImg_caption();

        if(reporterString.equalsIgnoreCase("")){
            reporterString="Rtv Desk";
        }

        try {
            Picasso.with(mContext)
                    .load(imgUrl)
                    .into(imageView);
        }
        catch (Exception ex){}

        try {
            entryDate=convertEnglishDateToBengali(dataSet.get(listPosition).getEntry_time());
            //final String returnDate=sdf.toString();
            entryDate=convertEngToBn(entryDate);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        textViewVersion.setText(entryDate);

        try {
            updateDate=convertEnglishDateToBengali(dataSet.get(listPosition).getUpdate_time());
            //final String returnDate=sdf.toString();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        final String htmlText = "<html>"+"<head><link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">"+"<style>" + "@font-face {font-family: 'solaimanlipi';src: url('file:///android_asset/fonts/solaimanlipi.ttf');}body {font-family: 'solaimanlipi';}" +
                "        img{ max-width:100%; height:auto !important}" +
                "        a, span{ max-width:100%; display:inline-block; overflow:hidden}" +
                "        iframe{ max-width:100%; display:inline-block; overflow:hidden}" +
                "        </style>"+"</head>"+"<body style='height:100%; overflow:hidden;font-family:solaimanlipi'>" +
                "        <h2 style='font-family:solaimanlipi'>" + titleText + "</h2>" +reporterString+"<br/>"+"<strong>প্রকাশ :</strong>"+ entryDate + " <br/>"+"<br/>"+
                "        <img style='width:100%' src='" + imgUrl + "'  <div style='overflow-x:hidden;'>"+"<br/>"+ "<br/>"+ "<center> " + "</center> "
                + detailString +"<br/>"+
                "               </div><style>" +
                ".icon{width:35px; height:30px; border-radius:50%; background:#6A5ACD;}" +
                ".icon1{width:35px; height:30px; border-radius:50%; background:#262626;}" +
                ".icon2{width:35px; height:30px; border-radius:50%; background:#262626;}" +
                ".icon3{width:35px; height:30px; border-radius:50%; background:#F23000;}" +
                ".social_icon{display:block}" +
                ".fotter{padding-bottom:15px;text-align:center;}" +
                "</style>" +
                "<div class='container'>" +
                "<div class='row'>" +
                "<div class='col-md-12'style='background:#fff;'>" +
                "<div class='eskaton'style='text-align:center; color:#000;'>" +
                "</div>" +
                "</div>" +
                "</div>" +
                "</div>" +
                "</body>" +
                "</html>";

        final String detailUrl=dataSet.get(listPosition).getDtl_url_link();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                Intent i = new Intent(mContext, CourseDetailActivity.class);
                i.putExtra("URL", htmlText);
                i.putExtra("SURL", detailUrl);
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
