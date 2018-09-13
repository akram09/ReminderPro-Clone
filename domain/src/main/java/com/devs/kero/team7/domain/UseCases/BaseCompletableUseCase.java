package com.devs.kero.team7.domain.UseCases;

import com.devs.kero.team7.domain.executors.PostExecuteThread;
import com.devs.kero.team7.domain.executors.ThreadExecutor;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseCompletableUseCase<Params> {
    public CompositeDisposable disposables ;
    PostExecuteThread postExecuteThread;
    ThreadExecutor threadExecutor ;

    public BaseCompletableUseCase(PostExecuteThread postExecuteThread, ThreadExecutor threadExecutor) {
        this.postExecuteThread = postExecuteThread;
        this.threadExecutor = threadExecutor;
        disposables = new CompositeDisposable();
    }

    public abstract Completable getObservable(Params... params);


    public void execute(DisposableCompletableObserver observer, Params...params){
        Completable observable = getObservable(params).
                subscribeOn(Schedulers.from(threadExecutor)).observeOn(postExecuteThread.getScheduler())
                ;
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

}
