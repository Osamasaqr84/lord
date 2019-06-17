package com.codesroots.hossam.lordApp.presentation.screens.home.userCartFragment;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.support.v4.util.Consumer;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.codesroots.hossam.lordApp.dataLayer.localDatabase.userCart.entities.Product;
import com.codesroots.hossam.lordApp.dataLayer.repositories.UserCartRepository;

import java.util.List;

public class UserCartViewModel extends ViewModel {


    public UserCartRepository userCartRepository;
    MutableLiveData<List<Product>> ProductLiveData = new MutableLiveData<>();
    MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();
    MutableLiveData<Boolean> loading = new MutableLiveData<>();

    String name,imagepath,retecount,price;
    float rateStart;
    int quentity = 1;
    public UserCartViewModel() {
    }

    public UserCartViewModel(final UserCartRepository userCartRepository) {

        userCartRepository.setcartItemss(products -> {
            ProductLiveData.postValue(products);
            loading.postValue(false);
        });

        userCartRepository.setOnError(throwable -> {
            errorLiveData.postValue(throwable);
            loading.postValue(false);
        });

        this.userCartRepository = userCartRepository;
        userCartRepository.GetProductsFromDB();
    }

    public void deleteProductFromDB(Product product, UserCartRepository userCartRepository) {
        userCartRepository.deleteProductFromDB(product);
        userCartRepository.setcartItemss(new Consumer<List<Product>>() {
            @Override
            public void accept(List<Product> products) {
                ProductLiveData.postValue(products);
                loading.postValue(false);
            }
        });
    }

    public ObservableField<String> resultImageUrl = new ObservableField<>();


    public void imageUrlUpdated(String url) {
        resultImageUrl.set(url);
    }

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext()).load(url).into(view);
    }

    public String getQuentity() {
        return String.valueOf(quentity);
    }

    public void setQuentity(int quentity) {
        this.quentity = quentity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public String getRetecount() {
        return retecount;
    }

    public void setRetecount(String retecount) {
        this.retecount = retecount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public float getRateStart() {
        return rateStart;
    }

    public void setRateStart(float rateStart) {
        this.rateStart = rateStart;
    }
}
