package com.mytask.nytimespopular.base;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

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


public abstract class BaseActivity<T extends ViewDataBinding, V extends BaseViewModel> extends AppCompatActivity
        implements BaseFragment.Callback {

    public boolean mTracking = false;
    ActivityResultCallBack activityResultCallBack;
    private T mViewDataBinding;
    private V mViewModel;
    private CustomDialogUtils progressDialog;

    public abstract int getBindingVariable();

    public abstract void setUpToolbar();

    public abstract @LayoutRes
    int getLayoutId();

    public abstract V getViewModel();

    public abstract Context getMyContext();

    public ActivityResultCallBack getActivityResultCallBack() {
        return activityResultCallBack;
    }

    @Override
    public void onFragmentAttachedNeedActivityResult(boolean hideToolbar, boolean hideBottom,
                                                     ActivityResultCallBack activityResultCallBack) {
        this.activityResultCallBack = activityResultCallBack;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        progressDialog = new CustomDialogUtils(this, true, false);
        super.onCreate(savedInstanceState);
        performDataBinding();
    }
//
//    public void setUpToolbar(Toolbar toolbar, String title, boolean withHome) {
//        setSupportActionBar(toolbar);
//        setTitle(title);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(withHome);
//    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public T getViewDataBinding() {
        return mViewDataBinding;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(getApplicationContext());
    }

    public void openActivityOnTokenExpire() {
//        startActivity(LoginActivity.newIntent(this));
//        finish();
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }


    public void showSnackBar(View view, int icon,
                             String title, String body,
                             String actionText, SnackViewBulider.SnackbarCallback snackbarCallback) {
        SnackViewBulider snackViewBulider = new SnackViewBulider(this, view, icon, title,
                body, actionText);
        snackViewBulider.showSnackbar(snackbarCallback);
    }


    public void showLoading(boolean isCancelable) {
        progressDialog.showProgress(isCancelable);
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

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    /**
     * Method of calling SnackBar View
     *
     * @param view       - the view that basics on
     * @param duration   - duration of shown
     * @param icon       - to show
     * @param title      - title
     * @param body       - body to show
     * @param actionText - text of Action
     */
    public void showSnackBar(View view, int duration,
                             int icon, String title, String body,
                             String actionText) {
        SnackViewBulider snackViewBulider = new SnackViewBulider(this, view, icon, title,
                body, actionText);
        snackViewBulider.setDuration(duration);
        snackViewBulider.showSnackbar(snackbar -> snackbar.dismiss());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}

