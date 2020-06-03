using System.Collections.Generic;

namespace WebApplication.Datamodel
{
    public class RoomList
    {
        public RoomList() { }
        public IEnumerable<Room> rooms { get; set; }



        public void addMeasurements(RoomMeasurementList list)
        {
            foreach (var m in list.Measurements)
            {

                foreach (var room in rooms)
                {
                    if (m.roomNo.Equals(room.LocationCode))
                    {
                        room.measurements = m;
                    }
                }
            }
        }
    }
    }
