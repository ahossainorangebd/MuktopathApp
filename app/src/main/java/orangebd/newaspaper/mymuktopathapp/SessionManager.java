package orangebd.newaspaper.mymuktopathapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Handler;

import java.io.File;
import java.util.HashMap;

public class SessionManager {
    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    Editor editor;

    private Handler mHandler = new Handler();

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "BASAPref";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // User name (make variable public to access from outside)
    /*public static final String KEY_NAME = "name";*/

    // Email address (make variable public to access from outside)
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASS= "password";
    public static final String KEY_PHONE= "phone";
    // Constructor

    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     * */
    public void createLoginSession(String email, String password){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref
        /*editor.putString(KEY_NAME, name);*/

        // Storing email in pref
        editor.putString(KEY_EMAIL, email);

        editor.putString(KEY_PASS,password);
        // commit changes
        editor.commit();
    }


    public void createLoginSessionForPhone(String phone, String password){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref
        /*editor.putString(KEY_NAME, name);*/


        editor.putString(KEY_PHONE, phone);
        editor.putString(KEY_PASS,password);
        // commit changes
        editor.commit();
    }

    /**
     * Check login method will check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */

    public boolean checkLogin(){
        // Check login status

        String abcd="";

        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity

            String abcd2="";

            Intent i = new Intent(_context, SplashActivity.class);

            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);



            return false;
        }
        /*else {
            Intent i = new Intent(_context, MainActivity.class);
            i.putExtra("isadmin", true);

            _context.startActivity(i);
            return true;
        }*/
        return true;

    }



    /**
     * Get stored session data
     * */
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        /*user.put(KEY_NAME, pref.getString(KEY_NAME, null));*/

        // user email id
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
        user.put(KEY_PHONE, pref.getString(KEY_PHONE, null));
        user.put(KEY_PASS,pref.getString(KEY_PASS,null));
        // return user

        return user;
    }

    /**
     * Clear session details
     * */
    public void logoutUser() {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, LoginActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        deleteCache(_context);
        deleteData(_context);

        // Staring Login Activity
        _context.startActivity(i);
    }

    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn(){

        return pref.getBoolean(IS_LOGIN, false);
    }



    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();

            deleteDir(dir);
        } catch (Exception e) {}
    }

    public static void deleteData(Context context) {
        try {
            File cacheDirectory = context.getCacheDir();
            File applicationDirectory = new File(cacheDirectory.getParent());
            if (applicationDirectory.exists()) {
                String[] fileNames = applicationDirectory.list();
                for (String fileName : fileNames) {
                    //if (!fileName.equals("lib")) {
                    deleteDir(new File(applicationDirectory, fileName));
                    //}
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
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
