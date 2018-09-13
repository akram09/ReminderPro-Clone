package com.devs.kero.team7.learningrxjava.contract;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.devs.kero.team7.learningrxjava.Models.ColorView;
import com.devs.kero.team7.learningrxjava.Models.TaskView;
import com.devs.kero.team7.learningrxjava.base.BasePresenter;
import com.devs.kero.team7.learningrxjava.base.BaseView;
import com.devs.kero.team7.learningrxjava.presenter.MainScreenPresenter;
import com.devs.kero.team7.learningrxjava.ui.adapters.TasksAdapter;

import java.text.ParseException;
import java.util.List;

public interface MainScreenContract  {
    interface Presenter extends BasePresenter {
        void getTasks();
        void showTaks(List<TaskView > taskViews);
        void changeLabel(int number);
        void OnBackPressed();
        void DeleteTask() throws ParseException;
       void handleDialogClicks(boolean Ok);
    }
    interface View extends  BaseView {
        void showTasks(TasksAdapter adapter);
        void showNoTasks();
       void changeLabel(String message);
       void showDeleteTime();
       void showNotDeleteTime();
       void handleOnBackPressed();
       void deleteTasks();
       void showConfirmationDialog();
       void DisableConfirmationDialog();
       MainScreenPresenter getPresenter();
       FragmentManager  getManager();
       void goToUpdateTask(long id , String Title , String Description , String dateTime , String RepeatType , String RepeatBody , String AdvancedReminder ,String enddate , ColorView colorView);
    }
}
