package com.devs.kero.team7.domain.Repository;

import com.devs.kero.team7.domain.entities.Task;

public interface AlarmManagerDataSource {

    void startAlarm(Task task);
    void stopAlarm(Task task);
}
