package com.devs.kero.team7.learningrxjava.ui.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;

import com.devs.kero.team7.learningrxjava.base.BaseActivity;
import com.devs.kero.team7.learningrxjava.ui.ViewModels.AllTasksViewModel;

public class TabLayoutAdapter extends FragmentPagerAdapter {
      AllTasksViewModel viewModel;

    public TabLayoutAdapter(FragmentManager fm, AllTasksViewModel viewModel) {
        super(fm);
        this.viewModel = viewModel;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 : return viewModel.provideFragmentActive();
            case 1 : return  viewModel.provideCompleteFragment();
            default: return  viewModel.provideFragmentActive();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        super.getPageTitle(position);
          if(position==0){
               return "Active";
          }else {
              return "Complete";
          }


    }
}
