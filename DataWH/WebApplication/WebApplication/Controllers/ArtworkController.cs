using System;
using System.Collections.Generic;
using System.ComponentModel.Design.Serialization;
using System.Linq;
using System.Threading.Tasks;
using DnsClient.Internal;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Logging;
using WebApplication.Database.Repositories.ArtworkRep;
using WebApplication.Datamodel;


namespace WebApplication.Controllers
{

    [ApiController]
    [Route("artworks")]
    public class ArtworkController : ControllerBase
    {
        private readonly ArtworkRepository artworkRepository;
        private readonly ILogger<ArtworkController> logger;
        
      

        public ArtworkController(ArtworkRepository artworkRepository, ILogger<ArtworkController> logger)
        {
            this.artworkRepository = artworkRepository;
            this.logger = logger;
            
        }


        // GET
        [HttpGet("getall")]
        public async Task<IActionResult> getallArtworks()
        {
           
            try
            {
                ArtworkList artworkList = new ArtworkList();
                artworkList.artworks = (List<Artwork>) await artworkRepository.GetAllArtWorksAsync();

                logger.LogInformation("Returning all the artworks stored in the database");

                return Ok(artworkList);

            }
            catch (Exception exception)
            {
                logger.LogError($"Something went wrong internally in the server: ", exception.Message);
                return StatusCode(500, "Internal server error");
            }

        }

        // GET
        [HttpGet("getone/{id}")]
        public async Task<IActionResult> getOneArtwork(int id)
        {
            try
            {
                var obj = await artworkRepository.GetArtworkById(id);
                if (obj == null)
                {
                    logger.LogError("The artwork does not exist");
                    return NotFound();
                }

                logger.LogInformation($"Returned the artwork with id {id}");
                return Ok(obj);
            }
            catch (Exception exception)
            {
                logger.LogError($"Something went wrong internally in the server: ", exception.Message);
                return StatusCode(500, "Internal server error");
            }

        }

        // GET
        [HttpGet("getallbyroom/{Location}")]
        public async Task<IActionResult> getArtworkByRoom(string Location)
        {
            try
            {
                ArtworkList artworkList = new ArtworkList();
                artworkList.artworks = (List<Artwork>) await artworkRepository.GetArtworksByRoom(Location);

                logger.LogInformation($"Returning all the artworks stored in room {Location}");

                return Ok(artworkList);
            }
            catch (Exception exception)
            {
                logger.LogError($"Something went wrong internally in the server: ", exception.Message);
                return StatusCode(500, "Internal server error");
            }
        }



        // POST
        [HttpPost("createartwork")]
        public async Task<IActionResult> createArtwork([FromBody] Artwork artwork)
        {
            try
            {
                if (artwork == null)
                {
                    logger.LogError("Artwork object sent from client is null");
                    return BadRequest("Artwork object is null");
                }

                if (!ModelState.IsValid)
                {
                    logger.LogError("Invalid object sent from client");
                    return BadRequest("Invalid model object");
                }

                logger.LogInformation("A new artwork object has been created");
                 artworkRepository.CreateArtWork(artwork);
                await artworkRepository.saveChanges();
                

                return CreatedAtRoute("", new {id = artwork.Id}, artwork);

            }
            catch (Exception exception)
            {
                logger.LogError($"Something went wrong internally in the server: ", exception.Message);
                return StatusCode(500, "Internal server error");
            }

        }

        // GET
        [HttpDelete("delete/{id}")]
        public async Task<IActionResult> deleteArtwork(int id)
        {
            try
            {
                var artwork = await artworkRepository.GetArtworkById(id);

                if (artwork == null)
                {
                    logger.LogError($"Artwork object with id {id} has not been found in the database");
                    return NotFound();
                }
                
                
                artworkRepository.DeleteArtwork(artwork);
                await artworkRepository.saveChanges();

                return Ok("Artwork has been deleted");
            }
            catch (Exception exception)
            {
                logger.LogError($"Something went wrong internally in the server: ", exception.Message);
                return StatusCode(500, "Internal server error");
            }
        }

        [HttpPut("moveartwork/{artid}/{location}")]
        public async Task<IActionResult> MoveArtwork([FromRoute] int artid, [FromRoute] string location)
        {
            try
            {
                if (artid == null)
                {
                    logger.LogError("Art id is null");
                    return BadRequest("Null artwork id");
                    
                } 
                
                if (location == null)
                {
                    logger.LogError("Location id is null");
                    return BadRequest("Null Location id");
                }

              
                artworkRepository.MoveArtwork(artid, location);
                await artworkRepository.saveChanges();
                return Ok("it has been moved");
                
            }
            
            catch (Exception exception)
            {
                logger.LogError($"Something went wrong internally in the server: {exception.Message}");
                return StatusCode(500, "Internal server error");
            }

        }
        


        [HttpPut("edit/{id}")]
        public async Task<IActionResult> PutArtwork([FromRoute] int id, [FromBody] Artwork artwork)
        {
            

            try
            {
                if (artwork == null)
                {
                    logger.LogError("Artwork object sent was null");
                    return BadRequest("Artwork object is null");
                }
                
                if (!id.Equals(artwork.Id))
                {
                    logger.LogError("Invalid artwork id");
                    return BadRequest("Invalid artwork id");
                }
                

                if (!ModelState.IsValid)
                {
                    logger.LogError("Invalid artwork object sent from client");
                    return BadRequest("Invalid artwork object");
                }

                if (!artworkRepository.ArtworkExists(id))
                {
                    logger.LogError("Artwork does not exist in the database");
                    return BadRequest("Artwork does not exist in the database");
                }
                

                artworkRepository.UpdateArtwork(artwork);
                await artworkRepository.saveChanges();
                return Ok("Artwork has been edited");
            }
            catch (Exception exception)
            {
                logger.LogError($"Something went wrong internally in the server: {exception.Message}");
                return StatusCode(500, "Internal server error");
            }
     
            

        }
    }
}

