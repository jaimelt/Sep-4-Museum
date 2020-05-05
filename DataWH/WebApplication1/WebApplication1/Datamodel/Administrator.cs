using System.ComponentModel.DataAnnotations;

namespace WebApplication1.Datamodel
{
    public class Administrator
    {
        [Key] public int id { get; set; }
        [Required] public string username { get; set; }
        [Required] public string password { get; set; }
        
        
    }
}