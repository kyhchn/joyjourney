package com.example.joyjourney.utils;

import android.util.Patterns;

public class Utils {
    public static boolean isValidEmail(CharSequence email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
