package com.devs.kero.team7.learningrxjava.ui.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.devs.kero.team7.learningrxjava.R;
import com.devs.kero.team7.learningrxjava.base.BaseFragment;
import com.devs.kero.team7.learningrxjava.contract.DateTimePickerContract;
import com.devs.kero.team7.learningrxjava.databinding.FragmentDatetimepickerBinding;
import com.devs.kero.team7.learningrxjava.di.component.UserComponent;
import com.devs.kero.team7.learningrxjava.presenter.DateTimePickerPresenter;

import java.util.List;

import javax.inject.Inject;

public class DateTimePickerFragment extends BaseFragment implements DateTimePickerContract.View {
    FragmentDatetimepickerBinding binding ;
    @Inject
    DateTimePickerPresenter presenter ;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent(UserComponent.class).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_datetimepicker, container, false);
        presenter.setmMvpView(this);
        presenter.init();
         binding.settingMainDatepickerBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 showDatePickerDialog();
             }
         });
         binding.settingMainFirstdayBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 showFirstDayDialog();
             }
         });
         binding.settingMainTimepickerBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 showTimePicker();
             }
         });
       return binding.getRoot() ;
    }

    @Override
    public void initViews(List<String> strings) {
     binding.settingMainDatepickerTextview.setText(strings.get(0));
     binding.settingMainTimepickerTextview.setText(strings.get(1));
     binding.settingMainFirstdayTextview.setText(strings.get(2));
    }

    @Override
    public Context getContexte() {
        return getContext();
    }

    @Override
    public void Toast(String message) {
        Toast.makeText(mActivity, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDatePickerDialog() {
        presenter.showDialog(getFragmentManager(), 3);
    }

    @Override
    public void showTimePicker() {
      presenter.showDialog(getFragmentManager(), 4);
    }

    @Override
    public void showFirstDayDialog() {
presenter.showDialog(getFragmentManager(), 5);
    }

    @Override
    public DateTimePickerContract.Presenter getPresenter() {
        return presenter;
    }
}
