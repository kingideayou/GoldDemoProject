package me.next.golddemoproject.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONException;
import com.avos.avoscloud.AVUser;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.List;

import me.next.golddemoproject.R;
import me.next.golddemoproject.model.Entry;
import me.next.golddemoproject.ui.activities.WebViewEntryActivity;

/**
 * Created by NeXT on 16/4/25.
 * GoldDemoProject
 */
public class EntryListAdapter extends RecyclerView.Adapter<EntryListAdapter.EntryViewHolder> {

    private Context mContext;
    private List<Entry> mEntries;

    public EntryListAdapter(Context context, List<Entry> entries) {
        this.mContext = context;
        this.mEntries = entries;
    }

    @Override
    public EntryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new EntryViewHolder(LayoutInflater.from(mContext).inflate(R.layout.card_entry, parent, false));
    }

    @Override
    public void onBindViewHolder(EntryViewHolder holder, int position) {
        holder.onBindViewHolder(mEntries.get(position));
    }

    @Override
    public int getItemCount() {
        return mEntries.size();
    }

    public class EntryViewHolder extends RecyclerView.ViewHolder {

        private View mCard;
        private TextView tvTitle;
        private TextView tvContent;
        private ImageView ivAvatar;
        private ImageView ivScreenshot;

        public EntryViewHolder(View itemView) {
            super(itemView);
            mCard = itemView;
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvContent = (TextView) itemView.findViewById(R.id.tv_content);
            ivAvatar = (ImageView) itemView.findViewById(R.id.iv_avatar);
            ivScreenshot = (ImageView) itemView.findViewById(R.id.iv_screenshot);
        }

        public void onBindViewHolder(final Entry entry) {
            tvTitle.setText(entry.getString("title"));
            tvContent.setText(entry.getString("content"));
            Glide.with(mContext).load(entry.getString("screenshot")).into(ivScreenshot);
            try {
                AVUser user = entry.getAVUser("user");
                Glide.with(mContext).load(user != null ? user.getString("avatar_large") : "").asBitmap().error(R.mipmap.ic_launcher)
                        .into(new BitmapImageViewTarget(ivAvatar) {
                            @Override
                            protected void setResource(Bitmap resource) {
                                super.setResource(resource);
                                RoundedBitmapDrawable circularBitmapDrawable =
                                        RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                                circularBitmapDrawable.setCornerRadius(100f);
                                ivAvatar.setImageDrawable(circularBitmapDrawable);
                            }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }

            mCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    WebViewEntryActivity.start(mContext, entry.getString("url"));
                }
            });
        }
    }

}
