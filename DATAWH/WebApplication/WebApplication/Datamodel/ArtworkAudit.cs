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
                bool addToList = false;
                RoomMeasurement actualRoom = MeasurementList.getRoomMeasurementByRoomNo(a.Location);
                if (a.MaxCo2 < actualRoom.Co2 && a.MinCo2 > actualRoom.Co2)
                {
                    a.Comment += "Bad Co2 Levels,";
                    addToList = true;
                }

                if (a.MaxHumidity < actualRoom.Humidity && a.MinHumidity > actualRoom.Humidity)
                {
                    a.Comment += "Bad Humidity Levels,";
                    addToList = true;
                }

                if (a.MaxLight < actualRoom.Light && a.MinLight > actualRoom.Light)
                {
                    a.Comment += "Bad Light Levels,";
                    addToList = true;
                }

                if (a.MaxTemperature < actualRoom.Temperature && a.MinTemperature > actualRoom.Temperature)
                {
                    a.Comment += "Bad Temperature Levels,";
                    addToList = true;
                }

                if (addToList == true)
                    Artworks.artworks.Add(a);

            }   
        }
        
        
        
        

    }
}