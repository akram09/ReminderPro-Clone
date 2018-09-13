package com.devs.kero.team7.learningrxjava.di.component;

import android.app.Activity;

import com.devs.kero.team7.learningrxjava.di.PerActivity;
import com.devs.kero.team7.learningrxjava.di.modules.ActivityModule;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
interface ActivityComponent {
    //Exposed to sub-graphs.

    Activity activity();
}