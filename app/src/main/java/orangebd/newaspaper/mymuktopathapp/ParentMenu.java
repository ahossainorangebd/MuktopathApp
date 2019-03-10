package orangebd.newaspaper.mymuktopathapp;

import java.util.ArrayList;

public class ParentMenu {
    private String mTitle;

    private String mID;

    private ArrayList<Parent> mArrayChildren;
    public void setTitle(String title){
        mTitle=title;
    }
    public String getTitle(){
        return mTitle;
    }
    public ArrayList<Parent> getArrayChildren() {
        return mArrayChildren;
    }

    public void setArrayChildren(ArrayList<Parent> arrayChildren) {
        mArrayChildren = arrayChildren;
    }

    public String getmID() {
        return mID;
    }

    public void setmID(String mID) {
        this.mID = mID;
    }
}
