package com.devs.kero.team7.learningrxjava.di.modules;

import android.app.AlarmManager;
import android.content.Context;
import android.content.SharedPreferences;

import com.devs.kero.team7.data.Utils.JobExecutor;
import com.devs.kero.team7.data.repositories.AlarmManagerRepository;
import com.devs.kero.team7.data.repositories.CategorieRepository;
import com.devs.kero.team7.data.repositories.DesignRepository;
import com.devs.kero.team7.data.repositories.TaskRepository;
import com.devs.kero.team7.domain.Repository.AlarmManagerDataSource;
import com.devs.kero.team7.domain.Repository.CategoriesDataSource;
import com.devs.kero.team7.domain.Repository.DesignDataSource;
import com.devs.kero.team7.domain.Repository.TasksDataSource;
import com.devs.kero.team7.domain.executors.PostExecuteThread;
import com.devs.kero.team7.domain.executors.ThreadExecutor;
import com.devs.kero.team7.learningrxjava.Threads.UiThread;
import com.devs.kero.team7.learningrxjava.Utils.MyApp;
import com.devs.kero.team7.learningrxjava.services.AlarmReceiver;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
@Module
public class ApplicationModule {
    private MyApp app ;
    public static final String Shared = "com.devs.kero.team7.learningrxjava.di.modules.SharedPreferences";


    public ApplicationModule(MyApp app) {
        this.app = app;
    }

   @Singleton @Provides
    public Context provideContext(){
        return app ;
    }
    @Singleton @Provides
    public AlarmManager provideAlarmManager(Context context){
        return (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    }
    @Singleton @Provides
    public TasksDataSource provideRepository(TaskRepository repository){
        return repository;
    }
    @Singleton @Provides
    public PostExecuteThread provideThread(UiThread thread){
        return thread ;
    }
    @Singleton @Provides
    public ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor){
        return jobExecutor ;
    }
    @Provides @Singleton
    public SharedPreferences sharedPreferences (Context context){
        return context.getSharedPreferences(Shared, context.MODE_PRIVATE) ;
    }
    @Singleton @Provides
    public CategoriesDataSource provodeCategorieDataSource(SharedPreferences preferences){
        return new CategorieRepository(preferences);
    }
    @Singleton @Provides
    public DesignDataSource provideDataSource(SharedPreferences preferences){
        return new DesignRepository(preferences);
    }
    @Singleton @Provides
    public AlarmManagerDataSource provideAlarmManagerDataSource(Context context, AlarmManager alarmManager){
        return new AlarmManagerRepository(alarmManager, context , AlarmReceiver.class);
    }
}
