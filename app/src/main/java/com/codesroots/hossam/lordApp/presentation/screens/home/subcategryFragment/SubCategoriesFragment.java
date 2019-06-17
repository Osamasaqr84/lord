package com.codesroots.hossam.lordApp.presentation.screens.home.subcategryFragment;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codesroots.hossam.lordApp.entities.Categories;
import com.codesroots.hossam.lordApp.R;

import com.codesroots.hossam.lordApp.presentation.screens.home.HomeActivity;
import com.codesroots.hossam.lordApp.presentation.screens.home.categoryFragment.CategoryViewModel;
import com.codesroots.hossam.lordApp.presentation.screens.home.categoryFragment.CategoryViewModelFactory;
import com.codesroots.hossam.lordApp.presentation.screens.home.subcategryFragment.adapters.SliderPagerAdapter;
import com.codesroots.hossam.lordApp.presentation.screens.home.subcategryFragment.adapters.SubCategoriesAdapter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;


public class SubCategoriesFragment extends Fragment {


    @SerializedName("records")
    @Expose
    private RecyclerView recyclerView;
    private SubCategoriesAdapter customAdapter;  ////categry adapter
    ViewPager viewPager;
    private SliderPagerAdapter sliderPagerAdapter;  /// slider adapter
    private CategoryViewModel categoryViewModel;
    CirclePageIndicator indicator;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private List<Categories.SliderBean> slides;



    Categories.DataBean subcatsBean;
    public SubCategoriesFragment() {
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
        View view = inflater.inflate(R.layout.fragment_subcategry_home1, container, false);
        recyclerView = view.findViewById(R.id.recylerview);
        viewPager=view.findViewById(R.id.vp_slider);

        ((HomeActivity)Objects.requireNonNull(getActivity())).title.setText(getText(R.string.sub_categry));

        assert getArguments() != null;
        subcatsBean =  getArguments().getParcelable("subcategries");

        slides = getArguments().getParcelableArrayList("sliders");


        categoryViewModel = ViewModelProviders.of(this, getViewModelFactory()).get(CategoryViewModel.class);

        customAdapter = new SubCategoriesAdapter(getActivity(),subcatsBean.getSubcats(),getArguments().getString("depart"));
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));

        recyclerView.setAdapter(customAdapter);

        sliderPagerAdapter=new SliderPagerAdapter(getActivity(),slides);
        viewPager.setAdapter(sliderPagerAdapter);
        viewPager.setClipToPadding(false);
        indicator = view.findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);
        init();

        return view;
    }

    private void init() {

        final float density = getResources().getDisplayMetrics().density;
        //Set circle indicator radius
        indicator.setRadius(4 * density);
        NUM_PAGES =slides.size();
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };

        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);


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


    @NonNull
    private ViewModelProvider.Factory getViewModelFactory() {
        return new CategoryViewModelFactory(getActivity().getApplication());
    }

}
