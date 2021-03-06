package com.example.android_sep4.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class RoomTest {
    private Room room;

    @Before
    public void setUp() throws Exception {
        room = new Room();
        room.setTemperature(0);
    }

    @Test
    public void changeCelsiusToFahrenheit() {
        Assert.assertEquals(0, room.getTemperature(), 0);
        room.changeCelsiusToFahrenheit();
        Assert.assertEquals(32.0, room.getTemperature(), 0);
    }

    @After
    public void tearDown() throws Exception {
        room = new Room();
    }
}