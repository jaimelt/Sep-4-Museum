using System;
using System.Collections;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace REST_SEP4.Datamodel
{
    public class Artwork
    {
        [Key]
        public string Id { get; set; }
        public string Name { get; set; }
        public string description { get; set; }
        public string image { get; set; }
        public string type { get; set; }
        public string author { get; set; }
        
        public ICollection<ArtworkMeasurements> artworkMeasurements { get; set; }

      
    }
}