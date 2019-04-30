package orangebd.newaspaper.mymuktopathapp;

import org.json.JSONArray;

import java.util.ArrayList;

public class DetailDataModelCoursesDetailContents
{

    //replies and comments

    private String mCommenterNames;
    private String mCommentBody;

    private String mReplierNames;
    private String mReplyBody;

    //profile info arraylist

    private String mCourseCompleted;
    private String mId;
    private String mUserName;
    private String mUserEmail;
    private String mCompletenesss;
    private String mUserPhone;
    private String mLoginStatus;
    private String mStatus;
    private String mToken;
    private String mLoginIpAddress;
    private String mLastLoginTime;
    private String mLastLoginIpAddress;
    private String mTotalEnrollment;
    private String mName;


    public String getmUserName() {
        return mUserName;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public String getmUserPhone() {
        return mUserPhone;
    }

    public void setmUserPhone(String mUserPhone) {
        this.mUserPhone = mUserPhone;
    }

    public String getmUserEmail() {
        return mUserEmail;
    }

    public void setmUserEmail(String mUserEmail) {
        this.mUserEmail = mUserEmail;
    }

    public String getmLoginStatus() {
        return mLoginStatus;
    }

    public void setmLoginStatus(String mLoginStatus) {
        this.mLoginStatus = mLoginStatus;
    }

    public String getmCompletenesss() {
        return mCompletenesss;
    }

    public void setmCompletenesss(String mCompletenesss) {
        this.mCompletenesss = mCompletenesss;
    }

    public String getmTotalEnrollment() {
        return mTotalEnrollment;
    }

    public void setmTotalEnrollment(String mTotalEnrollment) {
        this.mTotalEnrollment = mTotalEnrollment;
    }

    public String getmLastLoginIpAddress() {
        return mLastLoginIpAddress;
    }

    public void setmLastLoginIpAddress(String mLastLoginIpAddress) {
        this.mLastLoginIpAddress = mLastLoginIpAddress;
    }

    public String getmLastLoginTime() {
        return mLastLoginTime;
    }

    public void setmLastLoginTime(String mLastLoginTime) {
        this.mLastLoginTime = mLastLoginTime;
    }

    public String getmToken() {
        return mToken;
    }

    public void setmToken(String mToken) {
        this.mToken = mToken;
    }

    public String getmLoginIpAddress() {
        return mLoginIpAddress;
    }

    public void setmLoginIpAddress(String mLoginIpAddress) {
        this.mLoginIpAddress = mLoginIpAddress;
    }


    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public String getmCourseCompleted() {
        return mCourseCompleted;
    }
    public void setmCourseCompleted(String mCourseCompleted) {
        this.mCourseCompleted = mCourseCompleted;
    }
    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    private JSONArray mQuesList;

    //Lesson elements


    private String IdLesson;
    private String NameLesson;
    private String OrderLessom;
    private String FixedLesson;

    //user info

    private String mUserProfilePhoto;
    private String mUserCoverPhoto;


    private ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> mUnitDataArrayListContent;


    private ArrayList<DetailDataModelCoursesDetailContents> mArrayListEnrolledCourseId;

    //TODO
    // For parsing the "data" of last object inside

    private String mAllowPreview;
    private String mAnsRand;
    private String mAttempt;
    private String mChooseVideoType;
    private String mContentType;
    private String mDesc;
    private String mDownloadable;
    private String mDurationAnother;
    private String mForward;
    private String mPeerLimit;
    private String mPeerReview;
    private String mPulse;
    private String mQuesRand;
    private String mQuiz;
    private String mTimeUnit;
    private String mTitleAnother;


    private String mQuizMarks;
    private String mQuizTime;

    private String mExamMarks;
    private String mExamTime;

    // For model of Quizes

    private String mQuizTitle;
    private String mQuizId;
    private String mSummeryDesc;

    // For model of pulse Quizes

    private String mpQuizTitle;

    // for quiz options

    private String mOptionBody;
    private String mOptionAnswer;


    // for pulse quiz options

    private String mpOptionBody;
    private String mpOptionAnswer;

    //for video ques pulse

    private String mVideoQuesPulse;



    //pulse of video multi
    private String mPulseOfVideoMulti;
    private int mPulseOfVideoMultiId;

    public String getmPulseOfVideoMulti() {
        return mPulseOfVideoMulti;
    }

    public void setmPulseOfVideoMulti(String mPulseOfVideoMulti) {
        this.mPulseOfVideoMulti = mPulseOfVideoMulti;
    }



    public String getmOptionBody() {
        return mOptionBody;
    }


    public void setmOptionBody(String mOptionBody) {
        this.mOptionBody = mOptionBody;
    }

    // for model of Units

    private String unitNames;
    private String unitOrders;
    private String unitID;

    public String getUnitNames() {
        return unitNames;
    }

    public void setUnitNames(String unitNames) {
        this.unitNames = unitNames;
    }

    public String getUnitOrders() {
        return unitOrders;
    }

    public void setUnitOrders(String unitOrders) {
        this.unitOrders = unitOrders;
    }

    //Dor parsing the "content" of last object inside

    private String paid;
    private String price;
    private String shareable;
    private String status_content;
    private String size;
    private String tags;
    private String title_content;
    private String type_content;
    private String updated_at_content;
    private String updated_by_content;
    private String cat_id;
    private String content_id;
    private String copy_protect;
    private String cover_thumb_img;
    private String created_by_content;
    private String deleted_at_content;
    private String description_content;
    private String file_encode_path;

    private ArrayList<DetailDataModelCourses3rdGrandFather> mVideoMultiPulse;

    private String file_name;
    private String id_content;
    private String license;
    private String owner_id;
    private String created_at_content;

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getShareable() {
        return shareable;
    }

    public void setShareable(String shareable) {
        this.shareable = shareable;
    }

    public String getStatus_content() {
        return status_content;
    }

    public void setStatus_content(String status_content) {
        this.status_content = status_content;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getTitle_content() {
        return title_content;
    }

    public void setTitle_content(String title_content) {
        this.title_content = title_content;
    }

    public String getType_content() {
        return type_content;
    }

    public void setType_content(String type_content) {
        this.type_content = type_content;
    }

    public String getUpdated_at_content() {
        return updated_at_content;
    }

    public void setUpdated_at_content(String updated_at_content) {
        this.updated_at_content = updated_at_content;
    }

    public String getUpdated_by_content() {
        return updated_by_content;
    }

    public void setUpdated_by_content(String updated_by_content) {
        this.updated_by_content = updated_by_content;
    }

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public String getContent_id() {
        return content_id;
    }

    public void setContent_id(String content_id) {
        this.content_id = content_id;
    }

    public String getCopy_protect() {
        return copy_protect;
    }

    public void setCopy_protect(String copy_protect) {
        this.copy_protect = copy_protect;
    }

    public String getCover_thumb_img() {
        return cover_thumb_img;
    }

    public void setCover_thumb_img(String cover_thumb_img) {
        this.cover_thumb_img = cover_thumb_img;
    }

    public String getCreated_by_content() {
        return created_by_content;
    }

    public void setCreated_by_content(String created_by_content) {
        this.created_by_content = created_by_content;
    }

    public String getDeleted_at_content() {
        return deleted_at_content;
    }

    public void setDeleted_at_content(String deleted_at_content) {
        this.deleted_at_content = deleted_at_content;
    }

    public String getDescription_content() {
        return description_content;
    }

    public void setDescription_content(String description_content) {
        this.description_content = description_content;
    }

    public String getFile_encode_path() {
        return file_encode_path;
    }

    public void setFile_encode_path(String file_encode_path) {
        this.file_encode_path = file_encode_path;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getId_content() {
        return id_content;
    }

    public void setId_content(String id_content) {
        this.id_content = id_content;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(String owner_id) {
        this.owner_id = owner_id;
    }

    public String getCreated_at_content() {
        return created_at_content;
    }

    public void setCreated_at_content(String created_at_content) {
        this.created_at_content = created_at_content;
    }

    public String getmQuizTitle() {
        return mQuizTitle;
    }

    public void setmQuizTitle(String mQuizTitle) {
        this.mQuizTitle = mQuizTitle;
    }



    public String getmAllowPreview() {
        return mAllowPreview;
    }

    public void setmAllowPreview(String mAllowPreview) {
        this.mAllowPreview = mAllowPreview;
    }

    public String getmAnsRand() {
        return mAnsRand;
    }

    public void setmAnsRand(String mAnsRand) {
        this.mAnsRand = mAnsRand;
    }

    public String getmAttempt() {
        return mAttempt;
    }

    public void setmAttempt(String mAttempt) {
        this.mAttempt = mAttempt;
    }

    public String getmChooseVideoType() {
        return mChooseVideoType;
    }

    public void setmChooseVideoType(String mChooseVideoType) {
        this.mChooseVideoType = mChooseVideoType;
    }

    public String getmContentType() {
        return mContentType;
    }

    public void setmContentType(String mContentType) {
        this.mContentType = mContentType;
    }

    public String getmDesc() {
        return mDesc;
    }

    public void setmDesc(String mDesc) {
        this.mDesc = mDesc;
    }

    public String getmDownloadable() {
        return mDownloadable;
    }

    public void setmDownloadable(String mDownloadable) {
        this.mDownloadable = mDownloadable;
    }

    public String getmDurationAnother() {
        return mDurationAnother;
    }

    public void setmDurationAnother(String mDurationAnother) {
        this.mDurationAnother = mDurationAnother;
    }

    public String getmForward() {
        return mForward;
    }

    public void setmForward(String mForward) {
        this.mForward = mForward;
    }

    public String getmPeerLimit() {
        return mPeerLimit;
    }

    public void setmPeerLimit(String mPeerLimit) {
        this.mPeerLimit = mPeerLimit;
    }

    public String getmPeerReview() {
        return mPeerReview;
    }

    public void setmPeerReview(String mPeerReview) {
        this.mPeerReview = mPeerReview;
    }

    public String getmPulse() {
        return mPulse;
    }

    public void setmPulse(String mPulse) {
        this.mPulse = mPulse;
    }

    public String getmQuesRand() {
        return mQuesRand;
    }

    public void setmQuesRand(String mQuesRand) {
        this.mQuesRand = mQuesRand;
    }

    public String getmQuiz() {
        return mQuiz;
    }

    public void setmQuiz(String mQuiz) {
        this.mQuiz = mQuiz;
    }

    public String getmTimeUnit() {
        return mTimeUnit;
    }

    public void setmTimeUnit(String mTimeUnit) {
        this.mTimeUnit = mTimeUnit;
    }

    public String getmTitleAnother() {
        return mTitleAnother;
    }

    public void setmTitleAnother(String mTitleAnother) {
        this.mTitleAnother = mTitleAnother;
    }

    public String getmOptionAnswer() {
        return mOptionAnswer;
    }

    public void setmOptionAnswer(String mOptionAnswer) {
        this.mOptionAnswer = mOptionAnswer;
    }

    public String getmVideoQuesPulse() {
        return mVideoQuesPulse;
    }

    public void setmVideoQuesPulse(String mVideoQuesPulse) {
        this.mVideoQuesPulse = mVideoQuesPulse;
    }

    public ArrayList<DetailDataModelCourses3rdGrandFather> getmVideoMultiPulse() {
        return mVideoMultiPulse;
    }

    public void setmVideoMultiPulse(ArrayList<DetailDataModelCourses3rdGrandFather> mVideoMultiPulse) {
        this.mVideoMultiPulse = mVideoMultiPulse;
    }

    public String getMpQuizTitle() {
        return mpQuizTitle;
    }

    public void setMpQuizTitle(String mpQuizTitle) {
        this.mpQuizTitle = mpQuizTitle;
    }

    public String getMpOptionBody() {
        return mpOptionBody;
    }

    public void setMpOptionBody(String mpOptionBody) {
        this.mpOptionBody = mpOptionBody;
    }

    public String getMpOptionAnswer() {
        return mpOptionAnswer;
    }

    public void setMpOptionAnswer(String mpOptionAnswer) {
        this.mpOptionAnswer = mpOptionAnswer;
    }

    public int getmPulseOfVideoMultiId() {
        return mPulseOfVideoMultiId;
    }

    public void setmPulseOfVideoMultiId(int mPulseOfVideoMultiId) {
        this.mPulseOfVideoMultiId = mPulseOfVideoMultiId;
    }

    public String getmQuizMarks() {
        return mQuizMarks;
    }

    public void setmQuizMarks(String mQuizMarks) {
        this.mQuizMarks = mQuizMarks;
    }

    public String getmQuizTime() {
        return mQuizTime;
    }

    public void setmQuizTime(String mQuizTime) {
        this.mQuizTime = mQuizTime;
    }



    public String getmUserProfilePhoto() {
        return mUserProfilePhoto;
    }

    public void setmUserProfilePhoto(String mUserProfilePhoto) {
        this.mUserProfilePhoto = mUserProfilePhoto;
    }

    public String getmUserCoverPhoto() {
        return mUserCoverPhoto;
    }

    public void setmUserCoverPhoto(String mUserCoverPhoto) {
        this.mUserCoverPhoto = mUserCoverPhoto;
    }

    public String getmSummeryDesc() {
        return mSummeryDesc;
    }

    public void setmSummeryDesc(String mSummeryDesc) {
        this.mSummeryDesc = mSummeryDesc;
    }

    public String getmExamMarks() {
        return mExamMarks;
    }

    public void setmExamMarks(String mExamMarks) {
        this.mExamMarks = mExamMarks;
    }

    public String getmExamTime() {
        return mExamTime;
    }

    public void setmExamTime(String mExamTime) {
        this.mExamTime = mExamTime;
    }

    public String getUnitID() {
        return unitID;
    }

    public void setUnitID(String unitID) {
        this.unitID = unitID;
    }

    public String getIdLesson() {
        return IdLesson;
    }

    public void setIdLesson(String idLesson) {
        IdLesson = idLesson;
    }

    public String getNameLesson() {
        return NameLesson;
    }

    public void setNameLesson(String nameLesson) {
        NameLesson = nameLesson;
    }

    public String getOrderLessom() {
        return OrderLessom;
    }

    public void setOrderLessom(String orderLessom) {
        OrderLessom = orderLessom;
    }

    public String getFixedLesson() {
        return FixedLesson;
    }

    public void setFixedLesson(String fixedLesson) {
        FixedLesson = fixedLesson;
    }

    public JSONArray getmQuesList() {
        return mQuesList;
    }

    public void setmQuesList(JSONArray mQuesList) {
        this.mQuesList = mQuesList;
    }

    public String getmQuizId() {
        return mQuizId;
    }

    public void setmQuizId(String mQuizId) {
        this.mQuizId = mQuizId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmReplierNames() {
        return mReplierNames;
    }

    public void setmReplierNames(String mReplierNames) {
        this.mReplierNames = mReplierNames;
    }

    public String getmCommenterNames() {
        return mCommenterNames;
    }

    public void setmCommenterNames(String mCommenterNames) {
        this.mCommenterNames = mCommenterNames;
    }

    public String getmCommentBody() {
        return mCommentBody;
    }

    public void setmCommentBody(String mCommentBody) {
        this.mCommentBody = mCommentBody;
    }

    public String getmReplyBody() {
        return mReplyBody;
    }

    public void setmReplyBody(String mReplyBody) {
        this.mReplyBody = mReplyBody;
    }




    /*public String getmOptionBody() {
        return mOptionBody;
    }





    public void setmOptionBody(String mOptionBody) {
        this.mOptionBody = mOptionBody;
    }*/



}
