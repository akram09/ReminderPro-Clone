package com.devs.kero.team7.learningrxjava.base;

import com.devs.kero.team7.domain.UseCases.BaseUseCase;

import io.reactivex.Completable;

public class Presenter<I extends BaseUseCase, V extends  BaseView> implements BasePresenter   {
    private  V mMvpView;
    private I mMvpInteractor;

    public Presenter(I mMvpInteractor) {
        this.mMvpInteractor = mMvpInteractor;
        start();
    }


    public V getmMvpView() {
        return mMvpView;
    }

    public void setmMvpView(V mMvpView) {
        this.mMvpView = mMvpView;
    }

    public I getmMvpInteractor() {
        return mMvpInteractor;
    }

    public void setmMvpInteractor(I mMvpInteractor) {
        this.mMvpInteractor = mMvpInteractor;
    }

    @Override
    public void start() {

    }
}
