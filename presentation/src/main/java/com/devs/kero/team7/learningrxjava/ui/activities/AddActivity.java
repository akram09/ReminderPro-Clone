package com.devs.kero.team7.learningrxjava.ui.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.devs.kero.team7.learningrxjava.R;
import com.devs.kero.team7.learningrxjava.base.BaseActivity;
import com.devs.kero.team7.learningrxjava.databinding.ActivityAddTaskBinding;
import com.devs.kero.team7.learningrxjava.di.HasComponent;
import com.devs.kero.team7.learningrxjava.di.component.DaggerUserComponent;
import com.devs.kero.team7.learningrxjava.di.component.UserComponent;
import com.devs.kero.team7.learningrxjava.presenter.AddTakPresenter;
import com.devs.kero.team7.learningrxjava.ui.fragments.AddTaskFragment;

public class AddActivity extends BaseActivity implements HasComponent<UserComponent> {
ActivityAddTaskBinding binding ;
    UserComponent component;
    AddTaskFragment fragment ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent= getIntent();
        this.component = DaggerUserComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        if(intent.getBooleanExtra("IsUpdate", false)){
            fragment = AddTaskFragment.newInstance(intent.getBooleanExtra("IsUpdate", false), intent.getBundleExtra("bundle_update"));

        }else {
            fragment = AddTaskFragment.newInstance(intent.getBooleanExtra("IsUpdate", false), new Bundle());

        }
        addFragment(R.id.addtask_framelayout, fragment);
        setUp();

    }

    @Override
    public void setUp() {
     binding = DataBindingUtil.setContentView(this, R.layout.activity_add_task);
    }

    public AddTakPresenter getPresenter(){
        return fragment.getPresenter();
    }
    @Override
    public UserComponent getComponent() {
        return component;
    }
}
