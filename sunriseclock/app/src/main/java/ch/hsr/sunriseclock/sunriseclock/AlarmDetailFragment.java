package ch.hsr.sunriseclock.sunriseclock;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by michi on 19.10.16.
 */

public class AlarmDetailFragment extends Fragment implements View.OnClickListener {

    private DateFormat formatter = new SimpleDateFormat("hh:mm");
;

    public interface OnAlarmSavedListener {
        void onAlarmSaved(Alarm alarm);
    }

    @Override
    public void onClick(View v) {
        Alarm alarm = saveData();
        ((MainActivity) getActivity()).onItemSelected(alarm);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.alarm_detail_fragment, container, false);

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Alarm alarm = (Alarm) getArguments().getSerializable(Constants.CURRENT_ALARM);
        fillData(root, alarm);

        Button saveButton = (Button) root.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this);

        return root;
    }

    private void fillData(View fragment, Alarm alarm) {
        if (fragment != null && alarm != null) {
            ((EditText) fragment.findViewById(R.id.wakeupTimeEditText)).setText(formatter.format(alarm.getWakeupTime()));
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

    private Alarm saveData() {
        View fragment  =  getView();

        Alarm alarm = new Alarm();

        Date wakeUpTime = null;
        try {
            wakeUpTime = formatter.parse( ((EditText) fragment.findViewById(R.id.wakeupTimeEditText)).getText().toString() );
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }

        String enlightenInterval = ((EditText) fragment.findViewById(R.id.enlightenIntervalEditText)).getText().toString();
        alarm.setEnlightenInterval(Integer.parseInt(enlightenInterval));

        String lightInterval = ((EditText) fragment.findViewById(R.id.lightDurationEditText)).getText().toString();
        alarm.setEnlightenInterval(Integer.parseInt(lightInterval));

        alarm.clearWeekday();

        if (((CheckBox)fragment.findViewById(R.id.mondayCheckbox)).isChecked()) {
            alarm.addWeekday(Weekday.MONDAY);
        }
        if (((CheckBox)fragment.findViewById(R.id.tuesdayCheckbox)).isChecked()) {
            alarm.addWeekday(Weekday.TUESDAY);
        }
        if (((CheckBox)fragment.findViewById(R.id.wednesdayCheckbox)).isChecked()) {
            alarm.addWeekday(Weekday.WEDNESDAY);
        }
        if (((CheckBox)fragment.findViewById(R.id.thursdayCheckbox)).isChecked()) {
            alarm.addWeekday(Weekday.THURSDAY);
        }
        if (((CheckBox)fragment.findViewById(R.id.fridayCheckbox)).isChecked()) {
            alarm.addWeekday(Weekday.FRIDAY);
        }
        if (((CheckBox)fragment.findViewById(R.id.saturdayCheckbox)).isChecked()) {
            alarm.addWeekday(Weekday.SATURDAY);
        }
        if (((CheckBox)fragment.findViewById(R.id.sundayCheckbox)).isChecked()) {
            alarm.addWeekday(Weekday.SUNDAY);
        }

        return alarm;
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        if (!(activity instanceof View.OnClickListener)) {
            throw new AssertionError("Activity must implement View.OnClickListener!");
        }
    }
}
