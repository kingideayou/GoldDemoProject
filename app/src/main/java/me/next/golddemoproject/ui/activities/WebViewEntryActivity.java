package me.next.golddemoproject.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.webkit.WebView;

import me.next.golddemoproject.R;

public class WebViewEntryActivity extends AppCompatActivity {

    private static final String ENTRY_URL = "ENTRY_URL";
    private WebView mWebView;

    public static void start(Context context, String url) {
        Intent starter = new Intent(context, WebViewEntryActivity.class);
        starter.putExtra(ENTRY_URL, url);
        starter.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_entry);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mWebView = (WebView) findViewById(R.id.web_view);
        mWebView.loadUrl(getIntent().getStringExtra(ENTRY_URL));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
