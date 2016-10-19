package ch.hsr.sunriseclock.sunriseclock;

import java.util.Date;
import java.util.List;

public class Alarm {

    private String name;
    private Date wakeupTime;
    private int enlightenInterval;
    private int lightDuration;
    private List<Weekday> weekdays;

    public Alarm(String name) {
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
}
