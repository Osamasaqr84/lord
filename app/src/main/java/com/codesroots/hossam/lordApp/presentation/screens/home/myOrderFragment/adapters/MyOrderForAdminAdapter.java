package com.codesroots.hossam.lordApp.presentation.screens.home.myOrderFragment.adapters;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.codesroots.hossam.lordApp.R;
import com.codesroots.hossam.lordApp.databinding.MyOrdersForAdminBinding;
import com.codesroots.hossam.lordApp.entities.MYOrdersModel;
import com.codesroots.hossam.lordApp.presentation.screens.home.allProductInsideOrderFragment.ProductsInsideOrderFragment;
import com.codesroots.hossam.lordApp.presentation.screens.home.myOrderFragment.MyOrderViewModel;
import com.codesroots.hossam.lordApp.presentation.screens.home.productsFragment.ProductsFragment;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MyOrderForAdminAdapter extends RecyclerView.Adapter<MyOrderForAdminAdapter.CustomView> {

    private LayoutInflater layoutInflater;
    private Context context;
    List<MYOrdersModel.DataBean> orderData;
    MyOrderViewModel MyOrderViewModel1;

    public MyOrderForAdminAdapter(FragmentActivity context, List<MYOrdersModel.DataBean> data, MyOrderViewModel myOrderViewModel) {
        this.context = context;
        orderData = data;
        MyOrderViewModel1 = myOrderViewModel;
    }

    @NonNull
    @Override
    public CustomView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        MyOrdersForAdminBinding MyOrdersForAdminBinding;
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

        MyOrdersForAdminBinding = DataBindingUtil.inflate(layoutInflater, R.layout.myorder_adapter_item_for_admin, parent, false);

        return new CustomView(MyOrdersForAdminBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomView holder, int position) {

        MyOrderViewModel myOrderViewModel = new MyOrderViewModel();
        try {

            if (orderData.get(position).getOrder_status().matches("0"))
                holder.MyOrdersForAdminBinding.accept.setVisibility(View.VISIBLE);

            if (orderData.get(position).getPhoto() != null)
                myOrderViewModel.setImagePath("http://parashot.codesroots.com/library/orderphoto/" + orderData.get(position).getPhoto());
            else
                myOrderViewModel.setImagePath(orderData.get(position).getStore_icon());

            myOrderViewModel.setName(orderData.get(position).getStorename());
            myOrderViewModel.setStorenamevalue(orderData.get(position).getStorename());


            if (orderData.get(position).getRate() > 0) {
                myOrderViewModel.setRatestart(orderData.get(position).getRate());
            } else {
                if (orderData.get(position).getOrderdetails().size() > 0) {
                    myOrderViewModel.setName(orderData.get(position).getOrderdetails().get(0).getProduct().getName());
                    myOrderViewModel.setItem_price(orderData.get(position).getOrderdetails().get(0).getProduct().getPrice() + " ريال ");
                }
                try {
                    if (orderData.get(position).getOrderdetails().get(0).getProduct().getTotal_rating() != null) {
                        if (orderData.get(position).getOrderdetails().get(0).getProduct().getTotal_rating().size() > 0) {
                            myOrderViewModel.setRatecount("(" + orderData.get(position).getOrderdetails().get(0).getProduct().getTotal_rating().get(0).getCount() + ")");
                            myOrderViewModel.setRatestart(orderData.get(position).getOrderdetails().get(0).getProduct().getTotal_rating().get(0).getStars());
                        } else
                            myOrderViewModel.setRatecount("(0)");

                    } else
                        myOrderViewModel.setRatecount("(0)");

                    if (orderData.get(position).getOrderdetails().size() > 0) {
                        if (orderData.get(position).getOrderdetails().get(0).getProduct().getProductphotos() != null)
                            myOrderViewModel.setImagePath(orderData.get(position).getOrderdetails().get(0).getProduct().getProductphotos().get(0).getPhoto());
                    }
                } catch (Exception e) {
                    Log.d("fg", e.getMessage());
                }
            }

            if (orderData.get(position).getSmallstore() != null) {
                if (!orderData.get(position).getSmallstore().getName().matches(""))
                    myOrderViewModel.setStorenamevalue(orderData.get(position).getSmallstore().getName());
            }

            if (orderData.get(position).getDelivry() != null) {
                if (!orderData.get(position).getDelivry().getName().matches(""))
                    myOrderViewModel.setCapitainnamevalue(orderData.get(position).getDelivry().getName());
                else
                    myOrderViewModel.setCapitainnamevalue(context.getString(R.string.nodelivery));
            } else
                myOrderViewModel.setCapitainnamevalue(context.getString(R.string.nodelivery));

            if (orderData.get(position).getOrder_status() != null)
                myOrderViewModel.setOrderstatuesvalue(orderData.get(position).getOrder_status());

            if (orderData.get(position).getCreated() != null)
                myOrderViewModel.setDateValue(getdate(orderData.get(position).getCreated()));
        } catch (Exception e) {
        }
        holder.bind(myOrderViewModel);

        holder.MyOrdersForAdminBinding.calluser.setOnClickListener(v ->
        {
            if (orderData.get(position).getSmallstore() != null) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.fromParts("tel", orderData.get(position).getSmallstore().getPhone(), null));

                if (ActivityCompat.checkSelfPermission(context,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                context.startActivity(callIntent);
            } else
                Toast.makeText(context, "رقم الهاتف غير متوفر", Toast.LENGTH_SHORT).show();
        });


        holder.itemView.setOnClickListener(v -> {
            Fragment fragment = new ProductsInsideOrderFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("allProduct", (Serializable) orderData.get(position).getOrderdetails());
            fragment.setArguments(bundle);
            ((FragmentActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, fragment).addToBackStack(null).commit();

        });


        holder.MyOrdersForAdminBinding.accept.setOnClickListener(v ->
        {
            MyOrderViewModel1.editorder(orderData.get(position).getId(), 1);
            holder.MyOrdersForAdminBinding.accept.setVisibility(View.GONE);

        });

        holder.MyOrdersForAdminBinding.storlocation.setOnClickListener(v ->
        {
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("geo:0,0?q=" + orderData.get(position).getSmallstore().getLatitude() + "," + orderData.get(position).getSmallstore().getLongitude()));
            context.startActivity(intent);
        });

        holder.MyOrdersForAdminBinding.userlocation.setOnClickListener(v ->
        {
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("geo:0,0?q=" + orderData.get(position).getUser().getLat() + "," + orderData.get(position).getUser().getLongX()));
            context.startActivity(intent);
        });
    }

    private String getdate(String date) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ");
        try {
            Date dateObj = sdf.parse(date);
            Log.d("newdatein", dateObj.getTime() + "");
            String timestamp = String.valueOf(dateObj.getTime());//  //Example -> in ms
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String dateString = formatter.format(new Date(Long.parseLong(timestamp)));
            return dateString;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public int getItemCount() {
        return orderData.size();
    }

    class CustomView extends RecyclerView.ViewHolder {

        MyOrdersForAdminBinding MyOrdersForAdminBinding;

        public CustomView(@NonNull MyOrdersForAdminBinding ordersBinding) {
            super(ordersBinding.getRoot());
            this.MyOrdersForAdminBinding = ordersBinding;
        }

        public void bind(MyOrderViewModel myOrderViewModel) {
            this.MyOrdersForAdminBinding.setOrdermodel(myOrderViewModel);
            MyOrdersForAdminBinding.executePendingBindings();
        }

        public MyOrdersForAdminBinding getCardBinding() {
            return MyOrdersForAdminBinding;
        }
    }

}
