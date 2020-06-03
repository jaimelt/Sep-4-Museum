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

        public RoomMeasurementList LoadAllMeasurements()
        {
            _roomMeasurement = database.GetCollection<MongoMeasurement>(_settings.MeasurementsCollectionName);
            RoomMeasurementList measurementList = new RoomMeasurementList();
            RoomMeasurement RoomMeasurement = new RoomMeasurement();
            List<MongoMeasurement> mongoMeasurements =
                _roomMeasurement.Find(FilterDefinition<MongoMeasurement>.Empty).ToList();
            foreach (var m in mongoMeasurements)
            {
                RoomMeasurement.setMeasurementsFromMongo(m);
                measurementList.addRoomMeasurement(RoomMeasurement);
            }


            return measurementList;
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

        public RoomMeasurementList LoadAllRoomLastMeasurements()
        {
            RoomMeasurementList list = new RoomMeasurementList();
            RoomMeasurement roomMeasurement = new RoomMeasurement();
            _roomMeasurement = database.GetCollection<MongoMeasurement>(_settings.MeasurementsCollectionName);
            for (int i = 1; i < 8; i++)
            {
                roomMeasurement = new RoomMeasurement();
                BsonDocument filter = new BsonDocument("room_no", i);
                MongoMeasurement mongoMeasurement = _roomMeasurement.Find(filter).SortByDescending(d => d.time).Limit(1)
                    .FirstOrDefault();
                
                roomMeasurement.setMeasurementsFromMongo(mongoMeasurement);

                list.Measurements.Add(roomMeasurement);
            }

            return list;
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