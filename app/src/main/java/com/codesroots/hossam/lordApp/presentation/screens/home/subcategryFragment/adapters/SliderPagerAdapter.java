package com.codesroots.hossam.lordApp.presentation.screens.home.subcategryFragment.adapters;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.codesroots.hossam.lordApp.entities.Categories;
import com.codesroots.hossam.lordApp.R;

import java.util.List;

/**
 * Created by hossam on 21/07/2018.
 */

public class SliderPagerAdapter extends PagerAdapter {
    private Context activity;
    List<Categories.SliderBean> sliders;
    public SliderPagerAdapter(FragmentActivity context, List<Categories.SliderBean> sliders1) {
        activity=context;
        sliders = sliders1;
    }


    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        LayoutInflater layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert layoutInflater != null;
        View view = layoutInflater.inflate(R.layout.viewpagerslide_home1, container, false);
        ImageView im_slider =  view.findViewById(R.id.im_slider);
       // TextView name= view.findViewById(R.id.doctornametxt);

       Glide.with(activity.getApplicationContext())
                .load(sliders.get(position).getPhoto())
                .into(im_slider);
        container.addView(view);
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

