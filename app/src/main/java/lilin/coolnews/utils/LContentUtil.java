package lilin.coolnews.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import lilin.coolnews.model.LContent;
import lilin.coolnews.model.LNews;

/**
 * Created by lilin on 2016/8/11.
 */
public class LContentUtil {

    /**
     * 解析新闻内容
     *
     * @param jsonResponse
     * @return
     */
    public static List<LNews> read(String jsonResponse) {
        List<LNews> lNewses = new ArrayList<>();
        try {
            JSONObject root = new JSONObject(jsonResponse);
            JSONObject showapi_res_body = root.getJSONObject("showapi_res_body");
            JSONObject pagebean = showapi_res_body.getJSONObject("pagebean");
            JSONArray contentlist = pagebean.getJSONArray("contentlist");
            //解析需要的新闻信息
            for (int i = 0; i < contentlist.length(); ++i) {
                JSONObject jsonObject = contentlist.getJSONObject(i);
                LNews news = new LNews();
                //解析新闻的一些概述、属性等
                news.setmTitle(jsonObject.getString("title"));
                news.setmDesc(jsonObject.getString("desc"));
                news.setmDate(jsonObject.getString("pubDate"));
                news.setmChannelName(jsonObject.getString("channelName"));
                news.setmChannelId(jsonObject.getString("channelId"));
                news.setmNewsLink(jsonObject.getString("link"));
                news.setmSource(jsonObject.getString("source"));
                //解析新闻的具体内容
                if (!jsonObject.isNull("allList")) {
                    List<LContent> lContents = new ArrayList<>();
                    JSONArray allList = jsonObject.getJSONArray("allList");
                    for (int j = 0; j < allList.length(); ++j) {
                        LContent lContent;
                        if (allList.get(j) instanceof JSONObject) {
                            //图片
                            JSONObject jsonObject1 = (JSONObject) allList.get(j);
                            lContent = new LContent(LContent.TYPE.IMG, jsonObject1.getString("url"));
                            lContent.setmImgWidth(jsonObject1.getInt("width"));
                            lContent.setmImgHeight(jsonObject1.getInt("height"));
                        } else {
                            //文字
                            lContent = new LContent(LContent.TYPE.TEXT, allList.get(j).toString());
                        }
                        lContents.add(lContent);
                    }
                    news.setmContents(lContents);
                }
                //将图集的第一张图解析出来
                JSONArray jsonArray = jsonObject.getJSONArray("imageurls");
                if (jsonArray.length() != 0) {
                    JSONObject imgObject = jsonArray.getJSONObject(0);
                    news.setmFristImg(imgObject.getString("url"));
                    news.setmFristImgWidth(imgObject.getInt("width"));
                    news.setmFristImgHeight(imgObject.getInt("height"));
                }

                lNewses.add(news);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return lNewses;
    }
}
