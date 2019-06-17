package com.codesroots.hossam.lordApp.dataLayer.repositories;

import android.support.annotation.NonNull;
import android.support.v4.util.Consumer;

import com.codesroots.hossam.lordApp.dataLayer.apiData.ApiInterface;
import com.codesroots.hossam.lordApp.entities.EditProfile;
import com.codesroots.hossam.lordApp.entities.LoginResponseModel;
import com.codesroots.hossam.lordApp.entities.Profile;
import com.codesroots.hossam.lordApp.entities.User;
import com.codesroots.hossam.lordApp.helper.PreferenceHelper;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileRepository {


    private ApiInterface apiService;
    private Consumer<Profile> onSuccess;
    private Consumer<EditProfile> onSuccessEdit;
    private Consumer<Throwable> onError;
    private Consumer<Throwable> onErrorEdit;


    public ProfileRepository(ApiInterface apiService) {
        this.apiService = apiService;
        getProfile();
    }

    public void getProfile() {
         apiService.getProfileData(PreferenceHelper.getUserId()).
                 enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, final Response<Profile> response) {
                if (response.body() != null) {
                        if (onSuccess != null) {
                            onSuccess.accept(response.body());
                        }
                }

                else {  // TODO: return error
                    if (onError != null) {
                        onError.accept(new Exception());
                    }
                }
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                if (onError != null) {
                    onError.accept(t);
                }
            }
        });
    }


    public void edit(String name, String phone , MultipartBody.Part part)
    {
        apiService.editProfile(PreferenceHelper.getUserId(),createPartFromString(name),createPartFromString(phone),part).
                enqueue(new Callback<EditProfile>() {
                    @Override
                    public void onResponse(Call<EditProfile> call, final Response<EditProfile> response) {
                        if (response.body() != null) {
                            if (onSuccessEdit != null) {
                                onSuccessEdit.accept(response.body());
                            }
                        }

                        else {  // TODO: return error
                            if (onErrorEdit != null) {
                                onErrorEdit.accept(new Exception());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<EditProfile> call, Throwable t) {
                        if (onErrorEdit != null) {
                            onErrorEdit.accept(t);
                        }
                    }
                });
    }

    @NonNull
    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                MultipartBody.FORM, descriptionString);
    }



    public void setOnSuccess(Consumer<Profile> onSuccess) {
        this.onSuccess = onSuccess;
    }

    public void setOnSuccessEdit(Consumer<EditProfile> onSuccessEdit1) {

        this.onSuccessEdit = onSuccessEdit1;
    }

    public void setOnError(Consumer<Throwable> onError) {
        this.onError = onError;
    }

    public void setOnErrorEdit(Consumer<Throwable> onErrorEdit) {
        this.onErrorEdit = onError;
    }

}
