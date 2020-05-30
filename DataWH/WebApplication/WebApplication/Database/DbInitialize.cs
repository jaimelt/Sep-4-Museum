using System.Collections.Generic;
using System.Linq;
using WebApplication.Database;
using WebApplication.Datamodel;

namespace WebApplication.Database
{
    public class DbInitialize
    {
        public static void Initialize(MuseumContext museumContext)
        {
            if (museumContext.Administrators.Any() && museumContext.Museum.Any()
                                                   && museumContext.Artworks.Any() && museumContext.Rooms.Any()
                                                    && museumContext.RoomMeasurements.Any())
            {
                return;
                
            }
            
            var rooms = new Room[]
          {
                new Room
                {
                    LocationCode= "A1", Description= "A room", CurrentCapacity= 100, TotalCapacity= 200, 
                    Light = 15, Temperature = 25, Humidity = 35, Co2 = 10,
                    ArtworkList = new List<Artwork>()
                },

                new Room
                {

                    LocationCode= "A2", Description= "A second room", CurrentCapacity= 100, TotalCapacity= 200,
                    Light = 17, Temperature = 27, Humidity = 37, Co2 = 12,
                    ArtworkList = new List<Artwork>()
                }
                ,
                new Room
                {

                    LocationCode= "A3", Description= "A second room", CurrentCapacity= 100, TotalCapacity= 200,
                    Light = 17, Temperature = 27, Humidity = 37, Co2 = 12,
                    ArtworkList = new List<Artwork>()
                }
                ,
                new Room
                {

                    LocationCode= "B1", Description= "A second room", CurrentCapacity= 100, TotalCapacity= 200,
                    Light = 17, Temperature = 27, Humidity = 37, Co2 = 12,
                    ArtworkList = new List<Artwork>()
                }
                
                ,

                new Room
                {

                    LocationCode= "B2", Description= "A second room", CurrentCapacity= 100, TotalCapacity= 200,
                    Light = 0, Temperature = 27, Humidity = 37, Co2 = 12,
                    ArtworkList = new List<Artwork>()

                },

                new Room
                {

                    LocationCode= "B3", Description= "A second room", CurrentCapacity= 100, TotalCapacity= 200,
                    Light = 0, Temperature = 0, Humidity = 0, Co2 = 0,
                    ArtworkList = new List<Artwork>()

                },

                new Room
                {

                    LocationCode= "B4", Description= "A second room", CurrentCapacity= 100, TotalCapacity= 200,
                    Light = 0, Temperature = 0, Humidity = 0, Co2 = 0,
                    ArtworkList = new List<Artwork>()

                },

                new Room
                {

                    LocationCode= "Storage", Description= "A second room", CurrentCapacity= 100, TotalCapacity= 200,
                    Light = 0, Temperature = 0, Humidity = 0, Co2 = 0,
                    ArtworkList = new List<Artwork>()

                }
            };

            foreach (var r in rooms)
            {
                museumContext.Rooms.Add(r);
                //museumContext.Rooms.Update(r);
            }

            museumContext.SaveChangesAsync();
           

        }
    }
 }