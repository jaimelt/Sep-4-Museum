package com.example.android_sep4.model;

import java.util.ArrayList;

public class Museum {
    private ArrayList<Room> roomList;
    private ArrayList<Administrator> administratorList;

    public Museum(ArrayList<Room> roomList, ArrayList<Administrator> administratorList)
    {
        this.roomList = roomList;
        this.administratorList = administratorList;
        //TODO: OR administratorList = new ArrayList<Administrator>
    }

    public void addAdministrator(Administrator administrator)
    {
        administratorList.add(administrator);
    }

    public void deleteAdministrator(Administrator administrator)
    {
        administratorList.remove(administrator);
    }

    public void deleteAdministratorByUsername(String username)
    {
        for(Administrator administrator: administratorList)
        {
            if(administrator.getUsername().equals(username))
            {
                administratorList.remove(administrator);
            }
        }
    }
}
