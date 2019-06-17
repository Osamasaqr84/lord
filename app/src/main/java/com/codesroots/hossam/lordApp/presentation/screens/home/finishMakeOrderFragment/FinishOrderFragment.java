package com.codesroots.hossam.lordApp.presentation.screens.home.finishMakeOrderFragment;

import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codesroots.hossam.lordApp.presentation.screens.home.categoryFragment.MainFragment;
import com.codesroots.hossam.lordApp.presentation.screens.home.storesFragment.AllStoresViewModelFactory;
import com.codesroots.hossam.lordApp.R;
import com.codesroots.hossam.lordApp.presentation.screens.home.HomeActivity;
import com.codesroots.hossam.lordApp.presentation.screens.home.myOrderFragment.MYOrderFragment;

import java.util.Objects;


public class FinishOrderFragment extends Fragment {



    public FinishOrderFragment() {
        // Required empty public constructor
    }

    TextView gotodelivery;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.first_finish_order, container, false);
        ((HomeActivity)Objects.requireNonNull(getActivity())).title.setText(getText(R.string.finish_order));

        gotodelivery = view.findViewById(R.id.gotodelivery);
        gotodelivery.setOnClickListener(v ->
                {
       FragmentManager fm =  getActivity().getSupportFragmentManager();
        for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
   getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new MainFragment()).addToBackStack(null).commit();
   getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new MYOrderFragment()).addToBackStack(null).commit();


    } );
        return view;
    }
    @NonNull
    private ViewModelProvider.Factory getViewModelFactory() {
        return new AllStoresViewModelFactory(getActivity().getApplication());
    }

}
