package com.rdc.sumiy.swiftgankio;

import android.app.Application;
import android.content.Context;

/**
 * Created by sumiy on 2016/8/10.
 */
public class GankioApplication extends Application {

    public  static  GankioApplication gankioApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        gankioApplication = this;
    }
    public Context getApplicationContext(){
        return  gankioApplication;
    }

}
