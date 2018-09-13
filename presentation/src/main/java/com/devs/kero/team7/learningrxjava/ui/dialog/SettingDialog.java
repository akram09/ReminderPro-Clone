package com.devs.kero.team7.learningrxjava.ui.dialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import com.devs.kero.team7.learningrxjava.base.BaseDialog;
import com.devs.kero.team7.learningrxjava.ui.activities.SettingActivity;

public class SettingDialog extends BaseDialog<SettingDialog.ClickListener> {
    private static final String INTEGER = "integer";
    public static final String  TITLE = "dialog_title";
    public static final String STRING_ARRAY = "stringarray";
    public static final String DIALOG_ID = "dialogid" ;
    public SettingDialog() {
    }

    public interface  ClickListener extends BaseDialog.ClickHnadlers{
        void onClickListener(int ID ,int which);
    }


    public static SettingDialog newInstance(boolean bool, int id , String title , String[] strings) {
        SettingDialog  dialog = new SettingDialog();
        Bundle bundle = new Bundle();
        bundle.putBoolean(INTEGER, bool);
        bundle.putString(TITLE,title);
        bundle.putStringArray(STRING_ARRAY, strings);
        bundle.putInt(DIALOG_ID,id);
        dialog.setArguments(bundle);
        return dialog;
    }
//"Show keyboard immediately \n (when create new reminder)"
    @Override
    protected AlertDialog.Builder setting(AlertDialog.Builder builder) {
        final Bundle b = getArguments();
        AlertDialog.Builder builder1 = builder;
        builder1.setSingleChoiceItems(b.getStringArray(STRING_ARRAY), b.getBoolean(INTEGER, true) ? 0 : 1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.onClickListener(b.getInt(DIALOG_ID), i);
            }
        }).setTitle(b.getString(TITLE)).setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.onClickListener(b.getInt(DIALOG_ID), -1);
            }
        });
        return builder1;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        listener = ((SettingActivity) getActivity()).getPresenter();
    }
}
