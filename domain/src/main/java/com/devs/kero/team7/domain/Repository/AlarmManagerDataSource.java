package com.devs.kero.team7.domain.Repository;

import com.devs.kero.team7.domain.entities.Task;

import java.util.List;

import io.reactivex.Completable;

public interface AlarmManagerDataSource {

    Completable startAlarm(Task task);
    Completable stopAlarm(Task task);
    Completable stopAlarms(List<Task> tasks) ;
}
