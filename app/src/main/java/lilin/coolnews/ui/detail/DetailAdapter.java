package lilin.coolnews.ui.detail;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

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
    private static final int TYPE_HEAD=2;

    private List<LContent> mLContents;
    private ImageLoader mImageLoader;
    private LNews mLNews;

    public DetailAdapter(Context context, LNews lNews) {
        mImageLoader = new ImageLoader(Volley.newRequestQueue(context), new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String url) {
                return null;
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {

            }
        });
        mLNews=lNews;
        mLContents = lNews.getmContents();

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        switch (viewType){
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
//            Log.e(TAG, "onBindViewHolder: TEXT->" + mLContents.get(position).getmValue());
//            Log.e(TAG, "onBindViewHolder: tv->" + (textViewHolder.tv == null));
            textViewHolder.tv.setText(mLContents.get(position-1).getmValue());
        } else if (holder instanceof ImgViewHolder) {
            ImgViewHolder imgViewHolder = (ImgViewHolder) holder;
            Log.e(TAG, "onBindViewHolder: IMG->" + mLContents.get(position-1).getmValue());
            ImageLoader.ImageListener imageListener = ImageLoader.getImageListener(imgViewHolder.img, R.drawable.ic_default, R.drawable
                    .ic_load_error);
            mImageLoader.get(mLContents.get(position-1).getmValue(), imageListener);
        } else if(holder instanceof HeadViewHolder){
            HeadViewHolder headViewHolder= (HeadViewHolder) holder;
            headViewHolder.tvTitle.setText(mLNews.getmTitle());
            headViewHolder.tvSource.setText(mLNews.getmSource());
            headViewHolder.tvDate.setText(mLNews.getmDate());

        }
    }

    @Override
    public int getItemCount() {

        return mLContents.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
       if(position==0){
           return TYPE_HEAD;
       }else {
           LContent lContent = mLContents.get(position-1);
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

    static class HeadViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle,tvSource,tvDate;
        public HeadViewHolder(View itemView) {
            super(itemView);
            tvTitle= (TextView) itemView.findViewById(R.id.tv_title);
            tvSource= (TextView) itemView.findViewById(R.id.tv_source);
            tvDate= (TextView) itemView.findViewById(R.id.tv_date);

        }
    }
}
