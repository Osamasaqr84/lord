package com.codesroots.hossam.lordApp.presentation.screens.home.productsDetailsFragment.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.codesroots.hossam.lordApp.entities.ImageToShowModel;
import com.codesroots.hossam.lordApp.presentation.screens.home.productsDetailsFragment.DetailImageActivity;
import com.codesroots.hossam.lordApp.R;
import com.codesroots.hossam.lordApp.entities.ProductDetailsModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hossam on 21/07/2018.
 */

public class SliderPagerAdapter extends PagerAdapter {
    private Context activity;
    List<ProductDetailsModel.DataBean.ProductphotosBean> sliders;
    ArrayList<ImageToShowModel> images = new ArrayList<>();

    public SliderPagerAdapter(FragmentActivity context, List<ProductDetailsModel.DataBean.ProductphotosBean> photos) {
        activity=context;
        sliders = photos;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        LayoutInflater layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert layoutInflater != null;
        View view = layoutInflater.inflate(R.layout.first_productdetails_for_viewpager, container, false);
        ImageView im_slider =  view.findViewById(R.id.item_img);


       Glide.with(activity.getApplicationContext())
                .load(sliders.get(position).getPhoto())
                .into(im_slider);
        container.addView(view);

        im_slider.setOnClickListener(v -> {
            for (int i = 0; i < sliders.size(); i++) {
                ImageToShowModel imageModel = new ImageToShowModel();
                imageModel.setName("Image " + i);
                imageModel.setUrl(sliders.get(i).getPhoto());
                images.add(imageModel);
            }


            Intent intent = new Intent(activity, DetailImageActivity.class);
            intent.putParcelableArrayListExtra("data", images);
            intent.putExtra("pos",position);
            activity.startActivity(intent);
        });
        return view;

    }

    @Override
    public int getCount() {
       return  sliders.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }

}

