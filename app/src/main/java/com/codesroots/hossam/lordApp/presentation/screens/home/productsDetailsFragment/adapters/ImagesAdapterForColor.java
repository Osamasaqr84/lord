package com.codesroots.hossam.lordApp.presentation.screens.home.productsDetailsFragment.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.codesroots.hossam.lordApp.R;
import com.codesroots.hossam.lordApp.entities.ProductDetailsModel;

import java.util.List;



public class ImagesAdapterForColor extends RecyclerView.Adapter<ImagesAdapterForColor.ViewHolder>  {

    private Context context;
    private int pos;
    public int mSelectedItem = -1;
    List<ProductDetailsModel.DataBean.ProductcolorsBean> colors;
    public ImagesAdapterForColor(Context context, List<ProductDetailsModel.DataBean.ProductcolorsBean> productcolors) {
        this.context = context;
        colors=productcolors;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_color_item, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        GradientDrawable drawable = (GradientDrawable)holder.color.getBackground();
        drawable.setColor(Color.parseColor(colors.get(position).getColor_hex()));

        if (position == mSelectedItem)
            holder.linearLayout.setBackground(ContextCompat.getDrawable(context, R.drawable.linear_background_choice_color));
        else
            holder.linearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
    }


    @Override
    public int getItemCount() {
        return colors.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        public TextView color;
        LinearLayout linearLayout;

        public ViewHolder(View view) {

            super(view);
            mView = view;
            color =  itemView.findViewById(R.id.textcolor);
            linearLayout =  itemView.findViewById(R.id.colorborder);

            View.OnClickListener clickListener  = v -> {
                mSelectedItem = getAdapterPosition();
                notifyDataSetChanged();
            };

            itemView.setOnClickListener(clickListener);
            linearLayout.setOnClickListener(clickListener);
        }
    }

}

