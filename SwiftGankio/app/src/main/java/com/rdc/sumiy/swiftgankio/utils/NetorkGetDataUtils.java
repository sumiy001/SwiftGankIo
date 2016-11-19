package com.rdc.sumiy.swiftgankio.utils;
import android.net.Uri;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.rdc.sumiy.swiftgankio.GankioApplication;
import com.rdc.sumiy.swiftgankio.model.ExpandModel;
import com.rdc.sumiy.swiftgankio.model.IOSModel;
import com.rdc.sumiy.swiftgankio.model.QianduanModel;
import com.rdc.sumiy.swiftgankio.model.VideoModel;
import com.rdc.sumiy.swiftgankio.model.XiaModel;
import com.rdc.sumiy.swiftgankio.myinterface.Callable;
import com.rdc.sumiy.swiftgankio.listener.VolleyOnErrorListener;
import com.rdc.sumiy.swiftgankio.listener.VolleyOnResponseListener;
import com.rdc.sumiy.swiftgankio.model.AndroidModel;

import java.util.ArrayList;
/**
 * Created by sumiy on 2016/8/10.
 */
public class NetorkGetDataUtils {
    private static final String TAG = " NetorkGetDataUtils";
    private static final String HOST = "http://gank.io/api/data/";
    public static ArrayList<String> imageUrls = new ArrayList<>();
    public static ArrayList<AndroidModel> androidModels = new ArrayList<>();
    public static ArrayList<IOSModel> iOSModels = new ArrayList<>();
    public static ArrayList<QianduanModel> qianduanModels = new ArrayList<>();
    public static ArrayList<VideoModel> videoModels = new ArrayList<>();
    public static ArrayList<ExpandModel> expandModels = new ArrayList<>();
    public static ArrayList<XiaModel> xiaModels = new ArrayList<>();
    public static int imageCurrentPage = 1;
    public static int androidCurrentPage = 1;
    public static int videoCurrentPage = 1;
    public static int iOSCurrentPage = 1;
    public static int qianduanCurrentPage = 1;
    public static int expandCurrentPage = 1;
    public static int xiaCurrentPage = 1;
    private static RequestQueue mRequestQueue = Volley.newRequestQueue(GankioApplication.gankioApplication);
    public static void getAndroidModelList(final Callable callable) {
        String strUrl = HOST + "Android/20/" + androidCurrentPage;
        JsonObjectRequest mJsonObjectRequest = new JsonObjectRequest(
                strUrl, null,
                new VolleyOnResponseListener(VolleyOnResponseListener.AndroidModelResponse, callable),
                new VolleyOnErrorListener(callable)
        );
        mRequestQueue.add(mJsonObjectRequest);
    }
    public static void getiOSModelList(final Callable callable) {
        String strUrl = HOST + "iOS/20/" + iOSCurrentPage;
        JsonObjectRequest mJsonObjectRequest = new JsonObjectRequest(
                strUrl, null,
                new VolleyOnResponseListener(VolleyOnResponseListener.iOSModelResponse, callable),
                new VolleyOnErrorListener(callable)
        );
        mRequestQueue.add(mJsonObjectRequest);
    }
    public static void getQianduanModelList(final Callable callable) {
        String strUrl = HOST + Uri.encode("前端") + "/20/" + qianduanCurrentPage;
        JsonObjectRequest mJsonObjectRequest = new JsonObjectRequest(
                strUrl, null,
                new VolleyOnResponseListener(VolleyOnResponseListener.QianduanModelResponse, callable),
                new VolleyOnErrorListener(callable)
        );
        mRequestQueue.add(mJsonObjectRequest);
    }
    public static void getExpandModelList(final Callable callable) {
        String strUrl = HOST + Uri.encode("拓展资源") + "/20/" + expandCurrentPage;
        JsonObjectRequest mJsonObjectRequest = new JsonObjectRequest(
                strUrl, null,
                new VolleyOnResponseListener(VolleyOnResponseListener.ExpandModelResponse, callable),
                new VolleyOnErrorListener(callable)
        );
        mRequestQueue.add(mJsonObjectRequest);
    }
    public static void getXiaModelList(final Callable callable) {
        String strUrl = HOST + Uri.encode("瞎推荐") + "/20/" + xiaCurrentPage;
        JsonObjectRequest mJsonObjectRequest = new JsonObjectRequest(
                strUrl, null,
                new VolleyOnResponseListener(VolleyOnResponseListener.XiaModelResponse, callable),
                new VolleyOnErrorListener(callable)
        );
        mRequestQueue.add(mJsonObjectRequest);
    }
    public static void getBitmapUrlList(final Callable callable) {
        String strUrl = "http://gank.io/api/data/" + Uri.encode("福利") + "/20/" + imageCurrentPage;
        JsonObjectRequest mJsonObjectRequest = new JsonObjectRequest(
                strUrl, null,
                new VolleyOnResponseListener(VolleyOnResponseListener.FuliResponse, callable),
                new VolleyOnErrorListener(callable)
        );
        mRequestQueue.add(mJsonObjectRequest);
    }
    public static void getVideoList(final Callable callable) {
        String strUrl = "http://gank.io/api/data/" + Uri.encode("休息视频") + "/20/" + videoCurrentPage;
        JsonObjectRequest mJsonObjectRequest = new JsonObjectRequest(
                strUrl, null,
                new VolleyOnResponseListener(VolleyOnResponseListener.VideoModelResponse, callable),
                new VolleyOnErrorListener(callable)
        );
        mRequestQueue.add(mJsonObjectRequest);
    }
}