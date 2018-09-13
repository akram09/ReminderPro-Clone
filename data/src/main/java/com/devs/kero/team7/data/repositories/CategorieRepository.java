 package com.devs.kero.team7.data.repositories;

import android.content.SharedPreferences;

import com.devs.kero.team7.data.R;

import com.devs.kero.team7.data.Utils.StaticThings;
import com.devs.kero.team7.data.dataModel.CategorieData;

import com.devs.kero.team7.data.mapper.PopupMapper;
import com.devs.kero.team7.domain.Repository.CategoriesDataSource;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.functions.Action;

import static com.devs.kero.team7.data.Utils.StaticThings.NOTIFICATION_TITLE;
import static com.devs.kero.team7.data.Utils.StaticThings.NOTIFICATION_TITLE_DISTINCT;
import static com.devs.kero.team7.data.Utils.StaticThings.POPUP_NOTIFICATION_DISTINCT;
import static com.devs.kero.team7.data.Utils.StaticThings.PopupNotification;
import static com.devs.kero.team7.data.Utils.StaticThings.SOUND_PATH_DISTINCT;
import static com.devs.kero.team7.data.Utils.StaticThings.SoundPath;
import static com.devs.kero.team7.data.Utils.StaticThings.Vibration;
import static com.devs.kero.team7.data.Utils.StaticThings.turnOnScreen;

public class CategorieRepository implements CategoriesDataSource {
    private static CategorieData[] categorieTable  ;
    SharedPreferences preferences ;

    @Inject
    public CategorieRepository( SharedPreferences preferences) {
        this.preferences = preferences;
        categorieTable = new CategorieData[]{new CategorieData(0,preferences.getString(SoundPath,String.valueOf(R.raw.alarm) ) ,
                preferences.getBoolean(Vibration, true ),PopupMapper.to(
                preferences.getString(StaticThings.PopupNotification, "NoOnLockScreen")),
                preferences.getBoolean(turnOnScreen, true), preferences.getString(NOTIFICATION_TITLE, "Reminder")
                ),new CategorieData(1,preferences.getString(SoundPath+String.valueOf(1),String.valueOf(R.raw.alarm) ) ,
                preferences.getBoolean(Vibration+String.valueOf(1), true ),PopupMapper.to(
                preferences.getString(StaticThings.PopupNotification+String.valueOf(1), "NoOnLockScreen")),
                preferences.getBoolean(turnOnScreen+String.valueOf(1), true), preferences.getString(NOTIFICATION_TITLE+String.valueOf(1), "Reminder")
        ), new CategorieData(2,preferences.getString(SoundPath+String.valueOf(2),String.valueOf(R.raw.alarm) ) ,
                preferences.getBoolean(Vibration+String.valueOf(2), true ),PopupMapper.to(
                preferences.getString(StaticThings.PopupNotification+String.valueOf(2), "NoOnLockScreen")),
                preferences.getBoolean(turnOnScreen+String.valueOf(2), true), preferences.getString(NOTIFICATION_TITLE+String.valueOf(2), "Reminder")
        ), new CategorieData(3,preferences.getString(SoundPath+String.valueOf(3),String.valueOf(R.raw.alarm) ) ,
                        preferences.getBoolean(Vibration+String.valueOf(3), true ),PopupMapper.to(
                        preferences.getString(StaticThings.PopupNotification+String.valueOf(3), "NoOnLockScreen")),
                        preferences.getBoolean(turnOnScreen+String.valueOf(3), true), preferences.getString(NOTIFICATION_TITLE+String.valueOf(3), "Reminder")
                ), new CategorieData(4,preferences.getString(SoundPath+String.valueOf(4),String.valueOf(R.raw.alarm) ) ,
                preferences.getBoolean(Vibration+String.valueOf(4), true ),PopupMapper.to(
                preferences.getString(StaticThings.PopupNotification+String.valueOf(4), "NoOnLockScreen")),
                preferences.getBoolean(turnOnScreen+String.valueOf(4), true), preferences.getString(NOTIFICATION_TITLE+String.valueOf(4), "Reminder")
        )}; }

    @Override
    public Observable<List<String>> getCategoriesString(Integer Distinct) throws ParseException {
        List<String> strings = new ArrayList<>();
        for (CategorieData categorieData:categorieTable
                ) {
           switch (Distinct.intValue()){
               case SOUND_PATH_DISTINCT :
                   strings.add(categorieData.getSoundPath());
                   break;
               case POPUP_NOTIFICATION_DISTINCT :
                   strings.add(PopupMapper.from(categorieData.getPopupNotification()));
                    break;
               case NOTIFICATION_TITLE_DISTINCT: strings.add(categorieData.getNotificationTitle());
               break;
           }
        }
        return Observable.just(strings);
    }

    @Override
    public Observable<List<Boolean>> getCategoriesVibration(Boolean isVibration) {
        List<Boolean> booleans = new ArrayList<>();
        for (CategorieData categorieData : categorieTable){
            if(isVibration.booleanValue()){
                booleans.add(categorieData.getVibration());
            }else {
                booleans.add(categorieData.getTurnOnScreen());
            }
        }
        return Observable.just(booleans);
    }

    @Override
    public Completable UpdateCategoriesStrings(List<String> list, Integer Distinct) {
        final Integer Distincte = Distinct ;
        final SharedPreferences.Editor editor =  preferences.edit();

        final List<String> list1 = list ;
       return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                for (int i = 0 ; i<list1.size(); i++
                        ) {
                    switch (Distincte.intValue()){
                        case SOUND_PATH_DISTINCT :
                            categorieTable[i].setSoundPath(list1.get(i));
                            editor.putString(SoundPath+String.valueOf(i), list1.get(i)).commit();
                            break;
                        case POPUP_NOTIFICATION_DISTINCT :
                            categorieTable[i].setPopupNotification(PopupMapper.to(list1.get(i)));
                            editor.putString(PopupNotification+String.valueOf(i), list1.get(i)).commit();
                            break;
                        case NOTIFICATION_TITLE_DISTINCT:
                            categorieTable[i].setNotificationTitle(list1.get(i));
                            editor.putString(NOTIFICATION_TITLE+String.valueOf(i), list1.get(i)).commit();
                            break;
                    }
                }
            }
        }) ;


    }

    @Override
    public Completable UpdateCategoriesBool(List<Boolean> booleans, Boolean isVibration) {
       final List<Boolean> booleans1 = booleans;
       final SharedPreferences.Editor editor = preferences.edit();
        final Boolean b= isVibration;
         return Completable.fromAction(new Action() {
             @Override
             public void run() throws Exception {
                 for (int i = 0; i<booleans1.size(); i++){
                     if(b.booleanValue()){
                         categorieTable[i].setVibration(booleans1.get(i));
                         editor.putBoolean(Vibration+String.valueOf(i), booleans1.get(i)).commit();
                     }else {
                         categorieTable[i].setTurnOnScreen(booleans1.get(i));
                         editor.putBoolean(turnOnScreen+String.valueOf(i), booleans1.get(i)).commit();
                     }
                 }
             }
         }) ;

    }
}
