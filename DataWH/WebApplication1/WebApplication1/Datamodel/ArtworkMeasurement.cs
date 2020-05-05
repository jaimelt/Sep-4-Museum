using System.ComponentModel.DataAnnotations;

namespace WebApplication1.Datamodel
{
    public class ArtworkMeasurement
    {
        [Key] public int id { get; set; }
        public int minLight { get; set; }
        public int minCo2 { get; set; }
        public int minTemp { get; set; }
        
        public int maxLight { get; set; }
        public int maxCo2 { get; set; }
        public int maxTemp { get; set; }
    }
}