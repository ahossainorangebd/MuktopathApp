package orangebd.newaspaper.mymuktopathapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import static java.sql.Types.INTEGER;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "muktopaath";
    public static final int DBVERSION = 1;

    public static final String TBL_CONTENT = "tblContent";

    public static final String COL_VIDEO_ID = BaseColumns._ID;
    public static final String COL_VIDEO_PATH = "video_path";
    public static final String COL_USER_ID="User_Id";
    public static final String COL_CONTENT_ID="Content_Id";

    SQLiteDatabase mDB;

    public DBHelper(Context context) {
        super(context, DBNAME, null, DBVERSION);
        mDB = this.getWritableDatabase();
        String crt_video_table = "CREATE TABLE IF NOT EXISTS " + TBL_CONTENT + "(" +
                COL_VIDEO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_USER_ID + "INTEGER,"+ COL_CONTENT_ID +"INTEGER,"+
                COL_VIDEO_PATH + " TEXT" +
                ")";
        mDB.execSQL(crt_video_table);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String crt_video_table = "CREATE TABLE IF NOT EXISTS " + TBL_CONTENT + "(" +
                COL_VIDEO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_USER_ID + "INTEGER,"+ COL_CONTENT_ID +"INTEGER,"+
                COL_VIDEO_PATH + " TEXT" +
                ")";
        db.execSQL(crt_video_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long addVideo(String path,int UserId,int ContentID) {
        ContentValues cv = new ContentValues();
        cv.put(COL_VIDEO_PATH,path);
        cv.put(COL_USER_ID,UserId);
        cv.put(COL_CONTENT_ID,ContentID);
        return mDB.insert(TBL_CONTENT,null,cv);
    }

    public Cursor getVideos() {
        return mDB.query(TBL_CONTENT,null,null,null,null,null,null);
    }

    public int deleteVideoFromDB(long id) {
        String whereclause = COL_VIDEO_ID + "=?";
        String[] whereargs = new String[]{String.valueOf(id)};
        return mDB.delete(TBL_CONTENT,whereclause,whereargs);
    }
}