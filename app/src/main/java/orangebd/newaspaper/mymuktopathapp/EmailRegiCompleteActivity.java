package orangebd.newaspaper.mymuktopathapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class EmailRegiCompleteActivity extends AppCompatActivity {

    private String getMsg;

    private TextView mReturnMessage;
    private Button mLoginRedirectbtn;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_regi_complete);

        context=this;

        getMsg = getIntent().getExtras().getString("msg");

        mReturnMessage=findViewById(R.id.returnMessage);
        mLoginRedirectbtn=findViewById(R.id.submitBtn);


        mReturnMessage.setText(getMsg);


        mLoginRedirectbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(context,LoginActivity.class);
                startActivity(i);

            }
        });

    }
}
