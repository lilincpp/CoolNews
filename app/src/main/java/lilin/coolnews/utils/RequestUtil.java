package lilin.coolnews.utils;

import lilin.coolnews.config.AppConfig;

/**
 * Created by lilin on 2016/8/10.
 */
public class RequestUtil {
    private static final String TAG = "RequestUtil";

    private RequestUtil() {
    }

    /**
     * 请求新闻的类型
     *
     * @return
     */
    public static String getNewsType() {
        return AppConfig.URL_REQUEST_TYPE + "?" + "showapi_appid=" + AppConfig.APP_ID + "&showapi_sign=" + AppConfig.APP_SECRET;
    }

    public static String getNews(String channelName) {
        StringBuilder stringBuilder = new StringBuilder(AppConfig.URL_REQUEST_CONTENT);
        stringBuilder
                .append("?")
                .append("showapi_appid=").append(AppConfig.APP_ID).append("&")
                .append("channelId=").append("&")
                .append("channelName=").append(channelName).append("&")
                .append("title=").append("&")
                .append("page=").append("&")
                .append("needContent=0").append("&")
                .append("needAllList=1").append("&")
                .append("showapi_sign=").append(AppConfig.APP_SECRET);

        return stringBuilder.toString();
    }
}
