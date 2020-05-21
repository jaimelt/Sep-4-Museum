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
                                                    && museumContext.RoomMeasurements.Any())
            {
                return; // seeding 
            }
            
            
            /*var artworks = new[]
            {
                new Artwork
                {
                    Id = 1, Author = "fake", Description = "none", Image = "none", Location = "100",
                    Name = "this is not an art", ArtworkMeasurement = new ArtworkMeasurement()
                },

                new Artwork
                {
                    Id = 2, Author = "notfake", Description = "adescription", Image = "aimage", Location = "200",
                    Name = "this is a piece of art",ArtworkMeasurement = new ArtworkMeasurement
                    {
                        Id = 1, MaxCo2 = 20, MaxHumidity = 20, MaxLight = 20, MaxTemperature = 20, 
                        MinCo2 = 20, MinHumidity = 20, MinLight = 20, MinTemperature = 20
                        
                    }
                }, 
                
                new Artwork
                {
                    Id = 3, Author = "florin", Description = "too many of a description", Image = "aimage", Location = "300",
                    Name = "this is a piece of art",ArtworkMeasurement = new ArtworkMeasurement
                    {
                        Id = 2, MaxCo2 = 20, MaxHumidity = 20, MaxLight = 20, MaxTemperature = 20, 
                        MinCo2 = 20, MinHumidity = 20, MinLight = 20, MinTemperature = 20
                        
                    }
                }, 
                
                new Artwork
                {
                    Id = 4, Author = "fake", Description = "none", Image = "none", Location = "100",
                    Name = "this is not an art", ArtworkMeasurement = new ArtworkMeasurement()
                },
            };



            foreach (var c in artworks)
            {
                museumContext.Artworks.Add(c);
             //  museumContext.Artworks.Update(c);
               // after adding, use update. Otherwise there will be issues in overwritting 
            }*/

            

            var rooms = new Room[]
          {
                new Room
                {
                    
                    LocationCode= "A100", Description= "A room", CurrentCapacity= 100, TotalCapacity= 200, 
                    Light = 15, Temperature = 25, Humidity = 35, Co2 = 10,
                    ArtworkList = new List<Artwork>(),
                    LiveRoomMeasurements = new RoomMeasurement
                    {
                        Id = 1, Light = 10, Temperature = 20, Humidity = 30, Co2 = 5
                    }
                    
                },

                new Room
                {

                    LocationCode= "A101", Description= "A second room", CurrentCapacity= 100, TotalCapacity= 200,
                    Light = 17, Temperature = 27, Humidity = 37, Co2 = 12,
                    ArtworkList = new List<Artwork>(),
                    LiveRoomMeasurements = new RoomMeasurement()

                }
            };

            foreach (var r in rooms)
            {
                //museumContext.Rooms.Add(r);
                museumContext.Rooms.Update(r);
                // after adding, use update. Otherwise there will be issues in overwritting 
            }

            museumContext.SaveChangesAsync();
           

        }
    }
 }