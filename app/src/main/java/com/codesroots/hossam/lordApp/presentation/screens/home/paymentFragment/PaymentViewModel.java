package com.codesroots.hossam.lordApp.presentation.screens.home.paymentFragment;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.v4.util.Consumer;

import com.codesroots.hossam.lordApp.presentation.screens.home.userCartFragment.helper.ProductModel;
import com.codesroots.hossam.lordApp.dataLayer.repositories.PaymentRepository;

import java.util.List;
import okhttp3.MultipartBody;


public class PaymentViewModel extends ViewModel {

    private PaymentRepository paymentRepository;
    MutableLiveData<Boolean> saveResultLiveData = new MutableLiveData<>();
    MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();
    MutableLiveData<Boolean> loading = new MutableLiveData<>();
   // List<ProductModel> ProductModels;
    public PaymentViewModel() {

    }

    public void saveData (List<ProductModel> ProductModels)
    {

        paymentRepository.addOrder(ProductModels);

    }

    public void saveDataFromGoogle (List<ProductModel> ProductModels,MultipartBody.Part file)
    {

        paymentRepository.addOrderFromGoogle(ProductModels,file);

    }

    public PaymentViewModel(final PaymentRepository paymentRepository) {

        paymentRepository.setOnSuccess(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) {
                saveResultLiveData.postValue(aBoolean);
                loading.postValue(false);
            }
        });

        paymentRepository.setOnError(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                errorLiveData.postValue(throwable);
                loading.postValue(false);
            }
        });
        this.paymentRepository = paymentRepository;
    }


}
