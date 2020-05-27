using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace WebApplication.Datamodel
{
    public class RoomMeasurement
    {
        [Key] public int Id { get; set; }
        [Column(TypeName = "decimal(8,4)")] public decimal Light { get; set; }
        [Column(TypeName = "decimal(8,4)")] public decimal Temperature { get; set; }
        [Column(TypeName = "decimal(8,4)")] public decimal Humidity { get; set; }
        [Column(TypeName = "decimal(8,4)")] public decimal Co2 { get; set; }

        public string roomNo { get; set; }


        public void setroomNoFromInt(int roomNo)
        {
            switch (roomNo)
            {
                case 1:
                    this.roomNo = "A1";
                    break;
                case 2:
                    this.roomNo = "A2";
                    break;
                case 3:
                    this.roomNo = "A3";
                    break;
                case 4:
                    this.roomNo = "B1";
                    break;
                case 5:
                    this.roomNo = "B2";
                    break;
                case 6:
                    this.roomNo = "B3";
                    break;
                case 7:
                    this.roomNo = "B4";
                    break;
                case 8:
                    this.roomNo = "Storage";
                    break;

                default:
                    this.roomNo = "No Value";
                    break;
                
            }
        }

        public void setMeasurementsFromMongo(MongoMeasurement mongoMeasurement)
        {
            Co2 = mongoMeasurement.co2;
            Humidity = mongoMeasurement.humidity;
            Light = mongoMeasurement.light;
            Temperature = mongoMeasurement.temperature;
            setroomNoFromInt(mongoMeasurement.room_no);
        }
    }
}