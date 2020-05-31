package com.mytask.nytimespopular.base;


import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.mytask.nytimespopular.helpers.interfaces.ActivityResultCallBack;
import com.mytask.nytimespopular.helpers.utils.CustomDialogUtils;
import com.mytask.nytimespopular.helpers.utils.NetworkUtils;
import com.mytask.nytimespopular.helpers.utils.SnackViewBulider;


public abstract class BaseActivity<T extends ViewDataBinding, V extends BaseViewModel> extends AppCompatActivity {

    ActivityResultCallBack activityResultCallBack;
    private T mViewDataBinding;
    private V mViewModel;
    private CustomDialogUtils progressDialog;

    public abstract int getBindingVariable();

    public abstract @LayoutRes
    int getLayoutId();

    public abstract V getViewModel();

    public abstract Context getMyContext();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        progressDialog = new CustomDialogUtils(this, true, false);
        super.onCreate(savedInstanceState);
        performDataBinding();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public T getViewDataBinding() {
        return mViewDataBinding;
    }

    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(getApplicationContext());
    }

    public void showSnackBar(View view, int icon,
                             String title, String body,
                             String actionText, SnackViewBulider.SnackbarCallback snackbarCallback) {
        SnackViewBulider snackViewBulider = new SnackViewBulider(this, view, icon, title,
                body, actionText);
        snackViewBulider.showSnackbar(snackbarCallback);
    }


    public void showLoading() {
        progressDialog.showProgress();
    }

    public void hideLoading() {
        progressDialog.hideProgress();
    }

    private void performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        this.mViewModel = mViewModel == null ? getViewModel() : mViewModel;
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
        mViewDataBinding.executePendingBindings();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}

