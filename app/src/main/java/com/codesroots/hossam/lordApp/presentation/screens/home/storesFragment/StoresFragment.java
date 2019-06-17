package com.codesroots.hossam.lordApp.presentation.screens.home.storesFragment;

import android.app.AlertDialog;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.codesroots.hossam.lordApp.entities.StoresList;
import com.codesroots.hossam.lordApp.helper.GpsTracker;
import com.codesroots.hossam.lordApp.helper.PreferenceHelper;
import com.codesroots.hossam.lordApp.presentation.screens.home.HomeActivity;
import com.codesroots.hossam.lordApp.presentation.screens.home.storesFragment.adapter.AllStoriesAdapter;
import com.codesroots.hossam.lordApp.R;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class StoresFragment extends Fragment {


    @SerializedName("records")
    @Expose
    private RecyclerView recyclerView;
    private StoresViewModel stores_viewModel;
    List<StoresList.DataBean> allStories=new ArrayList<>();
    MutableLiveData<List<StoresList.DataBean>> allStoriesLiveData=new MutableLiveData<List<StoresList.DataBean>>();
    private FrameLayout progress;
    AllStoriesAdapter storesAdapter;
    int type,cate_or_sub_ID;
    PreferenceHelper preferenceHelper;
    GpsTracker gpsTracker;
    String location,categrytype;
    public StoresFragment() {
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

        View view = inflater.inflate(R.layout.fragment_first_resturant_list, container, false);
        recyclerView = view.findViewById(R.id.recylerview);
        progress = view.findViewById(R.id.progress);
        ((HomeActivity)Objects.requireNonNull(getActivity())).title.setText(getText(R.string.stores));

        preferenceHelper = new PreferenceHelper(getActivity());

        gpsTracker = new GpsTracker(getActivity());
        categrytype = preferenceHelper.getCURRENTCATEGRY();


         assert getArguments() != null;
         type=getArguments().getInt("type");
         cate_or_sub_ID=getArguments().getInt("categryId");
        stores_viewModel = ViewModelProviders.of(this,getViewModelFactory()).get(StoresViewModel.class);


        stores_viewModel.loading.observe(this, loading ->
                progress.setVisibility(loading ? View.VISIBLE : View.GONE));


        stores_viewModel.allStoriesLiveData.observe(getActivity(),storeslistData ->
            {
                storesAdapter = new AllStoriesAdapter(getActivity(),storeslistData);
                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
                recyclerView.setAdapter(storesAdapter);
             }
        );

        stores_viewModel.errorLiveData.observe(this, throwable -> {
            // todo show errorى
         if (throwable!=null)
            Toast.makeText(getActivity(),getResources().getString(R.string.erroroccur)+
                    throwable.getCause().getMessage(),Toast.LENGTH_SHORT).show();
        }
        );

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 111) {

            if(gpsTracker.canGetLocation())
            {
                double latitude  =  gpsTracker.getLatitude();
                double longitude =  gpsTracker.getLongitude();
                preferenceHelper.setCURRENTLAT(String.valueOf(latitude));
                preferenceHelper.setCURRENTLONG(String.valueOf(longitude));
                location= preferenceHelper.getCURRENTLAT()+","+preferenceHelper.getCURRENTLONG();
                stores_viewModel.getGoogleResults(location);
            }
        }
    }

    private void showAlertDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Warning");
        builder.setMessage("Device GPS is currently OFF. Please Turn ON TO get more results.");
        builder.setNegativeButton("not now", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivityForResult(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), 111);
            }
        }).show();

    }


    @NonNull
    private ViewModelProvider.Factory getViewModelFactory() {
        return new AllStoresViewModelFactory(getActivity().getApplication(),type,cate_or_sub_ID,location,categrytype);
    }

}
