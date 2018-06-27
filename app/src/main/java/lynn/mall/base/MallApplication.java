package lynn.mall.base;

import android.app.Application;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by zowee-laisc on 2018/6/27.
 */

public class MallApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initOkhttpClient();
    }

    private void initOkhttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS) //链接超时
                .readTimeout(10000L, TimeUnit.MILLISECONDS) //读取超时
                .build(); //其他配置

        OkHttpUtils.initClient(okHttpClient);
    }
}
