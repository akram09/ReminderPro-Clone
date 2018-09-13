package com.devs.kero.team7.learningrxjava.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import com.devs.kero.team7.learningrxjava.Models.ColorView;
import com.devs.kero.team7.learningrxjava.R;
import com.devs.kero.team7.learningrxjava.base.BaseDialog;
import com.devs.kero.team7.learningrxjava.databinding.AddtaskCategoriesDialogBinding;
import com.devs.kero.team7.learningrxjava.ui.activities.AddActivity;


public class CategorieDialog extends BaseDialog<CategorieDialog.OnclickListener> {
    public interface  OnclickListener extends BaseDialog.ClickHnadlers {
        void setOnClickListenr(ColorView color);
    }
   AddtaskCategoriesDialogBinding dialogBinding ;

    @Override
    protected AlertDialog.Builder setting(AlertDialog.Builder builder) {
        AlertDialog.Builder builder1 = builder;

         dialogBinding = AddtaskCategoriesDialogBinding.inflate( (LayoutInflater)
                 getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE));
//        View v = getActivity().getLayoutInflater().inflate(R.layout.addtask_categories_dialog, null);

        builder1.setView(dialogBinding.getRoot()).setTitle("Select Categorie");
        dialogBinding.addtaskCategoridialogImagebtnBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.setOnClickListenr(ColorView.Blue);
            }
        });
        dialogBinding.addtaskCategoridialogImagebtnRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.setOnClickListenr(ColorView.Red);
            }
        });
        dialogBinding.addtaskCategoridialogImagebtnGreen
                .setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                listener.setOnClickListenr(ColorView.Green);
            }
        });
        dialogBinding.addtaskCategoridialogImagebtnYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.setOnClickListenr(ColorView.Yellow);
            }
        });
        dialogBinding.addtaskCategoridialogImagebtnDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.setOnClickListenr(ColorView.Default);
            }
        }) ;
        return builder1;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener= ((AddActivity) context).getPresenter();
    }
}
