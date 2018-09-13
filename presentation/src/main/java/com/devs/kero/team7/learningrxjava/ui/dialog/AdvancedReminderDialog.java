package com.devs.kero.team7.learningrxjava.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.devs.kero.team7.learningrxjava.R;
import com.devs.kero.team7.learningrxjava.base.BaseDialog;
import com.devs.kero.team7.learningrxjava.ui.activities.AddActivity;


public class AdvancedReminderDialog  extends BaseDialog<AdvancedReminderDialog.AdvancedReminderListener> {
    public interface AdvancedReminderListener extends  BaseDialog.ClickHnadlers{
        void onclickItem(int which) ;
        }
    @Override
    protected AlertDialog.Builder setting(AlertDialog.Builder builder) {
        AlertDialog.Builder bb = builder ;
        bb.setItems(R.array.AdvancedReminder, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.onclickItem(i);
            }
        });
        return bb;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener= ((AddActivity) context).getPresenter();

    }
}
