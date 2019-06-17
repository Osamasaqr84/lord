package com.codesroots.hossam.lordApp.presentation.screens.home.productsDetailsFragment.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.codesroots.hossam.lordApp.helper.BroadcastHelper;
import com.codesroots.hossam.lordApp.R;
import com.codesroots.hossam.lordApp.entities.ProductDetailsModel;

import java.util.List;



public class ImagesAdapterForSideImages extends RecyclerView.Adapter<ImagesAdapterForSideImages.ViewHolder>  {

    private Context mcontext;
    private int pos;
    private LayoutInflater inflater;
    private ImageView ivGallery;
    List<ProductDetailsModel.DataBean.ProductphotosBean> images;

    public ImagesAdapterForSideImages(Context context, List<ProductDetailsModel.DataBean.ProductphotosBean> productphotos) {
        this.mcontext = context;
        this.images = productphotos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_image_side_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        Glide.with(mcontext)
                .load(images.get(position).getPhoto())
                .into(  holder.ivGallery);

        holder.ivGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("image_position");
                intent.putExtra("position",position);
                BroadcastHelper.sendInform(mcontext, "image_position", intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return images.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        public ImageView ivGallery;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ivGallery =itemView.findViewById(R.id.ivGallery);
        }
    }

}

