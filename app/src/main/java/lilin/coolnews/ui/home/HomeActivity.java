package lilin.coolnews.ui.home;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import lilin.coolnews.Base.BaseActivity;
import lilin.coolnews.R;
import lilin.coolnews.utils.RequestUtil;

public class HomeActivity extends BaseActivity {
    private static final String TAG = "HomeActivity";

    @BindView(R.id.toolbar)
    Toolbar mToolBar;
    @BindView(R.id.tablayout)
    TabLayout mTablayout;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        StringRequest stringRequest = new StringRequest(StringRequest.Method.GET, RequestUtil.getNewsType(), new Response
                .Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }


}
