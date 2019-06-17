package com.codesroots.hossam.lordApp.dataLayer.repositories;

import android.support.v4.util.Consumer;
import android.util.Log;

import com.codesroots.hossam.lordApp.dataLayer.apiData.ApiInterface;
import com.codesroots.hossam.lordApp.entities.StoresFromGoogleModel;
import com.codesroots.hossam.lordApp.entities.StoresList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AllStorsRepository {

    private ApiInterface apiService;
    private Consumer<StoresList> onSuccess;
    private Consumer<StoresFromGoogleModel> onSuccessGooglePlaces;
    private Consumer<Throwable> onError;
    private int categryId,type;
    private  String userlocation,categrytype;
    public AllStorsRepository(ApiInterface apiService1, int typeid, int categryid, String userlocation1, String categrytype1)
    {
        apiService = apiService1;
        categryId = categryid;
        type = typeid;
        userlocation = userlocation1;
        categrytype = categrytype1;
        getAllStoriesData();
     //   getAllStoresFromGoogleData(userlocation);
    }

    private void getAllStoriesData() {
        try {
            apiService.getStorsData(categryId,type).enqueue(new Callback<StoresList>() {
                @Override
                public void onResponse(Call<StoresList> call, final Response<StoresList> response) {
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
                public void onFailure(Call<StoresList> call, Throwable t) {
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


    public void getAllStoresFromGoogleData(String location) {
        userlocation = location;
        try {
            apiService.getStoresfroomgooglesData("AIzaSyD8z2lWzm896P2g8VhaBfrVam0JL1BaiW0",userlocation,categrytype).enqueue(new Callback<StoresFromGoogleModel>() {
                @Override
                public void onResponse(Call<StoresFromGoogleModel> call, final Response<StoresFromGoogleModel> response) {
                    if (response.body() != null) {
                        if (response.isSuccessful()) {
                            if (onSuccessGooglePlaces != null) {
                                onSuccessGooglePlaces.accept(response.body());
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
                public void onFailure(Call<StoresFromGoogleModel> call, Throwable t) {
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


    public void setOnSuccess(Consumer<StoresList> onSuccess) {
        this.onSuccess = onSuccess;
    }

    public void setOnSuccessGooglePlaces(Consumer<StoresFromGoogleModel> onSuccessGooglePlaces) {
        this.onSuccessGooglePlaces = onSuccessGooglePlaces;
    }


    public void setOnError(Consumer<Throwable> onError) {
        this.onError = onError;
    }

}
