package com.codesroots.hossam.lordApp.presentation.screens.home.categoryFragment.adapters;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codesroots.hossam.lordApp.entities.Categories;
import com.codesroots.hossam.lordApp.presentation.screens.home.storesFragment.StoresFragment;
import com.codesroots.hossam.lordApp.presentation.screens.home.subcategryFragment.SubCategoriesFragment;
import com.codesroots.hossam.lordApp.R;

import java.util.ArrayList;
import java.util.List;

public class DepartmentAdapter extends RecyclerView.Adapter<DepartmentAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private Context context;
    List<Categories.DataBean> data;
    List<Categories.SliderBean> slider;

    public DepartmentAdapter(Context context, List<Categories.DataBean> data1, List<Categories.SliderBean> slider1)
    {
        this.context =  context;
        data =data1;
        slider = slider1;
    }

    @NonNull
    @Override
    public DepartmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_depart_item, parent, false);


        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int width = displayMetrics.widthPixels;
        //////set percentage from all screen
        layoutParams.width = (width / 3) + 20;
        layoutParams.height = (width / 3) - 10;
        view.setLayoutParams(layoutParams);

        return new DepartmentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DepartmentAdapter.ViewHolder holder, final int position) {

        holder.name.setText(data.get(position).getName());

        Glide.with(context)
                .load(data.get(position).getPhoto())
                .into(holder.Image);


        holder.mView.setOnClickListener(v -> {

            if (data.get(position).getSubcats().size()>0) {
                Fragment fragment = new SubCategoriesFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("subcategries",  data.get(position));
                bundle.putString("depart",data.get(position).getName());
                bundle.putParcelableArrayList("sliders", (ArrayList<? extends Parcelable>) slider);
                fragment.setArguments(bundle);
                ((FragmentActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, fragment).addToBackStack(null).commit();
            }

            else
            {
                Fragment fragment = new StoresFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("type",0);
                bundle.putInt("categryId",data.get(position).getId());
                bundle.putString("depart",data.get(position).getName());
                fragment.setArguments(bundle);
                ((FragmentActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, fragment).addToBackStack(null).commit();

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        private ImageView Image;
        private TextView name;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            Image = mView.findViewById(R.id.itemimage);
            name = mView.findViewById(R.id.itemtxt);
        }
    }


}
