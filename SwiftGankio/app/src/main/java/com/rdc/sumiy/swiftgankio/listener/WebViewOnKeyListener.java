package com.rdc.sumiy.swiftgankio.listener;

import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;

/**
 * Created by sumiy on 2016/8/13.
 */
public class WebViewOnKeyListener implements View.OnKeyListener {
    private WebView webView;
    public WebViewOnKeyListener(WebView webView) {
        this.webView = webView;
    }
        @Override
        public boolean onKey (View v,int keyCode, KeyEvent event){
            if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
                webView.goBack(); // goBack()表示返回WebView的上一页面
                return true;
            }
            return false;
        }
}
