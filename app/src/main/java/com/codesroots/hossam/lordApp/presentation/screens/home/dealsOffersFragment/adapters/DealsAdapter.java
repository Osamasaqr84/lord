package com.codesroots.hossam.lordApp.presentation.screens.home.dealsOffersFragment.adapters;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.codesroots.hossam.lordApp.entities.DealsModel;
import com.codesroots.hossam.lordApp.R;

public class DealsAdapter extends RecyclerView.Adapter<DealsAdapter.Deal> {

    private LayoutInflater layoutInflater;
    private Context context;
    DealsModel dealsData;
    RecyclerImagesAdapter recycle_images_adapter;

    public DealsAdapter(Context context, DealsModel data) {
        this.context = context;
        dealsData = data;
    }

    @NonNull
    @Override
    public Deal onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycler_deals, parent, false);

        return new Deal(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Deal holder, int position) {

        DealsModel.Data.Product currentProduct = dealsData.getData().get(position).getProduct();

        if (currentProduct.getProductphotos() != null) {
            recycle_images_adapter = new RecyclerImagesAdapter(context, currentProduct.getProductphotos());
            holder.recyclerViewImages.setAdapter(recycle_images_adapter);
        }

        holder.name.setText(currentProduct.getName());

        String percentage = dealsData.getData().get(position).getPercentage();
        double doublePrice = Double.parseDouble(currentProduct.getPrice());
        double priceAfterDiscount = doublePrice - (doublePrice * (Double.parseDouble(percentage) / 100));
        holder.tvDescription.setText("خصم " + percentage + "% عند شراء هذه المجموعة");
        holder.tvStoreName.setText(currentProduct.getSmallstore().getName());
        holder.tvNewPrice.setText(priceAfterDiscount + " ريال ");
        holder.tvOldPrice.setText(currentProduct.getPrice() + " ريال ");
        if (currentProduct.getTotal_rating().size() > 0) {
            holder.tvRate.setText("(" + currentProduct.getTotal_rating().get(0).getCount() + ")");
            holder.ratingBar.setRating(currentProduct.getTotal_rating().get(0).getStars());
        } else {
            holder.tvRate.setText("(0)");
            holder.ratingBar.setRating(0);
        }

        holder.tvPercentage.setText(percentage + "%");


   /*     holder.myOrdersBinding.ordercard.setOnClickListener(v ->
        {
            Fragment fragment = new ProductsInsideOrderFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("allProduct", (Serializable) offersData.get(position).getOrderdetails());
            fragment.setArguments(bundle);
            ((FragmentActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, fragment).addToBackStack(null).commit();

        } );*/
    }


    @Override
    public int getItemCount() {
        return dealsData.getData().size();
    }

    class Deal extends RecyclerView.ViewHolder {

        RecyclerView recyclerViewImages;
        TextView name, tvOldPrice, tvNewPrice, tvRate,
                tvDescription, tvStoreName, tvPercentage;
        RatingBar ratingBar;


        public Deal(View v) {
            super(v);

            Typeface tf = Typeface.createFromAsset(v.getContext().getAssets(), "fonts/Chalkduster.ttf");
            recyclerViewImages = v.findViewById(R.id.recyclerImages);
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

        }


    }

}
