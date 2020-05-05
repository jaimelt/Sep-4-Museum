using System.ComponentModel.DataAnnotations;

namespace WebApplication1.Datamodel
{
    public class RoomMeasurement
    {
        [Key] public int id { get; set; }
        public int light { get; set; }
        public int temp { get; set; }
        public int humidity { get; set; }
        public int Co2 { get; set; }
        
   
    }
}