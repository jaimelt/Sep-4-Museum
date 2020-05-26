package com.example.android_sep4.model;

import java.util.ArrayList;

public class Visitors {
    private ArrayList<Visitor> visitors;

    public Visitors(ArrayList<Visitor> visitors) {
        this.visitors = visitors;
    }

    public ArrayList<Visitor> getVisitors() {
        return visitors;
    }

    public void setVisitors(ArrayList<Visitor> visitors) {
        this.visitors = visitors;
    }

    @Override
    public String toString() {
        return "Visitors{" +
                "visitors=" + visitors +
                '}';
    }
}
