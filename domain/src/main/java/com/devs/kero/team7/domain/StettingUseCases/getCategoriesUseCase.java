package com.devs.kero.team7.domain.StettingUseCases;

import com.devs.kero.team7.domain.Repository.CategoriesDataSource;
import com.devs.kero.team7.domain.UseCases.BaseUseCase;
import com.devs.kero.team7.domain.executors.PostExecuteThread;
import com.devs.kero.team7.domain.executors.ThreadExecutor;

import java.text.ParseException;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class getCategoriesUseCase extends BaseUseCase<List<String>, Integer> {
    CategoriesDataSource dataSource ;
    @Inject
    public getCategoriesUseCase(PostExecuteThread postExecuteThread, ThreadExecutor threadExecutor, CategoriesDataSource dataSource) {
        super(postExecuteThread, threadExecutor);
        this.dataSource = dataSource;
    }

    @Override
    public Observable<List<String>> getObservable(Integer... integers) throws ParseException {
        return dataSource
                .getCategoriesString(integers[0]);

    }
}
