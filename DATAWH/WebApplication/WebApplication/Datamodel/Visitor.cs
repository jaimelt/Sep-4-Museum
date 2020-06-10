using System;
using System.ComponentModel.DataAnnotations;

namespace WebApplication.Datamodel
{
    public class Visitor
    {
        public Visitor()
        {
        }
        [Key] public int Id { get; set; }
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public string Gender { get; set; }
        public string Nationality { get; set; }
        public int Age { get; set; }
        public string ReasonToVisit { get; set; }
        public DateTime VisitingDate { get; set; }

    }
}