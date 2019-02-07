package orangebd.newaspaper.mymuktopathapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MyPageCourseDetail extends AppCompatActivity {

    private static RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page_course_detail);

        getSupportActionBar().hide();

        mContext=this;

        setRecyclerView();

    }

    private void setRecyclerView() {

        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setNestedScrollingEnabled(false);


        //Alter
        adapter=new RecyclerViewAdapterMyPage(GlobalVar.gChildArrayOfContent,mContext);

        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}
