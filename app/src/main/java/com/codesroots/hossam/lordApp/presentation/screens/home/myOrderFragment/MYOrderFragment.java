package com.codesroots.hossam.lordApp.presentation.screens.home.myOrderFragment;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.codesroots.hossam.lordApp.helper.PreferenceHelper;
import com.codesroots.hossam.lordApp.presentation.screens.home.HomeActivity;
import com.codesroots.hossam.lordApp.presentation.screens.home.myOrderFragment.adapters.MyOrderAdapter;
import com.codesroots.hossam.lordApp.R;
import com.codesroots.hossam.lordApp.entities.MYOrdersModel;
import com.codesroots.hossam.lordApp.presentation.screens.home.myOrderFragment.adapters.MyOrderForAdminAdapter;
import com.codesroots.hossam.lordApp.presentation.screens.home.myOrderFragment.adapters.MyOrderForTraderAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class MYOrderFragment extends Fragment {

    MyOrderViewModel myOrderViewModel;
    private RecyclerView recyclerView;
    MyOrderAdapter myOrderAdapter;
    MyOrderForAdminAdapter myOrderForAdminAdapter;
    MyOrderForTraderAdapter orderForTraderAdapter;
    int userid,groupid;
    TextView txtnotfound;
    PreferenceHelper preferenceHelper;
    private FrameLayout progress;
    public  List<MYOrdersModel.DataBean> completOrders = new ArrayList<>(), notcompleteOrders = new ArrayList<>();
    private RadioGroup orderstatues;
     RadioButton radio1;

    public MYOrderFragment() {
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
        View view = inflater.inflate(R.layout.fragment_main_myorders, container, false);
        preferenceHelper = new PreferenceHelper(getActivity());
       ((HomeActivity)Objects.requireNonNull(getActivity())).title.setText(getText(R.string.myorder));
      //  ((HomeActivity)Objects.requireNonNull(getActivity())).footer.getMenu().findItem(R.id.navigation_order).setChecked(true);
        radio1 = view.findViewById(R.id.ordernotcommplet);
            ProgressBar progressBar;
        orderstatues = view.findViewById(R.id.orderstatues);
        userid = PreferenceHelper.getUserId();

        groupid = preferenceHelper.getUSER_GROUP_ID();
       // userid = 113;
        recyclerView = view.findViewById(R.id.recylerview);
        progress = view.findViewById(R.id.progress);
        txtnotfound = view.findViewById(R.id.txtnotfound);
     //  view.findViewById(R.id.ordernotcommplet).setch

        myOrderViewModel = ViewModelProviders.of(this, getViewModelFactory()).get(MyOrderViewModel.class);
        radio1.setChecked(true);



        myOrderViewModel.allMyOrders.observe(this, myOrdersModel ->
                {
                    assert myOrdersModel != null;
                    notcompleteOrders =myOrdersModel.notCommpleteOrderData;
                    completOrders =myOrdersModel.commpleteOrderData;

                    if (notcompleteOrders.size()>0) {
                        if (groupid == 2) {
                            myOrderAdapter = new MyOrderAdapter(getActivity(), notcompleteOrders, myOrderViewModel,this);
                            recyclerView.setAdapter(myOrderAdapter);
                        }
                        else if (groupid == 1)
                        {
                            myOrderForAdminAdapter = new MyOrderForAdminAdapter(getActivity(), notcompleteOrders, myOrderViewModel);
                            recyclerView.setAdapter(myOrderForAdminAdapter);
                        }
                        else
                        {
                            orderForTraderAdapter = new MyOrderForTraderAdapter(getActivity(), notcompleteOrders, myOrderViewModel);
                            recyclerView.setAdapter(orderForTraderAdapter);
                        }

                        recyclerView.setVisibility(View.VISIBLE);
                        txtnotfound.setVisibility(View.GONE);
                    }
                    else {
                        txtnotfound.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                    }
                });


        myOrderViewModel.loading.observe(this, loading ->
                progress.setVisibility(loading ? View.VISIBLE : View.GONE));

        myOrderViewModel.successeditOrder.observe(this,aBoolean ->
                {
                    if (aBoolean)
                        Snackbar.make(view,getText(R.string.editsuccess),Snackbar.LENGTH_LONG).show();
                    else
                        Snackbar.make(view,getText(R.string.erroroccur),Snackbar.LENGTH_LONG).show();
                }
        );

        myOrderViewModel.errorLiveData.observe(this,throwable ->
                Snackbar.make(view,getText(R.string.erroroccur)+throwable.getMessage(),Snackbar.LENGTH_LONG).show()
        );

        orderstatues.setOnCheckedChangeListener((group, checkedId) -> {

                switch (checkedId) {
                    case R.id.ordercommplet:
                        if (completOrders.size()>0) {
                            if (groupid == 2) {
                                myOrderAdapter = new MyOrderAdapter(getActivity(), completOrders,myOrderViewModel,this);
                                recyclerView.setAdapter(myOrderAdapter);
                            }
                            else if (groupid == 1)
                            {
                                myOrderForAdminAdapter = new MyOrderForAdminAdapter(getActivity(), completOrders,myOrderViewModel);
                                recyclerView.setAdapter(myOrderForAdminAdapter);
                            }
                            else
                            {
                                orderForTraderAdapter = new MyOrderForTraderAdapter(getActivity(), completOrders,myOrderViewModel);
                                recyclerView.setAdapter(orderForTraderAdapter);
                            }
                            recyclerView.setVisibility(View.VISIBLE);
                            txtnotfound.setVisibility(View.GONE);
                        }

                        else {
                            txtnotfound.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.GONE);
                        }

                        break;
                    case R.id.ordernotcommplet:
                        if (notcompleteOrders.size()>0) {

                            if (groupid == 2) {
                                myOrderAdapter = new MyOrderAdapter(getActivity(), notcompleteOrders,myOrderViewModel,this);
                                recyclerView.setAdapter(myOrderAdapter);
                            }
                            else if (groupid == 1)
                            {
                                myOrderForAdminAdapter = new MyOrderForAdminAdapter(getActivity(), notcompleteOrders,myOrderViewModel);
                                recyclerView.setAdapter(myOrderForAdminAdapter);
                            }
                            else
                            {
                                orderForTraderAdapter = new MyOrderForTraderAdapter(getActivity(), notcompleteOrders,myOrderViewModel);
                                recyclerView.setAdapter(orderForTraderAdapter);
                            }
                            recyclerView.setVisibility(View.VISIBLE);
                            txtnotfound.setVisibility(View.GONE);
                        }
                        else {
                            txtnotfound.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.GONE);
                        }
                        break;

                }
        });

        return view;
    }

    public void addtocpmmlet (MYOrdersModel.DataBean dataBean)
    {
        dataBean.setOrder_status("3");
        completOrders.add(dataBean);
    }
    @NonNull
    private ViewModelProvider.Factory getViewModelFactory() {
        return new MyOrderViewModelFactory(getActivity().getApplication(),userid,preferenceHelper.getUSER_GROUP_ID());
    }
}
