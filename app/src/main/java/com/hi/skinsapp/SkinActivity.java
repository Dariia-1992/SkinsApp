package com.hi.skinsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ShareActionProvider;
import android.widget.TextView;


public class SkinActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "position";
    private WebView mWebView;
    private TextView mTextView;
    private ImageButton mBackButton;
    private ImageButton mShareButton;
    private ShareActionProvider mShareAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skin);

        Intent intent = getIntent();
        int position = intent.getIntExtra(EXTRA_POSITION, -1);
        if (position == -1){
            finish();
            return;
        }

        mBackButton = findViewById(R.id.back_button);
        mShareButton = findViewById(R.id.share_image_button);
        mTextView = findViewById(R.id.position_number);
        mTextView.setText("#" + (position + 1));
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(SkinActivity.this, MainActivity.class);
                startActivity(back);
            }
        });
        mShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent = new Intent();
                setShareIntent(shareIntent);
            }
        });

        mWebView = findViewById(R.id.web_view);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setAllowFileAccessFromFileURLs(true);
        //mWebView.setWebViewClient(new WebViewClient());
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.loadUrl("file:///android_asset/3d-skin-preview/index.html?url=file:///android_asset/skins_image/" + position + ".png");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.skin_toolbar, menu);
        MenuItem item= menu.findItem(R.id.share_button);
        mShareAction = (ShareActionProvider) item.getActionProvider();
        return true;
    }
    private void setShareIntent(Intent shareIntent){
        if (mShareAction != null){
            mShareAction.setShareIntent(shareIntent);
        }
    }
}
