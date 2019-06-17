package com.codesroots.hossam.lordApp.presentation.screens.home.storesFragment;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.graphics.Color;
import android.support.v4.util.Consumer;
import android.view.View;
import android.widget.TextView;

import com.codesroots.hossam.lordApp.entities.StoresList;
import com.codesroots.hossam.lordApp.dataLayer.repositories.AllStorsRepository;
import com.codesroots.hossam.lordApp.entities.StoresFromGoogleModel;

import java.util.ArrayList;
import java.util.List;

public class StoresViewModel extends ViewModel {

    private AllStorsRepository allStorsRepository;
    List<StoresList.DataBean> allStories = new ArrayList<>();
    MutableLiveData<List<StoresList.DataBean>> allStoriesLiveData = new MutableLiveData<List<StoresList.DataBean>>();
    MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();
    MutableLiveData<Boolean> loading = new MutableLiveData<>();
    String place, ratecount, chat, name, cover, logo, ratenum;
    int ratestar;
    String like = "0";

    public StoresViewModel() {
    }

    public StoresViewModel(final AllStorsRepository allStorsRepository) {

        allStorsRepository.setOnSuccess(new Consumer<StoresList>() {
            @Override
            public void accept(StoresList storesList) {
                allStories.addAll(storesList.getData());
                allStoriesLiveData.postValue(allStories);
                loading.postValue(false);
            }
        });


        allStorsRepository.setOnSuccessGooglePlaces(storesFromGoogleModel -> {

            for (int i = 0; i < storesFromGoogleModel.getResults().size(); i++) {
                StoresList.DataBean store = new StoresList.DataBean();
                store.setName(storesFromGoogleModel.getResults().get(i).getName());
                store.setLogo(storesFromGoogleModel.getResults().get(i).getIcon());
                store.setLatitude(storesFromGoogleModel.getResults().get(i).getGeometry().getLocation().getLat());
                store.setLongitude(storesFromGoogleModel.getResults().get(i).getGeometry().getLocation().getLng());
                store.setAddress(storesFromGoogleModel.getResults().get(i).getVicinity());
                StoresList.DataBean.StoreratesBean storeratesBean = new StoresList.DataBean.StoreratesBean();
                storeratesBean.setStars((int) storesFromGoogleModel.getResults().get(i).getRating());
                List<StoresList.DataBean.StoreratesBean> storeratesBeans = new ArrayList<>();
                storeratesBeans.add(storeratesBean);
                store.setStorerates(storeratesBeans);
                store.setRate((float) storesFromGoogleModel.getResults().get(i).getRating());

                if (storesFromGoogleModel.getResults().get(i).getPhotos() != null) {
                    store.setCover(storesFromGoogleModel.getResults().get(i).getPhotos().get(0).getPhoto_reference());
                    store.setMaxwidth(storesFromGoogleModel.getResults().get(i).getPhotos().get(0).getWidth());
                }

                store.setFrom_google(true);
                allStories.add(store);
            }
            allStoriesLiveData.postValue(allStories);

        });

        allStorsRepository.setOnError(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                errorLiveData.postValue(throwable);
                loading.postValue(false);
            }
        });
        this.allStorsRepository = allStorsRepository;
    }

    public ObservableField<String> resultImageUrl = new ObservableField<>();

    public void getGoogleResults(String location)
    {
        allStorsRepository.getAllStoresFromGoogleData(location);

    }


    @BindingAdapter("bind:textstr")
    public static void settext(TextView view, String color) {
        if (color!=null)
        view.setTextColor(Color.parseColor(color));
        else
            view.setBackgroundColor(Color.parseColor("#000000"));
    }


    @BindingAdapter("bind:background")
    public static void setback(View view, String backgroundcolor) {
        if (backgroundcolor!=null)
            view.setBackgroundColor(Color.parseColor(backgroundcolor));
        else
            view.setBackgroundColor(Color.parseColor("#ffffff"));
    }


    @BindingAdapter("bind:background")
    public static void onClickItem(View view,String backgroundcolor) {
        if (backgroundcolor!=null)
            view.setBackgroundColor(Color.parseColor(backgroundcolor));
        else
            view.setBackgroundColor(Color.parseColor("#ffffff"));
    }

    public int getRatestar() {
        return ratestar;
    }

    public void setRatestar(int ratestar) {
        this.ratestar = ratestar;
    }

    public String getRatenum() {
        return ratenum;
    }

    public void setRatenum(String ratenum) {
        this.ratenum = ratenum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getRatecount() {
        return ratecount;
    }

    public void setRatecount(String ratecount) {
        this.ratecount = ratecount;
    }

    public String getChat() {
        return chat;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }
}
