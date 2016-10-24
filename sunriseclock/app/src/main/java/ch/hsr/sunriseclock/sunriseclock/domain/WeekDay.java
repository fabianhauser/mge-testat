package ch.hsr.sunriseclock.sunriseclock.domain;

/**
 * Created by michi on 19.10.16.
 */

public enum WeekDay {
    // TODO adjust JSON to match only selected days (webclient!)
    MONDAY("monday"),
    TUESDAY("tuesday"),
    WEDNESDAY("wednesday"),
    THURSDAY("thursday"),
    FRIDAY("friday"),
    SATURDAY("saturday"),
    SUNDAY("sunday");

    private String value;

    WeekDay(String value) {
        this.value=value;
    }

    public String getValue() {
        return(value);
    }
}
