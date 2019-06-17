package com.codesroots.hossam.lordApp.presentation.screens.home.subcategryFragment.adapters;

import android.content.Context;
import android.os.Bundle;
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
import com.codesroots.hossam.lordApp.entities.StoreSettingEntity;
import com.codesroots.hossam.lordApp.R;
import com.codesroots.hossam.lordApp.presentation.screens.home.storesFragment.StoresFragment;

import java.util.List;

public class SubCategoriesAdapter extends RecyclerView.Adapter<SubCategoriesAdapter.CustomView> {


    private LayoutInflater layoutInflater;
    private List<Categories.DataBean.SubcatsBean> dataBeanArrayList;
    private Context context;
    private StoreSettingEntity.DataBean.StoresettingsBean.DesignBean designBean;
    String depart;

    public SubCategoriesAdapter(FragmentActivity activity, List<Categories.DataBean.SubcatsBean> subcats, String depart1) {
        this.dataBeanArrayList = subcats;
        this.context =  activity;
        this.depart = depart1;
    }


    @Override
    public SubCategoriesAdapter.CustomView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home1_subcategries_model, parent, false);

        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();

        DisplayMetrics displayMetrics =  context.getResources().getDisplayMetrics();
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        //////set percentage from all screen
        layoutParams.width = (width / 3)-20;
        layoutParams.height = layoutParams.width;
        view.setLayoutParams(layoutParams);
        return new SubCategoriesAdapter.CustomView(view);
    }


    @Override
    public void onBindViewHolder(@NonNull CustomView holder, final int position) {

        holder.title.setText(dataBeanArrayList.get(position).getName());
        Glide.with(context)
                .load(dataBeanArrayList.get(position).getPhoto())
                .error(R.drawable.storelogo)
                .into(holder.itemImage);

        holder.itemImage.setOnClickListener(v -> {

            Fragment fragment = new StoresFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("type",1);
            bundle.putInt("categryId",dataBeanArrayList.get(position).getId());
            bundle.putString("depart",depart);

            fragment.setArguments(bundle);
            ((FragmentActivity)context).getSupportFragmentManager().beginTransaction().
                                replace(R.id.main_frame,fragment).
                                    addToBackStack(null).commit();
        });
    }

    @Override
    public int getItemCount() {
        return dataBeanArrayList.size();
    }

    class CustomView extends RecyclerView.ViewHolder {

        private final View mView;
        private ImageView itemImage;
        private TextView title;
        private CustomView(View view) {
            super(view);
            mView = view;
            itemImage =mView.findViewById(R.id.sub_categimage);
            title=mView.findViewById(R.id.subcate_name);
        }
    }
}
