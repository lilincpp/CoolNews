package lilin.coolnews.app;

import android.app.Application;

import com.activeandroid.ActiveAndroid;

/**
 * Created by lilin on 2016/8/10.
 */
public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
    }
}
