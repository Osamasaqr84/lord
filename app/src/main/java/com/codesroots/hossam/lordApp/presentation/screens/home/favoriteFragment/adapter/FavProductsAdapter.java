package com.codesroots.hossam.lordApp.presentation.screens.home.favoriteFragment.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.codesroots.hossam.lordApp.presentation.screens.home.favoriteFragment.FavoritsViewModel;
import com.codesroots.hossam.lordApp.R;
import com.codesroots.hossam.lordApp.entities.FavProduct;
import com.codesroots.hossam.lordApp.presentation.screens.home.productsDetailsFragment.ProductDetailsFragment;
import com.codesroots.hossam.lordApp.presentation.screens.home.ratesOfProduct.RatesOfProductFragment;
import java.util.List;


public class FavProductsAdapter extends RecyclerView.Adapter<FavProductsAdapter.CustomView> {

    Context mcontext;
    RecycleImagesAdapter recycle_images_adapter;
    List<FavProduct.DataBean> productData;
    FavoritsViewModel favorits_viewModel;

    public FavProductsAdapter(FragmentActivity activity, List<FavProduct.DataBean> data,  FavoritsViewModel favoritsViewModel) {
        mcontext=activity;
        productData = data;
        favorits_viewModel = favoritsViewModel;
    }

    @Override
    public FavProductsAdapter.CustomView onCreateViewHolder(
            ViewGroup parent, int viewType) {

        View view =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fav_product_item_adapter, parent, false);

        return new FavProductsAdapter.CustomView(view);
    }

    @Override
    public void onBindViewHolder(final FavProductsAdapter.CustomView holder, final int position) {

        recycle_images_adapter = new RecycleImagesAdapter(mcontext,productData.get(position).getProduct().getProductphotos());
        holder.productImages.setLayoutManager(new GridLayoutManager(new FragmentActivity(),3));
        holder.productImages.setAdapter(recycle_images_adapter);

       holder.item_name.setText(productData.get(position).getProduct().getName());
        holder.item_price.setText(
                productData.get(position).getProduct().getPrice()+" ريال ");

        if (productData.get(position).getProduct().getTotal_rating().size()>0) {
           holder.ratingBar.setRating(productData.get(position).getProduct().getTotal_rating().get(0).getStars());
            holder.rating_count.setText("(" + productData.get(position).getProduct().getTotal_rating().get(0).getCount() + ")");
        }

        else{
            holder.ratingBar.setRating(0);
            holder.rating_count.setText("(0)");
        }


        holder.favorit.setOnClickListener(v -> {
                favorits_viewModel.DeleteFav(productData.get(position).getId(), favorits_viewModel.favoriteProductsRepository);

                favorits_viewModel.deleteFromFavoriteLiveData.observe((FragmentActivity) mcontext, aBoolean ->
                        {
                            if (aBoolean) {
                                Toast.makeText(mcontext, mcontext.getText(R.string.deletefromfavsucces), Toast.LENGTH_SHORT).show();
                                if (productData.size()>0)
                                productData.remove(position);
                                notifyDataSetChanged();
                            }
                            else
                                Toast.makeText(mcontext, mcontext.getText(R.string.erroroccur), Toast.LENGTH_SHORT).show();

                        }
                );
        });



        holder.mView.setOnClickListener(v -> {
            Fragment fragment = new ProductDetailsFragment();
            Bundle bundle =new Bundle();
            bundle.putInt("store_id", productData.get(position).getSmallstore_id());
            bundle.putInt("product_id",productData.get(position).getId());
            fragment.setArguments(bundle);
       ((FragmentActivity) mcontext).getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,fragment).addToBackStack(null).commit();
        });

        holder.ratingBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Fragment fragment = new RatesOfProductFragment();
                Bundle bundle =new Bundle();
                bundle.putInt("product_id",productData.get(position).getId());
                bundle.putInt("type",0); //////////send type 1 to return rate of that product  only
                fragment.setArguments(bundle);
                ((FragmentActivity) mcontext).getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,fragment).addToBackStack(null).commit();
                return false;
            }
        });


    }

    @Override
    public int getItemCount() {
      return  productData.size();
    }


    class CustomView extends RecyclerView.ViewHolder {
        private final View mView;
        private ImageView favorit;
        private TextView item_name,item_price,rating_count;
        RatingBar ratingBar;
        RecyclerView productImages;

        private CustomView(View view) {
            super(view);
            mView = view;
            favorit =mView.findViewById(R.id.favorite);
            ratingBar=mView.findViewById(R.id.rates);
            productImages=mView.findViewById(R.id.viewpager);
            item_name=mView.findViewById(R.id.item_name);
            item_price=mView.findViewById(R.id.item_price);
            rating_count=mView.findViewById(R.id.rate_count);

        }
    }
}
