package com.codesroots.hossam.lordApp.presentation.screens.splash;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.v4.util.Consumer;

import com.codesroots.hossam.lordApp.dataLayer.repositories.SplashRepository;
import com.codesroots.hossam.lordApp.entities.StoreSettingEntity;

import java.util.List;

public class SplashViewModel extends ViewModel {


    private SplashRepository splashRepository;
    MutableLiveData<StoreSettingEntity> storeSettingLiveData = new MutableLiveData<>();
    MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();
    MutableLiveData<Boolean> loading = new MutableLiveData<>();



    public SplashViewModel(final SplashRepository splashRepository) {

        splashRepository.setOnSuccess(new Consumer<StoreSettingEntity>() {
            @Override
            public void accept(StoreSettingEntity storeSettingEntity) {
                storeSettingLiveData.postValue(storeSettingEntity);
                storeData(storeSettingEntity.getData());
                loading.postValue(false);
            }
        });

        splashRepository.setOnError(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) {

                errorLiveData.postValue(throwable);
                loading.postValue(false);
            }
        });
        this.splashRepository = splashRepository;
    }



    private void storeData(List<StoreSettingEntity.DataBean> dataBeans) {
        splashRepository.saveDataInDB(dataBeans);
    }


}
