package com.devs.kero.team7.data.repositories;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.devs.kero.team7.domain.Repository.AlarmManagerDataSource;
import com.devs.kero.team7.domain.entities.Task;

import java.util.Calendar;
import java.util.Date;

import io.reactivex.Completable;
import io.reactivex.functions.Action;

public class AlarmManagerRepository implements AlarmManagerDataSource {
    AlarmManager alarmManager ;
    Context context ;

    public AlarmManagerRepository(AlarmManager alarmManager, Context context) {
        this.alarmManager = alarmManager;
        this.context = context;
    }

    @Override
    public Completable startAlarm(final Task task,final Class<?> classe) {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                Calendar calendar = Calendar.getInstance();
                Date date= task.getDateTime();
                calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.set(Calendar.HOUR_OF_DAY, date.getHours());
                calendar.set(Calendar.MINUTE, date.getMinutes());
                Intent intent = new Intent(context,classe);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(context,(int)task.getTaskId(), intent, 0);
                alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(), pendingIntent );
            }
        });
  }

    @Override
    public Completable stopAlarm(Task task) {
   return Completable.fromAction(new Action() {
       @Override
       public void run() throws Exception {

       }
   });
    }
}
