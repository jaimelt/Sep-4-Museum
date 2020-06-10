using System;
using System.Collections;
using System.Collections.Generic;
using WebApplication.Datamodel;


namespace WebApplication.MongoDB
{
    public interface IMongoRepository
    {
  
        RoomMeasurementList LoadAllMeasurements();
        ICollection<MongoMeasurement> LoadMeasurementsFromDate(DateTime dateTime);

        MongoMeasurement LoadLastRoomMeasurement(int room_no);
        RoomMeasurementList LoadAllRoomLastMeasurements();
        int modifyToMongoRoomID(string room_no);
    }
}