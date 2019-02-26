package orangebd.newaspaper.mymuktopathapp;

public class DetailDataModelAll
{
    //First Token Model
    private String mAccessToken;
    private String mExpiresIn;
    private String mTokenType;

    //All Course Categories

    private String mCourseCategoryNameEn;
    private String mCourseCategoryNameBn;
    private String mCourseCategoryId;

    public String getmAccessToken() {
        return mAccessToken;
    }

    public void setmAccessToken(String mAccessToken) {
        this.mAccessToken = mAccessToken;
    }

    public String getmExpiresIn() {
        return mExpiresIn;
    }

    public void setmExpiresIn(String mExpiresIn) {
        this.mExpiresIn = mExpiresIn;
    }

    public String getmTokenType() {
        return mTokenType;
    }

    public void setmTokenType(String mTokenType) {
        this.mTokenType = mTokenType;
    }


    public String getmCourseCategoryNameEn() {
        return mCourseCategoryNameEn;
    }

    public void setmCourseCategoryNameEn(String mCourseCategoryNameEn) {
        this.mCourseCategoryNameEn = mCourseCategoryNameEn;
    }

    public String getmCourseCategoryNameBn() {
        return mCourseCategoryNameBn;
    }

    public void setmCourseCategoryNameBn(String mCourseCategoryNameBn) {
        this.mCourseCategoryNameBn = mCourseCategoryNameBn;
    }

    public String getmCourseCategoryId() {
        return mCourseCategoryId;
    }

    public void setmCourseCategoryId(String mCourseCategoryId) {
        this.mCourseCategoryId = mCourseCategoryId;
    }
}
