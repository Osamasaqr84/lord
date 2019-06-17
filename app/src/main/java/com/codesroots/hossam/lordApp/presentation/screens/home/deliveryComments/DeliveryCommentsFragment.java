package com.codesroots.hossam.lordApp.presentation.screens.home.deliveryComments;

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
import android.widget.Toast;

import com.codesroots.hossam.lordApp.presentation.screens.home.HomeActivity;
import com.codesroots.hossam.lordApp.presentation.screens.home.deliveryComments.adapter.CommentsAdapter;
import com.codesroots.hossam.lordApp.R;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;


public class DeliveryCommentsFragment extends Fragment {


    @SerializedName("records")
    @Expose
    private RecyclerView recyclerView;
    private DeliveryCommentsViewModel deliveryCommentsViewModel;
    int type, deliverID;
    CommentsAdapter commentsAdapter;

    public DeliveryCommentsFragment() {
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

        ((HomeActivity) Objects.requireNonNull(getActivity())).title.setText(getText(R.string.comments));


        deliverID = getArguments().getInt("delivery_id");
        deliverID = 1;
        deliveryCommentsViewModel = ViewModelProviders.of(this, getViewModelFactory()).get(DeliveryCommentsViewModel.class);


        deliveryCommentsViewModel.deliveryCommentsMutableLiveData.observe(getActivity(), deliveryComments -> {
            commentsAdapter = new CommentsAdapter(getActivity(), deliveryComments.getData());
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
            recyclerView.setAdapter(commentsAdapter);

        });

        deliveryCommentsViewModel.errorLiveData.observe(this, new Observer<Throwable>() {
                    @Override
                    public void onChanged(@Nullable Throwable throwable) {
                        // todo show errorى
                        assert throwable != null;
                        Toast.makeText(getActivity(), getResources().getString(R.string.erroroccur) +
                                throwable.getCause().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );

        return view;
    }

    @NonNull
    private ViewModelProvider.Factory getViewModelFactory() {
        return new DeliveryComentsViewModelFactory(getActivity().getApplication(), deliverID);
    }

}
