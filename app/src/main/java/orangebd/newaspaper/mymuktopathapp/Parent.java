package orangebd.newaspaper.mymuktopathapp;

import java.util.ArrayList;

public class Parent {
    private String mTitle;
    private String id;
    private String cat_name;
    private String cat_id;
    private String dtl;
    private String dtl_url;
    private String entry_time;
    private String hl1;
    private String hl2;
    private String hl3;
    private String hl_color;
    private String img_caption;
    private String img_url;
    private String parent_cat_id;
    private String prefix_keyword;
    private String rpt;
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
    public String getDtl(){
        return dtl;
    }
    public void setDtl(String _dtl){
        dtl=_dtl;
    }
    public String getDtl_url(){
        return dtl_url;
    }
    public void setDtl_url(String _Dtl_utl){
        dtl_url=_Dtl_utl;
    }
    public String getEntry_time(){
        return entry_time;
    }
    public void setEntry_time(String _entry_time){
        entry_time=_entry_time;
    }
    public String getHl1(){
        return hl1;
    }
    public void setHl1(String _hl1){
        hl1=_hl1;
    }
    public String getHl2(){
        return hl2;
    }
    public void setHl2(String _hl2){
        hl2=_hl2;
    }
    public String getHl3(){
        return hl3;
    }
    public void setHl3(String _hl3){
        hl3=_hl3;
    }
    public String getHl_color(){
        return hl_color;
    }
    public void setHl_color(String _hl_color){
        hl_color=_hl_color;
    }

    public String getRpt() {
        return rpt;
    }
    public void setRpt(String _rpt){
        rpt=_rpt;
    }
    public String getImg_caption(){
        return img_caption;
    }
    public void setImg_caption(String _img_caption){
        img_caption=_img_caption;
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
    public String getPrefix_keyword(){
        return prefix_keyword;
    }
    public void setPrefix_keyword(String _prefix_keyword){
        prefix_keyword=_prefix_keyword;
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
