package com.codesroots.hossam.lordApp.presentation.screens.home.rateFragment;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.codesroots.hossam.lordApp.helper.PreferenceHelper;
import com.codesroots.hossam.lordApp.presentation.screens.home.HomeActivity;
import com.codesroots.hossam.lordApp.R;

import java.util.Objects;


public class RateFragment extends Fragment {


    public RateFragment() {
        // Required empty public constructor
    }


    ProductOrDelivery productOrDelivery;
    RatingViewModel ratingViewModel;
    FrameLayout progress;
    TextView sendRate, ordernum, name;
    ImageView imageView;
    EditText comment;
    RatingBar ratingBar;

    PreferenceHelper preferenceHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.product_rate, container, false);
        progress = view.findViewById(R.id.progress);
        sendRate = view.findViewById(R.id.ratesend);
        imageView = view.findViewById(R.id.item_rateimg);
        comment = view.findViewById(R.id.ratecomment);
        ratingBar = view.findViewById(R.id.ratebare);
        ordernum = view.findViewById(R.id.rate_ordernum);
        name = view.findViewById(R.id.rate_productname);

        preferenceHelper = new PreferenceHelper(getActivity());

        int product_or_delivery = getArguments().getInt("product_or_delivery");

        if (product_or_delivery == 0)
            ((HomeActivity) Objects.requireNonNull(getActivity())).title.setText(getText(R.string.prductrate));
        else
            ((HomeActivity) Objects.requireNonNull(getActivity())).title.setText(getText(R.string.deliveryrate));


        productOrDelivery = getArguments().getParcelable("item");
        ratingViewModel = ViewModelProviders.of(this, getViewModelFactory()).get(RatingViewModel.class);

        name.setText(productOrDelivery.getName());
        Glide.with(getActivity()).load(productOrDelivery.getImagepath()).into(imageView);


        ratingViewModel.addRatingResult.observe(this, aBoolean ->
                {
                    if (aBoolean) {
                        Toast.makeText(getActivity(), getText(R.string.addratesuccess), Toast.LENGTH_SHORT).show();
                        getActivity().getSupportFragmentManager().popBackStack();
                    }
                    else
                        Toast.makeText(getActivity(), getText(R.string.erroroccur), Toast.LENGTH_SHORT).show();
                }
        );

        ratingViewModel.errorLiveData.observe(this, throwable ->
                {
                    Toast.makeText(getActivity(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                }
        );


        ratingViewModel.loading.observe(this, loading ->
                progress.setVisibility(loading ? View.VISIBLE : View.GONE)
        );


        sendRate.setOnClickListener(v -> {
            if (product_or_delivery == 0)
                ratingViewModel.AddRatingToProduct(preferenceHelper.getUserId(), productOrDelivery.getId(), comment.getText().toString(), ratingBar.getRating());
            else
                ratingViewModel.AddRatingToDelivery(preferenceHelper.getUserId(), productOrDelivery.getId(), comment.getText().toString(), ratingBar.getRating());

            progress.setVisibility(View.VISIBLE);
        });
        return view;
    }


    @NonNull
    private ViewModelProvider.Factory getViewModelFactory() {
        return new RatingViewModelFactory(getActivity().getApplication());
    }

}
