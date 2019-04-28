package orangebd.newaspaper.mymuktopathapp;


import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewAdapterFlagList extends RecyclerView.Adapter<RecyclerViewAdapterFlagList.MyViewHolder> {

    private ArrayList<String> dataSet;

    private Context mContext;

    private View view;

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView reportextView;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            this.reportextView = itemView.findViewById(R.id.reportText);
        }
    }

    public RecyclerViewAdapterFlagList(ArrayList<String> data, Context context) {
        this.dataSet = data;

        this.mContext=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        view= new View(mContext);

        try {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_layout_flag_list, parent, false);
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

        final String reportBodyText=dataSet.get(listPosition);


        if(reportBodyText==null){
            textViewName.setText(". . . . .");
        }
        else {
            textViewName.setText(reportBodyText);
        }

        if(reportBodyText==null){
            textViewName.setText(". . . . .");
        }
        else {
            textViewName.setText(reportBodyText);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                String flagReport=textViewName.getText().toString();

                GlobalVar.gFlagReportStr=flagReport;

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
