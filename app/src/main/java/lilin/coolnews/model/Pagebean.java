package lilin.coolnews.model;

import java.util.List;

/**
 * Created by lilin on 2016/8/10.
 */
public class Pagebean {
    private int allPages;

    private List<Contentlist> contentlist ;

    private int currentPage;

    private int allNum;

    private int maxResult;

    public void setAllPages(int allPages){
        this.allPages = allPages;
    }
    public int getAllPages(){
        return this.allPages;
    }
    public void setContentlist(List<Contentlist> contentlist){
        this.contentlist = contentlist;
    }
    public List<Contentlist> getContentlist(){
        return this.contentlist;
    }
    public void setCurrentPage(int currentPage){
        this.currentPage = currentPage;
    }
    public int getCurrentPage(){
        return this.currentPage;
    }
    public void setAllNum(int allNum){
        this.allNum = allNum;
    }
    public int getAllNum(){
        return this.allNum;
    }
    public void setMaxResult(int maxResult){
        this.maxResult = maxResult;
    }
    public int getMaxResult(){
        return this.maxResult;
    }

}
