package com.devs.kero.team7.learningrxjava.ui.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.devs.kero.team7.learningrxjava.Models.TaskView;
import com.devs.kero.team7.learningrxjava.R;
import com.devs.kero.team7.learningrxjava.base.BaseFragment;
import com.devs.kero.team7.learningrxjava.contract.ActiveTasksContract;
import com.devs.kero.team7.learningrxjava.contract.CompleteTasksContract;
import com.devs.kero.team7.learningrxjava.databinding.FragmentCompleteTasksBinding;
import com.devs.kero.team7.learningrxjava.di.component.UserComponent;
import com.devs.kero.team7.learningrxjava.presenter.CompleteTasksPresenter;
import com.devs.kero.team7.learningrxjava.presenter.MainScreenPresenter;
import com.devs.kero.team7.learningrxjava.ui.adapters.TasksAdapter;

import java.text.ParseException;
import java.util.List;

import javax.inject.Inject;


public class CompleteTasksFragment extends MainScreenFragment implements CompleteTasksContract.View {

  FragmentCompleteTasksBinding binding ;
  @Inject
  CompleteTasksPresenter presenter;
    AlertDialog alertDialog ;

    public CompleteTasksFragment() {
        // Required empty public constructor
    }


    public static CompleteTasksFragment newInstance() {
        CompleteTasksFragment fragment = new CompleteTasksFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent(UserComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         binding = DataBindingUtil.inflate(inflater, R.layout.fragment_complete_tasks, container, false);
        presenter.setmMvpView(this);
        binding.completedtasksRecyclerview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.completedtasksRecyclerview.setHasFixedSize(true);
        presenter.getTasks();
        return binding.getRoot();
    }
    @Override
    public void showTasks(TasksAdapter adapter) {
        binding.completedtasksRecyclerview.setAdapter(adapter);
        binding.completedtasksTextview.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showNoTasks() {
         binding.completedtasksTextview.setVisibility(View.VISIBLE);
    }

    @Override
    public void handleOnBackPressed() {
        presenter.OnBackPressed();
    }

    @Override
    public void deleteTasks() {

            presenter.DeleteTask();

    }

    @Override
    public void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                presenter.handleDialogClicks(true);
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                presenter.handleDialogClicks(false);
            }
        }).setMessage("Are you sure you want to delete ALL selected reminders").setTitle("Confirmation");
         alertDialog= builder.create();
        alertDialog.show();
    }

    @Override
    public void DisableConfirmationDialog() {
        alertDialog.dismiss();
    }

    @Override
    public MainScreenPresenter getPresenter() {
        return presenter;
    }
}
