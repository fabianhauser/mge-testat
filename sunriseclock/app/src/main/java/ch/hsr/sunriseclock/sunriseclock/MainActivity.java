package ch.hsr.sunriseclock.sunriseclock;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import ch.hsr.sunriseclock.sunriseclock.domain.Alarm;
import ch.hsr.sunriseclock.sunriseclock.domain.Configuration;
import ch.hsr.sunriseclock.sunriseclock.fragments.AlarmDetailFragment;
import ch.hsr.sunriseclock.sunriseclock.fragments.AlarmsFragment;
import ch.hsr.sunriseclock.sunriseclock.fragments.ConfigurationFragment;
import ch.hsr.sunriseclock.sunriseclock.listener.OnAlarmItemSelectedListener;

public class MainActivity extends AppCompatActivity implements FloatingActionButton.OnClickListener, OnAlarmItemSelectedListener {

    ArrayList<Alarm> alarms = new ArrayList<>();
    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = getSupportFragmentManager();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);

        // TODO update config
        String config = "localhost";
        if (config.isEmpty()) {
            switchToFragment(new ConfigurationFragment(), null, null);
        } else {
            switchToFragment(new AlarmsFragment(), null, null);
        }
    }

    private void switchToFragment(Fragment fragment, Alarm alarm, Configuration configuration) {
        Bundle args = new Bundle();
        args.putParcelable(Constants.CURRENT_ALARM, alarm);
        args.putParcelable(Constants.CURRENT_CONFIGURATION, configuration);
        args.putParcelableArrayList(Constants.ALARM_LIST, alarms);
        fragment.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (manager.getBackStackEntryCount() <= 1) {
            finish();
        } else {
            manager.popBackStack();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                switchToFragment(new AlarmsFragment(), null, null);
                break;
            case R.id.action_settings:
                switchToFragment(new ConfigurationFragment(), null, null);
                break;
            case R.id.action_delete:
                // TODO delete item;
                break;
            case R.id.action_save:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void saveConfiguration(Configuration configuration) {
        // TODO save configuration
        switchToFragment(new AlarmsFragment(), null, null);
    }

    public void saveAlarm(Alarm alarm) {
        // TODO save alarm
        alarms.add(alarm);
        switchToFragment(new AlarmsFragment(), null, null);
    }

    @Override
    public void onClick(View v) {
        switchToFragment(new AlarmDetailFragment(), null, null);
    }

    @Override
    public void onItemSelected(Alarm alarm) {
        switchToFragment(new AlarmDetailFragment(), alarm, null);
    }
}
