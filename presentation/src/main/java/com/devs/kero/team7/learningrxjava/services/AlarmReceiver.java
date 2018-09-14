package com.devs.kero.team7.learningrxjava.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.devs.kero.team7.learningrxjava.R;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        Notification notification=  builder.setContentTitle("hello")
                .setContentText("hello world")
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .build();
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);

    }
}
