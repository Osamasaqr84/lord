package com.codesroots.hossam.lordApp.presentation.screens.home.deliveryComments;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.v4.util.Consumer;

import com.codesroots.hossam.lordApp.dataLayer.repositories.DeliveryCommentsRepository;
import com.codesroots.hossam.lordApp.entities.DeliveryComments;

public class DeliveryCommentsViewModel extends ViewModel {


    private DeliveryCommentsRepository productRatesRepository;
    MutableLiveData<DeliveryComments> deliveryCommentsMutableLiveData = new MutableLiveData<>();
    MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();
    MutableLiveData<Boolean> loading = new MutableLiveData<>();


    public DeliveryCommentsViewModel() {
    }

    public DeliveryCommentsViewModel(final DeliveryCommentsRepository deliveryCommentsRepository) {

        deliveryCommentsRepository.setOnSuccess(new Consumer<DeliveryComments>() {
            @Override
            public void accept(DeliveryComments deliveryComments) {
                deliveryCommentsMutableLiveData.postValue(deliveryComments);
                loading.postValue(false);
            }
        });


        deliveryCommentsRepository.setOnError(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                errorLiveData.postValue(throwable);
                loading.postValue(false);
            }
        });

        this.productRatesRepository = deliveryCommentsRepository;
    }


}
