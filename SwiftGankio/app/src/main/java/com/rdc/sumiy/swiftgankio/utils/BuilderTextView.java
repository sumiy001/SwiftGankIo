package com.rdc.sumiy.swiftgankio.utils;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rdc.sumiy.swiftgankio.R;

/**
 * Created by sumiy on 2016/8/16.
 */
public class BuilderTextView {
    public static class Builder {
        private ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        private Context context;
        private int padding;
        private int color;
        private int textSize;
        public Builder setLayoutParams(ViewGroup.LayoutParams layoutParams) {
            this.layoutParams = layoutParams;
            return this;
        }

        public Builder setPadding(int padding) {
            this.padding = padding;
            return this;
        }

        public Builder setTextSize(int textSize) {
            this.textSize = textSize;
            return this;
        }

        public Builder setTextColor(int color) {
            this.color = color;
            return this;

        }

        public Builder(Context context) {
            this.context = context;
            padding = 8;
            color = context.getResources().getColor(R.color.grey);
            textSize = 16;
        }

        public TextView create() {
            TextView textView = new TextView(context);
            textView.setLayoutParams(layoutParams);
            textView.setPadding(padding, padding, padding, padding);
            textView.setTextColor(color);
            textView.setTextSize(textSize);
            return textView;
        }
    }
}
