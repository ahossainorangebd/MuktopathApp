package orangebd.newaspaper.mymuktopathapp;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MyCustomAdapterQuiz extends BaseExpandableListAdapter {

    private boolean isCorrAnsGiven = false;
    private boolean isExpandedIsAlwaysTrue=true;

    private ArrayList<String> mCheckCheckedString = new ArrayList<>();

    Button btn;
    private ExpandableListView mExpandableList;
    private LayoutInflater inflater;
    private ArrayList<ParentMenu> mParent;
    private ArrayList<Parent> mChild;
    private Context mContext;
    private String parentTxt;
    private boolean isChildSelected;

    public MyCustomAdapterQuiz(Context context, ArrayList<ParentMenu> parent, ExpandableListView mExpandableList){
        mParent = parent;
        inflater = LayoutInflater.from(context);
        this.mExpandableList=mExpandableList;
        this.mContext=context;
    }


    //modelAlter.setmArrayListCourseQuizOptions
    @Override
    //counts the number of group/parent items so the list knows how many times calls getGroupView() method
    public int getGroupCount() {
        return mParent.size();
    }

    @Override
    //counts the number of children items so the list knows how many times calls getChildView() method
    public int getChildrenCount(int i) {

        return mParent.get(i).getArrayChildren().size();
    }

    @Override
    //gets the title of each parent/group

    public Object getGroup(int i) {

        return mParent.get(i).getTitle();
    }

    @Override
    //gets the name of each item
    public Object getChild(int i, int i1) {

        return mParent.get(i).getArrayChildren().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    //in this method you must set the text to see the parent/group on the list
    public View getGroupView(final int groupPosition, final boolean isExpanded, View convertView, ViewGroup viewGroup) {

        ViewHolder holder = new ViewHolder();
        holder.groupPosition = groupPosition;
        final String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_parent, viewGroup,false);
        }

        TextView textView = convertView.findViewById(R.id.list_item_text_view);
        ImageView expand = convertView.findViewById(R.id.expand);

        try {
            int count = getChildrenCount(groupPosition);
            if (count > 0)
                expand.setVisibility(View.VISIBLE);
        }
        catch (Exception ex){
            expand.setVisibility(View.INVISIBLE);
        }

        if (isExpanded) {
            expand.setImageResource(R.drawable.arrow_down_exp_white);
        }
        else {
            expand.setImageResource(R.drawable.arrow_up_exp_white);
        }

        //GlobalVar.isExpandForMenu=false;

        mExpandableList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener()
        {
            @Override
            public boolean onGroupClick(ExpandableListView parent,View v,int groupPosition, long id)
            {
                return true;
            }
        });
        mExpandableList.setOnChildClickListener(new ExpandableListView.OnChildClickListener()
        {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id)
            {
                return true;
            }
        });

        if (isExpandedIsAlwaysTrue==true)
            ( mExpandableList).expandGroup(groupPosition, true);

        else
            ( mExpandableList).expandGroup(groupPosition, true);

        textView.setText(headerTitle);

        //textView.setTextColor(Color.parseColor("#000"));

        convertView.setTag(holder);

        //return the entire view
        return convertView;
    }


    @Override
    //in this method you must set the text to see the children on the list
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup viewGroup) {

        ViewHolder holder = new ViewHolder();
        holder.childPosition = childPosition;
        holder.groupPosition = groupPosition;

        if (view == null) {
            view = inflater.inflate(R.layout.list_item_child, viewGroup,false);
        }

        final TextView textView = view.findViewById(R.id.list_item_text_child);
        final CheckBox checkBox = view.findViewById(R.id.checkBox1);

        /*textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkBox.isChecked()) {
                    checkBox.setChecked(true);
                }
                else
                    checkBox.setChecked(false);
            }
        });*/

        textView.setText(mParent.get(groupPosition).getArrayChildren().get(childPosition).getCat_name());

        final ArrayList<Parent> answerList= mParent.get(groupPosition).getArrayChildren();

        for(int ccs=0; ccs<mCheckCheckedString.size(); ccs++) {

            if(mCheckCheckedString.get(ccs).equalsIgnoreCase(mParent.get(groupPosition).getArrayChildren().get(childPosition).getCat_name())){

                checkBox.setChecked(true);
            }
            else
                checkBox.setChecked(false);
        }

        if(checkBox.isChecked() && isCorrAnsGiven==true) {
            checkBox.setChecked(true);
        }
        else
            checkBox.setChecked(false);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(checkBox.isChecked()) {

                    for(int tap=0; tap<answerList.size(); tap++) {

                        CharSequence checkedAns=textView.getText();

                        String ans=answerList.get(tap).getCat_name();

                        if(checkedAns.equals(ans)) {

                            String trueOrFalse = answerList.get(tap).getCat_id();

                            mCheckCheckedString.add(checkedAns.toString());

                            if (trueOrFalse.equalsIgnoreCase("true")) {

                                Toast.makeText(mContext,"Correct Answer",Toast.LENGTH_SHORT).show();
                                 isCorrAnsGiven=true;
                            }
                            else{

                                Toast.makeText(mContext,"Incorrect Answer",Toast.LENGTH_SHORT).show();
                                 isCorrAnsGiven=false;
                            }
                        }
                    }
                }
            }
        }
        );


        view.setTag(holder);

        //return the entire view
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        /* used to make the notifyDataSetChanged() method work */
        super.registerDataSetObserver(observer);
    }

    protected class ViewHolder {
        protected int childPosition;
        protected int groupPosition;
        protected Button button;
    }

    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            deleteDir(dir);
        }
        catch (Exception e) {
            Log.d("", "msg: ");
        }
    }
    public static boolean deleteDir(File dir) {

        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        }

        else if(dir!= null && dir.isFile()) {
            return dir.delete();
        }

        else {
            return false;
        }
    }
}
