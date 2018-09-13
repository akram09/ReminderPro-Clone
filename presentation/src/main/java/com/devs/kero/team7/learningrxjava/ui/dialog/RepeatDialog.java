package com.devs.kero.team7.learningrxjava.ui.dialog;

import android.content.Context;
import android.content.DialogInterface;

import android.support.v7.app.AlertDialog;

import com.devs.kero.team7.learningrxjava.R;
import com.devs.kero.team7.learningrxjava.base.BaseDialog;
import com.devs.kero.team7.learningrxjava.ui.activities.AddActivity;


public class RepeatDialog extends BaseDialog<RepeatDialog.RepeatHandle> {
   public interface RepeatHandle extends BaseDialog.ClickHnadlers{
        void handlerepeatClicks(int  i );

    }
    @Override
    public AlertDialog.Builder setting(AlertDialog.Builder builder) {
        AlertDialog.Builder builder1 = builder ;
        builder1.setItems(R.array.Root, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.handlerepeatClicks(i);

            }
        });
        return builder1;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener= ((AddActivity) context).getPresenter();

    }
}
