using System.ComponentModel.DataAnnotations;

namespace WebApplication.Datamodel
{
    public class Artwork
    {
        [Key] public int Id { get; set; }
        public string Name { get; set; }
        public string Description { get; set; }
        public string Image { get; set; }
        public string Type { get; set; }
        public string Author { get; set; }
        public string Location { get; set; }
        
        public int MinLight { get; set; }
        public int MaxLight { get; set; }
        
        public int MinCo2 { get; set; }
        public int MaxCo2 { get; set; }
        
        public int MinTemperature { get; set; }
        public int MaxTemperature { get; set; }
        
        public int MinHumidity { get; set; }
        public int MaxHumidity { get; set; }
        
        public int ArtworkPosition { get; set; }
        public string Comment { get; set; }
        
    }
}