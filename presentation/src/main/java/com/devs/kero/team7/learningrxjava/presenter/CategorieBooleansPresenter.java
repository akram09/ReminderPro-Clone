package com.devs.kero.team7.learningrxjava.presenter;

import com.devs.kero.team7.domain.StettingUseCases.UpdateCategoriesBoolUseCase;
import com.devs.kero.team7.domain.StettingUseCases.getBooleanCategoriesUseCase;
import com.devs.kero.team7.learningrxjava.base.Presenter;
import com.devs.kero.team7.learningrxjava.contract.CategoriesBooleansContract;

import java.util.List;
import javax.inject.Inject;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableObserver;

public class CategorieBooleansPresenter extends Presenter<getBooleanCategoriesUseCase, CategoriesBooleansContract.View>
                                       implements CategoriesBooleansContract.Presenter{
    UpdateCategoriesBoolUseCase updateCategoriesBoolUseCase ;
    private List<Boolean> booleanes ;
    private boolean isVibration ;
    @Inject
    public CategorieBooleansPresenter(getBooleanCategoriesUseCase mMvpInteractor, UpdateCategoriesBoolUseCase updateCategoriesBoolUseCase) {
        super(mMvpInteractor);
        this.updateCategoriesBoolUseCase = updateCategoriesBoolUseCase;
    }


    @Override
    public void initviews(boolean isVibration) {
        this.isVibration =isVibration;
        getmMvpInteractor().execute(new BooleansObserver(), isVibration);
    }
    private class BooleansObserver extends DisposableObserver<List<Boolean > >{
        @Override
        public void onNext(List<Boolean> booleans) {
               booleanes = booleans;
               getmMvpView().initViews(booleans);
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }
private class CompletabeBooleanObserver extends DisposableCompletableObserver{
    @Override
    public void onComplete() {
        getmMvpInteractor().execute(new BooleansObserver(), isVibration);

    }

    @Override
    public void onError(Throwable e) {

    }
}
    @Override
    public void handleClicks(boolean b, int whichItem) {
        booleanes.set(whichItem, b);
        updateCategoriesBoolUseCase.execute(new CompletabeBooleanObserver(), booleanes, isVibration);

    }
}
