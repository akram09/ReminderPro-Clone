package com.devs.kero.team7.learningrxjava.Utils;

import android.app.Application;

import com.devs.kero.team7.learningrxjava.di.component.ApplicationComponent;
import com.devs.kero.team7.learningrxjava.di.component.DaggerApplicationComponent;
import com.devs.kero.team7.learningrxjava.di.modules.ApplicationModule;

public class MyApp extends Application {
    private ApplicationComponent applicationComponent  ;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
    }
    public ApplicationComponent getApplicationComponent(){
        return applicationComponent ;
    }
}
