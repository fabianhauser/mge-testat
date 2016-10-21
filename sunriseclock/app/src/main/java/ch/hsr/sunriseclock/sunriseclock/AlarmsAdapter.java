package ch.hsr.sunriseclock.sunriseclock;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AlarmsAdapter extends  RecyclerView.Adapter<AlarmViewHolder> {

    private List<Alarm> alarms;
    private View.OnClickListener listener;

    public AlarmsAdapter(List<Alarm> alarms, View.OnClickListener onClickListener) {
        this.alarms = alarms;
        this.listener = onClickListener;
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
        viewHolder.parent.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return this.alarms != null ? this.alarms.size() : 0;
    }


    public Alarm getAlarm(String name) {
        for (Alarm alarm : this.alarms) {
            if (alarm.getName().equalsIgnoreCase(name)) {
                return alarm;
            }
        }
        return null;
    }

    public void addAlarm(Alarm alarm) {
        this.alarms.add(alarm);
    }

    public void removeAlarm(Alarm alarm) {
        int position = alarms.indexOf(alarm);
        alarms.remove(position);
        notifyItemRemoved(position);
    }

}
