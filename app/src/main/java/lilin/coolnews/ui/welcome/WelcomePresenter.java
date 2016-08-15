package lilin.coolnews.ui.welcome;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;
import java.util.List;

import lilin.coolnews.model.ChannelModel;
import lilin.coolnews.utils.RequestUtil;

;

/**
 * Created by lilin on 2016/8/11.
 */
public class WelcomePresenter implements WelcomeContract.Presenter {
    private static final String TAG = "WelcomePresenter";

    private WelcomeContract.View mWelcomeView;

    public WelcomePresenter(WelcomeContract.View view) {
        mWelcomeView = view;
        mWelcomeView.setPresent(this);
    }

    @Override
    public void checkChannel(final Context context) {
        List<ChannelModel.Channel> channelList = ChannelModel.Channel.getAll();
        if (channelList.size() != 0) {
            mWelcomeView.go();
        } else {
            reqeust(context);
        }
    }

    @Override
    public void reload(Context context) {
        reqeust(context);
    }

    private void reqeust(Context context) {
        StringRequest stringRequest = new StringRequest(StringRequest.Method.GET, RequestUtil.getNewsType(), new Response
                .Listener<String>() {
            @Override
            public void onResponse(final String response) {
                //不能直接Gson gson=new Gson();
                // 6.0 会出现SecurityException
                //原因是ActiveAndroid+Gson引起的。目前来说应该算是个BUG
                GsonBuilder builder = new GsonBuilder();
                builder.excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC);
                Gson gson = builder.create();
                ChannelModel channelModel = gson.fromJson(response, ChannelModel.class);
                for (ChannelModel.Channel channel : channelModel.getShowapi_res_body().getChannelList()) {
                    channel.save();
                }
                mWelcomeView.go();
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mWelcomeView.loadFail();
            }
        });

        Volley.newRequestQueue(context).add(stringRequest);
    }
}
