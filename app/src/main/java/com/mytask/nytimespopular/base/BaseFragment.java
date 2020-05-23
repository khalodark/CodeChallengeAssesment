package com.mytask.nytimespopular.base;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.mytask.nytimespopular.helpers.interfaces.ActivityResultCallBack;

public abstract class BaseFragment<T extends ViewDataBinding,
        V extends BaseViewModel> extends Fragment {

    private BaseActivity mActivity;
    private View mRootView;
    private T mViewDataBinding;
    private V mViewModel;

    public abstract int getBindingVariable();

    public abstract boolean isNeedActivityResult();

    public abstract ActivityResultCallBack activityResultCallBack();

    public abstract
    @LayoutRes
    int getLayoutId();

    public abstract V getViewModel();

    public abstract Context getMyContext();

    protected abstract void setUp();

    @Override
    public void onDetach() {
        mActivity = null;
        super.onDetach();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView != null) {
            return mRootView;
        } else {
            setHasOptionsMenu(false);
            mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
            mViewDataBinding.setLifecycleOwner(getViewLifecycleOwner());
            mRootView = mViewDataBinding.getRoot();
            this.mViewModel = mViewModel == null ? getViewModel() : mViewModel;
            mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
            mViewDataBinding.executePendingBindings();
            setUp();
            return mRootView;
        }
    }


    public BaseActivity getBaseActivity() {
        return mActivity;
    }

    public T getViewDataBinding() {
        return mViewDataBinding;
    }

    public void hideKeyboard() {
        if (mActivity != null) {
            mActivity.hideKeyboard();
        }
    }

    public boolean isNetworkConnected() {
        return mActivity != null && mActivity.isNetworkConnected();
    }

    public void openActivityOnTokenExpire() {
        if (mActivity != null) {
            mActivity.openActivityOnTokenExpire();
        }
    }


//    protected void setUpToolbar(ToolbarBinding toolbar, String TAG, String title) {
//        if (!title.isEmpty()) {
//            toolbar.toolbarTitle.setText(title);
//            toolbar.toolbarTitle.setVisibility(View.VISIBLE);
//        }
//        setUpToolbar(toolbar, TAG);
//    }
//
//    protected void setUpToolbar(ToolbarBinding toolbar, String TAG) {
//        toolbar.toolbar.setNavigationIcon(getMyContext().getResources().getDrawable(R.drawable.ic_arrow_back));
//        toolbar.toolbar.setNavigationOnClickListener(v -> {
//            getBaseActivity().onFragmentDetached(TAG);
//            getViewModel().popUp();
//        });
//    }


    public interface Callback {

        void onFragmentAttached(boolean hideToolbar, boolean hideBottomSheet);

        void onFragmentDetached(String tag);

        void onFragmentAttachedNeedActivityResult(boolean hideToolbar, boolean hideBottomSheet,
                                                  ActivityResultCallBack activityResultCallBack);
    }

}

