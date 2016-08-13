package lilin.coolnews.config;

/**
 * Created by lilin on 2016/8/9.
 */
public class AppConfig {

    private AppConfig() {
    }


    //http://route.showapi.com/109-35?showapi_appid=23036&channelId=&channelName=&title=&page=&needContent=0&needHtml=&needAllList=1
    // &showapi_sign=abfe1d1679c245ac8fecfb1410177b45

    //http://route.showapi.com/109-34?showapi_appid=23036&showapi_sign=abfe1d1679c245ac8fecfb1410177b45
    //易源的新闻接口
    public static final String APP_ID = "23036";

    public static final String APP_SECRET = "abfe1d1679c245ac8fecfb1410177b45";

    public static final String URL_REQUEST_TYPE = "http://route.showapi.com/109-34";

    public static final String URL_REQUEST_CONTENT = "http://route.showapi.com/109-35";
}
