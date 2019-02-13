package com.example.riya.notif_060219;

import android.os.Build;
import android.service.notification.NotificationListenerService;
import android.support.annotation.RequiresApi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.support.v4.content.LocalBroadcastManager;


@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
public class NotificationService extends NotificationListenerService {

    Context context;

    @Override

    public void onCreate() {

        super.onCreate();
        context = getApplicationContext();

    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override

    public void onNotificationPosted(StatusBarNotification sbn) {


        String pack = sbn.getPackageName();
        String ticker = "" + sbn.getNotification().tickerText;
        Bundle extras = sbn.getNotification().extras;
        String title = extras.getString("android.title");
        String text = "" + extras.getCharSequence("android.text");


        Intent msgrcv = new Intent("Msg");
        msgrcv.putExtra("package", pack);
        msgrcv.putExtra("ticker", ticker);
        msgrcv.putExtra("title", title);
        msgrcv.putExtra("text", text);


        LocalBroadcastManager.getInstance(context).sendBroadcast(msgrcv);


    }

    @Override

    public void onNotificationRemoved(StatusBarNotification sbn) {
        Log.i("Msg","Notification Removed");

    }}