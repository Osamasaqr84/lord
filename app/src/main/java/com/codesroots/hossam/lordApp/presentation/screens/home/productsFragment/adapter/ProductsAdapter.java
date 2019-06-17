package com.codesroots.hossam.lordApp.presentation.screens.home.productsFragment.adapter;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.codesroots.hossam.lordApp.helper.PreferenceHelper;
import com.codesroots.hossam.lordApp.presentation.screens.home.productsFragment.ProductsFragment;
import com.codesroots.hossam.lordApp.presentation.screens.home.productsFragment.helper.AddToCart;
import com.codesroots.hossam.lordApp.R;
import com.codesroots.hossam.lordApp.dataLayer.localDatabase.userCart.entities.Product;
import com.codesroots.hossam.lordApp.databinding.ProductDataBindings;
import com.codesroots.hossam.lordApp.entities.Products_in_Stories_Model;
import com.codesroots.hossam.lordApp.presentation.screens.home.productsDetailsFragment.ProductDetailsFragment;
import com.codesroots.hossam.lordApp.presentation.screens.home.productsFragment.ProductsViewModel;
import com.codesroots.hossam.lordApp.presentation.screens.home.ratesOfProduct.RatesOfProductFragment;
import java.util.List;


public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    LayoutInflater  layoutInflater;
    Context mcontext;
    RecycleImagesAdapter recycle_images_adapter;
    List<Products_in_Stories_Model.DataBean> productData;
    ProductsViewModel productsViewModel1;
    AddToCart addToCart;
    PreferenceHelper preferenceHelper;
    boolean [] favorite;
    ProductsFragment productsFragment;
    public ProductsAdapter(FragmentActivity activity, List<Products_in_Stories_Model.DataBean> products_from_view,
                           ProductsViewModel viewModel, ProductsFragment toCart1, ProductsFragment productsFragment1) {
        mcontext=activity;
        productData = products_from_view;
        productsViewModel1 = viewModel;
        addToCart = toCart1;
        preferenceHelper = new PreferenceHelper(mcontext);
        favorite = new boolean[productData.size()];
        productsFragment=productsFragment1;
//
//        productsViewModel1 = ViewModelProviders.of(productsFragment.getActivity(), getViewModelFactory()).get(ProductsViewModel.class);
//        productsViewModel1.storProductInDBResult.observe(productsFragment.getActivity(), new Observer<Boolean>() {
//            @Override
//            public void onChanged(@Nullable Boolean aBoolean) {
//                if (aBoolean)
//                {
//                    int currentcount =Integer.parseInt(productsFragment.cart_count.getText().toString())+1;
//                    productsFragment.cart_count.setText(String.valueOf(currentcount));
//                    productsFragment.productcounttext.setText(mcontext.getText(R.string.youhave)+" "+currentcount+" "+mcontext.getText(R.string.productincart));
//
//                }
//            }
//        });

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(layoutInflater == null)
        {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

        ProductDataBindings ProductDataBindings = DataBindingUtil.inflate(layoutInflater, R.layout.product_item_adapter,parent,false);
        return new ProductsAdapter.ViewHolder(ProductDataBindings);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        recycle_images_adapter = new RecycleImagesAdapter(mcontext,productData.get(position).getProductphotos());

        Glide.with(mcontext).load(productData.get(position).getProductphotos().get(0).getPhoto()).into(holder.ProductDataBindings.itemImg);
       // holder.ProductDataBindings.itemImg.setAdapter(recycle_images_adapter);

        LinearLayout.LayoutParams params = new
                LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        DisplayMetrics displayMetrics = mcontext.getResources().getDisplayMetrics();
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        //////set percentage from all screen
        params.height = (int) (height * 0.5)-10;
        params.width = width / 2 -20;
            params.setMargins(0,0,0,10);
        holder.itemView.setLayoutParams(params);
        ProductsViewModel products_viewModel = new ProductsViewModel();
        products_viewModel.setName(productData.get(position).getName());
        products_viewModel.setPrice(productData.get(position).getPrice()+" ريال ");

        if (productData.get(position).getTotal_rating().size()>0) {
            products_viewModel.setRatestars(productData.get(position).getTotal_rating().get(0).getStars());
            products_viewModel.setRatecount("(" + productData.get(position).getTotal_rating().get(0).getCount() + ")");
        }

        else{
            products_viewModel.setRatestars(0);
            products_viewModel.setRatecount("(0)");
        }

        holder.ProductDataBindings.favorite.setOnClickListener(v -> {

            int userid = PreferenceHelper.getUserId();

            if (userid>0) {
                if (!favorite[position])
                {   products_viewModel.AddToFav(userid, productData.get(position).getId(), productData.get(position).getSmallstore_id()
                            , productsViewModel1.allProducts_repository);
                    holder.ProductDataBindings.favorite.setImageResource(R.drawable.favoried);
                    favorite[position]=true;
                }
                    ////////////// delete here
                else {
                    products_viewModel.DeleteFav(productData.get(position).getFavourite().get(0).getId()
                            , productsViewModel1.allProducts_repository);
                    holder.ProductDataBindings.favorite.setImageResource(R.drawable.favicon);
                    favorite[position]=false;
                }
            }
            else
                Snackbar.make(v,mcontext.getText(R.string.loginfirst),Snackbar.LENGTH_LONG).show();

        });

        //////////////////  inialize with all product
        if (PreferenceHelper.getUserId()>0) {
            if (productData.get(position).getFavourite().size() > 0) {
                holder.ProductDataBindings.favorite.setImageResource(R.drawable.favoried);
                favorite[position]=true;
            }
            else {
                holder.ProductDataBindings.favorite.setImageResource(R.drawable.favicon);
                favorite[position] = false;
            }
        }


        productsViewModel1.addToFavoriteLiveData.observe((FragmentActivity) mcontext, aBoolean -> {
            if (aBoolean) {
                Toast.makeText(mcontext, mcontext.getResources().getString(R.string.addtofavsucces), Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(mcontext, mcontext.getResources().getString(R.string.erroroccur),Toast.LENGTH_SHORT).show();
        });

        productsViewModel1.deleteFromFavoriteLiveData.observe((FragmentActivity) mcontext,aBoolean -> {
            if (aBoolean) {
                Toast.makeText(mcontext, mcontext.getResources().getString(R.string.deletefromfavsucces), Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(mcontext, mcontext.getResources().getString(R.string.erroroccur), Toast.LENGTH_SHORT).show();

        });

        holder.bind(products_viewModel);
        holder.ProductDataBindings.productview.setOnClickListener(v -> {
            Fragment fragment = new ProductDetailsFragment();
            Bundle bundle =new Bundle();
            bundle.putInt("store_id",productData.get(position).getSmallstore_id());
            bundle.putInt("product_id",productData.get(position).getId());
            fragment.setArguments(bundle);
       ((FragmentActivity) mcontext).getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,fragment).addToBackStack(null).commit();

        });

        holder.ProductDataBindings.rates.setOnTouchListener(new View.OnTouchListener() {
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


        holder.ProductDataBindings.addtoCart.setOnClickListener(v -> {

            Product product = new Product();
            product.setName(productData.get(position).getName());
            product.setProduct_id(productData.get(position).getId());
            if (productData.get(position).getProductphotos().size()>0)
            product.setPhoto(productData.get(position).getProductphotos().get(0).getPhoto());
            product.setStor_id(productData.get(position).getSmallstore_id());
            product.setPrice(productData.get(position).getPrice());

            if (productData.get(position).getTotal_rating().size()>0) {
                product.setRateCount(productData.get(position).getTotal_rating().get(0).getCount());
                product.setRateStars(productData.get(position).getTotal_rating().get(0).getStars());
            }

            productsViewModel1.storeData(product,productsViewModel1.allProducts_repository);

        });


    }

    @Override
    public int getItemCount() {
      return  productData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ProductDataBindings ProductDataBindings;

        public ViewHolder(ProductDataBindings bindings) {
            super(bindings.getRoot());
            this.ProductDataBindings = bindings;
        }


        public void bind(ProductsViewModel products_viewModel)
        {
            this.ProductDataBindings.setProductdata(products_viewModel);
            ProductDataBindings.executePendingBindings();
        }

        public ProductDataBindings  getCardBinding()
        {
            return  ProductDataBindings;
        }

//        }
    }

}
