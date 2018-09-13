package com.devs.kero.team7.domain.Repository;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

public interface DesignDataSource {
    Observable<Integer> getCurrentTheme();
    Observable<List<Boolean>> getBooleans(boolean isDatePicker);

    Completable setCurrentTheme(Integer integer);
    Completable setBooleans(List<Boolean> strings, boolean isDatePicker);

}
