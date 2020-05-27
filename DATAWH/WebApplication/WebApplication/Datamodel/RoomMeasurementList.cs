using System.Collections.Generic;

namespace WebApplication.Datamodel
{
    public class RoomMeasurementList
    {
        public RoomMeasurementList()
        {
        }

        public List<RoomMeasurement> Measurements { get; set; }

        public RoomMeasurement getRoomMeasurementByRoomNo(string roomNo)
        {
            foreach (var roomMeasurement in Measurements)
            {
                if (roomMeasurement.roomNo.Equals(roomNo))
                    return roomMeasurement;
            }

            return null;
        }

        public void addRoomMeasurement(RoomMeasurement roomMeasurement)
        {
            Measurements.Add(roomMeasurement);
        }
    }
}