package lynn.mall.fragment;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import lynn.mall.R;
import lynn.mall.base.BaseFragment;
import lynn.mall.bean.ResultBeanData;
import lynn.mall.utils.Constant;
import okhttp3.Call;

/**
 * Created by zowee-laisc on 2018/6/26.
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener {
    private final static String TAG = HomeFragment.class.getSimpleName();

    private TextView tv_search_home;
    private TextView tv_message_home;
    private RecyclerView rv_home;
    private ImageView ib_top;

    //返回的数据
    private ResultBeanData.ResultBean resultBean;

    @Override
    public View initView() {
        Log.e(TAG, "主页面的Fragment的UI被初始化了");
        View view = View.inflate(mContext, R.layout.fragment_home, null);
        //初始化布局控件
        tv_search_home = (TextView) view.findViewById(R.id.tv_search_home);
        tv_message_home = (TextView) view.findViewById(R.id.tv_message_home);
        rv_home = (RecyclerView) view.findViewById(R.id.rv_home);
        ib_top = (ImageView) view.findViewById(R.id.ib_top);

        //设置点击事件
        ib_top.setOnClickListener(this);
        tv_search_home.setOnClickListener(this);
        tv_message_home.setOnClickListener(this);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG, "主页面的Fragment的数据被初始化了");

        getDataFromNet();
    }

    private void getDataFromNet() {
        String url = Constant.HOME_URL;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {

                    /**
                     * 请求失败的时候回调
                     * @param call
                     * @param e
                     * @param id
                     */
                    @Override
                    public void onError(Call call, Exception e, int id) {

                        Log.e(TAG, "首页请求失败==" + e.getMessage());
                    }

                    /**
                     * 当请求成功的时候回调
                     * @param response 请求成功数据
                     * @param id
                     */
                    @Override
                    public void onResponse(String response, int id) {

                        Log.e(TAG, "首页请求成功==" + response);
                        //解析数据
                        processData(response);
                    }
                });
    }

    private void processData(String json) {

        //使用FastJson去解析Json数据，将json字符串转换成一个ResultBeanData对象
        ResultBeanData resultBeanData = JSON.parseObject(json, ResultBeanData.class);
        resultBean = resultBeanData.getResult();
        Log.e(TAG, "解析成功==" + resultBean.getHot_info().get(0).getName());
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_top: //置顶的监听
                rv_home.scrollToPosition(0);
                break;
            case R.id.tv_search_home:  //搜索的监听
                Toast.makeText(mContext, "搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_message_home: //消息监听
                Toast.makeText(mContext, "进入消息中心", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
