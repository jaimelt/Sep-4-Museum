using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using REST_SEP4.Database;
using REST_SEP4.Datamodel;

namespace REST_SEP4.Controllers
{

    [Route("api/[controller]")]
    [ApiController]
    public class ArtworksController : ControllerBase
    {
        private readonly RoomContext _context;

        public ArtworksController(RoomContext context)
        {
            _context = context;

        }

        // GET: api/Artworks
        [HttpGet()]
        [Produces("application/json")]
        public async Task<ActionResult<IEnumerable<Artwork>>> GetArtWorks()
        {
            return await _context.Artworks.ToListAsync();
        }

        // GET: api/Artworks/5
        [HttpGet("{id}")]
        public async Task<ActionResult<Artwork>> GetArtWork(string id)
        {
            var artwork = await _context.Artworks.FindAsync(id);

            if (artwork == null)
            {
                return NotFound();
            }

            return artwork;
        }

        // PUT: api/Artworks/5
        // To protect from overposting attacks, enable the specific properties you want to bind to, for
        // more details, see https://go.microsoft.com/fwlink/?linkid=2123754.
        [HttpPut("{id}")]
        public async Task<IActionResult> PutArtwork(string id, Artwork artwork)
        {
            if (!id.Equals(artwork.Id))
            {
                return BadRequest();
            }

            _context.Entry(artwork).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!ArtworkExists(id))
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

    

     //POST: api/Artworks/5
        [HttpPost]
        public IActionResult Create([FromBody] Artwork item)
        {
            if (item == null)
            {
                return BadRequest();
            }

            _context.Artworks.Add(item);
            _context.SaveChanges();

            return CreatedAtAction(nameof(GetArtWork), new {item.Id}, item);
        }

        // DELETE: api/Artworks/5
        [HttpDelete("{id}")]
        public async Task<ActionResult<Artwork>> DeleteArtwork(string id)
        {
            var artwork = await _context.Artworks.FindAsync(id);
            if (artwork == null)
            {
                return NotFound();
            }

            _context.Artworks.Remove(artwork);
            await _context.SaveChangesAsync();

            return artwork;
        }

        private bool ArtworkExists(string id)
        {
            return _context.Artworks.Any(e => e.Id.Equals(id));
        }
    }
    }
