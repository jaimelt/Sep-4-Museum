using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApplication.Datamodel;

namespace WebApplication.Database.Repositories.RoomRep
{
    interface IRoomRepository : IRepositoryBase<Room>
    {

        Task<IEnumerable<Room>> getAllRoomsAsync();

        Task<Room> GetRoomByLocationCodeAsync(string roomLocationCode);

       // Task<Room> GetRoomOptimalMeasurementsAsync(string roomLocationCode);

        Task<RoomMeasurement> GetRoomMeasurementConditionsAsync(string roomLocationCode);

        void createRoom(Room room);

        void updateRoom(Room room);

        void deleteRoom(Room room);

        bool roomExists(string roomLocationCode);

        Task saveChanges();

    }
}
