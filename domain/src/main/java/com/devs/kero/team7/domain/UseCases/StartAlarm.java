package com.devs.kero.team7.domain.UseCases;

import com.devs.kero.team7.domain.Repository.AlarmManagerDataSource;
import com.devs.kero.team7.domain.entities.Task;
import com.devs.kero.team7.domain.executors.PostExecuteThread;
import com.devs.kero.team7.domain.executors.ThreadExecutor;

import javax.inject.Inject;

import io.reactivex.Completable;

public class StartAlarm extends BaseCompletableUseCase<Task> {
    AlarmManagerDataSource alarmManagerDataSource;
    @Inject
    public StartAlarm(PostExecuteThread postExecuteThread, ThreadExecutor threadExecutor, AlarmManagerDataSource alarmManagerDataSource) {
        super(postExecuteThread, threadExecutor);
        this.alarmManagerDataSource = alarmManagerDataSource;
    }

    @Override
    public Completable getObservable(Task... tasks) {
        return alarmManagerDataSource.startAlarm(tasks[0]);
    }
}
