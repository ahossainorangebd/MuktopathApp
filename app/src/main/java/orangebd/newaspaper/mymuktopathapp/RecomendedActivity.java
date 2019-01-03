package orangebd.newaspaper.mymuktopathapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class RecomendedActivity extends AppCompatActivity {

    private Context mContext;

    // Button Layout of footer

    private LinearLayout myPageBtn;
    private LinearLayout myKhujunBtn;
    private LinearLayout myRecommendedBtn;
    private LinearLayout myDownloadsBtn;
    private LinearLayout myProfileBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomended);

        mContext=this;

        myPageBtn = findViewById(R.id.myPageId);
        myKhujunBtn = findViewById(R.id.khujunBtnId);
        myRecommendedBtn = findViewById(R.id.recomendedBtnId);
        myDownloadsBtn = findViewById(R.id.downloadsBtnId);
        myProfileBtn = findViewById(R.id.profilePageBtnId);

        myPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(mContext,AllCourseActivity.class);
                v.getContext().startActivity(i);
            }
        });

        myKhujunBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(mContext,SearchActivity.class);
                v.getContext().startActivity(i);
            }
        });

        myDownloadsBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i=new Intent(mContext,DownloadActivity.class);
                v.getContext().startActivity(i);
            }
        });

        myProfileBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i=new Intent(mContext,ProfileActivity.class);
                v.getContext().startActivity(i);
            }
        });



    }
}
