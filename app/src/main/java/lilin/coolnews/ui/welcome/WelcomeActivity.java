package lilin.coolnews.ui.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lilin.coolnews.R;
import lilin.coolnews.ui.home.HomeActivity;

/**
 * Created by lilin on 2016/8/10.
 */
public class WelcomeActivity extends AppCompatActivity implements WelcomeContract.View {

    @BindView(R.id.pb)
    ContentLoadingProgressBar mPb;
    @BindView(R.id.btn_reload)
    Button mBtnReload;
    @BindView(R.id.tv)
    TextView mTip;

    private WelcomeContract.Presenter mWelPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);

        new WelcomePresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mWelPresenter != null) {
            mWelPresenter.checkChannel(this);
        }
    }

    @Override
    public void setPresent(WelcomeContract.Presenter presenter) {
        mWelPresenter = presenter;
    }

    @Override
    public void go() {
        startActivity(new Intent(WelcomeActivity.this, HomeActivity.class));
        finish();
    }

    @Override
    public void loadFail() {
        mPb.setVisibility(View.INVISIBLE);
        mBtnReload.setVisibility(View.VISIBLE);
        mTip.setText(getString(R.string.welcome_erro_load));
    }

    @OnClick(R.id.btn_reload)
    public void onClick() {
        mWelPresenter.reload(this);

        mPb.setVisibility(View.VISIBLE);
        mBtnReload.setVisibility(View.GONE);
        mTip.setText(getString(R.string.welcome_loading));
    }
}
