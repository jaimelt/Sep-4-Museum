package com.example.android_sep4.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;

@RunWith(JUnit4.class)
public class RoomsTest {
    private Rooms rooms;

    @Before
    public void setUp() throws Exception {
        ArrayList<Room> roomArrayList = new ArrayList<>();
        Room room = new Room();
        room.setTemperature(0);
        Room room1 = new Room();
        room1.setTemperature(-18);
        Room room2 = new Room();
        room2.setTemperature(60);
        Room room3 = new Room();
        room3.setTemperature(-40);
        roomArrayList.add(room);
        roomArrayList.add(room1);
        roomArrayList.add(room2);
        roomArrayList.add(room3);
        rooms = new Rooms(roomArrayList);
    }

    @Test
    public void changeCelsiusToFahrenheit() {
        rooms.changeCelsiusToFahrenheit();
        Room room = rooms.getRooms().get(0);
        Room room1 = rooms.getRooms().get(1);
        Room room2 = rooms.getRooms().get(2);
        Room room3 = rooms.getRooms().get(3);
        Assert.assertEquals(4, rooms.getRooms().size(), 0);
        Assert.assertEquals(32.0, room.getTemperature(), 0);
        Assert.assertEquals(-0.4, room1.getTemperature(), 0.1);
        Assert.assertEquals(140, room2.getTemperature(), 0);
        Assert.assertEquals(-40, room3.getTemperature(), 0);
    }

    @After
    public void tearDown() throws Exception {
        rooms = new Rooms(new ArrayList<>());
    }

}