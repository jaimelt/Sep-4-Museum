using System.Collections.Generic;
using System.Linq;
using WebApplication1.Datamodel;

namespace WebApplication1.Database
{
    public class DbInitialize
    {
        public static void Initialize(MuseumContext museumContext)
        {
            if (museumContext.Administrators.Any() && museumContext.Museum.Any()
                                                   && museumContext.Artworks.Any() && museumContext.Rooms.Any()
                                                   && museumContext.ArtworkMeasurements.Any())
            {
                return;
            }

            ArtworkMeasurement artworkMeasurements = new ArtworkMeasurement();
            
            var artworks = new[]
            {
                new Artwork
                {
                    Id = 1, Author = "fake", Description = "none", Image = "none", Location = "100",
                    Name = "this is not an art",
                },

                new Artwork
                {
                    Id = 2, Author = "notfake", Description = "adescription", Image = "aimage", Location = "200",
                    Name = "this is a piece of art",
                }
            };



            foreach (var c in artworks)
            {
                //museumContext.Artworks.Add(c);
               museumContext.Artworks.Update(c);
               // after adding, use update. Otherwise there will be issues in overwritting 
            }

            var rooms = new Room[]
          {
                new Room
                {
                    
                    LocationCode= "A100", Description= "A room", CurrentCapacity= 100, TotalCapacity= 200
                    
                },

                new Room
                {

                    LocationCode= "A101", Description= "A second room", CurrentCapacity= 100, TotalCapacity= 200

                }
            };

            foreach (var r in rooms)
            {
                museumContext.Rooms.Add(r);
                //museumContext.Rooms.Update(r);
                // after adding, use update. Otherwise there will be issues in overwritting 
            }

            museumContext.SaveChangesAsync();
           

        }
    }
}