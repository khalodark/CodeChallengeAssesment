package com.mytask.nytimespopular.ui.details;

import android.content.Context;

import androidx.databinding.ViewDataBinding;

import com.mytask.nytimespopular.base.BaseActions;
import com.mytask.nytimespopular.base.BaseViewModel;
import com.mytask.nytimespopular.databinding.FragmentDetailsBinding;
import com.mytask.nytimespopular.helpers.utils.GeneralFunction;
import com.mytask.nytimespopular.model.ResultResponse;
import com.mytask.nytimespopular.repository.DataManager;

public class DetailsFragmentViewModel extends BaseViewModel<DetailsFragmentActions,
        FragmentDetailsBinding> {


    public <V extends ViewDataBinding, N extends BaseActions>
    DetailsFragmentViewModel(Context mContext, DataManager dataManager,
                             V viewDataBinding, N navigation) {
        super(mContext, dataManager, (DetailsFragmentActions) navigation,
                (FragmentDetailsBinding) viewDataBinding);
    }

    @Override
    protected void setUp() {
        setDataOnScreen(getNavigator().getPassedResult());
        getViewBinding().setLifecycleOwner(getBaseActivity());
    }

    private void setDataOnScreen(ResultResponse resultResponse) {
        getViewBinding().actvAuthorText.setText(resultResponse.getByline());
        getViewBinding().actvTitleText.setText(resultResponse.getTitle());
        getViewBinding().actvPublishedDateText.setText(resultResponse.getPublishedDate());
        getViewBinding().actvAbstractText.setText(resultResponse.getAbstract());
        if (resultResponse.getMedia().get(0) != null)
            if (resultResponse.getMedia().get(0).getMediaMetadata() != null)
                if (resultResponse.getMedia().get(0).getMediaMetadata().size() > 0)
                    // for testing only - this will bring the biggest size picture.
                    GeneralFunction.loadImage(getMyContext(),
                            resultResponse.getMedia().get(0).getMediaMetadata().
                                    get(resultResponse.getMedia().get(0).getMediaMetadata().size() - 1).getUrl(), getViewBinding().image);
    }

}
