using System;
using System.Collections.Generic;
using System.ComponentModel.Design.Serialization;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Logging;
using WebApplication1.Database;
using WebApplication1.Database.Repositories.ArtworkRep;
using WebApplication1.Datamodel;

namespace WebApplication1.Controllers
{
    
    [ApiController]
    [Route("artworks")]
    public class ArtworkController : ControllerBase
    {
        private readonly ArtworkRepository artworkRepository;

        // TODO add ilogger  

        public ArtworkController(ArtworkRepository artworkRepository)
        {
            this.artworkRepository = artworkRepository;
             
        }


        // GET
        [HttpGet("getall")]
        public Task<IEnumerable<Artwork>> getallArtworks()
        {
            return artworkRepository.getAllArtworksAsync();
        }

        // GET
        [HttpGet("getone/{id}")]
        public async Task<Artwork> getOneArtwork(int id)
        {
            var obj = await artworkRepository.GetArtworkByIdAsync(id);
            return obj;
        }

        // GET
        [HttpGet("getdetails/{id}")]
        public async Task<Artwork> getDetails(int id)
        {
            var obj = await artworkRepository.GetArtworkWithDetailsAsync(id);
            return obj;
        }

        // POST
        [HttpPost("createartwork")]
        public async Task<IActionResult> createArtwork([FromBody] Artwork artwork)
        {
            if (artwork == null)
            {
                return BadRequest();
            }

            if (!ModelState.IsValid)
            {
                return BadRequest();
            }

            artworkRepository.createArtwork(artwork);
            return CreatedAtRoute("", new {id = artwork.Id}, artwork);
        }

        // GET
        [HttpDelete("delete/{id}")]
        public async Task<Artwork> deleteArtwork(int id)
        {
            var obj = await artworkRepository.GetArtworkByIdAsync(id);
            artworkRepository.deleteArtwork(obj);
            return obj;
        }
        
        
        [HttpPut("edit/{id}")]
        public async Task<IActionResult> PutArtwork([FromRoute] int id, [FromBody] Artwork artwork)
        {

            Console.WriteLine("This is the ID: " + id + ", and this is the location code: " + artwork.Id + ", and they are equal: " + id.Equals(artwork.Id));    

          if(id != artwork.Id)
            {
                return BadRequest();
            }

            

            //roomRepository.Entry(room).State = EntityState.Modified;

            artworkRepository.updateArtwork(artwork);

            try
            {
                artworkRepository.saveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (artworkRepository.artExists(id))
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
        
        
  
    }
}

