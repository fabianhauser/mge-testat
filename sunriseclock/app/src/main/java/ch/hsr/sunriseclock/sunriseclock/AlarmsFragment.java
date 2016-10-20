package ch.hsr.sunriseclock.sunriseclock;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AlarmsFragment extends Fragment implements View.OnClickListener {

    private RecyclerView recyclerView;
    private AlarmsAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.alarms_fragment, container, false);

        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new AlarmsAdapter(getAlarms(), this);
        recyclerView.setAdapter(adapter);

        FloatingActionButton addButton = (FloatingActionButton) root.findViewById(R.id.add_alarm_fab);
        addButton.setOnClickListener((FloatingActionButton.OnClickListener) getActivity());

        return root;
    }

    /* TODO: Replace with datasource */
    private List<Alarm> getAlarms() {
        final List<Alarm> alarms = new ArrayList<>();
        alarms.add(createDummy("Wochentage"));
        alarms.add(createDummy("Wochenende"));

        return alarms;
    }

    private Alarm createDummy(String name) {
        Alarm dummy =  new Alarm(name);
        dummy.setEnlightenInterval(5);
        dummy.setLightDuration(10);
        dummy.setWakeupTime(new Date());

        dummy.addWeekday(Weekday.MONDAY);
        dummy.addWeekday(Weekday.TUESDAY);
        dummy.addWeekday(Weekday.THURSDAY);
        dummy.addWeekday(Weekday.SUNDAY);

        return dummy;
    }

    @Override
    public void onClick(View v) {
        AlarmViewHolder holder =  (AlarmViewHolder) recyclerView.getChildViewHolder(v);
        Alarm alarm = adapter.getAlarm(holder.getTextView().getText().toString());
        ((MainActivity) getActivity()).onItemSelected(alarm);
    }

    public interface OnAlarmItemSelectedListener {
        public void onItemSelected(Alarm position);
    }
}
