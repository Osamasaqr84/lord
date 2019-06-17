package com.codesroots.hossam.lordApp.dataLayer.repositories;

import android.support.v4.util.Consumer;
import android.util.Log;

import com.codesroots.hossam.lordApp.dataLayer.apiData.ApiInterface;
import com.codesroots.hossam.lordApp.entities.DeliveryComments;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DeliveryCommentsRepository {

    private ApiInterface apiService;
    private Consumer<DeliveryComments> onSuccess;
    private Consumer<Throwable> onError;
    private int deliveryid;

    public DeliveryCommentsRepository(ApiInterface apiService1, int deliveryid1) {
        apiService = apiService1;
        deliveryid = deliveryid1;
        getRatesData();

    }

    private void getRatesData() {
        try {
            apiService.getDeliveryRates(deliveryid).enqueue(new Callback<DeliveryComments>() {
                @Override
                public void onResponse(Call<DeliveryComments> call, final Response<DeliveryComments> response) {
                    if (response.body() != null) {
                        if (response.isSuccessful()) {
                            if (onSuccess != null) {
                                onSuccess.accept(response.body());
                            }
                        } else {
                            // TODO: return error
                            if (onError != null) {
                                onError.accept(new Exception());
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<DeliveryComments> call, Throwable t) {
                    Log.d("GetAllData fail -> ", call.toString());
                    // TODO: return error
                    if (onError != null) {
                        onError.accept(t);
                    }
                }
            });
        } catch (Exception e) {
            Log.d("SplashRepository", e.getMessage());
            onError.accept(e);
        }
    }


    public void setOnSuccess(Consumer<DeliveryComments> onSuccess) {
        this.onSuccess = onSuccess;
    }


    public void setOnError(Consumer<Throwable> onError) {
        this.onError = onError;
    }

}
