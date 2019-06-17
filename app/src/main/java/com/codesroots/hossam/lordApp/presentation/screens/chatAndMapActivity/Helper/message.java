package com.codesroots.hossam.lordApp.presentation.screens.chatAndMapActivity.Helper;

import android.os.Parcel;
import android.os.Parcelable;

public class message implements Parcelable {


        String message,imageuri;
        int from;
       int localOrOnlinePath;

    public message(String message, int from,String imageuri1,int llocalOrOnlinePath) {
        this.message = message;
        this.from = from;
        this.imageuri = imageuri1;
        this.localOrOnlinePath = llocalOrOnlinePath;
    }

    public int getLocalOrOnlinePath() {
        return localOrOnlinePath;
    }

    public void setLocalOrOnlinePath(int localOrOnlinePath) {
        this.localOrOnlinePath = localOrOnlinePath;
    }

    public message(Parcel in) {
        message = in.readString();
        from = in.readInt();
    }

    public String getImageuri() {
        return imageuri;
    }

    public void setImageuri(String imageuri) {
        this.imageuri = imageuri;
    }

    public static final Creator<message> CREATOR = new Creator<message>() {
        @Override
        public message createFromParcel(Parcel in) {
            return new message(in);
        }

        @Override
        public message[] newArray(int size) {
            return new message[size];
        }
    };

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(message);
        dest.writeInt(from);
    }
}
