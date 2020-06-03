using System;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using WebApplication.Database.Repositories.ArtworkRep;
using WebApplication.Datamodel;
using WebApplication.MongoDB;

namespace WebApplication.Controllers
{
    [ApiController]
    [Route("measurements")]
    public class MeasurementsController
    {
        private readonly IMongoRepository _mongoRepository;
        private readonly IArtworkRepository _artworkRepository;


        public MeasurementsController(MongoRepository mongoRepository, ArtworkRepository artworkRepository)
        {
            _mongoRepository = mongoRepository;
            _artworkRepository = artworkRepository;
        }

        [HttpGet("{id}")]
        public async Task<ActionResult<RoomMeasurement>> GetMeasurementConditions([FromRoute] string id)
        {
            Console.WriteLine("--------------------");
            
            MongoMeasurement mongoMeasurement = _mongoRepository.LoadLastRoomMeasurement(_mongoRepository.modifyToMongoRoomID(id));
            RoomMeasurement temp = new RoomMeasurement();
            temp.setMeasurementsFromMongo(mongoMeasurement);

            return temp;
        }
        
        [HttpGet]
        public ArtworkList getArtworksInDanger()
        {
            ArtworkAudit artworkAudit = new ArtworkAudit();
          
            for (int i = 1; i < 7; i++)
            {
                RoomMeasurement roomMeasurement = new RoomMeasurement();
               roomMeasurement.setMeasurementsFromMongo(_mongoRepository.LoadLastRoomMeasurement(i));
                artworkAudit.MeasurementList.Measurements.Add(roomMeasurement);
            }

            artworkAudit.CheckArtworkConditions(_artworkRepository.GetAllArtWorksAsync().Result.ToList());

            foreach (var VARIABLE in artworkAudit.Artworks.artworks)
            {
             Console.WriteLine(  "--------"+ VARIABLE.Comment);
            }
            return artworkAudit.Artworks;
        }

        [HttpGet("getall/{roomCode}")]
        public RoomMeasurementList getAllMeasurements(string roomCode)
        {
            //Only 1 room is real the rest is harcoded because we only have 1 set of sensors
            
            RoomMeasurementList roomMeasurementList = new RoomMeasurementList();
            roomMeasurementList.mockMeasurementsExceptRoomNo(roomCode);
            RoomMeasurement roomMeasurement = new RoomMeasurement();
            roomMeasurement.setMeasurementsFromMongo(_mongoRepository.LoadLastRoomMeasurement(_mongoRepository.modifyToMongoRoomID(roomCode)));
            roomMeasurementList.addRoomMeasurement(roomMeasurement);

            return roomMeasurementList;

        }
    }
}