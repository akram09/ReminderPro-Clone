package com.devs.kero.team7.domain.UseCases;

import com.devs.kero.team7.domain.Repository.AlarmManagerDataSource;
import com.devs.kero.team7.domain.Repository.TasksDataSource;
import com.devs.kero.team7.domain.entities.Task;
import com.devs.kero.team7.domain.executors.PostExecuteThread;
import com.devs.kero.team7.domain.executors.ThreadExecutor;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.functions.Action;

public class UpdateUseCase extends  BaseCompletableUseCase<Task> {
    private TasksDataSource dataSource ;
    private AlarmManagerDataSource alarmManagerDataSource ;

    @Inject
    public UpdateUseCase(PostExecuteThread postExecuteThread, ThreadExecutor threadExecutor, TasksDataSource dataSource, AlarmManagerDataSource alarmManagerDataSource) {
        super(postExecuteThread, threadExecutor);
        this.dataSource = dataSource;
        this.alarmManagerDataSource = alarmManagerDataSource;

    }
    @Override
    public Completable getObservable( final Task... tasks) {
        return dataSource.UpdateTask(tasks[0]).andThen(alarmManagerDataSource.stopAlarm(tasks[0]).andThen(alarmManagerDataSource
                .startAlarm(tasks[0]))
        );
    }
}
