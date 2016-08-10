package lilin.coolnews.utils;

import lilin.coolnews.config.AppConfig;

/**
 * Created by lilin on 2016/8/10.
 */
public class RequestUtil {

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
}
