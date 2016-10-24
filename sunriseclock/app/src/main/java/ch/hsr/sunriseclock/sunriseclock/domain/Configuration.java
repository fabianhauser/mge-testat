package ch.hsr.sunriseclock.sunriseclock.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class Configuration implements Parcelable{

    private String hostname;
    private Integer port;

    public Configuration() {
    }

    protected Configuration(Parcel in) {
        hostname = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(hostname);
    }

    @Override
    public int describeContents() {
        return 0;
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

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }
}
