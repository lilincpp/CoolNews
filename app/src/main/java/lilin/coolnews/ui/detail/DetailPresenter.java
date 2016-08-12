package lilin.coolnews.ui.detail;

/**
 * Created by lilin on 2016/8/11.
 */
public class DetailPresenter implements DetailContract.Presenter {

    private DetailContract.View mView;

    public DetailPresenter(DetailContract.View view) {
        mView = view;
        mView.setPresent(this);
    }
}
