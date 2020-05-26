using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.HttpsPolicy;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Logging;
using Microsoft.Extensions.Options;
using WebApplication.Database;
using WebApplication.Database.Repositories.AccountRep;
using WebApplication.Database.Repositories.ArtworkRep;
using WebApplication.Database.Repositories.RoomRep;
using WebApplication.MongoDB;


namespace WebApplication
{
    public class Startup
    {
        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public IConfiguration Configuration { get; }

        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services)
        { services.Configure<MongoDbSettings>(
              Configuration.GetSection(nameof(MongoDbSettings)));
           services.AddScoped<ArtworkRepository>();
           services.AddScoped<RoomRepository>();
          services.AddScoped<MongoRepository>();
          services.AddScoped<AccountRepository>();
         string ConnectionString = Configuration.GetConnectionString("DefaultConnection");
            services.AddDbContext<MuseumContext>(opt =>
                opt.UseSqlServer("Server=sqlserversss.database.windows.net;Database=museum;User Id=museum;password=Mus12345;MultipleActiveResultSets=True;"));
            services.AddControllers();
            
            
           // services.AddSingleton<IMongoDBSettings>(sp =>
          //      sp.GetRequiredService<IOptions<MongoDbSettings>>().Value);
            
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IWebHostEnvironment env)
        {
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
            }

            app.UseHttpsRedirection();

            app.UseRouting();

            app.UseAuthorization();

            app.UseEndpoints(endpoints => { endpoints.MapControllers(); });
        }
    }
}