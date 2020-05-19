using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace WebApplication1.Datamodel
{
    public class Room
    {
        [Key] public string LocationCode { get; set;}
        public string Description { get; set; }
        public int CurrentCapacity { get; set; }
        public int TotalCapacity { get; set; }
        
        // required measurements of the room 
        
        public int Light { get; set; }
        public int Temperature { get; set; }
        public int Humidity { get; set; }
        public int Co2 { get; set; }
        
        // live room measurements  & artworks 
        
        public ICollection<Artwork> ArtworkList { get; set; }
        public RoomMeasurement LiveRoomMeasurements { get; set; }
    }
}