package com.devs.kero.team7.learningrxjava.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import com.devs.kero.team7.learningrxjava.Models.ColorView;
import com.devs.kero.team7.learningrxjava.base.BaseFragment;
import com.devs.kero.team7.learningrxjava.contract.MainScreenContract;
import com.devs.kero.team7.learningrxjava.ui.activities.AddActivity;
import com.devs.kero.team7.learningrxjava.ui.activities.MainActivity;

public abstract class MainScreenFragment  extends BaseFragment implements MainScreenContract.View {

    @Override
    public void Toast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getContexte() {
        return getContext() ;
    }

    @Override
    public void changeLabel(String message) {
        mActivity.getSupportActionBar().setTitle(message);
    }
    @Override
    public void showDeleteTime() {
        ((MainActivity)mActivity).showDeletTime();
    }

    @Override
    public void showNotDeleteTime() {
        ((MainActivity)mActivity).showNoDeletTime();
    }

    @Override
    public FragmentManager getManager() {
        return getFragmentManager();
    }

    @Override
    public void goToUpdateTask(long id , String Title, String Description, String dateTime,
                               String RepeatType, String RepeatBody, String AdvancedReminder,String enddate ,  ColorView colorView) {
        Bundle bundle  = new Bundle();
        Intent intent  = new Intent(((MainActivity)getActivity()),AddActivity.class);
       intent.putExtra("IsUpdate", true);
       bundle.putString("title", Title);
       bundle.putString("description", Description);
       bundle.putString("dateTime", dateTime);
       bundle.putLong("id", id);
             bundle.putString("repeatType", RepeatType);

             bundle.putString("repeatbody", RepeatBody
        );
             bundle.getString("enddate", enddate);
             bundle.putString("advancedReminder", AdvancedReminder);
             bundle.putString("colorView", colorView.toString());
        intent.putExtra("bundle_update", bundle);
        ((MainActivity)getActivity()).startActivity(intent);
    }
}
