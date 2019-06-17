package com.codesroots.hossam.lordApp.presentation.screens.profileActivity;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.codesroots.hossam.lordApp.dataLayer.apiData.ApiClient;
import com.codesroots.hossam.lordApp.dataLayer.apiData.ApiInterface;
import com.codesroots.hossam.lordApp.dataLayer.repositories.CategoryRepository;
import com.codesroots.hossam.lordApp.dataLayer.repositories.ProfileRepository;

public class ProfileViewModelFactory implements ViewModelProvider.Factory {


    private Application application;
    public ProfileViewModelFactory(@NonNull Application application1) {
        application = application1;
    }

    @SuppressWarnings("SingleStatementInBlock")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == ProfileViewModel.class)
                 {
            return (T) new ProfileViewModel(getProfileRepository());
        }
        throw new IllegalArgumentException("Invalid view model class type");
    }

    @NonNull
    private ProfileRepository getProfileRepository() {
        return new ProfileRepository(getApiService());
    }

    private ApiInterface getApiService() {
        return ApiClient.getClient().create(ApiInterface.class);
    }

}
