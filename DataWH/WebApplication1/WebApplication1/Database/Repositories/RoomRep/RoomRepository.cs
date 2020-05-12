//using Microsoft.EntityFrameworkCore;
//using System;
//using System.Collections.Generic;
//using System.Linq;
//using System.Threading.Tasks;
//using WebApplication1.Datamodel;
//
//namespace WebApplication1.Database.Repositories.RoomRep
//{
//    public class RoomRepository : RepositoryBase<Room>, IRoomRepository
//    {
//
//        public RoomRepository(MuseumContext context) : base(context)
//        {
//        }
//
//        public async Task<IEnumerable<Room>> getAllRoomsAsync()
//        {
//            return await FindAll().OrderBy(room => room.description).ToListAsync();
//        }
//
//        public async Task<Room> GetRoomByLocationCodeAsync(string roomLocationCode)
//        {
//            return await FindByCondition(room => room.locationCode.
//            Equals(roomLocationCode)).FirstOrDefaultAsync();
//        }
//
//        public async Task<Room> GetRoomWithDetailsAsync(string roomLocationCode)
//        {
//            return await FindByCondition(room => room.locationCode.Equals(roomLocationCode)).
//            Include(cod => cod.measurementConditions).FirstOrDefaultAsync();
//        }
//
//        public async Task<Room> GetRoomOptimalMeasurementsAsync(string roomLocationCode)
//        {
//
//            return await FindByCondition(room => room.locationCode.Equals(roomLocationCode)).
//            Include(cod => cod.optimalMeasurements).FirstOrDefaultAsync();
//        }
//
//        public async Task<Room> GetRoomMeasurementConditionsAsync(string roomLocationCode)
//        {
//
//            return await FindByCondition(room => room.locationCode.Equals(roomLocationCode)).
//            Include(cod => cod.measurementConditions).FirstOrDefaultAsync();
//        }
//
//        public void createRoom(Room room)
//        {
//            Create(room);
//            context.SaveChangesAsync();
//        }
//
//        public void updateRoom(Room room)
//        {
//            Update(room);
//            context.SaveChangesAsync();
//        }
//
//        public void deleteRoom(Room room)
//        {
//            Delete(room);
//            context.SaveChangesAsync();
//        }
//
//        public bool roomExists(string roomLocationCode)
//        {
//            return context.Rooms.Any(e => e.locationCode.Equals(roomLocationCode));
//        }
//
//        public void saveChanges()
//        {
//            context.SaveChangesAsync();
//        }
//
//    }
//}
