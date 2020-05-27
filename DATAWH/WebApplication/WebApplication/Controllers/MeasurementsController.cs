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

        [HttpGet("{id:int}")]
        public async Task<ActionResult<RoomMeasurement>> GetMeasurementConditions([FromRoute] int id)
        {
            Console.WriteLine("--------------------");
            MongoMeasurement mongoMeasurement = _mongoRepository.LoadLastRoomMeasurement(id);
            RoomMeasurement temp = new RoomMeasurement();
            temp.setMeasurementsFromMongo(mongoMeasurement);

            return temp;
        }
        
        [HttpGet]
        public ArtworkList getArtworksInDanger()
        {
            ArtworkAudit artworkAudit = new ArtworkAudit();
          
            for (int i = 1; i < 2; i++)
            {
                RoomMeasurement roomMeasurement = new RoomMeasurement();
               roomMeasurement.setMeasurementsFromMongo(_mongoRepository.LoadLastRoomMeasurement(i));
                artworkAudit.MeasurementList.Measurements.Add(roomMeasurement);
            }
            Console.Write(_artworkRepository.GetAllArtWorksAsync().Result.ToList()==null);

            artworkAudit.CheckArtworkConditions(_artworkRepository.GetAllArtWorksAsync().Result.ToList());
            

            return artworkAudit.Artworks;
        }
    }
}