package com.mytask.nytimespopular;


import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.mytask.nytimespopular.base.BaseActions;
import com.mytask.nytimespopular.repository.DataManager;

/**
 * @ViewDataBinding : Base class for generated data binding classes
 * @ViewModel: The purpose of the ViewModel is to acquire and keep the information that is necessary for an
 * Activity or a Fragment. The Activity or the Fragment should be able to observe changes in the
 * ViewModel. ViewModels usually expose this information via LiveData or Android Data
 * Binding. You can also use any observability construct from you favorite framework.
 * <p>
 * ViewModel's only responsibility is to manage the data for the UI. It <b>should never</b> access
 * your view hierarchy or hold a reference back to the Activity or the Fragment.
 * <p>
 * ViewModelProviderFactory - Simple factory, which calls empty constructor on the give class.
 */
public class ViewModelProviderFactory<V extends ViewDataBinding, N extends BaseActions>
        extends ViewModelProvider.NewInstanceFactory {

    private final DataManager dataManager;
    private final Context mContext;

    public ViewModelProviderFactory(DataManager dataManager, Context mContext) {
        this.dataManager = dataManager;
        this.mContext = mContext;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public <T extends ViewModel> T create(Class<T> modelClass, V viewDataBinding, N navigation) {
//        if (modelClass.isAssignableFrom(SplashScreenViewModel.class))
//            return (T) new SplashScreenViewModel(mContext, dataManager, viewDataBinding, navigation);
//        else if (modelClass.isAssignableFrom(LoginViewModel.class))
//            return (T) new LoginViewModel(mContext, dataManager, viewDataBinding, navigation);
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}