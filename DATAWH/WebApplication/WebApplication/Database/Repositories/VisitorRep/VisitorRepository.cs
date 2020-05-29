using System.Collections.Generic;
using System.Threading.Tasks;
using WebApplication.Datamodel;

namespace WebApplication.Database.Repositories.VisitorRep
{
    public class VisitorRepository : RepositoryBase<Visitor>, IVisitorRep
    {
        public VisitorRepository(MuseumContext context) : base(context)
        {
        }

        public void AddVisitors(VisitorList visitors)
        {
            foreach (var c in visitors.visitors)
            {
                Create(c);
            }
        }

        public Task saveChanges()
        {
            return context.SaveChangesAsync();
        }
    }
}