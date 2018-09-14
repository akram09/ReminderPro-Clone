package com.devs.kero.team7.domain.Repository;

import com.devs.kero.team7.domain.entities.Task;

import io.reactivex.Completable;

public interface AlarmManagerDataSource {

    Completable startAlarm(Task task, Class<?> classe);
    Completable stopAlarm(Task task);
}
