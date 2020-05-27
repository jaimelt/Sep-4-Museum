using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using WebApplication.Database.Repositories.AccountRep;
using WebApplication.Datamodel;
using WebApplication.MongoDB;
using WebApplication.Datamodel;


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
        [HttpGet]
        public bool login([FromBody] Administrator admin)
        {
            if (admin == null)
            {
                Console.WriteLine("admin is null");
                return false;
            }

            return _accountRepository.login(admin);
        }

        [HttpGet]
        public async Task<IActionResult> getAll()
        {
            AdministratorList users = new AdministratorList();
              users.users =  (List<Administrator>) _accountRepository.getAllUsernames().Result;
            foreach (var u in users.users)
            {
                u.Password = "";
            }

            return Ok(users);
        }

        // Create new account
        [HttpPost]
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
        [HttpDelete]
        public async Task<Administrator> deleteAdmin(Administrator admin)
        {
            Console.WriteLine("delete admin");
            var obj = _accountRepository.GetAdminByUsername(admin);
            Task.Delay(3000);
            _accountRepository.Delete(await obj);
            return admin;
        }

        // Edit admin account username or password
        [HttpPut]
        public async Task<IActionResult> UpdateAdmin([FromBody] Administrator admin)
        {
            Console.WriteLine("update admin");
            var obj = _accountRepository.GetAdminByUsername(admin);
            Task.Delay(3000);
            if (admin == null)
            {
                return BadRequest();
            }

            Console.WriteLine("This is the ID:  and this is the location code: " + admin.Id + ", and they are equal: ");


            _accountRepository.Update(await obj);

            return NoContent();
        }
    }
}