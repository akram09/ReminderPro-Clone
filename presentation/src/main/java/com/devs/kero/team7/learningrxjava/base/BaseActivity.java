package com.devs.kero.team7.learningrxjava.base;



import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.devs.kero.team7.learningrxjava.R;
import com.devs.kero.team7.learningrxjava.Utils.MyApp;
import com.devs.kero.team7.learningrxjava.di.HasComponent;
import com.devs.kero.team7.learningrxjava.di.component.ApplicationComponent;
import com.devs.kero.team7.learningrxjava.di.modules.ActivityModule;

public abstract class BaseActivity extends AppCompatActivity {
    public ActionBarDrawerToggle toggle;
    public abstract void setUp();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getApplicationComponent().inject(this);
    }

    private void showSnackBar(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                message, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView
                .findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(this, R.color.white));
        snackbar.show();
    }
    public void  setToolbar(Toolbar toolbar){
        setSupportActionBar(toolbar);
    }
    public void setDrawer(DrawerLayout drawer ,  Toolbar toolbar){
         toggle= new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open_drawer, R.string.close_drawer ){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                hideKeyboard() ;
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
         toggle.syncState();


    }
    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    protected void addFragment(int containerViewId, Fragment fragment) {
        final FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((MyApp) getApplication()).getApplicationComponent();
    }

    protected  void StartActivityIntent(Class<BaseActivity> baseActivityClass  ){
        Intent intent = new Intent(this, baseActivityClass);
        startActivity(intent);
    }
    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }
}
