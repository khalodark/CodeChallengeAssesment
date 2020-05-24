package com.mytask.nytimespopular.ui.master;

import android.content.Context;
import android.os.Bundle;

import androidx.databinding.ViewDataBinding;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;

import com.mytask.nytimespopular.R;
import com.mytask.nytimespopular.base.BaseActions;
import com.mytask.nytimespopular.base.BaseViewModel;
import com.mytask.nytimespopular.databinding.FragmentMasterBinding;
import com.mytask.nytimespopular.helpers.interfaces.RecyclerClick;
import com.mytask.nytimespopular.helpers.utils.CustomDialogUtils;
import com.mytask.nytimespopular.helpers.utils.SupportRefreshingAndRetrying;
import com.mytask.nytimespopular.model.ResultResponse;
import com.mytask.nytimespopular.repository.DataManager;
import com.mytask.nytimespopular.repository.api.APICallBack;
import com.mytask.nytimespopular.repository.api.CustomObserverResponse;
import com.mytask.nytimespopular.ui.adapter.NewsAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MasterFragmentViewModel extends BaseViewModel<MasterFragmentActions,
        FragmentMasterBinding> implements RecyclerClick<ResultResponse> {

    private SupportRefreshingAndRetrying supportRefreshingAndRetrying;
    private NewsAdapter homeAdapter;
    private CustomDialogUtils customDialogUtils;

    public <V extends ViewDataBinding, N extends BaseActions>
    MasterFragmentViewModel(Context mContext, DataManager dataManager, V viewDataBinding, N navigation) {
        super(mContext, dataManager, (MasterFragmentActions) navigation,
                (FragmentMasterBinding) viewDataBinding);
        setUp();
    }

    public void getData() {
        if (!supportRefreshingAndRetrying.getisRefreshing()) {
            supportRefreshingAndRetrying.setIsLoading(true);
        }
        getDataManager().getServices().getDataApi().getArticles(getMyContext().getString(R.string.ny_key))
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CustomObserverResponse<>(new APICallBack<List<ResultResponse>>() {
                    @Override
                    public void onSuccess(List<ResultResponse> response) {
                        supportRefreshingAndRetrying.checkIsLoadMoreAndRefreshing(
                                true);
                        homeAdapter.addItems(response);
                        homeAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(String error, int errorCode) {
                        showSnackBar(getMyContext().getString(R.string.error),
                                error, getMyContext().getResources().getString(R.string.OK),
                                snackbar -> snackbar.dismiss());
                        supportRefreshingAndRetrying.checkIsLoadMoreAndRefreshing(
                                false);
                    }
                }));
    }


    private void setUpRecycler() {
        getViewBinding().swipeRefreshLayout.setOnRefreshListener(() -> {
            supportRefreshingAndRetrying.setIsRefreshing(true);
            getData();
        });
        homeAdapter = new NewsAdapter(getMyContext(), new ArrayList<>(), this);
        getViewBinding().recyclerView.setItemAnimator(new DefaultItemAnimator());
        getViewBinding().recyclerView.setAdapter(homeAdapter);
    }

    @Override
    protected void setUp() {
        customDialogUtils = new CustomDialogUtils(getMyContext(), true, true);
        this.supportRefreshingAndRetrying = new SupportRefreshingAndRetrying(
                customDialogUtils, getViewBinding().swipeRefreshLayout);
        setUpRecycler();
        getData();
    }

    @Override
    public void onClick(ResultResponse resultResponse, int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("Result", resultResponse);
        Navigation.findNavController(getBaseActivity(), R.id.nav_host_fragment).
                navigate(R.id.action_masterFragment_to_detailsFragment, bundle);
    }

}
