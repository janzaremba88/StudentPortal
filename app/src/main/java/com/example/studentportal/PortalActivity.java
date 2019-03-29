package com.example.studentportal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class PortalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portal);
        Toolbar toolbar = findViewById(R.id.toolbar2);
        WebView myWebView = findViewById(R.id.webview);
        setSupportActionBar(toolbar);
        Intent intent = this.getIntent();
        final Portal portal = intent.getParcelableExtra(MainActivity.EXTRA_PORTAL);
        String url = portal.getmPortalAddress();
        if (url != null) {
            WebSettings webSettings = myWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            myWebView.loadUrl(url);
        }
    }
}
