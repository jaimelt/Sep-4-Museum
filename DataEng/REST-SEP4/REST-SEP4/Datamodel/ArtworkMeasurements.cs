using System.ComponentModel.DataAnnotations;

namespace REST_SEP4.Datamodel
{
    public class ArtworkMeasurements
    {
        [Key]
        public int Id { get; set; }
        public int maxLight { get; set; }
        public int minLight { get; set; }
        public int maxTemp { get; set; }
        public int minTemp { get; set; }
        public int maxHumidity { get; set; }
        public int minHumidity { get; set; }
        public int maxCo2 { get; set; }
        public int minCo2 { get; set; }

       
    }
}
