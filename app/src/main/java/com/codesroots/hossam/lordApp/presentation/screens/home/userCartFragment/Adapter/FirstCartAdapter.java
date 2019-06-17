package com.codesroots.hossam.lordApp.presentation.screens.home.userCartFragment.Adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codesroots.hossam.lordApp.presentation.screens.home.userCartFragment.UserCartViewModel;
import com.codesroots.hossam.lordApp.presentation.screens.home.userCartFragment.helper.CartPrice;
import com.codesroots.hossam.lordApp.R;
import com.codesroots.hossam.lordApp.dataLayer.localDatabase.userCart.entities.Product;
import com.codesroots.hossam.lordApp.databinding.UserCartBinding;

import java.util.List;

public class FirstCartAdapter extends RecyclerView.Adapter<FirstCartAdapter.CustomView> {

    private LayoutInflater layoutInflater;
    public List<Product> productList;
    private Context context;
    UserCartViewModel userCartViewModel1;
    CartPrice cartPrice;

    public FirstCartAdapter(Context context, List<Product> productList, UserCartViewModel viewModel, CartPrice c) {
        this.productList = productList;
        this.context = context;
        this.userCartViewModel1 = viewModel;
        this.cartPrice = c;
    }


    @NonNull
    @Override
    public CustomView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

        UserCartBinding productCartBinding = DataBindingUtil.inflate
                (layoutInflater, R.layout.first_product_cart_adapter_item, parent, false);

        return new CustomView(productCartBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomView holder, int position) {
        holder.bind(productList.get(position));

        if (productList.get(position).getId()==0){
            holder.userCartBinding.quintityPlus.setEnabled(false);
            holder.userCartBinding.quintityMinus.setEnabled(false);
            holder.userCartBinding.quintityValue.setText(String.valueOf(productList.get(position).getProduct_count()));
            holder.userCartBinding.quintityValue.setEnabled(false);
        }

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class CustomView extends RecyclerView.ViewHolder {

        UserCartBinding userCartBinding;
        private UserCartViewModel userCartViewModel;
        private Product product;

        public CustomView(UserCartBinding cartBinding) {
            super(cartBinding.getRoot());
            this.userCartBinding = cartBinding;
            userCartViewModel = new UserCartViewModel();


            userCartBinding.deleteItem.setOnClickListener(v -> {
                int position = getAdapterPosition();

                cartPrice.deleteItem(position, Integer.parseInt(userCartBinding.quintityValue.getText().toString()));
                if (productList.get(position).getId()!=0){
                    userCartViewModel.deleteProductFromDB(productList.get(position),userCartViewModel1.userCartRepository);
                }

                productList.remove(position);
                notifyItemRemoved(position);

            });

            userCartBinding.quintityPlus.setOnClickListener(v ->
                    {
                        int position = getAdapterPosition();
                        userCartBinding.quintityValue.setText((Integer.parseInt(userCartBinding.quintityValue.getText().toString()) + 1) + "");
                        cartPrice.plusItem(position);
                        userCartViewModel.setQuentity(Integer.parseInt(userCartBinding.quintityValue.getText().toString()) + 1);
                        product.setProduct_count(product.getProduct_count() + 1);
                        userCartBinding.quintityValue.setError(null);
                    }
            );

            userCartBinding.quintityMinus.setOnClickListener((View.OnClickListener) v ->
            {
                if (Integer.parseInt(userCartBinding.quintityValue.getText().toString()) > 1) {
                    int position = getAdapterPosition();
                    userCartBinding.quintityValue.setText((Integer.parseInt(userCartBinding.quintityValue.getText().toString()) - 1) + "");
                    userCartViewModel.setQuentity(Integer.parseInt(userCartBinding.quintityValue.getText().toString()) - 1);
                    cartPrice.minusItem(position);
                    product.setProduct_count(product.getProduct_count() - 1);
                    userCartBinding.quintityValue.setError(null);

                } else
                    userCartBinding.quintityValue.setError("لا يمكن ان تكون الكمية اقل من واحد");

            });
        }

        public void bind(Product product) {
            this.product = product;
            userCartViewModel.setName(product.getName());
            userCartViewModel.setImagepath(product.getPhoto());
            userCartViewModel.setPrice(product.getPrice() + "ريال ");
            userCartViewModel.setRetecount("(" + product.getRateCount() + ")");
            userCartViewModel.setRateStart(product.getRateStars());
            userCartViewModel.setQuentity(product.getProduct_count());
            this.userCartBinding.setCartmodel(userCartViewModel);
            userCartBinding.executePendingBindings();

        }

        public UserCartBinding getCardBinding() {
            return userCartBinding;
        }

    }

}
