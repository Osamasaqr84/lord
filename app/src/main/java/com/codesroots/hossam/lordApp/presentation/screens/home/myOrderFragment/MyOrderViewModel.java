package com.codesroots.hossam.lordApp.presentation.screens.home.myOrderFragment;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.BindingAdapter;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codesroots.hossam.lordApp.dataLayer.repositories.MyOrderRepository;


public class MyOrderViewModel extends ViewModel {

    private MyOrderRepository myOrder_repository;
    MutableLiveData<FilterMyOrder> allMyOrders = new MutableLiveData<FilterMyOrder>();
     MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();
     MutableLiveData<Boolean> loading = new MutableLiveData<>();
    MutableLiveData<Throwable> erroreditOrder = new MutableLiveData<>();
    MutableLiveData<Boolean> successeditOrder = new MutableLiveData<>();
    MutableLiveData<Boolean> loadingeditOrder = new MutableLiveData<>();

    private String name, imagePath, item_description, storenamevalue,
            capitainnamevalue, orderstatuesvalue, orderdatevalue, item_price, ratecount,dateValue;

    private float ratestart;

    public MyOrderViewModel() {
    }

    public MyOrderViewModel(final MyOrderRepository repository) {

        repository.setOnSuccess(orders -> {
            allMyOrders.postValue(orders);
            loading.postValue(false);
        });

        repository.setOnError(throwable -> {
            errorLiveData.postValue(throwable);
            loading.postValue(false);
        });

        repository.setOnSuccessEdit(aBoolean ->
                {
                    successeditOrder.postValue(true);
                    loadingeditOrder.postValue(false);
                }
        );

        repository.setOnErrorEdit(throwable  ->
                {
                    erroreditOrder.postValue(throwable);
                    loadingeditOrder.postValue(false);
                }
        );


        this.myOrder_repository = repository;
    }

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext()).load(url).into(view);
    }


    @BindingAdapter("bind:textcolorvalue")
    public static void settextt(TextView view, String text) {


        if (text.matches("3")) {
            view.setText(" مكتمل");
            view.setTextColor(Color.parseColor("#00FF00"));
        }
        else if (text.matches("1"))
        {
            view.setText(" جاري التسليم  ");
            view.setTextColor(Color.parseColor("#FF0000"));

        }
        else
        {
            view.setText(" في الانتظار ");
            view.setTextColor(Color.parseColor("#FF0000"));
        }
    }

    public void editorder (int orderid,int statues)
    {
        myOrder_repository.editOrder(statues,orderid);
    }

    public String getDateValue() {
        return dateValue;
    }

    public void setDateValue(String dateValue) {
        this.dateValue = dateValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getItem_description() {
        return item_description;
    }

    public void setItem_description(String item_description) {
        this.item_description = item_description;
    }

    public String getStorenamevalue() {
        return storenamevalue;
    }

    public void setStorenamevalue(String storenamevalue) {
        this.storenamevalue = storenamevalue;
    }

    public String getCapitainnamevalue() {
        return capitainnamevalue;
    }

    public void setCapitainnamevalue(String capitainnamevalue) {
        this.capitainnamevalue = capitainnamevalue;
    }

    public String getOrderstatuesvalue() {
        return orderstatuesvalue;
    }

    public void setOrderstatuesvalue(String orderstatuesvalue) {
        this.orderstatuesvalue = orderstatuesvalue;
    }

    public String getOrderdatevalue() {
        return orderdatevalue;
    }

    public void setOrderdatevalue(String orderdatevalue) {
        this.orderdatevalue = orderdatevalue;
    }

    public String getItem_price() {
        return item_price;
    }

    public void setItem_price(String item_price) {
        this.item_price = item_price;
    }

    public String getRatecount() {
        return ratecount;
    }

    public void setRatecount(String ratecount) {
        this.ratecount = ratecount;
    }

    public float getRatestart() {
        return ratestart;
    }

    public void setRatestart(float ratestart) {
        this.ratestart = ratestart;
    }
}
