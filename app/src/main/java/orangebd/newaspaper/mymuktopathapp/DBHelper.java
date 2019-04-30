package orangebd.newaspaper.mymuktopathapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "muktopaath.db";
    public static final String TABLE_NAME = "course_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "COURSEID";
    public static final String COL_3 = "UNITID";
    public static final String COL_4 = "PATH";
    public static final String COL_5 = "TITLE";
    public static final String COL_6 = "DETAIL";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,COURSEID TEXT, UNITID TEXT, FILEPATH TEXT, LESSONNAME TEXT, DETAIL TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String courseid, String unitid, String filepath,String lessonTitle,String detail) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,courseid);
        contentValues.put(COL_3,unitid);
        contentValues.put(COL_4,filepath);
        contentValues.put(COL_5,lessonTitle);
        contentValues.put(COL_6,detail);
        long result = db.insert(TABLE_NAME,null ,contentValues);
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

    public boolean updateData(String id,String name,String surname,String marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,marks);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }


    public Cursor GetDataFromDB(String courseId){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor =  db.rawQuery("select * from " + TABLE_NAME + " where " + COL_2 + "='" + courseId + "'" , null);

        return cursor;
    }
}