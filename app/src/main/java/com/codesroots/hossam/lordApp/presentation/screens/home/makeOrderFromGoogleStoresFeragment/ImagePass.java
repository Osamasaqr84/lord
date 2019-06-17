package com.codesroots.hossam.lordApp.presentation.screens.home.makeOrderFromGoogleStoresFeragment;

import android.os.Parcel;
import android.os.Parcelable;

import okhttp3.MultipartBody;


public class ImagePass implements Parcelable {

    MultipartBody.Part photo_part;

    public MultipartBody.Part getPhoto_part() {
        return photo_part;
    }

    public ImagePass() {
    }

    public void setPhoto_part(MultipartBody.Part photo_part) {
        this.photo_part = photo_part;
    }

    protected ImagePass(Parcel in) {
    }

    public static final Creator<ImagePass> CREATOR = new Creator<ImagePass>() {
        @Override
        public ImagePass createFromParcel(Parcel in) {
            return new ImagePass(in);
        }

        @Override
        public ImagePass[] newArray(int size) {
            return new ImagePass[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
