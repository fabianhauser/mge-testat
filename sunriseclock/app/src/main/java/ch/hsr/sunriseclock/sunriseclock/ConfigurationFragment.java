package ch.hsr.sunriseclock.sunriseclock;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class ConfigurationFragment extends Fragment implements View.OnClickListener {

    public interface OnConfigSavedListener {
        public void onConfigSaved(Configuration configuration);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.configuration_fragment, container, false);

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Configuration configuration = (Configuration) getArguments().getParcelable(Constants.CURRENT_CONFIGURATION);
        if (configuration != null) {
            ((EditText) root.findViewById(R.id.hostnameEditText)).setText(configuration.getName());
        }

        Button saveButton = (Button) root.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {
        Configuration configuration = saveConfiguration();
        ((MainActivity) getActivity()).onConfigSaved(configuration);
    }

    private Configuration saveConfiguration() {
        View fragment  =  getView();

        Configuration configuration = new Configuration();

        String hostname = ((EditText) fragment.findViewById(R.id.hostnameEditText)).getText().toString();
        configuration.setName(hostname);

        return configuration;
    }
}
