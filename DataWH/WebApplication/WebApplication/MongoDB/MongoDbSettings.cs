namespace WebApplication.MongoDB
{
    public class MongoDbSettings : IMongoDBSettings
    {
        public MongoDbSettings()
        {
            MeasurementsCollectionName = "test";
            ConnectionString = "mongodb+srv://adminsep4iot:kk7ojsEwek8yOk8m@sep4iot-5ef3i.azure.mongodb.net/test?retryWrites=true&w=majority";
            DatabaseName = "sep4iot";
        }

        public string MeasurementsCollectionName { get; set; }
        public string ConnectionString { get; set; }
        public string DatabaseName { get; set; }
    }
}