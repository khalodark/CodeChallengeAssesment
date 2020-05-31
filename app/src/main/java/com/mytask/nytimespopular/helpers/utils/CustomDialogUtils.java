package com.mytask.nytimespopular.helpers.utils;


import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Window;

import androidx.databinding.DataBindingUtil;

import com.mytask.nytimespopular.R;
import com.mytask.nytimespopular.databinding.DialogProgressBinding;

/**
 * Custom Dialog Utils class for showing Dialogs
 */
public class CustomDialogUtils extends Dialog {

    private CustomDialogUtils mProgressbar;

    private CustomDialogUtils(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        DialogProgressBinding dialogProgressBinding =
                DataBindingUtil.inflate(LayoutInflater.from(getContext()),
                        R.layout.dialog_progress, null, false);
        setContentView(dialogProgressBinding.getRoot());

        this.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialogProgressBinding.tvTitle.setText(context.getResources().getString(R.string.loading));
    }

    public CustomDialogUtils(Context context, Boolean withProgress, boolean isShowNow) {
        super(context);
        if (withProgress) {
            mProgressbar = new CustomDialogUtils(context);
            if (isShowNow)
                showProgress();
        }
    }

    public void showProgress() {
        if (mProgressbar != null && mProgressbar.isShowing()) {
            mProgressbar.cancel();
        }
        mProgressbar.setCancelable(false);
        mProgressbar.show();
    }

    public void hideProgress() {
        if (mProgressbar != null) {
            mProgressbar.dismiss();
        }
    }

}