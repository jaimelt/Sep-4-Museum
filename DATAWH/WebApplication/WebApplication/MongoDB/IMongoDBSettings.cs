namespace WebApplication.MongoDB
{
    public interface IMongoDBSettings
    {
         string MeasurementsCollectionName { get; set; }
         
         string ConnectionString { get; set; }
         string DatabaseName { get; set; }
         
         
    }
}