using System.Collections.Generic;
using System.Security.AccessControl;

namespace WebApplication.Datamodel
{
    public class AdministratorList
    {
        public List<Administrator> users { get; set; }


        public AdministratorList()
        {
        }
    }
}