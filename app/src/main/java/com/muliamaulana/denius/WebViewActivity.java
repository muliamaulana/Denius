package com.muliamaulana.denius;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class WebViewActivity extends AppCompatActivity {

    private WebView webView;
    private WebSettings settings;
    private SwipeRefreshLayout refreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        final String url = getIntent().getStringExtra("url");
        String title = getIntent().getStringExtra("title");
        String time = getIntent().getStringExtra("time");
        webView = findViewById(R.id.webView);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(title);
        actionBar.setSubtitle(time);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);


        initWebView(url);


        refreshLayout = findViewById(R.id.refresh_webview);
//        refreshLayout.post(new Runnable() {
//            @Override
//            public void run() {
//                refreshLayout.setRefreshing(true);
//
//            }
//        });

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initWebView(url);
            }
        });
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView(String url) {
        webView.setWebViewClient(new CustWebViewClient());
        webView.setWebChromeClient(new CustChromeClient());

        CookieManager.getInstance().setAcceptCookie(true);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        String cacheDirPath =getFilesDir().getAbsolutePath() + "checktool";
        webSettings.setDatabasePath(cacheDirPath);
        webSettings.setAppCachePath(cacheDirPath);
        webSettings.setDefaultTextEncodingName("utf-8");
        webSettings.setAppCacheEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);
        webSettings.setDisplayZoomControls(false);
        webView.loadUrl(url);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class CustWebViewClient extends WebViewClient {
        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            Log.d("CustWebViewClient", "error:" + error.toString());
            refreshLayout.setRefreshing(false);
            super.onReceivedError(view, request, error);
        }

        @Override
        public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
            Log.d("CustWebViewClient", "errorResponse:" + errorResponse.getData());
            refreshLayout.setRefreshing(false);
            super.onReceivedHttpError(view, request, errorResponse);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            refreshLayout.setRefreshing(true);
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            refreshLayout.setRefreshing(false);
            super.onPageFinished(view, url);
        }
    }

    private class CustChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            Log.d("CustChromeClient", "newProgress:" + newProgress);
            if (newProgress == 100) {
                refreshLayout.setRefreshing(false);
            }
            super.onProgressChanged(view, newProgress);
        }
    }
}
