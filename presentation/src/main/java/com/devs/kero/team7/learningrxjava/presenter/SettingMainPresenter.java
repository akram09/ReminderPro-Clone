package com.devs.kero.team7.learningrxjava.presenter;

import android.support.v4.app.FragmentManager;

import com.devs.kero.team7.domain.StettingUseCases.GetCurrentherme;
import com.devs.kero.team7.domain.StettingUseCases.GetDesignBoolean;
import com.devs.kero.team7.domain.StettingUseCases.UpdateCurrentTheme;
import com.devs.kero.team7.domain.StettingUseCases.UpdateDesignBoolean;
import com.devs.kero.team7.domain.UseCases.BaseUseCase;
import com.devs.kero.team7.learningrxjava.R;
import com.devs.kero.team7.learningrxjava.contract.MainSettingContract;
import com.devs.kero.team7.learningrxjava.di.PerActivity;
import com.devs.kero.team7.learningrxjava.ui.dialog.SelectThemeDialog;
import com.devs.kero.team7.learningrxjava.ui.dialog.SettingDialog;
import com.devs.kero.team7.learningrxjava.ui.fragments.SettingMainFragment;


import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableObserver;

@PerActivity
public class SettingMainPresenter  extends SettingPresenter<GetCurrentherme, SettingMainFragment> implements MainSettingContract.Presenter, SelectThemeDialog.onClick {
    private SelectThemeDialog dialog ;
    private UpdateCurrentTheme updateCurrentTheme ;
  private Integer whichTheme ;
    @Override
    public void start() {

    }
    @Inject
    public SettingMainPresenter(GetCurrentherme mMvpInteractor, GetDesignBoolean getDesignBoolean, UpdateDesignBoolean updateDesignBoolean, UpdateCurrentTheme updateCurrentTheme) {
        super(mMvpInteractor, getDesignBoolean, updateDesignBoolean);
        this.updateCurrentTheme = updateCurrentTheme;
    }

    @Override
    public void showDialog(FragmentManager fragmentManager) {
        dialog = SelectThemeDialog.newInstance(whichTheme.intValue());
        dialog.show(fragmentManager, "integer");
    }

    @Override
    public void setOnClickListner(int which) {
        if(which>=0){
         updateCurrentTheme.execute(new CompletableObserver(), new Integer[]{which});
         getmMvpView().changeTextView(getmMvpView().getResources().getStringArray(R.array.Themes )[which], 0);
        }
        dialog.dismiss();
        dialog= null ;
    }
    class CompletableObserver extends DisposableCompletableObserver{
        @Override
        public void onComplete() {
      getDesignBoolean.execute(new SettingObserver(), new Boolean[]{false });
      getmMvpInteractor().execute(new ObserverGet(), new Void[]{});
        }

        @Override
        public void onError(Throwable e) {

        }
    }
    class ObserverGet extends DisposableObserver<Integer>{
        @Override
        public void onNext(Integer integer) {
            whichTheme = integer;
            getmMvpView().changeTextView(getmMvpView().getResources().getStringArray(R.array.Themes)[integer.intValue()], 0);

        }

        @Override
        public void onError(Throwable e) {

        }
        @Override
        public void onComplete() {

        }
    }

      class SettingObserver extends DisposableObserver<List<Boolean>>{
      @Override
      public void onNext(List<Boolean> booleans) {
          booleanes = booleans ;
          getmMvpView().changeTextView(booleans.get(0)?"Standard":"Compact", 1);
          getmMvpView().changeTextView(booleans.get(1)?"Enable":"Disable", 2);
          getmMvpView().changeTextView(booleans.get(2)?"Disable":"Enable", 3);
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

            }
        }
    @Override
    public void init() {
        getmMvpInteractor().execute(new ObserverGet(), new Void[]{null , null});
        getDesignBoolean.execute(new SettingObserver(), new Boolean[]{false} );
    }

    @Override
    public void onClickListener(int ID, int which) {
        if(which>=0){
            booleanes.set(ID, Boolean.valueOf(which==0)) ;
            updateDesignBoolean.execute(new CompletableObserver(), booleanes, false );
        }
       settingDialog.dismiss();
        settingDialog = null ;
    }

    @Override
    public void showDialogue(FragmentManager fragmentManager, int which) {
        String title ;
        String [] strings  ;
        switch (which){
            case 0 : title = "Reminders display view \n (for home screen) " ;
                strings=  new String[]{"Standard", "Compact"} ;
            break;
            case 1 : title = "Delete confirmation \n (for individual reminders) ";
            strings=new String[]{"Enable", "Disable"} ;
            break;
            case 2 : title = "Show keyboard immediately \n (when create new reminder)" ;
            strings = new String[]{"Disabled", "Enabled"};
            break;
            default:title ="Reminders display view \n (for home screen) " ;
            strings =  new String[]{"Standard", "Compact"} ;
            break;
        }
         settingDialog =SettingDialog.newInstance((Boolean)booleanes.get(which), which, title, strings);
         settingDialog.show(fragmentManager, "settingdialog"+String.valueOf(which));
    }
}
