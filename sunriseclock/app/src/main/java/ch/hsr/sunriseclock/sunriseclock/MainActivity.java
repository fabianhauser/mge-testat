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

public class MainActivity extends AppCompatActivity implements FloatingActionButton.OnClickListener, AlarmsFragment.OnAlarmItemSelectedListener {

    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = getSupportFragmentManager();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        switchToFragment(new AlarmsFragment(), null);
    }

    private void switchToFragment(Fragment fragment, Alarm alarm) {
        Bundle args = new Bundle();
        args.putSerializable(Constants.CURRENT_ALARM, alarm);
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

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        switch (item.getItemId()) {
            case android.R.id.home:
                // TODO implement back button
                switchToFragment(new AlarmsFragment(), null);
                break;
            case R.id.action_settings:
                switchToFragment(new ConfigurationFragment(), null);
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        switchToFragment(new AlarmDetailFragment(), null);
    }

    @Override
    public void onItemSelected(Alarm alarm) {
        // TODO add alarm
        switchToFragment(new AlarmDetailFragment(), alarm);
    }
}
