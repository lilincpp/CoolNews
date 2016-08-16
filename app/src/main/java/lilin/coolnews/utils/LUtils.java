package lilin.coolnews.utils;

import android.content.Context;

/**
 * Created by lilin on 2016/8/16.
 */
public class LUtils {
    private LUtils() {
    }

    public static int dpToPx(Context context, float value) {
        return Math.round(context.getResources().getDisplayMetrics().density * value);
    }
}
