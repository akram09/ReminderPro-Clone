package com.devs.kero.team7.domain.UseCases;

import com.devs.kero.team7.domain.Repository.AlarmManagerDataSource;
import com.devs.kero.team7.domain.Repository.TasksDataSource;
import com.devs.kero.team7.domain.entities.Task;
import com.devs.kero.team7.domain.executors.PostExecuteThread;
import com.devs.kero.team7.domain.executors.ThreadExecutor;

import java.awt.event.ComponentListener;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

public class AddTaskUseCase  {
    private TasksDataSource dataSource ;
    private AlarmManagerDataSource alarmManagerDataSource;
    private CompositeDisposable disposables ;
    private PostExecuteThread postExecuteThread;
    private ThreadExecutor threadExecutor ;

    @Inject
    public AddTaskUseCase(PostExecuteThread postExecuteThread, ThreadExecutor threadExecutor, TasksDataSource dataSource, AlarmManagerDataSource alarmManagerDataSource) {
        this.postExecuteThread= postExecuteThread;
        this.threadExecutor = threadExecutor;
        disposables = new CompositeDisposable();
        this.dataSource = dataSource;
        this.alarmManagerDataSource = alarmManagerDataSource;
    }


   public void execute(DisposableCompletableObserver disposableCompletableObserver, Task task , Class<?> classe){
       Completable completable = dataSource.AddTask(task).andThen(alarmManagerDataSource.startAlarm(task, classe))
               .subscribeOn(Schedulers.from(threadExecutor)).observeOn(postExecuteThread.getScheduler());
       addDisposable(completable.subscribeWith(disposableCompletableObserver));

   }
    public void dispose() {
        if (!disposables.isDisposed()) {
            disposables.dispose();
        }
    }

    /**
     * Dispose from current {@link CompositeDisposable}.
     */
    private void addDisposable(Disposable disposable) {
        disposables.add(disposable);
    }

}
