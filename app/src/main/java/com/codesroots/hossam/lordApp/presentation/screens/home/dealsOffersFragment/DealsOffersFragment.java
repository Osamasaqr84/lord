package com.codesroots.hossam.lordApp.presentation.screens.home.dealsOffersFragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.chip.Chip;
import android.support.design.chip.ChipGroup;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.codesroots.hossam.lordApp.entities.DealsModel;
import com.codesroots.hossam.lordApp.entities.OffersModel;
import com.codesroots.hossam.lordApp.presentation.screens.home.userCartFragment.UserCartFragment;
import com.codesroots.hossam.lordApp.R;

import com.codesroots.hossam.lordApp.presentation.screens.home.HomeActivity;
import com.codesroots.hossam.lordApp.presentation.screens.home.dealsOffersFragment.adapters.DealsAdapter;
import com.codesroots.hossam.lordApp.presentation.screens.home.dealsOffersFragment.adapters.OffersAdapter;
import com.codesroots.hossam.lordApp.presentation.screens.home.dealsOffersFragment.adapters.RecyclerItemClickListener;
import com.codesroots.hossam.lordApp.presentation.screens.home.productsDetailsFragment.ProductDetailsFragment;
import com.codesroots.hossam.lordApp.presentation.screens.home.storesFragment.AllStoresViewModelFactory;
import com.codesroots.hossam.lordApp.dataLayer.localDatabase.userCart.entities.Product;

import java.util.Objects;

public class DealsOffersFragment extends Fragment {

    DealsOffersViewModel dealsOffersViewModel;
    private RecyclerView recyclerView;
    OffersAdapter offersAdapter;
    DealsAdapter dealsAdapter;
    OffersModel currentOffers;
    DealsModel currentDeals;
    FrameLayout progress;
    int lastchecked = 0;

    public DealsOffersFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_offers_deals, container, false);
        ((HomeActivity) Objects.requireNonNull(getActivity())).title.setText(getText(R.string.fragment_offers));
       // ((HomeActivity)Objects.requireNonNull(getActivity())).footer.getMenu().findItem(R.id.navigation_deals).setChecked(true);

        dealsOffersViewModel = ViewModelProviders.of(this, getViewModelFactory()).get(DealsOffersViewModel.class);

        progress = view.findViewById(R.id.progress);
        recyclerView = view.findViewById(R.id.recylerview);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL));
//        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//
//                if (recyclerView.getAdapter() instanceof OffersAdapter) {
//
//                    int product_Id = currentOffers.getData().get(position).getProduct().getId();
//                    int store_Id = currentOffers.getData().get(position).getProduct().getSmallstore_id();
//                    int count = currentOffers.getData().get(position).getProduct().getAmount();
//                    Log.d("currentoffer", String.valueOf(currentOffers.getData().get(position).getProduct().getAmount()));
//                    Fragment productFragment = new ProductDetailsFragment();
//                    Bundle args = new Bundle();
//                    args.putInt("store_id", store_Id);
//                    args.putInt("product_id", product_Id);
//                    args.putInt("count", count);
//                    productFragment.setArguments(args);
//                    ((FragmentActivity) getActivity()).getSupportFragmentManager().beginTransaction().
//                            replace(R.id.main_frame, productFragment).addToBackStack(null).commit();
//
//                } else if (recyclerView.getAdapter() instanceof DealsAdapter) {
//                    DealsModel.Data currentDeal = currentDeals.getData().get(position);
//                    int product_Id = currentDeal.getProduct().getId();
//                    int store_Id = currentDeal.getProduct().getSmallstore_id();
//                    dealsOffersViewModel.getStore_id().setValue(store_Id);
//                    int count = currentDeal.getPieces();
//                    int totalPrice = Integer.parseInt(currentDeal.getProduct().getLast_price()) * count;
//
//                    Product product = new Product();
//                    product.setProduct_id(product_Id);
//                    product.setName(currentDeal.getProduct().getName());
//                    product.setStor_id(store_Id);
//                    product.setPhoto(currentDeal.getProduct().getProductphotos().get(0).getPhoto());
//                    product.setPrice(currentDeal.getProduct().getLast_price());
//                    product.setRateCount(currentDeal.getProduct().getTotal_rating().get(0).getCount());
//                    product.setRateStars(currentDeal.getProduct().getTotal_rating().get(0).getStars());
//                    product.setProduct_count(count);
//
//                    Fragment userCartFragment = new UserCartFragment();
//                    Bundle args = new Bundle();
//                    args.putInt("store_id", store_Id);
//                    args.putInt("product_id", product_Id);
//                    args.putInt("count", count);
//                    args.putInt("total_price", totalPrice);
//                    args.putSerializable("product", product);
//                    userCartFragment.setArguments(args);
//                    ((FragmentActivity) getActivity()).getSupportFragmentManager().beginTransaction().
//                            replace(R.id.main_frame, userCartFragment).addToBackStack(null).commit();
//
//                }
//            }
//
//            @Override
//            public void onLongItemClick(View view, int position) {
//            }
//        }));


        dealsOffersViewModel.dealsLD.observe(this, deals ->
                {
                    assert deals != null;
                    currentDeals = deals;
                    dealsAdapter = new DealsAdapter(getActivity(), deals);
                }
        );

        dealsOffersViewModel.offersLD.observe(this, offers ->
                {
                    assert offers != null;
                    currentOffers = offers;
                    offersAdapter = new OffersAdapter(getActivity(), offers);
                    if (recyclerView.getAdapter() == null) recyclerView.setAdapter(offersAdapter);
                }
        );

        dealsOffersViewModel.getLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean) progress.setVisibility(View.VISIBLE);
                else progress.setVisibility(View.GONE);
            }
        });


        ChipGroup chipGroup = view.findViewById(R.id.chipGroup);
        chipGroup.setOnCheckedChangeListener((chipGroup1, i) -> {
            Chip chip = chipGroup1.findViewById(i);
            if (chip != null) {
                for (int i2 = 0; i2 < chipGroup1.getChildCount(); ++i2) {
                    chipGroup1.getChildAt(i2).setClickable(true);
                }
                chip.setClickable(false);
            }

            if (i == 2131296394) {
                recyclerView.setAdapter(offersAdapter);
            } else if (i == 2131296392) {
                recyclerView.setAdapter(dealsAdapter);
                dealsOffersViewModel.getLastchecked().setValue(2131296395);
            }
        });

        return view;
    }


    @NonNull
    private ViewModelProvider.Factory getViewModelFactory() {
        return new AllStoresViewModelFactory(getActivity().getApplication());
    }


}
