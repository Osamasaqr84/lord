package com.codesroots.hossam.lordApp.helper;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.ndk.CrashlyticsNdk;
import com.codesroots.hossam.lordApp.R;

import io.fabric.sdk.android.Fabric;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


public class MyApplication extends Application {

    public static final String TAG = MyApplication.class.getSimpleName();
    private static MyApplication mInstance;
    private RequestQueue mRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics(), new CrashlyticsNdk());
        mInstance = this;
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/JF-Flat-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

    }


    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public void setConnectivityListener(NetworkChangeReceiver.ConnectivityReceiverListener listener) {
        NetworkChangeReceiver.connectivityReceiverListener = listener;
    }
}

