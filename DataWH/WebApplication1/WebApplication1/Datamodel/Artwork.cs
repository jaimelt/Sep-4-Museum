using System.ComponentModel.DataAnnotations;

namespace WebApplication1.Datamodel
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
        
        public ArtworkMeasurement ArtworkMeasurement { get; set; }
        
    }
}