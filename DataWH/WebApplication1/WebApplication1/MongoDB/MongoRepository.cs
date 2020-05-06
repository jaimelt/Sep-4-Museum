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
        private  IMongoCollection<RoomMeasurement> _roomMeasurement;
        MongoClient client;
        private IMongoDatabase database;
        private IMongoDBSettings _settings;
       
        
        public MongoRepository(IMongoDBSettings settings)
        {
             _settings = settings;
             client = new MongoClient(settings.ConnectionString);
             database = client.GetDatabase(settings.DatabaseName);
            
           
        }
        public RoomMeasurement loadActualMeasurement(int roomNo)
        {
            _roomMeasurement = database.GetCollection<RoomMeasurement>(_settings.MeasurementsCollectionName);
         
            throw new NotImplementedException();
        }

        public ICollection<RoomMeasurement> LoadAllMeasurements()
        { 
            
            _roomMeasurement = database.GetCollection<RoomMeasurement>(_settings.MeasurementsCollectionName);
            ICollection<RoomMeasurement> measurements = _roomMeasurement.Find("data").ToList();
            return measurements;

        }

        public ICollection<RoomMeasurement> LoadMeasurementsFromDate(DateTime dateTime)
        {
            throw new NotImplementedException();
        }
    }
}