package com.devs.kero.team7.learningrxjava.ui.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.devs.kero.team7.learningrxjava.R;
import com.devs.kero.team7.learningrxjava.base.BaseDialog;
import com.devs.kero.team7.learningrxjava.databinding.UpdateDialogBinding;
import com.devs.kero.team7.learningrxjava.ui.activities.MainActivity;

public class UpdateDialog extends BaseDialog<UpdateDialog.UpdateClickListener> {
    UpdateDialogBinding binding ;
public static final String TASK_TITLE="update_dialog_task_title" ;
public static final String TASK_REPEAT_TYPE= "update_dialog_repeat_type";
public static final String TASK_NEXT_RUN = "updaet_dialog_next_run";
public static final String TASK_ISREPEATED = "update_dialog_isrepeated";
public static final String TASK_DESCRIPTION = "task_description" ;
public static final String TASK_ISCOMPLETE = "task_is_complete";


    public static UpdateDialog NewInstance(String Title , String Desccripytion, boolean IssComplete, String RepeaType, String NextRun , boolean isRepeated ){
        UpdateDialog dialog = new UpdateDialog();
        Bundle  bundle  = new Bundle();
        bundle.putString(TASK_TITLE, Title);
        bundle.putString(TASK_REPEAT_TYPE, RepeaType);
        bundle.putString(TASK_NEXT_RUN, NextRun);
        bundle.putBoolean(TASK_ISREPEATED,isRepeated );
        bundle.putString(TASK_DESCRIPTION, Desccripytion);
        bundle.putBoolean(TASK_ISREPEATED, IssComplete);
        dialog.setArguments(bundle);
        return dialog ;
    }

   public interface UpdateClickListener extends BaseDialog.ClickHnadlers{
       void onButtonClicked(int which);
       void onSkipTurnClicked() ;
    }
    @Override
    protected AlertDialog.Builder setting(AlertDialog.Builder builder) {
        Bundle  b = getArguments();
        AlertDialog.Builder builder1 = builder ;
         binding = UpdateDialogBinding.inflate( (LayoutInflater)
                 getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE));
         builder1.setTitle("Choose an action").setView(binding.getRoot());
         if(!b.getBoolean(TASK_ISREPEATED)){
             binding.updateDialgSkipRun.setVisibility(View.GONE);
         }
         binding.updateDialgSkipRun.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 listener.onSkipTurnClicked();
             }
         });
         binding.updateDialogCompleteBtn.setOnClickListener(new View.OnClickListener(){
             @Override
             public void onClick(View view) {
                 listener.onButtonClicked(2);
             }
         });
         binding.updateDialogDeleteBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 listener.onButtonClicked(0);
             }
         });
         binding.updateDialogDismissBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                listener.onButtonClicked(3);
             }
         });
         binding.updateDialogEditBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 listener.onButtonClicked(1);
             }
         });
         if(b.getBoolean(TASK_ISREPEATED)){
             binding.updateDialogCompleteBtn.setCompoundDrawables(null , getContext().getResources().getDrawable(R.drawable.ic_play_arrow_black_24dp), null, null);
            binding.updateDialogCompleteBtn.setText("Activate");
            binding.updateDialgSkipRun.setText("Completed(Manuel)");
            binding.updateDialgSkipRun.setClickable(false);
         }
         binding.updateDialogDescription.setText(b.getString(TASK_DESCRIPTION));
       //Next run
        binding.updateDialogNextRun.setText(b.getString(TASK_NEXT_RUN));
        //Repeat Type
        binding.updateDialogRepeatType.setText(b.getString(TASK_REPEAT_TYPE));
         //Title
         binding.updateDialog.setText(b.getString(TASK_TITLE));
        return builder1;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = ((MainActivity)context).getPresenter();
    }
}
