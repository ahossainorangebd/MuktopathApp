package orangebd.newaspaper.mymuktopathapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class SelectCategoryActivity extends AppCompatActivity {

    ArrayList<ParentMenu> arrayParents = new ArrayList<ParentMenu>();
    ArrayList<Parent> childList=new ArrayList<>();

    private ExpandableListView mExpandableList;
    private DrawerLayout mDrawerLayout;

    private Context mContext;

    private Button mNextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_category);

        mContext=this;

        mNextBtn=findViewById(R.id.nextBtnId);
        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, MyPageActivity.class);
                v.getContext().startActivity(i);
            }
        });

        //mExpandableList = findViewById(R.id.expandable_list);

        new GetMenu().execute();
    }


    private class GetMenu extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //mProgressDialog=new ProgressDialog(context);
            //mProgressDialog.setIndeterminate(true);
            //mProgressDialog.setMessage("Menu Data is downloading...");
            //mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = "https://odhikar.news/json-feed/header-menu.json";
            //String burl="https://sahos24.com/json-feed/all-menu.json";
            String jsonStr = sh.makeServiceCall(url);
            int total=0;
            //Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) try {
                JSONObject jsonObj = new JSONObject(jsonStr);

                try {
                    JSONArray onlineObject=(JSONArray)jsonObj.get("header");
                    for (int i=0;i<onlineObject.length();i++){
                        ParentMenu parent = new ParentMenu();
                        JSONObject dynamicKey = (JSONObject) onlineObject.getJSONObject(i);
                        String oid = dynamicKey.getString("id");
                        String ocatName = dynamicKey.getString("cat_name");
                        String odetailURL = dynamicKey.getString("data-url");

                        parent.setTitle(ocatName);
                        parent.setDtl_url(odetailURL);
                        parent.setmID(oid);

                        JSONObject onlineChildObject = (JSONObject) jsonObj.get("child");
                        Iterator<?> keys = onlineChildObject.keys();
                        while(keys.hasNext() ) {
                            String oKey = (String) keys.next();
                            try {
                                JSONArray childArray = (JSONArray) onlineChildObject.get(oKey);
                                if (childArray.length() > 0) {
                                    childList=new ArrayList<>();
                                    for (int j = 0; j < childArray.length(); j++) {
                                        JSONObject childObj = (JSONObject) childArray.getJSONObject(j);
                                        String childID = childObj.getString("parent_id");
                                        if (oid.equalsIgnoreCase(childID)) {
                                            JSONObject nestedJson = childArray.getJSONObject(j);
                                            Parent p = new Parent();
                                            p.setCat_name(nestedJson.getString("cat_name"));
                                            p.setDtl_url(nestedJson.getString("data-url"));

                                            childList.add(p);
                                        }
                                        if (childList.size() > 0)
                                            parent.setArrayChildren(childList);
                                    }
                                }
                            }
                            catch (JSONException e){
                                continue;
                            }
                        }
                        arrayParents.add(parent);
                    }
                    //publishProgress((total*100)/object.length());

                    ParentMenu pp=new ParentMenu();
                    pp.setTitle("প্রচ্ছদ");
                    arrayParents.add(0,pp);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } catch (final JSONException e) {
                //Log.e(TAG, "Json parsing error: " + e.getMessage());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Json parsing error: " + e.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });

            }
            else {
                //Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server.",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            GlobalVar.gParentMenue=arrayParents;

            mExpandableList.setAdapter(new MyCustomAdapter(mContext,arrayParents,mExpandableList));
            mExpandableList.setGroupIndicator(null);
            mExpandableList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                @Override
                public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                    return true;
                }
            });
            //mProgressDialog.dismiss();
        }
    }
}
