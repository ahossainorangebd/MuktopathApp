package orangebd.newaspaper.mymuktopathapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    private Button splashActvtyCreateAccountBtn;
    private TextView slpashActLoginTxt;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mContext=this;


        splashActvtyCreateAccountBtn=findViewById(R.id.splashActvtyCreateAccountId);
        splashActvtyCreateAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(mContext,CreateAccountActivity.class);
                v.getContext().startActivity(i);
            }
        });

        slpashActLoginTxt=findViewById(R.id.slpashActLoginId);
        slpashActLoginTxt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent i=new Intent(mContext,LoginActivity.class);
                v.getContext().startActivity(i);
            }
        });
    }
}
