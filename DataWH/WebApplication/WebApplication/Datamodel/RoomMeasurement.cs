using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace WebApplication.Datamodel
{
    public class RoomMeasurement
    {
        [Key] public int Id { get; set; }
        [Column(TypeName = "decimal(8,4)")]
        public decimal Light { get; set; }
        [Column(TypeName = "decimal(8,4)")]
        public decimal Temperature { get; set; }
        [Column(TypeName = "decimal(8,4)")]
        public decimal Humidity { get; set; }
        [Column(TypeName = "decimal(8,4)")]
        public decimal Co2 { get; set; }
    }
}