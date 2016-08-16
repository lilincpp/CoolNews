package lilin.coolnews.model;

import java.io.Serializable;

/**
 * Created by lilin on 2016/8/11.
 *
 * 新闻正文
 */
public class LContent implements Serializable{

    public enum TYPE {
        IMG, TEXT
    }

    private TYPE mType;
    private String mValue;
    private int mImgWidth;
    private int mImgHeight;

    public LContent(TYPE mType, String mValue) {
        this.mType = mType;
        this.mValue = mValue;
    }

    public int getmImgWidth() {
        return mImgWidth;
    }

    public void setmImgWidth(int mImgWidth) {
        this.mImgWidth = mImgWidth;
    }

    public int getmImgHeight() {
        return mImgHeight;
    }

    public void setmImgHeight(int mImgHeight) {
        this.mImgHeight = mImgHeight;
    }

    public TYPE getmType() {
        return mType;
    }

    public void setmType(TYPE mType) {
        this.mType = mType;
    }

    public String getmValue() {
        return mValue;
    }

    public void setmValue(String mValue) {
        this.mValue = mValue;
    }

    @Override
    public String toString() {
        return "LContent{" +
                "mType=" + mType +
                ", mValue='" + mValue + '\'' +
                '}';
    }
}
