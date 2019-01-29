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

    private ArrayList<DetailDataModelCourseDetailContent> mArrayListContentDetails;


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

    public ArrayList<DetailDataModelCourseDetailContent> getmArrayListContentDetails() {
        return mArrayListContentDetails;
    }

    public void setmArrayListContentDetails(ArrayList<DetailDataModelCourseDetailContent> mArrayListContentDetails) {
        this.mArrayListContentDetails = mArrayListContentDetails;
    }






    //TODO


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
}
