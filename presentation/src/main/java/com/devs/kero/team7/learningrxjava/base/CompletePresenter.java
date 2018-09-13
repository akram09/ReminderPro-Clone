package com.devs.kero.team7.learningrxjava.base;

import com.devs.kero.team7.domain.UseCases.BaseCompletableUseCase;


public class CompletePresenter<I extends BaseCompletableUseCase, V extends  BaseView> implements BasePresenter   {
    private  V mMvpView;
    private I mMvpInteractor;

    public CompletePresenter(I mMvpInteractor) {
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
