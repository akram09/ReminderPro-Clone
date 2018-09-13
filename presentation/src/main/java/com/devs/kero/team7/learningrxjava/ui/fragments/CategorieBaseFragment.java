package com.devs.kero.team7.learningrxjava.ui.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.devs.kero.team7.learningrxjava.R;
import com.devs.kero.team7.learningrxjava.base.BaseFragment;
import com.devs.kero.team7.learningrxjava.contract.CategorieBaseContract;
import com.devs.kero.team7.learningrxjava.databinding.FragmentCategorieSettingBinding;
import com.devs.kero.team7.learningrxjava.di.component.UserComponent;
import com.devs.kero.team7.learningrxjava.presenter.CategorieBasePresenter;
import com.devs.kero.team7.learningrxjava.ui.activities.SettingActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static android.app.Activity.RESULT_OK;

public  class CategorieBaseFragment extends BaseFragment implements CategorieBaseContract.View {
    private FragmentCategorieSettingBinding binding ;
    private static final String PARAMS_1 = "params1";

    @Inject
    CategorieBasePresenter categorieBasePresenter;
    public static  CategorieBaseFragment newInstance(int which){
         CategorieBaseFragment categorieBaseFragment = new CategorieBaseFragment();
         Bundle bundle= new Bundle();
         bundle.putInt(PARAMS_1, which);
         categorieBaseFragment.setArguments(bundle);
         return categorieBaseFragment ;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent(UserComponent.class).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
         binding = DataBindingUtil.inflate(inflater, R.layout.fragment_categorie_setting, container, false);
         categorieBasePresenter.setmMvpView(this);
             binding.categorieFragmentSettingBluecategoryCheckbox.setVisibility(View.GONE);
             binding.categorieFragmentSettingGreencategoryCheckbox.setVisibility(View.GONE);
             binding.categorieFragmentSettingNocategoryCheckbox.setVisibility(View.GONE);
             binding.categorieFragmentSettingRedcategoryCheckbox.setVisibility(View.GONE);
             binding.categorieFragmentSettingYellowcategoryCheckbox.setVisibility(View.GONE);
             categorieBasePresenter.initViews(getArguments().getInt(PARAMS_1));
            binding.categorieFragmentSettingBluecategoryBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    categorieBasePresenter.ItemClicked(2);
                }
            });
            binding.categorieFragmentSettingGreencategoryBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    categorieBasePresenter.ItemClicked(3);
                }
            });
            binding.categorieFragmentSettingRedcategoryBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    categorieBasePresenter.ItemClicked(1);
                }
            });
            binding.categorieFragmentSettingNocategoryBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    categorieBasePresenter.ItemClicked(0);
                }
            });
            binding.categorieFragmentSettingYellowcategoryBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    categorieBasePresenter.ItemClicked(4);
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
    public void initViews(List<String> strings) {
       if(getArguments().getInt(PARAMS_1)==0){
           strings =getTitles(strings);
       }
        binding.categorieFragmentSettingNocategoryTextview.setText(strings.get(0));
        binding.categorieFragmentSettingYellowcategoryTextview.setText(strings.get(4));
        binding.categorieFragmentSettingGreencategoryTextview.setText(strings.get(3));
        binding.categorieFragmentSettingBluecategoryTextview.setText(strings.get(2));
        binding.categorieFragmentSettingCategoryredTextview.setText(strings.get(1));
        }
    @Override
    public void searchForRingtnes(String uri , boolean isDefault) {
        Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_NOTIFICATION);
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "Select Tone");
        if(!isDefault){
            intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI , Uri.parse(uri));
        }else{
            intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI, (Uri) null);
        }
        mActivity.startActivityForResult(intent, 145);
    }

    private List<String > getTitles (List<String> strings){
        List<String > list = new ArrayList<>();
        for (String string: strings
             ) {
            list.add(string.equals(String.valueOf(R.raw.alarm))?"Default ":
                    RingtoneManager.getRingtone(getContext(),Uri.parse(string) ).getTitle(getContext()));
        }
        return list ;
    }

    @Override
    public void handleRingtone(Uri uri) {
        categorieBasePresenter.RingtoneReturned(uri);
    }
}
