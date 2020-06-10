using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace WebApplication.Datamodel
{
    public class Room
    {
        [Key] public string LocationCode { get; set;}
       
        [StringLength(1000)]public string Description { get; set; }
        [Range(0, 200)]public int CurrentCapacity { get; set; }
        [Range(0, 200)] public int TotalCapacity { get; set; }
        
        public int Light { get; set; }
        public int Temperature { get; set; }
        public int Humidity { get; set; }
        public int Co2 { get; set; }
        
        public ICollection<Artwork> ArtworkList { get; set; }
        [NotMapped]  
        public RoomMeasurement measurements { get; set; }
   
        
        
        
        
        
    }
}