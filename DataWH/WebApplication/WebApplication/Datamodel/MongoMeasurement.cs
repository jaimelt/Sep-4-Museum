using System;
using MongoDB.Bson;

namespace WebApplication.Datamodel
{
    public class MongoMeasurement
    {
        public ObjectId _id { get; set; }
        public int light { get; set; }
        public decimal temperature { get; set; }
        public decimal humidity { get; set; }
        public decimal co2 { get; set; }
        public int room_no { get; set; }
        public DateTime time { get; set; }
    }
}