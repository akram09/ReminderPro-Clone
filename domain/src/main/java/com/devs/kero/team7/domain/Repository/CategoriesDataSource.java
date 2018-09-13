package com.devs.kero.team7.domain.Repository;

import com.devs.kero.team7.domain.entities.Categorie;

import java.text.ParseException;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.Observable;


public interface CategoriesDataSource {
    Observable<List<String >> getCategoriesString(Integer Distinct ) throws ParseException;
    Observable<List<Boolean>> getCategoriesVibration(Boolean isVibration);

    Completable UpdateCategoriesStrings(List<String> list , Integer Distinct ) ;
    Completable UpdateCategoriesBool(List<Boolean> booleans, Boolean isVibration);



}
