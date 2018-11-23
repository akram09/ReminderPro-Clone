package com.devs.kero.team7.learningrxjava.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.devs.kero.team7.data.mapper.CategorieToInt;
import com.devs.kero.team7.data.repositories.AlarmManagerRepository;
import com.devs.kero.team7.domain.UseCases.BaseCompletableUseCase;
import com.devs.kero.team7.domain.UseCases.CompleteTaskUseCase;
import com.devs.kero.team7.domain.UseCases.StartAlarm;
import com.devs.kero.team7.domain.entities.Task;
import com.devs.kero.team7.learningrxjava.R;
import com.devs.kero.team7.learningrxjava.Utils.MyApp;
import com.devs.kero.team7.learningrxjava.ui.activities.DialogActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.inject.Inject;

import io.reactivex.CompletableObserver;
import io.reactivex.observers.DisposableCompletableObserver;

import static com.devs.kero.team7.data.Utils.StaticThings.IS_THERE_DATA_IN_INTENT;
import static com.devs.kero.team7.data.Utils.StaticThings.TASK_ADVANCED_REMINDER;
import static com.devs.kero.team7.data.Utils.StaticThings.TASK_CATEGORIE;
import static com.devs.kero.team7.data.Utils.StaticThings.TASK_DATE_TIME;
import static com.devs.kero.team7.data.Utils.StaticThings.TASK_DESCRIPTION;
import static com.devs.kero.team7.data.Utils.StaticThings.TASK_END_DATE;
import static com.devs.kero.team7.data.Utils.StaticThings.TASK_HASREPEAT;
import static com.devs.kero.team7.data.Utils.StaticThings.TASK_ID;
import static com.devs.kero.team7.data.Utils.StaticThings.TASK_Name;
import static com.devs.kero.team7.data.Utils.StaticThings.TASK_REAPEAT_TYPE;
import static com.devs.kero.team7.data.Utils.StaticThings.TASK_REPEAT_BODY;

public class AlarmReceiver extends BroadcastReceiver {
    @Inject
    StartAlarm startAlarm;
    Context contexte ;
    @Override
    public void onReceive(Context context, Intent intent) {
        ((MyApp)context.getApplicationContext())
                .getApplicationComponent().inject(this);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        contexte = context ;
        Bundle bundle = intent.getBundleExtra("BUNDLE_RECEIVER");
        if(bundle.getBoolean(IS_THERE_DATA_IN_INTENT)){
            Task task = null;
            try {
                task= new Task(bundle.getLong(TASK_ID), bundle.getString(TASK_Name), bundle.getString(TASK_DESCRIPTION),
                        CategorieToInt.to(bundle.getInt(TASK_CATEGORIE)),false , dateFormat.parse(bundle.getString(TASK_DATE_TIME)), bundle.getBoolean(TASK_HASREPEAT)
                        , bundle.getString(TASK_REAPEAT_TYPE)
                        , bundle.getString(TASK_REPEAT_BODY),dateFormat.parse(bundle.getString(TASK_END_DATE)), bundle.getString(TASK_ADVANCED_REMINDER) );
                Log.d("AlarmManager", "onReceive: "+String.valueOf(task.getDateTime().getMonth()));
                task.getDateTime().setMonth(task.getDateTime().getMonth()+2);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Log.d("AlarmManager", "onReceive: "+task.getDateTime().toString());
//            startAlarm.execute(new
// Observer(), task);
        }else {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
            Notification notification=  builder.setContentTitle("hello")
                    .setContentText("not null bitch")
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setAutoCancel(true).build();
            context.startActivity(new Intent(context, DialogActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(1, notification);
        }
        }
        class Observer extends DisposableCompletableObserver{
            @Override
            public void onComplete() {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(contexte);
                Notification notification=  builder.setContentTitle("hello")
                        .setContentText("not null bitch")
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setAutoCancel(true).build();
                contexte.startActivity(new Intent(contexte, DialogActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                NotificationManager notificationManager = (NotificationManager) contexte.getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(1, notification);
            }

            @Override
            public void onError(Throwable e) {

            }
        }
}
