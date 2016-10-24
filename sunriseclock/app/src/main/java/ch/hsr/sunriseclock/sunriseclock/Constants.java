package ch.hsr.sunriseclock.sunriseclock;


import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Constants {

    public static final DateFormat timeFormatter = new SimpleDateFormat("hh:mm");

    public static final String CURRENT_CONFIGURATION = "current_configuration";
    public static final String CURRENT_ALARM = "current_alarm";
    public static final String ALARM_LIST = "alarm_list";


    public static final String REMOTE_HOST_KEY = "remote_host";
    public static final String REMOTE_HOST_DEFAULT = "localhost";

    public static final String REMOTE_PORT_KEY = "remote_port";
    public static final Integer REMOTE_PORT_DEFAULT = new Integer(3000);

}
