package com.codesroots.hossam.lordApp.presentation.screens.home.ratesOfProduct;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.codesroots.hossam.lordApp.R;
import com.codesroots.hossam.lordApp.presentation.screens.home.ratesOfProduct.adapter.AllRatesAdapter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class RatesOfProductFragment extends Fragment {


    @SerializedName("records")
    @Expose
    private RecyclerView recyclerView;
    private ProductRatesViewModel productRatesViewModel;
    int type,productid;
    AllRatesAdapter allRatesAdapter;
    FrameLayout progress;

    public RatesOfProductFragment() {
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

        View view = inflater.inflate(R.layout.product_rates, container, false);
        recyclerView = view.findViewById(R.id.recylerview);
        progress = view.findViewById(R.id.progress);

    //    ((HomeActivity)Objects.requireNonNull(getActivity())).title.setText(getText(R.string.comments));

        // type=getArguments().getInt("type");
        productid=getArguments().getInt("product_id");
        type =getArguments().getInt("type");  // get type is from stor or product


        productRatesViewModel = ViewModelProviders.of(this, getViewModelFactory()).get(ProductRatesViewModel.class);


        productRatesViewModel.productratesMutableLiveData.observe(getActivity(),ratessOfProductModel -> {


            allRatesAdapter = new AllRatesAdapter(getActivity(),ratessOfProductModel.getData());
           recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
            recyclerView.setAdapter(allRatesAdapter);

        });

        productRatesViewModel.loading.observe(this, loading ->
                progress.setVisibility(loading ? View.VISIBLE : View.GONE));


        productRatesViewModel.errorLiveData.observe(this, new Observer<Throwable>() {
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
        return new ProductRatesViewModelFactory(getActivity().getApplication(),type,productid);
    }

}
