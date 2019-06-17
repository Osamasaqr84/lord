package com.codesroots.hossam.lordApp.presentation.screens.home.storesFragment;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.codesroots.hossam.lordApp.presentation.screens.home.dealsOffersFragment.DealsOffersViewModel;
import com.codesroots.hossam.lordApp.presentation.screens.home.loginFragment.LoginViewModel;
import com.codesroots.hossam.lordApp.presentation.screens.home.productsFragment.ProductsViewModel;
import com.codesroots.hossam.lordApp.presentation.screens.home.registerFragment.RegisterViewModel;
import com.codesroots.hossam.lordApp.presentation.screens.home.userCartFragment.UserCartViewModel;
import com.codesroots.hossam.lordApp.presentation.screens.home.userCartFragment.helper.ProductModel;
import com.codesroots.hossam.lordApp.dataLayer.apiData.ApiClient;
import com.codesroots.hossam.lordApp.dataLayer.apiData.ApiInterface;
import com.codesroots.hossam.lordApp.dataLayer.localDatabase.LocalDatabase;
import com.codesroots.hossam.lordApp.dataLayer.localDatabase.userCart.deo.ProductDeo;
import com.codesroots.hossam.lordApp.dataLayer.repositories.AllProductsRepository;
import com.codesroots.hossam.lordApp.dataLayer.repositories.AllStorsRepository;
import com.codesroots.hossam.lordApp.dataLayer.repositories.LoginRepository;
import com.codesroots.hossam.lordApp.dataLayer.repositories.OffersRepository;
import com.codesroots.hossam.lordApp.dataLayer.repositories.PaymentRepository;
import com.codesroots.hossam.lordApp.dataLayer.repositories.RegisterRepository;
import com.codesroots.hossam.lordApp.dataLayer.repositories.UserCartRepository;
import com.codesroots.hossam.lordApp.presentation.screens.home.paymentFragment.PaymentViewModel;

import java.util.List;

public class AllStoresViewModelFactory implements ViewModelProvider.Factory {


    private Application application;
    int typeid,categry;
    int storeid,userid;//// for getProducts in that store
    String userlocation,categrytype;
    List<ProductModel> productList; ///// alldata of product send to serever when user parches
    public AllStoresViewModelFactory(@NonNull Application application1) {
        application = application1;
    }

    public AllStoresViewModelFactory(Application application1, int type, int cate_or_sub_id,String userlocation1
            ,String categrytype1) {
        application=application1;
        typeid =type;
        categry = cate_or_sub_id;
        userlocation = userlocation1;
        categrytype =categrytype1;
    }

    public AllStoresViewModelFactory(Application application1, int stor_id,int user) {
        application = application1;
        storeid=stor_id;
        userid=user;
    }

    public AllStoresViewModelFactory(Application application, List<ProductModel> productList1) {
        application = application;
        productList= productList1;
    }

    public AllStoresViewModelFactory(Application application1, int stor_id) {
        application = application1;
        storeid=stor_id;

    }

    @SuppressWarnings("SingleStatementInBlock")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == StoresViewModel.class)
                 {
            return (T) new StoresViewModel(getStoriesRepositry());
        }
        else if (modelClass == ProductsViewModel.class)
        {
            return (T) new ProductsViewModel(getProductsRepositry());
        }

        else if (modelClass == UserCartViewModel.class)
        {
            return (T) new UserCartViewModel(getUserCartRepositry());
        }
        else if (modelClass == PaymentViewModel.class)
        {
            return (T) new PaymentViewModel(getPaymentRepositry());
        }
        else if (modelClass == RegisterViewModel.class)
        {
            return (T) new RegisterViewModel(getRegisterRepositry());
        }
        else if (modelClass == LoginViewModel.class)
        {
            return (T) new LoginViewModel(getLoginRepositry());
        }
        else if (modelClass == DealsOffersViewModel.class)
        {
            return (T) new DealsOffersViewModel(getDealsOffersRepositry());
        }
        throw new IllegalArgumentException("Invalid view model class type");
    }


    @NonNull
    private AllStorsRepository getStoriesRepositry() {
        return new AllStorsRepository(getApiService(),typeid,categry,userlocation,categrytype);
    }

    @NonNull
    private AllProductsRepository
    getProductsRepositry() {
        return new AllProductsRepository(getApiService(),getProductDeo(),storeid,userid);
    }


    @NonNull
    private PaymentRepository getPaymentRepositry() {
        return new PaymentRepository(getApiService(),productList);
    }


    @NonNull
    private UserCartRepository getUserCartRepositry() {
        return new UserCartRepository(getProductDeo(),storeid);
    }



    @NonNull
    private RegisterRepository getRegisterRepositry() {
        return new RegisterRepository(getApiService());
    }

    @NonNull
    private LoginRepository getLoginRepositry() {
        return new LoginRepository(getApiService());
    }

    @NonNull
    private OffersRepository getDealsOffersRepositry() {
        return new OffersRepository(getApiService());
    }
    private ApiInterface getApiService() {
        return ApiClient.getClient().create(ApiInterface.class);
    }


    private ProductDeo getProductDeo() {
        return LocalDatabase.getInstance(application).productDeo();
    }

}
