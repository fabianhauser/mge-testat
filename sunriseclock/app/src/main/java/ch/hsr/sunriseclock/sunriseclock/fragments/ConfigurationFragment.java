package ch.hsr.sunriseclock.sunriseclock.fragments;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
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
            ((EditText) root.findViewById(R.id.remote_hostname_edittext)).setText(configuration.getHostname());
            ((EditText) root.findViewById(R.id.remote_port_edittext)).setText(configuration.getPort().toString());
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
                if (validateView()) {
                    ((MainActivity) getActivity()).saveConfiguration(getConfiguration());
                }
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean validateView() {
        TextInputLayout hostNameTextInputLayout = (TextInputLayout) getView().findViewById(R.id.remote_hostname_text_input_layout);
        TextInputLayout portTextInputLayout = (TextInputLayout) getView().findViewById(R.id.remote_port_text_input_layout);

        hostNameTextInputLayout.setErrorEnabled(false);
        portTextInputLayout.setErrorEnabled(false);

        EditText nameEditText = (EditText) getView().findViewById(R.id.remote_hostname_edittext);
        if (nameEditText.getText().toString().isEmpty()) {
            hostNameTextInputLayout.setErrorEnabled(true);
            hostNameTextInputLayout.setError(getString(R.string.configuration_hostname_error));
        } else {
            // TODO check pattern
            //  Patterns.WEB_URL.matcher(your_link).matches();
        }

        EditText portEditText = (EditText) getView().findViewById(R.id.remote_port_edittext);
        if (portEditText.getText().toString().isEmpty()) {
            portTextInputLayout.setErrorEnabled(true);
            portTextInputLayout.setError(getString(R.string.configuration_port_error));
        }

        return !(hostNameTextInputLayout.isErrorEnabled() || portTextInputLayout.isErrorEnabled());
    }

    private Configuration getConfiguration() {
        Configuration configuration = new Configuration();

        String hostname = ((EditText) getView().findViewById(R.id.remote_hostname_edittext)).getText().toString();
        configuration.setHostname(hostname);

        Integer port = Integer.parseInt( ((EditText) getView().findViewById(R.id.remote_port_edittext)).getText().toString() );
        configuration.setPort(port);

        return configuration;
    }
}
