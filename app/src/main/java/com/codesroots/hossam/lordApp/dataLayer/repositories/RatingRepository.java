package com.codesroots.hossam.lordApp.dataLayer.repositories;

import android.support.annotation.NonNull;
import android.support.v4.util.Consumer;
import android.util.Log;

import com.codesroots.hossam.lordApp.dataLayer.apiData.ApiInterface;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RatingRepository {

    private ApiInterface apiService;
    private Consumer<Boolean> onSuccess;
    private Consumer<Throwable> onError;
    private Consumer<Boolean> loading;


    public RatingRepository(ApiInterface apiService1) {
        apiService = apiService1;

    }


    public  void productRate(int userid, int productid, String comment, float rate) {
        try {
            apiService.productrates(createPartFromString(String.valueOf(userid)), createPartFromString(String.valueOf(productid)),
                    createPartFromString(comment), createPartFromString(String.valueOf(rate))).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
                    if (response.body() != null) {
                        if (response.isSuccessful()) {
                            if (onSuccess != null) {
                                onSuccess.accept(true);
                            }
                        } else {
                            // TODO: return error
                            if (onSuccess != null) {
                                onSuccess.accept(false);
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.d("Rating fail -> ", call.toString());
                    // TODO: return error
                    if (onError != null) {
                        onError.accept(t);
                    }
                }
            });
        } catch (Exception e) {
            Log.d("Rating Repositry", e.getMessage());
            onError.accept(e);
        }
    }


    private void deliveryRate(int userid, int deliveryid, String comment, float rate) {
        try {
            apiService.deliveryrates(createPartFromString(String.valueOf(userid)), createPartFromString(String.valueOf(deliveryid)),
                    createPartFromString(comment), createPartFromString(String.valueOf(rate))).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
                    if (response.body() != null) {
                        if (response.isSuccessful()) {
                            if (onSuccess != null) {
                                onSuccess.accept(true);
                            }
                        } else {
                            // TODO: return error
                            if (onSuccess != null) {
                                onSuccess.accept(false);
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.d("Rating fail -> ", call.toString());
                    // TODO: return error
                    if (onError != null) {
                        onError.accept(t);
                    }
                }
            });
        } catch (Exception e) {
            Log.d("Rating Repositry", e.getMessage());
            onError.accept(e);
        }
    }




    @NonNull
    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                MultipartBody.FORM, descriptionString);
    }


    public void setOnSuccess(Consumer<Boolean> onSuccess) {
        this.onSuccess = onSuccess;
    }


    public void setOnError(Consumer<Throwable> onError) {
        this.onError = onError;
    }

}
