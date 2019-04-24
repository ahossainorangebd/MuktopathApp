package orangebd.newaspaper.mymuktopathapp;


import org.json.JSONArray;

import java.util.ArrayList;

public class GlobalVar {

    public static String getParentName;

    public static String gLastReadLessonTitle;

    public static String gPulseTitle;

    public static ArrayList<DetailDataModelCoursesDetailContents> gPulseAnswerArray;

    public static int gTotalQuizNumberThisCourse;
    public static int gTotalExamNumberThisCourse;

    public static ArrayList<ParentMenu> gParentMenue;

    public static String gListPosition;


    public static ArrayList<String> gRecommendedCategories;
    public static ArrayList<String> gRecommendedCategoriesEn;
    public static ArrayList<String> gRecommendedCategoriesId;

    public static ArrayList<DetailDataModelCourses> gRecommendedDetailList10;
    public static ArrayList<DetailDataModelCourses> gEnrolledInstitution;
    public static ArrayList<DetailDataModelCoursesDetailContents> thisFragmentContents;
    public static ArrayList<DetailDataModelCoursesDetailContents> thisFragmentQuizes;

    public static ArrayList<DetailDataModelCoursesDetailContents> gThisVideoPulses;

    public static ArrayList<DetailDataModelCoursesDetailContents> gThisVideoPulsesQs;

    public static ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> thisFragmentPulses;
    public static ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> thisFragmentPulseQs;

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
    public static String gProfileCompleteness;


    public static String gReplacingToken;
    public static String gReplacingTokenForAllCategories;
    public static String gUserType;

    public static String gBaseUrl="http://muktopaath.orangebd.com";
    public static String gBaseUrlForProfile="http://testadmin.muktopaath.orangebd.com";

    public static String gApiBaseUrl="http://testapi.muktopaath.orangebd.com";
   //TODO
    //TODO
    //public static String gApiBaseUrl="http://testapi.muktopaath.orangebd.com";

    public static int gNthCourse;

    public static String gUsersNumber;



    //MainActivity

    public static ArrayList<DetailDataModelCourses> gMainActivityCourseList;
    public static ArrayList<DetailDataModelCourses> gMainActivityInstitutionList;
    public static ArrayList<DetailDataModelCourses> allDataDetailList;

    public static int gEnrollCourseNumber;



    //for expandable list view for q and option
    public static  ArrayList<DetailDataModelCoursesDetailContents> gEnrollCourseQuestionList;


    public static  ArrayList<DetailDataModelAll> gAllCourseCategories;


    public static int gEnrolledCourseUnitSize;


    public static boolean gIsLogin;


    public static int gMultiMarkCount=0;
    public static int gPulseMultiMarkCount;



    public static String gPassMark;
    public static String gTotalGainedMark;

    public static String gEPassMark;
    public static String gETotalGainedMark;



    ///Course Detail Page Put Extra Info into GlobalVar

    public static String gCourseDetailCoverPhoto;
    public static String gCourseDetailTitle;
    public static String gCourseDetailOwnerName;
    public static int gCourseNumber;
    public static boolean isRedirectFromContentPage;

    //Course Content Detail page put extra info into GlobalVar

    public static String gDescriptionText;
    public static String gUserNumber;
    public static String gVideoCode;
    public static String gTimeStatus;

    // Unit & Lesson info
    public static String gUnitId;
    public static String gLessonId;


    // Ques List for exam

    public static ArrayList<String> answerArray;
    public static ArrayList<String> attendedQArray;

    // Ques List for quiz

    public static ArrayList<String> answerArrayQuiz;
    public static ArrayList<String> attendedQArrayQuiz;
    public static int gNthQuiz;
    public static boolean isRedirectFromQuizFragment1;

    //enroll course Id

    public static ArrayList<DetailDataModelCourses> gEnrollCourseId;


    // Course motto and objective

    public static String gCourseMotto;
    public static String gCourseObj;
    public static String gCourseDesc;


    //push notification

    public static boolean isDownloadOnWifyOnly;
    public static boolean isDeleteAutometically;

}
