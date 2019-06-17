package com.codesroots.hossam.lordApp.dataLayer.repositories;

import android.support.v4.util.Consumer;
import android.util.Log;

import com.codesroots.hossam.lordApp.dataLayer.apiData.ApiInterface;
import com.codesroots.hossam.lordApp.entities.DeliveryOffers;
import com.codesroots.hossam.lordApp.entities.StoresFromGoogleModel;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DeliveryOffersRepository {

    private ApiInterface apiService;
    private Consumer<DeliveryOffers> onSuccess;
    private Consumer<Boolean> onSuccessEdit;
    private Consumer<Throwable> onErrorEdit;
    private Consumer<StoresFromGoogleModel> onSuccessGooglePlaces;
    private Consumer<Throwable> onError;
    private int orderid;
    public DeliveryOffersRepository(ApiInterface apiService1, int orderid1)
    {
        apiService = apiService1;
        orderid = orderid1;
        getDeliveryOffers();
    }

    private void getDeliveryOffers() {
        try {
            apiService.getDeliveryOffers(orderid).enqueue(new Callback<DeliveryOffers>() {
                @Override
                public void onResponse(Call<DeliveryOffers> call, final Response<DeliveryOffers> response) {
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
                public void onFailure(Call<DeliveryOffers> call, Throwable t) {
                    Log.d("GetAllData fail -> ", call.toString());
                    // TODO: return error
                    if (onError != null) {
                        onError.accept(t);
                    }
                }
            });
        } catch (Exception e) {
            Log.d("DeliveryRepository", e.getMessage());
            onError.accept(e);
        }
    }

    public  void editOrder(int orderid , RequestBody delivry_id, RequestBody status , RequestBody price)
    {

        apiService.editOrderStatues(orderid,delivry_id,status,price).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful())
                    onSuccessEdit.accept(true);

                else
                    onSuccessEdit.accept(false);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }

    public void setOnSuccess(Consumer<DeliveryOffers> onSuccess) {
        this.onSuccess = onSuccess;
    }


    public void setOnSuccessEdit(Consumer<Boolean> onSuccessEdit) {
        this.onSuccessEdit = onSuccessEdit;
    }

    public void sererroreditOrder(Consumer<Throwable> onErrorEdit1) {
        this.onErrorEdit = onErrorEdit1;
    }

    public void setOnError(Consumer<Throwable> onError) {
        this.onError = onError;
    }

}
