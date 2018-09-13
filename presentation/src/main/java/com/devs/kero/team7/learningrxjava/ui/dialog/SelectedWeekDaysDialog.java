package com.devs.kero.team7.learningrxjava.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.devs.kero.team7.learningrxjava.R;
import com.devs.kero.team7.learningrxjava.base.BaseDialog;
import com.devs.kero.team7.learningrxjava.ui.activities.AddActivity;


public class SelectedWeekDaysDialog extends BaseDialog<SelectedWeekDaysDialog.SelectedweekdayListener> {
     public interface SelectedweekdayListener extends BaseDialog.ClickHnadlers{
         void setOnOkClicked(boolean[] array);
         void setOnCancelClicked();
     }
    @Override
    protected AlertDialog.Builder setting(AlertDialog.Builder builder) {
         AlertDialog.Builder builder1 = builder;
        final boolean[] array = new boolean[]{false, true, true, true, true, true, false};
        builder.setMultiChoiceItems(R.array.SelectedWeekDays, array, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                if(b && !array[i]){
                    array[i] = true ;
                }else if(!b && array[i] ){
                    array[i]= false ;
                }
            }
        }).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.setOnOkClicked(array);
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.setOnCancelClicked();
            }
        });
        return builder1;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
      listener = (SelectedweekdayListener) ((AddActivity)getActivity()).getPresenter();
    }
}
