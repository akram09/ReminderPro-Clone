package com.devs.kero.team7.learningrxjava.ui.dialog;


import android.content.Context;
import android.content.DialogInterface;

import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.NumberPicker;

import com.devs.kero.team7.learningrxjava.R;
import com.devs.kero.team7.learningrxjava.base.BaseDialog;
import com.devs.kero.team7.learningrxjava.ui.activities.AddActivity;


public class Custom2Dialog extends BaseDialog<Custom2Dialog.clickListener> {

    public static  final int NUMBERPICKER_CUSTOM2=1 ;
    public interface clickListener extends NumberPicker.OnValueChangeListener , BaseDialog.ClickHnadlers{
     void onCancelCliceked();
     void onOkClicked(int choose, NumberPicker numberPicker, int i, int i1);
    }
    @Override
    protected AlertDialog.Builder setting(AlertDialog.Builder builder) {
        AlertDialog.Builder builder1 = builder ;
        final String[] weekdays = getResources().getStringArray(R.array.SelectedWeekDays);
        final String[] strings = getResources().getStringArray(R.array.Custom2);
        View v = getActivity().getLayoutInflater().inflate(R.layout.addtask_numberpicker, null);
        final NumberPicker weekpicker =v.findViewById(R.id.addtask_custom1dialog_time);
        final NumberPicker numberPicker = v.findViewById(R.id.addtask_custom1dialog_number);
        weekpicker.setMinValue(0);
        weekpicker.setMaxValue(weekdays.length-1);
        numberPicker.setMaxValue(strings.length-1);
        numberPicker.setMinValue(0);
        weekpicker.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int i) {
                return weekdays[i];
            }
        });
        numberPicker.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int i) {
                return strings[i];
            }
        });
        builder1.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                listener.onCancelCliceked();

            }
        }).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                listener.onOkClicked(NUMBERPICKER_CUSTOM2, numberPicker, numberPicker.getValue(), weekpicker.getValue());
            }
        });
        builder1.setView(v);
        return builder1;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener= ((AddActivity) context).getPresenter();

    }
}
