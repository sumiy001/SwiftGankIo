package com.rdc.sumiy.swiftgankio.utils;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.rdc.sumiy.swiftgankio.GankioApplication;
import com.rdc.sumiy.swiftgankio.listener.VolleyOnErrorListener;
import com.rdc.sumiy.swiftgankio.listener.VolleyOnResponseListener;
import com.rdc.sumiy.swiftgankio.model.AndroidModel;
import com.rdc.sumiy.swiftgankio.model.ExpandModel;
import com.rdc.sumiy.swiftgankio.model.IOSModel;
import com.rdc.sumiy.swiftgankio.model.QianduanModel;
import com.rdc.sumiy.swiftgankio.model.VideoModel;
import com.rdc.sumiy.swiftgankio.model.XiaModel;
import com.rdc.sumiy.swiftgankio.myinterface.Callable;
import com.rdc.sumiy.swiftgankio.utils.refresh.Constant;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
/**
 * Created by sumiy on 2016/8/16.
 */
public class NetworkGetRecentData {
    public static ArrayList<String> imageUrls = new ArrayList<>();
    public static ArrayList<AndroidModel> androidModels = new ArrayList<>();
    public static ArrayList<IOSModel> iOSModels = new ArrayList<>();
    public static ArrayList<QianduanModel> qianduanModels = new ArrayList<>();
    public static ArrayList<VideoModel> videoModels = new ArrayList<>();
    public static ArrayList<ExpandModel> expandModels = new ArrayList<>();
    public static ArrayList<XiaModel> xiaModels = new ArrayList<>();
    private static final String TAG = "NetworkGetRecentData";
    private static RequestQueue mRequestQueue = Volley.newRequestQueue(GankioApplication.gankioApplication);
    public static void getCurrentData(final Callable callable) {
        JsonObjectRequest mJsonObjectRequest = new JsonObjectRequest(
                Constant.getHistoryUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            getData(callable, jsonObject.getJSONArray("results").getString(0));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new VolleyOnErrorListener(callable)
        );
        mRequestQueue.add(mJsonObjectRequest);
    }
    private static void getData(final Callable callable, String date) {
        String url = "http://gank.io/api/day/" + date.replaceAll("-", "/");
        JsonObjectRequest mJsonObjectRequest = new JsonObjectRequest(
                url, null,
                new VolleyOnResponseListener(VolleyOnResponseListener.State2Response, callable),
                new VolleyOnErrorListener(callable)
        );
        mRequestQueue.add(mJsonObjectRequest);
    }
    public  static void  clean(){
        imageUrls.clear();
        androidModels.clear();
        iOSModels.clear();
        videoModels.clear();
        qianduanModels.clear();
        expandModels.clear();
        xiaModels.clear();
    }
}
