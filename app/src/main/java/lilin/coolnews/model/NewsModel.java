package lilin.coolnews.model;

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
}
