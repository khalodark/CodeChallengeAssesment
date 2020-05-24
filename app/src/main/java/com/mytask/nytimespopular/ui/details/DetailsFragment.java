package com.mytask.nytimespopular.ui.details;

import android.content.Context;

import com.mytask.nytimespopular.R;
import com.mytask.nytimespopular.ViewModelProviderFactory;
import com.mytask.nytimespopular.base.BaseFragment;
import com.mytask.nytimespopular.databinding.FragmentDetailsBinding;
import com.mytask.nytimespopular.helpers.interfaces.ActivityResultCallBack;
import com.mytask.nytimespopular.model.ResultResponse;
import com.mytask.nytimespopular.repository.DataManager;

import static com.mytask.nytimespopular.BR.viewModel;

public class DetailsFragment extends BaseFragment<FragmentDetailsBinding,
        DetailsFragmentViewModel> implements DetailsFragmentActions {
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
        return R.layout.fragment_details;
    }

    @Override
    public DetailsFragmentViewModel getViewModel() {
        return (DetailsFragmentViewModel) new ViewModelProviderFactory(DataManager.getInstance(),
                getMyContext()).create(DetailsFragmentViewModel.class, getViewDataBinding(), this);
    }

    @Override
    public Context getMyContext() {
        return getActivity();
    }

    @Override
    protected void setUp() {
        getViewModel().setUp();
    }

    @Override
    public ResultResponse getPassedResult() {
        return (ResultResponse) getArguments().getSerializable("Result");
    }
}
