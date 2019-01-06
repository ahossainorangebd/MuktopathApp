package orangebd.newaspaper.mymuktopathapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private Context mContext;
    String selectedItem;


    // Button Layout of footer

    private LinearLayout allCourseBtn;
    private LinearLayout recomendedBtn;
    private LinearLayout myPageBtn;
    private LinearLayout downloadsBtn;
    private LinearLayout profileBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mContext=this;

        allCourseBtn = findViewById(R.id.allCourseBtnId);
        recomendedBtn = findViewById(R.id.recomendedBtnId);
        myPageBtn = findViewById(R.id.myPageBtnId);
        downloadsBtn = findViewById(R.id.downloadsBtnId);
        profileBtn = findViewById(R.id.profilePageBtnId);

        allCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, MainActivity.class);
                v.getContext().startActivity(i);
            }
        });

        recomendedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, RecomendedActivity.class);
                v.getContext().startActivity(i);
            }
        });

        myPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(mContext,MyPageActivity.class);
                v.getContext().startActivity(i);
            }
        });

        downloadsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(mContext,DownloadActivity.class);
                v.getContext().startActivity(i);
            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(mContext,ProfileActivity.class);
                v.getContext().startActivity(i);
            }
        });

        //Dropdown List ids
        Spinner mSpinner=findViewById(R.id.spnrOne);
        Spinner mSpinner2=findViewById(R.id.spnrTwo);
        Spinner mSpinner3=findViewById(R.id.spnrThree);
        Spinner mSpinner4=findViewById(R.id.spnrFour);
        Spinner mSpinner5=findViewById(R.id.spnrFive);

        //Adding options
        final List<String> cc=new ArrayList<String>();
        cc.add("★ ★ ★ ★ ★");
        cc.add("★ ★ ★ ★");
        cc.add("★ ★ ★");
        cc.add("★ ★");
        cc.add("★");


        final List<String> cc2=new ArrayList<String>();
        cc2.add("0-1 Hours");
        cc2.add("1-1 Hours");
        cc2.add("2-3 Hours");
        cc2.add("3-4 Hours");
        cc2.add("4-5 Hours");
        cc2.add("5-6 Hours");
        cc2.add("6-7 Hours");
        cc2.add("7-8 Hours");
        cc2.add("8-9 Hours");
        cc2.add("9-10 Hours");


        final List<String> cc3=new ArrayList<String>();
        cc3.add("Bangla");
        cc3.add("English");


        final List<String> cc4=new ArrayList<String>();
        cc4.add("Beginner");
        cc4.add("Intermediate");
        cc4.add("Expert");
        cc4.add("All levels");


        final List<String> cc5=new ArrayList<String>();
        cc5.add("BDT 1-999");
        cc5.add("BDT 1000-1999");
        cc5.add("BDT 2000-2999");
        cc5.add("BDT 3000-3999");
        cc5.add("BDT 4000-4999");
        cc5.add("BDT 5000-5999");
        cc5.add("BDT 6000-6999");
        cc5.add("BDT 7000-7999");
        cc5.add("BDT 800 0-8999");
        cc5.add("BDT 9000-10,000");

        //calling the spinner_item_layout
        ArrayAdapter<String> spinnerArrayAdapterCC = new ArrayAdapter<>(mContext, R.layout.spinner_item, cc);
        spinnerArrayAdapterCC.setDropDownViewResource(R.layout.spinner_item);
        mSpinner.setAdapter(spinnerArrayAdapterCC);




        ArrayAdapter<String> spinnerArrayAdapterCC2 = new ArrayAdapter<>(mContext, R.layout.spinner_item, cc2);
        spinnerArrayAdapterCC2.setDropDownViewResource(R.layout.spinner_item);
        mSpinner2.setAdapter(spinnerArrayAdapterCC2);



        ArrayAdapter<String> spinnerArrayAdapterCC3 = new ArrayAdapter<>(mContext, R.layout.spinner_item, cc3);
        spinnerArrayAdapterCC3.setDropDownViewResource(R.layout.spinner_item);
        mSpinner3.setAdapter(spinnerArrayAdapterCC3);



        ArrayAdapter<String> spinnerArrayAdapterCC4 = new ArrayAdapter<>(mContext, R.layout.spinner_item, cc4);
        spinnerArrayAdapterCC4.setDropDownViewResource(R.layout.spinner_item);
        mSpinner4.setAdapter(spinnerArrayAdapterCC4);


        ArrayAdapter<String> spinnerArrayAdapterCC5 = new ArrayAdapter<>(mContext, R.layout.spinner_item, cc5);
        spinnerArrayAdapterCC5.setDropDownViewResource(R.layout.spinner_item);
        mSpinner5.setAdapter(spinnerArrayAdapterCC5);


        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                int item=position;
                selectedItem  =cc.get(item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });


        mSpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                int item=position;
                selectedItem  =cc2.get(item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        mSpinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                int item=position;
                selectedItem  =cc3.get(item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        mSpinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                int item=position;
                selectedItem  =cc4.get(item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        mSpinner5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                int item=position;
                selectedItem  =cc5.get(item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

    }
}
