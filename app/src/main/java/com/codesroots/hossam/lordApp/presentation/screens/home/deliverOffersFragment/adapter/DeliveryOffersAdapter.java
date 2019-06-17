package com.codesroots.hossam.lordApp.presentation.screens.home.deliverOffersFragment.adapter;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codesroots.hossam.lordApp.entities.DeliveryOffers;
import com.codesroots.hossam.lordApp.helper.PreferenceHelper;
import com.codesroots.hossam.lordApp.presentation.screens.home.deliverOffersFragment.DeliveryOffersViewModel;
import com.codesroots.hossam.lordApp.R;
import com.codesroots.hossam.lordApp.presentation.screens.home.categoryFragment.MainFragment;
import com.codesroots.hossam.lordApp.presentation.screens.home.deliveryComments.DeliveryCommentsFragment;
import com.codesroots.hossam.lordApp.presentation.screens.home.myOrderFragment.MYOrderFragment;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class DeliveryOffersAdapter extends RecyclerView.Adapter<DeliveryOffersAdapter.CustomView> {


    private FragmentActivity context;
    private List<DeliveryOffers.DataBean> allOffers;
    PreferenceHelper preferenceHelper;
    DeliveryOffersViewModel deliveryOffersViewModel;
    int orderid;
    public DeliveryOffersAdapter(FragmentActivity activity,
                                 List<DeliveryOffers.DataBean> allOffersData,
                                 DeliveryOffersViewModel deliveryOffersViewModel1,int orderID) {

        this.context = activity;
        allOffers = allOffersData;
        preferenceHelper = new PreferenceHelper(context);
        deliveryOffersViewModel = deliveryOffersViewModel1;
        orderid = orderID;
    }


    @Override
    public DeliveryOffersAdapter.CustomView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.deliveryoffers_adapter_item, parent, false);

        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();

        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int height = displayMetrics.heightPixels;
        //////set percentage from all screen
        layoutParams.height = (int) (height * 0.35);
        view.setLayoutParams(layoutParams);

        return new DeliveryOffersAdapter.CustomView(view);

    }


    @Override
    public void onBindViewHolder(@NonNull CustomView holder, final int position) {

        holder.capitainname.setText(allOffers.get(position).getOrder().getDelivry().getName());
        //(allOffers.get(position).getOrder().getDelivry().getName());
        holder.price.setText(allOffers.get(position).getPrice() + " " + context.getText(R.string.costunit));
        Glide.with(context)
                .load(allOffers.get(position).getOrder().getDelivry().getPhoto())
                .into(holder.itemImage);

        holder.distance.setText(getDistance(preferenceHelper.getCURRENTLAT(), preferenceHelper.getCURRENTLONG(),
                allOffers.get(position).getOrder().getDelivry().getDelivery_lat(),
                allOffers.get(position).getOrder().getDelivry().getDelivery_long()) + " " + context.getText(R.string.unit));

        holder.ratecount.setText("(" + allOffers.get(position).getOrder().getDelivry().getDeliveryrates().size() + ")");


        holder.deliverycomment.setOnClickListener(v -> {
            Fragment fragment = new DeliveryCommentsFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("delivery_id",  allOffers.get(position).getId());
            fragment.setArguments(bundle);
            (context).getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, fragment).addToBackStack(null).commit();

        });

        holder.accept.setOnClickListener(v -> {

            deliveryOffersViewModel.editOrder(orderid,
                    createPartFromString(String.valueOf(allOffers.get(position).getOrder().getDelivry_id())),
                    createPartFromString("1"),
                    createPartFromString(allOffers.get(position).getPrice())

            );

            deliveryOffersViewModel.editOrderResponse.observe(context,responseResult ->
            {
                if (responseResult)
                {
                    FragmentManager fm = context.getSupportFragmentManager();
                    for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                        fm.popBackStack();
                    }

                    context.getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new MainFragment()).addToBackStack(null).commit();
                    context.getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new MYOrderFragment()).addToBackStack(null).commit();

                }

            }
        );

        });
    }


    @NonNull
    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                MultipartBody.FORM, descriptionString);
    }

    @Override
    public int getItemCount() {
        return allOffers.size();
    }


    class CustomView extends RecyclerView.ViewHolder {

        private final View mView;
        private ImageView itemImage;
        private TextView capitainname, distance, price, ratecount,deliverycomment,accept;

        private CustomView(View view) {
            super(view);
            mView = view;
            itemImage = mView.findViewById(R.id.deliver_img);
            capitainname = mView.findViewById(R.id.capitainnamevalue);
            distance = mView.findViewById(R.id.home_end_address);
            price = mView.findViewById(R.id.costvalue);
            ratecount = mView.findViewById(R.id.ratecount);
            deliverycomment = mView.findViewById(R.id.deliverycomments);
            accept = mView.findViewById(R.id.accept);
            SpannableString ss = new SpannableString(context.getText(R.string.deliverycomments));
            ClickableSpan clickableSpan = new ClickableSpan() {
                @Override
                public void onClick( View widget) {

                }
            };

            ss.setSpan(clickableSpan, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            deliverycomment.setText(ss);
            deliverycomment.setMovementMethod(LinkMovementMethod.getInstance());
            deliverycomment.setHighlightColor(context.getResources().getColor(R.color.gray));
            deliverycomment.setHintTextColor(context.getResources().getColor(R.color.gray));
        }
    }


    private String getDistance(String latA, String lngA, String latB, String lngB) {

        Location locationA = new Location("point A");
        locationA.setLatitude(Double.parseDouble(latA));
        locationA.setLongitude(Double.parseDouble(lngA));
        Location locationB = new Location("point B");
        locationB.setLatitude(Double.parseDouble(latB));
        locationB.setLongitude(Double.parseDouble(lngB));
        float distance = locationA.distanceTo(locationB) / 1000;

        return String.valueOf(distance);
    }




}
