package com.codesroots.hossam.lordApp.presentation.screens.home.userCartFragment;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.codesroots.hossam.lordApp.helper.PreferenceHelper;
import com.codesroots.hossam.lordApp.R;
import com.codesroots.hossam.lordApp.dataLayer.localDatabase.userCart.entities.Product;
import com.codesroots.hossam.lordApp.presentation.screens.getUserLocation.GetUserLocationActivity;
import com.codesroots.hossam.lordApp.presentation.screens.home.HomeActivity;
import com.codesroots.hossam.lordApp.presentation.screens.home.paymentFragment.PaymentFragment;
import com.codesroots.hossam.lordApp.presentation.screens.home.storesFragment.AllStoresViewModelFactory;
import com.codesroots.hossam.lordApp.presentation.screens.home.userCartFragment.Adapter.FirstCartAdapter;
import com.codesroots.hossam.lordApp.presentation.screens.home.userCartFragment.helper.CartPrice;
import com.codesroots.hossam.lordApp.presentation.screens.home.userCartFragment.helper.ProductInfoToPost;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import static android.app.Activity.RESULT_OK;
import static com.codesroots.hossam.lordApp.presentation.screens.getUserLocation.GetUserLocationActivity.FULL_ADDRESS;
import static com.codesroots.hossam.lordApp.presentation.screens.getUserLocation.GetUserLocationActivity.USER_LANG;
import static com.codesroots.hossam.lordApp.presentation.screens.getUserLocation.GetUserLocationActivity.USER_LAT;


public class UserCartFragment extends Fragment implements CartPrice {


    public static final int REQUEST_CODE_LOCATION = 1024;
    @SerializedName("records")
    @Expose
    private RecyclerView recyclerView;
    private UserCartViewModel userCartViewModel;
    FirstCartAdapter firstCartAdapter;
    TextView productCount, totalprice, sale;
    float total = 0;
    List<Product> productList;
    List<ProductInfoToPost> products = new ArrayList<>();
    int stor_id;
    PreferenceHelper preferenceHelper;

    public UserCartFragment() {
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
        View view = inflater.inflate(R.layout.fragment_main_product_cart, container, false);
        ((HomeActivity) Objects.requireNonNull(getActivity())).title.setText(getText(R.string.cart));

        recyclerView = view.findViewById(R.id.recylerview);
        productCount = view.findViewById(R.id.proCount);
        totalprice = view.findViewById(R.id.totalprice);
        sale = view.findViewById(R.id.sale);
        preferenceHelper = new PreferenceHelper(getActivity());
        assert getArguments() != null;
        stor_id = getArguments().getInt("stor_id");

        userCartViewModel = ViewModelProviders.of(this, getViewModelFactory()).get(UserCartViewModel.class);

        if (getArguments() != null && getArguments().containsKey("count")) {

            float totalPrice = getArguments().getFloat("total_price");
            stor_id = getArguments().getInt("store_id");
            totalprice.setText(String.valueOf(totalPrice) + "ريال ");
            total = totalPrice;
            productList = new ArrayList<>();
            productList.add((Product) getArguments().getSerializable("product"));
            productCount.setText(getArguments().getInt("count") + "");


            firstCartAdapter = new FirstCartAdapter(getActivity(), productList, userCartViewModel, this);
            recyclerView.setAdapter(firstCartAdapter);

        } else {
            userCartViewModel.ProductLiveData.observe(this, products ->
                    {
                        productList = products;
                        productCount.setText(products.size() + "");
                        Log.d("fd", products.size() + "");
                        firstCartAdapter = new FirstCartAdapter(getActivity(), products, userCartViewModel, this);
                        recyclerView.setAdapter(firstCartAdapter);
                        total = 0;
                        NumberFormat nf = NumberFormat.getInstance(Locale.US);

                        for (int i = 0; i < products.size(); i++) {
                            try {
                                total += ((nf.parse(products.get(i).getPrice()).floatValue()) * firstCartAdapter.productList.get(i).getProduct_count());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                       totalprice.setText(String.valueOf(total)+ "ريال ");
                    }
            );
        }


        sale.setOnClickListener(v ->
                {
                    int current_count = Integer.parseInt(productCount.getText().toString());
                    if (current_count > 0) {

                        if (PreferenceHelper.getUserId()>0) {
                            for (int i = 0; i < productList.size(); i++) {
                                products.add(new ProductInfoToPost(productList.get(i).getProduct_id(), firstCartAdapter.productList.get(i).getProduct_count()));
                            }
                            startActivityForResult(new Intent(getActivity(), GetUserLocationActivity.class), REQUEST_CODE_LOCATION);
                        }

                        else
                            Snackbar.make(v,getText(R.string.loginfirst),Snackbar.LENGTH_LONG).show();
                    } else
                        Toast.makeText(getActivity(), getText(R.string.noitem), Toast.LENGTH_SHORT).show();
                }
        );

        return view;
    }


    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        if (reqCode == REQUEST_CODE_LOCATION) {
            if (resultCode == RESULT_OK) {

                Fragment fragment = new PaymentFragment();
                        Bundle bundle = new Bundle();
                        bundle.putParcelableArrayList("products", (ArrayList<? extends Parcelable>) products);
                        bundle.putBoolean("fromgoogle",false);
                        bundle.putInt("storid",stor_id);
                        bundle.putString("price", String.valueOf(total));
                        bundle.putString("USER_ADDRESS",data.getExtras().getString(FULL_ADDRESS));
                        bundle.putString("USER_LAT",data.getExtras().getString(USER_LAT));
                        bundle.putString("USER_LANG",data.getExtras().getString(USER_LANG));
                        fragment.setArguments(bundle);
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,fragment).addToBackStack(null).commit();

            } else {
                Toast.makeText(getActivity(), "You haven't selected address", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void setTextView(String text) {
        totalprice.setText(text);
    }


    @NonNull
    private ViewModelProvider.Factory getViewModelFactory() {
        return new AllStoresViewModelFactory(getActivity().getApplication(), stor_id);
    }

    @Override
    public void plusItem(int position) {
        total += Double.parseDouble(productList.get(position).getPrice());
        totalprice.setText(total + "ريال ");
    }

    @Override
    public void minusItem(int position) {
        total -= Double.parseDouble(productList.get(position).getPrice());
        totalprice.setText(total + "ريال ");
    }

    @Override
    public void deleteItem(int position, int count) {

        int current_count;
        NumberFormat nf = NumberFormat.getInstance(Locale.US);
        if (productList.get(0).getId() == 0) {
            Log.d("deleteItem: ", "0 ");
            total = 0;
            current_count = 0;
        } else {
            try {
                total -= ((nf.parse(productList.get(position).getPrice()).floatValue()) * count);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            current_count = Integer.parseInt(productCount.getText().toString()) - 1;
        }

        if (total >= 0.0 )
        totalprice.setText(total + " ريال ");
        productCount.setText(current_count + "");
    }
}
