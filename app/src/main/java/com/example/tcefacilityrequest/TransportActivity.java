package com.example.tcefacilityrequest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;

import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TransportActivity extends AppCompatActivity {
    private TextInputEditText requestDate, pickupTimeEditText, dropTimeEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String currentDate = dateFormat.format(calendar.getTime());

        pickupTimeEditText = findViewById(R.id.editTextPickupTime);
        dropTimeEditText = findViewById(R.id.editTextDropTime);
        requestDate = findViewById(R.id.requestDate);

        requestDate.setText(currentDate);
    }

    public void showPickUpTimePicker(View view) {
        // Get current time
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new TimePickerDialog instance
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        // Format the selected time with AM/PM
                        String am_pm = (hourOfDay < 12) ? "AM" : "PM";
                        int hour_of_12_hour_format = (hourOfDay == 0 || hourOfDay == 12) ? 12 : hourOfDay % 12;

                        // Ensure minute formatting with leading zero
                        String minuteFormatted = String.format(Locale.getDefault(), "%02d", minute);

                        // Set the selected time to the EditText with AM/PM
                        String selectedTime = String.format(Locale.getDefault(), "%d:%s %s", hour_of_12_hour_format, minuteFormatted, am_pm);
                        pickupTimeEditText.setText(selectedTime);
                    }
                }, hour, minute, false); // The last parameter indicates if the time is in 24-hour format or not

        // Show the time picker dialog
        timePickerDialog.show();
    }

    public void showDropTimePicker(View view) {
        // Get current time
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new TimePickerDialog instance
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        // Format the selected time with AM/PM
                        String am_pm = (hourOfDay < 12) ? "AM" : "PM";
                        int hour_of_12_hour_format = (hourOfDay == 0 || hourOfDay == 12) ? 12 : hourOfDay % 12;

                        // Ensure minute formatting with leading zero
                        String minuteFormatted = String.format(Locale.getDefault(), "%02d", minute);

                        // Set the selected time to the EditText with AM/PM
                        String selectedTime = String.format(Locale.getDefault(), "%d:%s %s", hour_of_12_hour_format, minuteFormatted, am_pm);
                        dropTimeEditText.setText(selectedTime);
                    }
                }, hour, minute, false); // The last parameter indicates if the time is in 24-hour format or not

        // Show the time picker dialog
        timePickerDialog.show();
    }

    public void cancelClicked(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("TCE Facility Request");
        builder.setMessage("Are You Sure You want to cancel");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Navigate to previous page or perform any action you want
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Negative button clicked
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}