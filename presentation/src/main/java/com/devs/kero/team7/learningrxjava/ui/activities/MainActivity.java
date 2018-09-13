package com.devs.kero.team7.learningrxjava.ui.activities;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.devs.kero.team7.learningrxjava.Models.TaskView;
import com.devs.kero.team7.learningrxjava.R;
import com.devs.kero.team7.learningrxjava.base.BaseActivity;
import com.devs.kero.team7.learningrxjava.databinding.ActivityHomeBinding;
import com.devs.kero.team7.learningrxjava.di.HasComponent;
import com.devs.kero.team7.learningrxjava.di.component.DaggerUserComponent;
import com.devs.kero.team7.learningrxjava.di.component.UserComponent;
import com.devs.kero.team7.learningrxjava.presenter.MainScreenPresenter;
import com.devs.kero.team7.learningrxjava.ui.ViewModels.AllTasksViewModel;
import com.devs.kero.team7.learningrxjava.ui.adapters.TasksAdapter;
import com.devs.kero.team7.learningrxjava.ui.fragments.ActiveTasksFragment;


public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, HasComponent<UserComponent> {
    ActivityHomeBinding binding;
    AllTasksViewModel viewModel;
    UserComponent component;
    boolean IsDelete = false;
    private boolean mToolBarNavigationListenerIsRegistered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        this.component = DaggerUserComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        setUp();
        
        binding.alltasksInclude.alltasksFloatingbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.alltasks_toolbar_menu, menu);
        menu.findItem(R.id.alltasks_toolbar_menu_deletasks).setVisible(false);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (IsDelete) {
            menu.findItem(R.id.alltasks_toolbar_menu_deletasks).setVisible(true);
            menu.findItem(R.id.alltasks_toolbar_menu_search).setVisible(false);
        } else {
            menu.findItem(R.id.alltasks_toolbar_menu_deletasks).setVisible(false);
            menu.findItem(R.id.alltasks_toolbar_menu_search).setVisible(true);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.alltasks_toolbar_menu_deletasks:
                if(binding.alltasksInclude.alltasksTablayout.getSelectedTabPosition()==0){
                    viewModel.provideFragmentActive().deleteTasks();
                }else {
                    viewModel.provideCompleteFragment().deleteTasks();
                }
                break;


        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Toast.makeText(this, "zertyuygfd", Toast.LENGTH_SHORT).show();
       switch (item.getItemId()){
           case R.id.alltasks_drawerlayout_menu_setting :
               Intent intent = new Intent(this, SettingActivity.class) ;
               startActivity(intent);
               break;
       }
       binding.alltasksDrawerlayout.closeDrawer(Gravity.START);

        return true;
    }

    @Override
    public void setUp() {
        setToolbar(binding.alltasksInclude.alltasksToolbar);
        setDrawer(binding.alltasksDrawerlayout, binding.alltasksInclude.alltasksToolbar);
        viewModel = ViewModelProviders.of(this).get(AllTasksViewModel.class);
        binding.alltasksInclude.alltasksViewpager.setAdapter(viewModel.getAdapter(getSupportFragmentManager()));
        binding.alltasksInclude.alltasksTablayout.setupWithViewPager(binding.alltasksInclude.alltasksViewpager);
        binding.alltasksInclude.alltasksIndicator.setTabLayoutAndViewPager(binding.alltasksInclude.alltasksTablayout,
                binding.alltasksInclude.alltasksViewpager);
        binding.alltasksNavigationview.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        if (IsDelete) {

            if(binding.alltasksInclude.alltasksTablayout.getSelectedTabPosition()==0){
                viewModel.provideFragmentActive().handleOnBackPressed();
            }else {
                viewModel.provideCompleteFragment().handleOnBackPressed();
            }

            showNoDeletTime();
        } else {
            if (binding.alltasksDrawerlayout.isDrawerOpen(Gravity.START)) {
                binding.alltasksDrawerlayout.closeDrawer(Gravity.START);
            } else {
                super.onBackPressed();
            }
        }
    }

    @Override
    public UserComponent getComponent() {
        return component;
    }
public MainScreenPresenter getPresenter(){
    if(binding.alltasksInclude.alltasksTablayout.getSelectedTabPosition()==0){
       return viewModel.provideFragmentActive().getPresenter();
    }else {
        return viewModel.provideCompleteFragment().getPresenter();
    }
}
      public void showDeletTime(){
        IsDelete = true ;
        invalidateOptionsMenu();
        enableViews(true);

    }
    public void showNoDeletTime(){
        IsDelete = false ;
        invalidateOptionsMenu();
        enableViews(false);

}
 private void enableViews(boolean enable) {

        if (enable) {
            binding.alltasksDrawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            toggle.setDrawerIndicatorEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            if (!mToolBarNavigationListenerIsRegistered) {
                toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Doesn't have to be onBackPressed
                        onBackPressed();
                    }
                });

                mToolBarNavigationListenerIsRegistered = true;
            }

        } else {
            //You must regain the power of swipe for the drawer.
            binding.alltasksDrawerlayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);

            // Remove back button
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            // Show hamburger
            toggle.setDrawerIndicatorEnabled(true);
            // Remove the/any drawer toggle listener
            toggle.setToolbarNavigationClickListener(null);
            mToolBarNavigationListenerIsRegistered = false;
        }
    }
}