package com.example.htmlapp;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * Created by pye on 6/28/16.
 */
public class WebAppInterface {

    private Context mContext;

    public WebAppInterface(Context context) {
        this.mContext = context;
    }

    @JavascriptInterface
    public void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }
}