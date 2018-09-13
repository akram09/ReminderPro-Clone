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

import com.devs.kero.team7.domain.entities.Categorie;
import com.devs.kero.team7.learningrxjava.R;
import com.devs.kero.team7.learningrxjava.base.BaseFragment;
import com.devs.kero.team7.learningrxjava.contract.MainSettingContract;
import com.devs.kero.team7.learningrxjava.databinding.SettingFragmentMainBinding;
import com.devs.kero.team7.learningrxjava.di.component.UserComponent;
import com.devs.kero.team7.learningrxjava.presenter.SettingMainPresenter;
import com.devs.kero.team7.learningrxjava.ui.activities.SettingActivity;

import javax.inject.Inject;

public class SettingMainFragment extends BaseFragment implements MainSettingContract.View {
     SettingFragmentMainBinding binding;

     @Inject
     SettingMainPresenter presenter;

    public SettingMainFragment() {
    }

    public static  SettingMainFragment newIntsance(){
         return new SettingMainFragment();
     }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent(UserComponent.class).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = DataBindingUtil.inflate(inflater, R.layout.setting_fragment_main, container, false);
        presenter.setmMvpView(this);
        presenter.init();
        binding.settingMainThemeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSelectThemeDialog();
            }
        });
        binding.settingMainDeleteConfirmationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDeleteConfirmationDialog();
            }
        });
        binding.settingMainReminderDisplayViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDisplayViewDialog();
            }
        });
        binding.settingMainShowkeyboardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showShowKeyboardImmediately();
            }
        });
        binding.settingMainDatetimepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              goToDateTimePickerFragment();
            }
        });
        binding.settingMainNotificationSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToNotificationSound();
            }
        });
        binding.settingMainNotificationTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToNotificationTitle();
            }
        });
        binding.settingMainPopupNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToPopupNotification();
            }
        });
        binding.settingMainVibration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToVibration();
            }
        });
        binding.settingMainTurnScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToTurnScreen();
            }
        });
        return binding.getRoot();
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
    public void showSelectThemeDialog() {
    presenter.showDialog(getFragmentManager());
    }

    @Override
    public MainSettingContract.Presenter getPresenter() {
        return presenter;
    }

    @Override
    public void showDisplayViewDialog() {
       presenter.showDialogue(getFragmentManager(), 0);
    }

    @Override
    public void showDeleteConfirmationDialog() {
        presenter.showDialogue(getFragmentManager(), 1);
    }

    @Override
    public void showShowKeyboardImmediately() {
     presenter.showDialogue(getFragmentManager(),2);
    }

    @Override
    public void changeTextView(String text, int which) {
        switch (which){
            case 0 : binding.settingMainThemeTextview.setText(text);
            break;
            case 1: binding.settingMainReminderDisplayViewTextview.setText(text);
            break;
            case 2 : binding.settingMainDeleteConfirmationTextview.setText(text);
            break;
            case 3 : binding.settingMainShowkeyboardTextview.setText(text);
            return;
        }
    }

    @Override
    public void goToDateTimePickerFragment() {
        ((SettingActivity)getActivity()).goToDateTimePicker();
    }

    @Override
    public void goToNotificationSound() {
        ((SettingActivity)getActivity()).goToRingtoneSelect();
    }

    @Override
    public void goToVibration() {
        ((SettingActivity)getActivity()).replaceFragment(CategorieBooleans.newInstance(true));

    }

    @Override
    public void goToPopupNotification() {
        ((SettingActivity)getActivity()).replaceFragment(CategorieBaseFragment.newInstance(1));
    }

    @Override
    public void goToTurnScreen() {
        ((SettingActivity)getActivity()).replaceFragment(CategorieBooleans.newInstance(false));
    }

    @Override
    public void goToNotificationTitle() {
        ((SettingActivity)getActivity()).replaceFragment(CategorieBaseFragment.newInstance(2));
    }
}
