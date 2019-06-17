package com.codesroots.hossam.lordApp.presentation.screens.home.userCartFragment.helper;

import android.os.Parcel;
import android.os.Parcelable;

public class ProductInfoToPost implements Parcelable {

    int productId,productCount;

    public ProductInfoToPost(int productId, int productCount) {
        this.productId = productId;
        this.productCount = productCount;
    }

    protected ProductInfoToPost(Parcel in) {
        productId = in.readInt();
        productCount = in.readInt();
    }

    public static final Creator<ProductInfoToPost> CREATOR = new Creator<ProductInfoToPost>() {
        @Override
        public ProductInfoToPost createFromParcel(Parcel in) {
            return new ProductInfoToPost(in);
        }

        @Override
        public ProductInfoToPost[] newArray(int size) {
            return new ProductInfoToPost[size];
        }
    };

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(productId);
        dest.writeInt(productCount);
    }
}
