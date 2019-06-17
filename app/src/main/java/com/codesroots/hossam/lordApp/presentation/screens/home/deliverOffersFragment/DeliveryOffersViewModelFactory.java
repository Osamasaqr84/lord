package com.codesroots.hossam.lordApp.presentation.screens.home.deliverOffersFragment;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import com.codesroots.hossam.lordApp.dataLayer.apiData.ApiClient;
import com.codesroots.hossam.lordApp.dataLayer.apiData.ApiInterface;
import com.codesroots.hossam.lordApp.dataLayer.repositories.DeliveryOffersRepository;


public class DeliveryOffersViewModelFactory implements ViewModelProvider.Factory {


    private Application application;
    int orderid;//// for offers in that order


    public DeliveryOffersViewModelFactory(Application application1, int orderid1) {
        application=application1;
        orderid =orderid1;
    }


    @SuppressWarnings("SingleStatementInBlock")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == DeliveryOffersViewModel.class)
                 {
            return (T) new DeliveryOffersViewModel(getdeliveryoffersRepositry());
        }

        throw new IllegalArgumentException("Invalid view model class type");
    }

    @NonNull
    private DeliveryOffersRepository getdeliveryoffersRepositry() {
        return new DeliveryOffersRepository(getApiService(),orderid);
    }

    private ApiInterface getApiService() {
        return ApiClient.getClient().create(ApiInterface.class);
    }

}
