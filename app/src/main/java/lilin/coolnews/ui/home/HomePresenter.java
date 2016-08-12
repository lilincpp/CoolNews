package lilin.coolnews.ui.home;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import lilin.coolnews.model.LNews;
import lilin.coolnews.model.NewsModel;
import lilin.coolnews.utils.LContentUtil;
import lilin.coolnews.utils.RequestUtil;

/**
 * Created by lilin on 2016/8/10.
 */
public class HomePresenter implements HomeContract.Presenter {


    private HomeContract.View mHomeView;

    public HomePresenter(HomeContract.View view) {
        mHomeView = view;
        mHomeView.setPresent(this);
    }

    @Override
    public void loadNews(Context context, final String channelName) {
        List<NewsModel> newsModels = NewsModel.getNews(channelName);
        if (newsModels.size() != 0) {
            List<LNews> lNewses = new ArrayList<>();
            for (NewsModel newsModel : newsModels) {
                List<LNews> newses = LContentUtil.read(newsModel.getmJson());
                lNewses.addAll(newses);
            }
            mHomeView.show(lNewses);
        } else {
            requestNews(context, channelName);
        }
    }

    @Override
    public void refresh(Context context, String channelName) {
        requestNews(context, channelName);
    }

    private void requestNews(Context context, final String channelName) {
        StringRequest stringRequest = new StringRequest(StringRequest.Method.GET, RequestUtil.getNews(channelName), new Response
                .Listener<String>() {

            @Override
            public void onResponse(String response) {
                List<LNews> newses = LContentUtil.read(response);
                mHomeView.show(newses);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mHomeView.loadFail();
            }
        });
        Volley.newRequestQueue(context).add(stringRequest);
    }
}
