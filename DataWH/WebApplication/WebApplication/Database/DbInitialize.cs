using System.Collections.Generic;
using System.Linq;
using WebApplication.Database;
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
                return;
                
            }
            
        




            

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