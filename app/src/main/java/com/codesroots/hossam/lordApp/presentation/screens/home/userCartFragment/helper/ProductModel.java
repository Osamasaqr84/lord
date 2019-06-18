package com.codesroots.hossam.lordApp.presentation.screens.home.userCartFragment.helper;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import okhttp3.MultipartBody;

public class ProductModel {


    @SerializedName("user_id")
    @Expose
    private int user_id;


    @SerializedName("store_id")
    @Expose
    private int store_id;

    @SerializedName("product_id")
    @Expose
    private int product_id;

    @SerializedName(" ")
    @Expose
    private String address;

    @SerializedName("user_address")
    @Expose
    private String user_address;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("user_lat")
    @Expose
    private String user_lat;

    @SerializedName("user_long")
    @Expose
    private String user_long;

    @SerializedName("payment_id")
    @Expose
    private int payment_id;


    @SerializedName("notes")
    @Expose
    private String notes;


    @SerializedName("price")
    @Expose
    private String price;

    @SerializedName("photo")
    @Expose
    private MultipartBody.Part photo;

    @SerializedName("store_icon")
    @Expose
    private String store_icon;

    @SerializedName("storename")
    @Expose
    private String storename;


    @SerializedName("delivery_price")
    @Expose
    private String delivery_price;


    @SerializedName("delivery_time")
    @Expose
    private String delivery_time;

    @SerializedName("store_lat")
    @Expose
    private String store_lat;

    @SerializedName("store_long")
    @Expose
    private String store_long;

    @SerializedName("order_status")
    @Expose
    private int order_status;


    @SerializedName("rate")
    @Expose
    private float rate;


    @SerializedName("delivry_id")
    @Expose
    private int delivry_id;


    @SerializedName("count")
    @Expose
    private String count;



    public ProductModel(int user_id, int store_id,
                        int product_id,String count1, String address, String type,
                        String user_lat, String user_long, int payment_id, String notes, String price, int statues) {
        this.user_id = user_id;
        this.store_id = store_id;
        this.product_id = product_id;
        this.count=count1;
        this.user_address = address;
        this.type = type;
        this.user_lat = user_lat;
        this.user_long = user_long;
        this.payment_id = payment_id;
        this.notes = notes;
        this.price = price;
        this.order_status = statues;


    }

    public ProductModel(int user_id, int store_id,
                        int product_id, String address, String user_address1,
                        String user_lat, String user_long, int payment_id, String notes, String price, MultipartBody.Part photo, String store_icon,
                        String storename, String delivery_price, int delivry_id, String delivery_time, String store_long, String store_lat,
                        int statues, float store_rate)
    {
        this.user_id = user_id;
        this.store_id = store_id;
        this.product_id = product_id;
        this.address = address;
        this.user_address = user_address1;
        this.user_lat = user_lat;
        this.user_long = user_long;
        this.payment_id = payment_id;
        this.notes = notes;
        this.price = price;
        this.photo = photo;
        this.store_icon = store_icon;
        this.delivry_id=delivry_id;
        this.storename=storename;
        this.delivery_price = delivery_price;
        this.delivery_time= delivery_time;
        this.store_lat = store_lat;
        this.store_long = store_long;
        this.order_status=statues;
        this.rate=store_rate;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getStore_lat() {
        return store_lat;
    }

    public void setStore_lat(String store_lat) {
        this.store_lat = store_lat;
    }

    public String getStore_long() {
        return store_long;
    }

    public void setStore_long(String store_long) {
        this.store_long = store_long;
    }

    public int getOrder_status() {
        return order_status;
    }

    public void setOrder_status(int order_status) {
        this.order_status = order_status;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getDelivery_time() {
        return delivery_time;
    }

    public void setDelivery_time(String delivery_time) {
        this.delivery_time = delivery_time;
    }

    public String getStore_late() {
        return store_lat;
    }

    public void setStore_late(String store_late) {
        this.store_lat = store_late;
    }

    public String getStore_lang() {
        return store_long;
    }

    public void setStore_lang(String store_lang) {
        this.store_long = store_lang;
    }

    public MultipartBody.Part getPhoto() {
        return photo;
    }

    public void setPhoto(MultipartBody.Part photo) {
        this.photo = photo;
    }

    public String getStore_icon() {
        return store_icon;
    }

    public void setStore_icon(String store_icon) {
        this.store_icon = store_icon;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    public String getDelivery_price() {
        return delivery_price;
    }

    public void setDelivery_price(String delivery_price) {
        this.delivery_price = delivery_price;
    }

    public int getDelivry_id() {
        return delivry_id;
    }

    public void setDelivry_id(int delivry_id) {
        this.delivry_id = delivry_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUser_lat() {
        return user_lat;
    }

    public void setUser_lat(String user_lat) {
        this.user_lat = user_lat;
    }

    public String getUser_long() {
        return user_long;
    }

    public void setUser_long(String user_long) {
        this.user_long = user_long;
    }

    public int getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(int payment_id) {
        this.payment_id = payment_id;
    }

    public String getNotice() {
        return notes;
    }

    public void setNotice(String notice) {
        this.notes = notice;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
