package com.codesroots.hossam.lordApp.dataLayer.repositories;

import android.support.v4.util.Consumer;
import android.util.Log;

import com.codesroots.hossam.lordApp.entities.RatessOfProductModel;
import com.codesroots.hossam.lordApp.dataLayer.apiData.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProductRatesRepository {

    private ApiInterface apiService;
    private Consumer<RatessOfProductModel> onSuccess;
    private Consumer<Throwable> onError;
    private int productid,type;
    public ProductRatesRepository(ApiInterface apiService1, int typeid, int productid1)
    {
        apiService = apiService1;
        productid = productid1;
        type = typeid;
        getRatesData();

    }

    private void getRatesData() {
        try {
            apiService.getProductsRate(productid,type).enqueue(new Callback<RatessOfProductModel>() {
                @Override
                public void onResponse(Call<RatessOfProductModel> call, final Response<RatessOfProductModel> response) {
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
                public void onFailure(Call<RatessOfProductModel> call, Throwable t) {
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




    public void setOnSuccess(Consumer<RatessOfProductModel> onSuccess) {
        this.onSuccess = onSuccess;
    }


    public void setOnError(Consumer<Throwable> onError) {
        this.onError = onError;
    }

}
