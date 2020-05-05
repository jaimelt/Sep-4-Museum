using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace WebApplication1.Datamodel
{
  
        public class Museum
        {
            [Key] public int id { get; set; }
            public ICollection<Administrator> adminList { get; set; }
            public ICollection<Room> roomList { get; set; }
        }
    
}