package com.example.android_sep4.model;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;
import java.util.Date;

public class Visitor {
    @SerializedName("FirstNameName")
    private String firstName;
    @SerializedName("LastName")
    private String lastName;
    @SerializedName("Gender")
    private String gender;
    @SerializedName("Nationality")
    private String nationality;
    @SerializedName("Age")
    private int age;
    @SerializedName("ReasonToVisit")
    private String reasonToVisit;

    public Visitor(String firstName, String lastName, String gender, String nationality, int age, String reasonToVisit) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.nationality = nationality;
        this.age = age;
        this.reasonToVisit = reasonToVisit;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getReasonToVisit() {
        return reasonToVisit;
    }

    public void setReasonToVisit(String reasonToVisit) {
        this.reasonToVisit = reasonToVisit;
    }

    @Override
    public String toString() {
        return "Visitor{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", nationality='" + nationality + '\'' +
                ", age=" + age +
                ", reasonToVisit='" + reasonToVisit + '\'' +
                '}';
    }
}
