package lilin.coolnews.model;

import android.text.TextUtils;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by lilin on 2016/8/11.
 */
@Table(name = "NewsModel")
public class NewsModel extends Model {
    private static final String TAG = "NewsModel";

    @Column(name = "json")
    private String mJson;
    @Column(name = "channel_id")
    private String mChannelId;
    @Column(name = "channel_name")
    private String mChannelName;

    public String getmChannelName() {
        return mChannelName;
    }

    public void setmChannelName(String mChannelName) {
        this.mChannelName = mChannelName;
    }

    public String getmJson() {
        return mJson;
    }

    public void setmJson(String mJson) {
        this.mJson = mJson;
    }

    public String getmChannelId() {
        return mChannelId;
    }

    public void setmChannelId(String mChannelId) {
        this.mChannelId = mChannelId;
    }

    public static List<NewsModel> getNews(String name) {
        return new Select().from(NewsModel.class).where("channel_name=?", name).execute();
    }

    public static boolean isExit(String json) {
        List<NewsModel> newsModels = new Select().from(NewsModel.class).where("json=?", json).execute();

        for (NewsModel newsModel1 : newsModels) {
            if (TextUtils.equals(json, newsModel1.getmJson())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;

        NewsModel newsModel = (NewsModel) o;

        return mJson != null ? mJson.equals(newsModel.mJson) : newsModel.mJson == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (mJson != null ? mJson.hashCode() : 0);
        return result;
    }
}
