package com.devs.kero.team7.learningrxjava.ui.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.devs.kero.team7.learningrxjava.R;
import com.devs.kero.team7.learningrxjava.base.BaseFragment;
import com.devs.kero.team7.learningrxjava.contract.CategoriesBooleansContract;
import com.devs.kero.team7.learningrxjava.databinding.FragmentCategorieSettingBinding;
import com.devs.kero.team7.learningrxjava.di.component.UserComponent;
import com.devs.kero.team7.learningrxjava.presenter.CategorieBooleansPresenter;

import java.util.List;

import javax.inject.Inject;

public class CategorieBooleans extends BaseFragment implements CategoriesBooleansContract.View {
    FragmentCategorieSettingBinding binding ;
    @Inject
    CategorieBooleansPresenter presenter ;
    public static final String PARAMS_1 = "params1";
    public static CategorieBooleans newInstance(boolean isVibration){
        CategorieBooleans categorieBooleans = new CategorieBooleans() ;
        Bundle bundle = new Bundle();
        bundle.putBoolean(PARAMS_1, isVibration);
        categorieBooleans.setArguments(bundle);
        return categorieBooleans ;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent(UserComponent.class).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_categorie_setting, container, false);
        presenter.setmMvpView(this);
        presenter.initviews(getArguments().getBoolean(PARAMS_1));
        binding.categorieFragmentSettingYellowcategoryCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                presenter.handleClicks(b, 4);
            }
        });
        binding.categorieFragmentSettingYellowcategoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.categorieFragmentSettingYellowcategoryCheckbox.setChecked(!binding.categorieFragmentSettingYellowcategoryCheckbox.isChecked());
            }
        });
        binding.categorieFragmentSettingRedcategoryCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                presenter.handleClicks(b, 1);
            }
        });
        binding.categorieFragmentSettingRedcategoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.categorieFragmentSettingRedcategoryCheckbox.setChecked(!binding.categorieFragmentSettingRedcategoryCheckbox.isChecked());
            }
        });
        binding.categorieFragmentSettingNocategoryCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                presenter.handleClicks(b, 0);
            }
        });
        binding.categorieFragmentSettingNocategoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.categorieFragmentSettingNocategoryCheckbox.setChecked(!binding.categorieFragmentSettingNocategoryCheckbox.isChecked());
            }
        });
        binding.categorieFragmentSettingGreencategoryCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                presenter.handleClicks(b, 3);
            }
        });
        binding.categorieFragmentSettingGreencategoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.categorieFragmentSettingGreencategoryCheckbox.setChecked(!binding.categorieFragmentSettingGreencategoryCheckbox.isChecked());
            }
        });
        binding.categorieFragmentSettingBluecategoryCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                presenter.handleClicks(b, 2);
            }
        });
        binding.categorieFragmentSettingBluecategoryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.categorieFragmentSettingBluecategoryCheckbox.setChecked(!binding.categorieFragmentSettingBluecategoryCheckbox.isChecked());
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
    public void initViews(List<Boolean> booleans) {
        binding.categorieFragmentSettingCategoryredTextview.setText(booleans.get(1)?"enabled":"disabled");
        binding.categorieFragmentSettingRedcategoryCheckbox.setChecked(booleans.get(1));
        binding.categorieFragmentSettingNocategoryTextview.setText(booleans.get(0)?"enabled":"disabled");
        binding.categorieFragmentSettingNocategoryCheckbox.setChecked(booleans.get(0));
        binding.categorieFragmentSettingBluecategoryTextview.setText(booleans.get(2)?"enabled":"disabled");
        binding.categorieFragmentSettingBluecategoryCheckbox.setChecked(booleans.get(2));
        binding.categorieFragmentSettingGreencategoryBtn.setText(booleans.get(3)?"enabled":"disabled");
        binding.categorieFragmentSettingGreencategoryCheckbox.setChecked(booleans.get(3));
        binding.categorieFragmentSettingYellowcategoryTextview.setText(booleans.get(4)?"enabled":"disabled");
        binding.categorieFragmentSettingYellowcategoryCheckbox.setChecked(booleans.get(4));

    }
}
