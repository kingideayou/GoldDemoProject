package me.next.golddemoproject.ui.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;

import java.util.ArrayList;
import java.util.List;

import me.next.golddemoproject.R;
import me.next.golddemoproject.model.Entry;
import me.next.golddemoproject.ui.adapter.EntryListAdapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);

        new GetEntriesTask().execute();
    }

    public class GetEntriesTask extends AsyncTask<Void, Void, List<Entry>> {
        @Override
        protected List<Entry> doInBackground(Void... params) {
            AVQuery<Entry> query = new AVQuery<>("Entry");
            query.setCachePolicy(AVQuery.CachePolicy.NETWORK_ELSE_CACHE);
            query.include("user");
            query.include("user.installation");
            query.orderByDescending("createdAt");
            query.limit(30);
            try {
                return query.find();
            } catch (AVException e) {
                e.printStackTrace();
                return new ArrayList<>();
            }
        }

        @Override
        protected void onPostExecute(List<Entry> entries) {
            super.onPostExecute(entries);
            mRecyclerView.setAdapter(new EntryListAdapter(getApplicationContext(), entries));
        }
    }

}
