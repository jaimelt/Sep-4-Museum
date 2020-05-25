package com.example.android_sep4.model;

import java.time.LocalDate;
import java.util.Date;

public class Visitor {
    private String firstName;
    private String lastName;
    private String gender;
    private String nationality;
    private int age;
    private String reasonToVisit;
    private LocalDate visitingDate;

    public Visitor(String firstName, String lastName, String gender, String nationality, int age, String reasonToVisit, LocalDate visitingDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.nationality = nationality;
        this.age = age;
        this.reasonToVisit = reasonToVisit;
        this.visitingDate = visitingDate;
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

    public LocalDate getVisitingDate() {
        return visitingDate;
    }

    public void setVisitingDate(LocalDate visitingDate) {
        this.visitingDate = visitingDate;
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
                ", visitingDate=" + visitingDate +
                '}';
    }
}
