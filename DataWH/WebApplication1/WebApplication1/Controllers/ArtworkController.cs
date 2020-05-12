//using System;
//using System.Collections.Generic;
//using System.ComponentModel.Design.Serialization;
//using System.Linq;
//using System.Threading.Tasks;
//using Microsoft.AspNetCore.Mvc;
//using Microsoft.EntityFrameworkCore;
//using Microsoft.Extensions.Logging;
//using WebApplication1.Database;
//using WebApplication1.Database.Repositories.ArtworkRep;
//using WebApplication1.Datamodel;
//
//namespace WebApplication1.Controllers
//{
//    [Route("artworks")]
//    [ApiController]
//    public class ArtworkController : ControllerBase
//    {
//        private readonly ArtworkRepository artworkRepository;
//
//        // TODO add ilogger  
//
//        public ArtworkController(ArtworkRepository artworkRepository)
//        {
//            this.artworkRepository = artworkRepository;
//             
//        }
//
//
//        // GET
//        [HttpGet("getall")]
//        public Task<IEnumerable<Artwork>> getallArtworks()
//        {
//            return artworkRepository.getAllArtworksAsync();
//        }
//
//        // GET
//        [HttpGet("get/{id}")]
//        public async Task<Artwork> getOneArtwork(string id)
//        {
//            var obj = await artworkRepository.GetArtworkByIdAsync(id);
//            return obj;
//        }
//
//        // GET
//        [HttpGet("getdetails/{id}")]
//        public async Task<Artwork> getDetails(string id)
//        {
//            var obj = await artworkRepository.GetArtowkrWithDetailsAsync(id);
//            return obj;
//        }
//
//        // POST
//        [HttpPost("createartwork")]
//        public async Task<IActionResult> createArtwork([FromBody] Artwork artwork)
//        {
//            if (artwork == null)
//            {
//                return BadRequest();
//            }
//
//            if (!ModelState.IsValid)
//            {
//                return BadRequest();
//            }
//
//            artworkRepository.createArtwork(artwork);
//            return CreatedAtRoute("", new {id = artwork.Id}, artwork);
//        }
//
//        // GET
//        [HttpDelete("delete/{id}")]
//        public async Task<Artwork> deleteArtwork(string id)
//        {
//            var obj = await artworkRepository.GetArtworkByIdAsync(id);
//            artworkRepository.deleteArtwork(obj);
//            return obj;
//        }
//
//        // PUT
//        [HttpPut("edit/{id}")]
//        //TODO: This is not working yet, can't find item.id 
//        public async Task<IActionResult> updateArt(string id, Artwork item)
//        {
//            
//            if (!id.Equals(item.Id))
//            {
//                Console.WriteLine("it has not been found");
//                return BadRequest();
//            }
//
//            artworkRepository.updateArtwork(item); 
//            
//            return NoContent();
//        }
//        
//  
//    }
//}
//
