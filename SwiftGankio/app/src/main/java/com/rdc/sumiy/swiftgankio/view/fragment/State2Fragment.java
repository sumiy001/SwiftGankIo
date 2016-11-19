package com.rdc.sumiy.swiftgankio.view.fragment;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rdc.sumiy.swiftgankio.R;
import com.rdc.sumiy.swiftgankio.myinterface.Callable;
import com.rdc.sumiy.swiftgankio.utils.BuilderTextView;
import com.rdc.sumiy.swiftgankio.utils.CallableContainer;
import com.rdc.sumiy.swiftgankio.utils.DeviceInfoUtils;
import com.rdc.sumiy.swiftgankio.utils.DpPxTransform;
import com.rdc.sumiy.swiftgankio.utils.NetworkGetRecentData;
import com.rdc.sumiy.swiftgankio.utils.refresh.Constant;
import com.rdc.sumiy.swiftgankio.utils.refresh.MyScrollOnRefreshListener;
import com.rdc.sumiy.swiftgankio.widget.ObservableScroll;
import com.squareup.picasso.Picasso;

/**
 * Created by sumiy on 2016/8/16.
 */
public class State2Fragment extends Fragment implements Callable {
    private static final String TAG = "State2Fragment";
    private static final int State2 = Constant.State2;
    private LinearLayout linearLayoutVideo;
    private LinearLayout linearLayoutAndroid;
    private LinearLayout linearLayoutiOS;
    private LinearLayout linearLayoutQianduan;
    private LinearLayout linearLayoutExpand;
    private LinearLayout linearLayoutXia;
    private View view;
    private ImageView imageView;
    private ObservableScroll observableScroll;
    private LinearLayout refreshLinearImage;
    private LinearLayout linearLayoutRefresh;
    private TextView refreshTextView;
    BuilderTextView.Builder titleBuilder;
    BuilderTextView.Builder contentBuilder;
    private Handler handler;
    private ImageView refreshImageView;
    private boolean once = true;
    private State2Fragment() {

    }

    public static State2Fragment getInstances() {
        return State2FragmentHolder.state2Fragment;
    }

    static class State2FragmentHolder {
        private static State2Fragment state2Fragment = new State2Fragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_state2, container, false);
        initUI();
        CallableContainer.addCallabble(State2, this);
        return view;
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void initUI() {
        handler = new Handler(Looper.getMainLooper());
        refreshImageView = (ImageView) view.findViewById(R.id.iv_refresh);
        linearLayoutRefresh = (LinearLayout) view.findViewById(R.id.layout_refresh);
        refreshLinearImage = (LinearLayout) view.findViewById(R.id.refreshLinearLayoutImage);
        refreshTextView = (TextView) view.findViewById(R.id.refreshTextview);
        observableScroll = (ObservableScroll) view.findViewById(R.id.scrollView1);
        MyScrollOnRefreshListener myScrollOnRefreshListener = new MyScrollOnRefreshListener(this, refreshImageView,getActivity());
        observableScroll.setScrollOnRefreshListener(myScrollOnRefreshListener);
        imageView = (ImageView) view.findViewById(R.id.fuli_state2);
        linearLayoutVideo = (LinearLayout) view.findViewById(R.id.line_state2_video);
        linearLayoutAndroid = (LinearLayout) view.findViewById(R.id.line_state2_android);
        linearLayoutiOS = (LinearLayout) view.findViewById(R.id.line_state2_ios);
        linearLayoutQianduan = (LinearLayout) view.findViewById(R.id.line_state2_qianduan);
        linearLayoutExpand = (LinearLayout) view.findViewById(R.id.line_state2_expand);
        linearLayoutXia = (LinearLayout) view.findViewById(R.id.line_state2_xia);
        observableScroll.setRefreshView(refreshLinearImage, refreshTextView, imageView);
        NetworkGetRecentData.getCurrentData(this);
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void initData() {
        initTextBuilder();
        initLinelayout();
        if (NetworkGetRecentData.imageUrls.size() != 0)
            Picasso.with(getActivity()).load(NetworkGetRecentData.imageUrls.get(0))
                    .resize(DeviceInfoUtils.getDeviceWidth(), DeviceInfoUtils.getDeviceHeight() / 2).into(imageView);
        if (NetworkGetRecentData.videoModels.size() != 0) {
            TextView titleView = titleBuilder.create();
            titleView.setText("休息视频");
            linearLayoutVideo.addView(titleView);
            for (int i = 0; i < NetworkGetRecentData.videoModels.size(); i++) {
                TextView textView = contentBuilder.create();
                textView.setText(NetworkGetRecentData.videoModels.get(i).getDesc());
                linearLayoutVideo.addView(textView);
            }
        }
        if (NetworkGetRecentData.androidModels.size() != 0) {
            TextView titleView = titleBuilder.create();
            titleView.setText("Android");
            linearLayoutAndroid.addView(titleView);
            for (int i = 0; i < NetworkGetRecentData.androidModels.size(); i++) {
                TextView textView = contentBuilder.create();
                textView.setText(NetworkGetRecentData.androidModels.get(i).getDesc());
                linearLayoutAndroid.addView(textView);
            }
        }
        if (NetworkGetRecentData.iOSModels.size() != 0) {
            TextView titleView = titleBuilder.create();
            titleView.setText("iOS");
            linearLayoutiOS.addView(titleView);
            for (int i = 0; i < NetworkGetRecentData.iOSModels.size(); i++) {
                TextView textView = contentBuilder.create();
                textView.setText(NetworkGetRecentData.iOSModels.get(i).getDesc());
                linearLayoutiOS.addView(textView);
            }
        }
        if (NetworkGetRecentData.qianduanModels.size() != 0) {
            TextView titleView = titleBuilder.create();
            titleView.setText("前端");
            linearLayoutQianduan.addView(titleView);
            for (int i = 0; i < NetworkGetRecentData.qianduanModels.size(); i++) {
                TextView textView = contentBuilder.create();
                textView.setText(NetworkGetRecentData.qianduanModels.get(i).getDesc());
                linearLayoutQianduan.addView(textView);
            }
        }
        if (NetworkGetRecentData.expandModels.size() != 0) {
            TextView titleView = titleBuilder.create();
            titleView.setText("拓展资源");
            linearLayoutExpand.addView(titleView);
            for (int i = 0; i < NetworkGetRecentData.expandModels.size(); i++) {
                TextView textView = contentBuilder.create();
                textView.setText(NetworkGetRecentData.expandModels.get(i).getDesc());
                linearLayoutExpand.addView(textView);
            }
        }
        if (NetworkGetRecentData.xiaModels.size() != 0) {
            TextView titleView = titleBuilder.create();
            titleView.setText("瞎推荐");
            linearLayoutXia.addView(titleView);
            for (int i = 0; i < NetworkGetRecentData.xiaModels.size(); i++) {
                TextView textView = contentBuilder.create();
                textView.setText(NetworkGetRecentData.xiaModels.get(i).getDesc());
                linearLayoutXia.addView(textView);
            }
        }
        linearLayoutRefresh.setVisibility(View.VISIBLE);
    }

    private void initLinelayout() {
        linearLayoutVideo.removeAllViews();
        linearLayoutAndroid.removeAllViews();
        linearLayoutiOS.removeAllViews();
        linearLayoutQianduan.removeAllViews();
        linearLayoutExpand.removeAllViews();
        linearLayoutXia.removeAllViews();

    }

    private void initTextBuilder() {
        titleBuilder = new BuilderTextView.Builder(getActivity());
        titleBuilder.setTextColor(getActivity().getResources().getColor(R.color.black))
                .setTextSize(22);
        contentBuilder = new BuilderTextView.Builder(getActivity());
    }

    @Override
    public void onCallBack() {
        if (once) {
            initData();
            once = false;
        } else {
            Log.e(TAG, "onCallBack: "+"刷新成功" );
            initData();
            refreshTextView.setText("刷新成功");
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    observableScroll.scrollBy(0, DpPxTransform.Dp2Px(getActivity(), 160));
                    refreshLinearImage.setVisibility(View.VISIBLE);
                }
            }, 500);
            refreshImageView.clearAnimation();
            refreshImageView.setVisibility(View.GONE);
        }
    }

    @Override
    public void initDatabase() {
    }
}
