package ch.hsr.sunriseclock.sunriseclock.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import ch.hsr.sunriseclock.sunriseclock.Constants;
import ch.hsr.sunriseclock.sunriseclock.MainActivity;
import ch.hsr.sunriseclock.sunriseclock.R;
import ch.hsr.sunriseclock.sunriseclock.domain.Configuration;


public class ConfigurationFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.configuration_fragment, container, false);

        Configuration configuration = (Configuration) getArguments().getParcelable(Constants.CURRENT_CONFIGURATION);
        if (configuration != null) {
            ((EditText) root.findViewById(R.id.hostnameEditText)).setText(configuration.getName());
        }

        setHasOptionsMenu(true);

        return root;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_save).setVisible(true);
        menu.findItem(R.id.action_delete).setVisible(false);
        menu.findItem(R.id.action_settings).setVisible(false);

        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save : {
                Configuration configuration = getConfiguration();
                ((MainActivity) getActivity()).saveConfiguration(configuration);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private Configuration getConfiguration() {
        Configuration configuration = new Configuration();

        String hostname = ((EditText) getView().findViewById(R.id.hostnameEditText)).getText().toString();
        configuration.setName(hostname);

        return configuration;
    }
}
