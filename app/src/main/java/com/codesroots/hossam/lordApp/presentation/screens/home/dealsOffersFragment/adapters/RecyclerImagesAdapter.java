package com.codesroots.hossam.lordApp.presentation.screens.home.dealsOffersFragment.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.codesroots.hossam.lordApp.entities.DealsModel;
import com.codesroots.hossam.lordApp.R;

import java.util.List;

public class RecyclerImagesAdapter extends RecyclerView.Adapter<RecyclerImagesAdapter.ViewHolder>  {

    private Context context;
    private List<DealsModel.Data.Product.Productphotos> productphotos;



    public RecyclerImagesAdapter(Context mcontext,
                                           List<DealsModel.Data.Product.Productphotos> productphotos1) {
        context = mcontext;
        productphotos =productphotos1;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler_images_offers, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        Glide.with(context).load(productphotos.get(position).getPhoto()).into(holder.Image);
    }

    @Override
    public int getItemCount() {
        return productphotos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        private ImageView Image;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            Image=mView.findViewById(R.id.item_image);
        }
    }
}