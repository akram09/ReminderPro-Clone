package com.devs.kero.team7.learningrxjava.di.component;

import android.app.AlarmManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.SharedPreferences;

import com.devs.kero.team7.domain.Repository.AlarmManagerDataSource;
import com.devs.kero.team7.domain.Repository.CategoriesDataSource;
import com.devs.kero.team7.domain.Repository.DesignDataSource;
import com.devs.kero.team7.domain.Repository.TasksDataSource;
import com.devs.kero.team7.domain.executors.PostExecuteThread;
import com.devs.kero.team7.domain.executors.ThreadExecutor;
import com.devs.kero.team7.learningrxjava.base.BaseActivity;
import com.devs.kero.team7.learningrxjava.di.modules.ApplicationModule;
import com.devs.kero.team7.learningrxjava.services.AlarmReceiver;

import javax.inject.Singleton;

import dagger.Component;
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
  void inject(BaseActivity activity);
  void inject(AlarmReceiver broadcastReceiver);

  Context context();

  SharedPreferences sharedpreference();
  PostExecuteThread postexecutionthread();
  ThreadExecutor threadexecutor();
  TasksDataSource taskdatasource();
  CategoriesDataSource categoriesdatasource();
  DesignDataSource designdatasource();
  AlarmManagerDataSource alarmmanagerdatasource();
  AlarmManager alarmmanager();



}
