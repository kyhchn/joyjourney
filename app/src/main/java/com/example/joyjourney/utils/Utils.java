package com.example.joyjourney.utils;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.Patterns;
import android.view.View;
import android.widget.DatePicker;

import com.google.android.material.textfield.TextInputEditText;

public class Utils {
    public static boolean isValidEmail(CharSequence email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static void showDatePickerDialog(View view, Context context) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                context,
                (DatePicker view1, int year, int month, int dayOfMonth) -> {
                    String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                    ((TextInputEditText) view).setText(selectedDate);
                },
                // Set initial date if needed
                1960, 0, 1
        );
        datePickerDialog.show();
    }
}
