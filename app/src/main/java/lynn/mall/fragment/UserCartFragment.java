package lynn.mall.fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import lynn.mall.base.BaseFragment;

/**
 * Created by zowee-laisc on 2018/6/26.
 */

public class UserCartFragment extends BaseFragment {
    private final static String TAG = UserCartFragment.class.getSimpleName();

    private TextView textView;

    @Override
    public View initView() {
        textView = new TextView(mContext);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        Log.e(TAG, "主页面的Fragment的UI被初始化了");
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        textView.setText("首页");
        Log.e(TAG, "主页面的Fragment的数据被初始化了");
    }
}
