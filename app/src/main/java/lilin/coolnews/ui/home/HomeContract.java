package lilin.coolnews.ui.home;

import android.content.Context;

import java.util.List;

import lilin.coolnews.base.BasePresenter;
import lilin.coolnews.base.BaseView;
import lilin.coolnews.model.LNews;

/**
 * Created by lilin on 2016/8/9.
 */
public interface HomeContract {

    interface View extends BaseView<Presenter> {
        void show(List<LNews> newses);

        void loadFail();


    }

    interface Presenter extends BasePresenter {
        void loadNews(Context context, String channelName);

        void refresh(Context context, String channelName);
    }
}
