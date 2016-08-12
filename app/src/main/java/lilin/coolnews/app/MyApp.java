package lilin.coolnews.app;

import android.app.Application;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;

import lilin.coolnews.model.ChannelModel;
import lilin.coolnews.model.NewsModel;


/**
 * Created by lilin on 2016/8/10.
 */
public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Configuration.Builder configurationBuilder = new Configuration.Builder(this);
        configurationBuilder.addModelClass(ChannelModel.Channel.class);
        configurationBuilder.addModelClass(NewsModel.class);
        ActiveAndroid.initialize(configurationBuilder.create());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ActiveAndroid.dispose();
    }
}
