using System.Collections.Generic;

namespace WebApplication.Datamodel
{
    public class ArtworkList
    {
        public ArtworkList()
        {
            artworks= new List<Artwork>();
        }
        
        public List<Artwork> artworks { get; set; }
    }
}