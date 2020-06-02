

CREATE TABLE dim_room (	--Creates room dimension table
R_ID int IDENTITY,
locationCode nvarchar(10),
Description nvarchar(50),
optimalLight int,
optimalTemperature int,
optimalHumidity int,
optimalCo2 int,
ValidFrom Datetime,
ValidTo Datetime
PRIMARY KEY (R_ID)
);

CREATE TABLE dim_date(	--creates date dimention table
D_ID int IDENTITY,
CalendarDate datetime,
Year int,
Month int,
Day int,
WeekDay int,
MonthDay int,
WeekDayname nvarchar(50),
MonthName nvarchar(50),
PRIMARY KEY (D_ID)
);

CREATE TABLE F_Measurements (	--create sales fact table
R_ID int FOREIGN KEY REFERENCES dim_room(R_ID),
D_ID int FOREIGN KEY REFERENCES dim_date(D_ID),
locationCode nvarchar(10),
MeasurementDate datetime,
lightMeasurement int,
temperatureMeasurement int,
humidityMeasurement int,
co2Measurement int,
);

CREATE TABLE LastUpdate(	--creates lastupdate table
	LastUpdate datetime
);


--creates the staging area tables
CREATE TABLE stage_dim_room 
(	
R_ID int,
locationCode nvarchar(10),
Description nvarchar(50),
optimalLight int,
optimalTemperature int,
optimalHumidity int,
optimalCo2 int
);

CREATE TABLE stage_fact_measurements
(
[R_ID] [int] NULL,
[D_ID] [int] NULL,
[locationCode] [nvarchar](10) NULL,
[MeasurementDate] [datetime] NULL,
[lightMeasurement] [int] NULL,
[temperatureMeasurement] [int] NULL,
[humidityMeasurement] [int] NULL,
[co2Measurement] [int] NULL
)

select * from [stage_dim_room]

CREATE TABLE [stage_dim_date](
D_ID int IDENTITY,
CalendarDate datetime,
Year int,
Month int,
Day int,
WeekDay int,
MonthDay int,
WeekDayname nvarchar(50),
MonthName nvarchar(50)
)



--Inserts data to the staging area(point 1)

INSERT INTO[stage_dim_room]
(
locationCode,
Description,
optimalLight,
optimalTemperature,
optimalHumidity,
optimalCo2
)
SELECT LocationCode, Description, Light, Temperature, Humidity, Co2 FROM [museum].[dbo].[Rooms] r


go
DECLARE @StartDate DATETIME
DECLARE @EndDate DATETIME
SET @StartDate='2020-05-21'
SET @EndDate= DATEADD(d, 15, @StartDate)
WHILE @StartDate<= @EndDate
BEGIN INSERT INTO [stage_dim_date]
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
END


--Populate the DW dimention tables (point 3)
INSERT INTO dbo.dim_room(locationCode,
Description,
optimalLight,
optimalTemperature,
optimalHumidity,
optimalCo2)(SELECT locationCode,
Description,
optimalLight,
optimalTemperature,
optimalHumidity,
optimalCo2 FROM stage_dim_room);
UPDATE dbo.dim_room set ValidFrom='2020-05-21';
UPDATE dbo.dim_room set ValidTo='2099-12-31';

INSERT INTO dbo.dim_date(CalendarDate, Year, Month, Day, WeekDay,MonthDay, WeekDayname, MonthName)(SELECT CalendarDate, Year, Month, Day, WeekDay,MonthDay, WeekDayname, MonthName FROM stage_dim_date);



--Populate stating fact table(point 4)
SELECT * FROM stage_fact_measurements;

INSERT INTO[musemDW].[dbo].[stage_fact_measurements](
locationCode,
MeasurementDate,
lightMeasurement,
temperatureMeasurement,
humidityMeasurement,
co2Measurement)
SELECT
LocationCode,
rm.MeasurementDate,
r.Light,
r.Temperature,
r.Humidity,
r.Co2
FROM museum.dbo.Rooms r
JOIN museum.dbo.RoomMeasurements rm on r.LiveRoomMeasurementsId = rm.Id
WHERE rm.MeasurementDate <= '2020-05-25'

--Key lookup for surrogate keys (point 5)

UPDATE stage_fact_measurements
SET R_ID=( SELECT R_ID FROM musemDW.dbo.dim_room r WHERE r.locationCode = stage_fact_measurements.locationCode);

UPDATE stage_fact_measurements
SET D_ID=( SELECT D_ID FROM musemDW.dbo.stage_dim_date d WHERE d.CalendarDate=stage_fact_measurements.MeasurementDate);



--Populate fact table in DW(point 6)
INSERT INTO dbo.F_Measurements(
R_ID,
D_ID,
locationCode,
MeasurementDate,
lightMeasurement,
temperatureMeasurement,
humidityMeasurement,
co2Measurement)
(SELECT 
R_ID,
D_ID,
locationCode,
MeasurementDate,
lightMeasurement,
temperatureMeasurement,
humidityMeasurement,
co2Measurement FROM stage_fact_measurements);
SELECT *FROM F_Measurements;




--Set lastUpdated teble to '2020-05-25'(point 7)
INSERT INTO dbo.LastUpdate(LastUpdate) VALUES ('2020-06-01');
select * from LastUpdate;


use musemDW

------START OF THE INCREMENTAL LOAD------

--create temporary dimension tables which will be used 
-- for the incremental load of the data

CREATE TABLE Temp_Room
(
	R_ID int NOT NULL,
	locationCode nvarchar(10) NULL,
	Description nvarchar(50) NULL,
	optimalLight int NULL,
	optimalTemperature int NULL,
	optimalHumidity int NULL,
	optimalCo2 int NULL
)

--create the temporary fact measurements table

CREATE TABLE Temp_F_Measurements
(
[R_ID] [int] NULL,
[D_ID] [int] NULL,
[locationCode] [nvarchar] NULL,
[MeasurementDate] [datetime] NULL,
[lightMeasurement] [int] NULL,
[temperatureMeasurement] [int] NULL,
[humidityMeasurement] [int] NULL,
[co2Measurement] [int] NULL
)

-- we  create a room to test the add and delete functionality

INSERT INTO [museum].[dbo].[Rooms]
(
	LocationCode,
	Description,
	CurrentCapacity,
	TotalCapacity,
	Light,
	Temperature,
	Humidity,
	Co2,
	LiveRoomMeasurementsId
)
VALUES
(
	'C1',
	'A special room',
	10,
	30,
	567,
	23,
	47,
	12,
	37
)

INSERT INTO [museum].[dbo].[RoomMeasurements]
(
	Light,
	Temperature,
	Humidity,
	Co2,
	MeasurementDate
)
VALUES
(
	300,
	20,
	50,
	35,
	'2020-06-01'
)

-- remove Room and Room measurement

--go
--UPDATE museum.dbo.Rooms
--set Description = 'A changed room'
--where LocationCode = 'B3';

go
delete from museum.dbo.Rooms
where LocationCode = 'C1';

go
delete from museum.dbo.RoomMeasurements
where Id = 38;

-- edit room measurements

go 
update museum.dbo.RoomMeasurements
set Light=123, Temperature = 18, Humidity = 10,Co2 = 21
where Id= 31;

use musemDW

--------CHECK FOR ADDED ROWS

go 
insert into dim_room
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
select LocationCode, Description, r.Light, r.Temperature, r.Humidity, r.Co2, (select LastUpdate from LastUpdate), '9999-12-31' from [museum].[dbo].[Rooms] r
join [museum].[dbo].[RoomMeasurements] rm on r.LiveRoomMeasurementsId = rm.Id
where LocationCode in
(

	(
		select LocationCode from [museum].[dbo].[Rooms] r
	)
	except
	(
		select LocationCode from dim_room
	)

)

use musemDW


------CHECK FOR DELETED ROWS

go
update dbo.dim_room
set ValidTo = (select LastUpdate from LastUpdate)
where locationCode in
(
	(
		select locationCode from dbo.dim_room
	)
	except
	(
		select locationCode from [museum].[dbo].[Rooms]
	)
)

-- code for handling changes in the dimension data

-- for room

go 
insert into Temp_Room
(
	locationCode,
	Description,
	optimalLight,
	optimalTemperature,
	optimalHumidity,
	optimalCo2
)
(

	(
	
		(
			select LocationCode, Description, r.Light, r.Temperature, r.Humidity, r.Co2  from [museum].[dbo].[Rooms] r
		)
		except
		(
			select locationCode, Description, optimalLight, optimalTemperature, optimalHumidity, optimalCo2 from dim_room
			where ValidTo > (select LastUpdate from LastUpdate)
		)

	)
	except
	(
		select LocationCode, Description, r.Light, r.Temperature, r.Humidity, r.Co2 from [museum].[dbo].[Rooms] r
		where LocationCode not in
		(
			select LocationCode from dim_room
		)
	)

)



--here we upadted the rows that already exist

update dim_room set ValidTo = (select LastUpdate from LastUpdate)
where locationCode in
( 
	select locationCode from Temp_Room
)

--insert the newly added rows into the dimension tables


insert into musemDW.dbo.dim_room
(
	locationCode,
	Description,
	optimalLight,
	optimalTemperature,
	optimalHumidity,
	optimalCo2,
	ValidFrom,
	ValidTo
)select locationCode, Description, optimalLight, optimalTemperature, optimalHumidity, optimalCo2, (select LastUpdate from LastUpdate), '9999-12-31' from Temp_Room

use musemDW


------Inserting the data from the Tempoary fact measurements table
------to the final fact table

go
insert into Temp_F_Measurements
(
	locationCode,
	MeasurementDate,
	lightMeasurement,
	temperatureMeasurement,
	humidityMeasurement,
	co2Measurement
)
select LocationCode, rm.MeasurementDate, r.Light, r.Temperature, r.Humidity, r.Co2 from [museum].[dbo].[Rooms] r
join [museum].[dbo].[RoomMeasurements] rm on r.LiveRoomMeasurementsId = rm.Id
where rm.MeasurementDate > (select LastUpdate from LastUpdate)

go
update Temp_F_Measurements
set R_ID = 
(
	select R_ID from dim_room
	where dim_room.locationCode = Temp_F_Measurements.locationCode
	and ValidTo = '9999-12-31'
);
update Temp_F_Measurements
set D_ID = 
(
	select D_ID from dim_date
	where dim_date.CalendarDate = Temp_F_Measurements.MeasurementDate
);


use musemDW

insert into F_Measurements
(
	locationCode,
	MeasurementDate,
	lightMeasurement,
	temperatureMeasurement,
	humidityMeasurement,
	co2Measurement
)
select locationCode, MeasurementDate, lightMeasurement, temperatureMeasurement, humidityMeasurement,co2Measurement from Temp_F_Measurements

-- When the Incremental load is done, the LastUpdate will be 
-- reseted to the current date

update LastUpdate
set LastUpdate = (select GETDATE());



