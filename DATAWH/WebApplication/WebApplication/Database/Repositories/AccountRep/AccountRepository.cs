using System;
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
        
        public  void Create(Administrator entity)
        {
   
         context.Administrators.Add(entity);
         context.SaveChanges();
        }

        public void Update(Administrator entity)
        {
            context.Administrators.Update(entity);
            context.SaveChanges();
        }

        public void Delete(Administrator entity)
        {
            context.Administrators.Remove(entity);
            context.SaveChanges();
        }

        public bool login(Administrator admin)
        {
            Administrator a1=  GetAdminByUsername(admin).Result;
            if (a1 == null)
            {
                Console.WriteLine("null object---------------------");
                return false;
            }
            if (a1.Password.Equals(admin.Password))
                return true;

            return false;
        }

        public async Task<Administrator> GetAdminByUsername(Administrator entity)
        {
            return await FindByCondition(art => art.Email.Equals(entity.Email))
                .FirstOrDefaultAsync();
        }
    }
}