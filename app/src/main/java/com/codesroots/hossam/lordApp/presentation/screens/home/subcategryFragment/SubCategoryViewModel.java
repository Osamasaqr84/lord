package com.codesroots.hossam.lordApp.presentation.screens.home.subcategryFragment;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.graphics.Color;
import android.support.v4.util.Consumer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codesroots.hossam.lordApp.entities.Categories;
import com.codesroots.hossam.lordApp.dataLayer.repositories.CategoryRepository;

public class SubCategoryViewModel extends ViewModel {


    private CategoryRepository categoryRepository;
    MutableLiveData<Categories> firstHomeCategriesLiveData = new MutableLiveData<>();
    MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();
    MutableLiveData<Boolean> loading = new MutableLiveData<>();
    ImageView image;
    private   String name,imagepath,backcolor,textcolor;


    public SubCategoryViewModel() {

    }

    public SubCategoryViewModel(final CategoryRepository categoryRepository) {

        categoryRepository.setOnSuccess(new Consumer<Categories>() {
            @Override
            public void accept(Categories categories) {
                firstHomeCategriesLiveData.postValue(categories);
           //     resultImageUrl.set("http://parashot.codesroots.com/library/default/burger.png");
                loading.postValue(false);
            }
        });

        categoryRepository.setOnError(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                errorLiveData.postValue(throwable);
                loading.postValue(false);
            }
        });
        this.categoryRepository = categoryRepository;
    }

    public ObservableField<String> resultImageUrl = new ObservableField<>();

    public void imageUrlUpdated(String url) {
        resultImageUrl.set(url);
    }

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView view, String url) {
        Glide.with(view.getContext()).load(url).into(view);
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


    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBackcolor() {
        return backcolor;
    }

    public void setBackcolor(String backcolor) {
        this.backcolor = backcolor;
    }

    public String getTextcolor() {
        return textcolor;
    }

    public void setTextcolor(String textcolor) {
        this.textcolor = textcolor;
    }

}
