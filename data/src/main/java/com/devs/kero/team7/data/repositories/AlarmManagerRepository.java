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

public class AlarmManagerRepository implements AlarmManagerDataSource {
    AlarmManager alarmManager ;
    Context context ;

    public AlarmManagerRepository(AlarmManager alarmManager, Context context) {
        this.alarmManager = alarmManager;
        this.context = context;
    }

    @Override
    public void startAlarm(Task task) {
        Calendar calendar = Calendar.getInstance();
        Date date= task.getDateTime();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 32);
//        calendar.set(date.getYear()+1900, date.getMonth()+1, date.getDate(), date.getHours(), date.getMinutes(), date.getSeconds());
//
        Intent intent = new Intent(context, Receiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,0, intent, 0);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(), AlarmManager.INTERVAL_FIFTEEN_MINUTES, pendingIntent );
    }

    @Override
    public void stopAlarm(Task task) {

    }
}
