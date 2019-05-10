package com.hi.skinsapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class SkinActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "position";
    private WebView mWebView;

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

        mWebView = findViewById(R.id.web_view);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl("file:///android_asset/skins_image/0.png");
    }
}
