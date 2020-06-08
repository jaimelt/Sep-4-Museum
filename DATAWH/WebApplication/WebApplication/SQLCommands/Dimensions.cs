using System.Data;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Data.SqlClient;
using Microsoft.Extensions.Logging;

namespace WebApplication.SQLCommands
{
    public static class Dimensions
    {
       
        
        private const string MuseumConnectionString =
            "Server=sqlserversss.database.windows.net;Database=museum;User Id=museum;password=Mus12345;MultipleActiveResultSets=True;";

        public static async Task<IActionResult> DimRoomTable()
        {
            await using var sqlConnection = new SqlConnection(Dimensions.MuseumConnectionString);
            await sqlConnection.OpenAsync();
            var commandCreateDimRoom = sqlConnection.CreateCommand();

            commandCreateDimRoom.CommandText = 
                @"CREATE TABLE museum.dbo.DIMENSION_DimRoom
            (
            R_ID int IDENTITY, 
            locationCode varchar (10),
            Description varchar (50),
            optimalLight int,
            optimalTemperature int,
            optimalHumidity int,
            optimalCo2 int,
            ValidFrom Date,
            ValidTo DATE,
            PRIMARY KEY (R_ID));
            ";

            await commandCreateDimRoom.ExecuteNonQueryAsync();
            await commandCreateDimRoom.DisposeAsync();
            await sqlConnection.CloseAsync();

            return null;
        }
         
         
         public static async Task<IActionResult> DimDateTable()
         {
             await using var sqlConnection = new SqlConnection(Dimensions.MuseumConnectionString);
             await sqlConnection.OpenAsync();
             var commandCreateDimDate = sqlConnection.CreateCommand();
             
             commandCreateDimDate.CommandText =
                 @"CREATE TABLE museum.dbo.DIMENSION_DimDate
                 (D_ID int IDENTITY,
                CalendarDate DATE ,
                Year int,
                Month int,
                Day int,
                WeekDay int,
                MonthDay int,
                WeekDayname varchar (50),
                 MonthName varchar (50),
                  PRIMARY KEY (D_ID))";
             
             await commandCreateDimDate.ExecuteNonQueryAsync();
             await commandCreateDimDate.DisposeAsync();
             await sqlConnection.CloseAsync();

             return null;
         }
         
         public static async Task<IActionResult> FMeasurementsTable()
         {
             await using var sqlConnection = new SqlConnection(Dimensions.MuseumConnectionString);
             await sqlConnection.OpenAsync();
             var commandCreateMeasurementsFTable = sqlConnection.CreateCommand();

             commandCreateMeasurementsFTable.CommandText =
                 @"CREATE TABLE museum.dbo.FACT_Measurement
                 (
                
                 R_ID int FOREIGN KEY REFERENCES DIMENSION_DimRoom(R_ID), 
                 D_ID int FOREIGN KEY REFERENCES DIMENSION_DimDate(D_ID), 
                 locationCode varchar (10),
                MeasurementDate DATE ,
                    lightMeasurement int,
            temperatureMeasurement int,
                humidityMeasurement int,
                    co2Measurement int)
                 ";
             
             await commandCreateMeasurementsFTable.ExecuteNonQueryAsync();
             await commandCreateMeasurementsFTable.DisposeAsync();
             await sqlConnection.CloseAsync();

             return null;
         }
         
         public static async Task<IActionResult> LastUpdateTable()
         {
             await using var sqlConnection = new SqlConnection(Dimensions.MuseumConnectionString);
             await sqlConnection.OpenAsync();
             var commandCreateLastUpdate = sqlConnection.CreateCommand();
            
             commandCreateLastUpdate.CommandText = 
                 @"CREATE TABLE museum.dbo.DIMENSION_LastUpdated
                 (
                 LastUpdate Date);
                 ";
             
             await commandCreateLastUpdate.ExecuteNonQueryAsync();
             await commandCreateLastUpdate.DisposeAsync();
             await sqlConnection.CloseAsync(); 

             return null;
         }
         
      
    }
}