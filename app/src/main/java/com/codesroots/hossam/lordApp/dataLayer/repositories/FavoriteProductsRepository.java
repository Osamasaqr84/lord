package com.codesroots.hossam.lordApp.dataLayer.repositories;

import android.support.v4.util.Consumer;
import android.util.Log;

import com.codesroots.hossam.lordApp.entities.FavProduct;
import com.codesroots.hossam.lordApp.dataLayer.apiData.ApiInterface;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FavoriteProductsRepository {

    private ApiInterface apiService;
    private Consumer<FavProduct> onSuccess;
    private Consumer<Throwable> onError;
    private int user_id;
    private Consumer<Boolean> onSuccessFavDelete;



    public FavoriteProductsRepository(ApiInterface apiService1, int userId)
    {
        apiService = apiService1;
        user_id = userId;
        getAllProduct();
    }

    public void getAllProduct()
    {
        this.getAllProductData();
    }

    private void getAllProductData() {
        try {
            apiService.getFavProductsData(user_id).enqueue(new Callback<FavProduct>() {
                @Override
                public void onResponse(Call<FavProduct> call, final Response<FavProduct> response) {
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
                public void onFailure(Call<FavProduct> call, Throwable t) {
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


    public void DeleteFav(int favid )
    {
        apiService.deleteFav(favid).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful())
                    onSuccessFavDelete.accept(true);
                else
                    onSuccessFavDelete.accept(false);
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }


    public void setOnSuccess(Consumer<FavProduct> onSuccess) {
        this.onSuccess = onSuccess;
    }


    public void setOnError(Consumer<Throwable> onError) {
        this.onError = onError;
    }

    public void setDeleteFromFavResult(Consumer<Boolean> booleanDeleteFromFav) {
        this.onSuccessFavDelete = booleanDeleteFromFav;
    }


}
