package com.devs.kero.team7.domain.UseCases;

import com.devs.kero.team7.domain.Repository.TasksDataSource;
import com.devs.kero.team7.domain.entities.Task;
import com.devs.kero.team7.domain.executors.PostExecuteThread;
import com.devs.kero.team7.domain.executors.ThreadExecutor;

import javax.inject.Inject;

import io.reactivex.Completable;

public class UpdateUseCase extends  BaseCompletableUseCase<Task> {
    private TasksDataSource dataSource ;

    @Inject
    public UpdateUseCase(PostExecuteThread postExecuteThread, ThreadExecutor threadExecutor, TasksDataSource dataSource) {
        super(postExecuteThread, threadExecutor);
        this.dataSource = dataSource;

    }
    @Override
    public Completable getObservable(Task... tasks) {
        return dataSource.UpdateTask(tasks[0]);
    }
}
