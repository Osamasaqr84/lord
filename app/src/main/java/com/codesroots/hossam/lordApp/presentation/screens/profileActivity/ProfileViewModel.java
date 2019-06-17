package com.codesroots.hossam.lordApp.presentation.screens.profileActivity;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.v4.util.Consumer;

import com.codesroots.hossam.lordApp.dataLayer.repositories.LoginRepository;
import com.codesroots.hossam.lordApp.dataLayer.repositories.ProfileRepository;
import com.codesroots.hossam.lordApp.entities.EditProfile;
import com.codesroots.hossam.lordApp.entities.LoginResponseModel;
import com.codesroots.hossam.lordApp.entities.Profile;
import com.codesroots.hossam.lordApp.entities.User;

import okhttp3.MultipartBody;
import retrofit2.http.Multipart;


public class ProfileViewModel extends ViewModel {

    MutableLiveData<Profile> profileLiveData = new MutableLiveData<>();
    MutableLiveData<EditProfile> profileEditLiveData = new MutableLiveData<>();
    MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();
    MutableLiveData<Throwable> errorEditLiveData = new MutableLiveData<>();
    MutableLiveData<Boolean> loading = new MutableLiveData<>();
    ProfileRepository profileRepository;

    public ProfileViewModel() {
    }

    public ProfileViewModel(final ProfileRepository repository) {

        repository.setOnSuccess(model -> {
            profileLiveData.postValue(model);
            loading.postValue(false);
        });


        repository.setOnError(throwable -> {
            errorLiveData.postValue(throwable);
            loading.postValue(false);
        });



        profileRepository =  repository;
        profileRepository.setOnSuccessEdit(editProfile ->
                profileEditLiveData.postValue(editProfile)
        );

        profileRepository.setOnErrorEdit(throwable -> {
            errorEditLiveData.postValue(throwable);
        });


    }

    public  void editProfile (String name, String phone , MultipartBody.Part part)
    {
        profileRepository.edit(name,phone,part);
    }


}
