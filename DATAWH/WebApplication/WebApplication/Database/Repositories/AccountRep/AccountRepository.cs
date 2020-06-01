using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using WebApplication.Database;
using WebApplication.Database.Repositories;
using WebApplication.Datamodel;
using WebApplication.Database.Repositories;


namespace WebApplication.Database.Repositories.AccountRep
{
    public class AccountRepository : RepositoryBase<Administrator>, IAccountRepository
    {
     

        public AccountRepository(MuseumContext context) : base(context)
        {
            
        }
        public async Task<IEnumerable<Administrator>> getAllUsernames()
        {
            return await FindAll().OrderBy(room => room.Email).ToListAsync();
        }
        
        public  void Create(Administrator entity)
        {
   
         context.Administrators.Add(entity);
         context.SaveChanges();
        }

        public bool Update(Administrator entity)
        {
            if (entity != null)
            {
                Console.WriteLine("Email:"+entity.Email);
                Console.WriteLine("password:"+entity.Password);
                context.Administrators.Update(entity);
                context.SaveChanges();
                return true;
            }

            return false;

        }

        public bool Delete(Administrator entity)
        {
            if (entity != null)
            {
                context.Administrators.Remove(entity);
                context.SaveChanges();
                return true;
            }

            return false;

        }

        public bool login(Administrator admin)
        {
            Administrator a1=  GetAdminByEmail(admin).Result;
            if (a1 == null)
            {
                Console.WriteLine("null object---------------------");
                return false;
            }
            if (a1.Password.Equals(admin.Password))
                return true;

            return false;
        }

        public async Task<Administrator> GetAdminByEmail(Administrator entity)
        {
            try
            {
                return await FindByCondition(art => art.Email.Equals(entity.Email))
                    .FirstOrDefaultAsync();
            }
            catch (Exception e)
            {
                Console.WriteLine($"Message: {e.Message}");
                return null;
            }

          

        }
    }
}