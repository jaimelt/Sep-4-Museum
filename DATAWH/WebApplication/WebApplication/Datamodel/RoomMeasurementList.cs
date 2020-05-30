using System;
using System.Collections.Generic;

namespace WebApplication.Datamodel
{
    public class RoomMeasurementList
    {
        public RoomMeasurementList()
        {
            Measurements = new List<RoomMeasurement>();
        }

        public List<RoomMeasurement> Measurements { get; set; }

        public RoomMeasurement getRoomMeasurementByRoomNo(string roomNo)
        {
            foreach (var roomMeasurement in Measurements)
            {
                if (roomMeasurement.roomNo.Equals(roomNo))
                    return roomMeasurement;
            }

            Console.WriteLine("RETURNING NULL");
            return null;
        }

        public void addRoomMeasurement(RoomMeasurement roomMeasurement)
        {
            Measurements.Add(roomMeasurement);
            Console.WriteLine(roomMeasurement.roomNo);
        }
    }
}