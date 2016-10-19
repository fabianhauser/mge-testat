package ch.hsr.sunriseclock.sunriseclock;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class AlarmsFragment extends Fragment {

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

        adapter = new AlarmsAdapter(getAlarms());
        recyclerView.setAdapter(adapter);

        FloatingActionButton addButton = (FloatingActionButton) root.findViewById(R.id.add_alarm_fab);
        addButton.setOnClickListener((FloatingActionButton.OnClickListener) getActivity());

        return root;
    }

    /* TODO: Replace with datasource */
    private List<Alarm> getAlarms() {
        final List<Alarm> alarms = new ArrayList<>();
        alarms.add(new Alarm("Wochentage"));
        alarms.add(new Alarm("Wochenende"));

        return alarms;
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        if (!(activity instanceof FloatingActionButton.OnClickListener)) {
            throw new AssertionError("Activity must implement FloatingActionButton.OnClickListener!");
        }
    }

}
