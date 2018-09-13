package com.devs.kero.team7.learningrxjava.contract;



import android.support.v4.app.FragmentManager;

import com.devs.kero.team7.learningrxjava.base.BasePresenter;
import com.devs.kero.team7.learningrxjava.base.BaseView;

import java.util.List;

public interface DateTimePickerContract {
    interface Presenter extends BasePresenter{
       void init() ;
       void showDialog(FragmentManager fragmentManager, int which);
    }
interface  View extends BaseView{
        void initViews(List<String> strings);
        Presenter getPresenter();
        void showDatePickerDialog();
        void showTimePicker();
        void showFirstDayDialog();

}


}
