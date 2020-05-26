using WebApplication.Database.Repositories;
using WebApplication1.Datamodel;

namespace WebApplication1.Database.Repositories.AccountRep
{
    public interface IAccountRepository : IRepositoryBase<Administrator>
    {
        bool login(Administrator admin);
    }
}