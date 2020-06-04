using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Data.SqlClient;

namespace WebApplication.SQLCommands
{
    public class IncrementalLoad
    {
        internal const string MuseumConnectionString =
            "Server=sqlserversss.database.windows.net;Database=museum;User Id=museum;password=Mus12345;MultipleActiveResultSets=True;";


        public static async Task<IActionResult> CreateTempDimRoom()
        {
            await using var sqlConnection = new SqlConnection(StageDim.MuseumConnectionString);
            await sqlConnection.OpenAsync();
            var commandCreateDimRoom = sqlConnection.CreateCommand();

            commandCreateDimRoom.CommandText =
                @"CREATE TABLE Temp_DimRoom 
                (	
                    R_ID int NOT NULL ,
                    locationCode varchar (10) NULL,
                    Description varchar (50) NULL ,
                    optimalLight int NULL ,
                    optimalTemperature int NULL,
                    optimalHumidity int NULL,
                    optimalCo2 int NULL
                );";

            await commandCreateDimRoom.ExecuteNonQueryAsync();
            await commandCreateDimRoom.DisposeAsync();
            await sqlConnection.CloseAsync();

            return null;
        }
        
        public static async Task<IActionResult> CreateTempFactMeasurements()
        {
            await using var sqlConnection = new SqlConnection(StageDim.MuseumConnectionString);
            await sqlConnection.OpenAsync();
            var commandCreateDimRoom = sqlConnection.CreateCommand();

            commandCreateDimRoom.CommandText =
                @"CREATE TABLE Temp_Fact_Measurements
                (	
                [R_ID] [int] NULL,
                [D_ID] [int] NULL,
                [locationCode] [nvarchar](10) NULL,
                [MeasurementDate] [datetime] NULL,
                [lightMeasurement] [int] NULL,
                [temperatureMeasurement] [int] NULL,
                [humidityMeasurement] [int] NULL,
                [co2Measurement] [int] NULL
                );   ";


            await commandCreateDimRoom.ExecuteNonQueryAsync();
            await commandCreateDimRoom.DisposeAsync();
            await sqlConnection.CloseAsync();

            return null;
        }
        
        //A room is created to test the add functionality

        public static async Task<IActionResult> InsertIntoRoom()
        {
            await using var sqlConnection = new SqlConnection(StageDim.MuseumConnectionString);
            await sqlConnection.OpenAsync();
            var commandCreateDimRoom = sqlConnection.CreateCommand();

            commandCreateDimRoom.CommandText =
                @"INSERT INTO museum.dbo.Rooms
                (
                    locationCode,
                    Description,
                    CurrentCapacity,
                    TotalCapacity,
                    Light,
                    Temperature,
                    Humidity,
                    Co2
                )
                VALUES 
               (
                  'C1',
                  'A newly created room',
                15,
                25,
                567,
                23,
                47,
                12
               )";


            await commandCreateDimRoom.ExecuteNonQueryAsync();
            await commandCreateDimRoom.DisposeAsync();
            await sqlConnection.CloseAsync();

            return null;
        }


        //A room is deleted to test the delete functionality
        
        public static async Task<IActionResult> DeleteRoom()
        {
            await using var sqlConnection = new SqlConnection(StageDim.MuseumConnectionString);
            await sqlConnection.OpenAsync();
            var commandCreateDimRoom = sqlConnection.CreateCommand();

            commandCreateDimRoom.CommandText =
                @"
                delete from museum.dbo.Rooms
                where locationCode = 'DEL1';";


            await commandCreateDimRoom.ExecuteNonQueryAsync();
            await commandCreateDimRoom.DisposeAsync();
            await sqlConnection.CloseAsync();

            return null;
        }
        
        //A room is edited to test the edit functionality
        
        public static async Task<IActionResult> EditRoom()
        {
            await using var sqlConnection = new SqlConnection(StageDim.MuseumConnectionString);
            await sqlConnection.OpenAsync();
            var commandCreateDimRoom = sqlConnection.CreateCommand();

            commandCreateDimRoom.CommandText =
                @"
                update museum.dbo.Rooms
                set Light=100, Temperature=20, Humidity=30, Co2=40
                where locationCode = 'C1';";


            await commandCreateDimRoom.ExecuteNonQueryAsync();
            await commandCreateDimRoom.DisposeAsync();
            await sqlConnection.CloseAsync();

            return null;
        }
        
        //Here we check for added rows
        
        public static async Task<IActionResult> CheckAddedRows()
        {
            await using var sqlConnection = new SqlConnection(StageDim.MuseumConnectionString);
            await sqlConnection.OpenAsync();
            var commandCreateDimRoom = sqlConnection.CreateCommand();

            commandCreateDimRoom.CommandText =
                @"
insert into museum.dbo.DIMENSION_DimRoom
(
	locationCode,
	Description,
	optimalLight,
	optimalTemperature,
	optimalHumidity,
	optimalCo2,
	ValidFrom,
	ValidTo
)
select LocationCode, Description, r.Light, r.Temperature, r.Humidity, r.Co2, (select LastUpdate from museum.dbo.DIMENSION_LastUpdated), '9999-12-31' from [museum].[dbo].[Rooms] r
where LocationCode in
(

	(
		select LocationCode from [museum].[dbo].[Rooms] r
	)
	except
	(
		select locationCode from museum.dbo.DIMENSION_DimRoom
	)

)";


            await commandCreateDimRoom.ExecuteNonQueryAsync();
            await commandCreateDimRoom.DisposeAsync();
            await sqlConnection.CloseAsync();

            return null;
        }
        
        //Here we check for deleted rows
        
        public static async Task<IActionResult> CheckDeletedRows()
        {
            await using var sqlConnection = new SqlConnection(StageDim.MuseumConnectionString);
            await sqlConnection.OpenAsync();
            var commandCreateDimRoom = sqlConnection.CreateCommand();

            commandCreateDimRoom.CommandText =
                @"
                update museum.dbo.DIMENSION_DimRoom
                set ValidTo = (select LastUpdate from museum.dbo.DIMENSION_LastUpdated)
                where LocationCode in
                (

	                (
                        select locationCode from museum.dbo.DIMENSION_DimRoom
	                )
	                except
	                (
		                select LocationCode from [museum].[dbo].[Rooms] 
	                )

                )";


            await commandCreateDimRoom.ExecuteNonQueryAsync();
            await commandCreateDimRoom.DisposeAsync();
            await sqlConnection.CloseAsync();

            return null;
        }
        
        public static async Task<IActionResult> HandleChangesInDimRoom()
        {
            await using var sqlConnection = new SqlConnection(StageDim.MuseumConnectionString);
            await sqlConnection.OpenAsync();
            var commandCreateDimRoom = sqlConnection.CreateCommand();

            commandCreateDimRoom.CommandText =
                @"
insert into museum.dbo.Temp_DimRoom
(
	locationCode,
	Description,
	optimalLight,
	optimalTemperature,
	optimalHumidity,
	optimalCo2
)
select r.LocationCode, r.Description, r.Light, r.Temperature, r.Humidity, r.Co2  from [museum].[dbo].[Rooms] r  --source DB
except
		(
			select locationCode, Description, optimalLight, optimalTemperature, optimalHumidity, optimalCo2 from DIMENSION_DimRoom dr -- room dimension
			where ValidTo > (select LastUpdate from DIMENSION_LastUpdated)
		)
except
	(
		select LocationCode, Description, r.Light, r.Temperature, r.Humidity, r.Co2 from [museum].[dbo].[Rooms] r --source DB
		where LocationCode not in --business key 
		(
			select LocationCode from DIMENSION_DimRoom
		)
	)";


            await commandCreateDimRoom.ExecuteNonQueryAsync();
            await commandCreateDimRoom.DisposeAsync();
            await sqlConnection.CloseAsync();

            return null;
        }
        
        //Update the rows that already exist
        
        public static async Task<IActionResult> Update()
        {
	        await using var sqlConnection = new SqlConnection(StageDim.MuseumConnectionString);
	        await sqlConnection.OpenAsync();
	        var commandCreateDimRoom = sqlConnection.CreateCommand();

	        commandCreateDimRoom.CommandText =
		        @"update museum.dbo.DIMENSION_DimRoom 
set ValidTo = (select LastUpdate from museum.dbo.DIMENSION_LastUpdated)
where locationCode in
( 
	select locationCode from museum.dbo.Temp_DimRoom
)";


	        await commandCreateDimRoom.ExecuteNonQueryAsync();
	        await commandCreateDimRoom.DisposeAsync();
	        await sqlConnection.CloseAsync();

	        return null;
        }
        
        //insert the newly added rows into the dimension tables
        
        public static async Task<IActionResult> InsertNewRowsInDimRoom()
        {
	        await using var sqlConnection = new SqlConnection(StageDim.MuseumConnectionString);
	        await sqlConnection.OpenAsync();
	        var commandCreateDimRoom = sqlConnection.CreateCommand();

	        commandCreateDimRoom.CommandText =
		        @"insert into museum.dbo.DIMENSION_DimRoom
(
	locationCode,
	Description,
	optimalLight,
	optimalTemperature,
	optimalHumidity,
	optimalCo2,
	ValidFrom,
	ValidTo
)select locationCode, Description, optimalLight, optimalTemperature, optimalHumidity, optimalCo2, (select LastUpdate from DIMENSION_LastUpdated), '9999-12-31' from museum.dbo.Temp_DimRoom";


	        await commandCreateDimRoom.ExecuteNonQueryAsync();
	        await commandCreateDimRoom.DisposeAsync();
	        await sqlConnection.CloseAsync();

	        return null;
        }
        
        //Inserting the data from the Tempoary fact measurements table
        //to the final fact table
        
        public static async Task<IActionResult> InsertNewRowsInTemp_F_Measurements()
        {
	        await using var sqlConnection = new SqlConnection(StageDim.MuseumConnectionString);
	        await sqlConnection.OpenAsync();
	        var commandCreateDimRoom = sqlConnection.CreateCommand();

	        commandCreateDimRoom.CommandText =
		        @"
insert into museum.dbo.Temp_Fact_Measurements
(
	locationCode,
	MeasurementDate,
	lightMeasurement,
	temperatureMeasurement,
	humidityMeasurement,
	co2Measurement
)
select LocationCode, rm.MeasurementDate, r.Light, r.Temperature, r.Humidity, r.Co2 from [museum].[dbo].[Rooms] r
join [museum].[dbo].[RoomMeasurements] rm on r.LocationCode = rm.roomNo
where rm.MeasurementDate > (select LastUpdate from museum.dbo.DIMENSION_LastUpdated)
";


	        await commandCreateDimRoom.ExecuteNonQueryAsync();
	        await commandCreateDimRoom.DisposeAsync();
	        await sqlConnection.CloseAsync();

	        return null;
        }
        
        public static async Task<IActionResult> SetFKInTemp_F_Measurements()
        {
	        await using var sqlConnection = new SqlConnection(StageDim.MuseumConnectionString);
	        await sqlConnection.OpenAsync();
	        var commandCreateDimRoom = sqlConnection.CreateCommand();

	        commandCreateDimRoom.CommandText =
		        @"
update museum.dbo.Temp_Fact_Measurements
set R_ID = 
(
	select R_ID from museum.dbo.DIMENSION_DimRoom dr
	where dr.locationCode = Temp_Fact_Measurements.locationCode
	and ValidTo = '9999-12-31'
);
update museum.dbo.Temp_Fact_Measurements
set D_ID = 
(
	select D_ID from museum.dbo.DIMENSION_DimDate dd
	where dd.CalendarDate = Temp_Fact_Measurements.MeasurementDate
);";


	        await commandCreateDimRoom.ExecuteNonQueryAsync();
	        await commandCreateDimRoom.DisposeAsync();
	        await sqlConnection.CloseAsync();

	        return null;
        }
        
        public static async Task<IActionResult> TransferDataToFact()
        {
	        await using var sqlConnection = new SqlConnection(StageDim.MuseumConnectionString);
	        await sqlConnection.OpenAsync();
	        var commandCreateDimRoom = sqlConnection.CreateCommand();

	        commandCreateDimRoom.CommandText =
		        @"insert into museum.dbo.FACT_Measurement
(
 	R_ID,
 	D_ID,
	lightMeasurement,
 	temperatureMeasurement,
 	humidityMeasurement,
 	co2Measurement
)select R_ID, D_ID, lightMeasurement, temperatureMeasurement, humidityMeasurement,co2Measurement from museum.dbo.Temp_Fact_Measurements";


	        await commandCreateDimRoom.ExecuteNonQueryAsync();
	        await commandCreateDimRoom.DisposeAsync();
	        await sqlConnection.CloseAsync();

	        return null;
        }
        
        // When the Incremental load is done, the LastUpdate will be 
        // reseted to the current date
        
        public static async Task<IActionResult> UpdateLastUpdatedField()
        {
	        await using var sqlConnection = new SqlConnection(StageDim.MuseumConnectionString);
	        await sqlConnection.OpenAsync();
	        var commandCreateDimRoom = sqlConnection.CreateCommand();

	        commandCreateDimRoom.CommandText =
		        @"update museum.dbo.DIMENSION_LastUpdated
					set LastUpdate = (select GETDATE())";


	        await commandCreateDimRoom.ExecuteNonQueryAsync();
	        await commandCreateDimRoom.DisposeAsync();
	        await sqlConnection.CloseAsync();

	        return null;
        }
        
        
    }
}