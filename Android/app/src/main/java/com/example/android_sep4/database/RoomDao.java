//package com.example.android_sep4.database;
//
//import androidx.lifecycle.LiveData;
//import androidx.room.Dao;
//import androidx.room.Insert;
//import androidx.room.Query;
//import androidx.room.Update;
//
//import com.example.android_sep4.model.Room;
//import com.example.android_sep4.model.RoomMeasurements;
//
//import java.util.List;
//
//
//@Dao
//public interface RoomDao {
//    @Insert
//    void insert(Room room);
//
//    @Insert
//    void insert(RoomMeasurements roomMeasurements);
//
//    @Update
//    void update(RoomMeasurements roomMeasurements);
//
//    @Query("SELECT * FROM Room")
//    LiveData<List<RoomWithEverything>> getAllRooms();
//
//    @Query("SELECT * FROM Room WHERE location_code = :locationCode")
//    RoomWithEverything getRoom(String locationCode);
//
//}
