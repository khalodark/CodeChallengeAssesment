package com.mytask.nytimespopular.helpers.utils;

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
     * is Loading - for loading parameter
     */
    private boolean isLoading = false;
    private SwipeRefreshLayout swipeRefreshLayout;
    private CustomDialogUtils progressBar;

    public SupportRefreshingAndRetrying() {
        // empty constructor
    }

    public SupportRefreshingAndRetrying(CustomDialogUtils progressBar,
                                        SwipeRefreshLayout swipeRefreshLayout) {
        this.progressBar = progressBar;
        this.swipeRefreshLayout = swipeRefreshLayout;
    }

    public boolean getisRefreshing() {
        return isRefreshing;
    }

    public void setRefreshing(boolean refreshing) {
        isRefreshing = refreshing;
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


    public void finishRefreshing(boolean isSuccess) {
        this.swipeRefreshLayout.setRefreshing(false);
        setIsRefreshing(false);
    }

    public void checkIsLoadMoreAndRefreshing(boolean isSuccess) {
        if (getisRefreshing()) {
            finishRefreshing(isSuccess);
        }
        setIsLoading(false);
        progressBar.hideProgress();
    }

}
