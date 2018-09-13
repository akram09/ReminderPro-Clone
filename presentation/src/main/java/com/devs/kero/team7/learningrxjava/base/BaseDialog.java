package com.devs.kero.team7.learningrxjava.base;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.devs.kero.team7.learningrxjava.ui.activities.AddActivity;

public abstract class BaseDialog<T extends BaseDialog.ClickHnadlers> extends DialogFragment {
    protected  T listener ;
   public interface ClickHnadlers{

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder builder  = new AlertDialog.Builder(getContext());
        builder = setting(builder);
        return builder.create();
    }



    @Override
    public void onDetach() {
        super.onDetach();
        listener = null ;
    }

    protected abstract AlertDialog.Builder setting(AlertDialog.Builder builder);
}
