using System.ComponentModel.DataAnnotations;

namespace WebApplication1.Datamodel
{
    public class RoomMeasurement
    {
        [Key] public int Id { get; set; }
        public decimal Light { get; set; }
        public decimal Temperature { get; set; }
        public decimal Humidity { get; set; }
        public decimal Co2 { get; set; }
    }
}