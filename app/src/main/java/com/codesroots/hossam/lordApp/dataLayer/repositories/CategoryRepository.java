package com.codesroots.hossam.lordApp.dataLayer.repositories;

import android.support.v4.util.Consumer;
import android.util.Log;

import com.codesroots.hossam.lordApp.dataLayer.apiData.ApiInterface;
import com.codesroots.hossam.lordApp.entities.BestOffers;
import com.codesroots.hossam.lordApp.entities.Categories;
import com.codesroots.hossam.lordApp.entities.MoreRate;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CategoryRepository {

    private ApiInterface apiService;
    private Consumer<Categories> onSuccess;
    private Consumer<BestOffers> onSuccessBestOffera;
    private Consumer<MoreRate> onSuccessMoreRate;
    private Consumer<Throwable> onError;
    private Consumer<Throwable> onErrorBestOffers;
    private Consumer<Throwable> onErrorMoreRate;

    public CategoryRepository(ApiInterface apiService)
    {
        this.apiService = apiService;
    }

    public void getAllCategoryData() {
        try {
            apiService.getCatigriesData().enqueue(new Callback<Categories>() {
                @Override
                public void onResponse(Call<Categories> call, final Response<Categories> response) {
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
                public void onFailure(Call<Categories> call, Throwable t) {
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



    public void getBestOffers() {
        try {
            apiService.getbestoffers().enqueue(new Callback<BestOffers>() {
                @Override
                public void onResponse(Call<BestOffers> call, final Response<BestOffers> response) {
                    if (response.body() != null) {
                        if (response.isSuccessful()) {
                            if (onSuccessBestOffera != null) {
                                onSuccessBestOffera.accept(response.body());
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
                public void onFailure(Call<BestOffers> call, Throwable t) {
                    Log.d("GetAllData fail -> ", call.toString());
                    // TODO: return error
                    if (onErrorBestOffers != null) {
                        onErrorBestOffers.accept(t);
                    }
                }
            });
        } catch (Exception e) {
            Log.d("SplashRepository", e.getMessage());
            onErrorBestOffers.accept(e);
        }
    }


    public void getMoreRate() {
        try {
            apiService.getMoreRate().enqueue(new Callback<MoreRate>() {
                @Override
                public void onResponse(Call<MoreRate> call, final Response<MoreRate> response) {
                    if (response.body() != null) {
                        if (response.isSuccessful()) {
                            if (onSuccessMoreRate != null) {
                                onSuccessMoreRate.accept(response.body());
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
                public void onFailure(Call<MoreRate> call, Throwable t) {
                    Log.d("GetAllData fail -> ", call.toString());
                    // TODO: return error
                    if (onErrorMoreRate != null) {
                        onErrorMoreRate.accept(t);
                    }
                }
            });
        } catch (Exception e) {
            Log.d("SplashRepository", e.getMessage());
            onErrorMoreRate.accept(e);
        }
    }





    public void setOnSuccess(Consumer<Categories> onSuccess) {
        this.onSuccess = onSuccess;
    }

    public void setOnSuccessBest(Consumer<BestOffers> onSuccessBest) {
        this.onSuccessBestOffera = onSuccessBest;
    }

    public void setonSuccessMoreRate(Consumer<MoreRate> onSuccessRate) {
        this.onSuccessMoreRate = onSuccessRate;
    }


    public void setOnError(Consumer<Throwable> onError) {
        this.onError = onError;
    }

    public void onErrorBestOffers(Consumer<Throwable> onError) {
        this.onErrorBestOffers = onError;
    }

    public void onErrorMoreRate(Consumer<Throwable> onError) {
        this.onErrorMoreRate = onError;
    }


}
