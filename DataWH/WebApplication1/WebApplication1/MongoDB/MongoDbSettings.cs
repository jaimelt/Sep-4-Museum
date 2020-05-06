namespace WebApplication1.MongoDB
{
    public class MongoDbSettings : IMongoDBSettings
    {
        public string MeasurementsCollectionName { get; set; }
        public string ConnectionString { get; set; }
        public string DatabaseName { get; set; }
    }
}