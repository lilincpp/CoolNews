package lilin.coolnews.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by lilin on 2016/8/16.
 */
public class MyImgeView extends ImageView {
    public MyImgeView(Context context) {
        super(context);
    }

    public MyImgeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyImgeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = MeasureSpec.getSize(widthMeasureSpec) * 2 / 3;
        int heightSpec=MeasureSpec.makeMeasureSpec(height,MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightSpec);
    }
}
