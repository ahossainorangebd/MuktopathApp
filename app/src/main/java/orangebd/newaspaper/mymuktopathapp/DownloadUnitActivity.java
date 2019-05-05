package orangebd.newaspaper.mymuktopathapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class DownloadUnitActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private ArrayList<ArrayList<String>> mArrayLisWholeData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_unit);

        recyclerView=findViewById(R.id.recyclerviewdownloadedunitlist);

        mArrayLisWholeData = new ArrayList<>();

        ArrayList<String> unitList = this.getIntent().getExtras().getStringArrayList("unitidlist");
        ArrayList<String> pathList = this.getIntent().getExtras().getStringArrayList("pathlist");
        ArrayList<String> lessonList = this.getIntent().getExtras().getStringArrayList("lessonlist");
        ArrayList<String> lessonDetailList = this.getIntent().getExtras().getStringArrayList("lessondetaillist");
        ArrayList<String> coursename = this.getIntent().getExtras().getStringArrayList("coursename");


        mArrayLisWholeData.add(unitList);
        mArrayLisWholeData.add(pathList);
        mArrayLisWholeData.add(lessonList);
        mArrayLisWholeData.add(lessonDetailList);
        mArrayLisWholeData.add(coursename);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new RecyclerViewAdapterDownloadedCourseList(mArrayLisWholeData,this);
        recyclerView.setAdapter(adapter);
    }
}
