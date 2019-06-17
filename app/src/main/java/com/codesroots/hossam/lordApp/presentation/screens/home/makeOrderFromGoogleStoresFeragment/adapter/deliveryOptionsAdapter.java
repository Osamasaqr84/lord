package com.codesroots.hossam.lordApp.presentation.screens.home.makeOrderFromGoogleStoresFeragment.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;


import com.codesroots.hossam.lordApp.R;

import java.util.ArrayList;


public class deliveryOptionsAdapter extends RecyclerView.Adapter<deliveryOptionsAdapter.ViewHolder> {

    private ArrayList<String> ads_list,message_items_lists;
    private Context context;


    public deliveryOptionsAdapter(Context context, ArrayList<String> chalet_list_result) {
        this.context = context;
        this.ads_list = chalet_list_result;
        message_items_lists = new ArrayList<>();
        this.message_items_lists.addAll(ads_list);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.spinner_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.name.setText(ads_list.get(position));

    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }

    @Override
    public int getItemCount() {
        return ads_list.size();

    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
         RadioButton name;

        private ViewHolder(View itemView) {
            super(itemView);
       //     name = itemView.findViewById(R.id.city_name);
         }

    }



}
