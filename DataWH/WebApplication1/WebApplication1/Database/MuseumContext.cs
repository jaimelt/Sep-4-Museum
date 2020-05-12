using Microsoft.EntityFrameworkCore;
using WebApplication1.Datamodel;

namespace WebApplication1.Database
{
    public class MuseumContext : DbContext
    {
        public MuseumContext(DbContextOptions options) : base(options)
        {
            
        }
        
        public DbSet<Administrator> Administrators { get; set; }
        public DbSet<Artwork> Artworks { get; set; }
        public DbSet<ArtworkMeasurement> ArtworkMeasurements { get; set; }
        public DbSet<Museum> Museum { get; set; }
        public DbSet<Room> Rooms { get; set; }
        public DbSet<RoomMeasurement> RoomMeasurements { get; set; }
    }
}