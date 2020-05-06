using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using WebApplication1.Database.Repositories.RoomRep;
using WebApplication1.Datamodel;

namespace WebApplication1.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class RoomController : ControllerBase
    {
        //private readonly MuseumContext roomRepository;
        private readonly RoomRepository roomRepository;

        public RoomController(RoomRepository roomRepository)
        {
            this.roomRepository = roomRepository;
        }

        // GET: api/Rooms
        [HttpGet("getall")]
        public Task<IEnumerable<Room>> GetRooms()
        {
            return roomRepository.getAllRoomsAsync();
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
        [HttpGet("getmeasurementconditions/{id}")]
        public Task<Room> GetMeasurementConditions(string id)
        {
            var room = roomRepository.GetRoomMeasurementConditionsAsync(id);

            return room;
        }

        // PUT: api/Rooms/5
        // To protect from overposting attacks, enable the specific properties you want to bind to, for
        // more details, see https://go.microsoft.com/fwlink/?linkid=2123754.
        [HttpPut("{id}")]
        public async Task<IActionResult> PutRoom(string id, Room room)
        {
            if (id != room.locationCode)
            {
                return BadRequest();
            }

            //roomRepository.Entry(room).State = EntityState.Modified;

            roomRepository.updateRoom(room);

            //try
            /*{
                await roomRepository.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!RoomExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }*/

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
            return CreatedAtRoute("", new { id = room.locationCode }, room);
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
