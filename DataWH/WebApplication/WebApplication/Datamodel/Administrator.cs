using System;
using System.ComponentModel.DataAnnotations;

namespace WebApplication.Datamodel
{
    public class Administrator
    {
        [Key] public int Id { get; set; }
        public string Email { get; set; }
        public string Password { get; set; }
        public Administrator()
        {
        }

 
    }
}