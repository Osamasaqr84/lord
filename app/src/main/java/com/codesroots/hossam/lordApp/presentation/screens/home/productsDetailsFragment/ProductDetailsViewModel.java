package com.codesroots.hossam.lordApp.presentation.screens.home.productsDetailsFragment;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.v4.util.Consumer;

import com.codesroots.hossam.lordApp.dataLayer.localDatabase.userCart.entities.Product;
import com.codesroots.hossam.lordApp.dataLayer.repositories.ProductDetailsRepository;
import com.codesroots.hossam.lordApp.entities.ProductDetailsModel;

public class ProductDetailsViewModel extends ViewModel {


     ProductDetailsRepository productDetailsRepository;
    MutableLiveData<ProductDetailsModel> productDetails_MutableLiveData = new MutableLiveData<>();
    MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();
    public MutableLiveData<Boolean> addToFavoriteLiveData = new MutableLiveData<>();
    MutableLiveData<Boolean> loading = new MutableLiveData<>();
    public MutableLiveData<Boolean> stor_or_not_MutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Integer> product_count_MutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Boolean> deleteFromFavoriteLiveData = new MutableLiveData<>();

    public ProductDetailsViewModel() {
    }

    public ProductDetailsViewModel(final ProductDetailsRepository repository) {

        repository.setOnSuccess(products -> {
            productDetails_MutableLiveData.postValue(products);
            loading.postValue(false);
        });

        repository.setOnError(throwable -> {
            errorLiveData.postValue(throwable);
            loading.postValue(false);
        });

        repository.setbooleanConsumerForAdd(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) {
                stor_or_not_MutableLiveData.postValue(aBoolean);
            }
        });

        repository.setProductCount(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                product_count_MutableLiveData.postValue(integer);
            }
        });

        repository.setAddToToFavResult(aBoolean ->
                addToFavoriteLiveData.postValue(aBoolean)
        );

        repository.setDeleteFromFavResult(aBoolean ->
                deleteFromFavoriteLiveData.postValue(aBoolean)
        );


        this.productDetailsRepository = repository;
        loadData();
        getCount();
    }

    private void loadData() {
        loading.setValue(true);
        productDetailsRepository.getProductDetailsData();
    }

    public  void AddToFav (int user_id , int product_id, int smallstore_id, ProductDetailsRepository allProducts_repository1 )
    {
        allProducts_repository1.AddToFav(user_id,product_id,smallstore_id);
    }


    public  void DeleteFav (int favID, ProductDetailsRepository allProducts_repository1 )
    {
        allProducts_repository1.DeleteFav(favID);
    }

    public void storeData(Product dataBeans) {
        productDetailsRepository.saveDataInDB(dataBeans);
    }
    public void getCount() {
        productDetailsRepository.getProductCount();
    }
}
