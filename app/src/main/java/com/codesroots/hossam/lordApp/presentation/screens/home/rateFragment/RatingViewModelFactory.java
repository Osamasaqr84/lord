package com.codesroots.hossam.lordApp.presentation.screens.home.rateFragment;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.codesroots.hossam.lordApp.dataLayer.apiData.ApiClient;
import com.codesroots.hossam.lordApp.dataLayer.apiData.ApiInterface;
import com.codesroots.hossam.lordApp.dataLayer.repositories.RatingRepository;

public class RatingViewModelFactory implements ViewModelProvider.Factory {


    private Application application;
    public RatingViewModelFactory(@NonNull Application application1) {
        application = application1;
    }



    @SuppressWarnings("SingleStatementInBlock")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == RatingViewModel.class)
                 {
            return (T) new RatingViewModel(getRatesRepositry());
        }

        throw new IllegalArgumentException("Invalid view model class type");
    }


    @NonNull
    private RatingRepository getRatesRepositry() {
        return new RatingRepository(getApiService());
    }


    private ApiInterface getApiService() {
        return ApiClient.getClient().create(ApiInterface.class);
    }

}
