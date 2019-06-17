package com.codesroots.hossam.lordApp.presentation.screens.home.ratesOfProduct;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.codesroots.hossam.lordApp.presentation.screens.home.userCartFragment.helper.ProductModel;
import com.codesroots.hossam.lordApp.dataLayer.apiData.ApiClient;
import com.codesroots.hossam.lordApp.dataLayer.apiData.ApiInterface;
import com.codesroots.hossam.lordApp.dataLayer.repositories.ProductRatesRepository;

import java.util.List;

public class ProductRatesViewModelFactory implements ViewModelProvider.Factory {


    private Application application;
    int typeid, productid;
    int storeid;//// for getProducts in that store

    List<ProductModel> productList; ///// alldata of product send to serever when user parches
    public ProductRatesViewModelFactory(@NonNull Application application1) {
        application = application1;
    }

    public ProductRatesViewModelFactory(Application application1, int type, int productid1) {
        application=application1;
        typeid =type;
        productid = productid1;

    }

    @SuppressWarnings("SingleStatementInBlock")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == ProductRatesViewModel.class)
                 {
            return (T) new ProductRatesViewModel(getRatesRepositry());
        }

        throw new IllegalArgumentException("Invalid view model class type");
    }


    @NonNull
    private ProductRatesRepository getRatesRepositry() {
        return new ProductRatesRepository(getApiService(),typeid, productid);
    }


    private ApiInterface getApiService() {
        return ApiClient.getClient().create(ApiInterface.class);
    }

}
