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
import android.view.ViewGroup;
import android.widget.Toast;

import com.codesroots.hossam.lordApp.R;
import com.codesroots.hossam.lordApp.databinding.MyOrdersForTraderBinding;
import com.codesroots.hossam.lordApp.entities.MYOrdersModel;
import com.codesroots.hossam.lordApp.presentation.screens.home.allProductInsideOrderFragment.ProductsInsideOrderFragment;
import com.codesroots.hossam.lordApp.presentation.screens.home.myOrderFragment.MyOrderViewModel;
import com.codesroots.hossam.lordApp.presentation.screens.home.productsFragment.ProductsFragment;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MyOrderForTraderAdapter extends RecyclerView.Adapter<MyOrderForTraderAdapter.CustomView> {

    private LayoutInflater layoutInflater;
    private Context context;
    List<MYOrdersModel.DataBean> orderData;
    MyOrderViewModel myOrderViewModel;
    public MyOrderForTraderAdapter(Context context, List<MYOrdersModel.DataBean> data, MyOrderViewModel myOrderViewModel1) {
        this.context = context;
        orderData = data;
        myOrderViewModel= myOrderViewModel1;
    }

    @NonNull
    @Override
    public CustomView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        MyOrdersForTraderBinding MyOrdersForTraderBinding;
      
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

         MyOrdersForTraderBinding = DataBindingUtil.inflate(layoutInflater, R.layout.myorder_adapter_item_for_trader, parent, false);

        return new CustomView(MyOrdersForTraderBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomView holder, int position) {



            if (orderData.get(position).getPhoto() != null)
                myOrderViewModel.setImagePath("http://parashot.codesroots.com/library/orderphoto/" + orderData.get(position).getPhoto());
            else
                myOrderViewModel.setImagePath(orderData.get(position).getStore_icon());

            myOrderViewModel.setName(orderData.get(position).getStorename());
            myOrderViewModel.setStorenamevalue(orderData.get(position).getStorename());


        if (orderData.get(position).getRate()>0)
        {
            myOrderViewModel.setRatestart(orderData.get(position).getRate());
        }

        else {
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


        if (orderData.get(position).getSmallstore() != null)
        {
            if (!orderData.get(position).getSmallstore().getName().matches(""))
                myOrderViewModel.setStorenamevalue(orderData.get(position).getSmallstore().getName());
        }

        if (orderData.get(position).getDelivry() != null) {
            if (!orderData.get(position).getDelivry().getName().matches(""))
                myOrderViewModel.setCapitainnamevalue(orderData.get(position).getDelivry().getName());
            else
                myOrderViewModel.setCapitainnamevalue(context.getString(R.string.nodelivery));
        }
        else
            myOrderViewModel.setCapitainnamevalue(context.getString(R.string.nodelivery));

        if (orderData.get(position).getOrder_status()!=null)
            myOrderViewModel.setOrderstatuesvalue(orderData.get(position).getOrder_status());

        if (orderData.get(position).getCreated()!=null)
            myOrderViewModel.setDateValue(getdate(orderData.get(position).getCreated()));


        holder.bind(myOrderViewModel);

        holder.itemView.setOnClickListener(v -> {
            Fragment fragment = new ProductsFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("stor_id" ,orderData.get(position).getStore_id());
            fragment.setArguments(bundle);
            ((FragmentActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, fragment).addToBackStack(null).commit();
        });



        holder.MyOrdersForTraderBinding.userlocation.setOnClickListener(v ->
        {
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("geo:0,0?q=" + orderData.get(position).getUser().getLat() + "," +orderData.get(position).getUser().getLongX()));
            context.startActivity(intent);
        });

        holder.MyOrdersForTraderBinding.accept.setOnClickListener(v ->
        {
            myOrderViewModel.editorder(orderData.get(position).getId(),2);

        });




        holder.MyOrdersForTraderBinding.calluser.setOnClickListener(v ->
        {
            if (orderData.get(position).getSmallstore()!=null) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.fromParts("tel",orderData.get(position).getSmallstore().getPhone(),null));

                if (ActivityCompat.checkSelfPermission(context,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                context.startActivity(callIntent);
            }
            else
                Toast.makeText(context,"رقم الهاتف غير متوفر",Toast.LENGTH_SHORT).show();
        });


        holder.itemView.setOnClickListener(v -> {
            Fragment fragment = new ProductsInsideOrderFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("allProduct" , (Serializable) orderData.get(position).getOrderdetails());
            fragment.setArguments(bundle);
            ((FragmentActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, fragment).addToBackStack(null).commit();

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

        MyOrdersForTraderBinding MyOrdersForTraderBinding;

        public CustomView(@NonNull MyOrdersForTraderBinding ordersBinding) {
            super(ordersBinding.getRoot());
            this.MyOrdersForTraderBinding = ordersBinding;
        }

        public void bind(MyOrderViewModel myOrderViewModel) {
            this.MyOrdersForTraderBinding.setOrdermodel(myOrderViewModel);
            MyOrdersForTraderBinding.executePendingBindings();
        }

        public MyOrdersForTraderBinding getCardBinding() {
            return MyOrdersForTraderBinding;
        }
    }

}
