package com.rdc.sumiy.swiftgankio.utils;

import com.rdc.sumiy.swiftgankio.myinterface.Callable;
import com.rdc.sumiy.swiftgankio.utils.refresh.Constant;

/**
 * Created by sumiy on 2016/8/13.
 */
public class Load {
    private static final String TAG = "Load";
    private static final int ANDROID_LOAD = Constant.ANDROID;
    private static final int FULI_LOAD = Constant.FULI;
    private static final int VIDEO_LOAD = Constant.VIDEO;
    private static final int IOS_LOAD = Constant.IOS;
    private static final int QIANDUAN_LOAD = Constant.QIANDUAN;
    private static final int EXPAND_LOAD = Constant.EXPAND;
    private static final int XIA_LOAD = Constant.XIA_;
    public static void load(int sign, Callable callable) {
        switch (sign) {
            case ANDROID_LOAD:
                loadAndroids(callable);
                break;
            case FULI_LOAD:
                loadFuli(callable);
                break;
            case VIDEO_LOAD:
                loadVideo(callable);
                break;
            case IOS_LOAD:
                loadiOS(callable);
                break;
            case QIANDUAN_LOAD:
                loadQianduan(callable);
                break;
            case EXPAND_LOAD:
                loadExpands(callable);
                break;
            case XIA_LOAD:
                loadXia(callable);
                break;
        }
    }

    private static void loadFuli(Callable callable) {
        NetorkGetDataUtils.imageCurrentPage++;
        NetorkGetDataUtils.getBitmapUrlList(callable);
    }

    private static void loadAndroids(Callable callable) {
        NetorkGetDataUtils.androidCurrentPage++;
        NetorkGetDataUtils.getAndroidModelList(callable);
    }

    private static void loadVideo(Callable callable) {
        NetorkGetDataUtils.videoCurrentPage++;
        NetorkGetDataUtils.getVideoList(callable);
    }

    private static void loadiOS(Callable callable) {
        NetorkGetDataUtils.iOSCurrentPage++;
        NetorkGetDataUtils.getiOSModelList(callable);
    }

    private static void loadQianduan(Callable callable) {
        NetorkGetDataUtils.qianduanCurrentPage++;
        NetorkGetDataUtils.getQianduanModelList(callable);
    }

    private static void loadExpands(Callable callable) {
        NetorkGetDataUtils.expandCurrentPage++;
        NetorkGetDataUtils.getExpandModelList(callable);
    }

    private static void loadXia(Callable callable) {
        NetorkGetDataUtils.xiaCurrentPage++;
        NetorkGetDataUtils.getXiaModelList(callable);
    }
}
