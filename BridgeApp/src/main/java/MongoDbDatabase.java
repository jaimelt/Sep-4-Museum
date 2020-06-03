import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Date;

/**
 * An implementation of {@linkplain IDatabase} repository based on a mongoDb database.
 *
 * @author Marina Ionel
 * @version 1.0.0
 */
public class MongoDbDatabase implements IDatabase {
    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;

    //    constants
    private final String DATA_COLLECTION = "data";
    private final String DATABASE_NAME = "sep4iot";
    private final MongoClientURI MONGO_CLIENT_URI = new MongoClientURI("mongodb+srv://adminsep4iot:kk7ojsEwek8yOk8m@sep4iot-5ef3i.azure.mongodb.net/test?retryWrites=true&w=majority");

    /**
     * A plain constructor initializing the fields.
     */
    public MongoDbDatabase() {
        mongoClient = new MongoClient(MONGO_CLIENT_URI);
        mongoDatabase = mongoClient.getDatabase(DATABASE_NAME);
    }

    /**
     * The implementation of {@linkplain IDatabase#insert(int, double, double, double, int)}
     */
    @Override
    public void insert(int co2, double humidity, double temperature, double light, int roomId) {
        var document = new Document().append("room_no", roomId)
                .append("time", new Date())
                .append("co2", co2)
                .append("humidity", humidity)
                .append("temperature", temperature)
                .append("light", light);
        mongoDatabase.getCollection(DATA_COLLECTION).insertOne(document);
    }

}
