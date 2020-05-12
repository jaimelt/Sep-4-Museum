using System.ComponentModel.DataAnnotations;

namespace WebApplication1.Datamodel
{
    public class RoomMeasurement
    {
        [Key] public int Id { get; set; }
        public int Light { get; set; }
        public int Temperature { get; set; }
        public int Humidity { get; set; }
        public int Co2 { get; set; }
    }
}