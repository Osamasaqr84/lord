package com.codesroots.hossam.lordApp.presentation.screens.home.notificationFragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.codesroots.hossam.lordApp.R;
import com.codesroots.hossam.lordApp.presentation.screens.home.HomeActivity;
import com.codesroots.hossam.lordApp.presentation.screens.home.notificationFragment.adapter.NotificationsAdapter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;


public class NotificationsFragment extends Fragment {


    @SerializedName("records")
    @Expose
    private RecyclerView recyclerView;
    private NotificationsViewModel notificationsViewModel;
    int  userid;
    NotificationsAdapter notificationsAdapter;
    private FrameLayout progress;

    public NotificationsFragment() {
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

        ((HomeActivity) Objects.requireNonNull(getActivity())).title.setText(getText(R.string.notification));
        //   ((HomeActivity)Objects.requireNonNull(getActivity())).footer.getMenu().findItem(R.id.navigation_notifications).setChecked(true);

        progress = view.findViewById(R.id.progress);
        notificationsViewModel = ViewModelProviders.of(this, getViewModelFactory()).get(NotificationsViewModel.class);

        notificationsViewModel.loading.observe(this, loading ->
                progress.setVisibility(loading ? View.VISIBLE : View.GONE));


        notificationsViewModel.notificationsMutableLiveData.observe(getActivity(), notifications -> {
            if (notifications.getData().size()>0) {
                notificationsAdapter = new NotificationsAdapter(getActivity(), notifications.getData());
                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
                recyclerView.setAdapter(notificationsAdapter);
            }
            else
                Snackbar.make(view,getText(R.string.nonotifications),Snackbar.LENGTH_SHORT).show();
        });

        notificationsViewModel.errorLiveData.observe(this, new Observer<Throwable>() {
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
        return new NotificationViewModelFactory(getActivity().getApplication(), userid);
    }

}
