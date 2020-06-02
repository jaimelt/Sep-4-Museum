package com.example.android_sep4.model;

import android.widget.RadioGroup;

public class Validator {

    public Validator (){

    }

    public int validateRegister(String email, String password, String repeatPassword) {
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return 2;
        } else if (password.isEmpty() || password.length() < 6 || password.length() > 16) {
            return 3;
        } else if (!password.equals(repeatPassword)) {
            return 4;
        } else {
            return 1;
        }
    }

    public int validateFieldsArtwork(String name, String author, RadioGroup type, String description, String comment, String image, int minTempInt, int maxTempInt, int minLightInt, int maxLightInt, int minCO2Int, int maxCO2Int, int maxHumInt, int minHumInt) {
        if (name.isEmpty()) {
            return 1;
        } else if (author.isEmpty()) {
            return 2;
        } else if (!validate(type)) {
            return 3;
        } else if (description.isEmpty()) {
            return 4;
        } else if (comment.isEmpty()) {
            return 5;
        } else if (image.isEmpty()) {
            return 6;
        } else if (minTempInt <= 0) {
            return 7;
        } else if (maxTempInt <= 0) {
            return 8;
        } else if (minCO2Int <= 0) {
            return 9;
        } else if (maxCO2Int <= 0) {
            return 10;
        } else if (minHumInt <= 0) {
            return 11;
        } else if (maxHumInt <= 0) {
            return 12;
        } else if (minLightInt <= 0) {
            return 13;
        } else if (maxLightInt <= 0) {
            return 14;
        } else {
            return 15;
        }
    }

    private boolean validate(RadioGroup radioGroup) {
        if (radioGroup.getCheckedRadioButtonId() == -1)
        {
            // no radio buttons are checked
            return false;
        }
        else
        {
            // one of the radio buttons is checked
            return true;
        }
    }
    public int validateLogin(String email, String password){
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return 2;
        }
        else if (password.isEmpty() || password.length() < 6 || password.length() > 16) {
            return 3;
        }
        else {
            return 1;
        }
    }

    public int validateChangePassword(String password, String repeatPassword)
    {
        if (password.isEmpty() || password.length() < 6 || password.length() > 16) {
            return 2;
        } else if (!password.equals(repeatPassword)) {
            return 3;
        } else {
            return 1;
        }
    }
}
