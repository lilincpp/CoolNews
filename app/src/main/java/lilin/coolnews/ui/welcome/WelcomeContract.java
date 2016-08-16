package lilin.coolnews.ui.welcome;

import android.content.Context;

import lilin.coolnews.base.BasePresenter;
import lilin.coolnews.base.BaseView;

/**
 * Created by lilin on 2016/8/11.
 */
public interface WelcomeContract {

    interface View extends BaseView<Presenter> {

        void go();

        void loadFail();
    }


    interface Presenter extends BasePresenter {
        void checkChannel(Context context);
        void reload(Context context);
    }

}
