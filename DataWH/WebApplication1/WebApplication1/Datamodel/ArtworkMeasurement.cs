using System.ComponentModel.DataAnnotations;

namespace WebApplication1.Datamodel
{
    public class ArtworkMeasurement
    {
        [Key] public int Id { get; set; }
        public int MinLight { get; set; }
        public int MaxLight { get; set; }
        
        public int MinCo2 { get; set; }
        public int MaxCo2 { get; set; }
        
        public int MinTemperature { get; set; }
        public int MaxTemperature { get; set; }
        
        public int MinHumidity { get; set; }
        public int MaxHumidity { get; set; }
    }
}