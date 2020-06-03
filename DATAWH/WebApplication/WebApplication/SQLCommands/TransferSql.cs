using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Linq;
using Microsoft.Data.SqlClient;
using Microsoft.EntityFrameworkCore.SqlServer.Storage.Internal;
using MongoDB.Driver;
using WebApplication.Datamodel;
using Dapper;


namespace WebApplication.SQLCommands
{
    public static class TransferSql
    {
        public static void TransferDataFromMongoDb()
        {
            var mongoClient = new MongoClient("mongodb+srv://adminsep4iot:kk7ojsEwek8yOk8m@sep4iot-5ef3i.azure.mongodb.net/test?retryWrites=true&w=majority");

            var mongoDatabase = mongoClient.GetDatabase("sep4iot");

            var measurementsCollection = mongoDatabase.GetCollection<MongoMeasurement>("test");
            var allMeasurements = measurementsCollection.AsQueryable().ToList();
            
            var listMeasurements = new List<RoomMeasurement>(allMeasurements.Count);

            foreach (var c in allMeasurements)
            {
                int i = 0;
                listMeasurements[i].Co2 = c.co2;
                Console.WriteLine(listMeasurements[i].Co2);
                i++; 
            }


            var roomMeasurements = new RoomMeasurement();

                    roomMeasurements.Co2 = allMeasurements[1].co2;
                    roomMeasurements.Light = allMeasurements[1].light;
                    roomMeasurements.Humidity = allMeasurements[1].humidity;
                    roomMeasurements.Temperature = allMeasurements[1].temperature;
                    roomMeasurements.MeasurementDate = allMeasurements[1].time;
                    roomMeasurements.setroomNoFromInt(3);
                    
                    listMeasurements.Add(roomMeasurements);

                    var roomMeasurements1 = new RoomMeasurement();

                    roomMeasurements1.Co2 = allMeasurements[1].co2;
                    roomMeasurements1.Light = allMeasurements[1].light;
                    roomMeasurements1.Humidity = allMeasurements[1].humidity;
                    roomMeasurements1.Temperature = allMeasurements[1].temperature;
                    roomMeasurements1.MeasurementDate = allMeasurements[1].time;
                    roomMeasurements1.setroomNoFromInt(1);
                    
                    listMeasurements.Add(roomMeasurements1);
                    
                    var roomMeasurements2 = new RoomMeasurement();

                    roomMeasurements2.Co2 = allMeasurements[1].co2;
                    roomMeasurements2.Light = allMeasurements[1].light;
                    roomMeasurements2.Humidity = allMeasurements[1].humidity;
                    roomMeasurements2.Temperature = allMeasurements[1].temperature;
                    roomMeasurements2.MeasurementDate = allMeasurements[1].time;
                    roomMeasurements2.setroomNoFromInt(2);
                    
                    listMeasurements.Add(roomMeasurements2);
                    
                    
                    var roomMeasurements3 = new RoomMeasurement();

                    roomMeasurements3.Co2 = allMeasurements[1].co2;
                    roomMeasurements3.Light = allMeasurements[1].light;
                    roomMeasurements3.Humidity = allMeasurements[1].humidity;
                    roomMeasurements3.Temperature = allMeasurements[1].temperature;
                    roomMeasurements3.MeasurementDate = allMeasurements[1].time;
                    roomMeasurements3.setroomNoFromInt(4);
                    
                    listMeasurements.Add(roomMeasurements3);
                    
                    var roomMeasurements4 = new RoomMeasurement();

                    roomMeasurements4.Co2 = allMeasurements[1].co2;
                    roomMeasurements4.Light = allMeasurements[1].light;
                    roomMeasurements4.Humidity = allMeasurements[1].humidity;
                    roomMeasurements4.Temperature = allMeasurements[1].temperature;
                    roomMeasurements4.MeasurementDate = allMeasurements[1].time;
                    roomMeasurements4.setroomNoFromInt(5);
                    
                    listMeasurements.Add(roomMeasurements4);
                    
                    var roomMeasurements5 = new RoomMeasurement();

                    roomMeasurements5.Co2 = allMeasurements[1].co2;
                    roomMeasurements5.Light = allMeasurements[1].light;
                    roomMeasurements5.Humidity = allMeasurements[1].humidity;
                    roomMeasurements5.Temperature = allMeasurements[1].temperature;
                    roomMeasurements5.MeasurementDate = allMeasurements[1].time;
                    roomMeasurements5.setroomNoFromInt(6);
                    
                    listMeasurements.Add(roomMeasurements5);
                    
                    var roomMeasurements6 = new RoomMeasurement();

                    roomMeasurements6.Co2 = allMeasurements[1].co2;
                    roomMeasurements6.Light = allMeasurements[1].light;
                    roomMeasurements6.Humidity = allMeasurements[1].humidity;
                    roomMeasurements6.Temperature = allMeasurements[1].temperature;
                    roomMeasurements6.MeasurementDate = allMeasurements[1].time;
                    roomMeasurements6.setroomNoFromInt(7);
                    
                    listMeasurements.Add(roomMeasurements6);
                    
                    var roomMeasurements7 = new RoomMeasurement();

                    roomMeasurements7.Co2 = allMeasurements[1].co2;
                    roomMeasurements7.Light = allMeasurements[1].light;
                    roomMeasurements7.Humidity = allMeasurements[1].humidity;
                    roomMeasurements7.Temperature = allMeasurements[1].temperature;
                    roomMeasurements7.MeasurementDate = allMeasurements[1].time;
                    roomMeasurements7.setroomNoFromInt(8);
                    
                    listMeasurements.Add(roomMeasurements7);
                    
                    var roomMeasurements8 = new RoomMeasurement();

                    roomMeasurements8.Co2 = allMeasurements[1].co2;
                    roomMeasurements8.Light = allMeasurements[1].light;
                    roomMeasurements8.Humidity = allMeasurements[1].humidity;
                    roomMeasurements8.Temperature = allMeasurements[1].temperature;
                    roomMeasurements8.MeasurementDate = allMeasurements[1].time;
                    roomMeasurements8.setroomNoFromInt(8);
                    
                    listMeasurements.Add(roomMeasurements8);

                    foreach (var c in listMeasurements)
                    {
                        Console.WriteLine(c.Co2);
                        Console.WriteLine(c.Humidity);
                        Console.WriteLine(c.Temperature);
                        Console.WriteLine(c.roomNo);
                        Console.WriteLine(c.MeasurementDate);
                    }
        
                
            
            
        

            SqlConnection sqlConnection;
            SqlCommand sqlCommand;

            string connection ="Server=sqlserversss.database.windows.net;Database=museum;User Id=museum;password=Mus12345;MultipleActiveResultSets=True;";

            sqlConnection = new SqlConnection(connection);
            
            try
            {
                for (int i = 0; i < listMeasurements.Count; i++)
                {
                    string sql =
                        @"Insert Into RoomMeasurements (Temperature,Light,CO2,Humidity, roomNo,MeasurementDate)
 VALUES (@Temperature,@Light,@CO2,@Humidity, @roomNo, @MeasurementDate);";

                    sqlConnection.Open();

                    sqlCommand = new SqlCommand(sql, sqlConnection);

 
                    sqlCommand.Parameters.Add(@"Temperature", SqlDbType.Decimal).Value = listMeasurements[i].Temperature;
                    sqlCommand.Parameters.Add(@"Light", SqlDbType.Decimal).Value = listMeasurements[i].Light;
                    sqlCommand.Parameters.Add(@"Co2", SqlDbType.Decimal).Value = listMeasurements[i].Co2;
                    sqlCommand.Parameters.Add(@"Humidity", SqlDbType.Decimal).Value = listMeasurements[i].Humidity;
                    sqlCommand.Parameters.Add(@"MeasurementDate", SqlDbType.DateTime).Value = listMeasurements[i].MeasurementDate;

                    sqlCommand.ExecuteNonQuery();
                    sqlCommand.Dispose();
                    sqlConnection.Close();

                }
            }
            catch (Exception exception)
            {
                Console.WriteLine(" Transfer data from Mongodb is facing some issues" + exception.Message );
            }

          


        }






    }
}