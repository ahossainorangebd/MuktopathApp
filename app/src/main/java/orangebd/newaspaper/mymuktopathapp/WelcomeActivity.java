package orangebd.newaspaper.mymuktopathapp;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WelcomeActivity extends AppCompatActivity {

    private Context mContext;

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        mContext=this;

        getSupportActionBar().hide();

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(mContext, SelectACategoryActivity.class);
                startActivity(i);
            }
        }, 2000);
    }
}
