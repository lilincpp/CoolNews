package lilin.coolnews.ui.home;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import lilin.coolnews.R;
import lilin.coolnews.model.LNews;
import lilin.coolnews.utils.LContentUtil;

/**
 * Created by lilin on 2016/8/11.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> implements View.OnClickListener {
    private static final String TAG = "NewsAdapter";

    private List<LNews> mNewses;
    private RecyclerView mRecyclerView;
    private LOnClickListener mLOnClickListener;
    private SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.CHINA);


    public interface LOnClickListener {
        void onClick(int position, LNews lNews);
    }


    public NewsAdapter(List<LNews> newses, LOnClickListener lOnClickListener) {
        mNewses = newses;
        mLOnClickListener = lOnClickListener;
    }

    public void update(List<LNews> newses) {
        mNewses.addAll(0, newses);
        notifyDataSetChanged();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
    }

    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final NewsAdapter.ViewHolder holder, int position) {

        holder.tvSource.setText(mRecyclerView.getResources().getString(R.string.item_from) + "\t" + mNewses.get(position).getmSource());
        String title = (TextUtils.isEmpty(mNewses.get(position).getmTitle())) ? "服务器错误:获取标题失败" : mNewses.get(position).getmTitle();
        String desc = (TextUtils.isEmpty(mNewses.get(position).getmDesc())) ? "服务器错误:获取内容介绍失败" : mNewses.get(position).getmDesc();
        holder.tvTitle.setText(title);
        holder.tvContent.setText(desc);

        try {
            long newsTime = mSimpleDateFormat.parse(mNewses.get(position).getmDate()).getTime();
            Log.e(TAG, "onBindViewHolder: date->" + mNewses.get(position).getmDate() + ",title:" + title + ",time:" + LContentUtil.parseTime(newsTime));
            String tip;
            if(!TextUtils.isEmpty(mNewses.get(position).getmNewsTag())){
                tip=mNewses.get(position).getmNewsTag() + "\t" + "·" + "\t" + LContentUtil.parseTime(newsTime);
            }else {
                tip=LContentUtil.parseTime(newsTime);
            }
            holder.tvTime.setText(tip);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        final String imgUrl = mNewses.get(position).getmFristImg();
        if (imgUrl != null) {
            holder.iv.setVisibility(View.VISIBLE);
            //            holder.iv.setMinimumHeight(mNewses.get(position).getmFristImgHeight());
            Picasso.with(mRecyclerView.getContext()).load(imgUrl).into(holder.iv);
        } else {
            holder.iv.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mNewses.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvSource, tvTitle, tvContent, tvTime;
        RelativeLayout lay;
        ImageButton ibtn;
        ImageView iv;

        public ViewHolder(View itemView) {
            super(itemView);
            tvSource = (TextView) itemView.findViewById(R.id.tv_source);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvContent = (TextView) itemView.findViewById(R.id.tv_content);
            lay = (RelativeLayout) itemView.findViewById(R.id.lay);
            ibtn = (ImageButton) itemView.findViewById(R.id.ibtn_menu);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);
            iv = (ImageView) itemView.findViewById(R.id.iv);
        }
    }

    @Override
    public void onClick(View v) {
        int position = mRecyclerView.getChildAdapterPosition(v);
        if (mLOnClickListener != null) {
            mLOnClickListener.onClick(position, mNewses.get(position));
        }
    }
}
