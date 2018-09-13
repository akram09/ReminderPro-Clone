package com.devs.kero.team7.learningrxjava.contract;

import android.net.Uri;

import com.devs.kero.team7.learningrxjava.base.BasePresenter;
import com.devs.kero.team7.learningrxjava.base.BaseView;

import java.util.List;

public interface CategorieBaseContract {
    interface Presenter extends BasePresenter{
     void initViews(Integer integer) ;
     void ItemClicked(int whichItem);
     void RingtoneReturned(Uri uri);

    }
    interface View extends BaseView{
     void initViews(List<String> strings);
     void searchForRingtnes(String uri , boolean isDefault);
     void handleRingtone(Uri uri);
    }
}
