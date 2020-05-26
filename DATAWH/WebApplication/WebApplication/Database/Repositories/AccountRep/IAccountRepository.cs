using WebApplication.Datamodel;

namespace WebApplication.Database.Repositories.AccountRep
{
    public interface IAccountRepository : IRepositoryBase<Administrator>
    {
        void createAccount(Administrator admin);
        void editAccount(Administrator admin);
        void deleteAccount(Administrator admin);
    }
}