package orangebd.newaspaper.mymuktopathapp;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Typeface;
import android.support.v4.widget.DrawerLayout;
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

public class MyCustomAdapter extends BaseExpandableListAdapter {

    Button btn;
    private ExpandableListView mExpandableList;
    private LayoutInflater inflater;
    private ArrayList<ParentMenu> mParent;
    private Context mContext;
    private String parentTxt;
    private boolean isChildSelected;

    public MyCustomAdapter(Context context, ArrayList<ParentMenu> parent, ExpandableListView mExpandableList){
        mParent = parent;
        inflater = LayoutInflater.from(context);
        this.mExpandableList=mExpandableList;
        this.mContext=context;
    }


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


                //mDrawerLayout.closeDrawer(View.GONE);
                isChildSelected=false;
                try {
                    if (mParent.get(groupPosition).getDtl_url() != null) {

                        if (GlobalVar.isExpandForMenu==true) {
                            (mExpandableList).collapseGroup(groupPosition);

                            GlobalVar.isExpandForMenu=false;
                        }

                        else {
                            (mExpandableList).expandGroup(groupPosition, true);

                            GlobalVar.isExpandForMenu=true;
                        }

                        parentTxt=mParent.get(groupPosition).getTitle();

                        GlobalVar.gParentChildMenuURL=mParent.get(groupPosition).getDtl_url();

                        ArrayList<String> arrayList=new ArrayList<>();
                        parentTxt=mParent.get(groupPosition).getTitle();
                        arrayList.add(mParent.get(groupPosition).getDtl_url());
                        arrayList.add(mParent.get(groupPosition).getTitle());
                        arrayList.add("false");

                        GlobalVar.gMenuArrayList=arrayList;
                        GlobalVar.getParentName=parentTxt;

                        GlobalVar.gParentMenuURL=mParent.get(groupPosition).getDtl_url();
                    }

                    else {
                            deleteCache(mContext);
                            Intent i=new Intent(mContext,MainActivity.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            v.getContext().startActivity(i);
                    }
                }
                catch (Exception ex){
                    Log.d("",ex.getMessage());
                }
                return true;
            }
        });
        mExpandableList.setOnChildClickListener(new ExpandableListView.OnChildClickListener()
        {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id)
            {
                GlobalVar.isExpandForMenu=true;

                ArrayList<String> arrayList=new ArrayList<>();
                isChildSelected=true;


                String dtl=mParent.get(groupPosition).getArrayChildren().get(childPosition).getDtl_url();
                //new GetDetails().execute(dtl);
                String title=mParent.get(groupPosition).getArrayChildren().get(childPosition).getCat_name();
                String title2=parentTxt;

                GlobalVar.getParentName=title2;

                arrayList.add(dtl);
                arrayList.add(title);
                arrayList.add("true");
                arrayList.add(title2);

                GlobalVar.gMenuArrayList=arrayList;

                //mHeaderText.setText(title);
                //mHeaderText.setVisibility(View.VISIBLE);
                return true;
            }
        });

        /*expand.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (isExpanded)
                    ( mExpandableList).collapseGroup(groupPosition);

                else
                    ( mExpandableList).expandGroup(groupPosition, true);

                parentTxt=mParent.get(groupPosition).getTitle();

                GlobalVar.gParentChildMenuURL=mParent.get(groupPosition).getDtl_url();
            }

        });*/


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

        TextView textView = view.findViewById(R.id.list_item_text_child);
        final CheckBox checkBox = view.findViewById(R.id.checkBox1);

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkBox.isChecked()) {
                    checkBox.setChecked(true);
                }
                else
                    checkBox.setChecked(false);
            }
        });


        //textView.setText(mParent.get(groupPosition).getArrayChildren().get(childPosition));
        textView.setText(mParent.get(groupPosition).getArrayChildren().get(childPosition).getCat_name());

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
        } catch (Exception e) {}
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
        } else if(dir!= null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }
}
