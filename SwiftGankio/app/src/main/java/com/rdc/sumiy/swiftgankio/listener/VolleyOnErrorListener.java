package com.rdc.sumiy.swiftgankio.listener;
import android.os.Handler;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.rdc.sumiy.swiftgankio.GankioApplication;
import com.rdc.sumiy.swiftgankio.myinterface.Callable;
import com.rdc.sumiy.swiftgankio.utils.ToastUtils;
/**
 * Created by sumiy on 2016/8/12.
 */
public class VolleyOnErrorListener implements Response.ErrorListener {
    private Callable callable;
    public VolleyOnErrorListener(Callable callable) {
        this.callable = callable;
    }
    @Override
    public void onErrorResponse(VolleyError volleyError) {
       Handler handler = new Handler(GankioApplication.gankioApplication.getApplicationContext().getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                ToastUtils.showToast(GankioApplication.gankioApplication.getApplicationContext(),
                        "网络连接错误，请稍后重试",1500);
                callable.initDatabase();
                callable.onCallBack();
            }
        });
    }
}
