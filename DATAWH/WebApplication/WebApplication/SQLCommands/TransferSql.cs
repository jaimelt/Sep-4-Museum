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

            var mongoDatabase = mongoClient.GetDatabase("RoomMeasurements");

            var measurementsCollection = mongoDatabase.GetCollection<RoomMeasurement>("RoomMeasurements");
            var allMeasurements = measurementsCollection.AsQueryable<RoomMeasurement>().ToList();

            var roomMeasurements = new List<RoomMeasurement>();

            allMeasurements.ForEach(measurement => roomMeasurements.Add(measurement));

            //check if it worked
            Console.WriteLine(roomMeasurements[0].Co2);

            SqlConnection sqlConnection;
            SqlCommand sqlCommand;

            string connection = "sqlexpress connection";

            sqlConnection = new SqlConnection(connection);

            try
            {
                for (int i = 0; i < roomMeasurements.Count; i++)
                {
                    string sql =
                        "Insert into RoomMeasurements (RoomId,Temperature,Light,CO2,Humidity,ReadingDate)" +
                        " values (@RoomId,@Temperature,@Light,@CO2,@Humidity,@ReadingDate);";

                    sqlConnection.Open();

                    sqlCommand = new SqlCommand(sql, sqlConnection);

                    sqlCommand.Parameters.Add(@"RoomId", SqlDbType.Int).Value = roomMeasurements[i].Id;
                    sqlCommand.Parameters.Add(@"Temperature", SqlDbType.Decimal).Value = roomMeasurements[i].Temperature;
                    sqlCommand.Parameters.Add(@"Light", SqlDbType.Decimal).Value = roomMeasurements[i].Light;
                    sqlCommand.Parameters.Add(@"Co2", SqlDbType.Decimal).Value = roomMeasurements[i].Co2;
                    sqlCommand.Parameters.Add(@"Humidity", SqlDbType.Decimal).Value = roomMeasurements[i].Humidity;
                    sqlCommand.Parameters.Add(@"ReadingDate", SqlDbType.Date).Value = roomMeasurements[i].MeasurementDate;

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