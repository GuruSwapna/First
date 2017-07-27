package com.example.enchanter21.first;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by enchanter21 on 16/6/17.
 */

public class all_cats implements Parcelable {

    String uid;
    String uname;
    String udescription;


    protected all_cats(Parcel in) {
        uid = in.readString();
        uname = in.readString();
        udescription = in.readString();
    }

    public static final Creator<all_cats> CREATOR = new Creator<all_cats>() {
        @Override
        public all_cats createFromParcel(Parcel in) {
            return new all_cats(in);
        }

        @Override
        public all_cats[] newArray(int size) {
            return new all_cats[size];
        }
    };

    public String getUid() {
        return uid;
    }

    public void setUid(String id) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String name) {
        this.uname = uname;
    }

    public String getUdescription() {
        return udescription;
    }

    public void setUdescription(String udescription) {
        this.udescription = udescription;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(uid);
        parcel.writeString(uname);
        parcel.writeString(udescription);
    }
}
