package com.example.htmlapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewDemo extends Activity {
    WebSettings wSettings;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WebView webView = new WebView(this);
        webView.setClickable(true);
        wSettings = webView.getSettings();
        wSettings.setJavaScriptEnabled(true);

        // <b> Support Classes For WebView </b>
        WebClientClass webViewClient = new WebClientClass();
        webView.setWebViewClient(webViewClient);
        WebChromeClient webChromeClient = new WebChromeClient();
        webView.setWebChromeClient(webChromeClient);

        // Now Added Java Interface Class
        webView.addJavascriptInterface(new WebAppInterface(this), "Android");

        // Load Our Custom JS Inside WebView
        webView.loadUrl("file:///android_asset/demo.html");
        setContentView(webView);
    }

    public class WebClientClass extends WebViewClient {
        ProgressDialog pd = null;

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            pd = new ProgressDialog(WebViewDemo.this);
            pd.setTitle("Please wait");
            pd.setMessage("Page is loading..");
            pd.show();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            pd.dismiss();
        }
    }

    public class WebChromeClass extends WebChromeClient {
    }

}