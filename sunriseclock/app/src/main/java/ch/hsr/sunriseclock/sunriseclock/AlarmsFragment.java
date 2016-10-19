package ch.hsr.sunriseclock.sunriseclock;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michi on 19.10.16.
 */

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

        return root;
    }

    /* TODO: Replace with datasource */
    private List<Alarm> getAlarms() {
        final List<Alarm> alarms = new ArrayList<>();
        alarms.add(new Alarm("Wochentage"));
        alarms.add(new Alarm("Wochenende"));

        return alarms;
    }

}
