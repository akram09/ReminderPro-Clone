package com.devs.kero.team7.learningrxjava.ui.ViewModels;


import android.arch.lifecycle.ViewModel;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.devs.kero.team7.learningrxjava.ui.adapters.TabLayoutAdapter;
import com.devs.kero.team7.learningrxjava.ui.fragments.ActiveTasksFragment;
import com.devs.kero.team7.learningrxjava.ui.fragments.CompleteTasksFragment;
import com.devs.kero.team7.learningrxjava.ui.fragments.MainScreenFragment;

public class AllTasksViewModel extends ViewModel {
     private ActiveTasksFragment activeTasksFragment ;
     private CompleteTasksFragment completeTasksFragment ;
     private TabLayoutAdapter  adapter ;

    public TabLayoutAdapter getAdapter(FragmentManager manager) {
        if(adapter==null){
            adapter = new TabLayoutAdapter(manager, this);
        }
        return adapter ;
    }

    public MainScreenFragment provideFragmentActive(){
        if(activeTasksFragment==null){
            activeTasksFragment = ActiveTasksFragment.newInstance();

        }
        return  activeTasksFragment;
    }
    public MainScreenFragment provideCompleteFragment(){
        if(completeTasksFragment==null){
            completeTasksFragment = CompleteTasksFragment.newInstance() ;
        }
        return  completeTasksFragment  ;
    }


}
