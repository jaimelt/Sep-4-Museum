using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace WebApplication1.Datamodel
{
    public class Museum
    {
        [Key] public int Id { get; set; }
       public string Name { get; set; }
       public string Address { get; set; }
    }
}