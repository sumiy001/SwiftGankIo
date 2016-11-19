package com.rdc.sumiy.swiftgankio.utils.refresh;

import com.rdc.sumiy.swiftgankio.model.VideoModel;
import com.rdc.sumiy.swiftgankio.view.fragment.AndroidFragment;
import com.rdc.sumiy.swiftgankio.view.fragment.ExpandFragment;
import com.rdc.sumiy.swiftgankio.view.fragment.FuliFragment;
import com.rdc.sumiy.swiftgankio.view.fragment.IOSFragment;
import com.rdc.sumiy.swiftgankio.view.fragment.QianduanFragment;
import com.rdc.sumiy.swiftgankio.view.fragment.VideoFragment;
import com.rdc.sumiy.swiftgankio.view.fragment.XiaFragment;

/**
 * Created by sumiy on 2016/8/16.
 */
public class ScrollToTop {
    public static void scroll(int pos) {
        switch (pos) {
            case Constant.ANDROID:
                AndroidFragment.mRecyclerView.smoothScrollToPosition(0);
                break;
            case Constant.FULI:
                FuliFragment.mRecyclerView.smoothScrollToPosition(0);
                break;
            case Constant.VIDEO:
                VideoFragment.mRecyclerView.smoothScrollToPosition(0);
                break;
            case Constant.IOS:
                IOSFragment.mRecyclerView.smoothScrollToPosition(0);
                break;
            case Constant.QIANDUAN:
                QianduanFragment.mRecyclerView.smoothScrollToPosition(0);
                break;
            case Constant.EXPAND:
                ExpandFragment.mRecyclerView.smoothScrollToPosition(0);
                break;
            case Constant.XIA_:
                XiaFragment.mRecyclerView.smoothScrollToPosition(0);
                break;
        }

    }
}
