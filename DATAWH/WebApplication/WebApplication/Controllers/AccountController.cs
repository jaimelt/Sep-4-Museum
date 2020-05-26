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
        // Login method, verifies if given username/password matches in the database
        [HttpGet ("login")]
        public bool login([FromBody] Administrator admin)
        {
            if (admin == null)
            {
                Console.WriteLine("admin is null");
                return false;
            }
            return _accountRepository.login(admin);
        }
        
        // Create new account
        [HttpPost ("create")]
        public async Task<IActionResult> createAccount([FromBody] Administrator admin)
        {
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
        
        // Delete existing account by username//password
        [HttpDelete("delete")]
        public async Task<Administrator> deleteAdmin(Administrator admin)
        {
            Console.WriteLine("delete admin");
            var obj =  _accountRepository.GetAdminByUsername(admin);
            Task.Delay(5000);
              _accountRepository.Delete(await obj);
            return admin;
        }
        
        // Edit admin account username or password
        [HttpPut("edit")]
        public async Task<IActionResult> UpdateAdmin([FromBody] Administrator admin)
        {

            
            if(admin == null)
            {
                return BadRequest();
            }

            Console.WriteLine("This is the ID:  and this is the location code: " + admin.Id + ", and they are equal: ");    
            

            _accountRepository.Update(admin);
            
            return NoContent();
        }

        
    }
}