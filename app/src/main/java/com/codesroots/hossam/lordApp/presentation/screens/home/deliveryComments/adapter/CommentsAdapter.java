package com.codesroots.hossam.lordApp.presentation.screens.home.deliveryComments.adapter;

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
import com.codesroots.hossam.lordApp.entities.DeliveryComments;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    List<DeliveryComments.DataBean> allComments;
    private Context context;


    public CommentsAdapter(Context context , List<DeliveryComments.DataBean> allCommentsData)
    {
        this.allComments = allCommentsData;
        this.context =  context;
    }

    @Override
    public CommentsAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_comments_adapter_item, parent, false);
        return new CommentsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CommentsAdapter.ViewHolder holder, final int position) {


        Glide.with(context).load(allComments.get(0).getDeliveryrates().get(position).getUser().getPhoto()).into(holder.person_img);
        holder.name.setText(allComments.get(0).getDeliveryrates().get(position).getUser().getUsername());
        holder.comment.setText(allComments.get(0).getDeliveryrates().get(position).getComments());
        holder.ratingBar.setRating(allComments.get(0).getDeliveryrates().get(position).getRate());
        holder.time.setText(getdate(allComments.get(0).getDeliveryrates().get(position).getCreated()));
    }


    @Override
    public int getItemCount() {
        return allComments.get(0).getDeliveryrates().size();
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
