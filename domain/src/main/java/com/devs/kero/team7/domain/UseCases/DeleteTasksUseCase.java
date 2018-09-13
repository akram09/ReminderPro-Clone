package com.devs.kero.team7.domain.UseCases;

import com.devs.kero.team7.domain.Repository.TasksDataSource;
import com.devs.kero.team7.domain.entities.Task;
import com.devs.kero.team7.domain.executors.PostExecuteThread;
import com.devs.kero.team7.domain.executors.ThreadExecutor;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

public class DeleteTasksUseCase  {

   TasksDataSource dataSource ;
   public CompositeDisposable disposables ;
    PostExecuteThread postExecuteThread;
    ThreadExecutor threadExecutor ;

    @Inject
    public DeleteTasksUseCase(TasksDataSource dataSource, PostExecuteThread postExecuteThread, ThreadExecutor threadExecutor) {
        this.dataSource = dataSource;
        this.postExecuteThread = postExecuteThread;
        this.threadExecutor = threadExecutor;
        disposables=  new CompositeDisposable();
    }

    public void execute(DisposableCompletableObserver observer, List<Task> tasks){
        Completable observable = getObservable(tasks).
                subscribeOn(Schedulers.from(threadExecutor)).observeOn(postExecuteThread.getScheduler());
        addDisposable(observable.subscribeWith(observer));


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

    public Completable getObservable(List<Task> lists) {
        return dataSource.DeletTasks(lists);
    }
}
