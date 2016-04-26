package me.next.golddemoproject;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;

import me.next.golddemoproject.constants.ConstantKey;
import me.next.golddemoproject.model.Entry;

/**
 * Created by NeXT on 16/4/25.
 * GoldDemoProject
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AVObject.registerSubclass(Entry.class);
        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(
                this,
                ConstantKey.APP_ID,
                ConstantKey.APP_KEY);
    }
}
