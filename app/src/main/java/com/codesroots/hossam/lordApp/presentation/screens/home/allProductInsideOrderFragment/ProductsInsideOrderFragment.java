package com.codesroots.hossam.lordApp.presentation.screens.home.allProductInsideOrderFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codesroots.hossam.lordApp.R;
import com.codesroots.hossam.lordApp.entities.MYOrdersModel;
import com.codesroots.hossam.lordApp.presentation.screens.home.allProductInsideOrderFragment.adapter.ProductsInsideOrderAdapter;

import java.util.List;


public class ProductsInsideOrderFragment extends Fragment {



    private RecyclerView recyclerView;
    private ProductsInsideOrderAdapter productsInsideOrderAdapter;
    List<MYOrdersModel.DataBean.OrderdetailsBean> orderdetailsBeans;
    public ProductsInsideOrderFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_product_inside_orders, container, false);
        recyclerView = view.findViewById(R.id.recylerview);

        assert getArguments() != null;
        orderdetailsBeans = (List<MYOrdersModel.DataBean.OrderdetailsBean>) getArguments().getSerializable("allProduct");
        productsInsideOrderAdapter = new ProductsInsideOrderAdapter(getActivity(),orderdetailsBeans);
        recyclerView.setAdapter(productsInsideOrderAdapter);
        return view;
    }

}
