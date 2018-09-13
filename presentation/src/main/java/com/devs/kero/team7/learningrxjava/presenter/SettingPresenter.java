package com.devs.kero.team7.learningrxjava.presenter;

import com.devs.kero.team7.domain.StettingUseCases.GetDesignBoolean;
import com.devs.kero.team7.domain.StettingUseCases.UpdateDesignBoolean;
import com.devs.kero.team7.domain.UseCases.BaseUseCase;
import com.devs.kero.team7.learningrxjava.base.BasePresenter;
import com.devs.kero.team7.learningrxjava.base.BaseView;
import com.devs.kero.team7.learningrxjava.base.Presenter;
import com.devs.kero.team7.learningrxjava.ui.dialog.SettingDialog;

import java.util.List;

public abstract class SettingPresenter<I extends BaseUseCase, V extends BaseView> extends Presenter<I , V> implements SettingDialog.ClickListener {
    protected SettingDialog settingDialog ;
    protected GetDesignBoolean getDesignBoolean  ;
    protected UpdateDesignBoolean updateDesignBoolean ;
     protected List<Boolean> booleanes ;

    public SettingPresenter(I mMvpInteractor,  GetDesignBoolean getDesignBoolean, UpdateDesignBoolean updateDesignBoolean) {
        super(mMvpInteractor);
        this.getDesignBoolean = getDesignBoolean;
        this.updateDesignBoolean = updateDesignBoolean;
    }

}
