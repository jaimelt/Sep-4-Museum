using System;
using System.Collections;
using System.Collections.Generic;
using WebApplication1.Datamodel;

namespace WebApplication1.MongoDB
{
    public interface IMongoRepository
    {
        RoomMeasurement loadActualMeasurement(int roomNo);
        ICollection<RoomMeasurement> LoadAllMeasurements();
        ICollection<RoomMeasurement> LoadMeasurementsFromDate(DateTime dateTime);

    }
}