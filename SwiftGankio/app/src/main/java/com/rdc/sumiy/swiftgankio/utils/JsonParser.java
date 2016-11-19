package com.rdc.sumiy.swiftgankio.utils;

import android.util.Log;

import com.rdc.sumiy.swiftgankio.model.AndroidModel;
import com.rdc.sumiy.swiftgankio.model.ExpandModel;
import com.rdc.sumiy.swiftgankio.model.IOSModel;
import com.rdc.sumiy.swiftgankio.model.QianduanModel;
import com.rdc.sumiy.swiftgankio.model.VideoModel;
import com.rdc.sumiy.swiftgankio.model.XiaModel;
import com.rdc.sumiy.swiftgankio.utils.factory.ArticleTitle;
import com.rdc.sumiy.swiftgankio.myinterface.Callable;
import com.rdc.sumiy.swiftgankio.utils.factory.ConcreteFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sumiy on 2016/8/12.
 */
public class JsonParser<T extends ArticleTitle> {
    private static Map<String, Boolean> categoryMap = new HashMap();
    private static final String TAG = "JsonParser";

    public static void parserAndroidModel(JSONObject jsonObject, ArrayList<AndroidModel> androidModels) {
        try {
            boolean isError = jsonObject.getBoolean("error");
            if (isError == false) {
                JSONArray jsonArray = jsonObject.getJSONArray("results");
                parserArticalArray(jsonArray, AndroidModel.class, androidModels);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void parseriOSModel(JSONObject jsonObject, ArrayList<IOSModel> iOSModels) {
        try {
            boolean isError = jsonObject.getBoolean("error");
            if (isError == false) {
                JSONArray jsonArray = jsonObject.getJSONArray("results");
                parserArticalArray(jsonArray, IOSModel.class, iOSModels);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void parserQianduanModel(JSONObject jsonObject, ArrayList<QianduanModel> qianduanModels) {
        try {
            boolean isError = jsonObject.getBoolean("error");
            if (isError == false) {
                JSONArray jsonArray = jsonObject.getJSONArray("results");
                parserArticalArray(jsonArray, QianduanModel.class, qianduanModels);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public static void parserExpandModel(JSONObject jsonObject, ArrayList<ExpandModel> expandModels) {
        try {
            boolean isError = jsonObject.getBoolean("error");
            if (isError == false) {
                JSONArray jsonArray = jsonObject.getJSONArray("results");
                parserArticalArray(jsonArray, ExpandModel.class, expandModels);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void parserXiaModel(JSONObject jsonObject, ArrayList<XiaModel> xiaModels) {
        try {
            boolean isError = jsonObject.getBoolean("error");
            if (isError == false) {
                JSONArray jsonArray = jsonObject.getJSONArray("results");
                parserArticalArray(jsonArray, XiaModel.class, xiaModels);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void parserVideoModel(JSONObject jsonObject, ArrayList<VideoModel> videoModels) {
        try {
            boolean isError = jsonObject.getBoolean("error");
            if (isError == false) {
                JSONArray jsonArray = jsonObject.getJSONArray("results");
                parserArticalArray(jsonArray, VideoModel.class, videoModels);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void parserImagerModel(JSONObject jsonObject, ArrayList<String> imageUrls) {
        try {
            boolean isError = jsonObject.getBoolean("error");
            if (isError == false) {
                JSONArray jsonArray = jsonObject.getJSONArray("results");
                for (int i = 0; i < jsonArray.length(); i++) {
                    imageUrls.add(jsonArray.getJSONObject(i).getString("url"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public static void parserCurrentData(JSONObject jsonObject, Callable callable, JSONArray category) {
        initCategoryMap();
        Log.e(TAG, "parserCurrentData: ");
        try {
            for (int i = 0; i < category.length(); i++) {
                categoryMap.put(category.getString(i), true);
            }
            if (categoryMap.get("福利")) {
                JSONArray fuliJsonObj = jsonObject.getJSONArray("福利");
                String imageUrl = fuliJsonObj.getJSONObject(0).getString("url");
                NetworkGetRecentData.imageUrls.add(imageUrl);
            }
            if (categoryMap.get("休息视频")) {
                JSONArray videoArray = jsonObject.getJSONArray("休息视频");
                parserArticalArray(videoArray, VideoModel.class, NetworkGetRecentData.videoModels);
            }
            if (categoryMap.get("Android")) {
                JSONArray androidArray = jsonObject.getJSONArray("Android");
                parserArticalArray(androidArray, AndroidModel.class, NetworkGetRecentData.androidModels);
            }
            if (categoryMap.get("iOS")) {
                JSONArray iOSArray = jsonObject.getJSONArray("iOS");
                parserArticalArray(iOSArray, IOSModel.class, NetworkGetRecentData.iOSModels);
            }
            if (categoryMap.get("前端")) {
                JSONArray qianduanArray = jsonObject.getJSONArray("前端");
                parserArticalArray(qianduanArray, QianduanModel.class, NetworkGetRecentData.qianduanModels);
            }
            if (categoryMap.get("拓展资源")) {
                JSONArray expandArray = jsonObject.getJSONArray("拓展资源");
                parserArticalArray(expandArray, ExpandModel.class, NetworkGetRecentData.expandModels);
            }
            if (categoryMap.get("瞎推荐")) {
                JSONArray xiaArray = jsonObject.getJSONArray("瞎推荐");
                parserArticalArray(xiaArray, XiaModel.class, NetworkGetRecentData.xiaModels);
            }
            callable.onCallBack();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static void initCategoryMap() {
        Log.e(TAG, "initCategoryMap: ");
        categoryMap.put("iOS", false);
        categoryMap.put("福利", false);
        categoryMap.put("Android", false);
        categoryMap.put("前端", false);
        categoryMap.put("瞎推荐", false);
        categoryMap.put("拓展资源", false);
        categoryMap.put("休息视频", false);
    }



    private static void parserArticalArray(JSONArray jsonArray, Class clz, ArrayList articleTitles) {
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                ConcreteFactory concreteFactory = ConcreteFactory.getInstances();
                ArticleTitle articleTitle = concreteFactory.createArticleTitle(clz);
                articleTitle.setUrl(jsonArray.getJSONObject(i).getString("url"));
                articleTitle.setDesc(jsonArray.getJSONObject(i).getString("desc"));
                articleTitle.setCreatedAt(jsonArray.getJSONObject(i).getString("createdAt"));
                articleTitle.setWho(jsonArray.getJSONObject(i).getString("who"));
                articleTitles.add(articleTitle);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
