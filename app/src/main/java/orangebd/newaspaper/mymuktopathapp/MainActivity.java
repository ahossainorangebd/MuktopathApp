package orangebd.newaspaper.mymuktopathapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private Context mContext;

    private LinearLayout allCourseBtn;
    private ImageView mAllCourseLogo;

    private LinearLayout recomendedBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext=this;

        getSupportActionBar().hide();

        mAllCourseLogo=findViewById(R.id.allCourseLogo);

        allCourseBtn=findViewById(R.id.allCourseId);
        allCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(mContext,AllCourseActivity.class);
                v.getContext().startActivity(i);
            }
        });


        recomendedBtn=findViewById(R.id.recomendedBtnId);
        recomendedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(mContext,RecomendedActivity.class);
                v.getContext().startActivity(i);
            }
        });

        /*
        mContext=this;

        Intent i=new Intent(mContext,SplashActivity.class);
        startActivity(i);
        finish();
        */

        //TODO
        //this is to check if git hub is working properly or not
    }
}
