package com.mytask.nytimespopular.ui;


import android.content.Context;
import android.os.Bundle;

import com.mytask.nytimespopular.BR;
import com.mytask.nytimespopular.R;
import com.mytask.nytimespopular.ViewModelProviderFactory;
import com.mytask.nytimespopular.base.BaseActivity;
import com.mytask.nytimespopular.databinding.ActivityMainBinding;
import com.mytask.nytimespopular.repository.DataManager;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainActivityViewModel>
        implements MainActivityActions {

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public void setUpToolbar() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public MainActivityViewModel getViewModel() {
        return (MainActivityViewModel) new ViewModelProviderFactory(DataManager.getInstance(), getMyContext())
                .create(MainActivityViewModel.class, getViewDataBinding(), this);
    }

    @Override
    public Context getMyContext() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onFragmentAttached(boolean hideToolbar, boolean hideBottomSheet) {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }
}
