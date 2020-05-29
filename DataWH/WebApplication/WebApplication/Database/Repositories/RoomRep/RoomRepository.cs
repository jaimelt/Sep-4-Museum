using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApplication.Datamodel;
using WebApplication.Database.Repositories;
using WebApplication.Database.Repositories;


namespace WebApplication.Database.Repositories.RoomRep
{
    public class RoomRepository : RepositoryBase<Room>, IRoomRepository
    {

        public RoomRepository(MuseumContext context) : base(context)
        {
        }

        public async Task<IEnumerable<Room>> getAllRoomsAsync()
        {
            return await FindAll().OrderBy(room => room.Description).ToListAsync();
        }

        public async Task<Room> GetRoomByLocationCodeAsync(string roomLocationCode)
        {
            return await FindByCondition(room => room.LocationCode.
            Equals(roomLocationCode)).FirstOrDefaultAsync();
        }
        
        

        public void createRoom(Room room)
        {
            Create(room);
          
        }

        public void updateRoom(Room room)
        {
            Update(room);
           
        }

        public void deleteRoom(Room room)
        {
            Delete(room);
            
        }

        public bool roomExists(string roomLocationCode)
        {
            return context.Rooms.Any(e => e.LocationCode.Equals(roomLocationCode));
        }

        public Task saveChanges()
        {
           return context.SaveChangesAsync();
        }

    }
}
