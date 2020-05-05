using Microsoft.EntityFrameworkCore;
using WebApplication1.Datamodel;

namespace WebApplication1.Database
{
    public class MuseumContext : DbContext
    {
        public MuseumContext(DbContextOptions opt) : base(opt)
        {
            
        }
        
        public DbSet<Museum> Museum { get; set; }
        public DbSet<Administrator> Administrators { get; set; }
        public DbSet<Room> Rooms { get; set; }
        public DbSet<RoomMeasurement> OptimalMeasurements { get; set; }
        public DbSet<RoomMeasurement> MeasurementConditions { get; set; }
        public DbSet<Artwork> Artworks { get; set; }
        public DbSet<ArtworkMeasurement> ArtworkMeasurements { get; set; }
    }
}