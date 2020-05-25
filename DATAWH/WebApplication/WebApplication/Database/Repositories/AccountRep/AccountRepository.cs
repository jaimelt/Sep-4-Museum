using System;
using System.Linq;
using System.Linq.Expressions;
using WebApplication.Database;
using WebApplication1.Datamodel;

namespace WebApplication1.Database.Repositories.AccountRep
{
    public class AccountRepository : RepositoryBase<Artwork>, IAccountRepository
    {
        public AccountRepository(MuseumContext context) : base(context)
        {
            
        }

        public void createAccount(Administrator admin)
        {
        }

        public void editAccount(Administrator admin)
        {
            throw new System.NotImplementedException();
        }

        public void deleteAccount(Administrator admin)
        {
            throw new System.NotImplementedException();
        }

        public IQueryable<Administrator> FindAll()
        {
            throw new NotImplementedException();
        }

        public IQueryable<Administrator> FindByCondition(Expression<Func<Administrator, bool>> expression)
        {
            throw new NotImplementedException();
        }

        public void Create(Administrator entity)
        {
//        Create(entity); 
            context.Administrators.Add(entity);
            context.SaveChanges();
        }

        public void Update(Administrator entity)
        {
            throw new NotImplementedException();
        }

        public void Delete(Administrator entity)
        {
            throw new NotImplementedException();
        }
    }
}