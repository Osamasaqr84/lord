package com.codesroots.hossam.lordApp.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Suleiman19 on 10/22/15.
 */
public class ImageToShowModel implements Parcelable {

    String name, url;

    public ImageToShowModel() {

    }

    protected ImageToShowModel(Parcel in) {
        name = in.readString();
        url = in.readString();
    }

    public static final Creator<ImageToShowModel> CREATOR = new Creator<ImageToShowModel>() {
        @Override
        public ImageToShowModel createFromParcel(Parcel in) {
            return new ImageToShowModel(in);
        }

        @Override
        public ImageToShowModel[] newArray(int size) {
            return new ImageToShowModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(url);
    }
}
