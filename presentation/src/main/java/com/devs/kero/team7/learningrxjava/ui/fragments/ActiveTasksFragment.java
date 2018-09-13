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

import com.devs.kero.team7.data.Db.ReminderDatabase;
import com.devs.kero.team7.data.repositories.TaskRepository;
import com.devs.kero.team7.learningrxjava.Models.ColorView;
import com.devs.kero.team7.learningrxjava.Models.TaskView;
import com.devs.kero.team7.learningrxjava.R;
import com.devs.kero.team7.learningrxjava.base.BaseFragment;
import com.devs.kero.team7.learningrxjava.contract.ActiveTasksContract;
import com.devs.kero.team7.learningrxjava.databinding.FragmentActiveTasksBinding;
import com.devs.kero.team7.learningrxjava.di.component.UserComponent;
import com.devs.kero.team7.learningrxjava.presenter.ActiveTasksPresenter;
import com.devs.kero.team7.learningrxjava.presenter.MainScreenPresenter;
import com.devs.kero.team7.learningrxjava.ui.activities.MainActivity;
import com.devs.kero.team7.learningrxjava.ui.adapters.TasksAdapter;

import java.text.ParseException;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;


public class ActiveTasksFragment extends MainScreenFragment implements ActiveTasksContract.View {

 FragmentActiveTasksBinding binding ;
 AlertDialog alertDialog;

    public ActiveTasksFragment() {
        // Required empty public constructor
    }
    @Inject
    ActiveTasksPresenter presenter ;

    public static ActiveTasksFragment newInstance() {
        ActiveTasksFragment fragment = new ActiveTasksFragment();

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
       binding = DataBindingUtil.inflate(inflater, R.layout.fragment_active_tasks,container, false );
      presenter.setmMvpView(this);
      binding.activetaskRecylerview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
      binding.activetaskRecylerview.setHasFixedSize(true);
        presenter.getTasks();
        return binding.getRoot();
    }



    @Override
    public void showTasks(TasksAdapter adapter) {
     binding.activetaskRecylerview.setAdapter(adapter);
     binding.activetasksTextview.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showNoTasks() {
      binding.activetasksTextview.setVisibility(View.VISIBLE);
    }
    @Override
    public void deleteTasks() {
        
            presenter.DeleteTask();

    }

    @Override
    public void handleOnBackPressed() {
        presenter.OnBackPressed();
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
