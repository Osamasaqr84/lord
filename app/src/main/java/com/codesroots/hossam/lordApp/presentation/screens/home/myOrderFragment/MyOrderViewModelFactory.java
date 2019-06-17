package com.codesroots.hossam.lordApp.presentation.screens.home.myOrderFragment;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.codesroots.hossam.lordApp.dataLayer.apiData.ApiClient;
import com.codesroots.hossam.lordApp.dataLayer.apiData.ApiInterface;
import com.codesroots.hossam.lordApp.dataLayer.repositories.MyOrderRepository;

public class MyOrderViewModelFactory implements ViewModelProvider.Factory {


    private Application application;
    int userid,user_group_id;//// for getorder to this user



    public MyOrderViewModelFactory(Application application1, int user_id, int user_group_id1) {
        application = application1;
        userid =user_id;
        user_group_id =user_group_id1;
    }


    @SuppressWarnings("SingleStatementInBlock")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {


         if (modelClass == MyOrderViewModel.class)
        {
            return (T) new MyOrderViewModel(getMyOrderRepositry(userid,user_group_id));
        }

        throw new IllegalArgumentException("Invalid view model class type");
    }




    @NonNull
    private MyOrderRepository getMyOrderRepositry(int userid , int user_group_id) {
        return new MyOrderRepository(getApiService(),userid,user_group_id);
    }


    private ApiInterface getApiService() {
        return ApiClient.getClient().create(ApiInterface.class);
    }


}
