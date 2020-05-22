﻿using System;
using MongoDB.Bson;

namespace WebApplication1.Datamodel
{
    public class MongoMeasurement
    {
        public ObjectId _id { get; set; }
        public decimal light { get; set; }
        public decimal temperature { get; set; }
        public decimal humidity { get; set; }
        public int co2 { get; set; }
        public int room_no { get; set; }
        public DateTime time { get; set; }
    }
}