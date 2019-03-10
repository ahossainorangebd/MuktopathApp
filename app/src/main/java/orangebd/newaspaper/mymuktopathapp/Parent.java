package orangebd.newaspaper.mymuktopathapp;

import java.util.ArrayList;

public class Parent {
    private String mTitle;
    private String id;
    private String cat_name;
    private String cat_id;

    private String img_url;
    private String parent_cat_id;
    private String update_time;
    private ArrayList<String> mArrayChildren;

    public void setTitle(String title){
        mTitle=title;
    }
    public String getTitle(){
       return mTitle;
    }
    public String getId() {
        return id;
    }
    public String getCat_name(){
        return cat_name;
    }
    public void setCat_name(String _cat_name){
        cat_name=_cat_name;
    }
    public void setId(String _id) {
        id = _id;
    }
    public String getCat_id(){
        return cat_id;
    }
    public void setCat_id(String _cat_id){
        cat_id=_cat_id;
    }


    public String getImg_url(){
        return img_url;
    }
    public void setImg_url(String _img_url){
        img_url=_img_url;
    }
    public String getParent_cat_id(){
        return parent_cat_id;
    }
    public void setParent_cat_id(String _parent_cat_id){
        parent_cat_id=_parent_cat_id;
    }
    public String getUpdate_time(){
        return update_time;
    }
    public void setUpdate_time(String _update_time){
        update_time=_update_time;
    }

    public ArrayList<String> getArrayChildren() {
        return mArrayChildren;
    }

    public void setArrayChildren(ArrayList<String> arrayChildren) {
        mArrayChildren = arrayChildren;
    }
}
