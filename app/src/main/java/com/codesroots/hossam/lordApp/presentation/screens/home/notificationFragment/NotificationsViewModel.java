package com.codesroots.hossam.lordApp.presentation.screens.home.notificationFragment;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.v4.util.Consumer;

import com.codesroots.hossam.lordApp.entities.Notifications;
import com.codesroots.hossam.lordApp.dataLayer.repositories.NotificationRepository;

public class NotificationsViewModel extends ViewModel {


    private NotificationRepository productRatesRepository;
    MutableLiveData<Notifications> notificationsMutableLiveData = new MutableLiveData<>();
    MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();
    MutableLiveData<Boolean> loading = new MutableLiveData<>();


    public NotificationsViewModel() {
    }

    public NotificationsViewModel(final NotificationRepository notificationRepository) {

        notificationRepository.setOnSuccess(new Consumer<Notifications>() {
            @Override
            public void accept(Notifications  notifications) {
                notificationsMutableLiveData.postValue(notifications);
                loading.postValue(false);
            }
        });


        notificationRepository.setOnError(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                errorLiveData.postValue(throwable);
                loading.postValue(false);
            }
        });

        this.productRatesRepository = notificationRepository;
    }


}
