package com.example.studentportal;

import android.os.Parcel;
import android.os.Parcelable;

public class Portal implements Parcelable{
    private String mPortalTitle;
    private String mPortalAddress;

    protected Portal(Parcel in) {
        String[] data = new String[2];
        in.readStringArray(data);
        mPortalTitle = data[0];
        mPortalAddress = data[1];
    }

    public static final Creator<Portal> CREATOR = new Creator<Portal>() {
        @Override
        public Portal createFromParcel(Parcel in) {
            return new Portal(in);
        }

        @Override
        public Portal[] newArray(int size) {
            return new Portal[size];
        }
    };

    @Override
    public String toString() {
       return mPortalTitle;
    }

    public String getmPortalTitle() {
        return mPortalTitle;
    }

    public String getmPortalAddress() {
        return mPortalAddress;
    }

    public void setmPortalTitle(String mPortalTitle) {
        this.mPortalTitle = mPortalTitle;
    }

    public void setmPortalAdress(String mPortalAddress) {
        this.mPortalAddress = mPortalAddress;
    }

    public Portal(String mPortalTitle, String mPortalURL) {

        this.mPortalTitle = mPortalTitle;
        this.mPortalAddress = mPortalURL;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{mPortalTitle, mPortalAddress});
    }
}