package com.devs.kero.team7.domain.UseCases;

import com.devs.kero.team7.domain.Repository.AlarmManagerDataSource;
import com.devs.kero.team7.domain.Repository.TasksDataSource;
import com.devs.kero.team7.domain.executors.PostExecuteThread;
import com.devs.kero.team7.domain.executors.ThreadExecutor;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.functions.Action;

public class DeleteActiveTaskUseCase extends BaseCompletableUseCase {
    private TasksDataSource dataSource ;
    private  AlarmManagerDataSource alarmManagerDataSource;
   @Inject
    public DeleteActiveTaskUseCase(PostExecuteThread postExecuteThread, ThreadExecutor threadExecutor, TasksDataSource dataSource, AlarmManagerDataSource alarmManagerDataSource) {
        super(postExecuteThread, threadExecutor);
        this.dataSource = dataSource;
        this.alarmManagerDataSource =alarmManagerDataSource;
    }

    @Override
    public Completable getObservable(Object[] objects) {
        return dataSource.DeleteActiveTasks().andThen(Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
               //Todo
            }
        }));
    }
}
