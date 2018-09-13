package com.devs.kero.team7.learningrxjava.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.NumberPicker;

import com.devs.kero.team7.learningrxjava.R;
import com.devs.kero.team7.learningrxjava.base.BaseDialog;
import com.devs.kero.team7.learningrxjava.ui.activities.AddActivity;


public class Custom1Dialog extends BaseDialog<Custom2Dialog.clickListener> {


    public static final int NUMBERPICEKR_CUTSOM1=2 ;


    @Override
    protected AlertDialog.Builder setting(AlertDialog.Builder builder) {
        AlertDialog.Builder builder1 = builder ;
        View v = getActivity().getLayoutInflater().inflate(R.layout.addtask_numberpicker, null);
        final NumberPicker timepicker =v.findViewById(R.id.addtask_custom1dialog_time);
        final NumberPicker numberPicker = v.findViewById(R.id.addtask_custom1dialog_number);
        final String[] strings= getResources().getStringArray(R.array.Custom1);
        timepicker.setMinValue(0);
        timepicker.setMaxValue(strings.length-1);
        numberPicker.setMaxValue(100);
        numberPicker.setMinValue(2);
        timepicker.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int i) {
                return strings[i];
            }
        });
        builder1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.onOkClicked(NUMBERPICEKR_CUTSOM1, numberPicker, numberPicker.getValue(), timepicker.getValue());
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.onCancelCliceked();

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
