package com.devs.kero.team7.learningrxjava.ui.fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import com.devs.kero.team7.domain.UseCases.BaseCompletableUseCase;
import com.devs.kero.team7.domain.UseCases.UpdateUseCase;
import com.devs.kero.team7.learningrxjava.Models.ColorView;
import com.devs.kero.team7.learningrxjava.R;
import com.devs.kero.team7.learningrxjava.base.BaseFragment;
import com.devs.kero.team7.learningrxjava.contract.AddTaskContract;
import com.devs.kero.team7.learningrxjava.databinding.FragmentAddTaskBinding;
import com.devs.kero.team7.learningrxjava.di.component.UserComponent;
import com.devs.kero.team7.learningrxjava.presenter.AddTakPresenter;
import com.devs.kero.team7.learningrxjava.ui.activities.AddActivity;

import java.util.Date;

import javax.inject.Inject;

public class AddTaskFragment extends BaseFragment  implements AddTaskContract.View {
    public static final String ISUPDATE = "isupdate?";
    public static final String INTENET = "intenet";

    @Inject
    UpdateUseCase   updateUseCase ;
   FragmentAddTaskBinding binding;
   @Inject
   AddTakPresenter presenter ;
   @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_task, container,false );
        presenter.setmVpView(this);
        if(getArguments()==null){
            presenter.init(false ,null);
        }else{
            presenter.init(!getArguments().isEmpty() && !(getArguments()==null), getArguments());
        }
        binding.addtaskBtnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.showDatePicker(getContext());
            }
        });
        binding.addtaskBtnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.showTimePicker(getContext());
            }
        });
        binding.addtaskBtnRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.showRepeatDialog(getContext(), getFragmentManager());

            }
        });
        binding.addtaskBtnEnddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               presenter.showEndDateDialog(getContext());
            }
        });
        binding.addtaskBtnAdvanceedreminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.showAdvancedReminder(getContext(), getFragmentManager());
            }
        });
        binding.addtaskImagebtnCategorie.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                presenter.showCategorieDialog(getContext(),getFragmentManager());
            }
        });
        binding.addtaskFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              presenter.addTask(binding.addtaskTitleedittext.getText().toString(), binding.addtaskDescription.getText().toString());
            }
        });
        return binding.getRoot();
    }

    public AddTaskFragment() {
        // Required empty public constructor
    }


    public static AddTaskFragment newInstance(boolean isUpdate , Bundle intent) {
        AddTaskFragment fragment = new AddTaskFragment();
       if(isUpdate){
           fragment.setArguments(intent);
       }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent(UserComponent.class).inject(this);
    }

    @Override
    public Context getContexte() {
        return getContext();
    }

    @Override
    public void Toast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void goBackToGetTasks() {
        ((AddActivity)getActivity()).finish();
    }

    @Override
    public void changeDateTextView(String date) {
        binding.addtaskTextviewDate.setText(date);
    }
    @Override
    public void hangeTimeTextView(String string) {
      binding.addtaskTextviewTime.setText(string);
    }

    @Override
    public void initViews(String date, String time) {
        binding.addtaskTextviewDate.setText(date);
        binding.addtaskTextviewTime.setText(time);
       binding.addtaskRelativelayout.setVisibility(View.GONE);
    }

    @Override
    public void changeRepeatTypeTextView(String repeat) {
        binding.addtaskTextviewRepeat.setText(repeat);
    }
    @Override
    public void showEndDate() {
       binding.addtaskRelativelayout.setVisibility(View.VISIBLE);
    }
    @Override
    public void hideEndDate() {
binding.addtaskRelativelayout.setVisibility(View.GONE);
    }
    @Override
    public AddTakPresenter getPresenter() {
        return presenter;
    }
    @Override
    public String[] getStringArray(int array) {
        return getResources().getStringArray(array);
    }

    @Override
    public void changeEndDateTextView(String string) {
        binding.addtaskTextviewEnddate.setText(string);
    }

    @Override
    public void changeAdvancedTextView(String string) {
        binding.addtaskTextviewAdvancedreminder.setText(string);
    }

    @Override
    public void changeCategorie(@DrawableRes int drawable) {
        binding.addtaskImagebtnCategorie.setImageResource(drawable);
    }

    @Override
    public void showErrorDateDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Message").setMessage("Please set reminder for upcoming date (or) time").setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
        builder.create().show();
    }

    @Override
    public void showErrorEndDateDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Message").setMessage("End date should  be greater than start date ").setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
        builder.create().show();

    }

    @Override
    public BaseCompletableUseCase getUpdateUseCase() {
        return updateUseCase;
    }

    @Override
    public void initViewForUpdate(String Title, String description, String date, String time, String RepeatBody, String EndDate, String AdvancedReminder, ColorView colorView) {
        initViews(date, time);
        binding.addtaskTitleedittext.setText(Title);
        binding.addtaskDescription.setText(description);
        binding.addtaskTextviewAdvancedreminder.setText(AdvancedReminder);
        if(RepeatBody==null){
            binding.addtaskTextviewRepeat.setText("No Repeating");
        }else {
            binding.addtaskTextviewRepeat.setText(RepeatBody);
            binding.addtaskRelativelayout.setVisibility(View.VISIBLE);
            binding.addtaskTextviewEnddate.setText(EndDate);
        }
        switch (colorView){
            case Blue: binding.addtaskImagebtnCategorie.setImageResource(R.drawable.ic_star_blue_24dp);
                break;
            case Yellow: binding.addtaskImagebtnCategorie.setImageResource(R.drawable.ic_star_yellow_24dp);
                break;
            case Green: binding.addtaskImagebtnCategorie.setImageResource(R.drawable.ic_star_green_24dp);
                break;
            case Red: binding.addtaskImagebtnCategorie.setImageResource(R.drawable.ic_star_red_24dp);
                break;
            case Default:binding.addtaskImagebtnCategorie.setImageResource(R.drawable.ic_star_border_black_24dp);
                break;
        }
    }
}
