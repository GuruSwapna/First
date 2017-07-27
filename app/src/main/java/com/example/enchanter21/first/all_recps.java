package com.example.enchanter21.first;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by enchanter21 on 17/6/17.
 */

public class all_recps implements Parcelable {

    String cateid;
    String tpcname;
    String shtdesc;

    protected all_recps(Parcel in) {
        cateid = in.readString();
        tpcname = in.readString();
        shtdesc = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cateid);
        dest.writeString(tpcname);
        dest.writeString(shtdesc);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<all_recps> CREATOR = new Creator<all_recps>() {
        @Override
        public all_recps createFromParcel(Parcel in) {
            return new all_recps(in);
        }

        @Override
        public all_recps[] newArray(int size) {
            return new all_recps[size];
        }
    };

    public String getCateid() {
        return cateid;
    }

    public void setCateid(String cateid) {
        this.cateid = cateid;
    }

    public String getTpcname() {
        return tpcname;
    }

    public void setTpcname(String tpcname) {
        this.tpcname = tpcname;
    }

    public String getShtdesc() {
        return shtdesc;
    }

    public void setShtdesc(String shtdesc) {
        this.shtdesc = shtdesc;
    }
}
