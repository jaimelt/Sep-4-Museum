using System;
using System.Collections.Generic;

namespace WebApplication.Datamodel
{
    public class ArtworkAudit
    {
        public ArtworkList Artworks { get; set; }
        
        public RoomMeasurementList MeasurementList { get; set; }

        public ArtworkAudit()
        {
            Artworks = new ArtworkList();
            MeasurementList = new RoomMeasurementList();
        }

        public void CheckArtworkConditions(List<Artwork> artworks)
        {
        
            foreach (var a in artworks)
            {
                if (!a.Location.Equals("Storage"))
                {
                    bool addToList = false;
                    Console.WriteLine("a location="+a.Location);
                    RoomMeasurement actualRoom = MeasurementList.getRoomMeasurementByRoomNo(a.Location);
                    if (actualRoom != null)
                    {
                        Console.WriteLine("aMaxCo2 ="+a.MaxCo2+"actualRoomCo2:"+actualRoom.Co2);
                        if (a.MaxCo2 < actualRoom.Co2 ||  a.MinCo2 > actualRoom.Co2)
                        {
                            a.Comment += "Bad Co2 Levels,";
                            addToList = true;
                        }

                        if (a.MaxHumidity < actualRoom.Humidity || a.MinHumidity > actualRoom.Humidity)
                        {
                            a.Comment += "Bad Humidity Levels,";
                            addToList = true;
                        }

                        if (a.MaxLight < actualRoom.Light || a.MinLight > actualRoom.Light)
                        {
                            a.Comment += "Bad Light Levels,";
                            addToList = true;
                        }

                        if (a.MaxTemperature < actualRoom.Temperature || a.MinTemperature > actualRoom.Temperature)
                        {
                            a.Comment += "Bad Temperature Levels,";
                            addToList = true;
                        }

                        if (addToList)
                            Artworks.artworks.Add(a);
                        Console.WriteLine("artwork added"+a.Author);
                    

                    }}
                    
               
            }   
        }
        
        
        
        

    }
}