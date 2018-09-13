package com.devs.kero.team7.data.repositories;

import android.content.SharedPreferences;

import com.devs.kero.team7.data.Utils.StaticThings;
import com.devs.kero.team7.domain.Repository.DesignDataSource;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.functions.Action;

import static com.devs.kero.team7.data.Utils.StaticThings.CURRENT_THEME;
import static com.devs.kero.team7.data.Utils.StaticThings.DATE_PICKER_STYLE;
import static com.devs.kero.team7.data.Utils.StaticThings.DELETE_CONFIRMATION;
import static com.devs.kero.team7.data.Utils.StaticThings.DisplayView;
import static com.devs.kero.team7.data.Utils.StaticThings.FIRST_DAY_OF_THE_WEEK;
import static com.devs.kero.team7.data.Utils.StaticThings.SHOW_KEYBOARD_IMMEDIATLY;
import static com.devs.kero.team7.data.Utils.StaticThings.TIME_PICKER_STYE;

public class DesignRepository implements DesignDataSource {
    SharedPreferences preferences ;

    @Inject
    public DesignRepository(SharedPreferences editor) {
        this.preferences = editor;
    }

    @Override
    public Observable<Integer> getCurrentTheme() {
        return Observable.just(Integer.valueOf(preferences.getInt(CURRENT_THEME, 0))) ;
    }

    @Override
    public Observable<List<Boolean>> getBooleans(boolean isDatePicker) {
        List<Boolean  > booleans = new ArrayList<>();
        if(isDatePicker){
        booleans.add(Boolean.valueOf(preferences.getBoolean(DATE_PICKER_STYLE,true)));
        booleans.add(Boolean.valueOf(preferences.getBoolean(TIME_PICKER_STYE,true)));
        booleans.add(Boolean.valueOf(preferences.getBoolean(FIRST_DAY_OF_THE_WEEK,true)));
        }else {
             booleans.add(Boolean.valueOf(preferences.getBoolean(DisplayView, true)));
             booleans.add(Boolean.valueOf(preferences.getBoolean(DELETE_CONFIRMATION, true)));
             booleans.add(Boolean.valueOf(preferences.getBoolean(SHOW_KEYBOARD_IMMEDIATLY, true)));
        }
        return Observable.just(booleans);
    }

    @Override
    public Completable setBooleans( final List<Boolean> strings, final boolean isDatePicker) {
       return  Completable.fromAction(new Action() {
           @Override
           public void run() throws Exception {
               if(isDatePicker){
                   preferences.edit().putBoolean(DATE_PICKER_STYLE, strings.get(0)).putBoolean(TIME_PICKER_STYE, strings.get(1))
                        .putBoolean(   FIRST_DAY_OF_THE_WEEK, strings.get(2)).commit();
               }else {
                   preferences.edit().putBoolean(DisplayView, strings.get(0)).putBoolean(DELETE_CONFIRMATION, strings.get(1))
                        .putBoolean(SHOW_KEYBOARD_IMMEDIATLY, strings.get(2)).commit();
               }
           }
       });
    }


    @Override
    public Completable setCurrentTheme(final Integer integer) {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                preferences.edit().putInt(CURRENT_THEME, integer.intValue() ).commit();
            }
        });
    }

}
