package com.codesroots.hossam.lordApp.presentation.screens.home.categoryFragment;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.codesroots.hossam.lordApp.helper.MyApplication;
import com.codesroots.hossam.lordApp.helper.NetworkChangeReceiver;
import com.codesroots.hossam.lordApp.presentation.screens.home.HomeActivity;
import com.codesroots.hossam.lordApp.presentation.screens.home.categoryFragment.adapters.DepartmentAdapter;
import com.codesroots.hossam.lordApp.presentation.screens.home.categoryFragment.adapters.MoreRateAdapter;
import com.codesroots.hossam.lordApp.presentation.screens.home.categoryFragment.adapters.OffersAdapter;
import com.codesroots.hossam.lordApp.presentation.screens.home.categoryFragment.adapters.SliderPagerAdapter;
import com.codesroots.hossam.lordApp.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;


public class MainFragment extends Fragment  implements NetworkChangeReceiver.ConnectivityReceiverListener{

    @SerializedName("records")
    @Expose
    private RecyclerView departrecyclerView, moreraterecyclerView, offersrecylerview;
    private DepartmentAdapter departmentAdapter;
    private MoreRateAdapter moreRateAdapter;
    private OffersAdapter offersAdapter;
    ViewPager viewPager;
    private SliderPagerAdapter sliderPagerAdapter;
    CirclePageIndicator indicator;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private CategoryViewModel categoryViewModel;
    private ShimmerFrameLayout mShimmerViewContainer,mShimmerViewContainer2,mShimmerViewContainer3,mShimmerViewContainer4;

    public MainFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        findFromXml(view);
        setupviewPager(viewPager);
        ((HomeActivity) Objects.requireNonNull(getActivity())).title.setText(getText(R.string.homepage));

        categoryViewModel = ViewModelProviders.of(this, getViewModelFactory()).get(CategoryViewModel.class);

        categoryViewModel.categoriesLiveData.observe(this, categories -> {
            departmentAdapter = new DepartmentAdapter(getActivity(), categories.getData(), categories.getSlider());
            sliderPagerAdapter = new SliderPagerAdapter(getActivity(), categories.getSlider());
            viewPager.setAdapter(sliderPagerAdapter);
            init(categories.getSlider().size());
            viewPager.setAdapter(sliderPagerAdapter);
            mShimmerViewContainer.stopShimmerAnimation();
            mShimmerViewContainer.setVisibility(View.GONE);
            mShimmerViewContainer4.stopShimmerAnimation();
            mShimmerViewContainer4.setVisibility(View.GONE);
            viewPager.setClipToPadding(false);
            indicator.setViewPager(viewPager);
            departrecyclerView.setAdapter(departmentAdapter);
        });


        categoryViewModel.BestOffersLiveData.observe(this, bestOffers ->
                {
                    offersAdapter = new OffersAdapter(getActivity(), bestOffers.getData());
                    offersrecylerview.setAdapter(offersAdapter);
                    mShimmerViewContainer2.stopShimmerAnimation();
                    mShimmerViewContainer2.setVisibility(View.GONE);
                }
        );

        categoryViewModel.MoreRateLiveData.observe(this, moreRate ->
                {
                    moreRateAdapter = new MoreRateAdapter(getActivity(), moreRate.getData());
                    moreraterecyclerView.setAdapter(moreRateAdapter);
                    mShimmerViewContainer3.stopShimmerAnimation();
                    mShimmerViewContainer3.setVisibility(View.GONE);
                }
        );

        categoryViewModel.errorBestOffersLiveData.observe(this,throwable ->
                showSnackBar(view, throwable.getMessage()));

        categoryViewModel.errorMoreRateLiveData.observe(this,throwable ->
                showSnackBar(view, throwable.getMessage()));

        categoryViewModel.errorLiveData.observe(this, throwable ->
                showSnackBar(view, throwable.getMessage()));

        boolean b=  isOnline(getContext());
        return view;
    }

    public boolean isOnline(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        //should check null because in airplane mode it will be null
        return (netInfo != null && netInfo.isConnected());
    }


    private void setupviewPager(ViewPager viewPager) {
        viewPager.setPageTransformer(true, (view, position) -> {

            view.setTranslationX(-position * view.getWidth());
            if (Math.abs(position) < 0.5) {
                view.setVisibility(View.VISIBLE);
                view.setScaleX(1 - Math.abs(position));
                view.setScaleY(1 - Math.abs(position));
            } else if (Math.abs(position) > 0.5) {
                view.setVisibility(View.GONE);
            }
        });
    }


    private void showSnackBar(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }


    private void init(int size) {
        final float density = getResources().getDisplayMetrics().density;
        //Set circle indicator radius
        indicator.setRadius(4 * density);
        NUM_PAGES = size;
        final Handler handler = new Handler();
        final Runnable Update = () -> {
            if (currentPage == NUM_PAGES) {
                currentPage = 0;
            }
            viewPager.setCurrentItem(currentPage++, true);
        };

        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 4000, 5000);


        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });
    }


    private void findFromXml(View view) {
        departrecyclerView = view.findViewById(R.id.depatrecylerview);
        moreraterecyclerView = view.findViewById(R.id.morraterecylerview);
        offersrecylerview = view.findViewById(R.id.offersrecylerview);
        viewPager = view.findViewById(R.id.vp_slider);
        indicator = view.findViewById(R.id.indicator);
        mShimmerViewContainer = view.findViewById(R.id.shimmer_view_container);
        mShimmerViewContainer2 = view.findViewById(R.id.shimmer_view_container2);
        mShimmerViewContainer3 = view.findViewById(R.id.shimmer_view_container3);
        mShimmerViewContainer4 = view.findViewById(R.id.shimmer_view_container4);
    }

    @NonNull
    private ViewModelProvider.Factory getViewModelFactory() {
        return new CategoryViewModelFactory(getActivity().getApplication());
    }

    @Override
    public void onResume() {
        super.onResume();
        mShimmerViewContainer.startShimmerAnimation();
        mShimmerViewContainer2.startShimmerAnimation();
        mShimmerViewContainer3.startShimmerAnimation();
        mShimmerViewContainer4.startShimmerAnimation();
        MyApplication.getInstance().setConnectivityListener(this);
    }

    @Override
    public void onPause() {
        mShimmerViewContainer.stopShimmerAnimation();
        mShimmerViewContainer2.stopShimmerAnimation();
        mShimmerViewContainer3.stopShimmerAnimation();
        mShimmerViewContainer4.stopShimmerAnimation();
        super.onPause();
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if (isConnected)
            categoryViewModel.loadData();
            //Toast.makeText(getActivity(),isConnected+"",Toast.LENGTH_SHORT).show();
    }
}
