package com.codesroots.hossam.lordApp.dataLayer.repositories;

import android.arch.lifecycle.MutableLiveData;
import android.support.v4.util.Consumer;
import android.util.Log;

import com.codesroots.hossam.lordApp.dataLayer.apiData.ApiInterface;
import com.codesroots.hossam.lordApp.presentation.screens.home.userCartFragment.helper.ProductInfoToPost;
import com.codesroots.hossam.lordApp.presentation.screens.home.userCartFragment.helper.ProductModel;

import java.io.IOException;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PaymentRepository {


    private ApiInterface apiService;
    private Consumer<Boolean> onSuccess;
    private Consumer<Throwable> onError;
    List<ProductInfoToPost> productsdata;

    public PaymentRepository(ApiInterface apiService, List<ProductModel> productsdata) {
        this.apiService = apiService;
    }

    public void addOrder(List<ProductModel> ProductModels, MutableLiveData<Boolean> saveResultLiveData, MutableLiveData<Throwable> errorLiveData) {

        apiService.makeOrderProduct(ProductModels).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
                if (response.body() != null) {
                    if (response.isSuccessful()) {
                        if (saveResultLiveData != null) {
                            saveResultLiveData.postValue(true);
                        }
                    }
                    else {
                        // TODO: return error
                        if (errorLiveData != null) {
                            errorLiveData.postValue(new Exception());
                        }
                    }
                    if (errorLiveData != null) {
                        errorLiveData.postValue(new Exception());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (errorLiveData != null) {
                    errorLiveData.postValue(t);
                }
            }
        });
    }

    public void addOrderFromGoogle(List<ProductModel> ProductModels, MultipartBody.Part part) {

        apiService.makeOrder(ProductModels, part).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
                if (response.body() != null) {
                    if (response.isSuccessful()) {
                        try {
                            Log.d("rwe", response.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (onSuccess != null) {
                            onSuccess.accept(true);
                        }
                    } else {
                        // TODO: return error
                        if (onError != null) {
                            onError.accept(new Exception());
                        }
                    }

                    // TODO: return error
                    if (onError != null) {
                        onError.accept(new Exception());
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (onError != null) {
                    onError.accept(t);
                }
            }
        });
    }

    public void setOnSuccess(Consumer<Boolean> onSuccess) {
        this.onSuccess = onSuccess;
    }

    public void setOnError(Consumer<Throwable> onError) {
        this.onError = onError;
    }


}
