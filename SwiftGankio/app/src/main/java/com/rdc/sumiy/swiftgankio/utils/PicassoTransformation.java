package com.rdc.sumiy.swiftgankio.utils;


import android.graphics.Bitmap;

import com.squareup.picasso.Transformation;

/**
 * Created by sumiy on 2016/8/12.
 */
public class PicassoTransformation implements Transformation {
    @Override
    public Bitmap transform(Bitmap source) {

        int targetWidth = DeviceInfoUtils.getDeviceWidth();

        if (source.getWidth() == 0) {
            return source;
        }
        if (source.getWidth() < targetWidth) {
            return source;
        } else {
            double aspectRatio = (double) source.getHeight() / (double) source.getWidth();
            int targetHeight = (int) (targetWidth * aspectRatio);
            if (targetHeight != 0 && targetWidth != 0) {
                Bitmap result = Bitmap.createScaledBitmap(source, targetWidth, targetHeight, false);
                if (result != source) {
                    source.recycle();
                }
                return result;
            } else {
                return source;
            }
        }
    }
    @Override
    public String key() {
        return "transformation" + " desiredWidth";
    }
}
