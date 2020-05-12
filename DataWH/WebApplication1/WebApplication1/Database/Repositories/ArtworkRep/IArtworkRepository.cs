using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using WebApplication1.Datamodel;

namespace WebApplication1.Database.Repositories.ArtworkRep
{
    public interface IArtworkRepository : IRepositoryBase<Artwork>
    {
        Task<IEnumerable<Artwork>> getAllArtworksAsync();
        Task<Artwork> GetArtworkByIdAsync(string artId);
        Task<Artwork> GetArtworkWithDetailsAsync(string artId);
        void createArtwork(Artwork artwork);
        void updateArtwork(Artwork artwork);
        void deleteArtwork(Artwork artwork);

        bool artExists(string artId);

        void saveChanges();

    }
}