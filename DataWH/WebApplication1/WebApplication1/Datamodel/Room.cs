using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace WebApplication1.Datamodel
{
    public class Room
    {
        [Key] public string locationCode { get; set; }
        public string description { get; set; }
        public int currentCapacity { get; set; }
        public int totalCapacity { get; set; }
        public ICollection<RoomMeasurement> optimalMeasurements { get; set; }
        public ICollection<RoomMeasurement> measurementConditions { get; set; }
    }
}