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

        public void mockMeasurementsExceptRoomNo(string roomNo)
        {
          
            RoomMeasurement a1 = new RoomMeasurement(1,200,25,75,400,"A1");
            RoomMeasurement a2 = new RoomMeasurement(2,300,8,50,300,"A2");
            RoomMeasurement a3 = new RoomMeasurement(3,220,23,60,200,"A3");
            RoomMeasurement b1 = new RoomMeasurement(4,320,26,69,210,"B1");
            RoomMeasurement b2 = new RoomMeasurement(5,120,24,53,230,"B2");
            RoomMeasurement b3 = new RoomMeasurement(6,20,20,33,225,"B3");
            RoomMeasurement b4 = new RoomMeasurement(7,225,16,22,226,"B4");

            if (!roomNo.Equals("A1"))
            {
                addRoomMeasurement(a1);
            }
            if (!roomNo.Equals("A2"))
            {
                addRoomMeasurement(a2);
            }
            if (!roomNo.Equals("A3"))
            {
                addRoomMeasurement(a3);
            }
            if (!roomNo.Equals("B1"))
            {
                addRoomMeasurement(b1);
            }
            if (!roomNo.Equals("B2"))
            {
               addRoomMeasurement(b2);
            }
            if (!roomNo.Equals("B3"))
            {
                addRoomMeasurement(b3);
            }
            if (!roomNo.Equals("B4"))
            {
                addRoomMeasurement(b4);
            }

            

        }
    }
}