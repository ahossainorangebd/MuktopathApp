package orangebd.newaspaper.mymuktopathapp;

import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class GlobalVar {

    public static String getParentName;

    public static ArrayList<ParentMenu> gParentMenue;


    public static ArrayList<String> gMenuArrayList;
    public static ArrayList<DetailDataModelCourses> gRecommendedDetailList10;
    public static ArrayList<DetailDataModelCourses> gEnrolledInstitution;
    public static ArrayList<DetailDataModelCoursesDetailContents> thisFragmentContents;
    public static ArrayList<DetailDataModelCoursesDetailContents> thisFragmentQuizes;

    public static ArrayList<DetailDataModelCoursesThumbnails> gEnrollCoursePhotoList;

    public static String gParentMenuURL;

    public static String gParentChildMenuURL;

    public static boolean isLeadBtnClick;

    public static boolean isExpandForMenu;


    public static Object[] gChildArrayOfContent;
    public static Object[] gChildArrayOfContentMyPage;

    //arrayList of Course Content
    public static ArrayList<DetailDataModelCourses> courseContentDetailList;
    public static ArrayList<DetailDataModelCourses> gEnrollCourseList;


    //
    public static String gTokenUrl;


    public static ArrayList<DetailDataModelAll> getTokenArray;


    //user info

    public static String gName;
    public static String gMobile;
    public static String gEmail;
    public static String gReplacingToken;
    public static String gReplacingTokenForAllCategories;
    public static String gUserType;

    public static String gBaseUrl="http://muktopaath.orangebd.com";

    public static int gNthCourse;

    public static String gUsersNumber;



    //MainActivity

    public static ArrayList<DetailDataModelCourses> gMainActivityCourseList;
    public static ArrayList<DetailDataModelCourses> gMainActivityInstitutionList;
    public static ArrayList<DetailDataModelCourses> allDataDetailList;

    public static int gEnrollCourseNumber;
}
