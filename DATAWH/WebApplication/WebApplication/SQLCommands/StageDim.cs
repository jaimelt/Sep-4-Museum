using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Data.SqlClient;

namespace WebApplication.SQLCommands
{
    public static class StageDim
    {
        private const string MuseumConnectionString =
            "Server=sqlserversss.database.windows.net;Database=museum;User Id=museum;password=Mus12345;MultipleActiveResultSets=True;";


        public static async Task<IActionResult> StageDimRoom()
        {
            await using var sqlConnection = new SqlConnection(StageDim.MuseumConnectionString);
            await sqlConnection.OpenAsync();
            var commandCreateDimRoom = sqlConnection.CreateCommand();

            commandCreateDimRoom.CommandText =
                @"CREATE TABLE STAGE_dimRoom 
(	
locationCode varchar (10),
Description varchar (50),
optimalLight int,
optimalTemperature int,
optimalHumidity int,
optimalCo2 int
);
            ";

            await commandCreateDimRoom.ExecuteNonQueryAsync();
            await commandCreateDimRoom.DisposeAsync();
            await sqlConnection.CloseAsync();

            return null;
        }

        public static async Task<IActionResult> StageFactMeasurements()
        {
            await using var sqlConnection = new SqlConnection(StageDim.MuseumConnectionString);
            await sqlConnection.OpenAsync();
            var commandCreateDimRoom = sqlConnection.CreateCommand();

            commandCreateDimRoom.CommandText =
                @"CREATE TABLE STAGE_FactMeasurements
            (	
                [R_ID] [int] NULL,
                [D_ID] [int] NULL,
                [locationCode] [nvarchar](10) NULL,
                [MeasurementDate] [datetime] NULL,
                [lightMeasurement] [int] NULL,
                [temperatureMeasurement] [int] NULL,
                [humidityMeasurement] [int] NULL,
                [co2Measurement] [int] NULL);   ";


            await commandCreateDimRoom.ExecuteNonQueryAsync();
            await commandCreateDimRoom.DisposeAsync();
            await sqlConnection.CloseAsync();

            return null;
        }

        public static async Task<IActionResult> StageDimDate()
        {
            await using var sqlConnection = new SqlConnection(StageDim.MuseumConnectionString);
            await sqlConnection.OpenAsync();
            var commandCreateDimRoom = sqlConnection.CreateCommand();

            commandCreateDimRoom.CommandText =
                @"CREATE TABLE [STAGE_DimDate](
                  D_ID int IDENTITY,
                  CalendarDate DATE ,
                  Year int,
                  Month int,
                  Day int,
                  WeekDay int,
                  MonthDay int,
                  WeekDayname varchar (50),
                  MonthName varchar (50))  ";


            await commandCreateDimRoom.ExecuteNonQueryAsync();
            await commandCreateDimRoom.DisposeAsync();
            await sqlConnection.CloseAsync();

            return null;
        }

        public static async Task PopulateStageDimDate()
        {
            await using var sqlConnection = new SqlConnection(StageDim.MuseumConnectionString);
            await sqlConnection.OpenAsync();
            var commandCreateDimRoom = sqlConnection.CreateCommand();

            commandCreateDimRoom.CommandText =
                @"
                            go
            DECLARE @StartDate DATETIME
            DECLARE @EndDate DATETIME
            SET @StartDate='2018-01-01'
            SET @EndDate= DATEADD(d, 2000, @StartDate)
            WHILE @StartDate<= @EndDate
            BEGIN INSERT INTO [DIMENSION_DimDate]
            (CalendarDate, Year, Month, Day, WeekDay,MonthDay, WeekDayname, MonthName)
            SELECT
            @StartDate,
            DATENAME(YEAR, @StartDate),
            DATEPART(MONTH, @StartDate),
            DATEPART(DAY, @StartDate),
            DATEPART(WEEKDAY,@startDate),
            DATEPART(DAY, @StartDate),
            DATENAME(WEEKDAY, @StartDate),
            DATENAME(MONTH, @StartDate)
            SET @StartDate= DATEADD(dd, 1, @StartDate)
               END ";


            await commandCreateDimRoom.ExecuteNonQueryAsync();
            await commandCreateDimRoom.DisposeAsync();
            await sqlConnection.CloseAsync();
        }

        public static async Task<IActionResult> PopulateStageDimRoom()
        {
            await using var sqlConnection = new SqlConnection(StageDim.MuseumConnectionString);
            await sqlConnection.OpenAsync();
            var commandCreateDimRoom = sqlConnection.CreateCommand();

            commandCreateDimRoom.CommandText =
                @"INSERT INTO STAGE_DimRoom
(
locationCode,
Description,
optimalCo2,
optimalHumidity,
optimalTemperature,
optimalLight
)
SELECT LocationCode, Description, Light, Temperature, Humidity, Co2 FROM Rooms";


            await commandCreateDimRoom.ExecuteNonQueryAsync();
            await commandCreateDimRoom.DisposeAsync();
            await sqlConnection.CloseAsync();

            return null;
        }

        public static async Task<IActionResult> PopulateStageFactMeasurements()
        {
            await using var sqlConnection = new SqlConnection(StageDim.MuseumConnectionString);
            await sqlConnection.OpenAsync();
            var commandCreateDimRoom = sqlConnection.CreateCommand();

            commandCreateDimRoom.CommandText =
                @" INSERT INTO museum.dbo.STAGE_FactMeasurements(
locationCode,
MeasurementDate,
lightMeasurement,
temperatureMeasurement,
humidityMeasurement,
co2Measurement)
SELECT
LocationCode,
rm.MeasurementDate,
rm.Light,
rm.Temperature,
rm.Humidity,
rm.Co2
FROM museum.dbo.Rooms r
JOIN museum.dbo.RoomMeasurements rm on r.LocationCode = rm.roomNo
WHERE rm.MeasurementDate <= '2020-05-25T00:00:00'";


            await commandCreateDimRoom.ExecuteNonQueryAsync();
            await commandCreateDimRoom.DisposeAsync();
            await sqlConnection.CloseAsync();

            return null;
        }

        public static async Task<IActionResult> UpdateKeysStageFactMeasurements()
        {
            await using var sqlConnection = new SqlConnection(StageDim.MuseumConnectionString);
            await sqlConnection.OpenAsync();
            var coomand1 = sqlConnection.CreateCommand();

            coomand1.CommandText =
                @"UPDATE museum.dbo.STAGE_FactMeasurements SET R_ID=
                ( SELECT R_ID FROM museum.dbo.DIMENSION_DimRoom r WHERE r.locationCode = 
                STAGE_FactMeasurements.locationCode);";


            var command2 = sqlConnection.CreateCommand();

            command2.CommandText =
                @" UPDATE museum.dbo.STAGE_FactMeasurements SET D_ID=
                (SELECT   TOP 1 D_ID FROM museum.dbo.DIMENSION_DimDate d  WHERE d.CalendarDate = 
                CONVERT (DATE,STAGE_FactMeasurements.MeasurementDate))
    ;";


            await coomand1.ExecuteNonQueryAsync();
            await command2.ExecuteNonQueryAsync();
            await coomand1.DisposeAsync();
            await command2.DisposeAsync();
            await sqlConnection.CloseAsync();

            return null;
        }

        public static async Task<IActionResult> PopulateDimRoom()
        {
            await using var sqlConnection = new SqlConnection(StageDim.MuseumConnectionString);
            await sqlConnection.OpenAsync();
            var commandCreateDimRoom = sqlConnection.CreateCommand();

            commandCreateDimRoom.CommandText =
                @"INSERT INTO museum.dbo.DIMENSION_DimRoom
(
locationCode,
Description,
optimalLight,
optimalTemperature,
optimalHumidity,
optimalCo2
)
SELECT locationCode, Description, optimalLight, optimalTemperature, optimalHumidity, optimalCo2 FROM Museum.dbo.STAGE_DimRoom";


            await commandCreateDimRoom.ExecuteNonQueryAsync();
            await commandCreateDimRoom.DisposeAsync();
            await sqlConnection.CloseAsync();

            return null;
        }

        public static async Task<IActionResult> PopulateFactTable()
        {
            await using var sqlConnection = new SqlConnection(MuseumConnectionString);
            await sqlConnection.OpenAsync();
            var commandCreateDimRoom = sqlConnection.CreateCommand();

            commandCreateDimRoom.CommandText =
                @"INSERT INTO museum.dbo.FACT_Measurement
(
R_ID,
D_ID,
lightMeasurement,
temperatureMeasurement,
humidityMeasurement,
co2Measurement
)
SELECT R_ID, D_ID,  lightMeasurement, temperatureMeasurement, humidityMeasurement,co2Measurement FROM Museum.dbo.STAGE_FactMeasurements";


            await commandCreateDimRoom.ExecuteNonQueryAsync();
            await commandCreateDimRoom.DisposeAsync();
            await sqlConnection.CloseAsync();

            return null;
        }
    }
}