package com.devs.kero.team7.domain.StettingUseCases;

import com.devs.kero.team7.domain.Repository.DesignDataSource;
import com.devs.kero.team7.domain.UseCases.BaseUseCase;
import com.devs.kero.team7.domain.executors.PostExecuteThread;
import com.devs.kero.team7.domain.executors.ThreadExecutor;

import java.text.ParseException;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetCurrentherme extends BaseUseCase<Integer , Void> {

    DesignDataSource designDataSource;
   @Inject
    public GetCurrentherme(PostExecuteThread postExecuteThread, ThreadExecutor threadExecutor, DesignDataSource designDataSource) {
        super(postExecuteThread, threadExecutor);
        this.designDataSource = designDataSource;
    }

    @Override
    public Observable<Integer> getObservable(Void... voids) throws ParseException {
        return designDataSource.getCurrentTheme();
    }
}
