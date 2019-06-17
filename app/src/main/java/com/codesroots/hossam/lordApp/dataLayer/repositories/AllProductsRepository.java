package com.codesroots.hossam.lordApp.dataLayer.repositories;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.util.Consumer;
import android.util.Log;

import com.codesroots.hossam.lordApp.dataLayer.apiData.ApiInterface;
import com.codesroots.hossam.lordApp.entities.Products_in_Stories_Model;
import com.codesroots.hossam.lordApp.dataLayer.localDatabase.userCart.deo.ProductDeo;
import com.codesroots.hossam.lordApp.dataLayer.localDatabase.userCart.entities.Product;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AllProductsRepository {

    private ApiInterface apiService;
    private Consumer<Products_in_Stories_Model> onSuccess;
    private Consumer<Boolean> booleanConsumerForAddToCart;
    private Consumer<Integer> productsCount;
    private Consumer<Throwable> onError;
    private Consumer<Boolean> onSuccessFavAdd;
    private Consumer<Boolean> onSuccessFavDelete;
    private int store_id,userid;


    private ProductDeo productDeo;


    public AllProductsRepository(ApiInterface apiService1, ProductDeo pDeo, int storeid,int userID)
    {
        apiService = apiService1;
        productDeo=pDeo;
        store_id = storeid;
        userid = userID;
        getAllProductData();
    }

    public void getAllProduct()
    {
        this.getAllProductData();
    }

    private void getAllProductData() {
        try {
            apiService.getProductsData(store_id,userid).enqueue(new Callback<Products_in_Stories_Model>() {
                @Override
                public void onResponse(Call<Products_in_Stories_Model> call, final Response<Products_in_Stories_Model> response) {
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
                public void onFailure(Call<Products_in_Stories_Model> call, Throwable t) {
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

    public void AddToFav(int userid , int productid, int smallstore_id )
    {
        apiService.addfavourite(createPartFromString(String.valueOf(userid)),createPartFromString(String.valueOf(productid))
                ,createPartFromString(String.valueOf(smallstore_id))).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful())
                    onSuccessFavAdd.accept(true);
                else
                    onSuccessFavAdd.accept(false);
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


    @NonNull
    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                MultipartBody.FORM, descriptionString);
    }

    public void saveDataInDB(Product data) {
        new AllProductsRepository.ProductAsyncTask(productDeo).execute(data);
    }

    public void getProductCount() {
        new AllProductsRepository.ProductCountAsyncTask(productDeo).execute(store_id);
    }

    private  class ProductAsyncTask extends AsyncTask<Product, Void, Boolean> {
        private ProductDeo productdeo;
        public ProductAsyncTask(ProductDeo productDeo) {
            productdeo = productDeo;
        }

        @Override
        protected Boolean doInBackground(Product... products) {

            int count = productdeo.chieckItemExists(products[0].getProduct_id());
            if (count>0) {
               return  false;
            }

            else {
                productdeo.insertProduct(products[0]);
                if (productdeo.chieckItemExists(products[0].getProduct_id())>0)
                 return  true;
            }

            return null;
        }

        @Override
        protected void onPostExecute(Boolean addornot) {
            super.onPostExecute(addornot);
            booleanConsumerForAddToCart.accept(addornot);
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
            productsCount.accept(count);
            return null;
        }

    }


    public void setOnSuccess(Consumer<Products_in_Stories_Model> onSuccess) {
        this.onSuccess = onSuccess;
    }

    public void setbooleanConsumerForAdd(Consumer<Boolean> booleanConsumerForAdd) {
        this.booleanConsumerForAddToCart = booleanConsumerForAdd;
    }

    public void setAddToToFavResult(Consumer<Boolean> booleanAddToFav1) {
        this.onSuccessFavAdd = booleanAddToFav1;
    }

    public void setDeleteFromFavResult(Consumer<Boolean> booleanDeleteFromFav) {
        this.onSuccessFavDelete = booleanDeleteFromFav;
    }
    public void setProductsCount(Consumer<Integer> counter) {
        this.productsCount = counter;
    }

    public void setOnError(Consumer<Throwable> onError) {
        this.onError = onError;
    }

}
