using System;
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
        public IActionResult postVisitors([FromBody] VisitorList visitors)
        {


            foreach (var v in visitors.visitors)
            {
                context.Visitors.Add(v);
            }
            
            context.SaveChanges();


            return Ok("");
        }
    }
}