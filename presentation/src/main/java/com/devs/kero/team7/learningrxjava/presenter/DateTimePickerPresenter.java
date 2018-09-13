package com.devs.kero.team7.learningrxjava.presenter;

import android.support.v4.app.FragmentManager;

import com.devs.kero.team7.domain.StettingUseCases.GetDesignBoolean;
import com.devs.kero.team7.domain.StettingUseCases.UpdateDesignBoolean;
import com.devs.kero.team7.learningrxjava.base.Presenter;
import com.devs.kero.team7.learningrxjava.contract.DateTimePickerContract;
import com.devs.kero.team7.learningrxjava.ui.dialog.SettingDialog;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableObserver;

public class DateTimePickerPresenter extends SettingPresenter<GetDesignBoolean, DateTimePickerContract.View>
                   implements DateTimePickerContract.Presenter {
    @Inject
    public DateTimePickerPresenter(GetDesignBoolean mMvpInteractor, GetDesignBoolean getDesignBoolean, UpdateDesignBoolean updateDesignBoolean) {
        super(mMvpInteractor, getDesignBoolean, updateDesignBoolean);
    }

    @Override
    public void init() {
     getDesignBoolean.execute(new GetObserver(), new Boolean[]{true});
    }
    class UpdateObserver extends DisposableCompletableObserver{
        @Override
        public void onComplete() {
           getDesignBoolean.execute(new GetObserver(), new Boolean[]{true});
        }

        @Override
        public void onError(Throwable e) {

        }
    }
    class GetObserver extends DisposableObserver<List<Boolean>>{
        @Override
        public void onNext(List<Boolean> booleans) {
        booleanes = booleans ;
        getmMvpView().initViews(convertToString(booleans));
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }
    private List<String> convertToString(List<Boolean> booleans){
        List<String> strings  = new ArrayList<>();
        strings.add(booleans.get(0).booleanValue()?"Calendar":"Spinner");
        strings.add(booleans.get(1).booleanValue()?"Clock":"Spinner");
        strings.add(booleans.get(2).booleanValue()?"Sunday":"Monday");
        return strings;
    }

    @Override
    public void showDialog(FragmentManager fragmentManager, int which) {
        String title ;
        String [] strings  ;
        switch (which-3){
            case 0 : title = "Date Picker style " ;
                strings=  new String[]{"Calendar", "Spinner"} ;
                break;
            case 1 : title = "Time Picker style ";
                strings=new String[]{"Clock", "Spinner"} ;
                break;
            case 2 : title = "First Day Of the week" ;
                strings = new String[]{"Sunday", "Monday"};
                break;
            default:title = "Date Picker style " ;
                strings=  new String[]{"Calendar", "Spinner"} ;
                break;
        }
        settingDialog = SettingDialog.newInstance(booleanes.get(which-3),which,title, strings  );
        settingDialog.show(fragmentManager , "settingdialogue"+String.valueOf(which));
    }

    @Override
    public void onClickListener(int ID, int which) {
        if(which>=0){
            booleanes.set(ID-3, Boolean.valueOf(which==0)) ;
            updateDesignBoolean.execute(new UpdateObserver(), booleanes, true );
        }
        settingDialog.dismiss();
        settingDialog = null ;
    }
}
