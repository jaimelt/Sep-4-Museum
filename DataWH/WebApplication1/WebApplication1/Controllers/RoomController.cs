using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using MongoDB.Bson;
using WebApplication1.Database.Repositories.ArtworkRep;
using WebApplication1.Database.Repositories.RoomRep;
using WebApplication1.Datamodel;
using WebApplication1.MongoDB;

namespace WebApplication1.Controllers
{
    [Route("rooms")]
    [ApiController]
    public class RoomController : ControllerBase
    {
        //private readonly MuseumContext roomRepository;
        private readonly RoomRepository roomRepository;
        private readonly IMongoRepository _mongoRepository;
        private readonly ArtworkRepository _artworkRepository;


        public RoomController(RoomRepository roomRepository, MongoRepository mongoRepository,ArtworkRepository artworkRepository)
        {
            this.roomRepository = roomRepository;
            _mongoRepository = mongoRepository;
            _artworkRepository = artworkRepository;
        }

        // GET: api/Rooms
        [HttpGet("getall")]
        public RoomListCopy GetRooms()
        {
            RoomListCopy copylist= new RoomListCopy();
            RoomList roomList = new RoomList();
            roomList.rooms = roomRepository.getAllRoomsAsync().Result.ToList();
            RoomCopy roomCopy = new RoomCopy();
            RoomCopy roomCopy2 = new RoomCopy();
            
            //A bloody hard-coded room
            
            roomCopy.Co2 = roomList.rooms[0].Co2;
            roomCopy.LocationCode = roomList.rooms[0].LocationCode;
            roomCopy.Description = roomList.rooms[0].Description;
            roomCopy.Humidity = roomList.rooms[0].Humidity;
            roomCopy.Light = roomList.rooms[0].Light;
            roomCopy.Temperature = roomList.rooms[0].Temperature;
            roomCopy.CurrentCapacity = roomList.rooms[0].CurrentCapacity;
            roomCopy.TotalCapacity = roomList.rooms[0].TotalCapacity;
            
            //Another hard-coded room
            
            roomCopy2.Co2 = roomList.rooms[1].Co2;
            roomCopy2.LocationCode = roomList.rooms[1].LocationCode;
            roomCopy2.Description = roomList.rooms[1].Description;
            roomCopy2.Humidity = roomList.rooms[1].Humidity;
            roomCopy2.Light = roomList.rooms[1].Light;
            roomCopy2.Temperature = roomList.rooms[1].Temperature;
            roomCopy2.CurrentCapacity = roomList.rooms[1].CurrentCapacity;
            roomCopy2.TotalCapacity = roomList.rooms[1].TotalCapacity;
           
            ArtworkList al1 = new ArtworkList();
            ArtworkList al2 = new ArtworkList();
            Artwork a1= new Artwork();
            Artwork a2 = new Artwork();
            RoomListCopy copy = new RoomListCopy();
          
          a1.Author = "Eins";
          a1.Description = "Good";
          a1.Id = 1;
          a1.Location = "Center";
          a1.Name = "Davin";
          a1.MaxCo2 = 2;
          a1.Image = "Noimage";
          a1.Type = "NoType";
          a1.MaxHumidity = 50;
          a1.MaxTemperature = 20;
          a1.MinCo2 = 1;
          a1.MinHumidity = 2;
          a1.MinTemperature = 1;
          a2.Author = "Steven";
          a2.Description = "Awasome";
          a2.Id = 1;
          a2.Location = "North";
          a2.Name = "Cop";
          a2.MaxCo2 = 2;
          a2.MaxHumidity = 60;
          a2.MaxTemperature = 23;
          a2.MinCo2 = 11;
          a2.MinHumidity = 22;
          a2.MinTemperature = 12;
          al1.artworks.Add(a1);
          al2.artworks.Add(a2);
          roomCopy.ArtworkList = al1;
          roomCopy2.ArtworkList = al2;
          RoomMeasurement roomMeasurement = new RoomMeasurement();
          roomMeasurement.Co2 = 2;
          roomMeasurement.Humidity = 3;
          roomMeasurement.Light = 315;
          roomMeasurement.Temperature = 18;
         roomCopy.LiveRoomMeasurements = roomMeasurement;
         roomMeasurement.Co2 = 0;
          roomMeasurement.Humidity = 0;
          roomMeasurement.Light = Convert.ToDecimal(315.3);
          roomMeasurement.Temperature = 0;
         roomCopy2.LiveRoomMeasurements = roomMeasurement;
         copylist.rooms.Add(roomCopy);
          copylist.rooms.Add(roomCopy2);
        
          
            
            
            

            return copylist;

        }

        // GET: api/Rooms/5
        [HttpGet("get/{id}")]
        public Task<Room> GetRoom(string id)
        {
            var room = roomRepository.GetRoomByLocationCodeAsync(id);

            return room;
        }

        // GET: api/Rooms/5
        [HttpGet("getdetails/{id}")]
        public Task<Room> GetDetails(string id)
        {
            var room = roomRepository.GetRoomWithDetailsAsync(id);

            return room;
        }

        // GET: api/Rooms/5
        [HttpGet("getoptimalmeasurements/{id}")]
        public Task<Room> GetOptimalMeasurements(string id)
        {
            var room = roomRepository.GetRoomOptimalMeasurementsAsync(id);

            return room;
        }

        // GET: api/Rooms/5
        [HttpGet("getmeasurementconditions/{id:int}")]
        public async Task<ActionResult<RoomMeasurement>> GetMeasurementConditions([FromRoute] int id)
        {
            Console.WriteLine("--------------------");
           MongoMeasurement mongoMeasurement=  _mongoRepository.LoadLastRoomMeasurement(id);
           RoomMeasurement temp = new RoomMeasurement();
           temp.Co2 = mongoMeasurement.co2;
           temp.Humidity = mongoMeasurement.humidity;
           temp.Light = mongoMeasurement.light;
           temp.Temperature = mongoMeasurement.temperature;
           
           //PutRoom("A100")
           
           return temp;



//            var roomMeasurement = roomRepository.GetRoomMeasurementConditionsAsync(id);
//
//            return roomMeasurement;
        }

        // PUT: api/Rooms/5
        // To protect from overposting attacks, enable the specific properties you want to bind to, for
        // more details, see https://go.microsoft.com/fwlink/?linkid=2123754.
        [HttpPut("put/{id}")]
        public async Task<IActionResult> PutRoom([FromRoute] string id, [FromBody] Room room)
        {
            Console.WriteLine("This is the ID: " + id + ", and this is the location code: " + room.LocationCode +
                              ", and they are equal: " + id.Equals(room.LocationCode));

            if (!(id.Equals(room.LocationCode)))
            {
                return BadRequest();
            }


            //roomRepository.Entry(room).State = EntityState.Modified;

            roomRepository.updateRoom(room);

            try
            {
                roomRepository.saveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (roomRepository.roomExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return NoContent();
        }

        // POST: api/Rooms
        // To protect from overposting attacks, enable the specific properties you want to bind to, for
        // more details, see https://go.microsoft.com/fwlink/?linkid=2123754.
        [HttpPost]
        public async Task<ActionResult<Room>> PostRoom(Room room)
        {
            if (room == null)
            {
                return BadRequest();
            }

            if (!ModelState.IsValid)
            {
                return BadRequest();
            }

            roomRepository.createRoom(room);
            return CreatedAtRoute("", new {id = room.LocationCode}, room);
        }

        // DELETE: api/Rooms/5
        [HttpDelete("{id}")]
        public async Task<ActionResult<Room>> DeleteRoom(string id)
        {
            var room = await roomRepository.GetRoomByLocationCodeAsync(id);

            roomRepository.deleteRoom(room);

            return room;
        }
    }
}