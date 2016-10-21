package ch.hsr.sunriseclock.sunriseclock.helper;

import android.app.TimePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.EditText;

import java.util.Calendar;

public class CustomTimePicker implements View.OnFocusChangeListener, TimePickerDialog.OnTimeSetListener {

    private Context context;
    private EditText editText;

    public CustomTimePicker(EditText editText, Context context){
        this.editText = editText;
        this.editText.setOnFocusChangeListener(this);
        this.context = context;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if(hasFocus){
            Calendar calendar = Calendar.getInstance();

            if (editText != null) {
                try {
                    String[] times = editText.getText().toString().split(":");
                    calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(times[0]) - 1);
                    calendar.set(Calendar.MINUTE, Integer.parseInt(times[1]));
                } catch (Exception e) {

                }
            }

            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);
            new TimePickerDialog(context, this, hour, minute, true).show();
        }
    }

    @Override
    public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
        this.editText.setText( hourOfDay + ":" + minute);
    }

}