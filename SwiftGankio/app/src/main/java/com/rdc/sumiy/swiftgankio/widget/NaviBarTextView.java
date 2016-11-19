package com.rdc.sumiy.swiftgankio.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

import com.rdc.sumiy.swiftgankio.R;
/**
 * Created by sumiy on 2016/8/13.
 */
public class NaviBarTextView extends TextView {
    public static int WIDTH;
    private Paint paint;
    private float textWidth;
    public boolean isSelect = false;
    public int position;

    public NaviBarTextView(Context context) {
        super(context);
        paint = getPaint();
    }
    public NaviBarTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.NaviBarTextView);
        position = array.getInteger(R.styleable.NaviBarTextView_navibar_position, 0);
        array.recycle();
        paint = getPaint();
    }
    public NaviBarTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.NaviBarTextView);
        position = array.getInteger(R.styleable.NaviBarTextView_navibar_position, 0);
        array.recycle();
        paint = getPaint();
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public NaviBarTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.NaviBarTextView);
        position = array.getInteger(R.styleable.NaviBarTextView_navibar_position, 0);
        array.recycle();
        paint = getPaint();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        if (isSelect) {
            paint.setColor(getResources().getColor(R.color.black));
            paint.setStyle(Paint.Style.FILL);
            textWidth = paint.measureText((String) getText());
            canvas.drawRect((getMeasuredWidth() - textWidth) / 2,
                    getMeasuredHeight() - 8,
                    (getMeasuredWidth() - textWidth) / 2 + textWidth,
                    getHeight(),
                    paint
            );
            canvas.save();
            setTextColor(getResources().getColor(R.color.black));
        } else {
            setTextColor((getResources().getColor(R.color.write)));
        }
        super.onDraw(canvas);
    }
}
