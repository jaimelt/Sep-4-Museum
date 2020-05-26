<<<<<<< HEAD
﻿using WebApplication.Database.Repositories;
using WebApplication1.Datamodel;
=======
﻿using WebApplication.Datamodel;
>>>>>>> origin/dh.florinx

namespace WebApplication.Database.Repositories.AccountRep
{
    public interface IAccountRepository : IRepositoryBase<Administrator>
    {
        bool login(Administrator admin);
    }
}