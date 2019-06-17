package com.codesroots.hossam.lordApp.presentation.screens.home.categoryFragment.adapters;

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
import android.widget.RatingBar;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.codesroots.hossam.lordApp.entities.MoreRate;
import com.codesroots.hossam.lordApp.R;
import com.codesroots.hossam.lordApp.presentation.screens.home.productsDetailsFragment.ProductDetailsFragment;
import com.codesroots.hossam.lordApp.presentation.screens.home.rateFragment.RateFragment;

import java.util.List;

public class MoreRateAdapter extends RecyclerView.Adapter<MoreRateAdapter.ViewHolder> {


    private LayoutInflater layoutInflater;
    private Context context;
    List<MoreRate.DataBean> data;

    public MoreRateAdapter(Context context, List<MoreRate.DataBean> data1)
    {
        this.context =  context;
        data = data1;
    }

    @NonNull
    @Override
    public MoreRateAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_more_rate_item, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int width = displayMetrics.widthPixels;
        //////set percentage from all screen
        layoutParams.width = (width / 3) +20 ;
        layoutParams.height = (width / 3) + 30;
        view.setLayoutParams(layoutParams);

        return new MoreRateAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MoreRateAdapter.ViewHolder holder, final int position) {

        Glide.with(context)
                .load(data.get(position).getProductphotos().get(0).getPhoto())
                .into(holder.item_img);

        holder.name.setText(data.get(position).getName());
        holder.ratecount.setText("("+data.get(position).getCount()+")");
        holder.ratingBar.setRating(data.get(position).getSum());
        holder.storename.setText(data.get(position).getSmallstore().getName());

        holder.mView.setOnClickListener(v -> {

            Fragment fragment = new ProductDetailsFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("store_id",data.get(position).getSmallstore().getId());
            bundle.putInt("product_id",data.get(position).getId());
            fragment.setArguments(bundle);
            ((FragmentActivity) context).getSupportFragmentManager().beginTransaction().
                    replace(R.id.main_frame, fragment).addToBackStack(null).commit();

        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        private ImageView item_img;
        TextView storename,name,item_dept,ratecount;
        RatingBar ratingBar;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            item_img =view.findViewById(R.id.item_img);
            storename =view.findViewById(R.id.item_dept);
            name =view.findViewById(R.id.item_name);
            ratecount =view.findViewById(R.id.ratecount);
            ratingBar =view.findViewById(R.id.rates);
        }
    }

}
