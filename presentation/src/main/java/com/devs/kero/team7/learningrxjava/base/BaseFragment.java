package com.devs.kero.team7.learningrxjava.base;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.devs.kero.team7.learningrxjava.di.HasComponent;
import com.devs.kero.team7.learningrxjava.di.component.UserComponent;

public class BaseFragment extends Fragment  {
    protected BaseActivity mActivity;
    public void hideKeyboeard(){
        if(mActivity !=null){
            mActivity.hideKeyboard();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context  instanceof  BaseActivity){
            mActivity= (BaseActivity) context ;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mActivity = null ;
    }


    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }
}
