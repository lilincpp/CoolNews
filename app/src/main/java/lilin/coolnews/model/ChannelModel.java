package lilin.coolnews.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

/**
 * Created by lilin on 2016/8/10.
 */
public class ChannelModel {

    String showapi_res_code;
    String showapi_res_error;
    Body showapi_res_body;

    static class Body {
        String totalNum;
        String ret_code;
        List<Channel> channelList;

        public String getTotalNum() {
            return totalNum;
        }

        public void setTotalNum(String totalNum) {
            this.totalNum = totalNum;
        }

        public String getRet_code() {
            return ret_code;
        }

        public void setRet_code(String ret_code) {
            this.ret_code = ret_code;
        }

        public List<Channel> getChannelList() {
            return channelList;
        }

        public void setChannelList(List<Channel> channelList) {
            this.channelList = channelList;
        }
    }

    @Table(name = "Channel")
    static class Channel extends Model{
        @Column(name = "channel_id")
        String channelId;
        @Column(name = "name")
        String name;

        public String getChannelId() {
            return channelId;
        }

        public void setChannelId(String channelId) {
            this.channelId = channelId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public String getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(String showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public Body getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(Body showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }
}
