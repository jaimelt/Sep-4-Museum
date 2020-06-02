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

            var measurementsCollection = mongoDatabase.GetCollection<RoomMeasurement>("test");
            var allMeasurements = measurementsCollection.AsQueryable().ToList(); 
            
            Console.WriteLine(allMeasurements[0].Co2 + "hello");

            var roomMeasurements = new List<RoomMeasurement>();

            foreach (var c in allMeasurements)
            {
               roomMeasurements.Add(c);
            }

            
        

            SqlConnection sqlConnection;
            SqlCommand sqlCommand;

            string connection ="Server=sqlserversss.database.windows.net;Database=museum;User Id=museum;password=Mus12345;MultipleActiveResultSets=True;";

            sqlConnection = new SqlConnection(connection);

            try
            {
                for (int i = 0; i < roomMeasurements.Count; i++)
                {
                    string sql =
                        @"Insert Into RoomMeasurements (Temperature,Light,CO2,Humidity,MeasurementDate)
                         VALUES (@Temperature,@Light,@CO2,@Humidity);";

                    sqlConnection.Open();

                    sqlCommand = new SqlCommand(sql, sqlConnection);

                  
                    sqlCommand.Parameters.Add(@"Temperature", SqlDbType.Decimal).Value = roomMeasurements[i].Temperature;
                    sqlCommand.Parameters.Add(@"Light", SqlDbType.Decimal).Value = roomMeasurements[i].Light;
                    sqlCommand.Parameters.Add(@"Co2", SqlDbType.Decimal).Value = roomMeasurements[i].Co2;
                    sqlCommand.Parameters.Add(@"Humidity", SqlDbType.Decimal).Value = roomMeasurements[i].Humidity;
              

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