using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using WebApplication.Datamodel;
using WebApplication.MongoDB;
using WebApplication1.Database.Repositories.AccountRep;
using WebApplication1.Database.Repositories.RoomRep;
using WebApplication1.Datamodel;


namespace WebApplication.Controllers
{
    [Route("account")]
    [ApiController]
    public class AccountController : ControllerBase
    {
        private readonly AccountRepository _accountRepository;


        public AccountController(AccountRepository accountRepository)
        {
            _accountRepository = accountRepository;
           
        }
        
        // POST
        [HttpPost ("create")]
        public IActionResult createAccount([FromBody] Administrator admin)
        {
            Console.WriteLine("----------------------------");
            if (admin == null)
            {
                return BadRequest();
            }

            if (!ModelState.IsValid)
            {
                return BadRequest();
            }
            
            _accountRepository.Create(admin);
            return CreatedAtRoute("", new {id = admin.Id}, admin);
        }
        
        [HttpGet("getall")]
        public Task<IEnumerable<Room>> GetRooms()
        {
            Console.Write("dsadsadsadsa");
            return null;
        }

        
    }
}