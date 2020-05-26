using System.Collections.Generic;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using WebApplication.Database;
using WebApplication.Datamodel;

namespace WebApplication.Controllers
{
    [ApiController]
    [Route("visitors")]

    public class VisitorController : ControllerBase
    {

        private readonly MuseumContext context;

        public VisitorController(MuseumContext context)
        {
            this.context = context;
        }

        // POST
        [HttpPost]
        public Task<IActionResult> createArtwork([FromBody] List<Visitor> visitors)
        {

            foreach (var e in visitors)
            {
                context.Visitors.Add(e);
            }

            return null;


        }
    }
}