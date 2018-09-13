package com.devs.kero.team7.learningrxjava.ui.dialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import com.devs.kero.team7.learningrxjava.R;
import com.devs.kero.team7.learningrxjava.base.BaseDialog;
import com.devs.kero.team7.learningrxjava.ui.activities.SettingActivity;

public class SelectThemeDialog extends BaseDialog<SelectThemeDialog.onClick> {
    public interface onClick  extends BaseDialog.ClickHnadlers{
       void setOnClickListner(int which);
    }
    public static final String INTEGER = "innteger";

    public static SelectThemeDialog newInstance(int integer){
        SelectThemeDialog dialog =  new SelectThemeDialog() ;
        Bundle bundle = new Bundle();
        bundle.putInt(INTEGER, integer);
        dialog.setArguments(bundle);
        return dialog ;
    }
    @Override
    protected AlertDialog.Builder setting(AlertDialog.Builder builder) {
        Bundle b = getArguments();
        AlertDialog.Builder builder1 = builder;
        builder1.setSingleChoiceItems(R.array.Themes, b.getInt(INTEGER, 0), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                   listener.setOnClickListner(i);
            }
        }).setTitle("Select Theme").setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
               listener.setOnClickListner(-1);
            }
        });
        return builder1;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        listener=((SettingActivity)getActivity()).getPresenterfortTheme();
    }
}
