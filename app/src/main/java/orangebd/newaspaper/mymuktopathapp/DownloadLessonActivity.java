package orangebd.newaspaper.mymuktopathapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class DownloadLessonActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_lesson);

        recyclerView=findViewById(R.id.recyclerviewdownloadedLessonlist);


        ArrayList<String> pathList = this.getIntent().getExtras().getStringArrayList("pathlist");
        ArrayList<String> lessonList = this.getIntent().getExtras().getStringArrayList("lessonlist");
        ArrayList<String> lessonDetailList = this.getIntent().getExtras().getStringArrayList("lessondetaillist");



        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new RecyclerViewAdapterDownloadedContentList(lessonList,pathList,lessonDetailList,this);
        recyclerView.setAdapter(adapter);




    }
}
