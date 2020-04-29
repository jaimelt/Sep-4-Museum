using System.ComponentModel.DataAnnotations;

namespace REST_SEP4.Datamodel
{
    public class Measurements
    {
        [Key] public int Id { get; set; }
        public int temperature { get; set; }
        public int humidity { get; set; }
        public int co2 { get; set; }
        public int light { get; set; }

      
    }
}