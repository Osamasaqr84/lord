package com.codesroots.hossam.lordApp.presentation.screens.home.categoryFragment.adapters;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.codesroots.hossam.lordApp.entities.BestOffers;
import com.codesroots.hossam.lordApp.R;
import java.util.List;


public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.ViewHolder> {
    private Context context;
    List<BestOffers.DataBean> data;

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }


    public OffersAdapter(Context context, List<BestOffers.DataBean> data1)
    {
        this.context =  context;
        data= data1;
    }

    @NonNull
    @Override
    public OffersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_offers_item, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int width = displayMetrics.widthPixels;
        //////set percentage from all screen
        layoutParams.width = (width / 3) + 20;
        layoutParams.height = (width / 3) + 30;
        view.setLayoutParams(layoutParams);

        return new OffersAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final OffersAdapter.ViewHolder holder, final int position) {

        if (data.get(position).getProduct().getProductphotos().size()>0)
        Glide.with(context)
                .load(data.get(position).getProduct().getProductphotos().get(0).getPhoto())
                .into(holder.item_img);

        holder.name.setText(data.get(position).getProduct().getName());
        holder.oldprice.setText(data.get(position).getProduct().getLast_price()+"ريال");
        holder.newprice.setText(data.get(position).getProduct().getPrice()+"ريال");
      //  holder.storename.setText(data.get(position).getProduct().getSmallstore().getName());

        holder.mView.setOnClickListener(v -> {

//            Fragment userCartFragment = new UserCartFragment();
//            Bundle args = new Bundle();
//            args.putInt("store_id", data.get(position).getProduct().getSmallstore_id());
//            args.putInt("product_id", data.get(position).getProduct().getId());
//            args.putInt("count", data.get(position).getProduct().getAmount());
//            args.putFloat("total_price", data.get(position).getProduct().getAmount()* Float.valueOf(data.get(position).getProduct().getLast_price()));
//            args.putSerializable("product", (Serializable) data.get(position).getProduct());
//            userCartFragment.setArguments(args);
//            ((FragmentActivity) context).getSupportFragmentManager().beginTransaction().
//                    replace(R.id.main_frame, userCartFragment).addToBackStack(null).commit();


        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        private ImageView item_img;
        TextView storename,name,oldprice,newprice;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            item_img =view.findViewById(R.id.item_img);
            storename =view.findViewById(R.id.item_storename);
            name =view.findViewById(R.id.item_name);
            oldprice =view.findViewById(R.id.item_oldprice);
            newprice =view.findViewById(R.id.item_newprice);
            newprice.setPaintFlags(oldprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG) ;

        }
    }

}
