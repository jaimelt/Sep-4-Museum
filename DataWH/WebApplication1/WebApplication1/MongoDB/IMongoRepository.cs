using System;
using System.Collections;
using System.Collections.Generic;
using WebApplication1.Datamodel;

namespace WebApplication1.MongoDB
{
    public interface IMongoRepository
    {
        RoomMeasurement loadActualMeasurement(int roomNo);
        ICollection<MongoMeasurement> LoadAllMeasurements();
        ICollection<MongoMeasurement> LoadMeasurementsFromDate(DateTime dateTime);

        MongoMeasurement LoadLastRoomMeasurement(int room_no);
    }
}