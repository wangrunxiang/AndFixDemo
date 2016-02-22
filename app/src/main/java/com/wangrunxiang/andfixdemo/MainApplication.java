package com.wangrunxiang.andfixdemo;

import android.app.Application;
import android.os.Environment;
import android.util.Log;

import com.alipay.euler.andfix.patch.PatchManager;

import java.io.IOException;

/**
 * Created by kimmy on 2015/12/9.
 */
public class MainApplication extends Application {

    public PatchManager mPatchManager;

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化patch管理类
        mPatchManager = new PatchManager(this);
        // 初始化patch版本
        mPatchManager.init("1.0");
        // 加载已经添加到PatchManager中的patch
        mPatchManager.loadPatch();
    }
}
