using Microsoft.EntityFrameworkCore;
using WebApplication1.Datamodel;

namespace WebApplication.Database
{
    public class MuseumContext : DbContext
    {
        public MuseumContext(DbContextOptions options) : base(options)
        {
            
        }
        
        public DbSet<Administrator> Administrators { get; set; }
        public DbSet<Artwork> Artworks { get; set; }
        public DbSet<Museum> Museum { get; set; }
        public DbSet<Room> Rooms { get; set; }
        public DbSet<RoomMeasurement> RoomMeasurements { get; set; }
        public DbSet<Visitor> Visitors { get; set; }
    }
    }
