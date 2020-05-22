using System.Collections.Generic;

namespace WebApplication1.Datamodel
{
    public class RoomListCopy
    {
       

        public RoomListCopy()
        {
            rooms = new List<RoomCopy>();
        }
        
        public List<RoomCopy> rooms { get; set; }
    }
}