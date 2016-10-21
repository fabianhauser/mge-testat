package ch.hsr.sunriseclock.sunriseclock;


import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Constants {

    public static final DateFormat timeFormatter = new SimpleDateFormat("hh:mm");

    public static final String CURRENT_CONFIGURATION = "current_configuration";
    public static final String CURRENT_ALARM = "current_alarm";
    public static final String ALARM_LIST = "alarm_list";
}
