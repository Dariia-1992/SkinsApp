package com.hi.skinsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.webkit.WebChromeClient;
import android.webkit.WebView;


public class SkinActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "position";
    private WebView mWebView;
    private Toolbar mToolbar;

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
        mToolbar = findViewById(R.id.skin_toolbar);


        mWebView = findViewById(R.id.web_view);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setAllowFileAccessFromFileURLs(true);
        //mWebView.setWebViewClient(new WebViewClient());
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.loadUrl("file:///android_asset/3d-skin-preview/index.html?url=file:///android_asset/skins_image/" + position + ".png");

        setSupportActionBar(mToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.skin_toolbar, menu);
        return true;
    }
}
