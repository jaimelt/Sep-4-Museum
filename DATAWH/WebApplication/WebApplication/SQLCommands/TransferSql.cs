using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Linq;
using Microsoft.Data.SqlClient;
using Microsoft.EntityFrameworkCore.SqlServer.Storage.Internal;
using MongoDB.Driver;
using WebApplication.Datamodel;
using WebApplication.MongoDB;


namespace WebApplication.SQLCommands
{
    public static class TransferSql
    {
        public static void TransferDataFromMongoDb()
        {
          MongoRepository mongoRepository = new MongoRepository();

          RoomMeasurementList roomMeasurements = mongoRepository.LoadAllMeasurements();

            SqlConnection sqlConnection;
            SqlCommand sqlCommand;

            string connection =
                "User ID=museum;password=Mus12345;server=sqlserversss.database.windows.net;Database=museum;";

            sqlConnection = new SqlConnection(connection);

            try
            {
                for (int i = 1; i < roomMeasurements.Measurements.Count; i++)
                {
                    string sql =
                        "Insert into RoomMeasurements (roomNo,Temperature,Light,CO2,Humidity,MeasurementDate)" +
                        " values (@roomNo,@Temperature,@Light,@CO2,@Humidity,@MeasurementDate);";

                    sqlConnection.Open();

                    sqlCommand = new SqlCommand(sql, sqlConnection);

                    sqlCommand.Parameters.Add(@"roomNo", SqlDbType.VarChar).Value = roomMeasurements.Measurements[i].roomNo;
                    sqlCommand.Parameters.Add(@"Temperature", SqlDbType.Decimal).Value = roomMeasurements.Measurements[i].Temperature;
                    sqlCommand.Parameters.Add(@"Light", SqlDbType.Decimal).Value = roomMeasurements.Measurements[i].Light;
                    sqlCommand.Parameters.Add(@"Co2", SqlDbType.Decimal).Value = roomMeasurements.Measurements[i].Co2;
                    sqlCommand.Parameters.Add(@"Humidity", SqlDbType.Decimal).Value = roomMeasurements.Measurements[i].Humidity;
                    sqlCommand.Parameters.Add(@"MeasurementDate", SqlDbType.DateTime2).Value = roomMeasurements.Measurements[i].MeasurementDate;

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