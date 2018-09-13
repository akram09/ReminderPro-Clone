package com.devs.kero.team7.domain.UseCases;

import com.devs.kero.team7.domain.executors.PostExecuteThread;
import com.devs.kero.team7.domain.executors.ThreadExecutor;

import java.text.ParseException;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseUseCase<T, Params>  {
    public CompositeDisposable disposables ;
    PostExecuteThread postExecuteThread;
    ThreadExecutor threadExecutor ;

    public BaseUseCase(PostExecuteThread postExecuteThread, ThreadExecutor threadExecutor) {
        this.postExecuteThread = postExecuteThread;
        this.threadExecutor = threadExecutor;
        disposables = new CompositeDisposable();
    }

    public abstract Observable<T> getObservable(Params... params) throws ParseException;


    public void execute(DisposableObserver<T> observer,Params...params){
        try {


            Observable<T> observable = getObservable(params).
                    subscribeOn(Schedulers.from(threadExecutor)).observeOn(postExecuteThread.getScheduler());
            addDisposable(observable.subscribeWith(observer));
        }catch (ParseException e){
            e.printStackTrace();
        }



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
