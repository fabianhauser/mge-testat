package ch.hsr.sunriseclock.sunriseclock.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class Configuration implements Parcelable{

    private String name;

    public Configuration() {
    }

    protected Configuration(Parcel in) {
        name = in.readString();
    }

    public static final Creator<Configuration> CREATOR = new Creator<Configuration>() {
        @Override
        public Configuration createFromParcel(Parcel in) {
            return new Configuration(in);
        }

        @Override
        public Configuration[] newArray(int size) {
            return new Configuration[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
