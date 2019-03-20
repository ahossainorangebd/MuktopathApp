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

public class RecyclerViewAdapterRecomCats extends RecyclerView.Adapter<RecyclerViewAdapterRecomCats.MyViewHolder> {

    private ArrayList<String> dataSet;
    private Context mContext;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewName = itemView.findViewById(R.id.textViewName);
        }
    }

    public RecyclerViewAdapterRecomCats(ArrayList<String> data, Context context) {
        this.dataSet = data;

        this.mContext=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= new View(mContext);

        try {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_recommended_cats, parent, false);
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

        String textValue= dataSet.get(listPosition);

        textViewName.setText(textValue);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
