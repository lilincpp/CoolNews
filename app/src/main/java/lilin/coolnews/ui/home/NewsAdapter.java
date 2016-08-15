package lilin.coolnews.ui.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import lilin.coolnews.R;
import lilin.coolnews.model.LNews;

/**
 * Created by lilin on 2016/8/11.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> implements View.OnClickListener {
    private static final String TAG = "NewsAdapter";

    private List<LNews> mNewses;
    private RecyclerView mRecyclerView;
    private LOnClickListener mLOnClickListener;


    public interface LOnClickListener {
        void onClick(int position, LNews lNews);
    }


    public NewsAdapter(List<LNews> newses,LOnClickListener lOnClickListener) {
        mNewses = newses;
        mLOnClickListener=lOnClickListener;
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
        holder.tvTitle.setText(mNewses.get(position).getmTitle());
        holder.tvContent.setText(mNewses.get(position).getmDesc());


    }

    @Override
    public int getItemCount() {
        return mNewses.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvSource, tvTitle, tvContent;
        RelativeLayout lay;
        ImageButton ibtn;

        public ViewHolder(View itemView) {
            super(itemView);
            tvSource = (TextView) itemView.findViewById(R.id.tv_source);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvContent = (TextView) itemView.findViewById(R.id.tv_content);
            lay = (RelativeLayout) itemView.findViewById(R.id.lay);
            ibtn = (ImageButton) itemView.findViewById(R.id.ibtn_menu);
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
