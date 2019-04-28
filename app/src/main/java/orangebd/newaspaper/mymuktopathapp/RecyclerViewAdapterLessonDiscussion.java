package orangebd.newaspaper.mymuktopathapp;


import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
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

public class RecyclerViewAdapterLessonDiscussion extends RecyclerView.Adapter<RecyclerViewAdapterLessonDiscussion.MyViewHolder> {

    private ArrayList<DetailDataModelCoursesDetailContents> dataSet;
    private ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> dataSet2;
    private Context mContext;


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private ArrayList<DetailDataModelCoursesDetailContents> replyList = new ArrayList<>();

    //private String copyRightText;
    // private ImageView mainImage;
    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView CommentertextView;
        TextView CommentTextview;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            this.CommentertextView = itemView.findViewById(R.id.userNameText);
            this.CommentTextview = itemView.findViewById(R.id.commentText);

            //this.typeface=Typeface.createFromAsset(itemView.getContext().getAssets(), "fonts/SolaimanLipi.ttf");
            //textViewVersion.setTypeface(typeface);
        }
    }

    public RecyclerViewAdapterLessonDiscussion(ArrayList<DetailDataModelCoursesDetailContents> data,ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> data2, Context context) {
        this.dataSet = data;
        this.dataSet2 = data2;

        this.mContext=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= new View(mContext);

        try {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_layout_discussion, parent, false);
        }
        catch (Exception ex){
            Log.d("", ex.getMessage());
        }

        recyclerView = view.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition)
    {

        TextView textViewName = holder.CommentertextView;
        TextView textViewComment = holder.CommentTextview;


        final String commentBodyText=dataSet.get(listPosition).getmCommentBody();
        final String userBodyText=dataSet.get(listPosition).getmCommenterNames();


        if(userBodyText==null){
            textViewName.setText(". . . . .");
        }
        else {
            textViewName.setText(userBodyText);
        }

        if(commentBodyText==null){
            textViewName.setText(". . . . .");
        }
        else {
            textViewComment.setText(commentBodyText);
        }

        replyList = dataSet2.get(listPosition);


        setRecyclerViewContent();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

            }
        });

    }

    private void setRecyclerViewContent() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setNestedScrollingEnabled(false);

        adapter=new RecyclerViewAdapterLessonDiscussionReply(replyList, mContext);

        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public class GetAllReply extends AsyncTask<String, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... arg0) {

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);


        }
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
