package lilin.coolnews.ui.welcome;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

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
                Gson gson = new Gson();
                ChannelModel channelModel = gson.fromJson(response, ChannelModel.class);
                Log.e(TAG, "Channel Size:" + channelModel.getShowapi_res_body().getChannelList().size());
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
