package com.codesroots.hossam.lordApp.presentation.screens.home.rateFragment;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.v4.util.Consumer;

import com.codesroots.hossam.lordApp.dataLayer.repositories.RatingRepository;

public class RatingViewModel extends ViewModel {


    private RatingRepository ratingRepository;
    MutableLiveData<Boolean> addRatingResult = new MutableLiveData<>();
    MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();
    MutableLiveData<Boolean> loading = new MutableLiveData<>();



    public RatingViewModel() {
    }

    public RatingViewModel(final RatingRepository ratingRepository) {

        ratingRepository.setOnSuccess(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean result) {
                addRatingResult.postValue(result);
                loading.postValue(false);
            }
        });


        ratingRepository.setOnError(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                errorLiveData.postValue(throwable);
                loading.postValue(false);
            }
        });

        this.ratingRepository = ratingRepository;
    }



    public  void AddRatingToProduct (int user_id , int product_id, String comment, float rate )
    {
        ratingRepository.productRate(user_id,product_id,comment,rate);
    }

    public  void AddRatingToDelivery (int user_id , int delivery_id, String comment, float rate )
    {
        ratingRepository.productRate(user_id,delivery_id,comment,rate);
    }



}
