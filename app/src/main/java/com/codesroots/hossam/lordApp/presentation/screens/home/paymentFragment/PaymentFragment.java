package com.codesroots.hossam.lordApp.presentation.screens.home.paymentFragment;

import android.app.AlertDialog;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.codesroots.hossam.lordApp.helper.PreferenceHelper;
import com.codesroots.hossam.lordApp.presentation.screens.home.HomeActivity;
import com.codesroots.hossam.lordApp.presentation.screens.home.finishMakeOrderFragment.FinishOrderFragment;
import com.codesroots.hossam.lordApp.presentation.screens.home.storesFragment.AllStoresViewModelFactory;
import com.codesroots.hossam.lordApp.presentation.screens.home.userCartFragment.helper.ProductInfoToPost;
import com.codesroots.hossam.lordApp.presentation.screens.home.userCartFragment.helper.ProductModel;
import com.codesroots.hossam.lordApp.R;
import com.codesroots.hossam.lordApp.presentation.screens.home.categoryFragment.MainFragment;
import com.codesroots.hossam.lordApp.presentation.screens.home.makeOrderFromGoogleStoresFeragment.ImagePass;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class PaymentFragment extends Fragment {


    public PaymentFragment() {
        // Required empty public constructor
    }

    List<ProductInfoToPost> productList;
    ArrayList<ProductModel> ProductModels = new ArrayList<>();
    PaymentViewModel paymentViewModel;
    int paymentway, userid;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.payment_fragment, container, false);
        userid = PreferenceHelper.getUserId();
        ((HomeActivity) Objects.requireNonNull(getActivity())).title.setText(getText(R.string.paymenpage));
        paymentViewModel = ViewModelProviders.of(this, getViewModelFactory()).get(PaymentViewModel.class);
        notification();
        assert getArguments() != null;
        productList = getArguments().getParcelableArrayList("products");

        for (int i = 0; i < productList.size(); i++) {
            Log.i("forin", "showDialog: hossam");
            ProductModels.add(new ProductModel(userid, getArguments().getInt("storid"), productList.get(i).getProductId(),
                    String.valueOf(productList.get(i).getProductCount()),
                    getArguments().getString("USER_ADDRESS"), "jk",
                    getArguments().getString("USER_LAT"), getArguments().getString("USER_LANG"), paymentway,
                    "saied is here", getArguments().getString("price"), 0
            ));
        }

        paymentViewModel.saveData(ProductModels);
        paymentViewModel.saveResultLiveData.observe(this, aBoolean ->
                {
                    if (aBoolean) {
                        FragmentManager fm = getFragmentManager();
                        for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                            fm.popBackStack();
                        }
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new MainFragment()).addToBackStack(null).commit();
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new FinishOrderFragment()).addToBackStack(null).commit();
                    }
                    else
                        Snackbar.make(view, getText(R.string.erroroccur), Snackbar.LENGTH_LONG).show();
                }
        );
        return view;
    }

    @NonNull
    private ViewModelProvider.Factory getViewModelFactory() {
        return new AllStoresViewModelFactory(getActivity().getApplication());
    }


    ////////////////////Notification
    private void notification() {
        OneSignal.startInit(getActivity())
                .setNotificationOpenedHandler(new ExampleNotificationOpenedHandler())
                .init();
        OneSignal.enableSound(true);

        OneSignal.sendTag("id", String.valueOf(PreferenceHelper.getUserId()));
        OneSignal.sendTag("user_group_id", String.valueOf(PreferenceHelper.getUSER_GROUP_ID()));
        OneSignal.sendTag("store_id","0");
    }

    private class ExampleNotificationOpenedHandler implements OneSignal.NotificationOpenedHandler {


        @Override
        public void notificationOpened(OSNotificationOpenResult result) {

        }
    }

}
