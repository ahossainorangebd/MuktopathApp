package orangebd.newaspaper.mymuktopathapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ExamReportPendingActivity extends AppCompatActivity {

    private ImageView passOrFailImage;

    private TextView mPercentageTextView;

    private TextView passOrFailText;
    private TextView congratzTextView;
    private TextView passMarkTextview;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_report_pending);

        context=this;

        passOrFailImage=findViewById(R.id.passOrFailIcon);
        Button goBackBtn=findViewById(R.id.gobackToMyPage);
        goBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

                /*Intent i = new Intent(context, MyPageActivity.class);

                v.getContext().startActivity(i);*/
            }
        });

        mPercentageTextView=findViewById(R.id.percentageTextId);
        passOrFailText=findViewById(R.id.passOrFailTextId);
        congratzTextView=findViewById(R.id.congratzId);
        passMarkTextview=findViewById(R.id.passMarkTvId);

        passOrFailImage.setBackgroundResource(R.drawable.pending_clock_icon);
        passOrFailText.setText("Your result is pending.");
        congratzTextView.setText("");

        mPercentageTextView.setText(GlobalVar.gETotalGainedMark);


        String qwwrer="";

    }
}
