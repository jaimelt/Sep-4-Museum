package com.example.android_sep4.model;

import android.widget.RadioGroup;

import androidx.core.util.PatternsCompat;

public class Validator {

    public Validator (){

    }

    public int validateRegister(String email, String password, String repeatPassword) {
        if (email.isEmpty() || !PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()) {
            return 2;
        } else if (password.isEmpty() || password.length() < 6 || password.length() > 16) {
            return 3;
        } else if (!password.equals(repeatPassword)) {
            return 4;
        } else {
            return 1;
        }
    }

    public int validateFieldsArtwork(String name, String author, String description, String comment, String image, int minTempInt, int maxTempInt, int minLightInt, int maxLightInt, int minCO2Int, int maxCO2Int, int maxHumInt, int minHumInt) {
        if (name.isEmpty()) {
            return 1;
        } else if (author.isEmpty()) {
            return 2;
        } else if (description.isEmpty()) {
            return 3;
        } else if (comment.isEmpty()) {
            return 4;
        } else if (image.isEmpty()) {
            return 5;
        } else if (minTempInt <= 0) {
            return 6;
        } else if (maxTempInt <= 0) {
            return 7;
        } else if (minCO2Int <= 0) {
            return 8;
        } else if (maxCO2Int <= 0) {
            return 9;
        } else if (minHumInt <= 0) {
            return 10;
        } else if (maxHumInt <= 0) {
            return 11;
        } else if (minLightInt <= 0) {
            return 12;
        } else if (maxLightInt <= 0) {
            return 13;
        } else {
            return 14;
        }
    }

    public int validateLogin(String email, String password){
        if (email.isEmpty() || !PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()) {
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
