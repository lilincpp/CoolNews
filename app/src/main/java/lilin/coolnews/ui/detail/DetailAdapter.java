package lilin.coolnews.ui.detail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import lilin.coolnews.R;
import lilin.coolnews.model.LContent;
import lilin.coolnews.model.LNews;

/**
 * Created by lilin on 2016/8/12.
 */
public class DetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "DetailAdapter";

    private static final int TYPE_TEXT = 0;
    private static final int TYPE_IMG = 1;
    private static final int TYPE_HEAD = 2;

    private List<LContent> mLContents;
    private LNews mLNews;
    private Context mContext;

    public DetailAdapter(Context context, LNews lNews) {
        mLNews = lNews;
        mLContents = lNews.getmContents();
        mContext = context;

    }

    public void update(LNews lNews) {
        mLNews = lNews;
        mLContents = lNews.getmContents();
        notifyDataSetChanged();
    }

    public LNews getNowData() {
        return mLNews;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        switch (viewType) {
            case TYPE_HEAD:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_head, parent, false);
                return new HeadViewHolder(view);
            case TYPE_IMG:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_img, parent, false);
                return new ImgViewHolder(view);
            case TYPE_TEXT:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text, parent, false);
                return new TextViewHolder(view);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TextViewHolder) {
            TextViewHolder textViewHolder = (TextViewHolder) holder;
            textViewHolder.tv.setText(mLContents.get(position - 1).getmValue());
        } else if (holder instanceof ImgViewHolder) {
            ImgViewHolder imgViewHolder = (ImgViewHolder) holder;
            Picasso.with(mContext).load(mLContents.get(position - 1).getmValue())
                    .error(R.drawable.ic_load_error)
                    .placeholder(R.drawable.ic_default)
                    .into(imgViewHolder.img);
        } else if (holder instanceof HeadViewHolder) {
            HeadViewHolder headViewHolder = (HeadViewHolder) holder;
            headViewHolder.tvTitle.setText(mLNews.getmTitle());
            headViewHolder.tvSource.setText(mLNews.getmSource());
            headViewHolder.tvDate.setText(mLNews.getmDate());

        }
    }

    @Override
    public int getItemCount() {

        return mLContents.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEAD;
        } else {
            LContent lContent = mLContents.get(position - 1);
            if (lContent.getmType() == LContent.TYPE.IMG) {
                return TYPE_IMG;
            } else {
                return TYPE_TEXT;
            }
        }
    }

    static class TextViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public TextViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }

    static class ImgViewHolder extends RecyclerView.ViewHolder {
        ImageView img;

        public ImgViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.iv);
        }
    }

    static class HeadViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvSource, tvDate;

        public HeadViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvSource = (TextView) itemView.findViewById(R.id.tv_source);
            tvDate = (TextView) itemView.findViewById(R.id.tv_date);

        }
    }
}
