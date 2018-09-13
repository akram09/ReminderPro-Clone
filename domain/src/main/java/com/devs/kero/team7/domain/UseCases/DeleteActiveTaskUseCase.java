package com.devs.kero.team7.domain.UseCases;

import com.devs.kero.team7.domain.Repository.TasksDataSource;
import com.devs.kero.team7.domain.executors.PostExecuteThread;
import com.devs.kero.team7.domain.executors.ThreadExecutor;

import javax.inject.Inject;

import io.reactivex.Completable;

public class DeleteActiveTaskUseCase extends BaseCompletableUseCase {
    TasksDataSource dataSource ;
   @Inject
    public DeleteActiveTaskUseCase(PostExecuteThread postExecuteThread, ThreadExecutor threadExecutor, TasksDataSource dataSource) {
        super(postExecuteThread, threadExecutor);
        this.dataSource = dataSource;
    }

    @Override
    public Completable getObservable(Object[] objects) {
        return dataSource.DeleteActiveTasks();
    }
}
