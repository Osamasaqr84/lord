package com.codesroots.hossam.lordApp.presentation.screens.home.favoriteFragment;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.codesroots.hossam.lordApp.R;
import com.codesroots.hossam.lordApp.helper.PreferenceHelper;
import com.codesroots.hossam.lordApp.presentation.screens.home.HomeActivity;
import com.codesroots.hossam.lordApp.presentation.screens.home.favoriteFragment.adapter.FavProductsAdapter;

import java.util.Objects;


public class FavoriteFragment extends Fragment  {

    private RecyclerView recyclerView;
    private FavProductsAdapter favProductsAdapter; ////productsAdapter adapter
    private FavoritsViewModel products_viewModel;
    TextView gotocart,cart_count,productcounttext;
    int userid;
    PreferenceHelper preferenceHelper;
    private FrameLayout progress;
    public FavoriteFragment() {
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
        View view = inflater.inflate(R.layout.fragment_fav_product, container, false);
        recyclerView = view.findViewById(R.id.recylerview);
        progress = view.findViewById(R.id.progress);

         preferenceHelper =  new PreferenceHelper(getActivity());

        ((HomeActivity)Objects.requireNonNull(getActivity())).title.setText(getText(R.string.favproducts));

        userid = preferenceHelper.getUserId();
        //userid = 113;
        products_viewModel = ViewModelProviders.of(this, getViewModelFactory()).get(FavoritsViewModel.class);


        products_viewModel.loading.observe(this, loading ->
                progress.setVisibility(loading ? View.VISIBLE : View.GONE));

        products_viewModel.products_MutableLiveData.observe(this, products -> {
            ///////// send viewmodel here to can save in DB from adapter
            favProductsAdapter = new FavProductsAdapter(getActivity(),products.getData(),products_viewModel);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(favProductsAdapter);

            if (products.getData().size()==0)
                Snackbar.make(view,getText(R.string.nofavfound),Snackbar.LENGTH_LONG).show();
        });

        return view;
    }

    @NonNull
    private ViewModelProvider.Factory getViewModelFactory() {
        return new FavProductsViewModelFactory(getActivity().getApplication(), userid);
    }

}
