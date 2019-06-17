package com.codesroots.hossam.lordApp.presentation.screens.home.ratesOfProduct;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.v4.util.Consumer;

import com.codesroots.hossam.lordApp.dataLayer.repositories.ProductRatesRepository;
import com.codesroots.hossam.lordApp.entities.RatessOfProductModel;

public class ProductRatesViewModel extends ViewModel {


    private ProductRatesRepository productRatesRepository;
    MutableLiveData<RatessOfProductModel> productratesMutableLiveData = new MutableLiveData<>();
    MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();
    MutableLiveData<Boolean> loading = new MutableLiveData<>();



    public ProductRatesViewModel() {
    }

    public ProductRatesViewModel(final ProductRatesRepository productRatesRepository1) {

        productRatesRepository1.setOnSuccess(new Consumer<RatessOfProductModel>() {
            @Override
            public void accept(RatessOfProductModel ratessOfProductModel) {
                productratesMutableLiveData.postValue(ratessOfProductModel);
                loading.postValue(false);
            }
        });


        productRatesRepository1.setOnError(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                errorLiveData.postValue(throwable);
                loading.postValue(false);
            }
        });

        this.productRatesRepository = productRatesRepository1;
    }


}
