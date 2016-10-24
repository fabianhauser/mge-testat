package ch.hsr.sunriseclock.sunriseclock;

import android.content.SharedPreferences;
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;

import ch.hsr.sunriseclock.sunriseclock.API.ApiConfiguration;
import ch.hsr.sunriseclock.sunriseclock.API.ClockApi;
import ch.hsr.sunriseclock.sunriseclock.domain.Alarm;
import ch.hsr.sunriseclock.sunriseclock.domain.Configuration;
import ch.hsr.sunriseclock.sunriseclock.fragments.AlarmDetailFragment;
import ch.hsr.sunriseclock.sunriseclock.fragments.AlarmsFragment;
import ch.hsr.sunriseclock.sunriseclock.fragments.ConfigurationFragment;
import ch.hsr.sunriseclock.sunriseclock.listener.OnAlarmItemSelectedListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
        implements FloatingActionButton.OnClickListener, OnAlarmItemSelectedListener {

    private FragmentManager manager;
    private SharedPreferences localSharedPreferences;
    private SharedPreferences.Editor editor;

    private ClockApi api;
    private ApiConfiguration apiconfiguration;
    private ArrayList<Alarm> alarms = new ArrayList<>();
    private Configuration configuration;
    private Alarm currentAlarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = getSupportFragmentManager();

        localSharedPreferences = getSharedPreferences("local_shared_pref", MODE_PRIVATE);
        editor =  localSharedPreferences.edit();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getConfiguration().getHostname().equals(Constants.REMOTE_HOST_DEFAULT)) {
            switchToFragment(new ConfigurationFragment());
        } else {
            initAPI(getConfiguration().getHostname());
            switchToFragment(new AlarmsFragment());
        }
    }

    private void switchToFragment(Fragment fragment) {
        Bundle args = new Bundle();

        args.putParcelable(Constants.CURRENT_ALARM, currentAlarm);
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
                switchToFragment(new AlarmsFragment());
                break;
            case R.id.action_settings:
                switchToFragment(new ConfigurationFragment());
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
        storeConfiguration(configuration);
        switchToFragment(new AlarmsFragment());
    }

    private void storeConfiguration(Configuration configuration) {
        this.configuration = configuration;
        editor.putString(Constants.REMOTE_HOST_KEY, configuration.getHostname());
        editor.putInt(Constants.REMOTE_PORT_KEY, configuration.getPort());
        editor.commit();
    }


    @Override
    public void onPause() {
        super.onPause();
        storeConfiguration(getConfiguration());
    }

    @Override
    public void onResume() {
        this.configuration = getConfiguration();

        super.onResume();
    }

    public void saveAlarm(Alarm alarm) {
        if (!alarms.contains(alarm)) {
            alarms.add(alarm);
        }

        // TODO save alarm
        switchToFragment(new AlarmsFragment());
    }

    @Override
    public void onClick(View v) {
        // create new alarm
        switchToFragment(new AlarmDetailFragment());
    }

    @Override
    public void onItemSelected(Alarm alarm) {
        currentAlarm = alarm;
        // edit existing alarm
        switchToFragment(new AlarmDetailFragment());
    }

    private Configuration getConfiguration() {
        if (this.configuration == null) {
            this.configuration = new Configuration();
        }

        String remoteHostname = localSharedPreferences.getString(Constants.REMOTE_HOST_KEY, Constants.REMOTE_HOST_DEFAULT);
        configuration.setHostname(remoteHostname);

        Integer remotePort = localSharedPreferences.getInt(Constants.REMOTE_PORT_KEY, Constants.REMOTE_PORT_DEFAULT);
        configuration.setPort(remotePort);

        return this.configuration;
    }

    private void initAPI(String hostname) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://" + hostname + "/api/v2/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        this.api = retrofit.create(ClockApi.class);

        Call<ApiConfiguration> call = this.api.getConfiguration();

        call.enqueue(new Callback<ApiConfiguration>() {
            @Override
            public void onResponse(Call<ApiConfiguration> call, Response<ApiConfiguration> response) {
                if(response.code() != 200) {
                    // TODO whoopsie. errorify that!
                }
                apiconfiguration = response.body();
            }

            @Override
            public void onFailure(Call<ApiConfiguration> call, Throwable t) {
                // TODO Log error here since request failed
            }
        });
    }
}
