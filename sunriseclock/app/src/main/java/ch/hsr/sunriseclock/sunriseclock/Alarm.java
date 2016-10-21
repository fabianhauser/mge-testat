package ch.hsr.sunriseclock.sunriseclock;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Alarm implements Serializable {

    private String name;
    private Date wakeupTime;
    private int enlightenInterval;
    private int lightDuration;
    private List<Weekday> weekdays;

    public void addWeekday(Weekday weekday) {
        weekdays.add(weekday);
    }

    public void clearWeekday() {
        this.weekdays = new ArrayList<>();
    }

    public Alarm() {

    }

    public Alarm(String name) {
        this.name = name;
        this.weekdays = new ArrayList<>();
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
