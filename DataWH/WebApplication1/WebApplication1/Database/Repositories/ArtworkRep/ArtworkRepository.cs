using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Query;
using WebApplication1.Datamodel;

namespace WebApplication1.Database.Repositories.ArtworkRep
{

    using System.Linq;
    using WebApplication1.Database.Repositories;
    using WebApplication1.Database.Repositories.ArtworkRep;
    using WebApplication1.Datamodel;

    public class ArtworkRepository : RepositoryBase<Artwork>, IArtworkRepository
    {
        public ArtworkRepository(MuseumContext context) : base(context)
        {
        }

        public async Task<IEnumerable<Artwork>> getAllArtworksAsync()
        {
            return await FindAll().OrderBy(art => art.Name)
                .ToListAsync();
        }


        public async Task<Artwork> GetArtworkByIdAsync(int artId)
        {
            return await FindByCondition(art => art.Id.Equals(artId))
                .FirstOrDefaultAsync();
        }

        // public async Task<Artwork> GetArtworkWithDetailsAsync(int artId)
        // {
        //     return await FindByCondition(art => art.Id == artId)
        //         .Where(artId == 2);
        // }
        

        public void createArtwork(Artwork artwork)
        {
            Create(artwork);
            context.SaveChangesAsync();
        }

        public void updateArtwork(Artwork artwork)
        {
            context.Entry(artwork).State = EntityState.Modified;
            context.SaveChangesAsync();
        }

        public void deleteArtwork(Artwork artwork)
        {
            Delete(artwork);
            context.SaveChangesAsync();
        }

        public bool artExists(int artId)
        {
            return context.Artworks.Any(e => e.Id == artId);
        }

        public void saveChanges()
        {
            context.SaveChangesAsync();
        }



    }
}