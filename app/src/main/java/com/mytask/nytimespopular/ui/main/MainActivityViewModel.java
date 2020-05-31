package com.mytask.nytimespopular.ui.main;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.databinding.ViewDataBinding;

import com.mytask.nytimespopular.base.BaseActions;
import com.mytask.nytimespopular.base.BaseViewModel;
import com.mytask.nytimespopular.databinding.ActivityMainBinding;
import com.mytask.nytimespopular.repository.DataManager;

public class MainActivityViewModel extends BaseViewModel<MainActivityActions, ActivityMainBinding> {


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public <V extends ViewDataBinding, N extends BaseActions>
    MainActivityViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (MainActivityActions) navigation, (ActivityMainBinding) viewDataBinding);
        setUp();
    }

    @Override
    protected void setUp() {
    }
}
