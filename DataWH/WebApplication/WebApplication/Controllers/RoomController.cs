using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Cryptography.X509Certificates;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Logging;
using WebApplication.Database.Repositories.ArtworkRep;
using WebApplication.Datamodel;
using WebApplication.MongoDB;
using WebApplication.Database.Repositories.RoomRep;
using WebApplication.Datamodel;
using WebApplication.SQLCommands;


namespace WebApplication.Controllers
{
    [Route("rooms")]
    [ApiController]
    public class RoomController : ControllerBase
    {
        private readonly RoomRepository roomRepository;
        private readonly IMongoRepository _mongoRepository;
        private ILogger<RoomController> logger;
       


        public RoomController(RoomRepository roomRepository, MongoRepository mongoRepository,
            ILogger<RoomController> logger)
        {
            this.roomRepository = roomRepository;
            _mongoRepository = mongoRepository;
            this.logger = logger;
        }

        // GET: api/Rooms
        [HttpGet("getall")]
        public async Task<IActionResult> GetRooms()
        {
           
            try
            {
                RoomList roomList = new RoomList();
                roomList.rooms = (List<Room>) await roomRepository.getAllRoomsAsync();

                logger.LogInformation("Returning all the rooms stored in the database");

                return Ok(roomList);

            }
            catch (Exception exception)
            {
                logger.LogError($"Something went wrong internally in the server: ", exception.Message);
                return StatusCode(500, "Internal server error");
                
            }

        }

        // GET: api/Rooms/5
        [HttpGet("get/{id}")]
        public async Task<IActionResult> GetRoom(string id)
        {
            try
            {
                var obj = await roomRepository.GetRoomByLocationCodeAsync(id);
                if (obj == null)
                {
                    logger.LogError("The room does not exist");
                    return NotFound();
                }
                else
                {
                    logger.LogInformation($"Returned the room with id {id}");
                    return Ok(obj);
                }
            }
            catch (Exception exception)
            {
                logger.LogError($"Something went wrong internally in the server: ", exception.Message);
                return StatusCode(500, "Internal server error");
            }

        }


        // GET: api/Rooms/5
        [HttpGet("getmeasurementconditions/{id:int}")]
        public async Task<ActionResult<RoomMeasurement>> GetMeasurementConditions([FromRoute] int id)
        {
            Console.WriteLine("--------------------");
            MongoMeasurement mongoMeasurement = _mongoRepository.LoadLastRoomMeasurement(id);
            RoomMeasurement temp = new RoomMeasurement();
            temp.Co2 = mongoMeasurement.co2;
            temp.Humidity = mongoMeasurement.humidity;
            temp.Light = mongoMeasurement.light;
            temp.Temperature = mongoMeasurement.temperature;
            temp.setroomNoFromInt(mongoMeasurement.room_no);

            return temp;

        }

        // PUT: api/Rooms/5
        [HttpPut("put/{id}")]
        public async Task<IActionResult> PutRoom([FromRoute] string id, [FromBody] Room room)
        {
            try
            {
                if (!id.Equals(room.LocationCode))
                {
                    logger.LogError("Invalid room id");
                    return BadRequest("Invalid room id");
                }

                if (room == null)
                {
                    logger.LogError("Room object sent was null");
                    return BadRequest("Room object is null");
                }

                if (!ModelState.IsValid)
                {
                    logger.LogError("Invalid room object sent from client");
                    return BadRequest("Invalid room object");
                }

                roomRepository.updateRoom(room);
                await roomRepository.saveChanges();

                return NoContent();
            }
            catch (Exception exception)
            {
                logger.LogError($"Something went wrong internally in the server: ", exception.Message);
                return StatusCode(500, "Internal server error");
            }


        }
        
        
        
        
        // DELETE: api/Rooms/5
        [HttpDelete("delete/{id}")]
        public async Task<IActionResult> DeleteRoom(string id)
        {
            try
            {
                var artwork = await roomRepository.GetRoomByLocationCodeAsync(id);

                if (artwork == null)
                {
                    logger.LogError($"Room object with id {id} has not been found in the database");
                    return NotFound();
                }

                roomRepository.deleteRoom(artwork);
                await roomRepository.saveChanges();

                return NoContent();
            }
            catch (Exception exception)
            {
                logger.LogError($"Something went wrong internally in the server: ", exception.Message);
                return StatusCode(500, "Internal server error");
            }
        }

        // POST: api/Rooms
        // To protect from overposting attacks, enable the specific properties you want to bind to, for
        // more details, see https://go.microsoft.com/fwlink/?linkid=2123754.
        [HttpPost("createroom")]
        public async Task<IActionResult> PostRoom(Room room)
        {
            try
            {
                if (room == null)
                {
                    logger.LogError("Room object sent from client is null");
                    return BadRequest("Room object is null");
                }

                if (!ModelState.IsValid)
                {
                    logger.LogError("Invalid object sent from client");
                    return BadRequest("Invalid model object");
                }

                roomRepository.createRoom(room);
                await roomRepository.saveChanges();

                return CreatedAtRoute("", new {id = room.LocationCode}, room);

            }
            catch (Exception exception)
            {
                logger.LogError($"Something went wrong internally in the server: ", exception.Message);
                return StatusCode(500, "Internal server error");
            }


        }
    }
}