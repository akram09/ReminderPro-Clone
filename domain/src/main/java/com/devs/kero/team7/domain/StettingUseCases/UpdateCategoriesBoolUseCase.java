package com.devs.kero.team7.domain.StettingUseCases;

import com.devs.kero.team7.domain.Repository.CategoriesDataSource;
import com.devs.kero.team7.domain.executors.PostExecuteThread;
import com.devs.kero.team7.domain.executors.ThreadExecutor;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

public class UpdateCategoriesBoolUseCase extends UseCase<List<Boolean>, Boolean > {

    @Inject
    public UpdateCategoriesBoolUseCase(PostExecuteThread postExecuteThread, ThreadExecutor threadExecutor, CategoriesDataSource dataSource) {
        super(postExecuteThread, threadExecutor, dataSource);
    }

    @Override
    public Completable getObservable(List<Boolean> booleans, Boolean aBoolean) {
        return dataSource.UpdateCategoriesBool(booleans, aBoolean);
    }

}
