using System;
using System.Collections;
using System.Collections.Generic;
using MongoDB.Bson;
using MongoDB.Driver;
using WebApplication1.Datamodel;

namespace WebApplication1.MongoDB
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
            ICollection<MongoMeasurement> measurements = _roomMeasurement.Find("data").ToList();
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
    }
}