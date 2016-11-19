package com.rdc.sumiy.swiftgankio.listener;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import com.rdc.sumiy.swiftgankio.R;
/**
 * Created by sumiy on 2016/8/13.
 */
public class OnPlayVideoActivity extends AppCompatActivity {
    private static WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoplay);
        initUI();
        initData();
    }
    private void initUI() {
        webView = (WebView) findViewById(R.id.webViewInVideo);
    }
    private void initData() {
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl("http://weibo.com/p/230444d389e174bf27572c56c38c4f1366137c");
        webView.setOnKeyListener(new WebViewOnKeyListener(webView));
    }
}
