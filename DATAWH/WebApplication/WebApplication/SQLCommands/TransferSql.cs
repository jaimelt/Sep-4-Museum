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
            var mongoClient = new MongoClient("connectionstring of db");

            var mongoDatabase = mongoClient.GetDatabase("name of db");

            var measurementsCollection = mongoDatabase.GetCollection<RoomMeasurement>("RoomMeasurements");
            var allMeasurements = measurementsCollection.AsQueryable<RoomMeasurement>().ToList();

            var roomMeasurements = new List<RoomMeasurement>();

            allMeasurements.ForEach(measurement => roomMeasurements.Add(measurement));

            //check if it worked
            Console.WriteLine(roomMeasurements[0].Co2);

            SqlConnection sqlConnection;
            SqlCommand sqlCommand;

            string connection = "sqlexpress connection";

            sqlConnection = new SqlConnection();

            try
            {
                for (int i = 0; i < roomMeasurements.Count; i++)
                {
                    string sql =
                        "Insert into [SEP].[dbo].[Readings] (PlantID, PlantName,Temperature,Light,CO2,Humidity,AmountOfWater,HoursSinceWatering,ReadingDate)" +
                        " values (@PlantID,@PlantName,@Temperature,@Light,@CO2,@Humidity,@AmountOfWater,@HoursSinceWatering,@ReadingDate);";

                    sqlConnection.Open();

                    sqlCommand = new SqlCommand(sql, sqlConnection);

                    sqlCommand.Parameters.Add(@"RoomId", SqlDbType.Int).Value = roomMeasurements[i].Id;



                    sqlCommand.ExecuteNonQuery();
                    sqlCommand.Dispose();
                    sqlConnection.Close();

                }
            }
            catch (SystemException)
            {
                throw;
            }


        }

        public static List<Visitor> TransferDataFromSQL()
        {
            SqlConnection connection =
                new SqlConnection(
                    "Server=sqlserversss.database.windows.net;Database=museum;User Id=museum;password=Mus12345;MultipleActiveResultSets=True;");

            List<Visitor>  me  = connection.Query<Visitor>("Select * FROM Visitors").ToList();
            foreach (var c in me)
            {
                Console.WriteLine(c.VisitingDate);
            }
            return me; 


        }





    }
}