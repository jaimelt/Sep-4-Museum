use museum

CREATE TABLE DIMENSION_DimRoom (	--Creates room dimension table
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

CREATE TABLE DIMENSION_DimDate(	--creates date dimention table
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


-- Creates the time dimension table
CREATE TABLE [dbo].[DimTime](
    [TimeId] [int] IDENTITY(1,1) NOT NULL,
    [Time] [time](0) NULL,
    [Hour] [int] NULL,
    [Minute] [int] NULL,
    [MilitaryHour] int NOT null,
    [MilitaryMinute] int NOT null,
    [AMPM] [varchar](2) NOT NULL,
    [DayPartEN] [varchar](10) NULL,
    [HourFromTo12] [varchar](17) NULL,
    [HourFromTo24] [varchar](13) NULL,
    [Notation12] [varchar](10) NULL,
    [Notation24] [varchar](10) NULL
);

CREATE TABLE FACT_Measurement (	--create measurements fact table
R_ID int FOREIGN KEY REFERENCES DIMENSION_DimRoom(R_ID),
D_ID int FOREIGN KEY REFERENCES DIMENSION_DimDate(D_ID),
T_ID int,
lightMeasurement int,
temperatureMeasurement int,
humidityMeasurement int,
co2Measurement int,
);


CREATE TABLE DIMENSION_LastUpdated(	--creates lastupdate table
	LastUpdate datetime
);


--creates the staging area tables
CREATE TABLE STAGE_DimRoom
(	
locationCode nvarchar(10),
Description nvarchar(50),
optimalLight int,
optimalTemperature int,
optimalHumidity int,
optimalCo2 int
);

CREATE TABLE STAGE_FactMeasurements
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

select * from [STAGE_DimRoom]

CREATE TABLE [STAGE_DimDate](
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

INSERT INTO[STAGE_DimRoom]
(
locationCode,
Description,
optimalLight,
optimalTemperature,
optimalHumidity,
optimalCo2
)
SELECT LocationCode, Description, Light, Temperature, Humidity, Co2 FROM [museum].[dbo].[Rooms]

--Populate the stage date table

go
DECLARE @StartDate DATETIME
DECLARE @EndDate DATETIME
SET @StartDate='2020-05-21'
SET @EndDate= DATEADD(d, 15, @StartDate)
WHILE @StartDate<= @EndDate
BEGIN INSERT INTO [STAGE_DimDate]
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

--Populate the time dimension table


-- Needed if the dimension already existed
-- with other column, otherwise the validation
-- of the insert could fail.
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
 
    INSERT INTO DimTime ([Time]
                       , [Hour]
                       , [Minute]
                       , [MilitaryHour]
                       , [MilitaryMinute]
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
 
    -- Raise time with one minute
    SET @Time = DATEADD(minute, 1, @Time);
 
    -- Raise counter by one
    set @counter = @counter + 1;
END

--Populate the DW dimention tables (point 3)
INSERT INTO DIMENSION_DimRoom(locationCode,
Description,
optimalLight,
optimalTemperature,
optimalHumidity,
optimalCo2)(SELECT locationCode,
Description,
optimalLight,
optimalTemperature,
optimalHumidity,
optimalCo2 FROM STAGE_DimRoom);
UPDATE DIMENSION_DimRoom set ValidFrom='2020-05-21';
UPDATE DIMENSION_DimRoom set ValidTo='2099-12-31';

INSERT INTO DIMENSION_DimDate(CalendarDate, Year, Month, Day, WeekDay,MonthDay, WeekDayname, MonthName)(SELECT CalendarDate, Year, Month, Day, WeekDay,MonthDay, WeekDayname, MonthName FROM STAGE_DimDate);



--Populate stating fact table(point 4)
SELECT * FROM STAGE_FactMeasurements;

INSERT INTO[museum].[dbo].[STAGE_FactMeasurements](
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
JOIN museum.dbo.RoomMeasurements rm on r.LocationCode = rm.roomNo
WHERE rm.MeasurementDate <= '2020-05-25'

--Key lookup for surrogate keys (point 5)

UPDATE STAGE_FactMeasurements
SET R_ID=( SELECT R_ID FROM museum.dbo.DIMENSION_DimRoom r WHERE r.locationCode = STAGE_FactMeasurements.locationCode);

UPDATE STAGE_FactMeasurements
SET D_ID=( SELECT D_ID FROM museum.dbo.DIMENSION_DimDate d WHERE d.CalendarDate=STAGE_FactMeasurements.MeasurementDate);



--Populate fact table in DW(point 6)
INSERT INTO dbo.FACT_Measurement(
R_ID,
D_ID,
lightMeasurement,
temperatureMeasurement,
humidityMeasurement,
co2Measurement)
(SELECT 
R_ID,
D_ID,
lightMeasurement,
temperatureMeasurement,
humidityMeasurement,
co2Measurement FROM STAGE_FactMeasurements);
SELECT *FROM FACT_Measurement;




--Set lastUpdated teble to '2020-05-25'(point 7)
INSERT INTO dbo.DIMENSION_LastUpdated(LastUpdate) VALUES ('2020-05-20');
select * from DIMENSION_LastUpdated;


use museum

------START OF THE INCREMENTAL LOAD------

-- create temporary dimension tables which will be used 
-- for the incremental load of the data

CREATE TABLE Temp_DimRoom
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

CREATE TABLE Temp_Fact_Measurements
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
	Co2
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
	12
)



-- remove Room 


go
delete from museum.dbo.Rooms
where LocationCode = 'DEL1';


-- edit room measurements

go 
update museum.dbo.Rooms
set Light=123, Temperature = 18, Humidity = 10,Co2 = 21
where LocationCode='C1'

use museum

--------CHECK FOR ADDED ROWS

go 
insert into DIMENSION_DimRoom
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
select r.LocationCode, r.Description, r.Light, r.Temperature, r.Humidity, r.Co2, (select LastUpdate from DIMENSION_LastUpdated), '9999-12-31' from [museum].[dbo].[Rooms] r
where LocationCode in
(

	(
		select LocationCode from [museum].[dbo].[Rooms] r
	)
	except
	(
		select LocationCode from DIMENSION_DimRoom
	)

)

use museum


------CHECK FOR DELETED ROWS

go
update dbo.DIMENSION_DimRoom
set ValidTo = (select LastUpdate from DIMENSION_LastUpdated)
where locationCode in
(
	(
		select locationCode from dbo.DIMENSION_DimRoom
	)
	except
	(
		select locationCode from [museum].[dbo].[Rooms]
	)
)

-- code for handling changes in the dimension data

-- for room

use museum

go 
insert into Temp_DimRoom
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
	)






--here we upadted the rows that already exist

update DIMENSION_DimRoom set ValidTo = (select LastUpdate from DIMENSION_LastUpdated)
where locationCode in
( 
	select locationCode from Temp_DimRoom
)

--insert the newly added rows into the dimension tables


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
)select locationCode, Description, optimalLight, optimalTemperature, optimalHumidity, optimalCo2, (select LastUpdate from DIMENSION_LastUpdated), '9999-12-31' from Temp_DimRoom

use museum


------Inserting the data from the Tempoary fact measurements table
------to the final fact table

use museum

go
insert into Temp_Fact_Measurements
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
where rm.MeasurementDate > (select LastUpdate from DIMENSION_LastUpdated)

go
update Temp_Fact_Measurements
set R_ID = 
(
	select R_ID from DIMENSION_DimRoom
	where DIMENSION_DimRoom.locationCode = Temp_Fact_Measurements.locationCode
	and ValidTo = '9999-12-31'
);
update Temp_Fact_Measurements
set D_ID = 
(
	select D_ID from DIMENSION_DimDate
	where DIMENSION_DimDate.CalendarDate = Temp_Fact_Measurements.MeasurementDate
);


use museum

insert into FACT_Measurement
(
	R_ID,
	D_ID,
	lightMeasurement,
	temperatureMeasurement,
	humidityMeasurement,
	co2Measurement
)
select R_ID, D_ID, lightMeasurement, temperatureMeasurement, humidityMeasurement,co2Measurement from Temp_Fact_Measurements

-- When the Incremental load is done, the LastUpdate will be 
-- reseted to the current date

update DIMENSION_LastUpdated
set LastUpdate = (select GETDATE());



