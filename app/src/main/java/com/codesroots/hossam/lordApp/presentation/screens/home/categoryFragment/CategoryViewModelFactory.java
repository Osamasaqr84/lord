package com.codesroots.hossam.lordApp.presentation.screens.home.categoryFragment;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.codesroots.hossam.lordApp.dataLayer.apiData.ApiClient;
import com.codesroots.hossam.lordApp.dataLayer.apiData.ApiInterface;
import com.codesroots.hossam.lordApp.dataLayer.repositories.CategoryRepository;

public class CategoryViewModelFactory implements ViewModelProvider.Factory {


    private Application application;
    public CategoryViewModelFactory(@NonNull Application application1) {
        application = application1;
    }

    @SuppressWarnings("SingleStatementInBlock")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == CategoryViewModel.class)
                 {
            return (T) new CategoryViewModel(getSplashRepository());
        }
        throw new IllegalArgumentException("Invalid view model class type");
    }

    @NonNull
    private CategoryRepository getSplashRepository() {
        return new CategoryRepository(getApiService());
    }

    private ApiInterface getApiService() {
        return ApiClient.getClient().create(ApiInterface.class);
    }


}
