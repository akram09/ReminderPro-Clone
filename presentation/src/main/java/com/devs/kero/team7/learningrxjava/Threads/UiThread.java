package com.devs.kero.team7.learningrxjava.Threads;

import com.devs.kero.team7.domain.executors.PostExecuteThread;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UiThread implements PostExecuteThread {
    @Inject
    public UiThread() {
    }

    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
