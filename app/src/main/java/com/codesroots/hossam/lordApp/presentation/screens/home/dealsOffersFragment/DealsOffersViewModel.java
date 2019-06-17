package com.codesroots.hossam.lordApp.presentation.screens.home.dealsOffersFragment;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.codesroots.hossam.lordApp.entities.DealsModel;
import com.codesroots.hossam.lordApp.entities.OffersModel;
import com.codesroots.hossam.lordApp.dataLayer.repositories.OffersRepository;

public class DealsOffersViewModel extends ViewModel {

    private OffersRepository offersRepository;
    MutableLiveData<OffersModel> offersLD = new MutableLiveData<OffersModel>();
    MutableLiveData<DealsModel> dealsLD = new MutableLiveData<DealsModel>();
    private MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();

    public MutableLiveData<Boolean> getLoading() {
        return loading;
    }

    private MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private MutableLiveData<Integer> lastchecked = new MutableLiveData<>();

    public MutableLiveData<Integer> getStore_id() {
        return store_id;
    }

    private MutableLiveData<Integer> store_id = new MutableLiveData<>();


    public MutableLiveData<Integer> getLastchecked() {
        return lastchecked;
    }


    public DealsOffersViewModel(final OffersRepository repository) {

        repository.setOnDealsSuccess(deals -> {
            dealsLD.postValue(deals);
            loading.postValue(false);
        });
        repository.setOnOffersSuccess(offers -> {
            offersLD.postValue(offers);
            loading.postValue(false);
        });

        repository.setOnError(throwable -> {
            errorLiveData.postValue(throwable);
            loading.postValue(false);
        });
        this.lastchecked.setValue(0);


        this.offersRepository = repository;
    }





}
