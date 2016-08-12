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

/**
 * Created by lilin on 2016/8/12.
 */
public class DetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "DetailAdapter";

    private static final int TYPE_TEXT = 0;
    private static final int TYPE_IMG = 1;

    private List<LContent> mLContents;
    private ImageLoader mImageLoader;

    public DetailAdapter(Context context, List<LContent> lContents) {
        mImageLoader = new ImageLoader(Volley.newRequestQueue(context), new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String url) {
                return null;
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {

            }
        });

        mLContents = lContents;

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        if (viewType == TYPE_IMG) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_img, parent, false);
            return new ImgViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text, parent, false);
            return new TextViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TextViewHolder) {
            TextViewHolder textViewHolder = (TextViewHolder) holder;
            Log.e(TAG, "onBindViewHolder: TEXT->" + mLContents.get(position).getmValue());
            Log.e(TAG, "onBindViewHolder: tv->" + (textViewHolder.tv == null));
            textViewHolder.tv.setText(mLContents.get(position).getmValue());
        } else if (holder instanceof ImgViewHolder) {
            ImgViewHolder imgViewHolder = (ImgViewHolder) holder;
            Log.e(TAG, "onBindViewHolder: imge->" + (imgViewHolder.img == null));
//            ImageLoader.ImageListener imageListener = ImageLoader.getImageListener(imgViewHolder.img, R.drawable.ic_default, R.drawable
//                    .ic_load_error);
//            mImageLoader.get(mLContents.get(position).getmValue(), imageListener);
        }
    }

    @Override
    public int getItemCount() {

        return mLContents.size();
    }

    @Override
    public int getItemViewType(int position) {
        LContent lContent = mLContents.get(position);
        if (lContent.getmType() == LContent.TYPE.IMG) {
            return TYPE_IMG;
        } else {
            return TYPE_TEXT;
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
}
