package orangebd.newaspaper.mymuktopathapp;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class DownloadLessonActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private Context mContext;

    private DBHelper myDb;


    private String unitId;
    private String mCourseid;


    private ArrayList<String> mArrayLisFilePath;
    private ArrayList<String> mArrayLisLessonName;
    private ArrayList<String> mArrayLisLessonDetail;
    private ArrayList<String> mArrayLisBannerDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_lesson);

        mContext=this;

        recyclerView=findViewById(R.id.recyclerviewdownloadedLessonlist);



        /*ArrayList<String> pathList = this.getIntent().getExtras().getStringArrayList("pathlist");
        ArrayList<String> lessonList = this.getIntent().getExtras().getStringArrayList("lessonlist");
        ArrayList<String> lessonDetailList = this.getIntent().getExtras().getStringArrayList("lessondetaillist");*/

        unitId = this.getIntent().getExtras().getString("unitid");
        mCourseid = this.getIntent().getExtras().getString("cid");


        GetDownloadedData();



        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter=new RecyclerViewAdapterDownloadedContentList(mArrayLisLessonName, mArrayLisFilePath, mArrayLisLessonDetail, mArrayLisBannerDetail,this);
        recyclerView.setAdapter(adapter);
    }


    public void GetDownloadedData() {

        myDb = new DBHelper(mContext);

        Cursor mCursorForUnitNumber = myDb.GetLessonDataFromDB(unitId, mCourseid);

        mArrayLisFilePath = new ArrayList<>();
        mArrayLisLessonName = new ArrayList<>();
        mArrayLisLessonDetail = new ArrayList<>();
        mArrayLisBannerDetail = new ArrayList<>();

        while (mCursorForUnitNumber.moveToNext()) {

            /*mArrayLisFilePath.add(mCursorForUnitNumber.getString(3));
            mArrayLisLessonName.add(mCursorForUnitNumber.getString(4));
            mArrayLisLessonDetail.add(mCursorForUnitNumber.getString(5));
            mArrayLisBannerDetail.add(mCursorForUnitNumber.getString(8));*/

            mArrayLisLessonName.add(mCursorForUnitNumber.getString(0));
            mArrayLisLessonDetail.add(mCursorForUnitNumber.getString(1));
            mArrayLisFilePath.add(mCursorForUnitNumber.getString(2));
            mArrayLisBannerDetail.add(mCursorForUnitNumber.getString(3));
        }

        String abcd2="";

    }
}
