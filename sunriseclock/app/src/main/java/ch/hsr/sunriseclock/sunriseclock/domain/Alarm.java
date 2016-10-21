package ch.hsr.sunriseclock.sunriseclock.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Alarm implements Parcelable {

    private String name;
    private Date wakeupTime;
    private int enlightenInterval;
    private int lightDuration;
    private List<Weekday> weekdays = new ArrayList<>();

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

    public void addWeekday(Weekday weekday) {
        weekdays.add(weekday);
    }

    public void clearWeekday() {
        this.weekdays = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public List<Weekday> getWeekdays() {
        return weekdays;
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
}
