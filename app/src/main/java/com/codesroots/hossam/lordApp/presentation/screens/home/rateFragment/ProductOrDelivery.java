package com.codesroots.hossam.lordApp.presentation.screens.home.rateFragment;

import android.os.Parcel;
import android.os.Parcelable;

public class ProductOrDelivery  implements Parcelable {


    String name,imagepath,ordernumber;

    int id;
    public ProductOrDelivery(Parcel in) {
        name = in.readString();
        imagepath = in.readString();
        ordernumber = in.readString();
    }

    public static final Creator<ProductOrDelivery> CREATOR = new Creator<ProductOrDelivery>() {
        @Override
        public ProductOrDelivery createFromParcel(Parcel in) {
            return new ProductOrDelivery(in);
        }

        @Override
        public ProductOrDelivery[] newArray(int size) {
            return new ProductOrDelivery[size];
        }
    };

    public ProductOrDelivery(String name1,String imagepath1,String ordernum,int idd) {

        this.name = name1;
        this.imagepath = imagepath1;
        this.ordernumber = ordernum;
        this.id=idd;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public String getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(String ordernumber) {
        this.ordernumber = ordernumber;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(imagepath);
        dest.writeString(ordernumber);
    }
}
