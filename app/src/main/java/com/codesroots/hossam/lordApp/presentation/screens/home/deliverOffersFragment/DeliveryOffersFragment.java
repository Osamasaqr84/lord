package com.codesroots.hossam.lordApp.presentation.screens.home.deliverOffersFragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.codesroots.hossam.lordApp.helper.PreferenceHelper;
import com.codesroots.hossam.lordApp.R;
import com.codesroots.hossam.lordApp.presentation.screens.home.HomeActivity;
import com.codesroots.hossam.lordApp.presentation.screens.home.deliverOffersFragment.adapter.DeliveryOffersAdapter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;


public class DeliveryOffersFragment extends Fragment {


    @SerializedName("records")
    @Expose
    private RecyclerView recyclerView;
    private DeliveryOffersViewModel deliveryOffersViewModel;
    private FrameLayout progress;
    PreferenceHelper preferenceHelper;
    int orderid;
    DeliveryOffersAdapter deliveryOffersAdapter;

    public DeliveryOffersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_deliveryoffers_list, container, false);
        recyclerView = view.findViewById(R.id.recylerview);
        progress = view.findViewById(R.id.progress);

        preferenceHelper = new PreferenceHelper(getActivity());
        ((HomeActivity)Objects.requireNonNull(getActivity())).title.setText(getText(R.string.deliveryoffers));

        orderid=getArguments().getInt("orderid");

        //orderid =1;
        deliveryOffersViewModel = ViewModelProviders.of(this,getViewModelFactory()).get(DeliveryOffersViewModel.class);

        deliveryOffersViewModel.loading.observe(this, loading ->
                progress.setVisibility(loading ? View.VISIBLE : View.GONE));


        deliveryOffersViewModel.Deliveryoffers.observe(getActivity(),storeslistData ->
            {
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                deliveryOffersAdapter = new DeliveryOffersAdapter(getActivity(), storeslistData,deliveryOffersViewModel,orderid);
                recyclerView.setAdapter(deliveryOffersAdapter);
             }
        );

        deliveryOffersViewModel.errorLiveData.observe(this, new Observer<Throwable>() {
                    @Override
                    public void onChanged(@Nullable Throwable throwable) {
                        // todo show errorى
                        assert throwable != null;
                        Toast.makeText(getActivity(),getResources().getString(R.string.erroroccur)+
                                throwable.getCause().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
        );

        return view;
    }


    @NonNull
    private ViewModelProvider.Factory getViewModelFactory() {
        return new DeliveryOffersViewModelFactory(getActivity().getApplication(),1);
    }

}
