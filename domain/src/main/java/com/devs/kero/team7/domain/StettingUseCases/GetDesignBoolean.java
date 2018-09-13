package com.devs.kero.team7.domain.StettingUseCases;

import com.devs.kero.team7.domain.Repository.DesignDataSource;
import com.devs.kero.team7.domain.UseCases.BaseUseCase;
import com.devs.kero.team7.domain.executors.PostExecuteThread;
import com.devs.kero.team7.domain.executors.ThreadExecutor;

import java.text.ParseException;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetDesignBoolean extends BaseUseCase<List<Boolean>, Boolean> {

    private DesignDataSource designDataSource ;
    @Inject
    public GetDesignBoolean(PostExecuteThread postExecuteThread, ThreadExecutor threadExecutor, DesignDataSource designDataSource) {
        super(postExecuteThread, threadExecutor);
        this.designDataSource = designDataSource;
    }

    @Override
    public Observable<List<Boolean>> getObservable(Boolean... booleans) throws ParseException {
        return designDataSource.getBooleans(booleans[0].booleanValue());
    }
}
