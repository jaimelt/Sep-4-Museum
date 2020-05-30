using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using WebApplication.Database;
using WebApplication.Database.Repositories.VisitorRep;
using WebApplication.Datamodel;

namespace WebApplication.Controllers
{
    [ApiController]
    [Route("visitors")]
    public class VisitorController : ControllerBase
    {
        private readonly VisitorRepository VisitorRepository;
        private readonly ILogger<VisitorController> Logger;

        public VisitorController(VisitorRepository VisitorRepository, ILogger<VisitorController> Logger)
        {
            this.VisitorRepository = VisitorRepository;
            this.Logger = Logger; 
        }

        // POST
        [HttpPost]
        public async Task<IActionResult> postVisitors([FromBody] VisitorList visitors)
        {
            try
            {
                if (visitors == null)
                {
                    Logger.LogError("The visitor List is null. ");
                    return BadRequest("Null visitor list");
                }
                
                VisitorRepository.AddVisitors(visitors);
                await VisitorRepository.saveChanges();

            }
            catch(Exception exception)
            {
                Logger.LogError($"Something went wrong internally in the server: ", exception.Message);
                return StatusCode(500, "Internal server error");
            }

            return Ok("Visitor List has been added to the database");

        }
    }
}