using System;
using System.ComponentModel.DataAnnotations;

namespace WebApplication1.Datamodel
{
    public class Visitor
    {
        [Key] public int Id { get; set; }
        
        public string FirstNameName { get; set; }
        public string LastName { get; set; }
        public string Gender { get; set; }
        public string Nationality { get; set; }
        public int Age { get; set; }
        public string ReasonToVisit { get; set; }
        public DateTime VisitingDate { get; set; }
    }
}