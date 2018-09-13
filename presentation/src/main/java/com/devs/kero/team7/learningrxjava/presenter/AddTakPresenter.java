package com.devs.kero.team7.learningrxjava.presenter;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.TimePicker;

import com.devs.kero.team7.domain.UseCases.AddTaskUseCase;
import com.devs.kero.team7.domain.entities.Task;
import com.devs.kero.team7.learningrxjava.Models.ColorView;
import com.devs.kero.team7.learningrxjava.Models.TaskView;
import com.devs.kero.team7.learningrxjava.R;
import com.devs.kero.team7.learningrxjava.Utils.TaskMpper;
import com.devs.kero.team7.learningrxjava.base.CompletePresenter;
import com.devs.kero.team7.learningrxjava.contract.AddTaskContract;
import com.devs.kero.team7.learningrxjava.di.PerActivity;
import com.devs.kero.team7.learningrxjava.ui.dialog.AdvancedReminderDialog;
import com.devs.kero.team7.learningrxjava.ui.dialog.CategorieDialog;
import com.devs.kero.team7.learningrxjava.ui.dialog.Custom1Dialog;
import com.devs.kero.team7.learningrxjava.ui.dialog.Custom2Dialog;
import com.devs.kero.team7.learningrxjava.ui.dialog.RepeatDialog;
import com.devs.kero.team7.learningrxjava.ui.dialog.SelectedWeekDaysDialog;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import io.reactivex.observers.DisposableCompletableObserver;

@PerActivity
public class AddTakPresenter extends CompletePresenter<AddTaskUseCase , AddTaskContract.View>
        implements AddTaskContract.Presenter , RepeatDialog.RepeatHandle, SelectedWeekDaysDialog.SelectedweekdayListener
           , AdvancedReminderDialog.AdvancedReminderListener, Custom2Dialog.clickListener
                        , CategorieDialog.OnclickListener {
private TaskMpper mpper ;
private TaskView taskView;
private RepeatDialog repeatDialog;
private boolean isEndShown=false  ;
private SelectedWeekDaysDialog weekDaysDialog ;
private FragmentManager fragmentManager ;
private String [] ShortedDays = {"Sun", "Mon", "Tue", "Wed", "Thur", "Fri",  "Sat"};
private AdvancedReminderDialog dialog ;
private CategorieDialog categorieDialog;
private boolean ISUpdate ;
    @Inject
    public AddTakPresenter(AddTaskUseCase mMvpInteractor, TaskMpper mpper) {
        super(mMvpInteractor);
        this.mpper = mpper;
        taskView = new TaskView();
    }
    class AddTaskObserver extends DisposableCompletableObserver{
        @Override
        public void onComplete() {
        getmMvpView().goBackToGetTasks();

        }

        @Override
        public void onError(Throwable e) {

        }
    }

    @Override
    public void addTask(String Title, String Description) {

            if (Title == null || Title.isEmpty()) {
                taskView.setTaskTitle("[:Title:}");
            } else {
                taskView.setTaskTitle(Title);
            }
            taskView.setTaskDescripion(Description);
            if (checkForTask()) {
                if (ISUpdate) {
                    try {
                        getmMvpView().getUpdateUseCase().execute(new AddTaskObserver(), new Task[]{mpper.from(taskView)});
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else {
                    getmMvpInteractor().execute(new AddTaskObserver(), mpper.fromToAdd(taskView));
                }
            }

    }

    @Override
    public boolean checkForTask() {
        Date date = Calendar.getInstance().getTime();
        if(taskView.getDateTime().compareTo(date)<0){
            getmMvpView().showErrorDateDialog();
             return false;
        }else if(isEndShown){
            if(taskView.getEndDate().compareTo(new Date(112, 11, 12))>0){
                 return  true ;
            }else if(taskView.getDateTime().compareTo(taskView.getEndDate())>0){
                getmMvpView().showErrorEndDateDialog();
                return false;
        }

        }else if (taskView.getEndDate().compareTo(new Date(112, 11, 12))>0){
            return true;
        }
        return true;
    }

    @Override
    public void showDatePicker(Context context) {
      new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
               taskView.setDateTime(new Date(datePicker.getYear()-1900, datePicker.getMonth(),
                       datePicker.getDayOfMonth(), taskView.getDateTime().getHours(), taskView.getDateTime().getMinutes()));
               getmMvpView().changeDateTextView(String.valueOf(datePicker.getDayOfMonth())+'/'+
                       String.valueOf(datePicker.getMonth()+1)+'/'+String.valueOf(datePicker.getYear()));

            }
        },taskView.getDateTime().getYear()+1900, taskView.getDateTime().getMonth(),
                taskView.getDateTime().getDate()).show();
    }

    @Override
    public void showTimePicker(Context context) {
        new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                 taskView.setDateTime(new Date(taskView.getDateTime().getYear(), taskView.getDateTime().getMonth(),
                             taskView.getDateTime().getDate(),i , i1));
               getmMvpView().hangeTimeTextView(String.valueOf(i)+':'+String.valueOf(i1));
            }
        }, taskView.getDateTime().getHours(), taskView.getDateTime().getMinutes(), true).show();
    }

    @Override
    public void showRepeatDialog(Context context, FragmentManager fragmentManager) {
           repeatDialog = new RepeatDialog();
           this.fragmentManager = fragmentManager ;
           repeatDialog.show(fragmentManager, "Repeat_Dialog");
    }

    @Override
    public void handlerepeatClicks(int i) {
       String [] strings = getmMvpView().getStringArray(R.array.Root);
            if (i != 0 && i<6) {
                getmMvpView().showEndDate();
                isEndShown = true;
                getmMvpView().changeRepeatTypeTextView(strings[i]);
                taskView.setRepeatType("Simple");
                taskView.setRepeatBody(strings[i]);
            } else if(i>=6){
                switch (i){
                    case 6: weekDaysDialog = new SelectedWeekDaysDialog();
                         weekDaysDialog.show(fragmentManager, "selected_weekdays_dialog");
                         dissmissRepeatDialog();
                         break;
                    case 7 :  new Custom1Dialog().show(fragmentManager, "custom1_dialog");
                     dissmissRepeatDialog();
                     break;
                    case 8: new Custom2Dialog().show(fragmentManager, "custom2_dialog");
                    dissmissRepeatDialog();
                    break;
                }

            }else{
                taskView.setRepeatType("None");
                taskView.setRepeatBody(null);
            }
        if(isEndShown && i==0 ){
               taskView.setRepeatType("None");
               taskView.setRepeatBody(null);
                getmMvpView().hideEndDate();
                getmMvpView().changeRepeatTypeTextView(strings[0]);
                isEndShown = false ;
                }
    }
    private void dissmissRepeatDialog(){
      repeatDialog.dismiss();
      repeatDialog= null ;
    }

    @Override
    public void setOnOkClicked(boolean[] array) {
        getmMvpView().showEndDate();
        isEndShown =true ;
        String[] days = transform(array);
        if(days.length ==6){
            getmMvpView().changeRepeatTypeTextView("Repeat All Days Except "+getLast(days));
        }else{
            StringBuilder string = new StringBuilder();
            for (int i=0 ; i<days.length; i++){
                if(i==days.length-1){
                    string.append(days[i]);
                }else {
                    string.append(days[i]+", ");
                }
            }
            getmMvpView().changeRepeatTypeTextView("Repeat Every "+string.toString());
        }
        weekDaysDialog.dismiss();
        weekDaysDialog=null;
    }

    @Override
    public void setOnCancelClicked() {
   weekDaysDialog.dismiss();
   weekDaysDialog = null ;
   getmMvpView().hideEndDate();
    }
    private  String [] transform(boolean [] array){
        StringBuilder sb  = new StringBuilder();
        ArrayList<String> stringArrayList = new ArrayList<>();
        for (int i = 0; i <7 ; i++) {
            if(array[i]){
                sb.append(String.valueOf(i));
                stringArrayList.add(ShortedDays[i]);
            }
        }
        taskView.setRepeatType("SelectedWeekDays");
        taskView.setRepeatBody(sb.toString());
        String[] strings = new String[stringArrayList.size()];
        for(int i =0; i<strings.length; i++){
            strings[i] = stringArrayList.get(i);
        }
        return strings;
    }
    private String getLast(String[] array){
        int i ;
        for ( i=0; i<7; i++){
            if(!array[i].equals(ShortedDays[i])){
                break;
            }
        }
        if(i==7){
            return null;
        }else{
            return ShortedDays[0];
        }
    }

    @Override
    public void showEndDateDialog(Context context) {
        Date  date  = Calendar.getInstance().getTime();
        DatePickerDialog dialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                taskView.setEndDate(new Date(datePicker.getYear()-1900, datePicker.getMonth(),
                        datePicker.getDayOfMonth(),0,0));
                getmMvpView().changeEndDateTextView(String.valueOf(datePicker.getDayOfMonth())+'/'+
                        String.valueOf(datePicker.getMonth()+1)+'/'+String.valueOf(datePicker.getYear()));

            }
        }, date.getYear()+1900, date.getMonth(), date.getDate());
       dialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Forever", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       getmMvpView().changeEndDateTextView("Forever");
                       ///Todo thats forever
                       taskView.setEndDate(new Date(112, 11, 12));
                   }
               }
       );
        dialog.show();

    }

    @Override
    public void onclickItem(int which) {
        if(which==0){
            getmMvpView().changeAdvancedTextView("None");
            taskView.setAdvancedRemind(null);
        }else{
            String[] array = getmMvpView().getStringArray(R.array.AdvancedReminder);
            getmMvpView().changeAdvancedTextView(array[which]);
           taskView.setAdvancedRemind(array[which]);
        }
        dissmissAdvncedReminder();

    }

    @Override
    public void init(boolean isUpdate  , Bundle bundle) {
        if(isUpdate){
            ISUpdate = true ;
            String Date= bundle.getString("dateTime");
            StringBuilder sb  = new StringBuilder(Date);
            taskView.setTaskId(bundle.getLong("id"));
            taskView.setTaskTitle(bundle.getString("title"));
            taskView.setTaskDescripion(bundle.getString("description"));
            taskView.setDateTime(new Date(Integer.valueOf(sb.substring(6, 10))-1900, Integer.valueOf(sb.substring(3,5))-1 , Integer.valueOf(sb.substring(0 , 2)),
            Integer.valueOf(sb.substring(11 , 13)),Integer.valueOf(sb.substring(14))
            ));
            taskView.setCategorie(ColorView.valueOf(bundle.getString("colorView")));
            taskView.setAdvancedRemind(bundle.getString("advancedReminder"));
            taskView.setRepeatType(bundle.getString("repeatType"));
            taskView.setRepeatBody(bundle.getString("repeatbody"));
            if(taskView.getRepeatBody()==null){
              taskView.setEndDate(new Date(112, 11, 12));
            }else{
//                StringBuilder stringBuilder = new StringBuilder(bundle.getString("enddate"));
//                taskView.setEndDate(new Date(Integer.valueOf(stringBuilder.substring(6)), Integer.valueOf(sb.substring(3, 5))-1, Integer.valueOf(sb.substring(0, 2))));
            }
            getmMvpView().initViewForUpdate(bundle.getString("title"), bundle.getString("description") , sb.substring(0, 10),
                    sb.substring(11, 16),bundle.getString("repeatbody"),bundle.getString("repeatbody")==null?"Forever": bundle.getString("enddate")
                    , bundle.getString("advancedReminder")==null?"None":bundle.getString("advancedReminder"),ColorView.valueOf( bundle.getString("colorView")));
            }else {
            ISUpdate = false ;
            Date date = Calendar.getInstance().getTime();
            taskView.setDateTime(date);
            taskView.setEndDate(new Date(112, 11, 12));
            taskView.setRepeatType("None");
            taskView.setRepeatBody(null);
            taskView.setTaskTitle("[:No Title:]");
            taskView.setTaskDescripion("[: No Description :]");
            taskView.setCategorie(ColorView.Default);
            taskView.setAdvancedRemind(null);
            taskView.setComplete(false);
            getmMvpView().initViews(String.valueOf(date.getDate()) + "/" + String.valueOf(date.getMonth() + 1) + "/" +
                    String.valueOf(1900 + date.getYear()), String.valueOf(date.getHours())
                    + ':' + String.valueOf(date.getMinutes()));
        }
    }

    @Override
    public void onCancelCliceked() {
    }

    @Override
    public void onOkClicked(int choose, NumberPicker numberPicker, int i, int i1) {
         getmMvpView().showEndDate();
         isEndShown = true ;
        if(choose==Custom2Dialog.NUMBERPICKER_CUSTOM2) onValueChange(numberPicker, i, i1);
        else onValueChange(null , i, i1);
    }

    @Override
    public void showAdvancedReminder(Context context, FragmentManager fragmentManager) {
        if(this.fragmentManager==null) {
            this.fragmentManager = fragmentManager;
        }
        dialog = new AdvancedReminderDialog();
        dialog.show(this.fragmentManager,"advanced_dialog");
    }

    @Override
    public void dissmissAdvncedReminder() {
        dialog.dismiss();
        dialog= null ;
    }

    @Override
    public void setOnClickListenr(ColorView color) {
        switch (color){
            case Red:getmMvpView().changeCategorie(R.drawable.ic_star_red_24dp);
            break;
            case Green: getmMvpView().changeCategorie(R.drawable.ic_star_green_24dp);
            break;
            case Default:getmMvpView().changeCategorie(R.drawable.ic_star_border_black_24dp);
            break;
            case Yellow:getmMvpView().changeCategorie(R.drawable.ic_star_yellow_24dp);
            break;
            case Blue:getmMvpView().changeCategorie(R.drawable.ic_star_blue_24dp);
            break;
        }
        taskView.setCategorie(color);
         categorieDialog.dismiss();
        categorieDialog = null;

    }
    @Override
    public void showCategorieDialog(Context context, FragmentManager fragmentManager) {
        categorieDialog = new CategorieDialog();
         categorieDialog.show(fragmentManager, "categorie_dialog");
    }
    @Override
    public void onValueChange(NumberPicker numberPicker, int i, int i1) {
            if(numberPicker==null){
                String[] strings  = getmMvpView().getStringArray(R.array.Custom1) ;
                taskView.setRepeatType("Custom1");
                taskView.setRepeatBody((i<10?'0'+String.valueOf(i):String.valueOf(i))+strings[i1]);
                getmMvpView().changeRepeatTypeTextView("Every "+String.valueOf(i)+" "+strings[i1]);
            }else {
                final String[] weekdays = getmMvpView().getStringArray(R.array.SelectedWeekDays);
                final String[] strings = getmMvpView().getStringArray(R.array.Custom2);
                taskView.setRepeatType("Custom2");
                taskView.setRepeatBody(strings[i]+weekdays[i1]);
                getmMvpView().changeRepeatTypeTextView("Every " + strings[i] + " " + weekdays[i1]);
            }
    }

}
