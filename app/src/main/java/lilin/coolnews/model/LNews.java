package lilin.coolnews.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lilin on 2016/8/11.
 * <p>
 * 新闻类
 */
public class LNews implements Serializable {

    private String mTitle;
    private String mDesc;
    private String mDate;
    private String mChannelName;
    private String mChannelId;
    private String mNewsLink;
    private String mSource;
    private String mFristImg;
    private int mFristImgWidth,mFristImgHeight;
    private List<LContent> mContents = new ArrayList<>();

    public String getmFristImg() {
        return mFristImg;
    }

    public void setmFristImg(String mFristImg) {
        this.mFristImg = mFristImg;
    }

    public int getmFristImgWidth() {
        return mFristImgWidth;
    }

    public void setmFristImgWidth(int mFristImgWidth) {
        this.mFristImgWidth = mFristImgWidth;
    }

    public int getmFristImgHeight() {
        return mFristImgHeight;
    }

    public void setmFristImgHeight(int mFristImgHeight) {
        this.mFristImgHeight = mFristImgHeight;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmDesc() {
        return mDesc;
    }

    public void setmDesc(String mDesc) {
        this.mDesc = mDesc;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getmChannelName() {
        return mChannelName;
    }

    public void setmChannelName(String mChannelName) {
        this.mChannelName = mChannelName;
    }

    public String getmChannelId() {
        return mChannelId;
    }

    public void setmChannelId(String mChannelId) {
        this.mChannelId = mChannelId;
    }

    public String getmNewsLink() {
        return mNewsLink;
    }

    public void setmNewsLink(String mNewsLink) {
        this.mNewsLink = mNewsLink;
    }

    public List<LContent> getmContents() {
        return mContents;
    }

    public void setmContents(List<LContent> mContents) {
        this.mContents = mContents;
    }

    public String getmSource() {
        return mSource;
    }

    public void setmSource(String mSource) {
        this.mSource = mSource;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LNews news = (LNews) o;

        return mTitle != null ? mTitle.equals(news.mTitle) : news.mTitle == null;

    }

    @Override
    public int hashCode() {
        return mTitle != null ? mTitle.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "LNews{" +
                "mTitle='" + mTitle + '\'' +
                ", mChannelName='" + mChannelName + '\'' +
                ", mChannelId='" + mChannelId + '\'' +
                ", mSource='" + mSource + '\'' +
                ", mDate='" + mDate + '\'' +
                ", mDesc='" + mDesc + '\'' +
                ", mNewsLink='" + mNewsLink + '\'' +
                '}';
    }

}
