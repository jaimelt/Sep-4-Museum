using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace WebApplication1.Datamodel
{
    public class Artwork
    {
        [Key] public string Id { get; set; }
        public string Name { get; set; }
        public string description { get; set; }
        public string image { get; set; }
        
        public string type { get; set; }
        public string author { get; set; }
        public string location { get; set; }
        public ICollection<ArtworkMeasurement> artworkMeasurements { get; set; }
        
        //TODO: 
        // to add more data annotations when we know what are the setted conditions 
        // we can leave them as they are for the moment 
        //TODO: follow name convention 
    }
}