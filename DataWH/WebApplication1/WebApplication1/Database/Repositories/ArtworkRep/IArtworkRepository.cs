using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using WebApplication1.Datamodel;

namespace WebApplication1.Database.Repositories.ArtworkRep
{
    public interface IArtworkRepository : IRepositoryBase<Artwork>
    {
        Task<IEnumerable<Artwork>> getAllArtworksAsync();
        Task<Artwork> GetArtworkByIdAsync(int artId);
        Task<Artwork> GetArtworkWithDetailsAsync(int artId);
        void createArtwork(Artwork artwork);
        void updateArtwork(Artwork artwork);

        void updateArtworkMeasurement(ArtworkMeasurement artworkMeasurement);
        void deleteArtwork(Artwork artwork);

        bool artExists(int artId);

        void saveChanges();

    }
}