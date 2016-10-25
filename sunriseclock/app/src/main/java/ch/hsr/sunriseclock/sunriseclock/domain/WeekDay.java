package ch.hsr.sunriseclock.sunriseclock.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Created by michi on 19.10.16.
 */

public enum WeekDay {
    // TODO adjust JSON to match only selected days (webclient!)
    @SerializedName("monday")
    MONDAY("monday"),
    @SerializedName("tuesday")
    TUESDAY("tuesday"),
    @SerializedName("wednesday")
    WEDNESDAY("wednesday"),
    @SerializedName("thursday")
    THURSDAY("thursday"),
    @SerializedName("friday")
    FRIDAY("friday"),
    @SerializedName("saturday")
    SATURDAY("saturday"),
    @SerializedName("sunday")
    SUNDAY("sunday");

    private String value;

    WeekDay(String value) {
        this.value=value;
    }

    public String getValue() {
        return(value);
    }
}
