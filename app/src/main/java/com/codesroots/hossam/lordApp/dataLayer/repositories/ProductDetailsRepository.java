package com.codesroots.hossam.lordApp.dataLayer.repositories;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.util.Consumer;
import android.util.Log;

import com.codesroots.hossam.lordApp.dataLayer.apiData.ApiInterface;
import com.codesroots.hossam.lordApp.entities.ProductDetailsModel;
import com.codesroots.hossam.lordApp.dataLayer.localDatabase.userCart.deo.ProductDeo;
import com.codesroots.hossam.lordApp.dataLayer.localDatabase.userCart.entities.Product;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProductDetailsRepository {

    private ApiInterface apiService;
    private Consumer<ProductDetailsModel> onSuccess;
    private Consumer<Throwable> onError;
    private ProductDeo productDeo;
    private Consumer<Boolean> resultChieckForAdd;
    private Consumer<Integer> counter;
    private Consumer<Boolean> onSuccessFav;
    private int productid,userid,stor;
    private Consumer<Boolean> onSuccessFavDelete;

    public ProductDetailsRepository(ApiInterface apiService1, ProductDeo pDeo, int productid1, int storID, int user_id)
    {
        apiService = apiService1;
        productDeo=pDeo;
        this.productid = productid1;
        stor = storID;
        userid = user_id;

    }

    public void getProductDetailsData() {
        try {
            apiService.getProductDetails(productid,userid).enqueue(new Callback<ProductDetailsModel>() {
                @Override
                public void onResponse(Call<ProductDetailsModel> call, final Response<ProductDetailsModel> response) {
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
                public void onFailure(Call<ProductDetailsModel> call, Throwable t) {
                    Log.d("GetAllData fail -> ", call.toString());
                    // TODO: return error
                    if (onError != null) {
                        onError.accept(t);
                    }
                }
            });
        } catch (Exception e) {
            Log.d("product details", e.getMessage());
            onError.accept(e);
        }
    }



    @NonNull
    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                MultipartBody.FORM, descriptionString);
    }

    public void AddToFav(int userid , int productid, int smallstore_id )
    {
        apiService.addfavourite(createPartFromString(String.valueOf(userid)),createPartFromString(String.valueOf(productid))
                ,createPartFromString(String.valueOf(smallstore_id))).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful())
                    onSuccessFav.accept(true);
                else
                    onSuccessFav.accept(false);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
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




    public void saveDataInDB(Product data) {
        new ProductAsyncTask(productDeo).execute(data);
    }

    public void getProductCount() {
        new ProductCountAsyncTask(productDeo).execute(stor);
    }

    private  class ProductAsyncTask extends AsyncTask<Product, Void, Void> {
        private ProductDeo productdeo;
        public ProductAsyncTask(ProductDeo productDeo) {
            productdeo = productDeo;
        }

        @Override
        protected Void doInBackground(Product... products) {

            int count = productdeo.chieckItemExists(products[0].getProduct_id());
            if (count>0) {
                resultChieckForAdd.accept(false);
            }
            else {
                productdeo.insertProduct(products[0]);
                resultChieckForAdd.accept(true);
            }
            return null;
        }
    }


    private  class ProductCountAsyncTask extends AsyncTask<Integer, Void, Void> {
        private ProductDeo productdeo;
        public ProductCountAsyncTask(ProductDeo productDeo) {
            productdeo = productDeo;
        }

        @Override
        protected Void doInBackground(Integer... products) {
            int count = productdeo.getNumberOfRows(products[0]);
            counter.accept(count);
            return null;
        }
    }


    public void setProductCount(Consumer<Integer> prod_count) {
        this.counter= prod_count;
    }


    public void setbooleanConsumerForAdd(Consumer<Boolean> booleanConsumerForAdd) {
        this.resultChieckForAdd = booleanConsumerForAdd;
    }

    public void setOnSuccess(Consumer<ProductDetailsModel> onSuccess) {
        this.onSuccess = onSuccess;
    }

    public void setDeleteFromFavResult(Consumer<Boolean> booleanDeleteFromFav) {
        this.onSuccessFavDelete = booleanDeleteFromFav;
    }


    public void setAddToToFavResult(Consumer<Boolean> booleanAddToFav1) {
        this.onSuccessFav = booleanAddToFav1;
    }

    public void setOnError(Consumer<Throwable> onError) {
        this.onError = onError;
    }

}
