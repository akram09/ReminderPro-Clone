package com.devs.kero.team7.data.Db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.ContentValues;
import android.content.Context;
import android.support.annotation.NonNull;

import com.devs.kero.team7.data.Db.dao.TaskDao;
import com.devs.kero.team7.data.dataModel.CategorieData;
import com.devs.kero.team7.data.dataModel.TaskData;

@Database(entities = TaskData.class, version = 1, exportSchema = false)
public abstract class ReminderDatabase  extends RoomDatabase {

    private volatile static ReminderDatabase myInctance ;

    public abstract TaskDao getTaskDao();


    public static  ReminderDatabase getDatabase(final Context context){
        if(myInctance==null){
            synchronized (ReminderDatabase.class){
                if(myInctance==null){
                    myInctance = Room.databaseBuilder(context, ReminderDatabase.class, "reminderDb.db")
//                            .addCallback(new Callback() {
//                                @Override
//                                public void onOpen(@NonNull SupportSQLiteDatabase db) {
//                                    super.onOpen(db);
////                                    ContentValues values = new ContentValues();
////                                    values.put("Title", "hello");
////                                    values.put("Description" , "description");
////                                    values.put("CategorieId" , 2);
////                                    values.put("isActive", true);
////                                    values.put("Date", "2018/08/25 14:22:00");
////                                    values.put("HasRepeat" ,false);
////                                    values.put("RepeatType", "Simple");
////                                    values.put("RepeatBody", "");
////                                    values.put("DateEnd", "2018/08/27 14:22:00");
////                                    values.put("AdvancedReminder", "");
////                                    db.insert("Task", OnConflictStrategy.REPLACE, values);
//                                }
//                            })
                            .build();
                }
            }
        }
        return myInctance ;
    }






}
