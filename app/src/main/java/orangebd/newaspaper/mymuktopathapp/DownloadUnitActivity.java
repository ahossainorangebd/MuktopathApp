package orangebd.newaspaper.mymuktopathapp;

import android.content.Context;
import android.database.Cursor;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.io.File;
import java.util.ArrayList;

public class DownloadUnitActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private ArrayList<ArrayList<String>> mArrayLisWholeData;


    private DBHelper myDb;


    private ArrayList<String> mArrayLisUnitId;

    private String courseId;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_unit);


        mContext =this;

        recyclerView=findViewById(R.id.recyclerviewdownloadedunitlist);

        mArrayLisWholeData = new ArrayList<>();



        ArrayList<String> unitList = this.getIntent().getExtras().getStringArrayList("unitidlist");
        ArrayList<String> pathList = this.getIntent().getExtras().getStringArrayList("pathlist");
        ArrayList<String> lessonList = this.getIntent().getExtras().getStringArrayList("lessonlist");
        ArrayList<String> lessonDetailList = this.getIntent().getExtras().getStringArrayList("lessondetaillist");
        ArrayList<String> coursename = this.getIntent().getExtras().getStringArrayList("coursename");
        ArrayList<String> coursebanner = this.getIntent().getExtras().getStringArrayList("coursebanner");

        courseId = this.getIntent().getExtras().getString("courseid");


        mArrayLisWholeData.add(unitList);
        mArrayLisWholeData.add(pathList);
        mArrayLisWholeData.add(lessonList);
        mArrayLisWholeData.add(lessonDetailList);
        mArrayLisWholeData.add(coursename);
        mArrayLisWholeData.add(coursebanner);




        /***/

        File mPath=this.getExternalFilesDir(Environment.getExternalStorageDirectory().toString());
        String subPath=mPath.toString();
        subPath=subPath.substring(0,subPath.lastIndexOf("/storage"));
        subPath=subPath+"/muktopaath";

        int filesLength=0;

        File file=new File(subPath);
        File[] files=file.listFiles();


        if (files==null){
            filesLength =0;
        }
        else {
            filesLength =files.length;
        }


        GetDownloadedData();


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new RecyclerViewAdapterDownloadedCourseList( courseId ,mArrayLisUnitId,this);
        recyclerView.setAdapter(adapter);
    }


    public void GetDownloadedData() {

        myDb = new DBHelper(mContext);

        Cursor mCursorForUnitNumber = myDb.getGroupDataForUName(courseId);



        mArrayLisUnitId = new ArrayList<>();

        while (mCursorForUnitNumber.moveToNext()) {

            mArrayLisUnitId.add(mCursorForUnitNumber.getString(2));
        }

        String abcd2="";

    }
}
