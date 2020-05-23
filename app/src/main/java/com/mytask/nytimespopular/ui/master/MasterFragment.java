package com.mytask.nytimespopular.ui.master;

import android.content.Context;

import com.mytask.nytimespopular.R;
import com.mytask.nytimespopular.ViewModelProviderFactory;
import com.mytask.nytimespopular.base.BaseFragment;
import com.mytask.nytimespopular.databinding.FragmentMasterBinding;
import com.mytask.nytimespopular.helpers.interfaces.ActivityResultCallBack;
import com.mytask.nytimespopular.repository.DataManager;

import static com.mytask.nytimespopular.BR.viewModel;

public class MasterFragment extends BaseFragment<FragmentMasterBinding,
        MasterFragmentViewModel> implements MasterFragmentActions {
    @Override
    public int getBindingVariable() {
        return viewModel;
    }

    @Override
    public boolean isNeedActivityResult() {
        return false;
    }

    @Override
    public ActivityResultCallBack activityResultCallBack() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_master;
    }

    @Override
    public MasterFragmentViewModel getViewModel() {
        return (MasterFragmentViewModel) new ViewModelProviderFactory(DataManager.getInstance(),
                getMyContext()).create(MasterFragmentViewModel.class, getViewDataBinding(), this);
    }

    @Override
    public Context getMyContext() {
        return getActivity();
    }

    @Override
    protected void setUp() {
        getViewModel().setUp();
    }
}
