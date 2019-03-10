package orangebd.newaspaper.mymuktopathapp;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;


public class MyPageDetailFragment2 extends Fragment {

    private Context context;

    private View view;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private TextView unitOrderText;
    private TextView unitTitleText;

    private ExpandableListView mExpandableList;


    ArrayList<ParentMenu> arrayParents = new ArrayList<ParentMenu>();
    ArrayList<Parent> childList=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_my_page_detail_fragment2, container, false);

        context=getContext();

        mExpandableList = view.findViewById(R.id.ques_option_list);

        ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> unitArray = GlobalVar.courseContentDetailList.get(0).getmArrayListCourseUnits();
        final ArrayList<DetailDataModelCoursesDetailContents> units = unitArray.get(GlobalVar.gNthCourse);

        ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> unitQuizes = GlobalVar.courseContentDetailList.get(0).getmArrayListCourseQuizs();
        final ArrayList<DetailDataModelCoursesDetailContents> quizes = unitQuizes.get(GlobalVar.gNthCourse);



        /** For question and option expandable list*/
        final ArrayList<DetailDataModelCoursesDetailContents> mQuizParents = GlobalVar.courseContentDetailList.get(0).getmArrayListCourseQuizs().get(GlobalVar.gNthCourse);
        final ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> mQuizOptionChilds = GlobalVar.courseContentDetailList.get(0).getmArrayListCourseQuizOptions().get(GlobalVar.gNthCourse);

        for (int i=0;i<mQuizParents.size();i++){
            ParentMenu parent = new ParentMenu();


            String quesTitle = mQuizParents.get(i).getmQuizTitle();

            parent.setTitle(quesTitle);

            String childID="";
            childList=new ArrayList<>();
            for(int child=0; child<mQuizOptionChilds.size(); child++){
                try {
                     childID = mQuizOptionChilds.get(i).get(child).getmOptionBody();
                }
                catch (Exception ex){

                }

                Parent p = new Parent();

                p.setCat_name(childID);
                childList.add(p);
            }


            if (childList.size() > 0)
                parent.setArrayChildren(childList);

            arrayParents.add(parent);
        }

        mExpandableList.setAdapter(new MyCustomAdapterQuiz(context,arrayParents,mExpandableList));
        mExpandableList.setGroupIndicator(null);
        mExpandableList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });


        /**end of expandable list for questions and options*/


        if(units.size()>1) {
            final String unitTitle = units.get(1).getUnitNames();
            final String unitOrder = units.get(1).getUnitOrders();

            unitOrderText = view.findViewById(R.id.unitOrder);
            unitTitleText = view.findViewById(R.id.unitTitleId);

            unitOrderText.setText(unitOrder);
            unitTitleText.setText(unitTitle);
        }
        else {
            Toast.makeText(context,"No more data to show", Toast.LENGTH_LONG).show();
        }

        //setRecyclerView();

        return view;

    }

    /*private void setRecyclerView() {

        recyclerView = view.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setNestedScrollingEnabled(false);

        new GetCoursesContents().execute();
    }*/

    /*public class GetCoursesContents extends AsyncTask<String, Void, Void>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //mProgressSpinner.setIndeterminate(true);
            //mProgressSpinner.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(String... arg0) {

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            //adapter=new RecyclerViewAdapterMyPageContents(GlobalVar.thisFragmentContents,context);
            try {
                adapter = new RecyclerViewAdapterMyPageQuizes(GlobalVar.thisFragmentQuizes, context);
            }
            catch (Exception ex){
                Log.d("", "onPostExecute: ");
            }

            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            //mProgressSpinner.setVisibility(View.GONE);
        }
    }*/
}
