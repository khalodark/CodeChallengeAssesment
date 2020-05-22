/*
 *  Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://mindorks.com/license/apache-v2
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */

package com.mytask.nytimespopular.base;


import android.content.Context;
import android.widget.Toast;

import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;

import com.mytask.nytimespopular.repository.DataManager;

import java.lang.ref.WeakReference;

public abstract class BaseViewModel<N, T extends ViewDataBinding> extends ViewModel {


    private WeakReference<N> mNavigator;
    private T ViewBinding;
    private Context mContext;
    private DataManager dataManager;

    public BaseViewModel(Context mContext, DataManager dataManager, N navigator, T viewBinding) {
        this.mContext = mContext;
        this.dataManager = dataManager;
        this.ViewBinding = viewBinding;
        this.mNavigator = new WeakReference<>(navigator);
    }


    public BaseViewModel() {
    }

    protected abstract void setUp();

    protected T getViewBinding() {
        return ViewBinding;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    protected N getNavigator() {
        return mNavigator.get();
    }

    protected Context getMyContext() {
        return mContext;
    }

    protected DataManager getDataManager() {
        return dataManager;
    }

    public void showLoading() {
        ((BaseActivity<ViewDataBinding, BaseViewModel>) getMyContext()).showLoading();
    }

    public void hideLoading() {
        ((BaseActivity<ViewDataBinding, BaseViewModel>) getMyContext()).hideLoading();
    }

    protected BaseActivity getBaseActivity() {
        return ((BaseActivity) getMyContext());
    }


//    public void showSnackBar(String title, String message, String actionText, SnackViewBulider.SnackbarCallback snackbarCallback) {
//        getBaseActivity().showSnackBar(getViewBinding().getRoot(), R.drawable.ic_warning,
//                title, message, actionText, snackbarCallback);
//    }

    public void showToast(String message) {
        Toast.makeText(getMyContext(), message, Toast.LENGTH_SHORT).show();
    }
}