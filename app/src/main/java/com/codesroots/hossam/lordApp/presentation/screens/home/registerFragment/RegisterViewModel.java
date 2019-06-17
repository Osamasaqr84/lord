package com.codesroots.hossam.lordApp.presentation.screens.home.registerFragment;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.v4.util.Consumer;

import com.codesroots.hossam.lordApp.dataLayer.repositories.RegisterRepository;
import com.codesroots.hossam.lordApp.entities.RegisterResponseModel;
import com.codesroots.hossam.lordApp.entities.User;


public class RegisterViewModel extends ViewModel {

    private RegisterRepository registerRepository;
    MutableLiveData<RegisterResponseModel> registerLiveData = new MutableLiveData<>();
    MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();
    MutableLiveData<Boolean> loading = new MutableLiveData<>();
    MutableLiveData<Integer> coderesponse = new MutableLiveData<>();

    public RegisterViewModel() {
    }

    public void saveUser (User user)
    {
        registerRepository.registerUser(user);

    }

    public RegisterViewModel(final RegisterRepository repository) {

        repository.setOnSuccess(new Consumer<RegisterResponseModel>() {
            @Override
            public void accept(RegisterResponseModel model) {
                registerLiveData.postValue(model);
                loading.postValue(false);
            }
        });

        repository.setOnError(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                errorLiveData.postValue(throwable);
                loading.postValue(false);
            }
        });

        repository.setcodeSuccess(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                coderesponse.postValue(integer);
            }
        });
        this.registerRepository = repository;
    }


}
