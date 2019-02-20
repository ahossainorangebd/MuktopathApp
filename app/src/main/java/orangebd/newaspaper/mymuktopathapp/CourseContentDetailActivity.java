package orangebd.newaspaper.mymuktopathapp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class CourseContentDetailActivity extends AppCompatActivity {


    private Context context;

    String DetailDescription;
    String title;

    private TextView titleTextView;
    private WebView detailDescTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_content_detail);

        context=this;

        View view = LayoutInflater.from(context).inflate(R.layout.custom_logodetails, null, false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(view);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#7a19aa")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        titleTextView=findViewById(R.id.courseTitle);
        detailDescTextView=findViewById(R.id.detailDesc);

        DetailDescription=getIntent().getExtras().getString("detail");
        title = getIntent().getExtras().getString("ttl");

        titleTextView.setText(title);

        detailDescTextView.getSettings().setJavaScriptEnabled(true);
        detailDescTextView.getSettings().setDomStorageEnabled(true);

        detailDescTextView.loadDataWithBaseURL("",DetailDescription, "text/html", "utf-8", "");
    }
}
