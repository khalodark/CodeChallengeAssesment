package com.mytask.nytimespopular.helpers.utils;

import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/**
 * General Class to manage the operation of retrying and
 * refreshing the viewData
 */
public class SupportRefreshingAndRetrying {

    /**
     * Swipe Refresh Layout
     */
    private boolean isRefreshing = false;
    /**
     * Load More Param
     */
    private boolean isLoadMore = false;
    /**
     * is Retry Param
     */
    private boolean isRetry = false;
    /**
     * is Loading - for loading parameter
     */
    private boolean isLoading = false;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ProgressBar progressBar;
    private Button btn;

    public SupportRefreshingAndRetrying() {

    }

    public SupportRefreshingAndRetrying(Button btnRetry, ProgressBar progressBar,
                                        SwipeRefreshLayout swipeRefreshLayout) {
        this.btn = btnRetry;
        this.progressBar = progressBar;
        this.swipeRefreshLayout = swipeRefreshLayout;
    }

    public boolean getisRefreshing() {
        return isRefreshing;
    }

    public void setRefreshing(boolean refreshing) {
        isRefreshing = refreshing;
    }

    public boolean getIsLoadMore() {
        return isLoadMore;
    }

    public boolean getIsRetry() {
        return isRetry;
    }

    public boolean getIsLoading() {
        return isLoading;
    }

    public void setIsLoading(boolean loading) {
        this.isLoading = loading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    public void setIsRefreshing(boolean refreshing) {
        this.isRefreshing = refreshing;
    }

    public void setLoadMore(boolean loadMore) {
        this.isLoadMore = loadMore;
    }

    public void setRetry(boolean retry) {
        this.isRetry = retry;
    }

    public void setRetring() {
        if (btn != null)
            setVisibilityOfBtnRetry(View.GONE, btn);
        if (progressBar != null)
            setVisibilityOfBtnRetry(View.GONE, progressBar);
        setRetry(true);
    }

    /**
     * Setting visibility of any btn
     *
     * @param visibilityOfBtnRetry -
     * @param view                 -
     */
    public void setVisibilityOfBtnRetry(int visibilityOfBtnRetry, View view) {
        view.setVisibility(visibilityOfBtnRetry);
    }

    public void finishRetry(boolean isSuccess, OnFinishRetrying onFinishRetrying) {
        if (btn != null)
            setVisibilityOfBtnRetry(View.GONE, btn);
        if (progressBar != null)
            setVisibilityOfBtnRetry(View.GONE, progressBar);
        setRetry(false);
        if (isSuccess) {
//            homeAdapter.clearItems();
//            getViewBinding().relativeNoData.setVisibility(View.GONE);
            onFinishRetrying.onFinishing();
        }
    }

    public void finishLoadMore(OnFinishLoadMore onFinishLoadMore) {
        onFinishLoadMore.onLoadMoreFinish();
//        homeAdapter.remove(homeAdapter.getItemCount() - 1);
//        notifiAdapter();
//        homeAdapter.setLoaded();
        setLoadMore(false);
    }

    public void finishRefreshing(boolean isSuccess) {
        if (isSuccess) {
//            homeAdapter.clearItems();
        }
        this.swipeRefreshLayout.setRefreshing(false);
        setIsRefreshing(false);
    }

    public void checkIsLoadMoreAndRefreshing(boolean isSuccess, OnFinishLoadMore onFinishLoadMore
            , OnFinishRetrying onFinishRetrying) {
        if (getisRefreshing()) {
            finishRefreshing(isSuccess);
        } else if (getIsRetry()) {
            finishRetry(isSuccess, onFinishRetrying);
        } else if (getIsLoadMore()) {
            finishLoadMore(onFinishLoadMore);
        } else {
            setIsLoading(false);
        }
    }

    public interface OnFinishRetrying {
        void onFinishing();
    }


    public interface OnFinishLoadMore {
        void onLoadMoreFinish();
    }
}
