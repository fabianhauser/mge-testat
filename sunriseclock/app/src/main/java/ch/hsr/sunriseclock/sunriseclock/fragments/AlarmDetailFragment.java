package ch.hsr.sunriseclock.sunriseclock.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import java.text.ParseException;
import java.util.Date;

import ch.hsr.sunriseclock.sunriseclock.Constants;
import ch.hsr.sunriseclock.sunriseclock.MainActivity;
import ch.hsr.sunriseclock.sunriseclock.R;
import ch.hsr.sunriseclock.sunriseclock.domain.Alarm;
import ch.hsr.sunriseclock.sunriseclock.domain.Weekday;
import ch.hsr.sunriseclock.sunriseclock.helper.CustomTimePicker;

import static ch.hsr.sunriseclock.sunriseclock.Constants.timeFormatter;
import static ch.hsr.sunriseclock.sunriseclock.R.id.nameEditText;
import static ch.hsr.sunriseclock.sunriseclock.R.id.wakeupTimeEditText;

public class AlarmDetailFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.alarm_detail_fragment, container, false);

        // Time picker
        EditText wakeupTimeEditText = (EditText) root.findViewById(R.id.wakeupTimeEditText);
        CustomTimePicker fromTime = new CustomTimePicker(wakeupTimeEditText, getContext());

        // fill detail
        Alarm alarm = (Alarm) getArguments().getParcelable(Constants.CURRENT_ALARM);
        fillData(root, alarm);

        // show action toolbar
        setHasOptionsMenu(true);

        return root;
    }

    private void fillData(View fragment, Alarm alarm) {
        if (fragment != null && alarm != null) {
            ((EditText) fragment.findViewById(nameEditText)).setText(alarm.getName());
            ((EditText) fragment.findViewById(wakeupTimeEditText)).setText(timeFormatter.format(alarm.getWakeupTime()));
            ((EditText) fragment.findViewById(R.id.enlightenIntervalEditText)).setText(String.valueOf(alarm.getEnlightenInterval()));
            ((EditText) fragment.findViewById(R.id.lightDurationEditText)).setText(String.valueOf(alarm.getLightDuration()));
            ((CheckBox) fragment.findViewById(R.id.monday_checkbox)).setChecked(alarm.getWeekdays().contains(Weekday.MONDAY));
            ((CheckBox) fragment.findViewById(R.id.tuesday_checkbox)).setChecked(alarm.getWeekdays().contains(Weekday.TUESDAY));
            ((CheckBox) fragment.findViewById(R.id.wednesday_checkbox)).setChecked(alarm.getWeekdays().contains(Weekday.WEDNESDAY));
            ((CheckBox) fragment.findViewById(R.id.thursday_checkbox)).setChecked(alarm.getWeekdays().contains(Weekday.THURSDAY));
            ((CheckBox) fragment.findViewById(R.id.friday_checkbox)).setChecked(alarm.getWeekdays().contains(Weekday.FRIDAY));
            ((CheckBox) fragment.findViewById(R.id.saturday_checkbox)).setChecked(alarm.getWeekdays().contains(Weekday.SATURDAY));
            ((CheckBox) fragment.findViewById(R.id.sunday_checkbox)).setChecked(alarm.getWeekdays().contains(Weekday.SUNDAY));
        }
    }

    private Alarm getAlarm() {
        View fragment  =  getView();

        Alarm alarm = new Alarm();
        alarm.setName( ((EditText) fragment.findViewById(nameEditText)).getText().toString());

        Date wakeUpTime = null;
        try {
            wakeUpTime = timeFormatter.parse( ((EditText) fragment.findViewById(wakeupTimeEditText)).getText().toString() );
            alarm.setWakeupTime(wakeUpTime);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }

        String enlightenInterval = ((EditText) fragment.findViewById(R.id.enlightenIntervalEditText)).getText().toString();
        alarm.setEnlightenInterval(Integer.parseInt(enlightenInterval));

        String lightInterval = ((EditText) fragment.findViewById(R.id.lightDurationEditText)).getText().toString();
        alarm.setEnlightenInterval(Integer.parseInt(lightInterval));

        alarm.clearWeekday();

        if (((CheckBox)fragment.findViewById(R.id.monday_checkbox)).isChecked()) {
            alarm.addWeekday(Weekday.MONDAY);
        }
        if (((CheckBox)fragment.findViewById(R.id.tuesday_checkbox)).isChecked()) {
            alarm.addWeekday(Weekday.TUESDAY);
        }
        if (((CheckBox)fragment.findViewById(R.id.wednesday_checkbox)).isChecked()) {
            alarm.addWeekday(Weekday.WEDNESDAY);
        }
        if (((CheckBox)fragment.findViewById(R.id.thursday_checkbox)).isChecked()) {
            alarm.addWeekday(Weekday.THURSDAY);
        }
        if (((CheckBox)fragment.findViewById(R.id.friday_checkbox)).isChecked()) {
            alarm.addWeekday(Weekday.FRIDAY);
        }
        if (((CheckBox)fragment.findViewById(R.id.saturday_checkbox)).isChecked()) {
            alarm.addWeekday(Weekday.SATURDAY);
        }
        if (((CheckBox)fragment.findViewById(R.id.sunday_checkbox)).isChecked()) {
            alarm.addWeekday(Weekday.SUNDAY);
        }

        return alarm;
    }

    private boolean validateView() {
        EditText nameEditText = (EditText) getView().findViewById(R.id.nameEditText);
        EditText wakeupTimeEditText = (EditText) getView().findViewById(R.id.wakeupTimeEditText);
        EditText enlightenIntervalEditText = (EditText) getView().findViewById(R.id.enlightenIntervalEditText);
        EditText lightDurationEditText = (EditText) getView().findViewById(R.id.lightDurationEditText);

        TextInputLayout nameTextInputLayout = (TextInputLayout) getView().findViewById(R.id.name_input_layout);
        TextInputLayout wakeupTimeTextInputLayout = (TextInputLayout) getView().findViewById(R.id.wakeup_time_input_layout);
        TextInputLayout enlightenIntervalTextInputLayout = (TextInputLayout) getView().findViewById(R.id.enlighten_interval_input_layout);
        TextInputLayout lightDurationTextInputLayout = (TextInputLayout) getView().findViewById(R.id.light_duration_input_layout);

        nameTextInputLayout.setErrorEnabled(false);
        wakeupTimeTextInputLayout.setErrorEnabled(false);
        enlightenIntervalTextInputLayout.setErrorEnabled(false);
        lightDurationTextInputLayout.setErrorEnabled(false);

        if (nameEditText.getText().toString().isEmpty()) {
            nameTextInputLayout.setErrorEnabled(true);
            nameTextInputLayout.setError("Bitte geben Sie einen Weckernamen ein");
        }

        if (wakeupTimeEditText.getText().toString().isEmpty() ) {
            wakeupTimeTextInputLayout.setErrorEnabled(true);
            wakeupTimeTextInputLayout.setError("Bitte geben Sie eine gültige Zeit ein");
        } else {
            try {
                timeFormatter.parse(wakeupTimeEditText.getText().toString());
            } catch (ParseException e) {
                wakeupTimeTextInputLayout.setErrorEnabled(true);
                wakeupTimeTextInputLayout.setError("Bitte geben Sie eine gültige Zein ein");
            }
        }

        if (enlightenIntervalEditText.getText().toString().isEmpty()) {
            enlightenIntervalTextInputLayout.setErrorEnabled(true);
            enlightenIntervalTextInputLayout.setError("Bitte geben Sie ein Blendinterval ein");
        }

        if (lightDurationEditText.getText().toString().isEmpty()) {
            lightDurationTextInputLayout.setErrorEnabled(true);
            lightDurationTextInputLayout.setError("Bitte geben Sie eine Lichtdauer ein");
        }

        return !(nameTextInputLayout.isErrorEnabled() || wakeupTimeTextInputLayout.isErrorEnabled() || enlightenIntervalTextInputLayout.isErrorEnabled() || lightDurationTextInputLayout.isErrorEnabled() );
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_save).setVisible(true);
        menu.findItem(R.id.action_delete).setVisible(true);
        menu.findItem(R.id.action_settings).setVisible(false);

        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save : {
                if (validateView()) {
                    ((MainActivity) getActivity()).saveAlarm(getAlarm());
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        if (!(activity instanceof View.OnClickListener)) {
            throw new AssertionError("Activity must implement View.OnClickListener!");
        }
    }
}
