package com.mytask.nytimespopular.ui.main;


import android.content.Context;
import android.os.Bundle;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.mytask.nytimespopular.R;
import com.mytask.nytimespopular.ViewModelProviderFactory;
import com.mytask.nytimespopular.base.BaseActivity;
import com.mytask.nytimespopular.databinding.ActivityMainBinding;
import com.mytask.nytimespopular.repository.DataManager;

import java.util.Objects;

import static com.mytask.nytimespopular.BR.viewModel;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainActivityViewModel>
        implements MainActivityActions {

    private NavController navController;

    @Override
    public int getBindingVariable() {
        return viewModel;
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
        return MainActivity.this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        navController.setGraph(R.navigation.nav_graph);

        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(navController.getGraph())
                        .build();

        NavigationUI.setupWithNavController(getViewDataBinding().toolbar, navController, appBarConfiguration);

        setSupportActionBar(getViewDataBinding().toolbar);
        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getViewDataBinding().toolbar.setTitle(getString(R.string.newyork_times_popular));
    }

    @Override
    public void onFragmentAttached(boolean hideToolbar, boolean hideBottomSheet) {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }
}
