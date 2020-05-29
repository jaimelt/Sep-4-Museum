using System.Collections.Generic;
using System.Threading.Tasks;
using WebApplication.Datamodel;

namespace WebApplication.Database.Repositories.VisitorRep
{
    public interface IVisitorRep : IRepositoryBase<Visitor>
    {
        void AddVisitors(VisitorList visitors);

        Task saveChanges();
    }
}