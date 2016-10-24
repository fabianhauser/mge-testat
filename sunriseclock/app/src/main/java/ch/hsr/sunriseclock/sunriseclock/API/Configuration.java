package ch.hsr.sunriseclock.sunriseclock.API;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import ch.hsr.sunriseclock.sunriseclock.domain.Alarm;

public class Configuration {

    @SerializedName("alarms")
    @Expose
    private List<Alarm> alarms = new ArrayList<Alarm>();

    /**
     * 
     * @return
     *     The alarms
     */
    public List<Alarm> getAlarms() {
        return alarms;
    }

    /**
     * 
     * @param alarms
     *     The alarms
     */
    public void setAlarms(List<Alarm> alarms) {
        this.alarms = alarms;
    }

}
