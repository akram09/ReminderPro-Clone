package com.devs.kero.team7.domain.StettingUseCases;

import com.devs.kero.team7.domain.Repository.CategoriesDataSource;
import com.devs.kero.team7.domain.executors.PostExecuteThread;
import com.devs.kero.team7.domain.executors.ThreadExecutor;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

public abstract class UseCase<T, T1> {
    public CompositeDisposable disposables ;
    PostExecuteThread postExecuteThread;
    ThreadExecutor threadExecutor ;
    CategoriesDataSource dataSource ;

    public UseCase( PostExecuteThread postExecuteThread, ThreadExecutor threadExecutor, CategoriesDataSource dataSource) {
        this.postExecuteThread = postExecuteThread;
        this.threadExecutor = threadExecutor;
        disposables = new CompositeDisposable();
        this.dataSource= dataSource ;
    }
    public void dispose() {
        if (!disposables.isDisposed()) {
            disposables.dispose();
        }
    }
    public abstract  Completable getObservable(T t , T1 t1);

    public void execute(DisposableCompletableObserver observer, T strings, T1 Distinct){
        Completable observable = getObservable(strings, Distinct) .
                subscribeOn(Schedulers.from(threadExecutor)).observeOn(postExecuteThread.getScheduler())
                ;
        addDisposable(observable.subscribeWith(observer));
    }

    /**
     * Dispose from current {@link CompositeDisposable}.
     */
    private void addDisposable(Disposable disposable) {
        disposables.add(disposable);
    }

}
