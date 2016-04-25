package me.next.golddemoproject;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;

import me.next.golddemoproject.constants.ConstantKey;

/**
 * Created by NeXT on 16/4/25.
 * GoldDemoProject
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(
                this,
                ConstantKey.APP_ID,
                ConstantKey.APP_KEY);
    }
}
