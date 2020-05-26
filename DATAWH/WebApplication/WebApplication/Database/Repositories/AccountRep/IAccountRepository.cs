

﻿using WebApplication.Datamodel;


namespace WebApplication.Database.Repositories.AccountRep
{
    public interface IAccountRepository : IRepositoryBase<Administrator>
    {
        bool login(Administrator admin);
    }
}