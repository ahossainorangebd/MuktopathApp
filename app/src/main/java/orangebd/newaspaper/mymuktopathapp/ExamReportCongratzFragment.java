package orangebd.newaspaper.mymuktopathapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class ExamReportCongratzFragment extends Fragment {

    private View view;

    private ImageView passOrFailImage;

    private TextView mPercentageTextView;

    private TextView passOrFailText;
    private TextView congratzTextView;
    private TextView passMarkTextview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_exam_report_congratz, container, false);

        passOrFailImage=view.findViewById(R.id.passOrFailIcon);

        mPercentageTextView=view.findViewById(R.id.percentageTextId);
        passOrFailText=view.findViewById(R.id.passOrFailTextId);
        congratzTextView=view.findViewById(R.id.congratzId);
        passMarkTextview=view.findViewById(R.id.passMarkTvId);

        double iPassMark = Double.parseDouble(GlobalVar.gEPassMark);
        double iTotalGainedMark = Double.parseDouble(GlobalVar.gETotalGainedMark);

        passOrFailImage.setBackgroundResource(R.drawable.pending_clock_icon);
        passOrFailText.setText("Your result is pending.");
        congratzTextView.setText("");

        /*if(iPassMark <= iTotalGainedMark) {
            passOrFailImage.setBackgroundResource(R.drawable.congratz_passed_icon);
            passOrFailText.setText("You passed.");
            congratzTextView.setText("Congratz");
        }
        else {
            passOrFailImage.setBackgroundResource(R.drawable.mukto_not_pass_icon);
            passOrFailText.setText("You didn't pass.");

            passMarkTextview.setText("You have to score at least "+ GlobalVar.gEPassMark +"%");
        }*/

        mPercentageTextView.setText(GlobalVar.gETotalGainedMark);

        return view;
    }
}
