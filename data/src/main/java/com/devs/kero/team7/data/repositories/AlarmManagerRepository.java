package com.devs.kero.team7.data.repositories;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.devs.kero.team7.data.R;
import com.devs.kero.team7.data.dataModel.TaskData;
import com.devs.kero.team7.data.mapper.CategorieToInt;
import com.devs.kero.team7.domain.Repository.AlarmManagerDataSource;
import com.devs.kero.team7.domain.entities.Categorie;
import com.devs.kero.team7.domain.entities.Task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.functions.Action;

import static android.app.AlarmManager.INTERVAL_DAY;
import static android.app.AlarmManager.INTERVAL_HALF_DAY;
import static android.content.ContentValues.TAG;
import static com.devs.kero.team7.data.Utils.StaticThings.IS_THERE_DATA_IN_INTENT;
import static com.devs.kero.team7.data.Utils.StaticThings.REPEAT_BODY;
import static com.devs.kero.team7.data.Utils.StaticThings.REPEAT_TYPE;
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

public class AlarmManagerRepository implements AlarmManagerDataSource {
    AlarmManager alarmManager ;
    Context context ;
    Class<?> classe ;

    public AlarmManagerRepository(AlarmManager alarmManager, Context context, Class<?> classe) {
        this.alarmManager = alarmManager;
        this.context = context;
        this.classe = classe;
    }

    @Override
    public Completable startAlarm(final Task task) {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                Calendar calendar = Calendar.getInstance();
                Date date= task.getDateTime();
                calendar.setTimeInMillis(System.currentTimeMillis());
//                calendar.set(Calendar.YEAR, date.getYear());
                calendar.set(Calendar.MONTH, date.getMonth());
                calendar.set(Calendar.DAY_OF_MONTH, date.getDate());
                calendar.set(Calendar.HOUR_OF_DAY, date.getHours());
                calendar.set(Calendar.MINUTE, date.getMinutes());
                calendar.set(Calendar.SECOND, 0);
                Intent intent = new Intent(context,classe);
                if(task.getHasRepeat()){
                    long intervall =0;
                    if(task.getRepeatType().equals("Simple")){
                        if(task.getRepeatBody().equals("Every Hour")){
                            intervall = AlarmManager.INTERVAL_HOUR;
                        }else if (task.getRepeatBody().equals("Every Day")){
                            intervall = INTERVAL_DAY;
                        }else if (task.getRepeatBody().equals("Every Week")){
                            intervall =7* AlarmManager.INTERVAL_DAY;
                        }else if(task.getRepeatBody().equals("Every Year")){
                            intervall = AlarmManager.INTERVAL_DAY*365;
                        }
                        if(task.getRepeatBody().equals("Every Month")){
                            Bundle bundle = convertIt(task);
                            bundle.putBoolean(IS_THERE_DATA_IN_INTENT, true);
                            intent.putExtra("BUNDLE_RECEIVER", bundle);
                            PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                                    (int)task.getTaskId()*10 , intent , 0);
                            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),pendingIntent);
                        }else {
                            PendingIntent pendingIntent = PendingIntent.getBroadcast(context,(int) task.getTaskId()*10, intent, 0);
                            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),intervall, pendingIntent );
                        }
                    }else if (task.getRepeatType().equals("SelectedWeekDays")){
                        String[] strings = context.getResources().getStringArray(R.array.SelectedWeekDays);
                        for (int i = 0; i <task.getRepeatBody().length() ; i++) {

                        }
                    }else if(task.getRepeatType().equals("Custom1")){
                        boolean isThreeNumber = task.getRepeatBody().charAt(0)=='e';
                           String Text  = isThreeNumber?task.getRepeatBody().substring(3)
                                   :task.getRepeatBody().substring(2);
                           long intervalel=0 ;
                           if (Text.equals("Hours")){
                            intervalel = AlarmManager.INTERVAL_HOUR ;
                           }else if (Text.equals("Days")){
                            intervalel = AlarmManager.INTERVAL_DAY ;
                           }else if(Text.equals("Weeks")){
                               intervalel = AlarmManager.INTERVAL_DAY*7 ;

                           }else if(Text.equals("Months")){
                               //Todo

                           }else if(Text.equals("Years")){
                               intervalel = AlarmManager.INTERVAL_DAY*365 ;
                           }
                            PendingIntent pendingIntent = PendingIntent.getBroadcast(context,(int) task.getTaskId()*10, intent, 0);
                            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),isThreeNumber?100:Integer
                                    .valueOf(task.getRepeatBody().substring(0, 1))
                                    *intervalel, pendingIntent );
                    }
                }else {

                }

            }
        });
  }

    @Override
    public Completable stopAlarms(final List<Task> tasks) {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                for (Task task: tasks
                     ) {
                    stopAlarm(task);
                }
            }
        });
    }

    @Override
    public Completable stopAlarm(final Task task) {
   return Completable.fromAction(new Action() {
       @Override
       public void run() throws Exception {
           Intent intent = new Intent(context,classe);
           PendingIntent pendingIntent = PendingIntent.getBroadcast(context,(int)task.getTaskId()*10, intent, 0);
           alarmManager.cancel(pendingIntent);
       }
   });

    }
    private Bundle convertIt(Task task){
        Bundle bundle  = new Bundle();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String Date = dateFormat.format(task.getDateTime());
        String EndDate = dateFormat.format(task.getEndDate());
        bundle.putString(TASK_Name , task.getTaskTitle());
        bundle.putString(TASK_DESCRIPTION, task.getTaskDescripion());
        bundle.putString(TASK_ADVANCED_REMINDER, task.getAdvancedRemind());
        bundle.putString(TASK_DATE_TIME, Date);
        bundle.putString(TASK_END_DATE, EndDate);
        bundle.putString(TASK_REAPEAT_TYPE, task.getRepeatType());
        bundle.putString(TASK_REPEAT_BODY, task.getRepeatBody());
        bundle.putBoolean(TASK_HASREPEAT, task.getHasRepeat());
        bundle.putLong(TASK_ID, task.getTaskId());
        bundle.putInt(TASK_CATEGORIE, CategorieToInt.from(task.getCategorie()));
        return bundle;
    }
}
