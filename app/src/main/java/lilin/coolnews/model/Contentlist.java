package lilin.coolnews.model;

import java.util.List;

/**
 * Created by lilin on 2016/8/10.
 */
public class Contentlist {
    private String content;

    private String pubDate;

    private String title;

    private String channelName;

    private List<Imageurls> imageurls ;

    private String desc;

    private String source;

    private int sentiment_display;

    private String channelId;

    private String nid;

    private String link;

    public void setContent(String content){
        this.content = content;
    }
    public String getContent(){
        return this.content;
    }
    public void setPubDate(String pubDate){
        this.pubDate = pubDate;
    }
    public String getPubDate(){
        return this.pubDate;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }
    public void setChannelName(String channelName){
        this.channelName = channelName;
    }
    public String getChannelName(){
        return this.channelName;
    }
    public void setImageurls(List<Imageurls> imageurls){
        this.imageurls = imageurls;
    }
    public List<Imageurls> getImageurls(){
        return this.imageurls;
    }
    public void setDesc(String desc){
        this.desc = desc;
    }
    public String getDesc(){
        return this.desc;
    }
    public void setSource(String source){
        this.source = source;
    }
    public String getSource(){
        return this.source;
    }
    public void setSentiment_display(int sentiment_display){
        this.sentiment_display = sentiment_display;
    }
    public int getSentiment_display(){
        return this.sentiment_display;
    }
    public void setChannelId(String channelId){
        this.channelId = channelId;
    }
    public String getChannelId(){
        return this.channelId;
    }
    public void setNid(String nid){
        this.nid = nid;
    }
    public String getNid(){
        return this.nid;
    }
    public void setLink(String link){
        this.link = link;
    }
    public String getLink(){
        return this.link;
    }

}
