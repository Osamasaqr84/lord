package com.codesroots.hossam.lordApp.dataLayer.repositories;

import android.support.annotation.NonNull;
import android.support.v4.util.Consumer;

import com.codesroots.hossam.lordApp.dataLayer.apiData.ApiInterface;
import com.codesroots.hossam.lordApp.entities.RegisterResponseModel;
import com.codesroots.hossam.lordApp.entities.User;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterRepository {


    private ApiInterface apiService;
    private Consumer<RegisterResponseModel> onSuccess;
    private Consumer<Integer> codeResbonse;
    private Consumer<Throwable> onError;

    public RegisterRepository(ApiInterface apiService) {
        this.apiService = apiService;
    }

    public void registerUser(User user) {

         apiService.register(
                 createPartFromString(user.getUsername()),
                 createPartFromString(user.getPassword()),
                 createPartFromString(user.getGender()),
                 createPartFromString(user.getMobile()),
                 createPartFromString(user.getBirthdate())
                ).
                 enqueue(new Callback<RegisterResponseModel>() {
            @Override
            public void onResponse(Call<RegisterResponseModel> call, final Response<RegisterResponseModel> response) {
                if (response.code() == 422) {
                        if (codeResbonse != null)
                            codeResbonse.accept(422);
                }

                else {
                    if (response.body() != null) {

                        if (onSuccess != null) {
                            onSuccess.accept(response.body());
                            codeResbonse.accept(200);
                        }
                    } else {  // TODO: return error
                        if (onError != null) {
                            onError.accept(new Exception());
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<RegisterResponseModel> call, Throwable t) {
                if (onError != null) {
                    onError.accept(t);
                }
            }
        });
    }

    @NonNull
    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                MultipartBody.FORM, descriptionString);
    }

    public void setOnSuccess(Consumer<RegisterResponseModel> onSuccess) {
        this.onSuccess = onSuccess;
    }

    public void setcodeSuccess(Consumer<Integer> code) {
        this.codeResbonse = code;
    }


    public void setOnError(Consumer<Throwable> onError) {
        this.onError = onError;
    }


}
