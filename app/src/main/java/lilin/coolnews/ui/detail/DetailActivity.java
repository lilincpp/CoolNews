package lilin.coolnews.ui.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import lilin.coolnews.Base.BaseActivity;
import lilin.coolnews.R;
import lilin.coolnews.model.LNews;

/**
 * Created by lilin on 2016/8/11.
 */
public class DetailActivity extends BaseActivity implements DetailContract.View {

    private static final String TAG = "DetailActivity";


    @BindView(R.id.rv_news_content)
    RecyclerView mRvNewsContent;

    private LNews mLNews;
    private DetailContract.Presenter mPresenter;
    private DetailAdapter mDetailAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailActivity.this.finish();
            }
        });
        ButterKnife.bind(this);

        mLNews = (LNews) getIntent().getSerializableExtra("news");
//        Log.e(TAG, "onCreate: " + mLNews.toString());
//        Log.e(TAG, "======================================");
//
//        for (LContent lContent : mLNews.getmContents()) {
//            Log.e(TAG, "onCreate: " + lContent.toString());
//        }

        mDetailAdapter = new DetailAdapter(this,mLNews );
        mRvNewsContent.setLayoutManager(new LinearLayoutManager(this));
        mRvNewsContent.setAdapter(mDetailAdapter);

        new DetailPresenter(this);
    }


    @Override
    public void setPresent(DetailContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
