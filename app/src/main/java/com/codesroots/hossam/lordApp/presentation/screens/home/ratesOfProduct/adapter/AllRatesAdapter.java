package com.codesroots.hossam.lordApp.presentation.screens.home.ratesOfProduct.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codesroots.hossam.lordApp.R;
import com.codesroots.hossam.lordApp.entities.RatessOfProductModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AllRatesAdapter extends RecyclerView.Adapter<AllRatesAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    List<RatessOfProductModel.DataBean.ProductratesBean> productRates = new ArrayList<>();
    List<RatessOfProductModel.DataBean> AllproductRates;
    private Context context;


    public AllRatesAdapter(Context context , List<RatessOfProductModel.DataBean> arrayList)
    {
        this.AllproductRates = arrayList;
        this.context =  context;

        for (int i=0;i<AllproductRates.size();i++)
            productRates.addAll(AllproductRates.get(i).getProductrates());
    }

    @Override
    public AllRatesAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_comments_adapter_item, parent, false);
        return new AllRatesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AllRatesAdapter.ViewHolder holder, final int position) {


        Glide.with(context).load(productRates.get(position).getUser().getPhoto()).into(holder.person_img);
        holder.name.setText(productRates.get(position).getUser().getUsername());
        holder.comment.setText(productRates.get(position).getComment());
        holder.ratingBar.setRating(productRates.get(position).getRate());
        holder.time.setText(getdate(productRates.get(position).getCreated()));
    }


    @Override
    public int getItemCount() {
        return productRates.size();
    }

    private String  getdate(String date)
    {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ");
        try {
            Date dateObj= sdf.parse(date);
            Log.d("newdatein",dateObj.getTime()+"");
            String timestamp = String.valueOf(dateObj.getTime());//  //Example -> in ms
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String dateString = formatter.format(new Date(Long.parseLong(timestamp)));
            return dateString;
        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        TextView name,comment,time;
        RatingBar ratingBar;
        ImageView person_img;
        public ViewHolder(View v) {
            super(v);
            mView=v;

            name =mView.findViewById(R.id.notification_text);
            comment =mView.findViewById(R.id.comment);
            time =mView.findViewById(R.id.time);
            ratingBar =mView.findViewById(R.id.rates);
            person_img =mView.findViewById(R.id.person_img);
        }


    }

}
