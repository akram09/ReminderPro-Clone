package com.devs.kero.team7.domain.executors;

import io.reactivex.Scheduler;

public interface PostExecuteThread {
    Scheduler getScheduler();
}
