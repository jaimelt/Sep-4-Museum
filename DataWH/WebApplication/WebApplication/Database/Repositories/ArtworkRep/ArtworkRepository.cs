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
            
        }

        public void UpdateArtwork(Artwork artwork)
        {
            Update(artwork);
        }

        public void DeleteArtwork(Artwork artwork)
        {
            Delete(artwork);
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