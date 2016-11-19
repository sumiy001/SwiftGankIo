package com.rdc.sumiy.swiftgankio.listener;
import com.android.volley.Response;
import com.rdc.sumiy.swiftgankio.myinterface.Callable;
import com.rdc.sumiy.swiftgankio.utils.NetorkGetDataUtils;
import com.rdc.sumiy.swiftgankio.utils.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;
/**
 * Created by sumiy on 2016/8/12.
 */
public class VolleyOnResponseListener implements Response.Listener<JSONObject> {
    public final  static int AndroidModelResponse = 0;
    public final  static int FuliResponse = 1;
    public final  static int VideoModelResponse = 2;
    public final  static int iOSModelResponse = 3;
    public final  static int QianduanModelResponse = 4;
    public final  static int ExpandModelResponse = 5;
    public final  static int XiaModelResponse = 6;
    public final  static int State2Response = 7;
    private int sign;
    private Callable callable;
    public VolleyOnResponseListener(int sign,Callable callable) {
        this.callable = callable;
        this.sign = sign;
    }
    @Override
    public void onResponse(JSONObject jsonObject) {
        switch (sign) {
            case AndroidModelResponse:
                JsonParser.parserAndroidModel(jsonObject, NetorkGetDataUtils.androidModels);
                callable.onCallBack();
                break;
            case FuliResponse:
                JsonParser.parserImagerModel(jsonObject, NetorkGetDataUtils.imageUrls);
                callable.onCallBack();
                break;
            case VideoModelResponse:
                JsonParser.parserVideoModel(jsonObject, NetorkGetDataUtils.videoModels);
                callable.onCallBack();
                break;
            case iOSModelResponse:
                JsonParser.parseriOSModel(jsonObject, NetorkGetDataUtils.iOSModels);
                callable.onCallBack();
                break;
            case QianduanModelResponse:
                JsonParser.parserQianduanModel(jsonObject, NetorkGetDataUtils.qianduanModels);
                callable.onCallBack();
                break;
            case ExpandModelResponse:
                JsonParser.parserExpandModel(jsonObject, NetorkGetDataUtils.expandModels);
                callable.onCallBack();
                break;
            case XiaModelResponse:
                JsonParser.parserXiaModel(jsonObject, NetorkGetDataUtils.xiaModels);
                callable.onCallBack();
                break;
            case State2Response:
                JSONObject object = null;
                try {
                    object = jsonObject.getJSONObject("results");
                    JsonParser.parserCurrentData(object, callable, jsonObject.getJSONArray("category"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            default:
                break;
        }
    }
}
