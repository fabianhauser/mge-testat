package ch.hsr.sunriseclock.sunriseclock;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ch.hsr.sunriseclock.sunriseclock.domain.Alarm;

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
        // TODO Is this the correct alarm?
        final Alarm alarm = alarms.get(position);
        viewHolder.getTextView().setText(alarm.getName());
        viewHolder.getParent().setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        if (this.alarms != null) {
            return this.alarms.size();
        } else {
            return 0;
        }
    }

    public Alarm getAlarm(String name) {
        for (Alarm alarm : this.alarms) {
            if (alarm.getName().equalsIgnoreCase(name)) {
                return alarm;
            }
        }
        return null;
    }
}
