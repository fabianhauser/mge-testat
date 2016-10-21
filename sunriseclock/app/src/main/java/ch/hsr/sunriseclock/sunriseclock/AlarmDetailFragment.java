package ch.hsr.sunriseclock.sunriseclock;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

/**
 * Created by michi on 19.10.16.
 */

public class AlarmDetailFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.alarm_detail_fragment, container, false);

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Alarm alarm = (Alarm) getArguments().getSerializable(Constants.CURRENT_ALARM);
        fillData(root, alarm);

        return root;
    }

    private void fillData(View fragment, Alarm alarm) {
        if (fragment != null && alarm != null) {
            ((EditText) fragment.findViewById(R.id.wakeupTimeEditText)).setText(alarm.getWakeupTime().toString());
            ((EditText) fragment.findViewById(R.id.enlightenIntervalEditText)).setText(String.valueOf(alarm.getEnlightenInterval()));
            ((EditText) fragment.findViewById(R.id.lightDurationEditText)).setText(String.valueOf(alarm.getLightDuration()));
            ((CheckBox) fragment.findViewById(R.id.mondayCheckbox)).setChecked(alarm.getWeekdays().contains(Weekday.MONDAY));
            ((CheckBox) fragment.findViewById(R.id.tuesdayCheckbox)).setChecked(alarm.getWeekdays().contains(Weekday.TUESDAY));
            ((CheckBox) fragment.findViewById(R.id.wednesdayCheckbox)).setChecked(alarm.getWeekdays().contains(Weekday.WEDNESDAY));
            ((CheckBox) fragment.findViewById(R.id.thursdayCheckbox)).setChecked(alarm.getWeekdays().contains(Weekday.THURSDAY));
            ((CheckBox) fragment.findViewById(R.id.fridayCheckbox)).setChecked(alarm.getWeekdays().contains(Weekday.FRIDAY));
            ((CheckBox) fragment.findViewById(R.id.saturdayCheckbox)).setChecked(alarm.getWeekdays().contains(Weekday.SATURDAY));
            ((CheckBox) fragment.findViewById(R.id.sundayCheckbox)).setChecked(alarm.getWeekdays().contains(Weekday.SUNDAY));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:


                // User chose the "Settings" item, show the app settings UI...
                // TODO Open Settings
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        if (!(activity instanceof View.OnClickListener)) {
            throw new AssertionError("Activity must implement View.OnClickListener!");
        }
    }
}
