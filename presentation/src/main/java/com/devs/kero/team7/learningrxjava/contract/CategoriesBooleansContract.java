package com.devs.kero.team7.learningrxjava.contract;

import com.devs.kero.team7.learningrxjava.base.BasePresenter;
import com.devs.kero.team7.learningrxjava.base.BaseView;

import java.util.List;

public interface CategoriesBooleansContract {
    interface View extends BaseView{
       void initViews(List<Boolean > booleans);

    }
    interface Presenter extends BasePresenter{
        void initviews(boolean isVibration);
        void handleClicks(boolean b , int whichItem);

    }
}
