//package com.example.android_sep4.database;
//
//import androidx.room.Embedded;
//import androidx.room.Relation;
//
//import com.example.android_sep4.model.Artwork;
//import com.example.android_sep4.model.Room;
//import com.example.android_sep4.model.RoomMeasurements;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class RoomWithEverything {
//    @Embedded
//    public Room room;
//
//    @Relation(parentColumn = "location_code", entityColumn = "id", entity = RoomMeasurements.class)
//    public RoomMeasurements roomMeasurements;
//
//    @Relation(parentColumn = "location_code", entityColumn = "room_code", entity = Artwork.class)
//    public List<Artwork> artworkList;
//}
