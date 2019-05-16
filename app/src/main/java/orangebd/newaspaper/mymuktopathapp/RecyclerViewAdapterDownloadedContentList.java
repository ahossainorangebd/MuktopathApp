package orangebd.newaspaper.mymuktopathapp;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class RecyclerViewAdapterDownloadedContentList extends RecyclerView.Adapter<RecyclerViewAdapterDownloadedContentList.MyViewHolder> {

    private ArrayList<String> dataSet;
    private ArrayList<String> dataSet2;
    private ArrayList<String> dataSet3;
    private ArrayList<String> dataSet4;

    private Context mContext;

    private View view;

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView reportextView;

        ImageView deleteBtn;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            this.reportextView = itemView.findViewById(R.id.fileName);
            this.deleteBtn = itemView.findViewById(R.id.deleteContentId);
        }
    }

    public RecyclerViewAdapterDownloadedContentList(ArrayList<String> data,ArrayList<String> data2,ArrayList<String> data3,ArrayList<String> data4 , Context context) {
        this.dataSet = data;
        this.dataSet2 = data2;
        this.dataSet3 = data3;
        this.dataSet4 = data4;

        this.mContext=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        view= new View(mContext);

        try {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_layout_downloaded_list, parent, false);
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
        final ImageView deleteImgViewBtn = holder.deleteBtn;

        final String lessonName=dataSet.get(listPosition);
        final String pathName=dataSet2.get(listPosition);
        final String lessonDetail=dataSet3.get(listPosition);
        final String pathDetail=dataSet4.get(listPosition);

        textViewName.setText(lessonName);

        deleteImgViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                /** for deleting a file*/

                File contentFile=new File(pathName);

                contentFile.delete();

                dataSet.remove(listPosition);
                dataSet2.remove(listPosition);
                dataSet3.remove(listPosition);
                dataSet4.remove(listPosition);


                DBHelper myDb = new DBHelper(mContext);
                myDb.deleteParticularData(lessonName);

                Cursor mCurse = myDb.getAllData();
                int xount= mCurse.getCount();

                String abtesting="";

                Toast.makeText(mContext,"অফলাইনের " + convertEngToBn(String.valueOf(listPosition+1)) + " তম কোর্স টি মুছে ফেলা সম্পন্ন হয়েছে", Toast.LENGTH_LONG).show();

                Intent i=new Intent(mContext, DownloadActivity.class);
                v.getContext().startActivity(i);

                /*try {
                    File file = new File(uri.getPath());
                    file.delete();

                    if (file.exists()) {
                        file.getCanonicalFile().delete();
                        if (file.exists()) {
                            getApplicationContext().deleteFile(file.getName());
                        }
                    }
                }
                catch (Exception ex){
                    Log.d("", "onCreate: ");
                }


                //or


                File dir = getFilesDir();
                File file = new File(dir, "my_filename");
                boolean deleted = file.delete();*/


            }
        });


        holder.reportextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(mContext, DownloadedContentActivity.class);

                i.putExtra("contentpath",pathName);
                i.putExtra("lessondetail",lessonDetail);
                i.putExtra("lessonname",lessonName);

                v.getContext().startActivity(i);
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
