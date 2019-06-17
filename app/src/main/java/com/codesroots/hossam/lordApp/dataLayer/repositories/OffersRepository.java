package com.codesroots.hossam.lordApp.dataLayer.repositories;

import android.os.AsyncTask;
import android.support.v4.util.Consumer;
import android.util.Log;

import com.codesroots.hossam.lordApp.entities.DealsModel;
import com.codesroots.hossam.lordApp.entities.OffersModel;
import com.codesroots.hossam.lordApp.dataLayer.apiData.ApiInterface;
import com.codesroots.hossam.lordApp.dataLayer.localDatabase.userCart.deo.ProductDeo;
import com.codesroots.hossam.lordApp.dataLayer.localDatabase.userCart.entities.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OffersRepository {

    private ApiInterface apiService;
    private Consumer<OffersModel> onOffersSuccess;
    private Consumer<DealsModel> onDealsSuccess;
    private Consumer<Throwable> onError;

    private Consumer<Boolean> booleanConsumerForAdd;
    private Consumer<Integer> sizeConsumer;

    private ProductDeo productDao;

    public OffersRepository(ApiInterface apiService1)
    {
        apiService = apiService1;
        getOffersData();
        getDealsData();
    }

    private void getOffersData() {
        try {
            apiService.getOffers().enqueue(new Callback<OffersModel>() {
                @Override
                public void onResponse(Call<OffersModel> call, final Response<OffersModel> response) {
                    if (response.body() != null) {
                        if (response.isSuccessful()) {
                            if (onOffersSuccess != null) {
                                onOffersSuccess.accept(response.body());
                            }
                        }

                        else
                        {
                            // TODO: return error
                            if (onError != null) {
                                onError.accept(new Exception());
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<OffersModel> call, Throwable t) {
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


    private void getDealsData() {
        try {
            apiService.getDeals().enqueue(new Callback<DealsModel>() {
                @Override
                public void onResponse(Call<DealsModel> call, final Response<DealsModel> response) {
                    if (response.body() != null) {
                        if (response.isSuccessful()) {
                            if (onDealsSuccess != null) {
                                onDealsSuccess.accept(response.body());
                            }
                        }

                        else
                        {
                            // TODO: return error
                            if (onError != null) {
                                onError.accept(new Exception());
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<DealsModel> call, Throwable t) {
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

/*
    private FilterMyOrder filterData(OffersModel body) {

        List<OffersModel.DataBean> commpleteOrderData=new ArrayList<>();
        List<OffersModel.DataBean> notCommpleteOrderData=new ArrayList<>();

        for (int i=0;i<body.getData().size();i++)
        {
            if (body.getData().get(i).getOrder_status().matches("3"))
                commpleteOrderData.add(body.getData().get(i));
            else
                notCommpleteOrderData.add(body.getData().get(i));

        }

        return new FilterMyOrder(commpleteOrderData,notCommpleteOrderData);
    }

*/

    public void setOnOffersSuccess(Consumer<OffersModel> onSuccess) {
        this.onOffersSuccess = onSuccess;
    }
    public void setOnDealsSuccess(Consumer<DealsModel> onSuccess) {
        this.onDealsSuccess = onSuccess;
    }


    public void setOnError(Consumer<Throwable> onError) {
        this.onError = onError;
    }

   public List<Product> selectAllfromStore(int storeId){ return productDao.selectAllProductForStore(storeId) ; };
    public void saveDataInDB(Product data) {
        new ProductAsyncTask(productDao).execute(data);
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
                booleanConsumerForAdd.accept(false);
            }
            else {
                productdeo.insertProduct(products[0]);
                booleanConsumerForAdd.accept(true);
            }
            return null;
        }
    }

    private  class SizeAsyncTask extends AsyncTask<Integer, Void, Integer> {
        private ProductDeo productdeo;
        public SizeAsyncTask(ProductDeo productDeo) {
            productdeo = productDeo;
        }

        @Override
        protected Integer doInBackground(Integer... storeID) {

            Integer count = Integer.valueOf(productdeo.selectAllProductForStore(storeID[0]).size());

            return null;
        }


    }

    public void setSizeConsumer(Consumer<Integer> sizeConsumer) {
        this.sizeConsumer = sizeConsumer;
    }

    public void setbooleanConsumerForAdd(Consumer<Boolean> booleanConsumerForAdd) {
        this.booleanConsumerForAdd = booleanConsumerForAdd;
    }


}
