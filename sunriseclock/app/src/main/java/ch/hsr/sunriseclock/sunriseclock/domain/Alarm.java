package ch.hsr.sunriseclock.sunriseclock.domain;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Alarm implements Parcelable {
    @SerializedName("name")
    @Expose
    private String name;

    // TODO add enabled to GUI
    @SerializedName("selected")
    @Expose
    private Boolean enabled;

    // TODO adjust API and webclient to match full date format (and take the time out).
    @SerializedName("time")
    @Expose
    private Date wakeupTime;

    @SerializedName("enlightDuration")
    @Expose
    private int enlightenInterval;

    @SerializedName("lightDuration")
    @Expose
    private int lightDuration;

    @SerializedName("weekDays")
    @Expose
    private List<WeekDay> weekDays = new ArrayList<>();

    public Alarm() {
    }

    protected Alarm(Parcel in) {
        name = in.readString();
        enlightenInterval = in.readInt();
        lightDuration = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(enlightenInterval);
        dest.writeInt(lightDuration);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Alarm> CREATOR = new Creator<Alarm>() {
        @Override
        public Alarm createFromParcel(Parcel in) {
            return new Alarm(in);
        }

        @Override
        public Alarm[] newArray(int size) {
            return new Alarm[size];
        }
    };

    public void addWeekday(WeekDay weekDay) {
        weekDays.add(weekDay);
    }

    public void clearWeekday() {
        this.weekDays = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public List<WeekDay> getWeekDays() {
        return weekDays;
    }

    public int getLightDuration() {
        return lightDuration;
    }

    public int getEnlightenInterval() {
        return enlightenInterval;
    }

    public Date getWakeupTime() {
        return wakeupTime;
    }

    public void setWakeupTime(Date wakeupTime) {
        this.wakeupTime = wakeupTime;
    }

    public void setEnlightenInterval(int enlightenInterval) {
        this.enlightenInterval = enlightenInterval;
    }

    public void setLightDuration(int lightDuration) {
        this.lightDuration = lightDuration;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
