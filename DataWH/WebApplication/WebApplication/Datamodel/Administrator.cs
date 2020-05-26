using System.ComponentModel.DataAnnotations;

namespace WebApplication1.Datamodel
{
    public class Administrator
    {
        [Key] public int Id { get; set; }
        [Required] public string Email { get; set; }
        [Required] public string Password { get; set; }
    }
}