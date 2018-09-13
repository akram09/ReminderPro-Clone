package com.devs.kero.team7.learningrxjava.presenter;

import android.content.DialogInterface;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;

import com.devs.kero.team7.domain.StettingUseCases.UpdateCategoriesBoolUseCase;
import com.devs.kero.team7.domain.StettingUseCases.UpdateCategoriesUseCase;
import com.devs.kero.team7.domain.StettingUseCases.UseCase;
import com.devs.kero.team7.domain.StettingUseCases.getCategoriesUseCase;
import com.devs.kero.team7.domain.UseCases.BaseUseCase;
import com.devs.kero.team7.learningrxjava.R;
import com.devs.kero.team7.learningrxjava.base.Presenter;
import com.devs.kero.team7.learningrxjava.contract.CategorieBaseContract;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableObserver;

public class CategorieBasePresenter extends Presenter<getCategoriesUseCase,CategorieBaseContract.View > implements CategorieBaseContract.Presenter {
    UpdateCategoriesUseCase updateCategoriesUseCase ;
    List<String> stringsList ;
    AlertDialog dialoge;
    Integer integer;
    AlertDialog TitleDialog ;
    int whichItem ;
   @Inject
    public CategorieBasePresenter(getCategoriesUseCase mMvpInteractor, UpdateCategoriesUseCase updateCategoriesUseCase) {
        super(mMvpInteractor);
        this.updateCategoriesUseCase = updateCategoriesUseCase;
    }

    class CategorieObserverComplete extends DisposableCompletableObserver{
        @Override
        public void onComplete() {
          getmMvpInteractor().execute(new CategorieObserver(), integer);
        }

        @Override
        public void onError(Throwable e) {

        }
    }
    class CategorieObserver extends DisposableObserver<List<String>>{
        @Override
        public void onNext(List<String> strings) {
         getmMvpView().initViews(strings);
         stringsList = strings ;
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }
    @Override
    public void initViews(Integer integer) {
       getmMvpInteractor().execute(new CategorieObserver(),integer );
       this.integer =integer ;

    }
     private  int getCheckedItem(String string){
       if(string.equals("NoPopup")){
           return 0 ;
       }else if( string.equals("NoOnLockScreen")){
           return 1;
       }else{
           return 2;
       }
     }
     private String getString(int inter){
       switch (inter){
           case 0: return "NoPopup" ;
           case 1: return "NoOnLockScreen" ;
           default: return "Always" ;
       }
     }
    @Override
    public void ItemClicked(final int whichItem) {
       this.whichItem  =whichItem;
        switch (integer.intValue()){
            case 0:
                getmMvpView().searchForRingtnes(stringsList.get(whichItem),
                        stringsList.get(whichItem).equals(String.valueOf(R.raw.alarm)));

             break;
            case 1:
                final AlertDialog.Builder  builder1 = new AlertDialog.Builder(getmMvpView().getContexte());
                builder1.setTitle("Popup ntification").setSingleChoiceItems(new String[]{"No popup", "Do not show on lock screen ",
                        "Always show popup"}, getCheckedItem(stringsList.get(whichItem)), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        stringsList.set(whichItem,getString(i)) ;
                    updateCategoriesUseCase.execute(new CategorieObserverComplete(), stringsList,integer);
                    dissmissDialog();
                    }
                }).setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                      dissmissDialog();
                    }
                }).create();
                dialoge = builder1.create();
                dialoge.show();
                break;
            case 2:
                AlertDialog.Builder builder2 = new AlertDialog.Builder(getmMvpView().getContexte());
                final EditText editText = new EditText(getmMvpView().getContexte());
                editText.setText(stringsList.get(whichItem));
                   builder2.setTitle("Notification title").setView(editText).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialogInterface, int i) {
                             stringsList.set(whichItem, editText.getText().toString());
                             updateCategoriesUseCase.execute(new CategorieObserverComplete(), stringsList, integer);
                             TitleDialog.dismiss();
                             TitleDialog = null ;
                       }
                   }).setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialogInterface, int i) {
                           TitleDialog.dismiss();
                           TitleDialog = null ;
                       }
                   });
                 TitleDialog  = builder2.create();
                 TitleDialog.show();
                 break;
        }
    }
    private void dissmissDialog(){
        dialoge.dismiss();
        dialoge = null ;
    }

    @Override
    public void RingtoneReturned(Uri uri) {
        stringsList.set(whichItem, uri.toString());
        updateCategoriesUseCase.execute(new CategorieObserverComplete(), stringsList, integer);

    }
}
