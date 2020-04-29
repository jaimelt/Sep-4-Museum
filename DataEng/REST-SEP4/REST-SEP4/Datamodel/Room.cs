using System.Collections;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace REST_SEP4.Datamodel
{
    public class Room
    {
        [Key] public int Id { get; set; }
        public string locationCode { get; set; }
        public string description { get; set; }
        public string roomType { get; set; }
        public int currentCapacity { get; set; }
        public int totalCapacity { get; set; }

       public ICollection<Measurements> measurementList { get; set; }
       public ICollection<Artwork> artworkList { get; set; }

     
    }
}