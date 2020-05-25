using WebApplication1.Datamodel;

namespace WebApplication1.Database.Repositories.AccountRep
{
    public interface IAccountRepository : IRepositoryBase<Administrator>
    {
        void createAccount(Administrator admin);
        void editAccount(Administrator admin);
        void deleteAccount(Administrator admin);
    }
}