package me.next.golddemoproject.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import me.next.golddemoproject.R;
import me.next.golddemoproject.model.Entry;

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

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class EntryViewHolder extends RecyclerView.ViewHolder {
        public EntryViewHolder(View itemView) {
            super(itemView);
        }
    }

}
