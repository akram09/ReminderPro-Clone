package com.devs.kero.team7.domain.UseCases;

import com.devs.kero.team7.domain.Repository.TasksDataSource;
import com.devs.kero.team7.domain.entities.Task;
import com.devs.kero.team7.domain.executors.PostExecuteThread;
import com.devs.kero.team7.domain.executors.ThreadExecutor;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class getCompleteTasks extends BaseUseCase<List<Task>, Void> {
    private TasksDataSource dataSource ;
     @Inject
    public getCompleteTasks(PostExecuteThread postExecuteThread, ThreadExecutor threadExecutor, TasksDataSource dataSource) {
        super(postExecuteThread, threadExecutor);
        this.dataSource = dataSource;
    }

    @Override
    public Observable<List<Task>> getObservable(Void... voids) {
        return dataSource.getCompleteTasks();
    }
}
