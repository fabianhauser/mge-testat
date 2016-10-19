package ch.hsr.sunriseclock.sunriseclock;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AlarmsAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new AlarmsAdapter(getAlarms());
        recyclerView.setAdapter(adapter);
    }

    /* TODO: Replace with datasource */
    private List<Alarm> getAlarms() {
        final List<Alarm> alarms = new ArrayList<>();
        alarms.add(new Alarm("Wochentage"));
        alarms.add(new Alarm("Wochenende"));

        return alarms;
    }
}
