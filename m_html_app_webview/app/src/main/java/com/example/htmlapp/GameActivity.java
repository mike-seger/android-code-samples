package com.example.htmlapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by pye on 6/28/16.
 */
public class GameActivity extends Activity {
    WebSettings wSettings;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String gamePath = getIntent().getStringExtra("game_path");
        Log.d("pengye", "gamePath====" + gamePath);

        WebView webView = new WebView(this);
        webView.setClickable(true);
        wSettings = webView.getSettings();
        wSettings.setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setSupportMultipleWindows(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.setHorizontalScrollBarEnabled(false);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.getSettings().setAllowFileAccessFromFileURLs(true);
        webView.getSettings().setAllowUniversalAccessFromFileURLs(true);

        // <b> Support Classes For WebView </b>
        WebViewClient webViewClient = new WebViewClient();
        webView.setWebViewClient(webViewClient);
        WebChromeClient webChromeClient = new WebChromeClient();
        webView.setWebChromeClient(webChromeClient);

        // Now Added Java Interface Class
        webView.addJavascriptInterface(new WebAppInterface(this), "Android");

        // Load Our Custom JS Inside WebView
         webView.loadUrl(gamePath);
//        webView.loadUrl("file:///android_asset/pop.html");
        setContentView(webView);
    }

}
