using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using WebApplication1.Datamodel;

namespace WebApplication1.Database.Repositories.RoomRep
{
    interface IRoomRepository
    {

        Task<IEnumerable<Room>> getAllRoomsAsync();

        Task<Room> GetRoomByLocationCodeAsync(string roomLocationCode);

        Task<Room> GetRoomWithDetailsAsync(string roomLocationCode);

        Task<Room> GetRoomOptimalMeasurementsAsync(string roomLocationCode);

        Task<Room> GetRoomMeasurementConditionsAsync(string roomLocationCode);

        void createRoom(Room room);

        void updateRoom(Room room);

        void deleteRoom(Room room);

        bool roomExists(string roomLocationCode);

        void saveChanges();

    }
}
