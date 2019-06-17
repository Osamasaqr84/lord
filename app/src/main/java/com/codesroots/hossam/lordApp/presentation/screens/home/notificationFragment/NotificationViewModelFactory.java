package com.codesroots.hossam.lordApp.presentation.screens.home.notificationFragment;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.codesroots.hossam.lordApp.dataLayer.apiData.ApiClient;
import com.codesroots.hossam.lordApp.dataLayer.apiData.ApiInterface;
import com.codesroots.hossam.lordApp.dataLayer.repositories.NotificationRepository;

public class NotificationViewModelFactory implements ViewModelProvider.Factory {


    private Application application;
    int userid;


    public NotificationViewModelFactory(@NonNull Application application1) {
        application = application1;
    }

    public NotificationViewModelFactory(Application application1, int userid1) {
        application=application1;
        userid = userid1;
    }

    @SuppressWarnings("SingleStatementInBlock")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == NotificationsViewModel.class)
                 {
            return (T) new NotificationsViewModel(getNotification());
        }

        throw new IllegalArgumentException("Invalid view model class type");
    }


    @NonNull
    private NotificationRepository getNotification() {
        return new NotificationRepository(getApiService(), userid);
    }


    private ApiInterface getApiService() {
        return ApiClient.getClient().create(ApiInterface.class);
    }

}
