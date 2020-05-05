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
                                                   && museumContext.OptimalMeasurements.Any() &&
                                                   museumContext.MeasurementConditions.Any()
                                                   && museumContext.ArtworkMeasurements.Any())
            {
                return;
            }

            var artworks = new[]
            {
                new Artwork
                {
                    Id = "1", author = "fake", description = "none", image = "none", location = "100",
                    Name = "this is not an art",
                    artworkMeasurements = new List<ArtworkMeasurement>
                    {
                        new ArtworkMeasurement
                            {id = 2, minCo2 = 10, minLight = 10, minTemp = 10, maxCo2 = 20, maxLight = 20, maxTemp = 20}
                    }
                },

                new Artwork
                {
                    Id = "2", author = "notfake", description = "adescription", image = "aimage", location = "200",
                    Name = "this is a piece of art",
                    artworkMeasurements = new List<ArtworkMeasurement>
                    {
                        new ArtworkMeasurement
                            {id = 1, minCo2 = 10, minLight = 10, minTemp = 10, maxCo2 = 20, maxLight = 20, maxTemp = 20}
                    }
                }
            };

            foreach (var c in artworks)
            {
               // museumContext.Artworks.Add(c);
               museumContext.Artworks.Update(c);
               // after adding, use update. Otherwise there will be issues in overwritting 
            }
            museumContext.SaveChangesAsync();
           

        }
    }
}