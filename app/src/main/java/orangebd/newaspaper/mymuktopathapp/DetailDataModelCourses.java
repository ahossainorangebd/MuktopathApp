package orangebd.newaspaper.mymuktopathapp;

import java.util.ArrayList;

public class DetailDataModelCourses
{
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


    //for parsing thumbnailImage;

    private ArrayList<DetailDataModelCoursesDetailContents> mArrayListContentDetails;


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

    public ArrayList<DetailDataModelCoursesDetailContents> getmArrayListContentDetails() {
        return mArrayListContentDetails;
    }

    public void setmArrayListContentDetails(ArrayList<DetailDataModelCoursesDetailContents> mArrayListContentDetails) {
        this.mArrayListContentDetails = mArrayListContentDetails;
    }
}
