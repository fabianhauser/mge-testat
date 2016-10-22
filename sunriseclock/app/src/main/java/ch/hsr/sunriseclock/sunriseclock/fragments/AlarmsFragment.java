package ch.hsr.sunriseclock.sunriseclock.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ch.hsr.sunriseclock.sunriseclock.AlarmViewHolder;
import ch.hsr.sunriseclock.sunriseclock.AlarmsAdapter;
import ch.hsr.sunriseclock.sunriseclock.Constants;
import ch.hsr.sunriseclock.sunriseclock.MainActivity;
import ch.hsr.sunriseclock.sunriseclock.R;
import ch.hsr.sunriseclock.sunriseclock.domain.Alarm;

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

        List<Alarm> alarms = getArguments().getParcelableArrayList(Constants.ALARM_LIST);

        adapter = new AlarmsAdapter(alarms, this);
        recyclerView.setAdapter(adapter);

        FloatingActionButton addButton = (FloatingActionButton) root.findViewById(R.id.add_alarm_fab);
        addButton.setOnClickListener((FloatingActionButton.OnClickListener) getActivity());

        if (alarms.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            root.findViewById(R.id.empty_textView).setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            root.findViewById(R.id.empty_textView).setVisibility(View.GONE);
        }

        setHasOptionsMenu(true);

        return root;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_save).setVisible(false);
        menu.findItem(R.id.action_delete).setVisible(false);
        menu.findItem(R.id.action_settings).setVisible(true);

        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onClick(View v) {
        AlarmViewHolder holder =  (AlarmViewHolder) recyclerView.getChildViewHolder(v);
        Alarm alarm = adapter.getAlarm(holder.getTextView().getText().toString());
        ((MainActivity) getActivity()).onItemSelected(alarm);
    }
}
