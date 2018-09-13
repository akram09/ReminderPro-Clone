package com.devs.kero.team7.domain.StettingUseCases;

import com.devs.kero.team7.domain.Repository.CategoriesDataSource;
import com.devs.kero.team7.domain.Repository.DesignDataSource;
import com.devs.kero.team7.domain.UseCases.BaseCompletableUseCase;
import com.devs.kero.team7.domain.executors.PostExecuteThread;
import com.devs.kero.team7.domain.executors.ThreadExecutor;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

public class UpdateDesignBoolean {
    private  CompositeDisposable disposables ;
    private PostExecuteThread postExecuteThread;
    private  ThreadExecutor threadExecutor ;
    private DesignDataSource dataSource ;
    @Inject
    public UpdateDesignBoolean (DesignDataSource dataSource, PostExecuteThread postExecuteThread, ThreadExecutor threadExecutor) {
        this.postExecuteThread = postExecuteThread;
        this.threadExecutor = threadExecutor;
        disposables = new CompositeDisposable();
        this.dataSource= dataSource ;
    }




    public void execute(DisposableCompletableObserver observer, List<Boolean> booleans , boolean isDatePicker){
        Completable observable = dataSource.setBooleans(booleans, Boolean.valueOf(isDatePicker)).
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
