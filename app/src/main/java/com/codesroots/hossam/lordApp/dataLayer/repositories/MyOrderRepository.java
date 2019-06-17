package com.codesroots.hossam.lordApp.dataLayer.repositories;

import android.support.annotation.NonNull;
import android.support.v4.util.Consumer;
import android.util.Log;

import com.codesroots.hossam.lordApp.dataLayer.apiData.ApiInterface;
import com.codesroots.hossam.lordApp.entities.MYOrdersModel;
import com.codesroots.hossam.lordApp.presentation.screens.home.myOrderFragment.FilterMyOrder;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyOrderRepository {

    private ApiInterface apiService;
    private Consumer<FilterMyOrder> onSuccess;
    private Consumer<Throwable> onError;
    private Consumer<Boolean> onSuccessEdit;
    private Consumer<Throwable> onErrorEdit;

    Call<MYOrdersModel>  cal;
    public MyOrderRepository(ApiInterface apiService1, int userid, int user_group_id)
    {
        apiService = apiService1;
        setupgetData(userid, user_group_id);
    }

    private void setupgetData(int userid, int user_group_id) {

        if (user_group_id == 2) //////////// user
            cal = apiService.getMyOrders(userid);
        else if (user_group_id == 1)  //////// admin
            cal = apiService.getMyOrdersForAdmin();
        else if (user_group_id == 3)  ///////// tradeer
            cal = apiService.getMyOrdersFortrader(userid);

        getMyProductData();
    }


    public  void editOrder(int newstatues, int orderid)
    {
        apiService.
                editOrderStatuesData(orderid,createPartFromString(String.valueOf(newstatues))).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.isSuccessful())
                    onSuccessEdit.accept(true);

                else
                    onSuccessEdit.accept(false);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                    onError.accept(t);
            }
        });
    }

    @NonNull
    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                MultipartBody.FORM, descriptionString);
    }


    private void getMyProductData() {
        try {
            cal.enqueue(new Callback<MYOrdersModel>() {
                @Override
                public void onResponse(Call<MYOrdersModel> call, final Response<MYOrdersModel> response) {
                    if (response.body() != null) {
                        if (response.isSuccessful()) {
                            if (onSuccess != null) {
                                onSuccess.accept(filterData(response.body()));
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
                    else
                        onError.accept(new Exception());
                }

                @Override
                public void onFailure(Call<MYOrdersModel> call, Throwable t) {
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

    private FilterMyOrder filterData(MYOrdersModel body) {

        List<MYOrdersModel.DataBean> commpleteOrderData=new ArrayList<>();
        List<MYOrdersModel.DataBean> notCommpleteOrderData=new ArrayList<>();

        for (int i=0;i<body.getData().size();i++)
        {
            if (body.getData().get(i).getOrder_status().matches("3"))
                commpleteOrderData.add(body.getData().get(i));
            else
                notCommpleteOrderData.add(body.getData().get(i));

        }

        return new FilterMyOrder(commpleteOrderData,notCommpleteOrderData);
    }


    public void setOnSuccess(Consumer<FilterMyOrder> onSuccess) {
        this.onSuccess = onSuccess;
    }

    public void setOnSuccessEdit(Consumer<Boolean> onSuccess) {
        this.onSuccessEdit = onSuccess;
    }

    public void setOnErrorEdit(Consumer<Throwable> onError) {
        this.onErrorEdit = onError;
    }

    public void setOnError(Consumer<Throwable> onError) {
        this.onError = onError;
    }

}
