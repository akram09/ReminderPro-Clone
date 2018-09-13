package com.devs.kero.team7.domain.StettingUseCases;

import com.devs.kero.team7.domain.Repository.DesignDataSource;
import com.devs.kero.team7.domain.UseCases.BaseCompletableUseCase;
import com.devs.kero.team7.domain.executors.PostExecuteThread;
import com.devs.kero.team7.domain.executors.ThreadExecutor;

import javax.inject.Inject;

import io.reactivex.Completable;

public class UpdateCurrentTheme extends BaseCompletableUseCase<Integer> {
    DesignDataSource dataSource ;
   @Inject
    public UpdateCurrentTheme(PostExecuteThread postExecuteThread, ThreadExecutor threadExecutor, DesignDataSource dataSource) {
        super(postExecuteThread, threadExecutor);
        this.dataSource = dataSource;
    }

    @Override
    public Completable getObservable(Integer... integers) {
        return dataSource.setCurrentTheme(integers[0]);
    }
}
