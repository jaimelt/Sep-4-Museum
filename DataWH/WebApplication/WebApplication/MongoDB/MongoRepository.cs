using System;
using System.Collections;
using System.Collections.Generic;
using MongoDB.Bson;
using MongoDB.Driver;
using WebApplication.Datamodel;


namespace WebApplication.MongoDB
{
    public class MongoRepository : IMongoRepository


    {
        private IMongoCollection<MongoMeasurement> _roomMeasurement;
        MongoClient client;
        private IMongoDatabase database;
        private IMongoDBSettings _settings;


        public MongoRepository()
        {
            _settings = new MongoDbSettings();
            client = new MongoClient(_settings.ConnectionString);
            database = client.GetDatabase(_settings.DatabaseName);
        }


        public RoomMeasurement loadActualMeasurement(int roomNo)
        {
            _roomMeasurement = database.GetCollection<MongoMeasurement>(_settings.MeasurementsCollectionName);

            throw new NotImplementedException();
        }

        public ICollection<MongoMeasurement> LoadAllMeasurements()
        {
            _roomMeasurement = database.GetCollection<MongoMeasurement>(_settings.MeasurementsCollectionName);
            BsonDocument filter = new BsonDocument("room_no", 1);
            ICollection<MongoMeasurement> measurements = _roomMeasurement.Find(filter).ToList();
            
            return measurements;
        }

        public ICollection<MongoMeasurement> LoadMeasurementsFromDate(DateTime dateTime)
        {
            throw new NotImplementedException();
        }

        public MongoMeasurement LoadLastRoomMeasurement(int room_no)
        {
            _roomMeasurement = database.GetCollection<MongoMeasurement>(_settings.MeasurementsCollectionName);
            BsonDocument filter = new BsonDocument("room_no", room_no);
            var result = _roomMeasurement.Find(filter).SortByDescending(d => d.time).Limit(1).FirstOrDefaultAsync();


            MongoMeasurement measurement = result.Result;
            Console.WriteLine(result.Result.time);

            return measurement;
        }

        public int modifyToMongoRoomID(string room_no)
        {
            if (room_no == "A1")
            {
                return 1;
            }
            if (room_no == "A2")
            {
                return 2;
            }
            if (room_no == "A3")
            {
                return 3;
            }
            if (room_no == "B1")
            {
                return 4;
            }
            if (room_no == "B2")
            {
                return 5;
            }
            if (room_no == "B3")
            {
                return 6;
            }
            if (room_no == "B4")
            {
                return 7;
            }

            return 7;
        }
    }
}