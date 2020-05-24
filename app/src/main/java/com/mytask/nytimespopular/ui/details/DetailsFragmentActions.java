package com.mytask.nytimespopular.ui.details;

import com.mytask.nytimespopular.base.BaseActions;
import com.mytask.nytimespopular.model.ResultResponse;

/**
 * Additional Necessary actions defined here to implement
 */
public interface DetailsFragmentActions extends BaseActions {
    // necessary to pass intent bundle.
    ResultResponse getPassedResult();
}
