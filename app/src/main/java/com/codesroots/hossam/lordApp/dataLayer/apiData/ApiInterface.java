package com.codesroots.hossam.lordApp.dataLayer.apiData;

import com.codesroots.hossam.lordApp.entities.BestOffers;
import com.codesroots.hossam.lordApp.entities.Categories;
import com.codesroots.hossam.lordApp.entities.DealsModel;
import com.codesroots.hossam.lordApp.entities.DeliveryComments;
import com.codesroots.hossam.lordApp.entities.DeliveryOffers;
import com.codesroots.hossam.lordApp.entities.EditProfile;
import com.codesroots.hossam.lordApp.entities.FavProduct;
import com.codesroots.hossam.lordApp.entities.LoginResponseModel;
import com.codesroots.hossam.lordApp.entities.MYOrdersModel;
import com.codesroots.hossam.lordApp.entities.MoreRate;
import com.codesroots.hossam.lordApp.entities.Notifications;
import com.codesroots.hossam.lordApp.entities.OffersModel;
import com.codesroots.hossam.lordApp.entities.ProductDetailsModel;
import com.codesroots.hossam.lordApp.entities.Products_in_Stories_Model;
import com.codesroots.hossam.lordApp.entities.Profile;
import com.codesroots.hossam.lordApp.entities.RatessOfProductModel;
import com.codesroots.hossam.lordApp.entities.RegisterResponseModel;
import com.codesroots.hossam.lordApp.entities.StoreSettingEntity;
import com.codesroots.hossam.lordApp.entities.StoresFromGoogleModel;
import com.codesroots.hossam.lordApp.entities.StoresList;
import com.codesroots.hossam.lordApp.presentation.screens.home.userCartFragment.helper.ProductModel;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiInterface {

    @GET("stores/getalluserstoredata/{userId}.json")
    @Headers("Accept: Application/json")
    Call<StoreSettingEntity> getStoreSettingData(
            @Path(value = "userId") int userId
    );

    @GET("Categories/GetALLCat.json")
    @Headers("Accept: Application/json")
    Call<Categories> getCatigriesData(
    );

    @GET("offers/getbestoffers.json")
    @Headers("Accept: Application/json")
    Call<BestOffers> getbestoffers(
    );

    @GET("products/getmostproductrate.json")
    @Headers("Accept: Application/json")
    Call<MoreRate> getMoreRate(
    );

    @GET("smallstores/getsmallstoredata/{cateOrUbbcategryId}/{type}.json")
    @Headers("Accept: Application/json")
    Call<StoresList> getStorsData(
            @Path(value = "cateOrUbbcategryId") int storid,
            @Path(value = "type") int userId
    );

    @GET("https://maps.googleapis.com/maps/api/place/nearbysearch/json?radius=3000")
    @Headers("Accept: Application/json")
    Call<StoresFromGoogleModel> getStoresfroomgooglesData(
            @Query("key") String key,
            @Query("location") String location,
            @Query("types") String types
    );


    @GET("products/ProductList/{storid}/{userid}.json")
    @Headers("Accept: Application/json")
    Call<Products_in_Stories_Model> getProductsData(
            @Path(value = "storid") int storid,
            @Path(value = "userid") int userid
    );


    @GET("favourite/getuserfavourite/{userid}.json")
    @Headers("Accept: Application/json")
    Call<FavProduct> getFavProductsData(
            @Path(value = "userid") int userid
    );


    @GET("products/getproduct/{productid}/{userid}.json")
    @Headers("Accept: Application/json")
    Call<ProductDetailsModel> getProductDetails(
            @Path(value = "productid") int productid,
            @Path(value = "userid") int userid
    );

    @GET("orders/getorders/{userid}.json")
    @Headers("Accept: Application/json")
    Call<MYOrdersModel> getMyOrders(
            @Path(value = "userid") int userid
    );

    @GET("orders/getordersadmins.json")
    @Headers("Accept: Application/json")
    Call<MYOrdersModel> getMyOrdersForAdmin(

    );

    @GET("orders/getorders/{userid}.json")
    @Headers("Accept: Application/json")
    Call<MYOrdersModel> getMyOrdersFortrader(
            @Path(value = "userid") int userid
    );


    @Multipart
    @POST("orders/androidorder/2.json")
    Call<ResponseBody> makeOrder(
            @Part("orders") List<ProductModel> models,
            @Part MultipartBody.Part file
    );

    @POST("orders/androidorder/1.json")
    Call<ResponseBody> makeOrderProduct(
            @Body List<ProductModel> models
    );


    @Multipart
    @POST("users/add.json")
    Call<RegisterResponseModel> register(
            @Part("username") RequestBody username,
            @Part("password") RequestBody password,
            @Part("gender") RequestBody gender,
            @Part("phone") RequestBody phone,
            @Part("birthdate") RequestBody birthdate
    );

    @Multipart
    @POST("users/token.json")
    Call<LoginResponseModel> login(
            @Part("username") RequestBody username,
            @Part("password") RequestBody password
    );


    @Multipart
    @POST("orders/edit/{orderid}.json")
    Call<ResponseBody> editOrderStatues(
            @Path(value = "orderid") int orderid,
            @Part("delivery_id") RequestBody delivry,
            @Part("order_status") RequestBody status,
            @Part("delivery_price") RequestBody price
    );


    @Multipart
    @POST("orders/edit/{orderid}.json")
    Call<ResponseBody> editOrderStatuesData(
            @Path(value = "orderid") int orderid,
            @Part("order_status") RequestBody order_status
    );


    @POST("users/view/{userid}.json")
    Call<Profile> getProfileData(
            @Path(value = "userid") int userid
    );


    @GET("products/getallproductratesbyid/{productid}/{type}.json")
    @Headers("Accept: Application/json")
    Call<RatessOfProductModel> getProductsRate(
            @Path(value = "productid") int productid,
            @Path(value = "type") int type
    );


    @GET("delivries/getdeliveryrate/{deliveryId}.json")
    @Headers("Accept: Application/json")
    Call<DeliveryComments> getDeliveryRates(
            @Path(value = "deliveryId") int deliveryId
    );


    @GET("offers/getoffers.json")
    Call<OffersModel> getOffers();

    @GET("deals/getdeals.json")
    Call<DealsModel> getDeals();

    @GET("offers/getorderdelivery/{orderid}.json")
    @Headers("Accept: Application/json")
    Call<DeliveryOffers> getDeliveryOffers(
            @Path(value = "orderid") int orderid
    );


    @Multipart
    @POST("favourite/addfavourite.json")
    Call<ResponseBody> addfavourite(
            @Part("user_id") RequestBody user_id,
            @Part("product_id") RequestBody product_id,
            @Part("smallstore_id") RequestBody smallstore_id
    );

    @DELETE("favourite/delete/{favid}.json")
    @Headers("Accept: Application/json")
    Call<ResponseBody> deleteFav(
            @Path(value = "favid") int favid
    );


    @GET("Notifications/getnotifications/{userid}.json")
    @Headers("Accept: Application/json")
    Call<Notifications> getnotifications(
            @Path(value = "userid") int userid
    );


    @Multipart
    @POST("productrates/add.json")
    Call<ResponseBody> productrates(
            @Part("user_id") RequestBody user_id,
            @Part("product_id") RequestBody product_id,
            @Part("comment") RequestBody comment,
            @Part("rate") RequestBody rate
    );


    @Multipart
    @POST("deliveryrates/add.json")
    Call<ResponseBody> deliveryrates(
            @Part("user_id") RequestBody user_id,
            @Part("product_id") RequestBody product_id,
            @Part("comment") RequestBody comment,
            @Part("rate") RequestBody rate
    );


    @Multipart
    @POST("users/edit/{userid}.json")
    Call<EditProfile> editProfile(
            @Path(value = "userid", encoded = true) int user_id,
            @Part("username") RequestBody username,
            @Part("phone") RequestBody phone,
            @Part MultipartBody.Part file);

}

