package orangebd.newaspaper.mymuktopathapp;

import java.util.ArrayList;

public class DetailDataModelCourses
{
    private int mExamNumbers;
    private int mAssignmentNumbers;
    private String mCourseObjective;

    private String mAdmissionStatus;
    private String mAverageRating;
    private String mCertificateAliasName;
    private String mCloneStatus;
    private String mCode;
    private String mCursesForStatus;
    private String mCourseAliasName;
    private String mCourseMotto;
    private String mCreatedAt;
    private String mDetails;
    private String mDuration;
    private String mEndDate;
    private String mEnrolmentApprovalStatus;
    private String mFeatured;
    private String mId;

    private String mLimit;
    private String mMigrationAllowe;
    private String mMigrationFee;
    private String mMigPaymentAmount;
    private String mMigPaStatus;
    private String mObjective;
    private String mPaymentPointAmount;
    private String mPaymentPointStatus;
    private String mPaymentStatus;
    private String mRegStartDate;
    private String mRegEndDate;
    private String mRequirement;
    private String mStartDate;
    private String mStatus;
    private String mTitle;
    private String mTotalEnroll;
    private String mUpdatedAt;

    // For file type (pdf/doc/....)

    private String mDoc;
    private String mPdf;
    private String mXcel;
    private String mCsv;


    // For parsing multiple Q array

    private String pulse;

    // for model of Lessons

    private String fixedLesson;
    private String idLesson;
    private String nameLesson;
    private String orderLessom;

    //for model of Syllebus direct strings

    private String studyModeSyllebus;

    //For model of Course Direct String

    private String course_codeCourse;
    private String course_levelCourse;
    private String idCourse;
    private String promovideoCourse;
    private String titleCourse;

    //for model of updated by direct strings

    private String education_statusUpdatedBy;
    private String emailUpdatedBy;
    private String idUpdatedBy;
    private String nameUpdatedBy;
    private String phoneUpdatedBy;
    private String UserInfoUpdatedBy;
    private String usernameUpdatedBy;

    //for model of updated by direct strings

    private String education_statusCreatedBy;
    private String emailCreatedBy;
    private String idCreatedBy;
    private String nameCreatedBy;
    private String phoneCreatedBy;
    private String UserInfoCreatedBy;
    private String usernameCreatedBy;

    //for model of owner by direct string

    private String updated_at_owner;
    private String institution_name_owner;
    private String id_owner;
    private String created_at_owner;

    //for parsing 2nd parent of grandfather

    private String firstSecondParent;
    private String lastSecondParent;
    private String nextSecondParent;
    private String prevSecondParent;

    private ArrayList<DetailDataModelCourses3rdGrandFather> m3rdGrandFather;

    //for parsing thumbnailImage;

    private ArrayList<DetailDataModelCoursesThumbnails> mArrayListThumbnails;


    private ArrayList<DetailDataModelCoursesMarks> mArrayListMarks;


    private ArrayList<DetailDataModelCoursesDetailContents> mUserInformationArrayList;

    private ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> mUnitDataArrayListContent;
    private ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> mUnitDataArrayListContent2;
    private ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> mUnitDataArrayListContent3;
    private ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> mUnitDataArrayListContent4;








    //for parsing thumbnailImage;

    /*private ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> mArrayListContentDetails;*/


    private ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> mArrayListContentDetails;
    private ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> mArrayListCourseUnits;
    private ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> mArrayListCourseLesson;
    private ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> mArrayListCourseQuizs;
    private ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> mArrayListCourseQuizsExam;

    //for ques list's options
    private ArrayList<ArrayList<ArrayList<DetailDataModelCoursesDetailContents>>> mArrayListCourseQuizOptions;
    private ArrayList<ArrayList<ArrayList<DetailDataModelCoursesDetailContents>>> mArrayListCoursePulseQuizOptions;
    private ArrayList<ArrayList<ArrayList<DetailDataModelCoursesDetailContents>>> mArrayListCourseVideoPulseMulti;

    private ArrayList<ArrayList<ArrayList<DetailDataModelCoursesDetailContents>>> mUnitDataArrayListArray;

    public String getmAdmissionStatus() {
        return mAdmissionStatus;
    }

    public void setmAdmissionStatus(String mAdmissionStatus) {
        this.mAdmissionStatus = mAdmissionStatus;
    }

    public String getmAverageRating() {
        return mAverageRating;
    }

    public void setmAverageRating(String mAverageRating) {
        this.mAverageRating = mAverageRating;
    }

    public String getmCertificateAliasName() {
        return mCertificateAliasName;
    }

    public void setmCertificateAliasName(String mCertificateAliasName) {
        this.mCertificateAliasName = mCertificateAliasName;
    }

    public String getmCloneStatus() {
        return mCloneStatus;
    }

    public void setmCloneStatus(String mCloneStatus) {
        this.mCloneStatus = mCloneStatus;
    }

    public String getmCode() {
        return mCode;
    }

    public void setmCode(String mCode) {
        this.mCode = mCode;
    }

    public String getmCursesForStatus() {
        return mCursesForStatus;
    }

    public void setmCursesForStatus(String mCursesForStatus) {
        this.mCursesForStatus = mCursesForStatus;
    }

    public String getmCourseAliasName() {
        return mCourseAliasName;
    }

    public void setmCourseAliasName(String mCourseAliasName) {
        this.mCourseAliasName = mCourseAliasName;
    }

    public String getmCourseMotto() {
        return mCourseMotto;
    }

    public void setmCourseMotto(String mCourseMotto) {
        this.mCourseMotto = mCourseMotto;
    }

    public String getmCreatedAt() {
        return mCreatedAt;
    }

    public void setmCreatedAt(String mCreatedAt) {
        this.mCreatedAt = mCreatedAt;
    }

    public String getmDetails() {
        return mDetails;
    }

    public void setmDetails(String mDetails) {
        this.mDetails = mDetails;
    }

    public String getmDuration() {
        return mDuration;
    }

    public void setmDuration(String mDuration) {
        this.mDuration = mDuration;
    }

    public String getmEndDate() {
        return mEndDate;
    }

    public void setmEndDate(String mEndDate) {
        this.mEndDate = mEndDate;
    }

    public String getmEnrolmentApprovalStatus() {
        return mEnrolmentApprovalStatus;
    }

    public void setmEnrolmentApprovalStatus(String mEnrolmentApprovalStatus) {
        this.mEnrolmentApprovalStatus = mEnrolmentApprovalStatus;
    }

    public String getmFeatured() {
        return mFeatured;
    }

    public void setmFeatured(String mFeatured) {
        this.mFeatured = mFeatured;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmLimit() {
        return mLimit;
    }

    public void setmLimit(String mLimit) {
        this.mLimit = mLimit;
    }

    public String getmMigrationAllowe() {
        return mMigrationAllowe;
    }

    public void setmMigrationAllowe(String mMigrationAllowe) {
        this.mMigrationAllowe = mMigrationAllowe;
    }

    public String getmMigrationFee() {
        return mMigrationFee;
    }

    public void setmMigrationFee(String mMigrationFee) {
        this.mMigrationFee = mMigrationFee;
    }

    public String getmMigPaymentAmount() {
        return mMigPaymentAmount;
    }

    public void setmMigPaymentAmount(String mMigPaymentAmount) {
        this.mMigPaymentAmount = mMigPaymentAmount;
    }

    public String getmMigPaStatus() {
        return mMigPaStatus;
    }

    public void setmMigPaStatus(String mMigPaStatus) {
        this.mMigPaStatus = mMigPaStatus;
    }

    public String getmObjective() {
        return mObjective;
    }

    public void setmObjective(String mObjective) {
        this.mObjective = mObjective;
    }

    public String getmPaymentPointAmount() {
        return mPaymentPointAmount;
    }

    public void setmPaymentPointAmount(String mPaymentPointAmount) {
        this.mPaymentPointAmount = mPaymentPointAmount;
    }

    public String getmPaymentPointStatus() {
        return mPaymentPointStatus;
    }

    public void setmPaymentPointStatus(String mPaymentPointStatus) {
        this.mPaymentPointStatus = mPaymentPointStatus;
    }

    public String getmPaymentStatus() {
        return mPaymentStatus;
    }

    public void setmPaymentStatus(String mPaymentStatus) {
        this.mPaymentStatus = mPaymentStatus;
    }

    public String getmRegStartDate() {
        return mRegStartDate;
    }

    public void setmRegStartDate(String mRegStartDate) {
        this.mRegStartDate = mRegStartDate;
    }

    public String getmRegEndDate() {
        return mRegEndDate;
    }

    public void setmRegEndDate(String mRegEndDate) {
        this.mRegEndDate = mRegEndDate;
    }

    public String getmRequirement() {
        return mRequirement;
    }

    public void setmRequirement(String mRequirement) {
        this.mRequirement = mRequirement;
    }

    public String getmStartDate() {
        return mStartDate;
    }

    public void setmStartDate(String mStartDate) {
        this.mStartDate = mStartDate;
    }

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmTotalEnroll() {
        return mTotalEnroll;
    }

    public void setmTotalEnroll(String mTotalEnroll) {
        this.mTotalEnroll = mTotalEnroll;
    }

    public String getmUpdatedAt() {
        return mUpdatedAt;
    }

    public void setmUpdatedAt(String mUpdatedAt) {
        this.mUpdatedAt = mUpdatedAt;
    }

    public String getmDoc() {
        return mDoc;
    }

    public void setmDoc(String mDoc) {
        this.mDoc = mDoc;
    }

    public String getmPdf() {
        return mPdf;
    }

    public void setmPdf(String mPdf) {
        this.mPdf = mPdf;
    }

    public String getmXcel() {
        return mXcel;
    }

    public void setmXcel(String mXcel) {
        this.mXcel = mXcel;
    }

    public String getmCsv() {
        return mCsv;
    }

    public void setmCsv(String mCsv) {
        this.mCsv = mCsv;
    }

    public String getPulse() {
        return pulse;
    }

    public void setPulse(String pulse) {
        this.pulse = pulse;
    }

    public String getFixedLesson() {
        return fixedLesson;
    }

    public void setFixedLesson(String fixedLesson) {
        this.fixedLesson = fixedLesson;
    }

    public String getIdLesson() {
        return idLesson;
    }

    public void setIdLesson(String idLesson) {
        this.idLesson = idLesson;
    }

    public String getNameLesson() {
        return nameLesson;
    }

    public void setNameLesson(String nameLesson) {
        this.nameLesson = nameLesson;
    }

    public String getOrderLessom() {
        return orderLessom;
    }

    public void setOrderLessom(String orderLessom) {
        this.orderLessom = orderLessom;
    }

    public String getStudyModeSyllebus() {
        return studyModeSyllebus;
    }

    public void setStudyModeSyllebus(String studyModeSyllebus) {
        this.studyModeSyllebus = studyModeSyllebus;
    }

    public String getCourse_codeCourse() {
        return course_codeCourse;
    }

    public void setCourse_codeCourse(String course_codeCourse) {
        this.course_codeCourse = course_codeCourse;
    }

    public String getCourse_levelCourse() {
        return course_levelCourse;
    }

    public void setCourse_levelCourse(String course_levelCourse) {
        this.course_levelCourse = course_levelCourse;
    }

    public String getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(String idCourse) {
        this.idCourse = idCourse;
    }

    public String getPromovideoCourse() {
        return promovideoCourse;
    }

    public void setPromovideoCourse(String promovideoCourse) {
        this.promovideoCourse = promovideoCourse;
    }

    public String getTitleCourse() {
        return titleCourse;
    }

    public void setTitleCourse(String titleCourse) {
        this.titleCourse = titleCourse;
    }

    public String getEducation_statusUpdatedBy() {
        return education_statusUpdatedBy;
    }

    public void setEducation_statusUpdatedBy(String education_statusUpdatedBy) {
        this.education_statusUpdatedBy = education_statusUpdatedBy;
    }

    public String getEmailUpdatedBy() {
        return emailUpdatedBy;
    }

    public void setEmailUpdatedBy(String emailUpdatedBy) {
        this.emailUpdatedBy = emailUpdatedBy;
    }

    public String getIdUpdatedBy() {
        return idUpdatedBy;
    }

    public void setIdUpdatedBy(String idUpdatedBy) {
        this.idUpdatedBy = idUpdatedBy;
    }

    public String getNameUpdatedBy() {
        return nameUpdatedBy;
    }

    public void setNameUpdatedBy(String nameUpdatedBy) {
        this.nameUpdatedBy = nameUpdatedBy;
    }

    public String getPhoneUpdatedBy() {
        return phoneUpdatedBy;
    }

    public void setPhoneUpdatedBy(String phoneUpdatedBy) {
        this.phoneUpdatedBy = phoneUpdatedBy;
    }

    public String getUserInfoUpdatedBy() {
        return UserInfoUpdatedBy;
    }

    public void setUserInfoUpdatedBy(String userInfoUpdatedBy) {
        UserInfoUpdatedBy = userInfoUpdatedBy;
    }

    public String getUsernameUpdatedBy() {
        return usernameUpdatedBy;
    }

    public void setUsernameUpdatedBy(String usernameUpdatedBy) {
        this.usernameUpdatedBy = usernameUpdatedBy;
    }

    public String getEducation_statusCreatedBy() {
        return education_statusCreatedBy;
    }

    public void setEducation_statusCreatedBy(String education_statusCreatedBy) {
        this.education_statusCreatedBy = education_statusCreatedBy;
    }

    public String getEmailCreatedBy() {
        return emailCreatedBy;
    }

    public void setEmailCreatedBy(String emailCreatedBy) {
        this.emailCreatedBy = emailCreatedBy;
    }

    public String getIdCreatedBy() {
        return idCreatedBy;
    }

    public void setIdCreatedBy(String idCreatedBy) {
        this.idCreatedBy = idCreatedBy;
    }

    public String getNameCreatedBy() {
        return nameCreatedBy;
    }

    public void setNameCreatedBy(String nameCreatedBy) {
        this.nameCreatedBy = nameCreatedBy;
    }

    public String getPhoneCreatedBy() {
        return phoneCreatedBy;
    }

    public void setPhoneCreatedBy(String phoneCreatedBy) {
        this.phoneCreatedBy = phoneCreatedBy;
    }

    public String getUserInfoCreatedBy() {
        return UserInfoCreatedBy;
    }

    public void setUserInfoCreatedBy(String userInfoCreatedBy) {
        UserInfoCreatedBy = userInfoCreatedBy;
    }

    public String getUsernameCreatedBy() {
        return usernameCreatedBy;
    }

    public void setUsernameCreatedBy(String usernameCreatedBy) {
        this.usernameCreatedBy = usernameCreatedBy;
    }

    public String getUpdated_at_owner() {
        return updated_at_owner;
    }

    public void setUpdated_at_owner(String updated_at_owner) {
        this.updated_at_owner = updated_at_owner;
    }

    public String getInstitution_name_owner() {
        return institution_name_owner;
    }

    public void setInstitution_name_owner(String institution_name_owner) {
        this.institution_name_owner = institution_name_owner;
    }

    public String getId_owner() {
        return id_owner;
    }

    public void setId_owner(String id_owner) {
        this.id_owner = id_owner;
    }

    public String getCreated_at_owner() {
        return created_at_owner;
    }

    public void setCreated_at_owner(String created_at_owner) {
        this.created_at_owner = created_at_owner;
    }

    public String getFirstSecondParent() {
        return firstSecondParent;
    }

    public void setFirstSecondParent(String firstSecondParent) {
        this.firstSecondParent = firstSecondParent;
    }

    public String getLastSecondParent() {
        return lastSecondParent;
    }

    public void setLastSecondParent(String lastSecondParent) {
        this.lastSecondParent = lastSecondParent;
    }

    public String getNextSecondParent() {
        return nextSecondParent;
    }

    public void setNextSecondParent(String nextSecondParent) {
        this.nextSecondParent = nextSecondParent;
    }

    public String getPrevSecondParent() {
        return prevSecondParent;
    }

    public void setPrevSecondParent(String prevSecondParent) {
        this.prevSecondParent = prevSecondParent;
    }

    public ArrayList<DetailDataModelCoursesThumbnails> getmArrayListThumbnails() {
        return mArrayListThumbnails;
    }

    public void setmArrayListThumbnails(ArrayList<DetailDataModelCoursesThumbnails> mArrayListThumbnails) {
        this.mArrayListThumbnails = mArrayListThumbnails;
    }

    public ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> getmArrayListContentDetails() {
        return mArrayListContentDetails;
    }

    public void setmArrayListContentDetails(ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> mArrayListContentDetails) {
        this.mArrayListContentDetails = mArrayListContentDetails;
    }

    /*public ArrayList<DetailDataModelCoursesDetailContents> getmArrayListContentDetails() {
        return mArrayListContentDetails;
    }

    public void setmArrayListContentDetails(ArrayList<DetailDataModelCoursesDetailContents> mArrayListContentDetails) {
        this.mArrayListContentDetails = mArrayListContentDetails;
    }*/





    //TODO
//    for users

    private String mSuccess;

    //
    private String mUserDataId;
    private String mName;

    private String mUserName;
    private String mUserPhone;
    private String mUserEmail;
    private String mUserDataStatus;
    private String mUserPassword;

    private String mLoginTime;
    private String mLoginIpAddress;
    private String mLoginStatus;
    private String mCompletenesss;
    private String mTotalEnrollment;
    private String mCourseCompleted;
    private String mLastLoginTime;
    private String mLastLoginIpAddress;
    private String mToken;

    // for own institution

    private String mInstId;
    private String mInstAddress;
    private String mInstContacts;
    private String mInstContactPerson;
    private String mInstcontactPersonEmail;
    private String mInstContactPersonMobile;
    private String mInstCreatedAt;
    private String mInstCreatedBy;
    private String mInstCcredit;
    private String mInstDeletedAt;
    private String mInstEmail;
    private String mInstGoogleLocation;
    private String mInstHeading;
    private String mInitial;
    private String mInstName;
    private String mInstType;
    private String mInstLegalDocFile;
    private String mInstMetaData;
    private String instphone;
    private String instsocial;
    private String inststatus;
    private String instsubscription;
    private String insttype;
    private String instupdated_at;
    private String instupdated_by;
    private String instusername;
    private String instuser_id;
    private String instwebsite;



    //Login API course name

    private String mUserDataAdmissionStatus;
    private String mUserDataAverageRating;
    private String mUserDataCertificateAliasName;
    private String mUserDataCloneStatus;
    private String mUserDataCode;
    private String mUserDataCursesForStatus;
    private String mUserDataCourseAliasName;
    private String mUserDataCourseMotto;
    private String mUserDataCreatedAt;
    private String mUserDataDetails;
    private String mUserDataDuration;
    private String mUserDataEndDate;
    private String mUserDataEnrolmentApprovalStatus;
    private String mUserDataFeatured;
    private String mCourseId;


    public String getmSuccess() {
        return mSuccess;
    }

    public void setmSuccess(String mSuccess) {
        this.mSuccess = mSuccess;
    }



    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

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

    public String getmUserPassword() {
        return mUserPassword;
    }

    public void setmUserPassword(String mUserPassword) {
        this.mUserPassword = mUserPassword;
    }

    public String getmLoginTime() {
        return mLoginTime;
    }

    public void setmLoginTime(String mLoginTime) {
        this.mLoginTime = mLoginTime;
    }

    public String getmLoginIpAddress() {
        return mLoginIpAddress;
    }

    public void setmLoginIpAddress(String mLoginIpAddress) {
        this.mLoginIpAddress = mLoginIpAddress;
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

    public String getmCourseCompleted() {
        return mCourseCompleted;
    }

    public void setmCourseCompleted(String mCourseCompleted) {
        this.mCourseCompleted = mCourseCompleted;
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

    public String getmInstId() {
        return mInstId;
    }

    public void setmInstId(String mInstId) {
        this.mInstId = mInstId;
    }

    public String getmInstAddress() {
        return mInstAddress;
    }

    public void setmInstAddress(String mInstAddress) {
        this.mInstAddress = mInstAddress;
    }

    public String getmInstContacts() {
        return mInstContacts;
    }

    public void setmInstContacts(String mInstContacts) {
        this.mInstContacts = mInstContacts;
    }

    public String getmInstContactPerson() {
        return mInstContactPerson;
    }

    public void setmInstContactPerson(String mInstContactPerson) {
        this.mInstContactPerson = mInstContactPerson;
    }

    public String getmInstcontactPersonEmail() {
        return mInstcontactPersonEmail;
    }

    public void setmInstcontactPersonEmail(String mInstcontactPersonEmail) {
        this.mInstcontactPersonEmail = mInstcontactPersonEmail;
    }

    public String getmInstContactPersonMobile() {
        return mInstContactPersonMobile;
    }

    public void setmInstContactPersonMobile(String mInstContactPersonMobile) {
        this.mInstContactPersonMobile = mInstContactPersonMobile;
    }

    public String getmInstCreatedAt() {
        return mInstCreatedAt;
    }

    public void setmInstCreatedAt(String mInstCreatedAt) {
        this.mInstCreatedAt = mInstCreatedAt;
    }

    public String getmInstCreatedBy() {
        return mInstCreatedBy;
    }

    public void setmInstCreatedBy(String mInstCreatedBy) {
        this.mInstCreatedBy = mInstCreatedBy;
    }

    public String getmInstCcredit() {
        return mInstCcredit;
    }

    public void setmInstCcredit(String mInstCcredit) {
        this.mInstCcredit = mInstCcredit;
    }

    public String getmInstDeletedAt() {
        return mInstDeletedAt;
    }

    public void setmInstDeletedAt(String mInstDeletedAt) {
        this.mInstDeletedAt = mInstDeletedAt;
    }

    public String getmInstEmail() {
        return mInstEmail;
    }

    public void setmInstEmail(String mInstEmail) {
        this.mInstEmail = mInstEmail;
    }

    public String getmInstGoogleLocation() {
        return mInstGoogleLocation;
    }

    public void setmInstGoogleLocation(String mInstGoogleLocation) {
        this.mInstGoogleLocation = mInstGoogleLocation;
    }

    public String getmInstHeading() {
        return mInstHeading;
    }

    public void setmInstHeading(String mInstHeading) {
        this.mInstHeading = mInstHeading;
    }

    public String getmInitial() {
        return mInitial;
    }

    public void setmInitial(String mInitial) {
        this.mInitial = mInitial;
    }

    public String getmInstName() {
        return mInstName;
    }

    public void setmInstName(String mInstName) {
        this.mInstName = mInstName;
    }

    public String getmInstType() {
        return mInstType;
    }

    public void setmInstType(String mInstType) {
        this.mInstType = mInstType;
    }

    public String getmInstLegalDocFile() {
        return mInstLegalDocFile;
    }

    public void setmInstLegalDocFile(String mInstLegalDocFile) {
        this.mInstLegalDocFile = mInstLegalDocFile;
    }

    public String getmInstMetaData() {
        return mInstMetaData;
    }

    public void setmInstMetaData(String mInstMetaData) {
        this.mInstMetaData = mInstMetaData;
    }

    public String getInstphone() {
        return instphone;
    }

    public void setInstphone(String instphone) {
        this.instphone = instphone;
    }

    public String getInstsocial() {
        return instsocial;
    }

    public void setInstsocial(String instsocial) {
        this.instsocial = instsocial;
    }

    public String getInststatus() {
        return inststatus;
    }

    public void setInststatus(String inststatus) {
        this.inststatus = inststatus;
    }

    public String getInstsubscription() {
        return instsubscription;
    }

    public void setInstsubscription(String instsubscription) {
        this.instsubscription = instsubscription;
    }

    public String getInsttype() {
        return insttype;
    }

    public void setInsttype(String insttype) {
        this.insttype = insttype;
    }

    public String getInstupdated_at() {
        return instupdated_at;
    }

    public void setInstupdated_at(String instupdated_at) {
        this.instupdated_at = instupdated_at;
    }

    public String getInstupdated_by() {
        return instupdated_by;
    }

    public void setInstupdated_by(String instupdated_by) {
        this.instupdated_by = instupdated_by;
    }

    public String getInstusername() {
        return instusername;
    }

    public void setInstusername(String instusername) {
        this.instusername = instusername;
    }

    public String getInstuser_id() {
        return instuser_id;
    }

    public void setInstuser_id(String instuser_id) {
        this.instuser_id = instuser_id;
    }

    public String getInstwebsite() {
        return instwebsite;
    }

    public void setInstwebsite(String instwebsite) {
        this.instwebsite = instwebsite;
    }

    public String getmCourseId() {
        return mCourseId;
    }

    public void setmCourseId(String mCourseId) {
        this.mCourseId = mCourseId;
    }

    public int getmExamNumbers() {
        return mExamNumbers;
    }

    public void setmExamNumbers(int mExamNumbers) {
        this.mExamNumbers = mExamNumbers;
    }

    public int getmAssignmentNumbers() {
        return mAssignmentNumbers;
    }

    public void setmAssignmentNumbers(int mAssignmentNumbers) {
        this.mAssignmentNumbers = mAssignmentNumbers;
    }


    public ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> getmArrayListCourseUnits() {
        return mArrayListCourseUnits;
    }

    public void setmArrayListCourseUnits(ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> mArrayListCourseUnits) {
        this.mArrayListCourseUnits = mArrayListCourseUnits;
    }

    public ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> getmArrayListCourseQuizs() {
        return mArrayListCourseQuizs;
    }

    public void setmArrayListCourseQuizs(ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> mArrayListCourseQuizs) {
        this.mArrayListCourseQuizs = mArrayListCourseQuizs;
    }

    public ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> getmUnitDataArrayListContent() {
        return mUnitDataArrayListContent;
    }

    public void setmUnitDataArrayListContent(ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> mUnitDataArrayListContent) {
        this.mUnitDataArrayListContent = mUnitDataArrayListContent;
    }

    public ArrayList<ArrayList<ArrayList<DetailDataModelCoursesDetailContents>>> getmUnitDataArrayListArray() {
        return mUnitDataArrayListArray;
    }

    public void setmUnitDataArrayListArray(ArrayList<ArrayList<ArrayList<DetailDataModelCoursesDetailContents>>> mUnitDataArrayListArray) {
        this.mUnitDataArrayListArray = mUnitDataArrayListArray;
    }

    public ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> getmUnitDataArrayListContent2() {
        return mUnitDataArrayListContent2;
    }

    public void setmUnitDataArrayListContent2(ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> mUnitDataArrayListContent2) {
        this.mUnitDataArrayListContent2 = mUnitDataArrayListContent2;
    }

    public ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> getmUnitDataArrayListContent3() {
        return mUnitDataArrayListContent3;
    }

    public void setmUnitDataArrayListContent3(ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> mUnitDataArrayListContent3) {
        this.mUnitDataArrayListContent3 = mUnitDataArrayListContent3;
    }

    public ArrayList<ArrayList<ArrayList<DetailDataModelCoursesDetailContents>>> getmArrayListCourseQuizOptions() {
        return mArrayListCourseQuizOptions;
    }

    public void setmArrayListCourseQuizOptions(ArrayList<ArrayList<ArrayList<DetailDataModelCoursesDetailContents>>> mArrayListCourseQuizOptions) {
        this.mArrayListCourseQuizOptions = mArrayListCourseQuizOptions;
    }

    public ArrayList<ArrayList<ArrayList<DetailDataModelCoursesDetailContents>>> getmArrayListCourseVideoPulseMulti() {
        return mArrayListCourseVideoPulseMulti;
    }

    public void setmArrayListCourseVideoPulseMulti(ArrayList<ArrayList<ArrayList<DetailDataModelCoursesDetailContents>>> mArrayListCourseVideoPulseMulti) {
        this.mArrayListCourseVideoPulseMulti = mArrayListCourseVideoPulseMulti;
    }

    public ArrayList<ArrayList<ArrayList<DetailDataModelCoursesDetailContents>>> getmArrayListCoursePulseQuizOptions() {
        return mArrayListCoursePulseQuizOptions;
    }

    public void setmArrayListCoursePulseQuizOptions(ArrayList<ArrayList<ArrayList<DetailDataModelCoursesDetailContents>>> mArrayListCoursePulseQuizOptions) {
        this.mArrayListCoursePulseQuizOptions = mArrayListCoursePulseQuizOptions;
    }

    public ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> getmUnitDataArrayListContent4() {
        return mUnitDataArrayListContent4;
    }

    public void setmUnitDataArrayListContent4(ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> mUnitDataArrayListContent4) {
        this.mUnitDataArrayListContent4 = mUnitDataArrayListContent4;
    }


    public ArrayList<DetailDataModelCoursesDetailContents> getmUserInformationArrayList() {
        return mUserInformationArrayList;
    }

    public void setmUserInformationArrayList(ArrayList<DetailDataModelCoursesDetailContents> mUserInformationArrayList) {
        this.mUserInformationArrayList = mUserInformationArrayList;
    }

    public ArrayList<DetailDataModelCoursesMarks> getmArrayListMarks() {
        return mArrayListMarks;
    }

    public void setmArrayListMarks(ArrayList<DetailDataModelCoursesMarks> mArrayListMarks) {
        this.mArrayListMarks = mArrayListMarks;
    }

    public ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> getmArrayListCourseQuizsExam() {
        return mArrayListCourseQuizsExam;
    }

    public void setmArrayListCourseQuizsExam(ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> mArrayListCourseQuizsExam) {
        this.mArrayListCourseQuizsExam = mArrayListCourseQuizsExam;
    }

    public ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> getmArrayListCourseLesson() {
        return mArrayListCourseLesson;
    }

    public void setmArrayListCourseLesson(ArrayList<ArrayList<DetailDataModelCoursesDetailContents>> mArrayListCourseLesson) {
        this.mArrayListCourseLesson = mArrayListCourseLesson;
    }


    private ArrayList<DetailDataModelCoursesDetailContents> mCourseEnrollArrayListId;

    public ArrayList<DetailDataModelCoursesDetailContents> getmCourseEnrollArrayListId() {
        return mCourseEnrollArrayListId;
    }

    public void setmCourseEnrollArrayListId(ArrayList<DetailDataModelCoursesDetailContents> mCourseEnrollArrayListId) {
        this.mCourseEnrollArrayListId = mCourseEnrollArrayListId;
    }

    // ec id
    private String mEcId;

    public String getmEcId() {
        return mEcId;
    }

    public void setmEcId(String mEcId) {
        this.mEcId = mEcId;
    }

    public String getmCourseObjective() {
        return mCourseObjective;
    }

    public void setmCourseObjective(String mCourseObjective) {
        this.mCourseObjective = mCourseObjective;
    }
}
