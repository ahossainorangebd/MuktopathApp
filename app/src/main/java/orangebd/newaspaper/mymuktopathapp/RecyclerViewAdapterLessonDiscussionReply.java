package orangebd.newaspaper.mymuktopathapp;


import android.content.Context;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class RecyclerViewAdapterLessonDiscussionReply extends RecyclerView.Adapter<RecyclerViewAdapterLessonDiscussionReply.MyViewHolder> {

    private ArrayList<DetailDataModelCoursesDetailContents> dataSet;
    private ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> dataSet2;
    private Context mContext;
    private String stringPath;


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private String entryDate;
    private String updateDate;

    private Typeface tf;

    private View view;

    private ArrayList<DetailDataModel> mFilteredList;

    ArrayList<DetailDataModelCoursesDetailContents> mPulseArrayList = new ArrayList<DetailDataModelCoursesDetailContents>();

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

    public RecyclerViewAdapterLessonDiscussionReply(ArrayList<DetailDataModelCoursesDetailContents> data, Context context) {
        this.dataSet = data;

        this.mContext=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        view= new View(mContext);

        try {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_layout_discussion_reply, parent, false);
        }
        catch (Exception ex){
            Log.d("", ex.getMessage());
        }


        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition)
    {

        TextView textViewName = holder.CommentertextView;
        TextView textViewComment = holder.CommentTextview;

        final String commentBodyText=dataSet.get(listPosition).getmReplyBody();
        final String userBodyText=dataSet.get(listPosition).getmReplierNames();


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






        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {


            }
        });

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
