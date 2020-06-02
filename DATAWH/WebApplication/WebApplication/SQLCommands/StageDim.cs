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
R_ID int,
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
[co2Measurement] [int] NULL
);
            ";

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
MonthName varchar (50)
)
            ";

            await commandCreateDimRoom.ExecuteNonQueryAsync();
            await commandCreateDimRoom.DisposeAsync();
            await sqlConnection.CloseAsync();

            return null;
        }
    }
}