using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace WebApplication1.Datamodel
{
    public class Museum
    {
        [Key] public int Id { get; set; }
        public ICollection<Administrator> AdminList {get; set;}
        public ICollection<Room> RoomList { get; set; }
    }
}