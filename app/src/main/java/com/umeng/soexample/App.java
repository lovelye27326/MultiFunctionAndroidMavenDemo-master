package com.umeng.soexample;


import android.app.Application;

import com.tencent.tauth.Tencent;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.PushAgent;
import com.umeng.soexample.sp.SharedPreferencesHelper;

/**
 * Created by wangfei on 2018/1/23.
 */

public class App extends Application {

    SharedPreferencesHelper sharedPreferencesHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        sharedPreferencesHelper=new SharedPreferencesHelper(this,"umeng");

        //设置LOG开关，默认为false
        UMConfigure.setLogEnabled(true);
        //解决推送消息显示乱码的问题
        PushAgent.setup(this, "59892f08310c9307b60023d0", "669c30a9584623e70e8cd01b0381dcb4");
        //友盟预初始化
        UMConfigure.preInit(getApplicationContext(),"59892f08310c9307b60023d0","Umeng");

        /**
         * 打开app首次隐私协议授权，以及sdk初始化，判断逻辑请查看SplashTestActivity
         */
        //判断是否同意隐私协议，uminit为1时为已经同意，直接初始化umsdk
        if(sharedPreferencesHelper.getSharedPreference("uminit","").equals("1")){
            //友盟正式初始化
            UmInitConfig umInitConfig=new UmInitConfig();
            umInitConfig.UMinit(getApplicationContext());
            //QQ官方sdk授权
            Tencent.setIsPermissionGranted(true);
        }


    }
}
