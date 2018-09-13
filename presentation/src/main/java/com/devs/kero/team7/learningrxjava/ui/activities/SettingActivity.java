package com.devs.kero.team7.learningrxjava.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.devs.kero.team7.learningrxjava.R;
import com.devs.kero.team7.learningrxjava.base.BaseActivity;
import com.devs.kero.team7.learningrxjava.databinding.ActivitySettingBinding;
import com.devs.kero.team7.learningrxjava.di.HasComponent;
import com.devs.kero.team7.learningrxjava.di.component.DaggerUserComponent;
import com.devs.kero.team7.learningrxjava.di.component.UserComponent;
import com.devs.kero.team7.learningrxjava.presenter.DateTimePickerPresenter;
import com.devs.kero.team7.learningrxjava.presenter.SettingMainPresenter;
import com.devs.kero.team7.learningrxjava.presenter.SettingPresenter;
import com.devs.kero.team7.learningrxjava.ui.fragments.CategorieBaseFragment;
import com.devs.kero.team7.learningrxjava.ui.fragments.DateTimePickerFragment;
import com.devs.kero.team7.learningrxjava.ui.fragments.SettingMainFragment;

public class SettingActivity extends BaseActivity  implements HasComponent<UserComponent> {
     ActivitySettingBinding binding;
    UserComponent component;
    SettingMainFragment fragment;
   CategorieBaseFragment categorieBaseFragment ;
    DateTimePickerFragment dateTimePickerFragment ;
    int whichfragment =0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.component = DaggerUserComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
       setUp();
       fragment = SettingMainFragment.newIntsance();
       addFragment(R.id.setting_framelayout, fragment);
    }

    @Override
    public void setUp() {
     binding= DataBindingUtil.setContentView(this, R.layout.activity_setting);
     setToolbar(binding.settingToolbar);
    }

    public SettingPresenter getPresenter(){
        if(whichfragment==0){
            return (SettingMainPresenter)fragment.getPresenter();
        }else {
        return (DateTimePickerPresenter)dateTimePickerFragment.getPresenter();
        }

    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager() ;
        fm.beginTransaction().replace(R.id.setting_framelayout, fragment).addToBackStack("main_fragment").commit();
    }

    public void setToolbarLabel(String title){
        binding.settingToolbar.setTitle(title);
    }
    public SettingMainPresenter getPresenterfortTheme(){
        return (SettingMainPresenter)fragment.getPresenter();
    }
    public void  goToDateTimePicker(){
        whichfragment = 1 ;
               dateTimePickerFragment = new DateTimePickerFragment() ;
           replaceFragment(dateTimePickerFragment);
    }
    @Override
    public UserComponent getComponent() {
        return component;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(whichfragment!=0){
            whichfragment--  ;
        }
    }
    public void goToRingtoneSelect(){
        categorieBaseFragment = CategorieBaseFragment.newInstance(0);
        replaceFragment(categorieBaseFragment);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == 145)
        {
            Toast.makeText(this, "sdfdsd", Toast.LENGTH_SHORT).show();
            Uri uri = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
        categorieBaseFragment.handleRingtone(uri);

        }
    }

}
