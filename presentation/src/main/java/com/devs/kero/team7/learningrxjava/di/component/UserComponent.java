package com.devs.kero.team7.learningrxjava.di.component;
import com.devs.kero.team7.learningrxjava.di.PerActivity;
import com.devs.kero.team7.learningrxjava.di.modules.ActivityModule;
import com.devs.kero.team7.learningrxjava.di.modules.UserModule;
import com.devs.kero.team7.learningrxjava.ui.fragments.ActiveTasksFragment;
import com.devs.kero.team7.learningrxjava.ui.fragments.AddTaskFragment;
import com.devs.kero.team7.learningrxjava.ui.fragments.CategorieBaseFragment;
import com.devs.kero.team7.learningrxjava.ui.fragments.CategorieBooleans;
import com.devs.kero.team7.learningrxjava.ui.fragments.CompleteTasksFragment;
import com.devs.kero.team7.learningrxjava.ui.fragments.DateTimePickerFragment;
import com.devs.kero.team7.learningrxjava.ui.fragments.SettingMainFragment;

import java.util.Date;

import dagger.Component;


@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, UserModule.class})
public interface UserComponent extends ActivityComponent {
  void inject(ActiveTasksFragment userListFragment);
  void inject(CompleteTasksFragment completeTasksFragment);
  void inject(AddTaskFragment addTaskFragment);
  void inject(SettingMainFragment settingMainFragment);
  void inject(DateTimePickerFragment dateTimePickerFragment);
  void inject(CategorieBaseFragment categorieBaseFragment);
  void inject(CategorieBooleans categorieBooleans);

}
