package com.devs.kero.team7.learningrxjava.contract;

import android.support.v4.app.FragmentManager;

import com.devs.kero.team7.learningrxjava.base.BasePresenter;
import com.devs.kero.team7.learningrxjava.base.BaseView;

public interface MainSettingContract {

    interface View extends BaseView{
        void showSelectThemeDialog();
        Presenter getPresenter();
        void showDisplayViewDialog();
        void showDeleteConfirmationDialog();
        void showShowKeyboardImmediately();
        void  changeTextView(String text , int which);
        void goToDateTimePickerFragment();
        void goToNotificationSound();
        void goToVibration();
        void goToPopupNotification();
        void goToTurnScreen();
        void goToNotificationTitle();

    }
    interface Presenter extends BasePresenter{
       void showDialog(FragmentManager fragmentManager);
       void showDialogue(FragmentManager fragmentManager , int which);
       void init();

    }
}
