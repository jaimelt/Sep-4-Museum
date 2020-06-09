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
         
         public static async Task<IActionResult> CreateDimTableAndPopulate()
         {
             await using var sqlConnection = new SqlConnection(Dimensions.MuseumConnectionString);
             await sqlConnection.OpenAsync();
             var commandCreateLastUpdate = sqlConnection.CreateCommand();
            
             commandCreateLastUpdate.CommandText = 
                 @"CREATE TABLE [DimTime]
(
    [TimeId] [int] IDENTITY(1,1) NOT NULL,
    [Time] [time](0) NULL,
    [Hour] [int] NULL,
    [Minute] [int] NULL,
    [AMPM] [varchar](2) NOT NULL,
    [DayPartEN] [varchar](10) NULL,
    [HourFromTo12] [varchar](17) NULL,
    [HourFromTo24] [varchar](13) NULL,
    [Notation12] [varchar](10) NULL,
    [Notation24] [varchar](10) NULL
);
 

GO
 
-- Create a time and a counter variable for the loop
DECLARE @Time as time;
SET @Time = '0:00';
 
DECLARE @counter as int;
SET @counter = 0;
 
 
-- Two variables to store the day part for two languages
DECLARE @daypartEN as varchar(20);
set @daypartEN = '';

 
 
-- Loop 1440 times (24hours * 60minutes)
WHILE @counter < 1440
BEGIN
 
    -- Determine datepart
    SELECT  @daypartEN = CASE
                         WHEN (@Time >= '0:00' and @Time < '6:00') THEN 'Night'
                         WHEN (@Time >= '6:00' and @Time < '12:00') THEN 'Morning'
                         WHEN (@Time >= '12:00' and @Time < '18:00') THEN 'Afternoon'
                         ELSE 'Evening'
                         END
    ,   
 
    INSERT INTO DimTime ([Time]
                       , [Hour]
                       , [Minute]
                       , [AMPM]
                       , [DayPartEN]           
                       , [HourFromTo12]
                       , [HourFromTo24]
                       , [Notation12]
                       , [Notation24])
                VALUES (@Time
                       , DATEPART(Hour, @Time) + 1
                       , DATEPART(Minute, @Time) + 1
                       , DATEPART(Hour, @Time)
                       , DATEPART(Minute, @Time)
                       , CASE WHEN (DATEPART(Hour, @Time) < 12) THEN 'AM' ELSE 'PM' END
                       , @daypartEN                
                       , CONVERT(varchar(10), DATEADD(Minute, -DATEPART(Minute,@Time), @Time),100)  + ' - ' + CONVERT(varchar(10), DATEADD(Hour, 1, DATEADD(Minute, -DATEPART(Minute,@Time), @Time)),100)
                       , CAST(DATEADD(Minute, -DATEPART(Minute,@Time), @Time) as varchar(5)) + ' - ' + CAST(DATEADD(Hour, 1, DATEADD(Minute, -DATEPART(Minute,@Time), @Time)) as varchar(5))
                       , CONVERT(varchar(10), @Time,100)
                       , CAST(@Time as varchar(5))
                       );
 

    SET @Time = DATEADD(minute, 1, @Time);
 

    set @counter = @counter + 1;
END";
             
             await commandCreateLastUpdate.ExecuteNonQueryAsync();
             await commandCreateLastUpdate.DisposeAsync();
             await sqlConnection.CloseAsync(); 

             return null;
         }
         
      
    }
}