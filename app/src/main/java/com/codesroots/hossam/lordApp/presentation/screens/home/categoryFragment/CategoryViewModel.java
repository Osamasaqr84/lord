package com.codesroots.hossam.lordApp.presentation.screens.home.categoryFragment;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.codesroots.hossam.lordApp.dataLayer.repositories.CategoryRepository;
import com.codesroots.hossam.lordApp.entities.BestOffers;
import com.codesroots.hossam.lordApp.entities.Categories;
import com.codesroots.hossam.lordApp.entities.MoreRate;

public class CategoryViewModel extends ViewModel {
    private CategoryRepository categoryRepository;
    MutableLiveData<Categories> categoriesLiveData = new MutableLiveData<>();
    MutableLiveData<BestOffers> BestOffersLiveData = new MutableLiveData<>();
    MutableLiveData<MoreRate> MoreRateLiveData = new MutableLiveData<>();
    MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();
    MutableLiveData<Throwable> errorMoreRateLiveData = new MutableLiveData<>();
    MutableLiveData<Throwable> errorBestOffersLiveData = new MutableLiveData<>();
    MutableLiveData<Boolean> loadingmorerate = new MutableLiveData<>();
    MutableLiveData<Boolean> loadingCAtegry = new MutableLiveData<>();
    MutableLiveData<Boolean> loadingBestoffers = new MutableLiveData<>();


    public CategoryViewModel() {

    }

    public CategoryViewModel(final CategoryRepository categoryRepository) {

        categoryRepository.setOnSuccess(categories -> {
            categoriesLiveData.postValue(categories);
            loadingCAtegry.postValue(false);
        });

        categoryRepository.setOnSuccessBest(bestOffers -> {
            BestOffersLiveData.postValue(bestOffers);
            loadingBestoffers.postValue(false);
        });

        categoryRepository.setonSuccessMoreRate(moreRate -> {
            MoreRateLiveData.postValue(moreRate);
            loadingmorerate.postValue(false);
        });

        categoryRepository.setOnError(throwable -> {
            errorLiveData.postValue(throwable);
            loadingCAtegry.postValue(false);
        });


        categoryRepository.onErrorBestOffers(throwable -> {
            errorBestOffersLiveData.postValue(throwable);
            loadingBestoffers.postValue(false);
        });

        categoryRepository.onErrorMoreRate(throwable -> {
            errorMoreRateLiveData.postValue(throwable);
            loadingmorerate.postValue(false);
        });


        this.categoryRepository = categoryRepository;

        loadData();
    }

    public void loadData() {
        loadingCAtegry.setValue(true);
        categoryRepository.getAllCategoryData();
        categoryRepository.getBestOffers();
        categoryRepository.getMoreRate();
    }


}
