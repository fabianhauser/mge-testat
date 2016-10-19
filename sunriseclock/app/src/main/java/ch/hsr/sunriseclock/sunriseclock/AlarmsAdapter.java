package ch.hsr.sunriseclock.sunriseclock;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by michi on 19.10.16.
 */

public class AlarmsAdapter extends RecyclerView.Adapter<AlarmViewHolder> {

    private List<Alarm> alarms;

    public AlarmsAdapter(List<Alarm> alarms) {
        this.alarms = alarms;
    }

    @Override
    public AlarmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.list_item, parent, false);

        TextView textView = (TextView) v.findViewById(R.id.alarm_name_textview);

        AlarmViewHolder alarmViewHolder = new AlarmViewHolder(v, textView);
        return alarmViewHolder;
    }

    @Override
    public void onBindViewHolder(AlarmViewHolder viewHolder, int position) {
        final Alarm alarm = alarms.get(position);
        viewHolder.textView.setText(alarm.getName());
    }

    @Override
    public int getItemCount() {
        return this.alarms != null ? this.alarms.size() : 0;
    }

    public void addAlarm(int position, Alarm alarm) {

    }

    public void removeAlarm(Alarm alarm) {
        int position = alarms.indexOf(alarm);
        alarms.remove(position);
        notifyItemRemoved(position);
    }

}
