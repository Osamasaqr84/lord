package com.codesroots.hossam.lordApp.presentation.screens.home.productsDetailsFragment;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.codesroots.hossam.lordApp.dataLayer.apiData.ApiClient;
import com.codesroots.hossam.lordApp.dataLayer.apiData.ApiInterface;
import com.codesroots.hossam.lordApp.dataLayer.localDatabase.LocalDatabase;
import com.codesroots.hossam.lordApp.dataLayer.localDatabase.userCart.deo.ProductDeo;
import com.codesroots.hossam.lordApp.dataLayer.repositories.ProductDetailsRepository;

public class ProductDetailsModelFactory implements ViewModelProvider.Factory {


    private Application application;
    int userID,productid,storid;//// for getProducts in that store

    public ProductDetailsModelFactory(Application application1, int productid1,int stor_id ,int user_id) {
        application = application1;
        userID =user_id;
        storid = stor_id;
        productid = productid1;
    }


    @SuppressWarnings("SingleStatementInBlock")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
       if (modelClass == ProductDetailsViewModel.class)
        {
            return (T) new ProductDetailsViewModel(getProductDetailsRepositry());
        }

        throw new IllegalArgumentException("Invalid view model class type");
    }



    @NonNull
    private ProductDetailsRepository getProductDetailsRepositry() {
        return new ProductDetailsRepository(getApiService(),getProductDeo(),productid,storid,userID);
    }

    private ApiInterface getApiService() {
        return ApiClient.getClient().create(ApiInterface.class);
    }



    private ProductDeo getProductDeo() {
        return LocalDatabase.getInstance(application).productDeo();
    }

}
