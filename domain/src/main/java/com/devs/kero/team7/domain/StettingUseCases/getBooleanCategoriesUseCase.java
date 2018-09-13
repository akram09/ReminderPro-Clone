package com.devs.kero.team7.domain.StettingUseCases;

import com.devs.kero.team7.domain.Repository.CategoriesDataSource;
import com.devs.kero.team7.domain.UseCases.BaseUseCase;
import com.devs.kero.team7.domain.executors.PostExecuteThread;
import com.devs.kero.team7.domain.executors.ThreadExecutor;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class getBooleanCategoriesUseCase extends BaseUseCase<List<Boolean>, Boolean > {
CategoriesDataSource dataSource ;
    @Inject
    public getBooleanCategoriesUseCase(PostExecuteThread postExecuteThread, ThreadExecutor threadExecutor, CategoriesDataSource dataSource) {
        super(postExecuteThread, threadExecutor);
        this.dataSource = dataSource;
    }

    @Override
    public Observable<List<Boolean>> getObservable(Boolean... booleans) {
        return dataSource.getCategoriesVibration(booleans[0]);
    }
}
