using Microsoft.EntityFrameworkCore;
using REST_SEP4.Datamodel;

namespace REST_SEP4.Database
{
    public class RoomContext : DbContext
    {
        
        public DbSet<Room> Rooms { get; set; }
        public DbSet<Measurements> Measurements { get; set; }
        public DbSet<ArtworkMeasurements> ArtWorkMeasurements { get; set;  }
        public DbSet<Artwork> Artworks { get; set; }
        
        public RoomContext(DbContextOptions<RoomContext> options) : base(options)
        {
            
        }
        
        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.UseSqlite("Data source = museum.db");
        }
    }
}