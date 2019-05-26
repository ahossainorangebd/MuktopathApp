package orangebd.newaspaper.mymuktopathapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "newmuktopaathfinal3.db";
    public static final String TABLE_NAME = "download_table_final3";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "COURSEID";
    public static final String COL_3 = "UNITID";
    public static final String COL_4 = "FILEPATH";
    public static final String COL_5 = "LESSONNAME";
    public static final String COL_6 = "DETAIL";
    public static final String COL_7 = "COURSENAME";
    public static final String COL_8 = "UNITNAME";
    public static final String COL_9 = "COURSEBANNER";

    private String[] strColumnName={COL_1, COL_2 ,COL_3 ,COL_4 ,COL_5 ,COL_6 ,COL_7, COL_8, COL_9};

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,COURSEID TEXT, UNITID TEXT, FILEPATH TEXT, LESSONNAME TEXT, DETAIL TEXT, COURSENAME TEXT, UNITNAME TEXT, COURSEBANNER TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String courseid, String unitid, String filepath,String lessonTitle,String detail, String courseTitle, String unitTitle, String courseBanner) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2,courseid);
        contentValues.put(COL_3,unitid);
        contentValues.put(COL_4,filepath);
        contentValues.put(COL_5,lessonTitle);
        contentValues.put(COL_6,detail);
        contentValues.put(COL_7,courseTitle);
        contentValues.put(COL_8,unitTitle);
        contentValues.put(COL_9,courseBanner);

        long result=0;

        try {
            result = db.insert(TABLE_NAME, null, contentValues);
        }
        catch (Exception ex){
            Log.d("", "insertData: ");
        }
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public Cursor getGroupDataForCName() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res1=db.query(TABLE_NAME, strColumnName,null,null, DBHelper.COL_7,null,null);
        return res1;
    }

    public Cursor getGroupDataForUName(String courseId) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res2=db.query(TABLE_NAME + " where " + COL_2 + "='" + courseId + "'" , strColumnName,null,null, DBHelper.COL_8,null, COL_3);
        return res2;
    }

    public Cursor GetUnitDataFromDB(String courseId){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor =  db.rawQuery("select * from " + TABLE_NAME + " where " + COL_2 + "='" + courseId + "'" , null);

        return cursor;
    }

    public Cursor getGroupDataForUID() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res2=db.query(TABLE_NAME, strColumnName,null,null, DBHelper.COL_3,null,null);
        return res2;
    }

    public Cursor GetUnitNameFromDB(String mCourseId){
        SQLiteDatabase db = this.getWritableDatabase();

        String[] asColumnsToReturn = new String[] {COL_2};
        //Cursor cursor = db.query(false, TABLE_NAME, asColumnsToReturn, COL_2 + "=" + mCourseId + " AND " + COL_3 + "=" + unitId , null, null, null, null, null);

        Cursor cursor = db.rawQuery("select UNITNAME from " + TABLE_NAME + " where " + COL_2 + " = ? ", new String[] { mCourseId });

        return cursor;
    }





    public void deleteData () {
        SQLiteDatabase db = this.getWritableDatabase();

        String sqlStr="delete from " +TABLE_NAME;
        db.execSQL(sqlStr);
    }

    /*public boolean updateData(String id,String name,String surname,String marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,marks);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }

*/




    public Cursor GetLessonDataFromDB(String unitId, String mCourseId){
        SQLiteDatabase db = this.getWritableDatabase();
        //Cursor cursor =  db.rawQuery("select * from " + TABLE_NAME + " where " + COL_3 + "='" + unitId + "'" , null);

        String[] asColumnsToReturn = new String[] {COL_2, COL_3};
        //Cursor cursor = db.query(false, TABLE_NAME, asColumnsToReturn, COL_2 + "=" + mCourseId + " AND " + COL_3 + "=" + unitId , null, null, null, null, null);

        Cursor cursor = db.rawQuery("select LESSONNAME ,DETAIL, FILEPATH, COURSEBANNER from " + TABLE_NAME + " where " + COL_2 + " = ? AND " + COL_3 + " = ? ", new String[] { mCourseId, unitId });

        return cursor;
    }

    /*String testing = "select * from " + TABLE_NAME + " where " + COL_3 + "='" + unitId + "' and " + COL_7 + "='" + mCourseId + "'" ;

    Cursor cursor =  db.rawQuery("select * from " + TABLE_NAME + " where " + COL_3 + "='" + unitId + "' and " + COL_7 + "='" + mCourseId + "'" , null);*/

    public Integer deleteParticularData (String fileName) {

        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLE_NAME, "LESSONNAME = ?",new String[] {fileName});
    }




}