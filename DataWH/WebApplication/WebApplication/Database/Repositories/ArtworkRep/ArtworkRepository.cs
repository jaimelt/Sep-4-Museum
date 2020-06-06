using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Query;
using WebApplication.Database;
using WebApplication.Database.Repositories;
using WebApplication.Datamodel;
using WebApplication.Database.Repositories;


namespace WebApplication.Database.Repositories.ArtworkRep
{

 

    public class ArtworkRepository : RepositoryBase<Artwork>, IArtworkRepository
    {
        public ArtworkRepository(MuseumContext context) : base(context)
        {
        }


        public async Task<IEnumerable<Artwork>> GetAllArtWorksAsync()
        {
            return await FindAll().OrderBy(art => art.Name).ToListAsync();
        }

        public async Task<Artwork> GetArtworkById(int artId)
        {
            return await FindByCondition(art => art.Id.Equals(artId))
                .FirstOrDefaultAsync();
            
        }
        

        public async Task<IEnumerable<Artwork>> GetArtworksByRoom(string Location)
        {
            return await FindAll().Where(art => art.Location.Equals(Location))
                .ToListAsync();
        }

        public void CreateArtWork(Artwork artwork)
        {
            
            Create(artwork);
            var storage = context.Rooms.Find("Storage");
            if (storage.CurrentCapacity != storage.TotalCapacity)
            {
                storage.ArtworkList = new List<Artwork>();
                storage.ArtworkList.Add(artwork);
                storage.CurrentCapacity++; 
            }

        }

     

        public string MoveArtwork(int artId, string location)
        {
            var artworkobject = context.Artworks.Find(artId);
            artworkobject.Location = location; 

            var previousroom =  context.Rooms.Find(artworkobject.Location);
            var newRoom = context.Rooms.Find(location);

            if (previousroom == null)
            {
                Console.WriteLine("The previous room is null");
            }
            else if (artworkobject == null)
            {
                Console.WriteLine("The artwork is null");
            }  
            else if (newRoom == null)
            {
                Console.WriteLine("The new room is null");
            }

            if (previousroom != null)
            {
                previousroom.ArtworkList = new List<Artwork>();
                previousroom.ArtworkList.Remove(artworkobject);
                previousroom.CurrentCapacity--;
            }

            
            if (newRoom.CurrentCapacity != newRoom.TotalCapacity)
            { newRoom.ArtworkList = new List<Artwork>();
                newRoom.ArtworkList.Add(artworkobject);
                newRoom.CurrentCapacity++;
            }
            else
            {
                return "Storage is full, artwork is not assigned to any room";
            }

            context.SaveChanges();
            return "Room has been added to the Storage";

           
        }

        

        public void UpdateArtwork(Artwork artwork)
        {
            Update(artwork);
        }

        public void DeleteArtwork(Artwork artwork)
        {
            var room = context.Rooms.Find(artwork.Location);
            
            switch (room.LocationCode)
            {
                case "Storage":
                    room.ArtworkList = new List<Artwork>();
                    room.ArtworkList.Remove(artwork);
                    room.CurrentCapacity++;
                    break;
                
                case "A1":
                    room.ArtworkList = new List<Artwork>();
                    room.ArtworkList.Remove(artwork);
                    room.CurrentCapacity++;
                    break;
                
                case "A2":
                    room.ArtworkList = new List<Artwork>();
                    room.ArtworkList.Remove(artwork);
                    room.CurrentCapacity++;
                    break;
                
                case "A3":
                    room.ArtworkList = new List<Artwork>();
                    room.ArtworkList.Remove(artwork);
                    room.CurrentCapacity++;
                    break;
                
                case "B1":
                    room.ArtworkList = new List<Artwork>();
                    room.ArtworkList.Remove(artwork);
                    room.CurrentCapacity++;
                    break;
                
                case "B2":
                    room.ArtworkList = new List<Artwork>();
                    room.ArtworkList.Remove(artwork);
                    room.CurrentCapacity++;
                    break;
                
                case "B3":
                    room.ArtworkList = new List<Artwork>();
                    room.ArtworkList.Remove(artwork);
                    room.CurrentCapacity++;
                    break;
                
                case "B4":
                    room.ArtworkList = new List<Artwork>();
                    room.ArtworkList.Remove(artwork);
                    room.CurrentCapacity++;
                    break;
                
                default:
                    Console.WriteLine("Room was not assigned to any room");
                    break;
                
            }
            Delete(artwork);

            context.SaveChanges();
        }

        public bool ArtworkExists(int artId)
        {
            return context.Artworks.Any(e => e.Id.Equals(artId));
        }

        public  Task saveChanges()
        {
           return  context.SaveChangesAsync();
        }
        
     
    }
}