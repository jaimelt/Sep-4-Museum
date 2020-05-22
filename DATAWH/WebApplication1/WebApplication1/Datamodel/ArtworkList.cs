using System.Collections.Generic;

namespace WebApplication1.Datamodel
{
    public class ArtworkList
    {
        public List<Artwork> artworks { get; set; }

        public ArtworkList()
        {
            artworks = new List<Artwork>();
        }
        
     
    }
}