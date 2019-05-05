package orangebd.newaspaper.mymuktopathapp;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewAdapterDownloadedCourseList extends RecyclerView.Adapter<RecyclerViewAdapterDownloadedCourseList.MyViewHolder> {

    private ArrayList<ArrayList<String>> dataSet;

    private Context mContext;

    private View view;

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView reportextView;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            this.reportextView = itemView.findViewById(R.id.fileName);
        }
    }

    public RecyclerViewAdapterDownloadedCourseList(ArrayList<ArrayList<String>> data, Context context) {
        this.dataSet = data;

        this.mContext=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        view= new View(mContext);

        try {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_layout_downloaded_unit_list, parent, false);
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
        final TextView textViewName = holder.reportextView;

        final ArrayList<String> mArrayLisUnitId=dataSet.get(0);
        final ArrayList<String> filePathList=dataSet.get(1);
        final ArrayList<String> mArrayLisLessonName=dataSet.get(2);
        final ArrayList<String> mArrayLisLessonDetail=dataSet.get(3);

        final String unitName=mArrayLisUnitId.get(listPosition);

        textViewName.setText(unitName);

        holder.reportextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String action;
                Intent i=new Intent(mContext, DownloadLessonActivity.class);

                i.putExtra("pathlist",filePathList);
                i.putExtra("lessonlist",mArrayLisLessonName);
                i.putExtra("lessondetaillist",mArrayLisLessonDetail);

                v.getContext().startActivity(i);
            }
        });


    }


    @Override
    public int getItemCount() {
        if(dataSet.get(0)==null) {
            return 0;
        }
        else
            return dataSet.get(0).size();
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
