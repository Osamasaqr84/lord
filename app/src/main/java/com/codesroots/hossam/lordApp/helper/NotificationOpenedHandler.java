package com.codesroots.hossam.lordApp.helper;

import android.content.Context;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;



public class NotificationOpenedHandler implements OneSignal.NotificationOpenedHandler{

     Context context;

    public NotificationOpenedHandler(Context context) {
        this.context = context;
    }

    ////////////////////Notification
    public void notification() {
        OneSignal.startInit(context)
                .setNotificationOpenedHandler(this)
                .init();

        OneSignal.sendTag("id", String.valueOf(PreferenceHelper.getUserId()));
        OneSignal.sendTag("user_group_id", String.valueOf(PreferenceHelper.getUSER_GROUP_ID()));
    }


    @Override
    public void notificationOpened(OSNotificationOpenResult result) {

    }
}
