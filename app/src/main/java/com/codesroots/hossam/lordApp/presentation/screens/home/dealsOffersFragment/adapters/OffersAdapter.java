package com.codesroots.hossam.lordApp.presentation.screens.home.dealsOffersFragment.adapters;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codesroots.hossam.lordApp.dataLayer.localDatabase.userCart.entities.Product;
import com.codesroots.hossam.lordApp.entities.OffersModel;
import com.codesroots.hossam.lordApp.R;
import com.codesroots.hossam.lordApp.presentation.screens.home.userCartFragment.UserCartFragment;

import java.io.Serializable;

public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.Offers> {

    private LayoutInflater layoutInflater;
    private Context context;
    OffersModel offersData;

    public OffersAdapter(Context context, OffersModel data) {
        this.context = context;
        offersData = data;
    }

    @NonNull
    @Override
    public Offers onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler_offers, parent, false);
        return new Offers(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Offers holder, int position) {

        OffersModel.DataBean.ProductBean currentProduct = offersData.getData().get(position).getProduct();

        holder.name.setText(currentProduct.getName());

        holder.tvDescription.setText("خصم " + offersData.getData().get(position).getPercentage() + " عند شراء " + currentProduct.getAmount() + " قطع من هذا المنتج");
        holder.tvStoreName.setText(currentProduct.getSmallstore().getName());
        holder.tvNewPrice.setText(currentProduct.getLast_price() + " ريال ");
        holder.tvOldPrice.setText(currentProduct.getPrice() + " ريال ");
        if (currentProduct.getTotal_rating().size() > 0) {
            holder.tvRate.setText("(" + currentProduct.getTotal_rating().get(0).getCount() + ")");
            holder.ratingBar.setRating(currentProduct.getTotal_rating().get(0).getStars());
        } else {
            holder.tvRate.setText("(0)");
            holder.ratingBar.setRating(0);
        }

        Glide.with(context).load(currentProduct.getProductphotos().get(0).getPhoto()).into(holder.ivProduct);
        holder.tvPercentage.setText(offersData.getData().get(position).getPercentage());


       holder.itemView.setOnClickListener(v ->
        {

                    Product product = new Product();
                    product.setProduct_id( offersData.getData().get(position).getProduct().getId());
                    product.setName( offersData.getData().get(position).getProduct().getName());
                    product.setStor_id( offersData.getData().get(position).getProduct().getSmallstore_id());
                    product.setPhoto(offersData.getData().get(position).getProduct().getProductphotos().get(0).getPhoto());
                    product.setPrice(offersData.getData().get(position).getProduct().getLast_price());
                    product.setRateCount(offersData.getData().get(position).getProduct().getAmount());
                    product.setRateStars(offersData.getData().get(position).getProduct().getProductrates().get(0).getRate());
                    product.setProduct_count(offersData.getData().get(position).getProduct().getAmount());

            Fragment userCartFragment = new UserCartFragment();
            Bundle args = new Bundle();
            args.putInt("store_id", offersData.getData().get(position).getProduct().getSmallstore_id());
            args.putInt("product_id", offersData.getData().get(position).getProduct().getId());
            args.putInt("count", offersData.getData().get(position).getProduct().getAmount());
            args.putFloat("total_price", offersData.getData().get(position).getProduct().getAmount()*
                                                        Float.valueOf(offersData.getData().get(position).getProduct().getLast_price()));
            args.putSerializable("product",product);
            userCartFragment.setArguments(args);
            ((FragmentActivity) context).getSupportFragmentManager().beginTransaction().
                    replace(R.id.main_frame, userCartFragment).addToBackStack(null).commit();


        } );
    }


    @Override
    public int getItemCount() {
        return offersData.getData().size();
    }

    class Offers extends RecyclerView.ViewHolder {


        TextView name, tvOldPrice, tvNewPrice, tvRate,
                tvDescription, tvStoreName, tvPercentage;
        RatingBar ratingBar;
        ImageView ivProduct;

        public Offers(View v) {
            super(v);
            Typeface tf = Typeface.createFromAsset(v.getContext().getAssets(), "fonts/Chalkduster.ttf");
            name = v.findViewById(R.id.item_name);
            tvStoreName = v.findViewById(R.id.tvStoreName);
            tvPercentage = v.findViewById(R.id.tvPercentage);
            tvPercentage.setTypeface(tf);
            tvDescription = v.findViewById(R.id.tvDescription);
            tvOldPrice = v.findViewById(R.id.tv_old_price);
            tvOldPrice.setPaintFlags(tvOldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            tvNewPrice = v.findViewById(R.id.tv_new);
            tvRate = v.findViewById(R.id.tvRate);
            ratingBar = v.findViewById(R.id.rates);
            ivProduct = v.findViewById(R.id.ivProduct);
        }
    }
}

