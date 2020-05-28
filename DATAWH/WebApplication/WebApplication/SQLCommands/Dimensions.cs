using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Data.SqlClient;
using Microsoft.Extensions.Logging;
using NLog;

namespace WebApplication.SQLCommands
{
    public static class Dimensions
    {
        private const string WarehouseConnectionString =
            "Server=sqlserversss.database.windows.net;Database=museum.warehouse;User Id=museum;password=Mus12345;MultipleActiveResultSets=True;";
        
        private const string StagingConnectionString =
            "Server=sqlserversss.database.windows.net;Database=museum.staging;User Id=museum;password=Mus12345;MultipleActiveResultSets=True;";
        
        private const string MuseumConnectionString =
            "Server=sqlserversss.database.windows.net;Database=museum;User Id=museum;password=Mus12345;MultipleActiveResultSets=True;";

        

         public static async Task<IActionResult> DimRoomTable()
        {
            await using var sqlConnection = new SqlConnection(Dimensions.WarehouseConnectionString);
            await sqlConnection.OpenAsync();
            var commandCreateDimRoom = sqlConnection.CreateCommand();

            commandCreateDimRoom.CommandText = 
                @"CREATE TABLE dim_room
            (
            R_ID int IDENTITY, 
            locationCode char (10),
            Description char (50),
            optimalLight int,
            optimalTemperature int,
            optimalHumidity int,
            optimalCo2 int,
            ValidFrom DATE,
            ValidTo DATE,
            PRIMARY KEY (R_ID));
            ";

            await commandCreateDimRoom.ExecuteNonQueryAsync();
            await commandCreateDimRoom.DisposeAsync();
          

            return null;
        }
         
         public static async Task<IActionResult> DimDateTable()
         {
             await using var sqlConnection = new SqlConnection(Dimensions.WarehouseConnectionString);
             await sqlConnection.OpenAsync();
             var commandCreateDimDate = sqlConnection.CreateCommand();
             
             commandCreateDimDate.CommandText =
                 @"CREATE TABLE dim_date
                 (
                 D_ID int IDENTITY,
                 DateID nvarchar(10),
                 Year int, 
                 Month int, 
                 Day int, 
                 WeekDay int, 
                 MonthDay int, 
                 WeekDayName nvarchar(50), 
                 MonthName nvarchar(50), 
                 PRIMARY KEY (D_ID));
                 ";
             
             await commandCreateDimDate.ExecuteNonQueryAsync();
             await commandCreateDimDate.DisposeAsync();

             return null;
         }
         
         public static async Task<IActionResult> FMeasurementsTable()
         {
             await using var sqlConnection = new SqlConnection(Dimensions.WarehouseConnectionString);
             await sqlConnection.OpenAsync();
             var commandCreateMeasurementsFTable = sqlConnection.CreateCommand();

             commandCreateMeasurementsFTable.CommandText =
                 @"CREATE TABLE F_Measurements 
                 (
                 M_ID int identity, 
                 R_ID int FOREIGN KEY REFERENCES dim_room(R_ID), 
                 D_ID int FOREIGN KEY REFERENCES dim_date(D_ID), 
                 locationCode nvarchar(10), DateID nvarchar(10), 
                 lightMeasurement decimal, temperatureMeasurement decimal, 
                 humidityMeasurement decimal, 
                 co2Measurement decimal, 
                 PRIMARY KEY (M_ID))
                 ";
             
             await commandCreateMeasurementsFTable.ExecuteNonQueryAsync();
             await commandCreateMeasurementsFTable.DisposeAsync();

             return null;
         }
         
         public static async Task<IActionResult> LastUpdateTable()
         {
             await using var sqlConnection = new SqlConnection(Dimensions.WarehouseConnectionString);
             await sqlConnection.OpenAsync();
             var commandCreateLastUpdate = sqlConnection.CreateCommand();
            
             commandCreateLastUpdate.CommandText = 
                 @"CREATE TABLE LastUpdate 
                 (
                 LastUpdate Date);
                 ";
             
             await commandCreateLastUpdate.ExecuteNonQueryAsync();
             await commandCreateLastUpdate.DisposeAsync();

             return null;
         }
    }
}