package com.devs.kero.team7.learningrxjava.contract;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.ArrayRes;
import android.support.annotation.DrawableRes;
import android.support.v4.app.FragmentManager;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.devs.kero.team7.domain.UseCases.BaseCompletableUseCase;
import com.devs.kero.team7.learningrxjava.Models.ColorView;
import com.devs.kero.team7.learningrxjava.base.BasePresenter;
import com.devs.kero.team7.learningrxjava.base.BaseView;
import com.devs.kero.team7.learningrxjava.presenter.AddTakPresenter;
import com.devs.kero.team7.learningrxjava.ui.fragments.AddTaskFragment;

import java.sql.Time;
import java.util.Date;

public interface AddTaskContract  {
    interface  View extends BaseView{
        void goBackToGetTasks();
        void initViews(String date , String time);
        void changeDateTextView(String date);
        void hangeTimeTextView(String string);
        void changeRepeatTypeTextView(String repeat);
        void showEndDate();
        void hideEndDate();
        String[] getStringArray(@ArrayRes int array);
        AddTakPresenter getPresenter() ;
        void changeEndDateTextView(String string);
        void changeAdvancedTextView(String string);
        void changeCategorie(@DrawableRes int drawable);
        void showErrorDateDialog();
        void showErrorEndDateDialog();
        void initViewForUpdate(String Title , String description , String date , String time , String RepeatBody, String EndDate , String AdvancedReminder , ColorView colorView);
        BaseCompletableUseCase getUpdateUseCase();
    }
    interface Presenter extends BasePresenter{
      void addTask(String Title , String Description );
      boolean  checkForTask();
      void showDatePicker(Context context);
      void showTimePicker(Context context);
      void init(boolean isUpdate , Bundle bundle);
      void showRepeatDialog(Context context, FragmentManager fragmentManager);
      void showEndDateDialog(Context context);
      void showAdvancedReminder(Context context, FragmentManager fragmentManager);
      void dissmissAdvncedReminder();
      void showCategorieDialog(Context context, FragmentManager fragmentManager);
    }
}
