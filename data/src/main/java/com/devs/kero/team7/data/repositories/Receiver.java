package com.devs.kero.team7.data.repositories;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.devs.kero.team7.data.dataModel.TaskData;

public  class Receiver   extends BroadcastReceiver  {

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setAutoCancel(true).setDefaults(Notification.DEFAULT_ALL).setContentTitle("this is a notififcation").setContentText("text text ");
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build());
    }

}
