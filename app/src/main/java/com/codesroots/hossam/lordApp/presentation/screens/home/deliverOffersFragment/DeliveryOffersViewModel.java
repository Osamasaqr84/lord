package com.codesroots.hossam.lordApp.presentation.screens.home.deliverOffersFragment;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.codesroots.hossam.lordApp.entities.DeliveryOffers;
import com.codesroots.hossam.lordApp.dataLayer.repositories.DeliveryOffersRepository;

import java.util.List;

import okhttp3.RequestBody;

public class DeliveryOffersViewModel extends ViewModel {

    private DeliveryOffersRepository deliveryOffersRepository;
    MutableLiveData<List<DeliveryOffers.DataBean>> Deliveryoffers =new MutableLiveData<List<DeliveryOffers.DataBean>>();
    MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();
    MutableLiveData<Boolean> loading = new MutableLiveData<>();
    public MutableLiveData<Boolean> editOrderResponse = new MutableLiveData<>();
    MutableLiveData<Throwable> erroreditOrder = new MutableLiveData<>();
    MutableLiveData<Boolean> loadingeditOrder = new MutableLiveData<>();


    public DeliveryOffersViewModel() {

    }

    public DeliveryOffersViewModel(final DeliveryOffersRepository deliveryOffersRepository) {

        deliveryOffersRepository.setOnSuccess(deliveryOffers -> {
            Deliveryoffers.postValue(deliveryOffers.getData());
            loading.postValue(false);
        });

        deliveryOffersRepository.setOnError(throwable -> {
            errorLiveData.postValue(throwable);
            loading.postValue(false);
        });

        deliveryOffersRepository.setOnSuccessEdit(responseBody ->
                editOrderResponse.postValue(responseBody)
        );

        deliveryOffersRepository.sererroreditOrder(throwable ->
                erroreditOrder.postValue(throwable)
        );

        this.deliveryOffersRepository = deliveryOffersRepository;
    }

    public  void editOrder (int orderid , RequestBody delivry_id, RequestBody status , RequestBody price)
    {
        deliveryOffersRepository.editOrder(orderid,delivry_id,status,price);
    }



}
